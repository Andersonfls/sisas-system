import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    OrigemFinanciamentoService,
    OrigemFinanciamentoPopupService,
    OrigemFinanciamentoComponent,
    OrigemFinanciamentoDetailComponent,
    OrigemFinanciamentoDialogComponent,
    OrigemFinanciamentoPopupComponent,
    OrigemFinanciamentoDeletePopupComponent,
    OrigemFinanciamentoDeleteDialogComponent,
    origemFinanciamentoRoute,
    origemFinanciamentoPopupRoute,
    OrigemFinanciamentoResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...origemFinanciamentoRoute,
    ...origemFinanciamentoPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        OrigemFinanciamentoComponent,
        OrigemFinanciamentoDetailComponent,
        OrigemFinanciamentoDialogComponent,
        OrigemFinanciamentoDeleteDialogComponent,
        OrigemFinanciamentoPopupComponent,
        OrigemFinanciamentoDeletePopupComponent,
    ],
    entryComponents: [
        OrigemFinanciamentoComponent,
        OrigemFinanciamentoDialogComponent,
        OrigemFinanciamentoPopupComponent,
        OrigemFinanciamentoDeleteDialogComponent,
        OrigemFinanciamentoDeletePopupComponent,
    ],
    providers: [
        OrigemFinanciamentoService,
        OrigemFinanciamentoPopupService,
        OrigemFinanciamentoResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class sisasOrigemFinanciamentoModule {}
