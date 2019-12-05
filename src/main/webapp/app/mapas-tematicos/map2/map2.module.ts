import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SisasSharedModule } from '../../shared/index';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import {Map2Component} from './map2.component';
import {MapResolvePagingParams, map2Route} from './map2.route';
import {LeafletModule} from '@asymmetrik/ngx-leaflet';
import {UploadFileService} from './upload-file.service';

const ENTITY_STATES = [
    ...map2Route
];

@NgModule({
    imports: [
        SisasSharedModule,
        ChartsModule,
        LeafletModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        Map2Component
    ],
    entryComponents: [
        Map2Component
    ],
    providers: [
        MapResolvePagingParams,
        UploadFileService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasMap2Module {}
