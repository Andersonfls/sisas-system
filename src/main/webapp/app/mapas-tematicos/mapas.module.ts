import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {SisasMap2Module} from './nacional/map2.module';
import {SisasHuliaModule} from './hulia/hulia.module';
import {SisasHuamboModule} from './huambo/huambo.module';
import {SisasKuanzaNorteModule} from './kuanzaNorte/kuanzaNorte.module';
import {SisasMunicipalModule} from './municipal/municipal.module';

@NgModule({
    imports: [
        SisasMap2Module,
        SisasHuliaModule,
        SisasHuamboModule,
        SisasKuanzaNorteModule,
        SisasMunicipalModule
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasMapasTematicosModule {
}
