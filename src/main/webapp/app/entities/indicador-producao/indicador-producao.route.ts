import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { IndicadorProducaoComponent } from './indicador-producao.component';
import { IndicadorProducaoDetailComponent } from './indicador-producao-detail.component';
import {IndicadorProducaoDialogComponent} from './indicador-producao-dialog.component';
import { IndicadorProducaoDeletePopupComponent } from './indicador-producao-delete-dialog.component';
import {ProgramasProjectosDialogComponent} from '../programas-projectos';

@Injectable()
export class IndicadorProducaoResolvePagingParams implements Resolve<any> {

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

export const indicadorProducaoRoute: Routes = [
    {
        path: 'indicador-producao',
        component: IndicadorProducaoComponent,
        resolve: {
            'pagingParams': IndicadorProducaoResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN_LOCAL'],
            pageTitle: 'sisasApp.indicadorProducao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'indicador-producao/:id',
        component: IndicadorProducaoDetailComponent,
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN_LOCAL'],
            pageTitle: 'sisasApp.indicadorProducao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },

    {
        path: 'indicador-producao-new',
        component: IndicadorProducaoDialogComponent,
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN_LOCAL'],
            pageTitle: 'sisasApp.indicadorProducao.home.title'
        },
        canActivate: [UserRouteAccessService],
    },
    {
        path: 'indicador-producao/:id/edit',
        component: IndicadorProducaoDialogComponent,
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN_LOCAL'],
            pageTitle: 'sisasApp.indicadorProducao.home.title'
        },
        canActivate: [UserRouteAccessService],
    },
    {
        path: 'indicador-producao-edit/:id',
        component: IndicadorProducaoDialogComponent,
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN_LOCAL'],
            pageTitle: 'sisasApp.indicadorProducao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const indicadorProducaoPopupRoute: Routes = [
    // {
    //     path: 'indicador-producao-new',
    //     component: IndicadorProducaoPopupComponent,
    //     data: {
    //         authorities: ['ROLE_USER'],
    //         pageTitle: 'sisasApp.indicadorProducao.home.title'
    //     },
    //     canActivate: [UserRouteAccessService],
    //     outlet: 'popup'
    // },
    // {
    //     path: 'indicador-producao/:id/edit',
    //     component: IndicadorProducaoPopupComponent,
    //     data: {
    //         authorities: ['ROLE_USER'],
    //         pageTitle: 'sisasApp.indicadorProducao.home.title'
    //     },
    //     canActivate: [UserRouteAccessService],
    //     outlet: 'popup'
    // },
    {
        path: 'indicador-producao/:id/delete',
        component: IndicadorProducaoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.indicadorProducao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
