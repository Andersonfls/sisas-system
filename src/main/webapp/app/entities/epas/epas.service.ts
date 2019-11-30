import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {SERVER_API_URL} from '../../app.constants';

import {Epas} from './epas.model';
import {createRequestOption} from '../../shared';

export type EntityResponseType = HttpResponse<Epas>;

@Injectable()
export class EpasService {

    private resourceUrl =  SERVER_API_URL + 'api/epas';

    constructor(private http: HttpClient) { }

    create(epas: Epas): Observable<EntityResponseType> {
        const copy = this.convert(epas);
        return this.http.post<Epas>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(epas: Epas): Observable<EntityResponseType> {
        const copy = this.convert(epas);
        return this.http.put<Epas>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Epas>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Epas[]>> {
        const options = createRequestOption(req);
        return this.http.get<Epas[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Epas[]>) => this.convertArrayResponse(res));
    }

    queryUserNome(req: any): Observable<HttpResponse<Epas[]>> {
        const params: HttpParams = createRequestOption(req);

        const requestURL = SERVER_API_URL + 'api/epas/nomeFiltro';

        return this.http.get<Epas[]>(requestURL, {
            params,
            observe: 'response'
        });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Epas = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Epas[]>): HttpResponse<Epas[]> {
        const jsonResponse: Epas[] = res.body;
        const body: Epas[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Epas.
     */
    private convertItemFromServer(epas: Epas): Epas {
        const copy: Epas = Object.assign({}, epas);
        return copy;
    }

    /**
     * Convert a Epas to a JSON which can be sent to the server.
     */
    private convert(epas: Epas): Epas {
        const copy: Epas = Object.assign({}, epas);
        return copy;
    }
}
