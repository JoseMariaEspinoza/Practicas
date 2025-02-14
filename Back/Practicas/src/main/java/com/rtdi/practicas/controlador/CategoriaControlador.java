package com.rtdi.practicas.controlador;


import com.rtdi.practicas.dto.impl.ReporteDTO;
import com.rtdi.practicas.servicio.IServicioCategoria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("categorias") // El endpoint base
public class CategoriaControlador {

    @Autowired
    private IServicioCategoria servicioCategoria;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/top-5/{email}")
    public ResponseEntity<List<ReporteDTO>> obtenerReporte(@PathVariable("email")String email) {
        List<ReporteDTO> categorias = servicioCategoria.obtenerReporte(email)
                .stream()
                .map(categoria -> mapper.map(categoria, ReporteDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }


}

