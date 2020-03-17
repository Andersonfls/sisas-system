import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { ConcursoComponent } from './concurso.component';
import { ConcursoDetailComponent } from './concurso-detail.component';
import { ConcursoPopupComponent } from './concurso-dialog.component';
import { ConcursoDeletePopupComponent } from './concurso-delete-dialog.component';

@Injectable()
export class ConcursoResolvePagingParams implements Resolve<any> {

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

export const concursoRoute: Routes = [
    {
        path: 'concurso',
        component: ConcursoComponent,
        resolve: {
            'pagingParams': ConcursoResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.concurso.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'concurso/:id',
        component: ConcursoDetailComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.concurso.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const concursoPopupRoute: Routes = [
    {
        path: 'concurso-new',
        component: ConcursoPopupComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.concurso.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'concurso/:id/edit',
        component: ConcursoPopupComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.concurso.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'concurso/:id/delete',
        component: ConcursoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.concurso.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
