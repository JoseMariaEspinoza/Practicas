package com.rtdi.practicas.modelo;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @Column(length = 150, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 16, nullable = false)
    private String contrasenia;
}
