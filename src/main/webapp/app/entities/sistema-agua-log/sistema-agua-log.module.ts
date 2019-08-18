import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {RouterModule} from '@angular/router';

import {SisasSharedModule} from '../../shared';
import {
    SistemaAguaLogService,
    SistemaAguaLogPopupService,
    SistemaAguaLogComponent,
    SistemaAguaLogDetailComponent,
    SistemaAguaLogDialogComponent,
    SistemaAguaLogPopupComponent,
    SistemaAguaLogDeletePopupComponent,
    SistemaAguaLogDeleteDialogComponent,
    sistemaAguaLogRoute,
    sistemaAguaLogPopupRoute,
    SistemaAguaLogResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...sistemaAguaLogRoute,
    ...sistemaAguaLogPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SistemaAguaLogComponent,
        SistemaAguaLogDetailComponent,
        SistemaAguaLogDialogComponent,
        SistemaAguaLogDeleteDialogComponent,
        SistemaAguaLogPopupComponent,
        SistemaAguaLogDeletePopupComponent,
    ],
    entryComponents: [
        SistemaAguaLogComponent,
        SistemaAguaLogDialogComponent,
        SistemaAguaLogPopupComponent,
        SistemaAguaLogDeleteDialogComponent,
        SistemaAguaLogDeletePopupComponent,
    ],
    providers: [
        SistemaAguaLogService,
        SistemaAguaLogPopupService,
        SistemaAguaLogResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasSistemaAguaLogModule {
}
