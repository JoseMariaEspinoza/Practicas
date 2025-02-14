import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Reporte } from '../_modelo/Categoria';
import { CategoriaService } from '../_servicio/categoria.service';

@Component({
  selector: 'app-reporte',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './reporte.component.html',
  styleUrl: './reporte.component.css'
})
export class ReporteComponent implements OnInit {
  reporte: Reporte[] = [];
  mensajeError: string = '';

  constructor(private categoriaService: CategoriaService) {}

  ngOnInit(): void {
    // Comprobación si estamos en el navegador
    if (typeof window !== 'undefined' && window.localStorage) {
      const email = localStorage.getItem('userEmail');
      
      if (email) {
        this.categoriaService.obtenerTopCategorias(email).subscribe({
          next: (categorias) => {
            this.reporte = categorias;
          },
          error: (err) => {
            this.mensajeError = 'Error al obtener las categorías';
          },
        });
      } else {
        this.mensajeError = 'No se encontró el email del usuario.';
      }
    } else {
      this.mensajeError = 'localStorage no está disponible en este entorno.';
    }
  }
  
}
