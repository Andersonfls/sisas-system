import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { IndicadorProducaoComponent } from './indicador-producao.component';

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
        path: 'indicador-producao-export',
        component: IndicadorProducaoComponent,
        resolve: {
            'pagingParams': IndicadorProducaoResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'ADMIN_PROVINCIAL'],
            pageTitle: 'sisasApp.indicadorProducao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const indicadorProducaoPopupRoute: Routes = [
];
