import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    AdjudicacaoService,
    AdjudicacaoPopupService,
    AdjudicacaoComponent,
    AdjudicacaoDetailComponent,
    AdjudicacaoDialogComponent,
    AdjudicacaoPopupComponent,
    AdjudicacaoDeletePopupComponent,
    AdjudicacaoDeleteDialogComponent,
    adjudicacaoRoute,
    adjudicacaoPopupRoute,
    AdjudicacaoResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...adjudicacaoRoute,
    ...adjudicacaoPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        AdjudicacaoComponent,
        AdjudicacaoDetailComponent,
        AdjudicacaoDialogComponent,
        AdjudicacaoDeleteDialogComponent,
        AdjudicacaoPopupComponent,
        AdjudicacaoDeletePopupComponent,
    ],
    entryComponents: [
        AdjudicacaoComponent,
        AdjudicacaoDialogComponent,
        AdjudicacaoPopupComponent,
        AdjudicacaoDeleteDialogComponent,
        AdjudicacaoDeletePopupComponent,
    ],
    providers: [
        AdjudicacaoService,
        AdjudicacaoPopupService,
        AdjudicacaoResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class sisasAdjudicacaoModule {}
