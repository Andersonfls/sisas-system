import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Inicio } from './inicio.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Inicio>;

@Injectable()
export class InicioService {

    private resourceUrl =  SERVER_API_URL + 'api/inicios';

    constructor(private http: HttpClient) { }

    create(inicio: Inicio): Observable<EntityResponseType> {
        const copy = this.convert(inicio);
        return this.http.post<Inicio>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(inicio: Inicio): Observable<EntityResponseType> {
        const copy = this.convert(inicio);
        return this.http.put<Inicio>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Inicio>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Inicio[]>> {
        const options = createRequestOption(req);
        return this.http.get<Inicio[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Inicio[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Inicio = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Inicio[]>): HttpResponse<Inicio[]> {
        const jsonResponse: Inicio[] = res.body;
        const body: Inicio[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Inicio.
     */
    private convertItemFromServer(inicio: Inicio): Inicio {
        const copy: Inicio = Object.assign({}, inicio);
        return copy;
    }

    /**
     * Convert a Inicio to a JSON which can be sent to the server.
     */
    private convert(inicio: Inicio): Inicio {
        const copy: Inicio = Object.assign({}, inicio);
        return copy;
    }
}
