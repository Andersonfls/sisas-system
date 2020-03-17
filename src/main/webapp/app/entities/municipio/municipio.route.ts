import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { MunicipioComponent } from './municipio.component';
import { MunicipioDetailComponent } from './municipio-detail.component';
import { MunicipioPopupComponent } from './municipio-dialog.component';
import { MunicipioDeletePopupComponent } from './municipio-delete-dialog.component';

@Injectable()
export class MunicipioResolvePagingParams implements Resolve<any> {

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

export const municipioRoute: Routes = [
    {
        path: 'municipio',
        component: MunicipioComponent,
        resolve: {
            'pagingParams': MunicipioResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'ADMIN_PROVINCIAL'],
            pageTitle: 'sisasApp.municipio.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'municipio/:id',
        component: MunicipioDetailComponent,
        data: {
            authorities: ['ROLE_USER', 'ADMIN_PROVINCIAL'],
            pageTitle: 'sisasApp.municipio.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const municipioPopupRoute: Routes = [
    {
        path: 'municipio-new',
        component: MunicipioPopupComponent,
        data: {
            authorities: ['ROLE_USER', 'ADMIN_PROVINCIAL'],
            pageTitle: 'sisasApp.municipio.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'municipio/:id/edit',
        component: MunicipioPopupComponent,
        data: {
            authorities: ['ROLE_USER', 'ADMIN_PROVINCIAL'],
            pageTitle: 'sisasApp.municipio.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'municipio/:id/delete',
        component: MunicipioDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER', 'ADMIN_PROVINCIAL'],
            pageTitle: 'sisasApp.municipio.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
