import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {SERVER_API_URL} from '../../app.constants';

import {Municipio} from './municipio.model';
import {createRequestOption} from '../../shared';

export type EntityResponseType = HttpResponse<Municipio>;

@Injectable()
export class MunicipioService {

    private resourceUrl =  SERVER_API_URL + 'api/municipios';

    constructor(private http: HttpClient) { }

    create(municipio: Municipio): Observable<EntityResponseType> {
        const copy = this.convert(municipio);
        return this.http.post<Municipio>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(municipio: Municipio): Observable<EntityResponseType> {
        const copy = this.convert(municipio);
        return this.http.put<Municipio>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Municipio>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Municipio[]>> {
        const options = createRequestOption(req);
        return this.http.get<Municipio[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Municipio[]>) => this.convertArrayResponse(res));
    }

    queryUserNome(req: any): Observable<HttpResponse<Municipio[]>> {
        const params: HttpParams = createRequestOption(req);

        const requestURL = SERVER_API_URL + 'api/municipios/nomeFiltro';

        return this.http.get<Municipio[]>(requestURL, {
            params,
            observe: 'response'
        });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Municipio = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Municipio[]>): HttpResponse<Municipio[]> {
        const jsonResponse: Municipio[] = res.body;
        const body: Municipio[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Municipio.
     */
    private convertItemFromServer(municipio: Municipio): Municipio {
        const copy: Municipio = Object.assign({}, municipio);
        return copy;
    }

    /**
     * Convert a Municipio to a JSON which can be sent to the server.
     */
    private convert(municipio: Municipio): Municipio {
        const copy: Municipio = Object.assign({}, municipio);
        return copy;
    }
}
