import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    ContratoService,
    ContratoPopupService,
    ContratoComponent,
    ContratoDetailComponent,
    ContratoDialogComponent,
    ContratoPopupComponent,
    ContratoDeletePopupComponent,
    ContratoDeleteDialogComponent,
    contratoRoute,
    contratoPopupRoute,
    ContratoResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...contratoRoute,
    ...contratoPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ContratoComponent,
        ContratoDetailComponent,
        ContratoDialogComponent,
        ContratoDeleteDialogComponent,
        ContratoPopupComponent,
        ContratoDeletePopupComponent,
    ],
    entryComponents: [
        ContratoComponent,
        ContratoDialogComponent,
        ContratoPopupComponent,
        ContratoDeleteDialogComponent,
        ContratoDeletePopupComponent,
    ],
    providers: [
        ContratoService,
        ContratoPopupService,
        ContratoResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class sisasContratoModule {}
