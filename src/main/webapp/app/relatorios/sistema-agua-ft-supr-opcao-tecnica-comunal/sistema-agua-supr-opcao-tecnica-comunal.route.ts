import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { SistemaAguaSupeOpcaoTecnicaComunalComponent} from './sistema-agua-supr-opcao-tecnica-comunal.component';

@Injectable()
export class SistemaAguaSupeOpcaoTecnicaComunalResolvePagingParams implements Resolve<any> {

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

export const sistemaAguaSuprOpcaoTecnicaComunalRoute: Routes = [
    {
        path: 'sistema-agua-ft-super-opcao-tecnica-comunal',
        component: SistemaAguaSupeOpcaoTecnicaComunalComponent,
        resolve: {
            'pagingParams': SistemaAguaSupeOpcaoTecnicaComunalResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN', 'ADMIN_PROVINCIAL'],
            pageTitle: 'relatorios.title.sistema-agua-super-opcao-tecnica-comunal'
        },
        canActivate: [UserRouteAccessService]
    }
];
