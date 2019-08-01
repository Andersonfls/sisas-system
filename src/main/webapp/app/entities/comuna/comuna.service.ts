import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {SERVER_API_URL} from '../../app.constants';

import {Comuna} from './comuna.model';
import {createRequestOption} from '../../shared';

export type EntityResponseType = HttpResponse<Comuna>;

@Injectable()
export class ComunaService {

    private resourceUrl =  SERVER_API_URL + 'api/comunas';

    constructor(private http: HttpClient) { }

    create(comuna: Comuna): Observable<EntityResponseType> {
        const copy = this.convert(comuna);
        return this.http.post<Comuna>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(comuna: Comuna): Observable<EntityResponseType> {
        const copy = this.convert(comuna);
        return this.http.put<Comuna>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Comuna>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Comuna[]>> {
        const options = createRequestOption(req);
        return this.http.get<Comuna[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Comuna[]>) => this.convertArrayResponse(res));
    }

    queryUserNome(req: any): Observable<HttpResponse<Comuna[]>> {
        const params: HttpParams = createRequestOption(req);

        const requestURL = SERVER_API_URL + 'api/comunas/nomeFiltro';

        return this.http.get<Comuna[]>(requestURL, {
            params,
            observe: 'response'
        });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Comuna = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Comuna[]>): HttpResponse<Comuna[]> {
        const jsonResponse: Comuna[] = res.body;
        const body: Comuna[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Comuna.
     */
    private convertItemFromServer(comuna: Comuna): Comuna {
        const copy: Comuna = Object.assign({}, comuna);
        return copy;
    }

    /**
     * Convert a Comuna to a JSON which can be sent to the server.
     */
    private convert(comuna: Comuna): Comuna {
        const copy: Comuna = Object.assign({}, comuna);
        return copy;
    }
}
