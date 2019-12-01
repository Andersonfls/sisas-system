import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {SERVER_API_URL} from '../../app.constants';

import {Especialidades} from './especialidades.model';
import {createRequestOption} from '../../shared';

export type EntityResponseType = HttpResponse<Especialidades>;

@Injectable()
export class EspecialidadesService {

    private resourceUrl =  SERVER_API_URL + 'api/especialidades';

    constructor(private http: HttpClient) { }

    create(especialidades: Especialidades): Observable<EntityResponseType> {
        const copy = this.convert(especialidades);
        return this.http.post<Especialidades>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(especialidades: Especialidades): Observable<EntityResponseType> {
        const copy = this.convert(especialidades);
        return this.http.put<Especialidades>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Especialidades>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Especialidades[]>> {
        const options = createRequestOption(req);
        return this.http.get<Especialidades[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Especialidades[]>) => this.convertArrayResponse(res));
    }

    queryUserNome(req: any): Observable<HttpResponse<Especialidades[]>> {
        const params: HttpParams = createRequestOption(req);

        const requestURL = SERVER_API_URL + 'api/especialidades/nomeFiltro';

        return this.http.get<Especialidades[]>(requestURL, {
            params,
            observe: 'response'
        });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Especialidades = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Especialidades[]>): HttpResponse<Especialidades[]> {
        const jsonResponse: Especialidades[] = res.body;
        const body: Especialidades[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Especialidades.
     */
    private convertItemFromServer(especialidades: Especialidades): Especialidades {
        const copy: Especialidades = Object.assign({}, especialidades);
        return copy;
    }

    /**
     * Convert a Especialidades to a JSON which can be sent to the server.
     */
    private convert(especialidades: Especialidades): Especialidades {
        const copy: Especialidades = Object.assign({}, especialidades);
        return copy;
    }
}
