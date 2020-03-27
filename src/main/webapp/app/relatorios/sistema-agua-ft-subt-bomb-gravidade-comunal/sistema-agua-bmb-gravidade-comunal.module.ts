import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SisasSharedModule } from '../../shared';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import {
    SistemaAguaBombGravidadeComunalResolvePagingParams,
    sistemaAguaTpBombaGravidadeComunalRoute,
} from './sistema-agua-bmb-gravidade-comunal.route';
import {SistemaAguaBombGravidadeComunalComponent} from './sistema-agua-bmb-gravidade-comunal.component';

const ENTITY_STATES = [
    ...sistemaAguaTpBombaGravidadeComunalRoute
];

@NgModule({
    imports: [
        SisasSharedModule,
        ChartsModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SistemaAguaBombGravidadeComunalComponent
    ],
    entryComponents: [
        SistemaAguaBombGravidadeComunalComponent
    ],
    providers: [
        SistemaAguaBombGravidadeComunalResolvePagingParams
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasSistemaAguaBmbGravidadeComunalModule {}
