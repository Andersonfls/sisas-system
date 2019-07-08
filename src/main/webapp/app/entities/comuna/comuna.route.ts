import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { ComunaComponent } from './comuna.component';
import { ComunaDetailComponent } from './comuna-detail.component';
import { ComunaPopupComponent } from './comuna-dialog.component';
import { ComunaDeletePopupComponent } from './comuna-delete-dialog.component';

@Injectable()
export class ComunaResolvePagingParams implements Resolve<any> {

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

export const comunaRoute: Routes = [
    {
        path: 'comuna',
        component: ComunaComponent,
        resolve: {
            'pagingParams': ComunaResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.comuna.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'comuna/:id',
        component: ComunaDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.comuna.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const comunaPopupRoute: Routes = [
    {
        path: 'comuna-new',
        component: ComunaPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.comuna.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'comuna/:id/edit',
        component: ComunaPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.comuna.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'comuna/:id/delete',
        component: ComunaDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.comuna.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
