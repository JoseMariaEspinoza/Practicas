package com.rtdi.practicas.repositorio;

import com.rtdi.practicas.modelo.VideoCategoria;
import org.springframework.stereotype.Repository;

@Repository
public interface IVideoCategoriaRepo extends IGenericRepo<VideoCategoria, Integer> {
}
