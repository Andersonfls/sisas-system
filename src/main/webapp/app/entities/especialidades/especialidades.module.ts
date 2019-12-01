import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    EspecialidadesService,
    EspecialidadesPopupService,
    EspecialidadesComponent,
    EspecialidadesDetailComponent,
    EspecialidadesDialogComponent,
    EspecialidadesPopupComponent,
    EspecialidadesDeletePopupComponent,
    EspecialidadesDeleteDialogComponent,
    especialidadesRoute,
    especialidadesPopupRoute,
    EspecialidadesResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...especialidadesRoute,
    ...especialidadesPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        EspecialidadesComponent,
        EspecialidadesDetailComponent,
        EspecialidadesDialogComponent,
        EspecialidadesDeleteDialogComponent,
        EspecialidadesPopupComponent,
        EspecialidadesDeletePopupComponent,
    ],
    entryComponents: [
        EspecialidadesComponent,
        EspecialidadesDialogComponent,
        EspecialidadesPopupComponent,
        EspecialidadesDeleteDialogComponent,
        EspecialidadesDeletePopupComponent,
    ],
    providers: [
        EspecialidadesService,
        EspecialidadesPopupService,
        EspecialidadesResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasEspecialidadesModule {}
