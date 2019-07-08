import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    ContactosService,
    ContactosPopupService,
    ContactosComponent,
    ContactosDetailComponent,
    ContactosDialogComponent,
    ContactosPopupComponent,
    ContactosDeletePopupComponent,
    ContactosDeleteDialogComponent,
    contactosRoute,
    contactosPopupRoute,
    ContactosResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...contactosRoute,
    ...contactosPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ContactosComponent,
        ContactosDetailComponent,
        ContactosDialogComponent,
        ContactosDeleteDialogComponent,
        ContactosPopupComponent,
        ContactosDeletePopupComponent,
    ],
    entryComponents: [
        ContactosComponent,
        ContactosDialogComponent,
        ContactosPopupComponent,
        ContactosDeleteDialogComponent,
        ContactosDeletePopupComponent,
    ],
    providers: [
        ContactosService,
        ContactosPopupService,
        ContactosResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class sisasContactosModule {}
