import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Situacao } from './situacao.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Situacao>;

@Injectable()
export class SituacaoService {

    private resourceUrl =  SERVER_API_URL + 'api/situacaos';

    constructor(private http: HttpClient) { }

    create(situacao: Situacao): Observable<EntityResponseType> {
        const copy = this.convert(situacao);
        return this.http.post<Situacao>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(situacao: Situacao): Observable<EntityResponseType> {
        const copy = this.convert(situacao);
        return this.http.put<Situacao>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Situacao>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Situacao[]>> {
        const options = createRequestOption(req);
        return this.http.get<Situacao[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Situacao[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Situacao = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Situacao[]>): HttpResponse<Situacao[]> {
        const jsonResponse: Situacao[] = res.body;
        const body: Situacao[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Situacao.
     */
    private convertItemFromServer(situacao: Situacao): Situacao {
        const copy: Situacao = Object.assign({}, situacao);
        return copy;
    }

    /**
     * Convert a Situacao to a JSON which can be sent to the server.
     */
    private convert(situacao: Situacao): Situacao {
        const copy: Situacao = Object.assign({}, situacao);
        return copy;
    }
}
