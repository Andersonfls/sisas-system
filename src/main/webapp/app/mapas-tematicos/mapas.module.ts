import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {SisasMapModule} from './map/map.module';
import {SisasMap2Module} from './map2/map2.module';

@NgModule({
    imports: [
        SisasMapModule,
        SisasMap2Module
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasMapasTematicosModule {
}
