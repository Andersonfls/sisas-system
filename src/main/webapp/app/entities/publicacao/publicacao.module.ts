import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    PublicacaoService,
    PublicacaoPopupService,
    PublicacaoComponent,
    PublicacaoDetailComponent,
    PublicacaoDialogComponent,
    PublicacaoPopupComponent,
    PublicacaoDeletePopupComponent,
    PublicacaoDeleteDialogComponent,
    publicacaoRoute,
    publicacaoPopupRoute,
    PublicacaoResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...publicacaoRoute,
    ...publicacaoPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        PublicacaoComponent,
        PublicacaoDetailComponent,
        PublicacaoDialogComponent,
        PublicacaoDeleteDialogComponent,
        PublicacaoPopupComponent,
        PublicacaoDeletePopupComponent,
    ],
    entryComponents: [
        PublicacaoComponent,
        PublicacaoDialogComponent,
        PublicacaoPopupComponent,
        PublicacaoDeleteDialogComponent,
        PublicacaoDeletePopupComponent,
    ],
    providers: [
        PublicacaoService,
        PublicacaoPopupService,
        PublicacaoResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class sisasPublicacaoModule {}
