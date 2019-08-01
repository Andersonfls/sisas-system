import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {SERVER_API_URL} from '../../app.constants';

import {Fornecedor} from './fornecedor.model';
import {createRequestOption} from '../../shared';

export type EntityResponseType = HttpResponse<Fornecedor>;

@Injectable()
export class FornecedorService {

    private resourceUrl = SERVER_API_URL + 'api/fornecedors';

    constructor(private http: HttpClient) {
    }

    create(fornecedor: Fornecedor): Observable<EntityResponseType> {
        const copy = this.convert(fornecedor);
        return this.http.post<Fornecedor>(this.resourceUrl, copy, {observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(fornecedor: Fornecedor): Observable<EntityResponseType> {
        const copy = this.convert(fornecedor);
        return this.http.put<Fornecedor>(this.resourceUrl, copy, {observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Fornecedor>(`${this.resourceUrl}/${id}`, {observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Fornecedor[]>> {
        const options = createRequestOption(req);
        return this.http.get<Fornecedor[]>(this.resourceUrl, {params: options, observe: 'response'})
            .map((res: HttpResponse<Fornecedor[]>) => this.convertArrayResponse(res));
    }

    queryUserNome(req: any): Observable<HttpResponse<Fornecedor[]>> {
        const params: HttpParams = createRequestOption(req);

        const requestURL = SERVER_API_URL + 'api/fornecedors/nomeFiltro';

        return this.http.get<Fornecedor[]>(requestURL, {
            params,
            observe: 'response'
        });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Fornecedor = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Fornecedor[]>): HttpResponse<Fornecedor[]> {
        const jsonResponse: Fornecedor[] = res.body;
        const body: Fornecedor[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Fornecedor.
     */
    private convertItemFromServer(fornecedor: Fornecedor): Fornecedor {
        const copy: Fornecedor = Object.assign({}, fornecedor);
        return copy;
    }

    /**
     * Convert a Fornecedor to a JSON which can be sent to the server.
     */
    private convert(fornecedor: Fornecedor): Fornecedor {
        const copy: Fornecedor = Object.assign({}, fornecedor);
        return copy;
    }
}
