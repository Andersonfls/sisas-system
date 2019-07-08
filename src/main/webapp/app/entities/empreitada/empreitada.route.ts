import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { EmpreitadaComponent } from './empreitada.component';
import { EmpreitadaDetailComponent } from './empreitada-detail.component';
import { EmpreitadaPopupComponent } from './empreitada-dialog.component';
import { EmpreitadaDeletePopupComponent } from './empreitada-delete-dialog.component';

@Injectable()
export class EmpreitadaResolvePagingParams implements Resolve<any> {

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

export const empreitadaRoute: Routes = [
    {
        path: 'empreitada',
        component: EmpreitadaComponent,
        resolve: {
            'pagingParams': EmpreitadaResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.empreitada.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'empreitada/:id',
        component: EmpreitadaDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.empreitada.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const empreitadaPopupRoute: Routes = [
    {
        path: 'empreitada-new',
        component: EmpreitadaPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.empreitada.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'empreitada/:id/edit',
        component: EmpreitadaPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.empreitada.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'empreitada/:id/delete',
        component: EmpreitadaDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.empreitada.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
