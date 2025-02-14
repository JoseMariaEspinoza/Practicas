package com.rtdi.practicas.servicio.impl;

import com.rtdi.practicas.dto.Reporte;
import com.rtdi.practicas.modelo.Categoria;
import com.rtdi.practicas.repositorio.ICategoriaRepo;
import com.rtdi.practicas.repositorio.IGenericRepo;
import com.rtdi.practicas.servicio.IServicioCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioCategoria extends CRUDImpl<Categoria, Integer> implements IServicioCategoria {

    @Autowired
    private ICategoriaRepo repoCategoria;

    @Override
    protected IGenericRepo<Categoria, Integer> getRepo() {
        return repoCategoria;
    }

    @Override
    public List<Reporte> obtenerReporte(String email) {
        return repoCategoria.obtenerReporte(email);
    }

}
