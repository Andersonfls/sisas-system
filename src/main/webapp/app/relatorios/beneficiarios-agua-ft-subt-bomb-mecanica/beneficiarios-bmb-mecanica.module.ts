import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SisasSharedModule } from '../../shared';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import {
    BeneficiariosBombMecanicaResolvePagingParams,
    beneficiariosTpBombaMecanicaRoute,
} from './beneficiarios-bmb-mecanica.route';
import {BeneficiariosBombMecanicaComponent} from './beneficiarios-bmb-mecanica.component';

const ENTITY_STATES = [
    ...beneficiariosTpBombaMecanicaRoute
];

@NgModule({
    imports: [
        SisasSharedModule,
        ChartsModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        BeneficiariosBombMecanicaComponent
    ],
    entryComponents: [
        BeneficiariosBombMecanicaComponent
    ],
    providers: [
        BeneficiariosBombMecanicaResolvePagingParams
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasBenefBmbMecanicaModule {}
