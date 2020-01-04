import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {SisasMap2Module} from './nacional/map2.module';
import {SisasHuliaModule} from './hulia/hulia.module';
import {SisasHuamboModule} from './huambo/huambo.module';
import {SisasKuanzaNorteModule} from './kuanzaNorte/kuanzaNorte.module';
import {SisasMunicipalModule} from './municipal/municipal.module';
import {SisasKuanzaNorteSaneamentoModule} from './kuanzaNorteSaneamento/kuanzaNorte.module';
import {SisasKuanzaNorteSaneamentoFuncionamModule} from './kuanzaNorteSaneamentoFunciona/kuanzaNorte.module';
import {SisasKuanzaNorteSaneamentoNaoFuncionamModule} from './kuanzaNorteSaneamentoNaoFunciona/kuanzaNorte.module';
import {SisasKuanzaNorteAguaFuncionamModule} from './kuanzaNorteAguaFuncionam/kuanzaNorte.module';
import {SisasKuanzaNorteAguaNaoFuncionamModule} from './kuanzaNorteAguaNaoFuncionam/kuanzaNorte.module';

@NgModule({
    imports: [
        SisasMap2Module,
        SisasHuliaModule,
        SisasHuamboModule,
        SisasKuanzaNorteModule,
        SisasMunicipalModule,
        SisasKuanzaNorteSaneamentoModule,
        SisasKuanzaNorteSaneamentoFuncionamModule,
        SisasKuanzaNorteSaneamentoNaoFuncionamModule,
        SisasKuanzaNorteAguaFuncionamModule,
        SisasKuanzaNorteAguaNaoFuncionamModule
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasMapasTematicosModule {
}
