import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { RelatoriosLogComponent } from './relatorios-log.component';
import { RelatoriosLogDetailComponent } from './relatorios-log-detail.component';
import { RelatoriosLogPopupComponent } from './relatorios-log-dialog.component';
import { RelatoriosLogDeletePopupComponent } from './relatorios-log-delete-dialog.component';

@Injectable()
export class RelatoriosLogResolvePagingParams implements Resolve<any> {

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

export const relatoriosLogRoute: Routes = [
    {
        path: 'relatorios-log',
        component: RelatoriosLogComponent,
        resolve: {
            'pagingParams': RelatoriosLogResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.relatoriosLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'relatorios-log/:id',
        component: RelatoriosLogDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.relatoriosLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const relatoriosLogPopupRoute: Routes = [
    {
        path: 'relatorios-log-new',
        component: RelatoriosLogPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.relatoriosLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'relatorios-log/:id/edit',
        component: RelatoriosLogPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.relatoriosLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'relatorios-log/:id/delete',
        component: RelatoriosLogDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.relatoriosLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
