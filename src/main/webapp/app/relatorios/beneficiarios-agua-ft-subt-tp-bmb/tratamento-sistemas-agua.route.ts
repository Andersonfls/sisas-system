import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { TratamentoSistemasAguaComponent} from './tratamento-sistemas-agua.component';

@Injectable()
export class TratamentoSistemasAguaResolvePagingParams implements Resolve<any> {

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

export const tratamentoSistemasAguaRoute: Routes = [
    {
        path: 'ben-agua-ft-subt-tp-bmb-comunal',
        component: TratamentoSistemasAguaComponent,
        resolve: {
            'pagingParams': TratamentoSistemasAguaResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN', 'ADMIN_PROVINCIAL'],
            pageTitle: 'relatorios.title.tratamento-sistemas-agua'
        },
        canActivate: [UserRouteAccessService]
    }
];
