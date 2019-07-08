import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { ProjectosComponent } from './projectos.component';
import { ProjectosDetailComponent } from './projectos-detail.component';
import { ProjectosPopupComponent } from './projectos-dialog.component';
import { ProjectosDeletePopupComponent } from './projectos-delete-dialog.component';

@Injectable()
export class ProjectosResolvePagingParams implements Resolve<any> {

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

export const projectosRoute: Routes = [
    {
        path: 'projectos',
        component: ProjectosComponent,
        resolve: {
            'pagingParams': ProjectosResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.projectos.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'projectos/:id',
        component: ProjectosDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.projectos.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const projectosPopupRoute: Routes = [
    {
        path: 'projectos-new',
        component: ProjectosPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.projectos.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'projectos/:id/edit',
        component: ProjectosPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.projectos.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'projectos/:id/delete',
        component: ProjectosDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.projectos.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
