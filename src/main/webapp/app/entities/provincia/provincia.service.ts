import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {SERVER_API_URL} from '../../app.constants';
import {Provincia} from './provincia.model';
import {createRequestOption} from '../../shared';
export type EntityResponseType = HttpResponse<Provincia>;

@Injectable()
export class ProvinciaService {

    private resourceUrl = SERVER_API_URL + 'api/provincias';

    constructor(private http: HttpClient) {
    }

    create(provincia: Provincia): Observable<EntityResponseType> {
        const copy = this.convert(provincia);
        return this.http.post<Provincia>(this.resourceUrl, copy, {observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(provincia: Provincia): Observable<EntityResponseType> {
        const copy = this.convert(provincia);
        return this.http.put<Provincia>(this.resourceUrl, copy, {observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Provincia>(`${this.resourceUrl}/${id}`, {observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Provincia[]>> {
        const options = createRequestOption(req);
        return this.http.get<Provincia[]>(this.resourceUrl, {params: options, observe: 'response'})
            .map((res: HttpResponse<Provincia[]>) => this.convertArrayResponse(res));
    }

    queryPorNivelUsuario(req?: any): Observable<HttpResponse<Provincia[]>> {
        const options = createRequestOption(req);
        return this.http.get<Provincia[]>(this.resourceUrl + '/nivel-usuario', {params: options, observe: 'response'})
            .map((res: HttpResponse<Provincia[]>) => this.convertArrayResponse(res));
    }

    buscaProvinciaDoUsuario(req?: any): Observable<HttpResponse<Provincia[]>> {
        const options = createRequestOption(req);
        return this.http.get<Provincia[]>(this.resourceUrl + '/provincia-usuario', {params: options, observe: 'response'});
    }

    queryUserNome(req: any): Observable<HttpResponse<Provincia[]>> {
        const params: HttpParams = createRequestOption(req);

        const requestURL = SERVER_API_URL + 'api/provincias/nomeFiltro';

        return this.http.get<Provincia[]>(requestURL, {
            params,
            observe: 'response'
        });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Provincia = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Provincia[]>): HttpResponse<Provincia[]> {
        const jsonResponse: Provincia[] = res.body;
        const body: Provincia[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Provincia.
     */
    private convertItemFromServer(provincia: Provincia): Provincia {
        const copy: Provincia = Object.assign({}, provincia);
        return copy;
    }

    /**
     * Convert a Provincia to a JSON which can be sent to the server.
     */
    private convert(provincia: Provincia): Provincia {
        const copy: Provincia = Object.assign({}, provincia);
        return copy;
    }
}
