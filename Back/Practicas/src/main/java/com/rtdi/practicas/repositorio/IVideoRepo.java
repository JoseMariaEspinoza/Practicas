package com.rtdi.practicas.repositorio;

import com.rtdi.practicas.modelo.Video;
import org.springframework.stereotype.Repository;

@Repository
public interface IVideoRepo extends IGenericRepo<Video, Integer> {
}
