import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    ProgramasProjectosService,
    ProgramasProjectosPopupService,
    ProgramasProjectosComponent,
    ProgramasProjectosDetailComponent,
    ProgramasProjectosDialogComponent,
    ProgramasProjectosPopupComponent,
    ProgramasProjectosDeletePopupComponent,
    ProgramasProjectosDeleteDialogComponent,
    programasProjectosRoute,
    programasProjectosPopupRoute,
    ProgramasProjectosResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...programasProjectosRoute,
    ...programasProjectosPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ProgramasProjectosComponent,
        ProgramasProjectosDetailComponent,
        ProgramasProjectosDialogComponent,
        ProgramasProjectosDeleteDialogComponent,
        ProgramasProjectosPopupComponent,
        ProgramasProjectosDeletePopupComponent,
    ],
    entryComponents: [
        ProgramasProjectosComponent,
        ProgramasProjectosDialogComponent,
        ProgramasProjectosPopupComponent,
        ProgramasProjectosDeleteDialogComponent,
        ProgramasProjectosDeletePopupComponent,
    ],
    providers: [
        ProgramasProjectosService,
        ProgramasProjectosPopupService,
        ProgramasProjectosResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasProgramasProjectosModule {}
