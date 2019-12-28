import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { SobreDnaComponent } from './sobre-dna.component';

@Injectable()
export class SobreDnaResolvePagingParams implements Resolve<any> {

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

export const sobreDnaRoute: Routes = [
    {
        path: 'sobre-dna',
        component: SobreDnaComponent,
        resolve: {
            'pagingParams': SobreDnaResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.sobreDna.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sobreDnaPopupRoute: Routes = [
];
