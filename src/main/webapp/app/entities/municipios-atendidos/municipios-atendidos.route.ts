import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { MunicipiosAtendidosComponent } from './municipios-atendidos.component';
import { MunicipiosAtendidosDetailComponent } from './municipios-atendidos-detail.component';
import { MunicipiosAtendidosPopupComponent } from './municipios-atendidos-dialog.component';
import { MunicipiosAtendidosDeletePopupComponent } from './municipios-atendidos-delete-dialog.component';

@Injectable()
export class MunicipiosAtendidosResolvePagingParams implements Resolve<any> {

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

export const municipiosAtendidosRoute: Routes = [
    {
        path: 'municipios-atendidos',
        component: MunicipiosAtendidosComponent,
        resolve: {
            'pagingParams': MunicipiosAtendidosResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.municipiosAtendidos.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'municipios-atendidos/:id',
        component: MunicipiosAtendidosDetailComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.municipiosAtendidos.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const municipiosAtendidosPopupRoute: Routes = [
    {
        path: 'municipios-atendidos-new',
        component: MunicipiosAtendidosPopupComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.municipiosAtendidos.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'municipios-atendidos/:id/edit',
        component: MunicipiosAtendidosPopupComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.municipiosAtendidos.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'municipios-atendidos/:id/delete',
        component: MunicipiosAtendidosDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL'],
            pageTitle: 'sisasApp.municipiosAtendidos.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
