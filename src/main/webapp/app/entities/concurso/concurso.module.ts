import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    ConcursoService,
    ConcursoPopupService,
    ConcursoComponent,
    ConcursoDetailComponent,
    ConcursoDialogComponent,
    ConcursoPopupComponent,
    ConcursoDeletePopupComponent,
    ConcursoDeleteDialogComponent,
    concursoRoute,
    concursoPopupRoute,
    ConcursoResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...concursoRoute,
    ...concursoPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ConcursoComponent,
        ConcursoDetailComponent,
        ConcursoDialogComponent,
        ConcursoDeleteDialogComponent,
        ConcursoPopupComponent,
        ConcursoDeletePopupComponent,
    ],
    entryComponents: [
        ConcursoComponent,
        ConcursoDialogComponent,
        ConcursoPopupComponent,
        ConcursoDeleteDialogComponent,
        ConcursoDeletePopupComponent,
    ],
    providers: [
        ConcursoService,
        ConcursoPopupService,
        ConcursoResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class sisasConcursoModule {}
