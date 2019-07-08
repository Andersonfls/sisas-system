import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { AdjudicacaoComponent } from './adjudicacao.component';
import { AdjudicacaoDetailComponent } from './adjudicacao-detail.component';
import { AdjudicacaoPopupComponent } from './adjudicacao-dialog.component';
import { AdjudicacaoDeletePopupComponent } from './adjudicacao-delete-dialog.component';

@Injectable()
export class AdjudicacaoResolvePagingParams implements Resolve<any> {

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

export const adjudicacaoRoute: Routes = [
    {
        path: 'adjudicacao',
        component: AdjudicacaoComponent,
        resolve: {
            'pagingParams': AdjudicacaoResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.adjudicacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'adjudicacao/:id',
        component: AdjudicacaoDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.adjudicacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const adjudicacaoPopupRoute: Routes = [
    {
        path: 'adjudicacao-new',
        component: AdjudicacaoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.adjudicacao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'adjudicacao/:id/edit',
        component: AdjudicacaoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.adjudicacao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'adjudicacao/:id/delete',
        component: AdjudicacaoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.adjudicacao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
