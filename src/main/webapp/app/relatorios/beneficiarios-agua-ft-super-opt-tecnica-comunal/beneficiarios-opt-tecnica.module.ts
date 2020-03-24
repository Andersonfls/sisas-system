import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SisasSharedModule } from '../../shared';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import {BeneficiariosOptTecnicaComponent} from './beneficiarios-opt-tecnica.component';
import {
    BeneficiariosOptTecnicaResolvePagingParams,
    beneficiariosOptTecnicaRoute
} from './beneficiarios-opt-tecnica.route';

const ENTITY_STATES = [
    ...beneficiariosOptTecnicaRoute
];

@NgModule({
    imports: [
        SisasSharedModule,
        ChartsModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        BeneficiariosOptTecnicaComponent
    ],
    entryComponents: [
        BeneficiariosOptTecnicaComponent
    ],
    providers: [
        BeneficiariosOptTecnicaResolvePagingParams
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasBenefOptTecnicaComunalModule {}
