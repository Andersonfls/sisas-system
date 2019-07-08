import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { EntidadeGestoraComponent } from './entidade-gestora.component';
import { EntidadeGestoraDetailComponent } from './entidade-gestora-detail.component';
import { EntidadeGestoraPopupComponent } from './entidade-gestora-dialog.component';
import { EntidadeGestoraDeletePopupComponent } from './entidade-gestora-delete-dialog.component';

@Injectable()
export class EntidadeGestoraResolvePagingParams implements Resolve<any> {

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

export const entidadeGestoraRoute: Routes = [
    {
        path: 'entidade-gestora',
        component: EntidadeGestoraComponent,
        resolve: {
            'pagingParams': EntidadeGestoraResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.entidadeGestora.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'entidade-gestora/:id',
        component: EntidadeGestoraDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.entidadeGestora.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const entidadeGestoraPopupRoute: Routes = [
    {
        path: 'entidade-gestora-new',
        component: EntidadeGestoraPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.entidadeGestora.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'entidade-gestora/:id/edit',
        component: EntidadeGestoraPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.entidadeGestora.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'entidade-gestora/:id/delete',
        component: EntidadeGestoraDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.entidadeGestora.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
