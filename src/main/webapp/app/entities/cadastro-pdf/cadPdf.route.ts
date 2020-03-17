import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { ProdutoPopupComponent } from './cadPdf-dialog.component';
import { ProdutoDeletePopupComponent } from './cadPdf-delete-dialog.component';
import {CadPdfComponent} from './cadPdf.component';

export const cadPdfRoute: Routes = [
    {
        path: 'pdf',
        component: CadPdfComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.pdf.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const cadPdfPopupRoute: Routes = [
    {
        path: 'pdf-new',
        component: ProdutoPopupComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.pdf.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'pdf/:id/edit',
        component: ProdutoPopupComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.pdf.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'pdf/:id/delete',
        component: ProdutoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.pdf.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
