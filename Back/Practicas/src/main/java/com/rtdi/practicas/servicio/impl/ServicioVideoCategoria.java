package com.rtdi.practicas.servicio.impl;

import com.rtdi.practicas.modelo.VideoCategoria;
import com.rtdi.practicas.repositorio.IGenericRepo;
import com.rtdi.practicas.repositorio.IVideoCategoriaRepo;
import com.rtdi.practicas.servicio.IServicioVideoCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioVideoCategoria extends CRUDImpl<VideoCategoria, Integer> implements IServicioVideoCategoria {

    @Autowired
    private IVideoCategoriaRepo repoVideoCategoria;

    @Override
    protected IGenericRepo<VideoCategoria, Integer> getRepo() {
        return repoVideoCategoria;
    }
}
