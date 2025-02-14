package com.rtdi.practicas.servicio.impl;

import com.rtdi.practicas.modelo.Historial;
import com.rtdi.practicas.repositorio.IGenericRepo;
import com.rtdi.practicas.repositorio.IHistorialRepo;
import com.rtdi.practicas.servicio.IServicioHistorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioHistorial extends CRUDImpl<Historial, Integer> implements IServicioHistorial {

    @Autowired
    private IHistorialRepo repoHistorial;

    @Override
    protected IGenericRepo<Historial, Integer> getRepo() {
        return repoHistorial;
    }
}
