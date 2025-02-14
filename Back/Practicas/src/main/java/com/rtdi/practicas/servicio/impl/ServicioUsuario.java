package com.rtdi.practicas.servicio.impl;

import com.rtdi.practicas.modelo.Usuario;
import com.rtdi.practicas.repositorio.IGenericRepo;
import com.rtdi.practicas.repositorio.IUsuarioRepo;
import com.rtdi.practicas.servicio.IServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioUsuario extends CRUDImpl<Usuario, String> implements IServicioUsuario {

    @Autowired
    private IUsuarioRepo repoUsuario;

    @Override
    protected IGenericRepo<Usuario, String> getRepo() {
        return repoUsuario;
    }

}
