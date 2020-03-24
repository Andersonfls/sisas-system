import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { SistemaAguaBombManualComunalComponent} from './sistema-agua-bmb-manual-comunal.component';

@Injectable()
export class SistemaAguaBombManualComunalResolvePagingParams implements Resolve<any> {

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

export const sistemaAguaTpBombaManualComunalRoute: Routes = [
    {
        path: 'sistema-agua-ft-subte-manual-comunal',
        component: SistemaAguaBombManualComunalComponent,
        resolve: {
            'pagingParams': SistemaAguaBombManualComunalResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN', 'ADMIN_PROVINCIAL'],
            pageTitle: 'relatorios.title.sistema-agua-fonte-subterranea-manual-comunal'
        },
        canActivate: [UserRouteAccessService]
    }
];
