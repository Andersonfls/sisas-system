import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { DashboardComponent } from './dashboard.component';

@Injectable()
export class DashboardResolvePagingParams implements Resolve<any> {

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

export const dashboardRoute: Routes = [
    {
        path: 'dashboard',
        component: DashboardComponent,
        resolve: {
            'pagingParams': DashboardResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN', 'ADMIN_PROVINCIAL'],
            pageTitle: 'dashboard.title.home'
        },
        canActivate: [UserRouteAccessService]
    }
];
