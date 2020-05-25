import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes} from '@angular/router';
import {JhiPaginationUtil} from 'ng-jhipster';
import {UserRouteAccessService} from '../../shared/index';
import {HuamboComponent} from './huambo.component';

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

export const huamboRoute: Routes = [
    {
        path: 'huambo-agua-nao-funcionam',
        component: HuamboComponent,
        resolve: {
            'pagingParams': MapResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN', 'ADMIN_PROVINCIAL'],
            pageTitle: 'map.title.huambo'
        },
        canActivate: [UserRouteAccessService]
    }
];
