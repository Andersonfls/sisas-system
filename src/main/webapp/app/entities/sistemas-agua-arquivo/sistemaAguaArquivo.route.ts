import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import {SistemaAguaArquivoComponent} from './sistemaAguaArquivo.component';

export const sistemaAguaArquivoRoute: Routes = [
    {
        path: 'sistema-arquivo',
        component: SistemaAguaArquivoComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.pdf.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const cadPdfPopupRoute: Routes = [
    {
        path: 'sist-agua-arquivo',
        component: SistemaAguaArquivoComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.pdf.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
