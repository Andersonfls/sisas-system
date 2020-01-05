import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SisasSharedModule } from '../../shared/index';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import {HuamboComponent} from './huambo.component';
import {MapResolvePagingParams, huamboRoute} from './huambo.route';
import {LeafletModule} from '@asymmetrik/ngx-leaflet';

const ENTITY_STATES = [
    ...huamboRoute
];

@NgModule({
    imports: [
        SisasSharedModule,
        ChartsModule,
        LeafletModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        HuamboComponent
    ],
    entryComponents: [
        HuamboComponent
    ],
    providers: [
        MapResolvePagingParams
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasHuamboAguaModule {}
