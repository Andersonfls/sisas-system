import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    IndicadorProducaoLogService,
    IndicadorProducaoLogPopupService,
    IndicadorProducaoLogComponent,
    IndicadorProducaoLogDetailComponent,
    IndicadorProducaoLogDialogComponent,
    IndicadorProducaoLogPopupComponent,
    IndicadorProducaoLogDeletePopupComponent,
    IndicadorProducaoLogDeleteDialogComponent,
    indicadorProducaoLogRoute,
    indicadorProducaoLogPopupRoute,
    IndicadorProducaoLogResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...indicadorProducaoLogRoute,
    ...indicadorProducaoLogPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        IndicadorProducaoLogComponent,
        IndicadorProducaoLogDetailComponent,
        IndicadorProducaoLogDialogComponent,
        IndicadorProducaoLogDeleteDialogComponent,
        IndicadorProducaoLogPopupComponent,
        IndicadorProducaoLogDeletePopupComponent,
    ],
    entryComponents: [
        IndicadorProducaoLogComponent,
        IndicadorProducaoLogDialogComponent,
        IndicadorProducaoLogPopupComponent,
        IndicadorProducaoLogDeleteDialogComponent,
        IndicadorProducaoLogDeletePopupComponent,
    ],
    providers: [
        IndicadorProducaoLogService,
        IndicadorProducaoLogPopupService,
        IndicadorProducaoLogResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasIndicadorProducaoLogModule {}
