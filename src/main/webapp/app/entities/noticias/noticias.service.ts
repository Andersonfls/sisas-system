import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Noticias } from './noticias.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Noticias>;

@Injectable()
export class NoticiasService {

    private resourceUrl =  SERVER_API_URL + 'api/noticias';

    constructor(private http: HttpClient) { }

    create(noticias: Noticias): Observable<EntityResponseType> {
        const copy = this.convert(noticias);
        return this.http.post<Noticias>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(noticias: Noticias): Observable<EntityResponseType> {
        const copy = this.convert(noticias);
        return this.http.put<Noticias>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Noticias>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Noticias[]>> {
        const options = createRequestOption(req);
        return this.http.get<Noticias[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Noticias[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Noticias = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Noticias[]>): HttpResponse<Noticias[]> {
        const jsonResponse: Noticias[] = res.body;
        const body: Noticias[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Noticias.
     */
    private convertItemFromServer(noticias: Noticias): Noticias {
        const copy: Noticias = Object.assign({}, noticias);
        return copy;
    }

    /**
     * Convert a Noticias to a JSON which can be sent to the server.
     */
    private convert(noticias: Noticias): Noticias {
        const copy: Noticias = Object.assign({}, noticias);
        return copy;
    }
}
