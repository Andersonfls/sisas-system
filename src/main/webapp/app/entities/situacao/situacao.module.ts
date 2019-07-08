import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    SituacaoService,
    SituacaoPopupService,
    SituacaoComponent,
    SituacaoDetailComponent,
    SituacaoDialogComponent,
    SituacaoPopupComponent,
    SituacaoDeletePopupComponent,
    SituacaoDeleteDialogComponent,
    situacaoRoute,
    situacaoPopupRoute,
    SituacaoResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...situacaoRoute,
    ...situacaoPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SituacaoComponent,
        SituacaoDetailComponent,
        SituacaoDialogComponent,
        SituacaoDeleteDialogComponent,
        SituacaoPopupComponent,
        SituacaoDeletePopupComponent,
    ],
    entryComponents: [
        SituacaoComponent,
        SituacaoDialogComponent,
        SituacaoPopupComponent,
        SituacaoDeleteDialogComponent,
        SituacaoDeletePopupComponent,
    ],
    providers: [
        SituacaoService,
        SituacaoPopupService,
        SituacaoResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasSituacaoModule {}
