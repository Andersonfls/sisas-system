import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {SisasMap2Module} from './nacional/map2.module';
import {SisasHuliaAguaFuncionamModule} from './huliaAguaFuncionam/hulia.module';
import {SisasHuamboModule} from './huambo/huambo.module';
import {SisasKuanzaNorteModule} from './kuanzaNorte/kuanzaNorte.module';
import {SisasMunicipalModule} from './municipal/municipal.module';
import {SisasKuanzaNorteSaneamentoModule} from './kuanzaNorteSaneamento/kuanzaNorte.module';
import {SisasKuanzaNorteSaneamentoFuncionamModule} from './kuanzaNorteSaneamentoFunciona/kuanzaNorte.module';
import {SisasKuanzaNorteSaneamentoNaoFuncionamModule} from './kuanzaNorteSaneamentoNaoFunciona/kuanzaNorte.module';
import {SisasKuanzaNorteAguaFuncionamModule} from './kuanzaNorteAguaFuncionam/kuanzaNorte.module';
import {SisasKuanzaNorteAguaNaoFuncionamModule} from './kuanzaNorteAguaNaoFuncionam/kuanzaNorte.module';
import {SisasHuliaNaoFuncionamModule} from './huliaAguaNaoFuncionam/hulia.module';
import {SisasHuliaAguaModule} from './huliaAgua/hulia.module';
import {SisasHuliaSaneamentoModule} from './huliaSaneamento/hulia.module';
import {SisasHuliaSaneamentoFuncionamModule} from './huliaSaneamentoFuncionam/hulia.module';
import {SisasHuliaSaneamentoNaoFuncionamModule} from './huliaSaneamentoNaoFuncionam/hulia.module';

@NgModule({
    imports: [
        SisasMap2Module,
        SisasHuamboModule,
        SisasKuanzaNorteModule,
        SisasMunicipalModule,
        SisasKuanzaNorteSaneamentoModule,
        SisasKuanzaNorteSaneamentoFuncionamModule,
        SisasKuanzaNorteSaneamentoNaoFuncionamModule,
        SisasKuanzaNorteAguaFuncionamModule,
        SisasKuanzaNorteAguaNaoFuncionamModule,
        SisasHuliaNaoFuncionamModule,
        SisasHuliaAguaFuncionamModule,
        SisasHuliaAguaModule,
        SisasHuliaSaneamentoModule,
        SisasHuliaSaneamentoFuncionamModule,
        SisasHuliaSaneamentoNaoFuncionamModule
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasMapasTematicosModule {
}
