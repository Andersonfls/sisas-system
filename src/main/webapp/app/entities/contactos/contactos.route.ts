import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { ContactosComponent } from './contactos.component';
import { ContactosDetailComponent } from './contactos-detail.component';
import { ContactosPopupComponent } from './contactos-dialog.component';
import { ContactosDeletePopupComponent } from './contactos-delete-dialog.component';

@Injectable()
export class ContactosResolvePagingParams implements Resolve<any> {

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

export const contactosRoute: Routes = [
    {
        path: 'contactos',
        component: ContactosComponent,
        resolve: {
            'pagingParams': ContactosResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.contactos.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'contactos/:id',
        component: ContactosDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.contactos.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const contactosPopupRoute: Routes = [
    {
        path: 'contactos-new',
        component: ContactosPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.contactos.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'contactos/:id/edit',
        component: ContactosPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.contactos.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'contactos/:id/delete',
        component: ContactosDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sisasApp.contactos.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
