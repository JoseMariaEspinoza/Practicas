package com.rtdi.practicas.dto.impl;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReporteDTO {
    private List<Integer> idCategoria;
    private String enlaceImagen;
    private String nombreCategoria;
    private Double porcentajeVistos;
    private List<String> videosNoVistos;
}
