import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    IndicadorArquivoComponent,
    indicadorArquivoRoute,
    cadPdfPopupRoute,
} from './';
import {HttpClientModule} from '@angular/common/http';
import {IndicadorProducaoService} from '../indicador-producao';

const ENTITY_STATES = [
    ...indicadorArquivoRoute,
    ...cadPdfPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        RouterModule.forChild(ENTITY_STATES),
        HttpClientModule
    ],
    declarations: [
        IndicadorArquivoComponent,
    ],
    entryComponents: [
        IndicadorArquivoComponent,
    ],
    providers: [
        IndicadorProducaoService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasIndicadorArquivoModule {}
