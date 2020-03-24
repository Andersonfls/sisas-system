import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SisasSharedModule } from '../../shared';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import {
    BeneficiariosBombEnergiaProvincialResolvePagingParams,
    beneficiariosTpBombaEnergiaProvincialRoute,
} from './beneficiarios-bmb-energia-provincial.route';
import {BeneficiariosBombEnergiaProvincialComponent} from './beneficiarios-bmb-energia-provincial.component';

const ENTITY_STATES = [
    ...beneficiariosTpBombaEnergiaProvincialRoute
];

@NgModule({
    imports: [
        SisasSharedModule,
        ChartsModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        BeneficiariosBombEnergiaProvincialComponent
    ],
    entryComponents: [
        BeneficiariosBombEnergiaProvincialComponent
    ],
    providers: [
        BeneficiariosBombEnergiaProvincialResolvePagingParams
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasBenefBmbEnergiaProvincialModule {}
