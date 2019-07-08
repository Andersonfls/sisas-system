import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    ConfiguracoesLogService,
    ConfiguracoesLogPopupService,
    ConfiguracoesLogComponent,
    ConfiguracoesLogDetailComponent,
    ConfiguracoesLogDialogComponent,
    ConfiguracoesLogPopupComponent,
    ConfiguracoesLogDeletePopupComponent,
    ConfiguracoesLogDeleteDialogComponent,
    configuracoesLogRoute,
    configuracoesLogPopupRoute,
    ConfiguracoesLogResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...configuracoesLogRoute,
    ...configuracoesLogPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ConfiguracoesLogComponent,
        ConfiguracoesLogDetailComponent,
        ConfiguracoesLogDialogComponent,
        ConfiguracoesLogDeleteDialogComponent,
        ConfiguracoesLogPopupComponent,
        ConfiguracoesLogDeletePopupComponent,
    ],
    entryComponents: [
        ConfiguracoesLogComponent,
        ConfiguracoesLogDialogComponent,
        ConfiguracoesLogPopupComponent,
        ConfiguracoesLogDeleteDialogComponent,
        ConfiguracoesLogDeletePopupComponent,
    ],
    providers: [
        ConfiguracoesLogService,
        ConfiguracoesLogPopupService,
        ConfiguracoesLogResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class sisasConfiguracoesLogModule {}
