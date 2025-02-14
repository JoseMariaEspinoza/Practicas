package com.rtdi.practicas.modelo;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(length = 100, nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;
}
