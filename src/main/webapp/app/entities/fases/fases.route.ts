import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { FasesComponent } from './fases.component';
import { FasesDetailComponent } from './fases-detail.component';
import { FasesPopupComponent } from './fases-dialog.component';
import { FasesDeletePopupComponent } from './fases-delete-dialog.component';

@Injectable()
export class FasesResolvePagingParams implements Resolve<any> {

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

export const fasesRoute: Routes = [
    {
        path: 'fases',
        component: FasesComponent,
        resolve: {
            'pagingParams': FasesResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.fases.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'fases/:id',
        component: FasesDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.fases.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const fasesPopupRoute: Routes = [
    {
        path: 'fases-new',
        component: FasesPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.fases.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'fases/:id/edit',
        component: FasesPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.fases.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'fases/:id/delete',
        component: FasesDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.fases.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
