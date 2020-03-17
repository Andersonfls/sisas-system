import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { ProvinciaComponent } from './provincia.component';
import { ProvinciaDetailComponent } from './provincia-detail.component';
import { ProvinciaPopupComponent } from './provincia-dialog.component';
import { ProvinciaDeletePopupComponent } from './provincia-delete-dialog.component';

@Injectable()
export class ProvinciaResolvePagingParams implements Resolve<any> {

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

export const provinciaRoute: Routes = [
    {
        path: 'provincia',
        component: ProvinciaComponent,
        resolve: {
            'pagingParams': ProvinciaResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'ADMIN_PROVINCIAL'],
            pageTitle: 'sisasApp.provincia.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'provincia/:id',
        component: ProvinciaDetailComponent,
        data: {
            authorities: ['ROLE_USER', 'ADMIN_PROVINCIAL'],
            pageTitle: 'sisasApp.provincia.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const provinciaPopupRoute: Routes = [
    {
        path: 'provincia-new',
        component: ProvinciaPopupComponent,
        data: {
            authorities: ['ROLE_USER', 'ADMIN_PROVINCIAL'],
            pageTitle: 'sisasApp.provincia.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'provincia/:id/edit',
        component: ProvinciaPopupComponent,
        data: {
            authorities: ['ROLE_USER', 'ADMIN_PROVINCIAL'],
            pageTitle: 'sisasApp.provincia.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'provincia/:id/delete',
        component: ProvinciaDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER', 'ADMIN_PROVINCIAL'],
            pageTitle: 'sisasApp.provincia.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
