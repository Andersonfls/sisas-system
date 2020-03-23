import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SisasSharedModule } from '../../shared';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import {
    BeneficiariosBombManualResolvePagingParams,
    beneficiariosTpBombaManualRoute,
} from './beneficiarios-bmb-manual.route';
import {BeneficiariosBombManualComponent} from './beneficiarios-bmb-manual.component';

const ENTITY_STATES = [
    ...beneficiariosTpBombaManualRoute
];

@NgModule({
    imports: [
        SisasSharedModule,
        ChartsModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        BeneficiariosBombManualComponent
    ],
    entryComponents: [
        BeneficiariosBombManualComponent
    ],
    providers: [
        BeneficiariosBombManualResolvePagingParams
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasBenefBmbManualModule {}
