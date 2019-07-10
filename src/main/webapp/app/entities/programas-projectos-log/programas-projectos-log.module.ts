import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    ProgramasProjectosLogService,
    ProgramasProjectosLogPopupService,
    ProgramasProjectosLogComponent,
    ProgramasProjectosLogDetailComponent,
    ProgramasProjectosLogDialogComponent,
    ProgramasProjectosLogPopupComponent,
    ProgramasProjectosLogDeletePopupComponent,
    ProgramasProjectosLogDeleteDialogComponent,
    programasProjectosLogRoute,
    programasProjectosLogPopupRoute,
    ProgramasProjectosLogResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...programasProjectosLogRoute,
    ...programasProjectosLogPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ProgramasProjectosLogComponent,
        ProgramasProjectosLogDetailComponent,
        ProgramasProjectosLogDialogComponent,
        ProgramasProjectosLogDeleteDialogComponent,
        ProgramasProjectosLogPopupComponent,
        ProgramasProjectosLogDeletePopupComponent,
    ],
    entryComponents: [
        ProgramasProjectosLogComponent,
        ProgramasProjectosLogDialogComponent,
        ProgramasProjectosLogPopupComponent,
        ProgramasProjectosLogDeleteDialogComponent,
        ProgramasProjectosLogDeletePopupComponent,
    ],
    providers: [
        ProgramasProjectosLogService,
        ProgramasProjectosLogPopupService,
        ProgramasProjectosLogResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasProgramasProjectosLogModule {}
