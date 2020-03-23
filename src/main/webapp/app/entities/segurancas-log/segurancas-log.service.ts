import { Injectable } from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { createRequestOption } from '../../shared';
import {SegurancasLog} from './segurancas-log.model';

export type EntityResponseType = HttpResponse<SegurancasLog>;

@Injectable()
export class SegurancasLogService {

    private resourceUrl =  SERVER_API_URL + 'api/segurancas-logs';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(relatoriosLog: SegurancasLog): Observable<EntityResponseType> {
        const copy = this.convert(relatoriosLog);
        return this.http.post<SegurancasLog>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(relatoriosLog: SegurancasLog): Observable<EntityResponseType> {
        const copy = this.convert(relatoriosLog);
        return this.http.put<SegurancasLog>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<SegurancasLog>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<SegurancasLog[]>> {
        const options = createRequestOption(req);
        return this.http.get<SegurancasLog[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<SegurancasLog[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    queryUserNome(req: any): Observable<HttpResponse<SegurancasLog[]>> {
        const params: HttpParams = createRequestOption(req);

        const requestURL = SERVER_API_URL + 'api/segurancas-logs/nomeFiltro';

        return this.http.get<SegurancasLog[]>(requestURL, {
            params,
            observe: 'response'
        });
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: SegurancasLog = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<SegurancasLog[]>): HttpResponse<SegurancasLog[]> {
        const jsonResponse: SegurancasLog[] = res.body;
        const body: SegurancasLog[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to RelatoriosLog.
     */
    private convertItemFromServer(relatoriosLog: SegurancasLog): SegurancasLog {
        const copy: SegurancasLog = Object.assign({}, relatoriosLog);
        copy.dtLog = this.dateUtils
            .convertLocalDateFromServer(relatoriosLog.dtLog);
        return copy;
    }

    /**
     * Convert a RelatoriosLog to a JSON which can be sent to the server.
     */
    private convert(relatoriosLog: SegurancasLog): SegurancasLog {
        const copy: SegurancasLog = Object.assign({}, relatoriosLog);
        copy.dtLog = this.dateUtils
            .convertLocalDateToServer(relatoriosLog.dtLog);
        return copy;
    }
}
