import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { ContratoComponent } from './contrato.component';
import { ContratoDetailComponent } from './contrato-detail.component';
import { ContratoPopupComponent } from './contrato-dialog.component';
import { ContratoDeletePopupComponent } from './contrato-delete-dialog.component';

@Injectable()
export class ContratoResolvePagingParams implements Resolve<any> {

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

export const contratoRoute: Routes = [
    {
        path: 'contrato',
        component: ContratoComponent,
        resolve: {
            'pagingParams': ContratoResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.contrato.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'contrato/:id',
        component: ContratoDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.contrato.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const contratoPopupRoute: Routes = [
    {
        path: 'contrato-new',
        component: ContratoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.contrato.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'contrato/:id/edit',
        component: ContratoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.contrato.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'contrato/:id/delete',
        component: ContratoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.contrato.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
