import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    CadPdfService,
    CadPdfComponent,
    CadPdfDetailComponent,
    ProdutoPopupComponent,
    ProdutoDeletePopupComponent,
    cadPdfRoute,
    produtoPopupRoute, CadPdfDialogComponent, CadPdfDeleteDialogComponent, CadPdfPopupService,
} from './';
import { UploadFileService } from './upload-file.service';
import {HttpClientModule} from '@angular/common/http';

const ENTITY_STATES = [
    ...cadPdfRoute,
    ...produtoPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES),
        HttpClientModule
    ],
    declarations: [
        CadPdfComponent,
        CadPdfDetailComponent,
        CadPdfDialogComponent,
        CadPdfDeleteDialogComponent,
        ProdutoPopupComponent,
        ProdutoDeletePopupComponent,
    ],
    entryComponents: [
        CadPdfComponent,
        CadPdfDialogComponent,
        ProdutoPopupComponent,
        CadPdfDeleteDialogComponent,
        ProdutoDeletePopupComponent,
    ],
    providers: [
        CadPdfService,
        CadPdfPopupService,
        UploadFileService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasPdfModule {}
