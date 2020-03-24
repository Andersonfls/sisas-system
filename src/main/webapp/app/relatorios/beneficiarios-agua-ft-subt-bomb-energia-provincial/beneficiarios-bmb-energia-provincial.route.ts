import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import {BeneficiariosBombEnergiaProvincialComponent} from './beneficiarios-bmb-energia-provincial.component';

@Injectable()
export class BeneficiariosBombEnergiaProvincialResolvePagingParams implements Resolve<any> {

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

export const beneficiariosTpBombaEnergiaProvincialRoute: Routes = [
    {
        path: 'benef-ft-subte-energia-provincial',
        component: BeneficiariosBombEnergiaProvincialComponent,
        resolve: {
            'pagingParams': BeneficiariosBombEnergiaProvincialResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN'],
            pageTitle: 'relatorios.title.benef-agua-fonte-subterranea-energia-provincial'
        },
        canActivate: [UserRouteAccessService]
    }
];
