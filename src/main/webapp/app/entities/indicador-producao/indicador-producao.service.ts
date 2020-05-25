import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {SERVER_API_URL} from '../../app.constants';

import {JhiDateUtils} from 'ng-jhipster';

import {IndicadorProducao} from './indicador-producao.model';
import {createRequestOption} from '../../shared';
import {SistemaAgua} from '../sistema-agua';

export type EntityResponseType = HttpResponse<IndicadorProducao>;

@Injectable()
export class IndicadorProducaoService {

    private resourceUrl =  SERVER_API_URL + 'api/indicador-producaos';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(indicadorProducao: IndicadorProducao): Observable<EntityResponseType> {
        const copy = this.convert(indicadorProducao);
        return this.http.post<IndicadorProducao>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    createFromArquivo(indicadorProducao: IndicadorProducao): Observable<EntityResponseType> {
        return this.http.post<IndicadorProducao>(this.resourceUrl, indicadorProducao, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(indicadorProducao: IndicadorProducao): Observable<EntityResponseType> {
        const copy = this.convert(indicadorProducao);
        return this.http.put<IndicadorProducao>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IndicadorProducao>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<IndicadorProducao[]>> {
        const options = createRequestOption(req);
        return this.http.get<IndicadorProducao[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<IndicadorProducao[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    queryUserNome(req: any): Observable<HttpResponse<IndicadorProducao[]>> {
        const params: HttpParams = createRequestOption(req);

        const requestURL = SERVER_API_URL + 'api/indicador-producaos/nomeFiltro';

        return this.http.get<IndicadorProducao[]>(requestURL, {
            params,
            observe: 'response'
        });
    }

    findLast(): Observable<EntityResponseType> {
        return this.http.get<IndicadorProducao>(`${this.resourceUrl}/last`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    // barra de pesquisa
    queryAno(req: any): Observable<HttpResponse<IndicadorProducao[]>> {
        const params: HttpParams = createRequestOption(req);

        const requestURL = this.resourceUrl + '/anoFiltro';

        return this.http.get<IndicadorProducao[]>(requestURL, {
            params,
            observe: 'response'
        });
    }

    queryProvincia(req: any): Observable<HttpResponse<SistemaAgua[]>> {
        const params: HttpParams = createRequestOption(req);

        const requestURL = this.resourceUrl + '/provinciaFiltro';

        return this.http.get<SistemaAgua[]>(requestURL, {
            params,
            observe: 'response'
        });
    }

    queryAnoeProvincia(req: any): Observable<HttpResponse<IndicadorProducao[]>> {
        const params: HttpParams = createRequestOption(req);

        const requestURL = this.resourceUrl + '/anoProvinciaFiltro';

        return this.http.get<IndicadorProducao[]>(requestURL, {
            params,
            observe: 'response'
        });
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: IndicadorProducao = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<IndicadorProducao[]>): HttpResponse<IndicadorProducao[]> {
        const jsonResponse: IndicadorProducao[] = res.body;
        const body: IndicadorProducao[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to IndicadorProducao.
     */
    private convertItemFromServer(indicadorProducao: IndicadorProducao): IndicadorProducao {
        const copy: IndicadorProducao = Object.assign({}, indicadorProducao);
        copy.dtLancamento = this.dateUtils
            .convertLocalDateFromServer(indicadorProducao.dtLancamento);
        copy.dtUltimaAlteracao = this.dateUtils
            .convertLocalDateFromServer(indicadorProducao.dtUltimaAlteracao);
        return copy;
    }

    /**
     * Convert a IndicadorProducao to a JSON which can be sent to the server.
     */
    private convert(indicadorProducao: IndicadorProducao): IndicadorProducao {
        const copy: IndicadorProducao = Object.assign({}, indicadorProducao);
        copy.dtLancamento = this.dateUtils
            .convertLocalDateToServer(indicadorProducao.dtLancamento);
        copy.dtUltimaAlteracao = this.dateUtils
            .convertLocalDateToServer(indicadorProducao.dtUltimaAlteracao);
        return copy;
    }
}
