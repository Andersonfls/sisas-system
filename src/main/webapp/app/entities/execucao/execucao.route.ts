import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { ExecucaoComponent } from './execucao.component';
import { ExecucaoDetailComponent } from './execucao-detail.component';
import { ExecucaoPopupComponent } from './execucao-dialog.component';
import { ExecucaoDeletePopupComponent } from './execucao-delete-dialog.component';

@Injectable()
export class ExecucaoResolvePagingParams implements Resolve<any> {

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

export const execucaoRoute: Routes = [
    {
        path: 'execucao',
        component: ExecucaoComponent,
        resolve: {
            'pagingParams': ExecucaoResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.execucao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'execucao/:id',
        component: ExecucaoDetailComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.execucao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const execucaoPopupRoute: Routes = [
    {
        path: 'execucao-new',
        component: ExecucaoPopupComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.execucao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'execucao/:id/edit',
        component: ExecucaoPopupComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.execucao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'execucao/:id/delete',
        component: ExecucaoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.execucao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
