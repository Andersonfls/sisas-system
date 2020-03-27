import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import {BeneficiariosBombEnergiaComunalComponent} from './sistemas-bmb-energia-comunal.component';

@Injectable()
export class BeneficiariosBombEnergiaComunalResolvePagingParams implements Resolve<any> {

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

export const beneficiariosTpBombaEnergiaComunalRoute: Routes = [
    {
        path: 'sist-agua-ft-subt-bmb-energia-comunal',
        component: BeneficiariosBombEnergiaComunalComponent,
        resolve: {
            'pagingParams': BeneficiariosBombEnergiaComunalResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN'],
            pageTitle: 'relatorios.title.sist-agua-fonte-subterranea-energia'
        },
        canActivate: [UserRouteAccessService]
    }
];
