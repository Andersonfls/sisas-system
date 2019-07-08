import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { OrigemFinanciamento } from './origem-financiamento.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<OrigemFinanciamento>;

@Injectable()
export class OrigemFinanciamentoService {

    private resourceUrl =  SERVER_API_URL + 'api/origem-financiamentos';

    constructor(private http: HttpClient) { }

    create(origemFinanciamento: OrigemFinanciamento): Observable<EntityResponseType> {
        const copy = this.convert(origemFinanciamento);
        return this.http.post<OrigemFinanciamento>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(origemFinanciamento: OrigemFinanciamento): Observable<EntityResponseType> {
        const copy = this.convert(origemFinanciamento);
        return this.http.put<OrigemFinanciamento>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<OrigemFinanciamento>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<OrigemFinanciamento[]>> {
        const options = createRequestOption(req);
        return this.http.get<OrigemFinanciamento[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrigemFinanciamento[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: OrigemFinanciamento = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<OrigemFinanciamento[]>): HttpResponse<OrigemFinanciamento[]> {
        const jsonResponse: OrigemFinanciamento[] = res.body;
        const body: OrigemFinanciamento[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to OrigemFinanciamento.
     */
    private convertItemFromServer(origemFinanciamento: OrigemFinanciamento): OrigemFinanciamento {
        const copy: OrigemFinanciamento = Object.assign({}, origemFinanciamento);
        return copy;
    }

    /**
     * Convert a OrigemFinanciamento to a JSON which can be sent to the server.
     */
    private convert(origemFinanciamento: OrigemFinanciamento): OrigemFinanciamento {
        const copy: OrigemFinanciamento = Object.assign({}, origemFinanciamento);
        return copy;
    }
}
