import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import {IndicadorArquivoComponent} from './indicadorArquivo.component';

export const indicadorArquivoRoute: Routes = [
    {
        path: 'indicador-arquivo',
        component: IndicadorArquivoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.pdf.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const cadPdfPopupRoute: Routes = [
    {
        path: 'ind-arquivo',
        component: IndicadorArquivoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.pdf.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
