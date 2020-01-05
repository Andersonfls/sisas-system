import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {SisasHuliaAguaFuncionamModule} from './huliaAguaFuncionam/hulia.module';
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
import {SisasHuamboSaneamentoNaoFuncionamModule} from './huamboSaneamentoNaoFuncionam/huambo.module';
import {SisasHuamboSaneamentoFuncionamModule} from './huamboSaneamentoFuncionam/huambo.module';
import {SisasHuamboSaneamentoModule} from './huamboSaneamento/huambo.module';
import {SisasHuamboAguaNaoFuncionamModule} from './huamboAguaNaoFuncionam/huambo.module';
import {SisasHuamboAguaFuncionamModule} from './huamboAguaFuncionam/huambo.module';
import {SisasHuamboAguaModule} from './huamboAgua/huambo.module';
import {SisasNacionalAguaModule} from './nacionalAgua/nacional.module';
import {SisasNacionalAguaFuncionamModule} from './nacionalAguaFuncionam/nacional.module';
import {SisasNacionalAguaNaoFuncionamModule} from './nacionalAguaNaoFuncionam/nacional.module';
import {SisasNacionalSaneamentoModule} from './nacionalSaneamento/nacional.module';
import {SisasNacionalSaneamentoFuncionamModule} from './nacionalSaneamentoFuncionam/nacional.module';
import {SisasNacionalSaneamentoNaoFuncionamModule} from './nacionalSaneamentoNaoFuncionam/nacional.module';

@NgModule({
    imports: [
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
        SisasHuliaSaneamentoNaoFuncionamModule,
        SisasHuamboSaneamentoNaoFuncionamModule,
        SisasHuamboSaneamentoFuncionamModule,
        SisasHuamboSaneamentoModule,
        SisasHuamboAguaNaoFuncionamModule,
        SisasHuamboAguaFuncionamModule,
        SisasHuamboAguaModule,
        SisasNacionalAguaModule,
        SisasNacionalAguaFuncionamModule,
        SisasNacionalAguaNaoFuncionamModule,
        SisasNacionalSaneamentoModule,
        SisasNacionalSaneamentoFuncionamModule,
        SisasNacionalSaneamentoNaoFuncionamModule
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasMapasTematicosModule {
}
