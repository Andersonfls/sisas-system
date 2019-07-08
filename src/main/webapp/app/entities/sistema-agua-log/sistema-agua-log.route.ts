import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { SistemaAguaLogComponent } from './sistema-agua-log.component';
import { SistemaAguaLogDetailComponent } from './sistema-agua-log-detail.component';
import { SistemaAguaLogPopupComponent } from './sistema-agua-log-dialog.component';
import { SistemaAguaLogDeletePopupComponent } from './sistema-agua-log-delete-dialog.component';

@Injectable()
export class SistemaAguaLogResolvePagingParams implements Resolve<any> {

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

export const sistemaAguaLogRoute: Routes = [
    {
        path: 'sistema-agua-log',
        component: SistemaAguaLogComponent,
        resolve: {
            'pagingParams': SistemaAguaLogResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.sistemaAguaLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'sistema-agua-log/:id',
        component: SistemaAguaLogDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.sistemaAguaLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sistemaAguaLogPopupRoute: Routes = [
    {
        path: 'sistema-agua-log-new',
        component: SistemaAguaLogPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.sistemaAguaLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'sistema-agua-log/:id/edit',
        component: SistemaAguaLogPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.sistemaAguaLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'sistema-agua-log/:id/delete',
        component: SistemaAguaLogDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.sistemaAguaLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
