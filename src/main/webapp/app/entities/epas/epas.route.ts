import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { EpasComponent } from './epas.component';
import { EpasDetailComponent } from './epas-detail.component';
import { EpasPopupComponent } from './epas-dialog.component';
import { EpasDeletePopupComponent } from './epas-delete-dialog.component';

@Injectable()
export class EpasResolvePagingParams implements Resolve<any> {

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

export const epasRoute: Routes = [
    {
        path: 'epas',
        component: EpasComponent,
        resolve: {
            'pagingParams': EpasResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.epas.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'epas/:id',
        component: EpasDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.epas.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const epasPopupRoute: Routes = [
    {
        path: 'epas-new',
        component: EpasPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.epas.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'epas/:id/edit',
        component: EpasPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.epas.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'epas/:id/delete',
        component: EpasDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.epas.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
