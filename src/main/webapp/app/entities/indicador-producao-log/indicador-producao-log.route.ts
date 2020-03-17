import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { IndicadorProducaoLogComponent } from './indicador-producao-log.component';
import { IndicadorProducaoLogDetailComponent } from './indicador-producao-log-detail.component';
import { IndicadorProducaoLogPopupComponent } from './indicador-producao-log-dialog.component';
import { IndicadorProducaoLogDeletePopupComponent } from './indicador-producao-log-delete-dialog.component';

@Injectable()
export class IndicadorProducaoLogResolvePagingParams implements Resolve<any> {

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

export const indicadorProducaoLogRoute: Routes = [
    {
        path: 'indicador-producao-log',
        component: IndicadorProducaoLogComponent,
        resolve: {
            'pagingParams': IndicadorProducaoLogResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.indicadorProducaoLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'indicador-producao-log/:id',
        component: IndicadorProducaoLogDetailComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.indicadorProducaoLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const indicadorProducaoLogPopupRoute: Routes = [
    {
        path: 'indicador-producao-log-new',
        component: IndicadorProducaoLogPopupComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.indicadorProducaoLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'indicador-producao-log/:id/edit',
        component: IndicadorProducaoLogPopupComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.indicadorProducaoLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'indicador-producao-log/:id/delete',
        component: IndicadorProducaoLogDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.indicadorProducaoLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
