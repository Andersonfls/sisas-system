import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { SistemaAguaComponent } from './sistema-agua.component';

@Injectable()
export class SistemaAguaResolvePagingParams implements Resolve<any> {

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

export const sistemaAguaRoute: Routes = [
    {
        path: 'sistema-agua-export',
        component: SistemaAguaComponent,
        resolve: {
            'pagingParams': SistemaAguaResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'ADMIN_PROVINCIAL'],
            pageTitle: 'sisasApp.sistemaAgua.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sistemaAguaPopupRoute: Routes = [
];
