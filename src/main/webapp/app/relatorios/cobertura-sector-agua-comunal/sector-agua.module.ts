import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SisasSharedModule } from '../../shared';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import {sectorAguaAguaRoute, SectorAguaResolvePagingParams} from './sector-agua.route';
import {ProvinciaService} from '../../entities/provincia';
import {RelatoriosService} from '../relatorios.service';
import {CoberturaSectorAguaProvincialComponent} from './sector-agua-comunal.component';

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
        CoberturaSectorAguaProvincialComponent
    ],
    entryComponents: [
        CoberturaSectorAguaProvincialComponent
    ],
    providers: [
        SectorAguaResolvePagingParams,
        ProvinciaService,
        RelatoriosService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasSectorAguaComunalModule {}
