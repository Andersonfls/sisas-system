import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    EntidadeGestoraService,
    EntidadeGestoraPopupService,
    EntidadeGestoraComponent,
    EntidadeGestoraDetailComponent,
    EntidadeGestoraDialogComponent,
    EntidadeGestoraPopupComponent,
    EntidadeGestoraDeletePopupComponent,
    EntidadeGestoraDeleteDialogComponent,
    entidadeGestoraRoute,
    entidadeGestoraPopupRoute,
    EntidadeGestoraResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...entidadeGestoraRoute,
    ...entidadeGestoraPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        EntidadeGestoraComponent,
        EntidadeGestoraDetailComponent,
        EntidadeGestoraDialogComponent,
        EntidadeGestoraDeleteDialogComponent,
        EntidadeGestoraPopupComponent,
        EntidadeGestoraDeletePopupComponent,
    ],
    entryComponents: [
        EntidadeGestoraComponent,
        EntidadeGestoraDialogComponent,
        EntidadeGestoraPopupComponent,
        EntidadeGestoraDeleteDialogComponent,
        EntidadeGestoraDeletePopupComponent,
    ],
    providers: [
        EntidadeGestoraService,
        EntidadeGestoraPopupService,
        EntidadeGestoraResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class sisasEntidadeGestoraModule {}
