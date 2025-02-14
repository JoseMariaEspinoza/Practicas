package com.rtdi.practicas.servicio.impl;

import com.rtdi.practicas.repositorio.IGenericRepo;
import com.rtdi.practicas.servicio.ICRUD;

import java.util.List;
import java.util.Optional;

public abstract class CRUDImpl<T,ID> implements ICRUD<T,ID> {

    protected abstract IGenericRepo<T,ID> getRepo();

    public T registrar(T t) throws Exception{
        return getRepo().save(t);
    }
    public T modificar(T t) throws Exception{
        return getRepo().save(t);
    }
    public List<T> listar() throws Exception{
        return getRepo().findAll();
    }
    public T listarPorId(ID id) throws Exception{
        Optional<T> t1 = getRepo().findById(id);
        return t1.isPresent() ? t1.get() : null;
    }
    public void eliminar(ID id) throws Exception{
        getRepo().deleteById(id);
    }
}