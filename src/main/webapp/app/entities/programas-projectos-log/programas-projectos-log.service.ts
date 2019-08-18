import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {SERVER_API_URL} from '../../app.constants';

import {JhiDateUtils} from 'ng-jhipster';

import {ProgramasProjectosLog} from './programas-projectos-log.model';
import {createRequestOption} from '../../shared';

export type EntityResponseType = HttpResponse<ProgramasProjectosLog>;

@Injectable()
export class ProgramasProjectosLogService {

    private resourceUrl =  SERVER_API_URL + 'api/programas-projectos-logs';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(programasProjectosLog: ProgramasProjectosLog): Observable<EntityResponseType> {
        const copy = this.convert(programasProjectosLog);
        return this.http.post<ProgramasProjectosLog>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(programasProjectosLog: ProgramasProjectosLog): Observable<EntityResponseType> {
        const copy = this.convert(programasProjectosLog);
        return this.http.put<ProgramasProjectosLog>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ProgramasProjectosLog>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<ProgramasProjectosLog[]>> {
        const options = createRequestOption(req);
        return this.http.get<ProgramasProjectosLog[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<ProgramasProjectosLog[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    queryUserNome(req: any): Observable<HttpResponse<ProgramasProjectosLog[]>> {
        const params: HttpParams = createRequestOption(req);

        const requestURL = SERVER_API_URL + 'api/programas-projectos-logs/nomeFiltro';

        return this.http.get<ProgramasProjectosLog[]>(requestURL, {
            params,
            observe: 'response'
        });
    }


    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: ProgramasProjectosLog = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<ProgramasProjectosLog[]>): HttpResponse<ProgramasProjectosLog[]> {
        const jsonResponse: ProgramasProjectosLog[] = res.body;
        const body: ProgramasProjectosLog[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to ProgramasProjectosLog.
     */
    private convertItemFromServer(programasProjectosLog: ProgramasProjectosLog): ProgramasProjectosLog {
        const copy: ProgramasProjectosLog = Object.assign({}, programasProjectosLog);
        copy.dtLog = this.dateUtils
            .convertLocalDateFromServer(programasProjectosLog.dtLog);
        return copy;
    }

    /**
     * Convert a ProgramasProjectosLog to a JSON which can be sent to the server.
     */
    private convert(programasProjectosLog: ProgramasProjectosLog): ProgramasProjectosLog {
        const copy: ProgramasProjectosLog = Object.assign({}, programasProjectosLog);
        copy.dtLog = this.dateUtils
            .convertLocalDateToServer(programasProjectosLog.dtLog);
        return copy;
    }
}
