package com.rtdi.practicas.servicio.impl;

import com.rtdi.practicas.modelo.Video;
import com.rtdi.practicas.repositorio.IGenericRepo;
import com.rtdi.practicas.repositorio.IVideoRepo;
import com.rtdi.practicas.servicio.IServicioVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioVideo extends CRUDImpl<Video, Integer> implements IServicioVideo {

    @Autowired
    private IVideoRepo repoVideo;

    @Override
    protected IGenericRepo<Video, Integer> getRepo() {
        return repoVideo;
    }
}
