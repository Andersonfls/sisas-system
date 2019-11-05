import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SisasSharedModule } from '../../shared';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import { CoberturaSectorAguaSaneamentoComponent} from './sector-agua-saneamento.component';
import {ProvinciaService} from '../../entities/provincia';
import {SectorAguaSaneamentoResolvePagingParams, sectorAguaSaneamentoRoute} from './sector-agua-saneamento.route';

const ENTITY_STATES = [
    ...sectorAguaSaneamentoRoute
];

@NgModule({
    imports: [
        SisasSharedModule,
        ChartsModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        CoberturaSectorAguaSaneamentoComponent
    ],
    entryComponents: [
        CoberturaSectorAguaSaneamentoComponent
    ],
    providers: [
        SectorAguaSaneamentoResolvePagingParams,
        ProvinciaService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasSectorAguaSaneamentoModule {}
