package com.rtdi.practicas.dto.impl;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReporteDTO {
    private Integer idCategoria;
    private String enlaceImagen;
    private List<String> nombreCategoria;
    private Double porcentajeVistos;
    private List<String> videosNoVistos;
}
