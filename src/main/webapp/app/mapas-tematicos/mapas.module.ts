import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {SisasMap2Module} from './nacional/map2.module';
import {SisasHuliaModule} from './hulia/hulia.module';
import {SisasHuamboModule} from './huambo/huambo.module';
import {SisasKuanzaNorteModule} from './kuanzaNorte/kuanzaNorte.module';

@NgModule({
    imports: [
        SisasMap2Module,
        SisasHuliaModule,
        SisasHuamboModule,
        SisasKuanzaNorteModule
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasMapasTematicosModule {
}
