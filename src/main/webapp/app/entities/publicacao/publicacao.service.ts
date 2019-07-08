import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Publicacao } from './publicacao.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Publicacao>;

@Injectable()
export class PublicacaoService {

    private resourceUrl =  SERVER_API_URL + 'api/publicacaos';

    constructor(private http: HttpClient) { }

    create(publicacao: Publicacao): Observable<EntityResponseType> {
        const copy = this.convert(publicacao);
        return this.http.post<Publicacao>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(publicacao: Publicacao): Observable<EntityResponseType> {
        const copy = this.convert(publicacao);
        return this.http.put<Publicacao>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Publicacao>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Publicacao[]>> {
        const options = createRequestOption(req);
        return this.http.get<Publicacao[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Publicacao[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Publicacao = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Publicacao[]>): HttpResponse<Publicacao[]> {
        const jsonResponse: Publicacao[] = res.body;
        const body: Publicacao[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Publicacao.
     */
    private convertItemFromServer(publicacao: Publicacao): Publicacao {
        const copy: Publicacao = Object.assign({}, publicacao);
        return copy;
    }

    /**
     * Convert a Publicacao to a JSON which can be sent to the server.
     */
    private convert(publicacao: Publicacao): Publicacao {
        const copy: Publicacao = Object.assign({}, publicacao);
        return copy;
    }
}
