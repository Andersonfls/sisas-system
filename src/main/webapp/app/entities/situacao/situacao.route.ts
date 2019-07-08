import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { SituacaoComponent } from './situacao.component';
import { SituacaoDetailComponent } from './situacao-detail.component';
import { SituacaoPopupComponent } from './situacao-dialog.component';
import { SituacaoDeletePopupComponent } from './situacao-delete-dialog.component';

@Injectable()
export class SituacaoResolvePagingParams implements Resolve<any> {

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

export const situacaoRoute: Routes = [
    {
        path: 'situacao',
        component: SituacaoComponent,
        resolve: {
            'pagingParams': SituacaoResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.situacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'situacao/:id',
        component: SituacaoDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.situacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const situacaoPopupRoute: Routes = [
    {
        path: 'situacao-new',
        component: SituacaoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.situacao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'situacao/:id/edit',
        component: SituacaoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.situacao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'situacao/:id/delete',
        component: SituacaoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.situacao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
