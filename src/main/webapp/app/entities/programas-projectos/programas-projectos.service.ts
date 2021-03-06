import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {SERVER_API_URL} from '../../app.constants';

import {JhiDateUtils} from 'ng-jhipster';

import {ProgramasProjectos} from './programas-projectos.model';
import {createRequestOption} from '../../shared';
import {SistemaAgua} from '../sistema-agua';

export type EntityResponseType = HttpResponse<ProgramasProjectos>;

@Injectable()
export class ProgramasProjectosService {

    private resourceUrl =  SERVER_API_URL + 'api/programas-projectos';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(programasProjectos: ProgramasProjectos): Observable<EntityResponseType> {
        const copy = this.convert(programasProjectos);
        return this.http.post<ProgramasProjectos>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(programasProjectos: ProgramasProjectos): Observable<EntityResponseType> {
        const copy = this.convert(programasProjectos);
        return this.http.put<ProgramasProjectos>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ProgramasProjectos>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<ProgramasProjectos[]>> {
        const options = createRequestOption(req);
        return this.http.get<ProgramasProjectos[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<ProgramasProjectos[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    // menu ce busca
    queryUserNome(req: any): Observable<HttpResponse<ProgramasProjectos[]>> {
        const params: HttpParams = createRequestOption(req);

        const requestURL = this.resourceUrl + '/nomeFiltro';

        return this.http.get<ProgramasProjectos[]>(requestURL, {
            params,
            observe: 'response'
        });
    }

    queryMunicipio(req: any): Observable<HttpResponse<ProgramasProjectos[]>> {
        const params: HttpParams = createRequestOption(req);

        const requestURL = this.resourceUrl + '/municipioFiltro';

        return this.http.get<ProgramasProjectos[]>(requestURL, {
            params,
            observe: 'response'
        });
    }

    queryProvincia(req: any): Observable<HttpResponse<ProgramasProjectos[]>> {
        const params: HttpParams = createRequestOption(req);

        const requestURL = this.resourceUrl + '/provinciaFiltro';

        return this.http.get<ProgramasProjectos[]>(requestURL, {
            params,
            observe: 'response'
        });
    }

    queryComuna(req: any): Observable<HttpResponse<ProgramasProjectos[]>> {
        const params: HttpParams = createRequestOption(req);

        const requestURL = this.resourceUrl + '/comunaFiltro';

        return this.http.get<ProgramasProjectos[]>(requestURL, {
            params,
            observe: 'response'
        });
    }

    queryPeríodo(req: any): Observable<HttpResponse<ProgramasProjectos[]>> {
        const params: HttpParams = createRequestOption(req);

        const requestURL = this.resourceUrl + '/periodoFiltro';

        return this.http.get<ProgramasProjectos[]>(requestURL, {
            params,
            observe: 'response'
        });
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: ProgramasProjectos = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<ProgramasProjectos[]>): HttpResponse<ProgramasProjectos[]> {
        const jsonResponse: ProgramasProjectos[] = res.body;
        const body: ProgramasProjectos[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to ProgramasProjectos.
     */
    private convertItemFromServer(programasProjectos: ProgramasProjectos): ProgramasProjectos {
        const copy: ProgramasProjectos = Object.assign({}, programasProjectos);
        // copy.dtLancamento = this.dateUtils
        //     .convertLocalDateFromServer(programasProjectos.dtLancamento);
        copy.dtUltimaAlteracao = this.dateUtils
            .convertLocalDateFromServer(programasProjectos.dtUltimaAlteracao);
        return copy;
    }

    /**
     * Convert a ProgramasProjectos to a JSON which can be sent to the server.
     */
    private convert(programasProjectos: ProgramasProjectos): ProgramasProjectos {
        const copy: ProgramasProjectos = Object.assign({}, programasProjectos);
        // copy.dtLancamento = this.dateUtils
        //     .convertLocalDateToServer(programasProjectos.dtLancamento);
        copy.dtUltimaAlteracao = this.dateUtils
            .convertLocalDateToServer(programasProjectos.dtUltimaAlteracao);
        return copy;
    }
}
