package com.rtdi.practicas.dto;

import java.util.List;

public interface Reporte {
    Integer getIdCategoria();
    String getEnlaceImagen();
    List<String> getNombreCategoria();
    Double getPorcentajeVistos();
    List<String> getVideosNoVistos();
}
