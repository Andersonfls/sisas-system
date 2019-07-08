import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    IndicadorProducaoService,
    IndicadorProducaoPopupService,
    IndicadorProducaoComponent,
    IndicadorProducaoDetailComponent,
    IndicadorProducaoDialogComponent,
    IndicadorProducaoPopupComponent,
    IndicadorProducaoDeletePopupComponent,
    IndicadorProducaoDeleteDialogComponent,
    indicadorProducaoRoute,
    indicadorProducaoPopupRoute,
    IndicadorProducaoResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...indicadorProducaoRoute,
    ...indicadorProducaoPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        IndicadorProducaoComponent,
        IndicadorProducaoDetailComponent,
        IndicadorProducaoDialogComponent,
        IndicadorProducaoDeleteDialogComponent,
        IndicadorProducaoPopupComponent,
        IndicadorProducaoDeletePopupComponent,
    ],
    entryComponents: [
        IndicadorProducaoComponent,
        IndicadorProducaoDialogComponent,
        IndicadorProducaoPopupComponent,
        IndicadorProducaoDeleteDialogComponent,
        IndicadorProducaoDeletePopupComponent,
    ],
    providers: [
        IndicadorProducaoService,
        IndicadorProducaoPopupService,
        IndicadorProducaoResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasIndicadorProducaoModule {}
