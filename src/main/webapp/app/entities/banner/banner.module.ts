import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    BannerService,
    BannerPopupService,
    BannerComponent,
    BannerDetailComponent,
    BannerDialogComponent,
    ProdutoPopupComponent,
    ProdutoDeletePopupComponent,
    BannerDeleteDialogComponent,
    bannerRoute,
    produtoPopupRoute,
} from './';
import { UploadFileService } from './upload-file.service';
import {HttpClientModule} from '@angular/common/http';

const ENTITY_STATES = [
    ...bannerRoute,
    ...produtoPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES),
        HttpClientModule
    ],
    declarations: [
        BannerComponent,
        BannerDetailComponent,
        BannerDialogComponent,
        BannerDeleteDialogComponent,
        ProdutoPopupComponent,
        ProdutoDeletePopupComponent,
    ],
    entryComponents: [
        BannerComponent,
        BannerDialogComponent,
        ProdutoPopupComponent,
        BannerDeleteDialogComponent,
        ProdutoDeletePopupComponent,
    ],
    providers: [
        BannerService,
        BannerPopupService,
        UploadFileService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasBannerModule {}
