import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {SisasSectorAguaModule} from './cobertura-sector-agua/sector-agua.module';
import {SisasSectorAguaSaneamentoModule} from './cobertura-sector-agua-saneamento/sector-agua-saneamento.module';
import {SisasFuncAguaChafarizesModule} from './funcionamento-agua-chafarizes/func-agua-chafarizes.module';
import {SisasInqueritosPreenchidosModule} from './inqueritos-preenchidos/inqueritos-preenchidos.module';
import {RelatoriosService} from './relatorios.service';
import {SisasTratamentoSistemasAguaModule} from './tratamento-sistemas-agua/tratamento-sistemas-agua.module';

@NgModule({
    imports: [
        SisasSectorAguaModule,
        SisasSectorAguaSaneamentoModule,
        SisasFuncAguaChafarizesModule,
        SisasInqueritosPreenchidosModule,
        SisasTratamentoSistemasAguaModule
    ],
    declarations: [],
    entryComponents: [],
    providers: [RelatoriosService],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasRelatoriosModule {
}
