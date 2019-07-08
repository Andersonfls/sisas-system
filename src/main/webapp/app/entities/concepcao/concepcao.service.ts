import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Concepcao } from './concepcao.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Concepcao>;

@Injectable()
export class ConcepcaoService {

    private resourceUrl =  SERVER_API_URL + 'api/concepcaos';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(concepcao: Concepcao): Observable<EntityResponseType> {
        const copy = this.convert(concepcao);
        return this.http.post<Concepcao>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(concepcao: Concepcao): Observable<EntityResponseType> {
        const copy = this.convert(concepcao);
        return this.http.put<Concepcao>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Concepcao>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Concepcao[]>> {
        const options = createRequestOption(req);
        return this.http.get<Concepcao[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Concepcao[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Concepcao = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Concepcao[]>): HttpResponse<Concepcao[]> {
        const jsonResponse: Concepcao[] = res.body;
        const body: Concepcao[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Concepcao.
     */
    private convertItemFromServer(concepcao: Concepcao): Concepcao {
        const copy: Concepcao = Object.assign({}, concepcao);
        copy.dtLancamento = this.dateUtils
            .convertLocalDateFromServer(concepcao.dtLancamento);
        copy.dtUltimaAlteracao = this.dateUtils
            .convertLocalDateFromServer(concepcao.dtUltimaAlteracao);
        copy.dtElaboracaoCon = this.dateUtils
            .convertLocalDateFromServer(concepcao.dtElaboracaoCon);
        copy.dtAprovacaoCon = this.dateUtils
            .convertLocalDateFromServer(concepcao.dtAprovacaoCon);
        return copy;
    }

    /**
     * Convert a Concepcao to a JSON which can be sent to the server.
     */
    private convert(concepcao: Concepcao): Concepcao {
        const copy: Concepcao = Object.assign({}, concepcao);
        copy.dtLancamento = this.dateUtils
            .convertLocalDateToServer(concepcao.dtLancamento);
        copy.dtUltimaAlteracao = this.dateUtils
            .convertLocalDateToServer(concepcao.dtUltimaAlteracao);
        copy.dtElaboracaoCon = this.dateUtils
            .convertLocalDateToServer(concepcao.dtElaboracaoCon);
        copy.dtAprovacaoCon = this.dateUtils
            .convertLocalDateToServer(concepcao.dtAprovacaoCon);
        return copy;
    }
}
