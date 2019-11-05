import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SisasSharedModule } from '../../shared';
import { DashboardResolvePagingParams, dashboardRoute } from './dashboard.route';
import { DashboardComponent } from './dashboard.component';
import { ChartsModule } from 'ng2-charts/ng2-charts';

const ENTITY_STATES = [
    ...dashboardRoute
];

@NgModule({
    imports: [
        SisasSharedModule,
        ChartsModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        DashboardComponent
    ],
    entryComponents: [
        DashboardComponent
    ],
    providers: [
        DashboardResolvePagingParams
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasDashboardModule {}
