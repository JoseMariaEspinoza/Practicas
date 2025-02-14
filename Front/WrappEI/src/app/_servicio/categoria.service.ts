import { Injectable } from '@angular/core';
import { entorno } from '../_entorno/entorno';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reporte } from '../_modelo/Categoria';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  private url: string = `${entorno.HOST}/categorias`;

  constructor(private http: HttpClient) { }

  obtenerTopCategorias(email: string): Observable<Reporte[]> {
    return this.http.get<Reporte[]>(`${this.url}/top-5/${email}`);
  }
}
