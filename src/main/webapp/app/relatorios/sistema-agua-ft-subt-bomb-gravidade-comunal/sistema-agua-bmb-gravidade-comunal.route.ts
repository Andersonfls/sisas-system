import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { SistemaAguaBombGravidadeComunalComponent} from './sistema-agua-bmb-gravidade-comunal.component';

@Injectable()
export class SistemaAguaBombGravidadeComunalResolvePagingParams implements Resolve<any> {

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

export const sistemaAguaTpBombaGravidadeComunalRoute: Routes = [
    {
        path: 'sistema-agua-ft-subte-gravidade-comunal',
        component: SistemaAguaBombGravidadeComunalComponent,
        resolve: {
            'pagingParams': SistemaAguaBombGravidadeComunalResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN', 'ADMIN_PROVINCIAL'],
            pageTitle: 'relatorios.title.sistema-agua-fonte-subterranea-gravidade-comunal'
        },
        canActivate: [UserRouteAccessService]
    }
];
