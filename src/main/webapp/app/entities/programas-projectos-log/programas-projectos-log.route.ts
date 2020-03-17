import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { ProgramasProjectosLogComponent } from './programas-projectos-log.component';
import { ProgramasProjectosLogDetailComponent } from './programas-projectos-log-detail.component';
import { ProgramasProjectosLogPopupComponent } from './programas-projectos-log-dialog.component';
import { ProgramasProjectosLogDeletePopupComponent } from './programas-projectos-log-delete-dialog.component';

@Injectable()
export class ProgramasProjectosLogResolvePagingParams implements Resolve<any> {

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

export const programasProjectosLogRoute: Routes = [
    {
        path: 'programas-projectos-log',
        component: ProgramasProjectosLogComponent,
        resolve: {
            'pagingParams': ProgramasProjectosLogResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.programasProjectosLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'programas-projectos-log/:id',
        component: ProgramasProjectosLogDetailComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.programasProjectosLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const programasProjectosLogPopupRoute: Routes = [
    {
        path: 'programas-projectos-log-new',
        component: ProgramasProjectosLogPopupComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.programasProjectosLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'programas-projectos-log/:id/edit',
        component: ProgramasProjectosLogPopupComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.programasProjectosLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'programas-projectos-log/:id/delete',
        component: ProgramasProjectosLogDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.programasProjectosLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
