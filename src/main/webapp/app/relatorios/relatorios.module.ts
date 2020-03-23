import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {SisasSectorAguaSaneamentoModule} from './cobertura-sector-agua-saneamento/sector-agua-saneamento.module';
import {SisasInqueritosPreenchidosModule} from './inqueritos-preenchidos/inqueritos-preenchidos.module';
import {RelatoriosService} from './relatorios.service';
import {SisasTratamentoSistemasAguaModule} from './tratamento-sistemas-agua/tratamento-sistemas-agua.module';
import {SisasFuncAguaModule} from './funcionamento-agua/func-agua.module';
import {SisasBenefOptTecnicaModule} from './beneficiarios-agua-ft-super-opt-tecnica/beneficiarios-opt-tecnica.module';
import {SisasBenefBmbMecanicaModule} from './beneficiarios-agua-ft-subt-bomb-mecanica/beneficiarios-bmb-mecanica.module';
import {SisasIndicadorProducaoProvinciaModule} from './indicador-producao/indicador-producao-provincia.module';
import {SisasSectorAguaProvincialModule} from './cobertura-sector-agua-provincial/sector-agua.module';
import {SisasSectorAguaComunalModule} from './cobertura-sector-agua-comunal/sector-agua.module';
import {SisasSectorAguaMunicipioModule} from './cobertura-sector-agua-municipal/sector-agua.module';
import { SisasTratamentoSistemasAguaComunaModule} from './tratamento-sistemas-agua-municipal/tratamento-sistemas-agua.module';
import {SisasTratamentoSistemasAguaMunicipioModule} from './tratamento-sistemas-agua-comunal/tratamento-sistemas-agua.module';
import {SisasFuncAguaComunalModule} from './funcionamento-agua-comunal/func-agua.module';
import {SisasFuncAguaMunicipalModule} from './funcionamento-agua-municipal/func-agua.module';
import {SisasBenefAguaTpBombaModule} from './beneficiarios-agua-ft-subt-tp-bmb/tratamento-sistemas-agua.module';
import {SisasBenefAguaTpBombaMuniModule} from './beneficiarios-agua-ft-subt-tp-bmb-muni/tratamento-sistemas-agua.module';
import {SisasBenefAguaTpBombaManualProModule} from './beneficiarios-agua-ft-subt-tp-bmb-manual-pro/tratamento-sistemas-agua.module';
import {SisasFuncAguaChafarizesComunalModule} from './funcionamento-agua-chafarizes-comunal/func-agua-chafarizes.module';
import {SisasFuncAguaChafarizesMunicipalModule} from './funcionamento-agua-chafarizes-municipal/func-agua-chafarizes.module';
import {SisasFuncAguaChafarizesProvincialModule} from './funcionamento-agua-chafarizes-provincial/func-agua-chafarizes.module';

@NgModule({
    imports: [
        SisasSectorAguaSaneamentoModule,
        SisasIndicadorProducaoProvinciaModule,
        SisasInqueritosPreenchidosModule,
        SisasTratamentoSistemasAguaModule,
        SisasFuncAguaModule,
        SisasBenefOptTecnicaModule,
        SisasBenefBmbMecanicaModule,
        SisasSectorAguaProvincialModule,
        SisasSectorAguaComunalModule,
        SisasSectorAguaMunicipioModule,
        SisasTratamentoSistemasAguaMunicipioModule,
        SisasTratamentoSistemasAguaComunaModule,
        SisasFuncAguaComunalModule,
        SisasFuncAguaMunicipalModule,
        SisasBenefAguaTpBombaModule,
        SisasBenefAguaTpBombaMuniModule,
        SisasBenefAguaTpBombaManualProModule,
        SisasFuncAguaChafarizesComunalModule,
        SisasFuncAguaChafarizesMunicipalModule,
        SisasFuncAguaChafarizesProvincialModule
    ],
    declarations: [],
    entryComponents: [],
    providers: [RelatoriosService],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasRelatoriosModule {
}
