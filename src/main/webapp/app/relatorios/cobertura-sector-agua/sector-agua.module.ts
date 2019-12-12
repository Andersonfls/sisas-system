import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SisasSharedModule } from '../../shared';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import {sectorAguaAguaRoute, SectorAguaResolvePagingParams} from './sector-agua.route';
import {CoberturaSectorAguaComponent} from './sector-agua.component';
import {ProvinciaService} from '../../entities/provincia';
import {RelatoriosService} from '../relatorios.service';

const ENTITY_STATES = [
    ...sectorAguaAguaRoute
];

@NgModule({
    imports: [
        SisasSharedModule,
        ChartsModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        CoberturaSectorAguaComponent
    ],
    entryComponents: [
        CoberturaSectorAguaComponent
    ],
    providers: [
        SectorAguaResolvePagingParams,
        ProvinciaService,
        RelatoriosService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasSectorAguaModule {}
