import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    PublicacaoLogService,
    PublicacaoLogPopupService,
    PublicacaoLogComponent,
    PublicacaoLogDetailComponent,
    PublicacaoLogDialogComponent,
    PublicacaoLogPopupComponent,
    PublicacaoLogDeletePopupComponent,
    PublicacaoLogDeleteDialogComponent,
    publicacaoLogRoute,
    publicacaoLogPopupRoute,
    PublicacaoLogResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...publicacaoLogRoute,
    ...publicacaoLogPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        PublicacaoLogComponent,
        PublicacaoLogDetailComponent,
        PublicacaoLogDialogComponent,
        PublicacaoLogDeleteDialogComponent,
        PublicacaoLogPopupComponent,
        PublicacaoLogDeletePopupComponent,
    ],
    entryComponents: [
        PublicacaoLogComponent,
        PublicacaoLogDialogComponent,
        PublicacaoLogPopupComponent,
        PublicacaoLogDeleteDialogComponent,
        PublicacaoLogDeletePopupComponent,
    ],
    providers: [
        PublicacaoLogService,
        PublicacaoLogPopupService,
        PublicacaoLogResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class sisasPublicacaoLogModule {}
