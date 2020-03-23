import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {SisasAuthorityModule} from './authority/authority.module';
import {SisasDashboardModule} from './dashboard/dashboard.module';
import {SisasIndicadorProducaoModule} from './indicador-producao/indicador-producao.module';
import {SisasProgramasProjectosModule} from './programas-projectos/programas-projectos.module';
import {SisasSistemaAguaModule} from './sistema-agua/sistema-agua.module';
import {SisasSituacaoModule} from './situacao/situacao.module';
import {SisasComunaModule} from './comuna/comuna.module';
import {SisasMunicipioModule} from './municipio/municipio.module';
import {SisasFornecedorModule} from './fornecedor/fornecedor.module';
import {SisasProvinciaModule} from './provincia/provincia.module';
import {SisasSistemaAguaLogModule} from './sistema-agua-log/sistema-agua-log.module';
import {SisasIndicadorProducaoLogModule} from './indicador-producao-log/indicador-producao-log.module';
import {SisasProgramasProjectosLogModule} from './programas-projectos-log/programas-projectos-log.module';
import {SisasEpasModule} from './epas/epas.module';
import {SisasFinalidadeProjetoModule} from './finalidade-projeto/finalidade-projeto.module';
import {SisasEspecialidadesModule} from './especialidades/especialidades.module';
import {SisasNoticiasPortalModule} from './noticias-portal/noticias-portal.module';
import {SisasBannerModule} from './banner/banner.module';
import {SisasPdfModule} from './cadastro-pdf/cadPdf.module';
import {SisasSobreDnaModule} from './sobre-dna/sobre-dna.module';
import {SisasContactoDnaModule} from './contacto/contacto.module';
import {SisasIndicadorArquivoModule} from './indicador-prod-arquivo/indicadorArquivo.module';
import {SisasSistemasArquivoModule} from './sistemas-agua-arquivo/sistemaAguaArquivo.module';
import {SisasSegurancasLogModule} from './segurancas-log/segurancas-log.module';

@NgModule({
    imports: [
        SisasDashboardModule,
        SisasAuthorityModule,
        SisasIndicadorProducaoModule,
        SisasProgramasProjectosModule,
        SisasSistemaAguaModule,
        SisasSituacaoModule,
        SisasComunaModule,
        SisasMunicipioModule,
        SisasFornecedorModule,
        SisasProvinciaModule,
        SisasEpasModule,
        SisasEspecialidadesModule,
        SisasFinalidadeProjetoModule,
        SisasSistemaAguaLogModule,
        SisasIndicadorProducaoLogModule,
        SisasProgramasProjectosLogModule,
        SisasNoticiasPortalModule,
        SisasBannerModule,
        SisasPdfModule,
        SisasSobreDnaModule,
        SisasContactoDnaModule,
        SisasIndicadorArquivoModule,
        SisasSistemasArquivoModule,
        SisasSegurancasLogModule
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SisasEntityModule {
}
