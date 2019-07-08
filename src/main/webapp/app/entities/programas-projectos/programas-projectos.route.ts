import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { ProgramasProjectosComponent } from './programas-projectos.component';
import { ProgramasProjectosDetailComponent } from './programas-projectos-detail.component';
import { ProgramasProjectosPopupComponent } from './programas-projectos-dialog.component';
import { ProgramasProjectosDeletePopupComponent } from './programas-projectos-delete-dialog.component';

@Injectable()
export class ProgramasProjectosResolvePagingParams implements Resolve<any> {

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

export const programasProjectosRoute: Routes = [
    {
        path: 'programas-projectos',
        component: ProgramasProjectosComponent,
        resolve: {
            'pagingParams': ProgramasProjectosResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.programasProjectos.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'programas-projectos/:id',
        component: ProgramasProjectosDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.programasProjectos.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const programasProjectosPopupRoute: Routes = [
    {
        path: 'programas-projectos-new',
        component: ProgramasProjectosPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.programasProjectos.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'programas-projectos/:id/edit',
        component: ProgramasProjectosPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.programasProjectos.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'programas-projectos/:id/delete',
        component: ProgramasProjectosDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.programasProjectos.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
