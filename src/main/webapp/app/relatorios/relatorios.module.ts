import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {SisasSectorAguaModule} from './cobertura-sector-agua/sector-agua.module';
import {SisasSectorAguaSaneamentoModule} from './cobertura-sector-agua-saneamento/sector-agua-saneamento.module';
import {SisasFuncAguaChafarizesModule} from './funcionamento-agua-chafarizes/func-agua-chafarizes.module';
import {SisasInqueritosPreenchidosModule} from './inqueritos-preenchidos/inqueritos-preenchidos.module';
import {RelatoriosService} from './relatorios.service';
import {SisasTratamentoSistemasAguaModule} from './tratamento-sistemas-agua/tratamento-sistemas-agua.module';
import {SisasFuncAguaModule} from './funcionamento-agua/func-agua.module';
import {SisasBenefOptTecnicaModule} from './beneficiarios-agua-ft-super-opt-tecnica/beneficiarios-opt-tecnica.module';
import {SisasBenefBmbMecanicaModule} from './beneficiarios-agua-ft-subt-bomb-mecanica/beneficiarios-bmb-mecanica.module';
import {SisasBeneficiariosTpBombaModule} from './beneficiarios-agua-ft-subt-bomba/beneficiarios-tp-bmb.module';
import {SisasIndicadorProducaoProvinciaModule} from './indicador-producao/indicador-producao-provincia.module';

@NgModule({
    imports: [
        SisasSectorAguaModule,
        SisasSectorAguaSaneamentoModule,
        SisasFuncAguaChafarizesModule,
        SisasIndicadorProducaoProvinciaModule,
        SisasInqueritosPreenchidosModule,
        SisasTratamentoSistemasAguaModule,
        SisasFuncAguaModule,
        SisasBenefOptTecnicaModule,
        SisasBenefBmbMecanicaModule,
        SisasBeneficiariosTpBombaModule
    ],
    declarations: [],
    entryComponents: [],
    providers: [RelatoriosService],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasRelatoriosModule {
}
