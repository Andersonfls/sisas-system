import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { ConfiguracoesLogComponent } from './configuracoes-log.component';
import { ConfiguracoesLogDetailComponent } from './configuracoes-log-detail.component';
import { ConfiguracoesLogPopupComponent } from './configuracoes-log-dialog.component';
import { ConfiguracoesLogDeletePopupComponent } from './configuracoes-log-delete-dialog.component';

@Injectable()
export class ConfiguracoesLogResolvePagingParams implements Resolve<any> {

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

export const configuracoesLogRoute: Routes = [
    {
        path: 'configuracoes-log',
        component: ConfiguracoesLogComponent,
        resolve: {
            'pagingParams': ConfiguracoesLogResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.configuracoesLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'configuracoes-log/:id',
        component: ConfiguracoesLogDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.configuracoesLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const configuracoesLogPopupRoute: Routes = [
    {
        path: 'configuracoes-log-new',
        component: ConfiguracoesLogPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.configuracoesLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'configuracoes-log/:id/edit',
        component: ConfiguracoesLogPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.configuracoesLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'configuracoes-log/:id/delete',
        component: ConfiguracoesLogDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.configuracoesLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
