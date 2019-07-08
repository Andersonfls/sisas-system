import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Adjudicacao } from './adjudicacao.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Adjudicacao>;

@Injectable()
export class AdjudicacaoService {

    private resourceUrl =  SERVER_API_URL + 'api/adjudicacaos';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(adjudicacao: Adjudicacao): Observable<EntityResponseType> {
        const copy = this.convert(adjudicacao);
        return this.http.post<Adjudicacao>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(adjudicacao: Adjudicacao): Observable<EntityResponseType> {
        const copy = this.convert(adjudicacao);
        return this.http.put<Adjudicacao>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Adjudicacao>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Adjudicacao[]>> {
        const options = createRequestOption(req);
        return this.http.get<Adjudicacao[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Adjudicacao[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Adjudicacao = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Adjudicacao[]>): HttpResponse<Adjudicacao[]> {
        const jsonResponse: Adjudicacao[] = res.body;
        const body: Adjudicacao[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Adjudicacao.
     */
    private convertItemFromServer(adjudicacao: Adjudicacao): Adjudicacao {
        const copy: Adjudicacao = Object.assign({}, adjudicacao);
        copy.dtLancamento = this.dateUtils
            .convertLocalDateFromServer(adjudicacao.dtLancamento);
        copy.dtComunicaoAdjudicacao = this.dateUtils
            .convertLocalDateFromServer(adjudicacao.dtComunicaoAdjudicacao);
        copy.dtPrestacaoGarantBoaExec = this.dateUtils
            .convertLocalDateFromServer(adjudicacao.dtPrestacaoGarantBoaExec);
        copy.dtSubmissaoMinutContrato = this.dateUtils
            .convertLocalDateFromServer(adjudicacao.dtSubmissaoMinutContrato);
        return copy;
    }

    /**
     * Convert a Adjudicacao to a JSON which can be sent to the server.
     */
    private convert(adjudicacao: Adjudicacao): Adjudicacao {
        const copy: Adjudicacao = Object.assign({}, adjudicacao);
        copy.dtLancamento = this.dateUtils
            .convertLocalDateToServer(adjudicacao.dtLancamento);
        copy.dtComunicaoAdjudicacao = this.dateUtils
            .convertLocalDateToServer(adjudicacao.dtComunicaoAdjudicacao);
        copy.dtPrestacaoGarantBoaExec = this.dateUtils
            .convertLocalDateToServer(adjudicacao.dtPrestacaoGarantBoaExec);
        copy.dtSubmissaoMinutContrato = this.dateUtils
            .convertLocalDateToServer(adjudicacao.dtSubmissaoMinutContrato);
        return copy;
    }
}
