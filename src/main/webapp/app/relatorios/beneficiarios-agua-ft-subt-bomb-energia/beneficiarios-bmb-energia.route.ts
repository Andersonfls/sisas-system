import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import {BeneficiariosBombEnergiaComponent} from './beneficiarios-bmb-energia.component';

@Injectable()
export class BeneficiariosBombEnergiaResolvePagingParams implements Resolve<any> {

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

export const beneficiariosTpBombaEnergiaRoute: Routes = [
    {
        path: 'benef-ft-subte-energia',
        component: BeneficiariosBombEnergiaComponent,
        resolve: {
            'pagingParams': BeneficiariosBombEnergiaResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN'],
            pageTitle: 'relatorios.title.benef-agua-fonte-subterranea-energia'
        },
        canActivate: [UserRouteAccessService]
    }
];
