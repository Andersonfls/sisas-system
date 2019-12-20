import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { BannerComponent } from './banner.component';
import { BannerDetailComponent } from './banner-detail.component';
import { ProdutoPopupComponent } from './banner-dialog.component';
import { ProdutoDeletePopupComponent } from './banner-delete-dialog.component';

export const bannerRoute: Routes = [
    {
        path: 'banner',
        component: BannerComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.comuna.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'banner/:id',
        component: BannerDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.comuna.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const produtoPopupRoute: Routes = [
    {
        path: 'banner-new',
        component: ProdutoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.comuna.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'banner/:id/edit',
        component: ProdutoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.comuna.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'banner/:id/delete',
        component: ProdutoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.comuna.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
