import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SisasSharedModule } from '../../shared';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import {
     TratamentoSistemasAguaResolvePagingParams,
    tratamentoSistemasAguaRoute,
} from './tratamento-sistemas-agua.route';
import { TratamentoSistemasAguaComponent} from './tratamento-sistemas-agua.component';

const ENTITY_STATES = [
    ...tratamentoSistemasAguaRoute
];

@NgModule({
    imports: [
        SisasSharedModule,
        ChartsModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        TratamentoSistemasAguaComponent
    ],
    entryComponents: [
        TratamentoSistemasAguaComponent
    ],
    providers: [
        TratamentoSistemasAguaResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasBenefAguaTpBombaManualMunModule {}
