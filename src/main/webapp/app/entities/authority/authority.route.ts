import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { AuthorityComponent } from './authority.component';
import { AuthorityDetailComponent } from './authority-detail.component';
import { AuthorityPopupComponent } from './authority-dialog.component';
import { AuthorityDeletePopupComponent } from './authority-delete-dialog.component';

@Injectable()
export class AuthorityResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'name,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const authorityRoute: Routes = [
    {
        path: 'authority',
        component: AuthorityComponent,
        resolve: {
            'pagingParams': AuthorityResolvePagingParams
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_USER', 'ADMIN_PROVINCIAL'],
            pageTitle: 'perfil.title.home'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'authority/:name',
        component: AuthorityDetailComponent,
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_USER', 'ADMIN_PROVINCIAL'],
            pageTitle: 'perfil.title.detail'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const authorityPopupRoute: Routes = [
    {
        path: 'authority-new',
        component: AuthorityPopupComponent,
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_USER', 'ADMIN_PROVINCIAL'],
            pageTitle: 'perfil.title.dialog'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'authority/:name/edit',
        component: AuthorityPopupComponent,
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_USER', 'ADMIN_PROVINCIAL'],
            pageTitle: 'perfil.title.dialog'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'authority/:name/delete',
        component: AuthorityDeletePopupComponent,
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_USER', 'ADMIN_PROVINCIAL'],
            pageTitle: 'perfil.title.dialog'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
