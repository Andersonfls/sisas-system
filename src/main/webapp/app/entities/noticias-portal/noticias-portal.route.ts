import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { NoticiasPortalComponent } from './noticias-portal.component';
import { NoticiasPortalDetailComponent } from './noticias-portal-detail.component';
import { NoticiasPortalPopupComponent } from './noticias-portal-dialog.component';
import { NoticiasPortalDeletePopupComponent } from './noticias-portal-delete-dialog.component';

@Injectable()
export class NoticiasPortalResolvePagingParams implements Resolve<any> {

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

export const noticiasPortalRoute: Routes = [
    {
        path: 'noticias-portal',
        component: NoticiasPortalComponent,
        resolve: {
            'pagingParams': NoticiasPortalResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.noticiasPortal.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'noticias-portal/:id',
        component: NoticiasPortalDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.noticiasPortal.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const noticiasPortalPopupRoute: Routes = [
    {
        path: 'noticias-portal-new',
        component: NoticiasPortalPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.noticiasPortal.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'noticias-portal/:id/edit',
        component: NoticiasPortalPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.noticiasPortal.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'noticias-portal/:id/delete',
        component: NoticiasPortalDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.noticiasPortal.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
