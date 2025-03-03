package com.rtdi.practicas.dto;

import java.util.List;

public interface Reporte {
    List<Integer> getIdCategoria();
    String getEnlaceImagen();
    String getNombreCategoria();
    Double getPorcentajeVistos();
    List<String> getVideosNoVistos();
}
