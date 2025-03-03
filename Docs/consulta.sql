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
        COALESCE(ARRAY_AGG(DISTINCT h.id_video ORDER BY h.id_video) FILTER (WHERE h.id_video IS NOT NULL), '{}'::int[]) AS videos_vistos,
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
        cvist.videos_vistos,
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
        ARRAY_AGG(tc.id_categoria ORDER BY tc.id_categoria) AS id_categorias,
        STRING_AGG(tc.nombre_categoria, ', ') AS nombre_categoria,
        tc.porcentaje_vistos,
        tc.videos_categoria,
        tc.videos_vistos
    FROM top_categorias tc
    GROUP BY tc.videos_categoria, tc.videos_vistos, tc.porcentaje_vistos
),
videos_no_vistos AS (
    SELECT
        vc.id_categoria,
        COALESCE(ARRAY_REMOVE(ARRAY_AGG(v.enlace_video ORDER BY v.id_video), NULL), ARRAY[]::text[]) AS videos_no_vistos
    FROM video_categoria vc
    JOIN videos v ON vc.id_video = v.id_video
    LEFT JOIN historial h ON vc.id_video = h.id_video AND h.email = 'usuario1@email.com'
    GROUP BY vc.id_categoria
),
enlaces_categoria AS (
    SELECT
        vc.id_categoria,
        vc.id_video,
        v.enlace_imagen
    FROM video_categoria vc
    JOIN videos v ON vc.id_video = v.id_video
    WHERE v.enlace_imagen IS NOT NULL
),
enlaces_disponibles AS (
    SELECT DISTINCT ON (ec.id_categoria) ec.id_categoria, ec.enlace_imagen
    FROM enlaces_categoria ec
    ORDER BY ec.id_categoria, ec.enlace_imagen
),
enlaces_filtrados AS (
    SELECT
        ed.id_categoria,
        ed.enlace_imagen
    FROM enlaces_disponibles ed
    WHERE NOT EXISTS (
        SELECT 1 FROM enlaces_disponibles ed2
        WHERE ed2.enlace_imagen = ed.enlace_imagen
        AND ed2.id_categoria < ed.id_categoria
    )
),
categorias_con_imagen AS (
    SELECT
        ca.id_categorias,
        ca.nombre_categoria,
        ca.porcentaje_vistos,
        ef.enlace_imagen,
        COALESCE(ARRAY_AGG(DISTINCT vnv.videos_no_vistos) FILTER (WHERE vnv.videos_no_vistos IS NOT NULL), '{}'::text[]) AS videos_no_vistos
    FROM categorias_agrupadas ca
    JOIN enlaces_filtrados ef ON ef.id_categoria = ANY(ca.id_categorias)
    LEFT JOIN videos_no_vistos vnv ON vnv.id_categoria = ANY(ca.id_categorias)
    GROUP BY ca.id_categorias, ca.nombre_categoria, ca.porcentaje_vistos, ef.enlace_imagen
    ORDER BY ca.porcentaje_vistos DESC
)
SELECT *
FROM categorias_con_imagen
LIMIT 5;
