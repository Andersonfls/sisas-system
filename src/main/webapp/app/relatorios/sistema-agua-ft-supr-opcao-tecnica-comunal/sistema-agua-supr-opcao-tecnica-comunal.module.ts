import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SisasSharedModule } from '../../shared';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import {
    SistemaAguaSupeOpcaoTecnicaComunalResolvePagingParams,
    sistemaAguaSuprOpcaoTecnicaComunalRoute,
} from './sistema-agua-supr-opcao-tecnica-comunal.route';
import {SistemaAguaSupeOpcaoTecnicaComunalComponent} from './sistema-agua-supr-opcao-tecnica-comunal.component';

const ENTITY_STATES = [
    ...sistemaAguaSuprOpcaoTecnicaComunalRoute
];

@NgModule({
    imports: [
        SisasSharedModule,
        ChartsModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SistemaAguaSupeOpcaoTecnicaComunalComponent
    ],
    entryComponents: [
        SistemaAguaSupeOpcaoTecnicaComunalComponent
    ],
    providers: [
        SistemaAguaSupeOpcaoTecnicaComunalResolvePagingParams
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasSistemaAguaSprOpcaoTecnicaComunalModule {}
