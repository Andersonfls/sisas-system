import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { EntidadeGestora } from './entidade-gestora.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<EntidadeGestora>;

@Injectable()
export class EntidadeGestoraService {

    private resourceUrl =  SERVER_API_URL + 'api/entidade-gestoras';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(entidadeGestora: EntidadeGestora): Observable<EntityResponseType> {
        const copy = this.convert(entidadeGestora);
        return this.http.post<EntidadeGestora>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(entidadeGestora: EntidadeGestora): Observable<EntityResponseType> {
        const copy = this.convert(entidadeGestora);
        return this.http.put<EntidadeGestora>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<EntidadeGestora>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<EntidadeGestora[]>> {
        const options = createRequestOption(req);
        return this.http.get<EntidadeGestora[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<EntidadeGestora[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: EntidadeGestora = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<EntidadeGestora[]>): HttpResponse<EntidadeGestora[]> {
        const jsonResponse: EntidadeGestora[] = res.body;
        const body: EntidadeGestora[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to EntidadeGestora.
     */
    private convertItemFromServer(entidadeGestora: EntidadeGestora): EntidadeGestora {
        const copy: EntidadeGestora = Object.assign({}, entidadeGestora);
        copy.dtConstituicao = this.dateUtils
            .convertLocalDateFromServer(entidadeGestora.dtConstituicao);
        return copy;
    }

    /**
     * Convert a EntidadeGestora to a JSON which can be sent to the server.
     */
    private convert(entidadeGestora: EntidadeGestora): EntidadeGestora {
        const copy: EntidadeGestora = Object.assign({}, entidadeGestora);
        copy.dtConstituicao = this.dateUtils
            .convertLocalDateToServer(entidadeGestora.dtConstituicao);
        return copy;
    }
}
