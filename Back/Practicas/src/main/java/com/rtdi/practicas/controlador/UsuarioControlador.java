package com.rtdi.practicas.controlador;

import com.rtdi.practicas.dto.impl.LoginDTO;
import com.rtdi.practicas.dto.impl.UsuarioRespuestaDTO;
import com.rtdi.practicas.modelo.Usuario;
import com.rtdi.practicas.servicio.IServicioUsuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {

    @Autowired
    private IServicioUsuario servicioUsuario;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/login")
    public ResponseEntity<?> autenticarUsuario(@RequestBody LoginDTO loginDTO) {
        // Buscar usuario por email usando el servicio
        Usuario usuario = null;
        try {
            usuario = servicioUsuario.listarPorId(loginDTO.getEmail());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (usuario != null) {
            // Si el usuario existe, comprobar la contraseña (verificar que coincida con la base de datos)
            if (usuario.getContrasenia().equals(loginDTO.getContrasenia())) {
                // Si la contraseña es correcta, devolver los datos del usuario sin la contraseña
                UsuarioRespuestaDTO usuarioRespuestaDTO = mapper.map(usuario, UsuarioRespuestaDTO.class);
                return ResponseEntity.ok(usuarioRespuestaDTO);
            } else {
                // Contraseña incorrecta
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
            }
        } else {
            // Usuario no encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }
}

