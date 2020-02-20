import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { IndicadorProducaoProvinciaComponent} from './indicador-producao-provincia.component';

@Injectable()
export class IndicadorProducaoProvinciaResolvePagingParams implements Resolve<any> {

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

export const indicadorProducaoProvinciaRoute: Routes = [
    {
        path: 'indicador-producao-provincia',
        component: IndicadorProducaoProvinciaComponent,
        resolve: {
            'pagingParams': IndicadorProducaoProvinciaResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN'],
            pageTitle: 'relatorios.title.indicador-producao-provincia'
        },
        canActivate: [UserRouteAccessService]
    }
];
