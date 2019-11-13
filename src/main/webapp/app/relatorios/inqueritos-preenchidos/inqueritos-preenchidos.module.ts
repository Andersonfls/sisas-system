import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SisasSharedModule } from '../../shared';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import {
    InqueritosPreenchidosResolvePagingParams,
    inqueritosPreenchidosRoute,
} from './inqueritos-preenchidos.route';
import { InqueritosPreenchidosComponent} from './inqueritos-preenchidos.component';
import {ProvinciaService} from '../../entities/provincia';

const ENTITY_STATES = [
    ...inqueritosPreenchidosRoute
];

@NgModule({
    imports: [
        SisasSharedModule,
        ChartsModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        InqueritosPreenchidosComponent
    ],
    entryComponents: [
        InqueritosPreenchidosComponent
    ],
    providers: [
        InqueritosPreenchidosResolvePagingParams,
        ProvinciaService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasInqueritosPreenchidosModule {}
