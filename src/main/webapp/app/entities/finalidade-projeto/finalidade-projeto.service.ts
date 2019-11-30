import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {SERVER_API_URL} from '../../app.constants';
import {FinalidadeProjeto} from './finalidade-projeto.model';
import {createRequestOption} from '../../shared';

export type EntityResponseType = HttpResponse<FinalidadeProjeto>;

@Injectable()
export class FinalidadeProjetoService {

    private resourceUrl =  SERVER_API_URL + 'api/finalidade-projetos';

    constructor(private http: HttpClient) { }

    create(finalidadeProjeto: FinalidadeProjeto): Observable<EntityResponseType> {
        const copy = this.convert(finalidadeProjeto);
        return this.http.post<FinalidadeProjeto>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(finalidadeProjeto: FinalidadeProjeto): Observable<EntityResponseType> {
        const copy = this.convert(finalidadeProjeto);
        return this.http.put<FinalidadeProjeto>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<FinalidadeProjeto>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<FinalidadeProjeto[]>> {
        const options = createRequestOption(req);
        return this.http.get<FinalidadeProjeto[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<FinalidadeProjeto[]>) => this.convertArrayResponse(res));
    }

    queryUserNome(req: any): Observable<HttpResponse<FinalidadeProjeto[]>> {
        const params: HttpParams = createRequestOption(req);

        const requestURL = SERVER_API_URL + 'api/finalidade-projetos/nomeFiltro';

        return this.http.get<FinalidadeProjeto[]>(requestURL, {
            params,
            observe: 'response'
        });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: FinalidadeProjeto = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<FinalidadeProjeto[]>): HttpResponse<FinalidadeProjeto[]> {
        const jsonResponse: FinalidadeProjeto[] = res.body;
        const body: FinalidadeProjeto[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to FinalidadeProjeto.
     */
    private convertItemFromServer(finalidadeProjeto: FinalidadeProjeto): FinalidadeProjeto {
        const copy: FinalidadeProjeto = Object.assign({}, finalidadeProjeto);
        return copy;
    }

    /**
     * Convert a FinalidadeProjeto to a JSON which can be sent to the server.
     */
    private convert(finalidadeProjeto: FinalidadeProjeto): FinalidadeProjeto {
        const copy: FinalidadeProjeto = Object.assign({}, finalidadeProjeto);
        return copy;
    }
}
