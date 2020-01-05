import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SisasSharedModule } from '../../shared/index';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import {NacionalComponent} from './nacional.component';
import {MapResolvePagingParams, nacionalRoute} from './nacional.route';
import {LeafletModule} from '@asymmetrik/ngx-leaflet';

const ENTITY_STATES = [
    ...nacionalRoute
];

@NgModule({
    imports: [
        SisasSharedModule,
        ChartsModule,
        LeafletModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        NacionalComponent
    ],
    entryComponents: [
        NacionalComponent
    ],
    providers: [
        MapResolvePagingParams
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasNacionalSaneamentoFuncionamModule {}
