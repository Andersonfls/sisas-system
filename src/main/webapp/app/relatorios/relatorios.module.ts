import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {SisasSectorAguaModule} from './cobertura-sector-agua/sector-agua.module';
import {SisasSectorAguaSaneamentoModule} from './cobertura-sector-agua-saneamento/sector-agua-saneamento.module';
import {SisasFuncAguaChafarizesModule} from './funcionamento-agua-chafarizes/func-agua-chafarizes.module';

@NgModule({
    imports: [
        SisasSectorAguaModule,
        SisasSectorAguaSaneamentoModule,
        SisasFuncAguaChafarizesModule
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasRelatoriosModule {
}
