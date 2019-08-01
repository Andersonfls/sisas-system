import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {SERVER_API_URL} from '../../app.constants';

import {JhiDateUtils} from 'ng-jhipster';

import {SistemaAgua} from './sistema-agua.model';
import {createRequestOption} from '../../shared';

export type EntityResponseType = HttpResponse<SistemaAgua>;

@Injectable()
export class SistemaAguaService {

    private resourceUrl =  SERVER_API_URL + 'api/sistema-aguas';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(sistemaAgua: SistemaAgua): Observable<EntityResponseType> {
        const copy = this.convert(sistemaAgua);
        return this.http.post<SistemaAgua>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(sistemaAgua: SistemaAgua): Observable<EntityResponseType> {
        const copy = this.convert(sistemaAgua);
        return this.http.put<SistemaAgua>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<SistemaAgua>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<SistemaAgua[]>> {
        const options = createRequestOption(req);
        return this.http.get<SistemaAgua[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<SistemaAgua[]>) => this.convertArrayResponse(res));
    }

    queryUserNome(req: any): Observable<HttpResponse<SistemaAgua[]>> {
        const params: HttpParams = createRequestOption(req);

        const requestURL = SERVER_API_URL + 'api/sistema-aguas/nomeFiltro';

        return this.http.get<SistemaAgua[]>(requestURL, {
            params,
            observe: 'response'
        });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: SistemaAgua = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<SistemaAgua[]>): HttpResponse<SistemaAgua[]> {
        const jsonResponse: SistemaAgua[] = res.body;
        const body: SistemaAgua[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to SistemaAgua.
     */
    private convertItemFromServer(sistemaAgua: SistemaAgua): SistemaAgua {
        const copy: SistemaAgua = Object.assign({}, sistemaAgua);
        copy.dtLancamento = this.dateUtils
            .convertLocalDateFromServer(sistemaAgua.dtLancamento);
        copy.dtUltimaAlteracao = this.dateUtils
            .convertLocalDateFromServer(sistemaAgua.dtUltimaAlteracao);
        return copy;
    }

    /**
     * Convert a SistemaAgua to a JSON which can be sent to the server.
     */
    private convert(sistemaAgua: SistemaAgua): SistemaAgua {
        const copy: SistemaAgua = Object.assign({}, sistemaAgua);
        copy.dtLancamento = this.dateUtils
            .convertLocalDateToServer(sistemaAgua.dtLancamento);
        copy.dtUltimaAlteracao = this.dateUtils
            .convertLocalDateToServer(sistemaAgua.dtUltimaAlteracao);
        return copy;
    }
}
