import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    SegurancasLogService,
    SegurancasLogPopupService,
    SegurancasLogComponent,
    SegurancasLogDetailComponent,
    SegurancasLogDialogComponent,
    RelatoriosLogPopupComponent,
    RelatoriosLogDeletePopupComponent,
    SegurancasLogDeleteDialogComponent,
    segurancasLogRoute,
    relatoriosLogPopupRoute,
    RelatoriosLogResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...segurancasLogRoute,
    ...relatoriosLogPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SegurancasLogComponent,
        SegurancasLogDetailComponent,
        SegurancasLogDialogComponent,
        SegurancasLogDeleteDialogComponent,
        RelatoriosLogPopupComponent,
        RelatoriosLogDeletePopupComponent,
    ],
    entryComponents: [
        SegurancasLogComponent,
        SegurancasLogDialogComponent,
        RelatoriosLogPopupComponent,
        SegurancasLogDeleteDialogComponent,
        RelatoriosLogDeletePopupComponent,
    ],
    providers: [
        SegurancasLogService,
        SegurancasLogPopupService,
        RelatoriosLogResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasSegurancasLogModule {}
