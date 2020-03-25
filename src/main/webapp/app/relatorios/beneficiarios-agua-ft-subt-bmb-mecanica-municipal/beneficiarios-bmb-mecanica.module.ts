import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SisasSharedModule } from '../../shared';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import {BeneficiariosTpBombaComponent} from './beneficiarios-bmb-mecanica.component';
import {BeneficiariosTpBombaResolvePagingParams, beneficiariosTpBombaRoute} from './beneficiarios-bmb-mecanica.route';

const ENTITY_STATES = [
    ...beneficiariosTpBombaRoute
];

@NgModule({
    imports: [
        SisasSharedModule,
        ChartsModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        BeneficiariosTpBombaComponent
    ],
    entryComponents: [
        BeneficiariosTpBombaComponent
    ],
    providers: [
        BeneficiariosTpBombaResolvePagingParams
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasBeneficiariosTpBombaModule {}
