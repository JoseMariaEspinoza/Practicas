package com.rtdi.practicas.modelo;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "video_categoria")
@IdClass(VideoCategoria.class)
public class VideoCategoria {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_video", nullable = false, foreignKey = @ForeignKey(name = "video_categoria_id_video_fkey"))
    private Video video;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false, foreignKey = @ForeignKey(name = "video_categoria_id_categoria_fkey"))
    private Categoria categoria;
}
