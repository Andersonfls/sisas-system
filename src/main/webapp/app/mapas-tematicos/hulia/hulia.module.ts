import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SisasSharedModule } from '../../shared/index';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import {HuliaComponent} from './hulia.component';
import {HuliaResolvePagingParams, huliaRoute} from './hulia.route';
import {LeafletModule} from '@asymmetrik/ngx-leaflet';

const ENTITY_STATES = [
    ...huliaRoute
];

@NgModule({
    imports: [
        SisasSharedModule,
        ChartsModule,
        LeafletModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        HuliaComponent
    ],
    entryComponents: [
        HuliaComponent
    ],
    providers: [
        HuliaResolvePagingParams
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasHuliaModule {}
