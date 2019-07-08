import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    ConcepcaoService,
    ConcepcaoPopupService,
    ConcepcaoComponent,
    ConcepcaoDetailComponent,
    ConcepcaoDialogComponent,
    ConcepcaoPopupComponent,
    ConcepcaoDeletePopupComponent,
    ConcepcaoDeleteDialogComponent,
    concepcaoRoute,
    concepcaoPopupRoute,
    ConcepcaoResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...concepcaoRoute,
    ...concepcaoPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ConcepcaoComponent,
        ConcepcaoDetailComponent,
        ConcepcaoDialogComponent,
        ConcepcaoDeleteDialogComponent,
        ConcepcaoPopupComponent,
        ConcepcaoDeletePopupComponent,
    ],
    entryComponents: [
        ConcepcaoComponent,
        ConcepcaoDialogComponent,
        ConcepcaoPopupComponent,
        ConcepcaoDeleteDialogComponent,
        ConcepcaoDeletePopupComponent,
    ],
    providers: [
        ConcepcaoService,
        ConcepcaoPopupService,
        ConcepcaoResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class sisasConcepcaoModule {}
