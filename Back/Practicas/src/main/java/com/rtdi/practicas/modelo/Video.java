package com.rtdi.practicas.modelo;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_video")
    private Integer idVideo;

    @Column(length = 255, nullable = false)
    private String enlaceImagen;

    @Column(length = 255, nullable = false)
    private String titulo;

    @Column(nullable = false)
    private Integer duracion;

    @Column(length = 255, nullable = false)
    private String enlaceVideo;
}
