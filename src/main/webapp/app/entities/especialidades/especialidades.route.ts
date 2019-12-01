import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { EspecialidadesComponent } from './especialidades.component';
import { EspecialidadesDetailComponent } from './especialidades-detail.component';
import { EspecialidadesPopupComponent } from './especialidades-dialog.component';
import { EspecialidadesDeletePopupComponent } from './especialidades-delete-dialog.component';

@Injectable()
export class EspecialidadesResolvePagingParams implements Resolve<any> {

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

export const especialidadesRoute: Routes = [
    {
        path: 'especialidades',
        component: EspecialidadesComponent,
        resolve: {
            'pagingParams': EspecialidadesResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.especialidades.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'especialidades/:id',
        component: EspecialidadesDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.especialidades.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const especialidadesPopupRoute: Routes = [
    {
        path: 'especialidades-new',
        component: EspecialidadesPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.especialidades.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'especialidades/:id/edit',
        component: EspecialidadesPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.especialidades.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'especialidades/:id/delete',
        component: EspecialidadesDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.especialidades.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
