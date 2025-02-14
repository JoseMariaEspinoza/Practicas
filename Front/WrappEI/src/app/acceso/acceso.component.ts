import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router'; // Importar Router
import { UsuarioService } from '../_servicio/usuario.service';
import { LoginDTO } from '../_modelo/Usuario';

@Component({
  selector: 'app-acceso',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './acceso.component.html',
  styleUrls: ['./acceso.component.css']
})
export class AccesoComponent {
  email: string = '';
  contrasenia: string = '';
  mensajeError: string = '';

  constructor(
    private usuarioService: UsuarioService,
    private router: Router  // Inyectar Router
  ) {}

  iniciarSesion() {
    const datos: LoginDTO = { email: this.email, contrasenia: this.contrasenia };

    this.usuarioService.login(datos).subscribe({
      next: (usuario) => {
        console.log('Inicio de sesión exitoso', usuario);
        alert(`Bienvenido, ${usuario.nombre}`);
        // Guardar el email en el localStorage
        localStorage.setItem('userEmail', this.email);
        // Redirigir al componente Reporte
        this.router.navigate(['/reporte']);  // Redirigir a la página de reporte
      },
      error: (err) => {
        if (err.status === 401) {
          this.mensajeError = 'Contraseña incorrecta';
        } else if (err.status === 404) {
          this.mensajeError = 'Usuario no encontrado';
        } else {
          this.mensajeError = 'Error en el inicio de sesión';
        }
      },
    });
  }
}
