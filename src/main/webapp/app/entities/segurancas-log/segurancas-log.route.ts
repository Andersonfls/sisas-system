import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { SegurancasLogComponent } from './segurancas-log.component';
import { SegurancasLogDetailComponent } from './segurancas-log-detail.component';
import { RelatoriosLogPopupComponent } from './segurancas-log-dialog.component';

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

export const segurancasLogRoute: Routes = [
    {
        path: 'segurancas-log',
        component: SegurancasLogComponent,
        resolve: {
            'pagingParams': RelatoriosLogResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.segurancasLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'segurancas-log/:id',
        component: SegurancasLogDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.segurancasLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const relatoriosLogPopupRoute: Routes = [
    {
        path: 'segurancas-log-new',
        component: RelatoriosLogPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.segurancassLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'segurancas-log/:id/edit',
        component: RelatoriosLogPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.segurancassLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
