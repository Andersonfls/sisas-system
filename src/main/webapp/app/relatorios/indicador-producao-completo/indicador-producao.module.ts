import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SisasSharedModule } from '../../shared';
import {
    IndicadorProducaoComponent,
    indicadorProducaoRoute,
    indicadorProducaoPopupRoute,
    IndicadorProducaoResolvePagingParams,
} from './';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {NgxCurrencyModule} from 'ngx-currency';
import {IndicadorProducaoService} from '../../entities/indicador-producao';

const ENTITY_STATES = [
    ...indicadorProducaoRoute,
    ...indicadorProducaoPopupRoute,
];

@NgModule({
    imports: [
        SisasSharedModule,
        NgxCurrencyModule,
        RouterModule.forChild(ENTITY_STATES),
        NgbModule.forRoot()
    ],
    declarations: [
        IndicadorProducaoComponent
    ],
    entryComponents: [
        IndicadorProducaoComponent
    ],
    providers: [
        IndicadorProducaoService,
        IndicadorProducaoResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasIndicadorProducaoExportModule {}
