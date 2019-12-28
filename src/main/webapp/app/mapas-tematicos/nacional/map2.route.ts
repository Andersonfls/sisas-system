import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes} from '@angular/router';
import {JhiPaginationUtil} from 'ng-jhipster';
import {UserRouteAccessService} from '../../shared/index';
import {Map2Component} from './map2.component';

@Injectable()
export class MapResolvePagingParams implements Resolve<any> {

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

export const map2Route: Routes = [
    {
        path: 'nacional',
        component: Map2Component,
        resolve: {
            'pagingParams': MapResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN'],
            pageTitle: 'map.title.nacional'
        },
        canActivate: [UserRouteAccessService]
    }
];
