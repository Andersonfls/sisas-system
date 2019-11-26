import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import {BeneficiariosTpBombaComponent} from './beneficiarios-tp-bmb.component';

@Injectable()
export class BeneficiariosTpBombaResolvePagingParams implements Resolve<any> {

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

export const beneficiariosTpBombaRoute: Routes = [
    {
        path: 'benef-agua-subterranea-tp-bomba',
        component: BeneficiariosTpBombaComponent,
        resolve: {
            'pagingParams': BeneficiariosTpBombaResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN'],
            pageTitle: 'relatorios.title.benef-agua-subterranea-tp-bomba'
        },
        canActivate: [UserRouteAccessService]
    }
];