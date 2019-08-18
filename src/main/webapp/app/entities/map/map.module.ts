import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SisasSharedModule } from '../../shared';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import {MapComponent} from "./map.component";
import {MapResolvePagingParams, mapRoute} from "./map.route";

const ENTITY_STATES = [
    ...mapRoute
];

@NgModule({
    imports: [
        SisasSharedModule,
        ChartsModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        MapComponent
    ],
    entryComponents: [
        MapComponent
    ],
    providers: [
        MapResolvePagingParams
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasMapModule {}
