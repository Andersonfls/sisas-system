import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { ProgramasProjectosComponent } from './programas-projectos.component';
import {ProgramasProjectos, ProgramasProjectosService} from '../../entities/programas-projectos';

@Injectable()
export class ProgramasProjectosResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        const id = route.params['id'] ? route.params['id'] : null;

        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort),
      };

    }
}

@Injectable()
export class ProgramasProjectosResolve implements Resolve<any> {

    constructor(private service: ProgramasProjectosService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;

        if (id) {
            return this.service.find(id);
        }
         return new ProgramasProjectos();
    }
}

export const programasProjectosRoute: Routes = [
    {
        path: 'programas-projectos-export',
        component: ProgramasProjectosComponent,
        resolve: {
            'pagingParams': ProgramasProjectosResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'USUARIO_PROVINCIAL', 'PROG_PROJECTOS'],
            pageTitle: 'sisasApp.programasProjectos.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const programasProjectosPopupRoute: Routes = [
];
