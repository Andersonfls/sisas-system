import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    SistemaAguaArquivoComponent,
    sistemaAguaArquivoRoute,
    cadPdfPopupRoute,
} from './';
import {HttpClientModule} from '@angular/common/http';
import {SistemaAguaService} from '../sistema-agua';

const ENTITY_STATES = [
    ...sistemaAguaArquivoRoute,
    ...cadPdfPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES),
        HttpClientModule
    ],
    declarations: [
        SistemaAguaArquivoComponent,
    ],
    entryComponents: [
        SistemaAguaArquivoComponent,
    ],
    providers: [
        SistemaAguaService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasSistemasArquivoModule {}
