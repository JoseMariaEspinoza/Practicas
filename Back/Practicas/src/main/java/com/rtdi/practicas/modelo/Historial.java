package com.rtdi.practicas.modelo;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "historial")
@IdClass(Historial.class)
public class Historial {

    @Id
    @ManyToOne
    @JoinColumn(name = "email", nullable = false, foreignKey = @ForeignKey(name = "historial_email_fkey"))
    private Usuario usuario;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_video", nullable = false, foreignKey = @ForeignKey(name = "historial_id_video_fkey"))
    private Video video;
}
