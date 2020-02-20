import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SisasSharedModule } from '../../shared';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import {ProvinciaService} from '../../entities/provincia';
import {IndicadorProducaoProvinciaComponent} from './indicador-producao-provincia.component';
import {IndicadorProducaoProvinciaResolvePagingParams, indicadorProducaoProvinciaRoute} from './indicador-producao-provincia.route';

const ENTITY_STATES = [
    ...indicadorProducaoProvinciaRoute
];

@NgModule({
    imports: [
        SisasSharedModule,
        ChartsModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        IndicadorProducaoProvinciaComponent
    ],
    entryComponents: [
        IndicadorProducaoProvinciaComponent
    ],
    providers: [
        IndicadorProducaoProvinciaResolvePagingParams,
        ProvinciaService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasIndicadorProducaoProvinciaModule {}
