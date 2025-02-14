import { Routes } from '@angular/router';
import { AccesoComponent } from './acceso/acceso.component';
import { ReporteComponent } from './reporte/reporte.component';

export const routes: Routes = [
    { path: 'acceso', component: AccesoComponent },
    { path: 'reporte', component: ReporteComponent},
    { path: '', redirectTo: '/acceso', pathMatch: 'full' }
];
