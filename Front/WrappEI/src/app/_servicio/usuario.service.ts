import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginDTO, UsuarioRespuestaDTO } from '../_modelo/Usuario';
import { entorno } from '../_entorno/entorno';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {

  private url: string = `${entorno.HOST}/usuarios`;

  constructor(private http: HttpClient) {}

  login(datos: LoginDTO): Observable<UsuarioRespuestaDTO> {
    return this.http.post<UsuarioRespuestaDTO>(`${this.url}/login`, datos);
  }
}
