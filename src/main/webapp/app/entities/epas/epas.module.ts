import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    EpasService,
    EpasPopupService,
    EpasComponent,
    EpasDetailComponent,
    EpasDialogComponent,
    EpasPopupComponent,
    EpasDeletePopupComponent,
    EpasDeleteDialogComponent,
    epasRoute,
    epasPopupRoute,
    EpasResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...epasRoute,
    ...epasPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        EpasComponent,
        EpasDetailComponent,
        EpasDialogComponent,
        EpasDeleteDialogComponent,
        EpasPopupComponent,
        EpasDeletePopupComponent,
    ],
    entryComponents: [
        EpasComponent,
        EpasDialogComponent,
        EpasPopupComponent,
        EpasDeleteDialogComponent,
        EpasDeletePopupComponent,
    ],
    providers: [
        EpasService,
        EpasPopupService,
        EpasResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasEpasModule {}
