import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import {FinalidadeProjetoComponent} from './finalidade-projeto.component';
import {FinalidadeProjetoDetailComponent} from './finalidade-projeto-detail.component';
import {FinalidadeProjetoPopupComponent} from './finalidade-projeto-dialog.component';
import {FinalidadeProjetoDeletePopupComponent} from './finalidade-projeto-delete-dialog.component';
@Injectable()
export class FinalidadeProjetoResolvePagingParams implements Resolve<any> {

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

export const finalidadeProjetoRoute: Routes = [
    {
        path: 'finalidade-projeto',
        component: FinalidadeProjetoComponent,
        resolve: {
            'pagingParams': FinalidadeProjetoResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.finalidadeProjeto.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'finalidade-projeto/:id',
        component: FinalidadeProjetoDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.finalidadeProjeto.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const finalidadeProjetoPopupRoute: Routes = [
    {
        path: 'finalidade-projeto-new',
        component: FinalidadeProjetoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.finalidadeProjeto.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'finalidade-projeto/:id/edit',
        component: FinalidadeProjetoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.finalidadeProjeto.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'finalidade-projeto/:id/delete',
        component: FinalidadeProjetoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.finalidadeProjeto.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
