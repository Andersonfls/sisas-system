import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    FinalidadeProjetoService,
    FinalidadeProjetoPopupService,
    FinalidadeProjetoComponent,
    FinalidadeProjetoDetailComponent,
    FinalidadeProjetoDialogComponent,
    FinalidadeProjetoPopupComponent,
    FinalidadeProjetoDeletePopupComponent,
    FinalidadeProjetoDeleteDialogComponent,
    finalidadeProjetoRoute,
    finalidadeProjetoPopupRoute,
    FinalidadeProjetoResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...finalidadeProjetoRoute,
    ...finalidadeProjetoPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        FinalidadeProjetoComponent,
        FinalidadeProjetoDetailComponent,
        FinalidadeProjetoDialogComponent,
        FinalidadeProjetoDeleteDialogComponent,
        FinalidadeProjetoPopupComponent,
        FinalidadeProjetoDeletePopupComponent,
    ],
    entryComponents: [
        FinalidadeProjetoComponent,
        FinalidadeProjetoDialogComponent,
        FinalidadeProjetoPopupComponent,
        FinalidadeProjetoDeleteDialogComponent,
        FinalidadeProjetoDeletePopupComponent,
    ],
    providers: [
        FinalidadeProjetoService,
        FinalidadeProjetoPopupService,
        FinalidadeProjetoResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasFinalidadeProjetoModule {}
