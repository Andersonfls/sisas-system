import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Fases } from './fases.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Fases>;

@Injectable()
export class FasesService {

    private resourceUrl =  SERVER_API_URL + 'api/fases';

    constructor(private http: HttpClient) { }

    create(fases: Fases): Observable<EntityResponseType> {
        const copy = this.convert(fases);
        return this.http.post<Fases>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(fases: Fases): Observable<EntityResponseType> {
        const copy = this.convert(fases);
        return this.http.put<Fases>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Fases>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Fases[]>> {
        const options = createRequestOption(req);
        return this.http.get<Fases[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Fases[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Fases = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Fases[]>): HttpResponse<Fases[]> {
        const jsonResponse: Fases[] = res.body;
        const body: Fases[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Fases.
     */
    private convertItemFromServer(fases: Fases): Fases {
        const copy: Fases = Object.assign({}, fases);
        return copy;
    }

    /**
     * Convert a Fases to a JSON which can be sent to the server.
     */
    private convert(fases: Fases): Fases {
        const copy: Fases = Object.assign({}, fases);
        return copy;
    }
}
