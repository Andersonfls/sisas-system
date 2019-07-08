import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { PublicacaoLogComponent } from './publicacao-log.component';
import { PublicacaoLogDetailComponent } from './publicacao-log-detail.component';
import { PublicacaoLogPopupComponent } from './publicacao-log-dialog.component';
import { PublicacaoLogDeletePopupComponent } from './publicacao-log-delete-dialog.component';

@Injectable()
export class PublicacaoLogResolvePagingParams implements Resolve<any> {

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

export const publicacaoLogRoute: Routes = [
    {
        path: 'publicacao-log',
        component: PublicacaoLogComponent,
        resolve: {
            'pagingParams': PublicacaoLogResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.publicacaoLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'publicacao-log/:id',
        component: PublicacaoLogDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.publicacaoLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const publicacaoLogPopupRoute: Routes = [
    {
        path: 'publicacao-log-new',
        component: PublicacaoLogPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.publicacaoLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'publicacao-log/:id/edit',
        component: PublicacaoLogPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.publicacaoLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'publicacao-log/:id/delete',
        component: PublicacaoLogDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.publicacaoLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
