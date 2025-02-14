package com.rtdi.practicas.servicio;

import com.rtdi.practicas.dto.Reporte;
import com.rtdi.practicas.modelo.Categoria;

import java.util.List;

public interface IServicioCategoria extends ICRUD<Categoria, Integer> {
    List<Reporte> obtenerReporte(String email);
}
