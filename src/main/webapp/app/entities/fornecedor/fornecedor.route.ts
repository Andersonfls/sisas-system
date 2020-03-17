import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { FornecedorComponent } from './fornecedor.component';
import { FornecedorDetailComponent } from './fornecedor-detail.component';
import { FornecedorPopupComponent } from './fornecedor-dialog.component';
import { FornecedorDeletePopupComponent } from './fornecedor-delete-dialog.component';

@Injectable()
export class FornecedorResolvePagingParams implements Resolve<any> {

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

export const fornecedorRoute: Routes = [
    {
        path: 'fornecedor',
        component: FornecedorComponent,
        resolve: {
            'pagingParams': FornecedorResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'ADMIN_PROVINCIAL'],
            pageTitle: 'sisasApp.fornecedor.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'fornecedor/:id',
        component: FornecedorDetailComponent,
        data: {
            authorities: ['ROLE_USER', 'ADMIN_PROVINCIAL'],
            pageTitle: 'sisasApp.fornecedor.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const fornecedorPopupRoute: Routes = [
    {
        path: 'fornecedor-new',
        component: FornecedorPopupComponent,
        data: {
            authorities: ['ROLE_USER', 'ADMIN_PROVINCIAL'],
            pageTitle: 'sisasApp.fornecedor.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'fornecedor/:id/edit',
        component: FornecedorPopupComponent,
        data: {
            authorities: ['ROLE_USER', 'ADMIN_PROVINCIAL'],
            pageTitle: 'sisasApp.fornecedor.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'fornecedor/:id/delete',
        component: FornecedorDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER', 'ADMIN_PROVINCIAL'],
            pageTitle: 'sisasApp.fornecedor.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
