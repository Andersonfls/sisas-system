import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { InicioComponent } from './inicio.component';
import { InicioDetailComponent } from './inicio-detail.component';
import { InicioPopupComponent } from './inicio-dialog.component';
import { InicioDeletePopupComponent } from './inicio-delete-dialog.component';

@Injectable()
export class InicioResolvePagingParams implements Resolve<any> {

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

export const inicioRoute: Routes = [
    {
        path: 'inicio',
        component: InicioComponent,
        resolve: {
            'pagingParams': InicioResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.inicio.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'inicio/:id',
        component: InicioDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.inicio.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const inicioPopupRoute: Routes = [
    {
        path: 'inicio-new',
        component: InicioPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.inicio.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'inicio/:id/edit',
        component: InicioPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.inicio.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'inicio/:id/delete',
        component: InicioDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.inicio.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
