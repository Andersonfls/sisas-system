import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

import {InqueritosPreenchidosDados} from './inqueritos-preenchidos/InqueritosPreenchidosDados.model';
import {SERVER_API_URL} from '../app.constants';
import {SectorAguaSaneamentoDados} from './cobertura-sector-agua-saneamento/SectorAguaSaneamentoDados.model';
import {FuncAguaChafarizes} from './funcionamento-agua-chafarizes/FuncAguaChafarizes.model';
import {Provincia} from '../entities/provincia';
import {createRequestOption} from '../shared';
import {TratamentoSistemaAguaDados} from './tratamento-sistemas-agua/tratamentoSistemasAguaDados.model';
import {IndicadorProducaoProvincia} from './indicador-producao/IndicadorProducaoProvincia.model';
import {CoberturaSectorAguaModel} from './cobertura-sector-agua-provincial/coberturaSectorAgua.model';
import {FuncAgua} from './funcionamento-agua/FuncAgua.model';
import {SectorAguaDados} from './cobertura-sector-agua-excluir/SectorAguaDados.model';
import {BeneficiariosBmbMecanica} from './beneficiarios-agua-ft-subt-bomb-mecanica/beneficiarios-bmb-mecanica.model';

export type EntityResponseType = HttpResponse<Provincia>;

@Injectable()
export class RelatoriosService {

    private resourceUrl = SERVER_API_URL + 'api/relatorios';

    constructor(private http: HttpClient) {
    }

    buscaDadosSectorAguaSaneamento(req?: any): Observable<HttpResponse<SectorAguaSaneamentoDados[]>> {
        const options = createRequestOption(req);
        return this.http.get<SectorAguaSaneamentoDados[]>(this.resourceUrl + '/provincias/relatorio-saneamento', {params: options, observe: 'response'});
    }
    buscaNomeCampos(req?: any): Observable<HttpResponse<IndicadorProducaoProvincia[]>> {
        const options = createRequestOption(req);
        return this.http.get<IndicadorProducaoProvincia[]>(this.resourceUrl + '/provincias/relatorio-indicador-producao', {params: options, observe: 'response'});
    }

    buscaDadosInqueritosPreenchidos(req?: any): Observable<HttpResponse<InqueritosPreenchidosDados[]>> {
        const options = createRequestOption(req);
        return this.http.get<InqueritosPreenchidosDados[]>(this.resourceUrl + '/inqueritos-preenchidos', {params: options, observe: 'response'});
    }

    // NOVOS RELATORIO 13/03
    buscaDadosCoberturaSectorAguaProvincial(req?: any): Observable<HttpResponse<CoberturaSectorAguaModel[]>> {
        const options = createRequestOption(req);
        return this.http.get<CoberturaSectorAguaModel[]>(this.resourceUrl + '/cobertura-sector-agua-provincial', {params: options, observe: 'response'});
    }
    buscaDadosCoberturaSectorAguaMunicipal(req?: any): Observable<HttpResponse<CoberturaSectorAguaModel[]>> {
        const options = createRequestOption(req);
        return this.http.get<CoberturaSectorAguaModel[]>(this.resourceUrl + '/cobertura-sector-agua-municipal', {params: options, observe: 'response'});
    }
    buscaDadosCoberturaSectorAguaComunal(req?: any): Observable<HttpResponse<CoberturaSectorAguaModel[]>> {
        const options = createRequestOption(req);
        return this.http.get<CoberturaSectorAguaModel[]>(this.resourceUrl + '/cobertura-sector-agua-comunal', {params: options, observe: 'response'});
    }

    buscaDadosBeneficiariosBmbMecanicaProvincial(req?: any): Observable<HttpResponse<FuncAguaChafarizes[]>> {
        const options = createRequestOption(req);
        return this.http.get<BeneficiariosBmbMecanica[]>(this.resourceUrl + '/benef-bomba-mecanica-provincial', {params: options, observe: 'response'});
    }

    buscaDadosFuncAguaChafarizMunicipal(req?: any): Observable<HttpResponse<FuncAguaChafarizes[]>> {
        const options = createRequestOption(req);
        return this.http.get<FuncAguaChafarizes[]>(this.resourceUrl + '/func-agua-chaf-municipal', {params: options, observe: 'response'});
    }

    buscaDadosTratamentoSistemasAgua(req?: any): Observable<HttpResponse<TratamentoSistemaAguaDados[]>> {
        const options = createRequestOption(req);
        return this.http.get<TratamentoSistemaAguaDados[]>(this.resourceUrl + '/trat-sistemas-agua', {params: options, observe: 'response'});
    }

    buscaDadosTratamentoSistemasAguaMunicipal(req?: any): Observable<HttpResponse<TratamentoSistemaAguaDados[]>> {
        const options = createRequestOption(req);
        return this.http.get<TratamentoSistemaAguaDados[]>(this.resourceUrl + '/trat-sistemas-agua-municipal', {params: options, observe: 'response'});
    }

    buscaDadosTratamentoSistemasAguaComunal(req?: any): Observable<HttpResponse<TratamentoSistemaAguaDados[]>> {
        const options = createRequestOption(req);
        return this.http.get<TratamentoSistemaAguaDados[]>(this.resourceUrl + '/trat-sistemas-agua-comunal', {params: options, observe: 'response'});
    }

    buscaDadosFuncSistAguaProvincial(req?: any): Observable<HttpResponse<FuncAgua[]>> {
        const options = createRequestOption(req);
        return this.http.get<FuncAgua[]>(this.resourceUrl + '/func-sis-agua-provincial', {params: options, observe: 'response'});
    }

    buscaDadosFuncSistAguaMunicipal(req?: any): Observable<HttpResponse<FuncAguaChafarizes[]>> {
        const options = createRequestOption(req);
        return this.http.get<FuncAgua[]>(this.resourceUrl + '/func-sis-agua-municipal', {params: options, observe: 'response'});
    }

    buscaDadosFuncSistAguaComunal(req?: any): Observable<HttpResponse<FuncAguaChafarizes[]>> {
        const options = createRequestOption(req);
        return this.http.get<FuncAgua[]>(this.resourceUrl + '/func-sis-agua-comunal', {params: options, observe: 'response'});
    }

    buscaDadosSectorAguaProvincia(req?: any): Observable<HttpResponse<CoberturaSectorAguaModel[]>> {
        const options = createRequestOption(req);
        return this.http.get<CoberturaSectorAguaModel[]>(this.resourceUrl + '/provincias/relatorio-saneamento-provincia', {params: options, observe: 'response'});
    }
    buscaDadosSectorAguaMunicipio(req?: any): Observable<HttpResponse<CoberturaSectorAguaModel[]>> {
        const options = createRequestOption(req);
        return this.http.get<CoberturaSectorAguaModel[]>(this.resourceUrl + '/provincias/relatorio-saneamento-municipio', {params: options, observe: 'response'});
    }
    buscaDadosSectorAguaComuna(req?: any): Observable<HttpResponse<CoberturaSectorAguaModel[]>> {
        const options = createRequestOption(req);
        return this.http.get<CoberturaSectorAguaModel[]>(this.resourceUrl + '/provincias/relatorio-saneamento-comuna', {params: options, observe: 'response'});
    }

}
