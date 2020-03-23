import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SisasSharedModule } from '../../shared';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import {
    BeneficiariosBombEnergiaResolvePagingParams,
    beneficiariosTpBombaEnergiaRoute,
} from './beneficiarios-bmb-energia.route';
import {BeneficiariosBombEnergiaComponent} from './beneficiarios-bmb-energia.component';

const ENTITY_STATES = [
    ...beneficiariosTpBombaEnergiaRoute
];

@NgModule({
    imports: [
        SisasSharedModule,
        ChartsModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        BeneficiariosBombEnergiaComponent
    ],
    entryComponents: [
        BeneficiariosBombEnergiaComponent
    ],
    providers: [
        BeneficiariosBombEnergiaResolvePagingParams
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasBenefBmbEnergiaModule {}
