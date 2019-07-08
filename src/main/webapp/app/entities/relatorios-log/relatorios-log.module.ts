import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    RelatoriosLogService,
    RelatoriosLogPopupService,
    RelatoriosLogComponent,
    RelatoriosLogDetailComponent,
    RelatoriosLogDialogComponent,
    RelatoriosLogPopupComponent,
    RelatoriosLogDeletePopupComponent,
    RelatoriosLogDeleteDialogComponent,
    relatoriosLogRoute,
    relatoriosLogPopupRoute,
    RelatoriosLogResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...relatoriosLogRoute,
    ...relatoriosLogPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        RelatoriosLogComponent,
        RelatoriosLogDetailComponent,
        RelatoriosLogDialogComponent,
        RelatoriosLogDeleteDialogComponent,
        RelatoriosLogPopupComponent,
        RelatoriosLogDeletePopupComponent,
    ],
    entryComponents: [
        RelatoriosLogComponent,
        RelatoriosLogDialogComponent,
        RelatoriosLogPopupComponent,
        RelatoriosLogDeleteDialogComponent,
        RelatoriosLogDeletePopupComponent,
    ],
    providers: [
        RelatoriosLogService,
        RelatoriosLogPopupService,
        RelatoriosLogResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class sisasRelatoriosLogModule {}
