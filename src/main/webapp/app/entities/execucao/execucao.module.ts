import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    ExecucaoService,
    ExecucaoPopupService,
    ExecucaoComponent,
    ExecucaoDetailComponent,
    ExecucaoDialogComponent,
    ExecucaoPopupComponent,
    ExecucaoDeletePopupComponent,
    ExecucaoDeleteDialogComponent,
    execucaoRoute,
    execucaoPopupRoute,
    ExecucaoResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...execucaoRoute,
    ...execucaoPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ExecucaoComponent,
        ExecucaoDetailComponent,
        ExecucaoDialogComponent,
        ExecucaoDeleteDialogComponent,
        ExecucaoPopupComponent,
        ExecucaoDeletePopupComponent,
    ],
    entryComponents: [
        ExecucaoComponent,
        ExecucaoDialogComponent,
        ExecucaoPopupComponent,
        ExecucaoDeleteDialogComponent,
        ExecucaoDeletePopupComponent,
    ],
    providers: [
        ExecucaoService,
        ExecucaoPopupService,
        ExecucaoResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class sisasExecucaoModule {}
