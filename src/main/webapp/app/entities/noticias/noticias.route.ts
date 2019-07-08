import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { NoticiasComponent } from './noticias.component';
import { NoticiasDetailComponent } from './noticias-detail.component';
import { NoticiasPopupComponent } from './noticias-dialog.component';
import { NoticiasDeletePopupComponent } from './noticias-delete-dialog.component';

@Injectable()
export class NoticiasResolvePagingParams implements Resolve<any> {

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

export const noticiasRoute: Routes = [
    {
        path: 'noticias',
        component: NoticiasComponent,
        resolve: {
            'pagingParams': NoticiasResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.noticias.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'noticias/:id',
        component: NoticiasDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.noticias.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const noticiasPopupRoute: Routes = [
    {
        path: 'noticias-new',
        component: NoticiasPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.noticias.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'noticias/:id/edit',
        component: NoticiasPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.noticias.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'noticias/:id/delete',
        component: NoticiasDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.noticias.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
