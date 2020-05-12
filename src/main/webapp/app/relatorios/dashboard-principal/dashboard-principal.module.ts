import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SisasSharedModule } from '../../shared';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import {
    InqueritosPreenchidosResolvePagingParams,
    dashboardPrincipalRoute,
} from './dashboard-principal.route';
import { DashboardPrincipalComponent} from './dashboard-principal.component';
import {ProvinciaService} from '../../entities/provincia';

const ENTITY_STATES = [
    ...dashboardPrincipalRoute
];

@NgModule({
    imports: [
        SisasSharedModule,
        ChartsModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        DashboardPrincipalComponent
    ],
    entryComponents: [
        DashboardPrincipalComponent
    ],
    providers: [
        InqueritosPreenchidosResolvePagingParams,
        ProvinciaService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasDashboardPrincipalModule {}
