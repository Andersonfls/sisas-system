import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes} from '@angular/router';
import {JhiPaginationUtil} from 'ng-jhipster';
import {UserRouteAccessService} from '../../shared/index';
import {HuliaComponent} from './hulia.component';

@Injectable()
export class HuliaResolvePagingParams implements Resolve<any> {

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

export const huliaRoute: Routes = [
    {
        path: 'hulia-agua-nao-funcionam',
        component: HuliaComponent,
        resolve: {
            'pagingParams': HuliaResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN', 'ADMIN_PROVINCIAL'],
            pageTitle: 'map.title.hulia'
        },
        canActivate: [UserRouteAccessService]
    }
];
