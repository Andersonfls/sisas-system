import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SisasSharedModule } from '../../shared';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import {
    BeneficiariosBombEnergiaComunalResolvePagingParams,
    beneficiariosTpBombaEnergiaComunalRoute,
} from './beneficiarios-bmb-energia-comunal.route';
import {BeneficiariosBombEnergiaComunalComponent} from './beneficiarios-bmb-energia-comunal.component';

const ENTITY_STATES = [
    ...beneficiariosTpBombaEnergiaComunalRoute
];

@NgModule({
    imports: [
        SisasSharedModule,
        ChartsModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        BeneficiariosBombEnergiaComunalComponent
    ],
    entryComponents: [
        BeneficiariosBombEnergiaComunalComponent
    ],
    providers: [
        BeneficiariosBombEnergiaComunalResolvePagingParams
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasBenefBmbEnergiaComunalModule {}
