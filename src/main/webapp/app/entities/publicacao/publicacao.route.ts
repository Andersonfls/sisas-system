import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { PublicacaoComponent } from './publicacao.component';
import { PublicacaoDetailComponent } from './publicacao-detail.component';
import { PublicacaoPopupComponent } from './publicacao-dialog.component';
import { PublicacaoDeletePopupComponent } from './publicacao-delete-dialog.component';

@Injectable()
export class PublicacaoResolvePagingParams implements Resolve<any> {

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

export const publicacaoRoute: Routes = [
    {
        path: 'publicacao',
        component: PublicacaoComponent,
        resolve: {
            'pagingParams': PublicacaoResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.publicacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'publicacao/:id',
        component: PublicacaoDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.publicacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const publicacaoPopupRoute: Routes = [
    {
        path: 'publicacao-new',
        component: PublicacaoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.publicacao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'publicacao/:id/edit',
        component: PublicacaoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.publicacao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'publicacao/:id/delete',
        component: PublicacaoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.publicacao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
