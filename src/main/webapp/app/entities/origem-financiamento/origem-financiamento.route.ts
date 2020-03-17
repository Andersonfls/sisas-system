import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { OrigemFinanciamentoComponent } from './origem-financiamento.component';
import { OrigemFinanciamentoDetailComponent } from './origem-financiamento-detail.component';
import { OrigemFinanciamentoPopupComponent } from './origem-financiamento-dialog.component';
import { OrigemFinanciamentoDeletePopupComponent } from './origem-financiamento-delete-dialog.component';

@Injectable()
export class OrigemFinanciamentoResolvePagingParams implements Resolve<any> {

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

export const origemFinanciamentoRoute: Routes = [
    {
        path: 'origem-financiamento',
        component: OrigemFinanciamentoComponent,
        resolve: {
            'pagingParams': OrigemFinanciamentoResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.origemFinanciamento.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'origem-financiamento/:id',
        component: OrigemFinanciamentoDetailComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.origemFinanciamento.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const origemFinanciamentoPopupRoute: Routes = [
    {
        path: 'origem-financiamento-new',
        component: OrigemFinanciamentoPopupComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.origemFinanciamento.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'origem-financiamento/:id/edit',
        component: OrigemFinanciamentoPopupComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.origemFinanciamento.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'origem-financiamento/:id/delete',
        component: OrigemFinanciamentoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.origemFinanciamento.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
