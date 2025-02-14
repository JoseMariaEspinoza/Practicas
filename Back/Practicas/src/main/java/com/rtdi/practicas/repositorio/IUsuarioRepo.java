package com.rtdi.practicas.repositorio;

import com.rtdi.practicas.modelo.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepo extends IGenericRepo<Usuario, String> {
}
