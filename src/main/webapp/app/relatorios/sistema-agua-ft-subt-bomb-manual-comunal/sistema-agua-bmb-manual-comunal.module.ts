import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SisasSharedModule } from '../../shared';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import {
    SistemaAguaBombManualComunalResolvePagingParams,
    sistemaAguaTpBombaManualComunalRoute,
} from './sistema-agua-bmb-manual-comunal.route';
import {SistemaAguaBombManualComunalComponent} from './sistema-agua-bmb-manual-comunal.component';

const ENTITY_STATES = [
    ...sistemaAguaTpBombaManualComunalRoute
];

@NgModule({
    imports: [
        SisasSharedModule,
        ChartsModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SistemaAguaBombManualComunalComponent
    ],
    entryComponents: [
        SistemaAguaBombManualComunalComponent
    ],
    providers: [
        SistemaAguaBombManualComunalResolvePagingParams
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasSistemaAguaBmbManualComunalModule {}
