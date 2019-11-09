import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { SistemaAguaComponent } from './sistema-agua.component';
import { SistemaAguaDetailComponent } from './sistema-agua-detail.component';
import {SistemaAguaDialogComponent, SistemaAguaPopupComponent} from './sistema-agua-dialog.component';
import { SistemaAguaDeletePopupComponent } from './sistema-agua-delete-dialog.component';

@Injectable()
export class SistemaAguaResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const sistemaAguaRoute: Routes = [
    {
        path: 'sistema-agua',
        component: SistemaAguaComponent,
        resolve: {
            'pagingParams': SistemaAguaResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.sistemaAgua.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'sistema-agua/:id',
        component: SistemaAguaDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.sistemaAgua.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sistema-agua-new',
        component: SistemaAguaDialogComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.sistemaAgua.home.title'
        },
        canActivate: [UserRouteAccessService],
    },
    {
        path: 'sistema-agua/:id/edit',
        component: SistemaAguaDialogComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.sistemaAgua.home.title'
        },
        canActivate: [UserRouteAccessService],
    }
];

export const sistemaAguaPopupRoute: Routes = [
    // {
    //     path: 'sistema-agua-new',
    //     component: SistemaAguaPopupComponent,
    //     data: {
    //         authorities: ['ROLE_USER'],
    //         pageTitle: 'sisasApp.sistemaAgua.home.title'
    //     },
    //     canActivate: [UserRouteAccessService],
    //     outlet: 'popup'
    // },
    // {
    //     path: 'sistema-agua/:id/edit',
    //     component: SistemaAguaPopupComponent,
    //     data: {
    //         authorities: ['ROLE_USER'],
    //         pageTitle: 'sisasApp.sistemaAgua.home.title'
    //     },
    //     canActivate: [UserRouteAccessService],
    //     outlet: 'popup'
    // },
    {
        path: 'sistema-agua/:id/delete',
        component: SistemaAguaDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.sistemaAgua.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
