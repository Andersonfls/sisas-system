import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { ConcepcaoComponent } from './concepcao.component';
import { ConcepcaoDetailComponent } from './concepcao-detail.component';
import { ConcepcaoPopupComponent } from './concepcao-dialog.component';
import { ConcepcaoDeletePopupComponent } from './concepcao-delete-dialog.component';

@Injectable()
export class ConcepcaoResolvePagingParams implements Resolve<any> {

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

export const concepcaoRoute: Routes = [
    {
        path: 'concepcao',
        component: ConcepcaoComponent,
        resolve: {
            'pagingParams': ConcepcaoResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.concepcao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'concepcao/:id',
        component: ConcepcaoDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.concepcao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const concepcaoPopupRoute: Routes = [
    {
        path: 'concepcao-new',
        component: ConcepcaoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.concepcao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'concepcao/:id/edit',
        component: ConcepcaoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.concepcao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'concepcao/:id/delete',
        component: ConcepcaoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.concepcao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
