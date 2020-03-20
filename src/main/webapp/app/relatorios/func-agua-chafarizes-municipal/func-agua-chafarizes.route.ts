import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { FuncAguaChafarizesComponent} from './func-agua-chafarizes.component';

@Injectable()
export class FuncAguaChafarizesResolvePagingParams implements Resolve<any> {

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

export const funcAguaChafarizesRoute: Routes = [
    {
        path: 'func-agua-chafariz-municipal',
        component: FuncAguaChafarizesComponent,
        resolve: {
            'pagingParams': FuncAguaChafarizesResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN', 'ADMIN_PROVINCIAL'],
            pageTitle: 'relatorios.title.func-agua-chafariz'
        },
        canActivate: [UserRouteAccessService]
    }
];
