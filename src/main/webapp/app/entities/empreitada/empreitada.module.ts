import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    EmpreitadaService,
    EmpreitadaPopupService,
    EmpreitadaComponent,
    EmpreitadaDetailComponent,
    EmpreitadaDialogComponent,
    EmpreitadaPopupComponent,
    EmpreitadaDeletePopupComponent,
    EmpreitadaDeleteDialogComponent,
    empreitadaRoute,
    empreitadaPopupRoute,
    EmpreitadaResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...empreitadaRoute,
    ...empreitadaPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        EmpreitadaComponent,
        EmpreitadaDetailComponent,
        EmpreitadaDialogComponent,
        EmpreitadaDeleteDialogComponent,
        EmpreitadaPopupComponent,
        EmpreitadaDeletePopupComponent,
    ],
    entryComponents: [
        EmpreitadaComponent,
        EmpreitadaDialogComponent,
        EmpreitadaPopupComponent,
        EmpreitadaDeleteDialogComponent,
        EmpreitadaDeletePopupComponent,
    ],
    providers: [
        EmpreitadaService,
        EmpreitadaPopupService,
        EmpreitadaResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class sisasEmpreitadaModule {}
