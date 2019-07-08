import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    InicioService,
    InicioPopupService,
    InicioComponent,
    InicioDetailComponent,
    InicioDialogComponent,
    InicioPopupComponent,
    InicioDeletePopupComponent,
    InicioDeleteDialogComponent,
    inicioRoute,
    inicioPopupRoute,
    InicioResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...inicioRoute,
    ...inicioPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        InicioComponent,
        InicioDetailComponent,
        InicioDialogComponent,
        InicioDeleteDialogComponent,
        InicioPopupComponent,
        InicioDeletePopupComponent,
    ],
    entryComponents: [
        InicioComponent,
        InicioDialogComponent,
        InicioPopupComponent,
        InicioDeleteDialogComponent,
        InicioDeletePopupComponent,
    ],
    providers: [
        InicioService,
        InicioPopupService,
        InicioResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class sisasInicioModule {}
