package com.rtdi.practicas.repositorio;

import com.rtdi.practicas.dto.Reporte;
import com.rtdi.practicas.modelo.Categoria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoriaRepo extends IGenericRepo<Categoria,Integer> {

    @Query(value = """
            WITH categoria_videos AS (
                SELECT
                    vc.id_categoria,
                    ARRAY_AGG(DISTINCT vc.id_video ORDER BY vc.id_video) AS videos_categoria,
                    COUNT(DISTINCT vc.id_video) AS total_videos
                FROM video_categoria vc
                GROUP BY vc.id_categoria
            ),
            categoria_vistos AS (
                SELECT
                    vc.id_categoria,
                    ARRAY_AGG(DISTINCT vc.id_video ORDER BY vc.id_video) FILTER (WHERE h.id_video IS NOT NULL) AS videos_vistos,
                    COUNT(DISTINCT h.id_video) AS total_vistos
                FROM video_categoria vc
                LEFT JOIN historial h ON vc.id_video = h.id_video AND h.email = :email
                GROUP BY vc.id_categoria
            ),
            top_categorias AS (
                SELECT
                    cat.id_categoria,
                    cat.nombre AS nombre_categoria,
                    cv.videos_categoria,
                    COALESCE(cvist.videos_vistos, '{}'::int[]) AS videos_vistos,
                    cv.total_videos,
                    COALESCE(cvist.total_vistos, 0) AS total_vistos,
                    CASE
                        WHEN cv.total_videos > 0 THEN ROUND((COALESCE(cvist.total_vistos, 0) * 100.0 / cv.total_videos), 2)
                        ELSE 0
                    END AS porcentaje_vistos
                FROM categorias cat
                LEFT JOIN categoria_videos cv ON cat.id_categoria = cv.id_categoria
                LEFT JOIN categoria_vistos cvist ON cat.id_categoria = cvist.id_categoria
            ),
            categorias_agrupadas AS (
                SELECT
                    STRING_AGG(tc.nombre_categoria, ', ') AS nombre_categoria,
                    ARRAY_AGG(tc.id_categoria ORDER BY tc.id_categoria) AS id_categorias,
                    tc.videos_categoria,
                    tc.videos_vistos,
                    tc.porcentaje_vistos,
                    ARRAY(
                        SELECT unnest(tc.videos_categoria)
                        EXCEPT
                        SELECT unnest(tc.videos_vistos)
                    ) AS videos_no_vistos
                FROM top_categorias tc
                GROUP BY tc.videos_categoria, tc.videos_vistos, tc.porcentaje_vistos
            ),
            enlaces_disponibles AS (
                SELECT DISTINCT ON (v.id_video)
                    v.id_video,
                    v.enlace_video  -- Cambiamos de enlace_imagen a enlace_video
                FROM videos v
                WHERE v.enlace_video IS NOT NULL  -- Aseguramos que el campo es enlace_video
                ORDER BY v.id_video
            ),
            enlaces_usados AS (
                SELECT
                    ed.enlace_video,  -- Cambiamos de enlace_imagen a enlace_video
                    ed.id_video
                FROM enlaces_disponibles ed
                WHERE ed.id_video IN (SELECT unnest(videos_no_vistos) FROM categorias_agrupadas)
            ),
            enlaces_prioritarios AS (
                SELECT
                    v.id_video,
                    v.enlace_video  -- Cambiamos de enlace_imagen a enlace_video
                FROM videos v
                WHERE v.enlace_video IS NOT NULL  -- Aseguramos que el campo es enlace_video
                ORDER BY v.id_video
            ),
            enlaces_categoria AS (
                SELECT
                    vc.id_categoria,
                    vc.id_video,
                    v.enlace_video  -- Cambiamos de enlace_imagen a enlace_video
                FROM video_categoria vc
                JOIN videos v ON vc.id_video = v.id_video
                WHERE v.enlace_video IS NOT NULL  -- Aseguramos que el campo es enlace_video
            )
            SELECT
                ca.id_categorias[1] AS id_categoria,
                ca.nombre_categoria,
                ca.porcentaje_vistos,
                ARRAY(
                    SELECT v.enlace_video  -- Cambiamos de enlace_imagen a enlace_video
                    FROM videos v
                    WHERE v.id_video = ANY(ca.videos_no_vistos)
                ) AS enlaces_video_no_vistos,  -- Aquí también cambiamos para devolver enlaces_video
                COALESCE(
                    (SELECT ec.enlace_video  -- Cambiamos de enlace_imagen a enlace_video
                     FROM enlaces_categoria ec
                     WHERE ec.id_categoria = ca.id_categorias[1]
                     AND ec.id_video = ANY(ca.videos_no_vistos)
                     LIMIT 1),
                    (SELECT ep.enlace_video  -- Cambiamos de enlace_imagen a enlace_video
                     FROM enlaces_prioritarios ep
                     LIMIT 1)
                ) AS enlace_video
            FROM categorias_agrupadas ca
            ORDER BY ca.porcentaje_vistos DESC
            LIMIT 5;
        """, nativeQuery = true)
    List<Reporte> obtenerReporte(@Param("email") String email);

}

