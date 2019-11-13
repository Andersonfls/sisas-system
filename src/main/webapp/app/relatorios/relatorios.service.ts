import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

import {InqueritosPreenchidosDados} from './inqueritos-preenchidos/InqueritosPreenchidosDados.model';
import {SERVER_API_URL} from '../app.constants';
import {SectorAguaDados} from './cobertura-sector-agua/SectorAguaDados.model';
import {SectorAguaSaneamentoDados} from './cobertura-sector-agua-saneamento/SectorAguaSaneamentoDados.model';
import {FuncAguaChafarizes} from './funcionamento-agua-chafarizes/FuncAguaChafarizes.model';
import {Provincia} from '../entities/provincia';
import {createRequestOption} from '../shared';
import {TratamentoSistemaAguaDados} from './tratamento-sistemas-agua/tratamentoSistemasAguaDados.model';

export type EntityResponseType = HttpResponse<Provincia>;

@Injectable()
export class RelatoriosService {

    private resourceUrl = SERVER_API_URL + 'api/relatorios';

    constructor(private http: HttpClient) {
    }

    buscaDadosSectorAgua(req?: any): Observable<HttpResponse<SectorAguaDados[]>> {
        const options = createRequestOption(req);
        return this.http.get<SectorAguaDados[]>(this.resourceUrl + '/provincias/relatorio', {params: options, observe: 'response'});
    }

    buscaDadosSectorAguaSaneamento(req?: any): Observable<HttpResponse<SectorAguaSaneamentoDados[]>> {
        const options = createRequestOption(req);
        return this.http.get<SectorAguaSaneamentoDados[]>(this.resourceUrl + '/provincias/relatorio-saneamento', {params: options, observe: 'response'});
    }

    buscaDadosFuncAguaChafariz(req?: any): Observable<HttpResponse<FuncAguaChafarizes[]>> {
        const options = createRequestOption(req);
        return this.http.get<FuncAguaChafarizes[]>(this.resourceUrl + '/provincias/relatorio-agua-chafarizes', {params: options, observe: 'response'});
    }

    buscaDadosInqueritosPreenchidos(req?: any): Observable<HttpResponse<InqueritosPreenchidosDados[]>> {
        const options = createRequestOption(req);
        return this.http.get<InqueritosPreenchidosDados[]>(this.resourceUrl + '/inqueritos-preenchidos', {params: options, observe: 'response'});
    }

    buscaDadosInqueritosPreenchidos2(req?: any): Observable<HttpResponse<InqueritosPreenchidosDados[]>> {
        const options = createRequestOption(req);
        return this.http.get<InqueritosPreenchidosDados[]>(this.resourceUrl + '/inqueritos-preenchidos2', {params: options, observe: 'response'});
    }

    buscaDadosTratamentoSistemasAgua(req?: any): Observable<HttpResponse<TratamentoSistemaAguaDados[]>> {
        const options = createRequestOption(req);
        return this.http.get<TratamentoSistemaAguaDados[]>(this.resourceUrl + '/trat-sistemas-agua', {params: options, observe: 'response'});
    }

}
