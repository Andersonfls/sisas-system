import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { SistemaAguaLog } from './sistema-agua-log.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<SistemaAguaLog>;

@Injectable()
export class SistemaAguaLogService {

    private resourceUrl =  SERVER_API_URL + 'api/sistema-agua-logs';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(sistemaAguaLog: SistemaAguaLog): Observable<EntityResponseType> {
        const copy = this.convert(sistemaAguaLog);
        return this.http.post<SistemaAguaLog>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(sistemaAguaLog: SistemaAguaLog): Observable<EntityResponseType> {
        const copy = this.convert(sistemaAguaLog);
        return this.http.put<SistemaAguaLog>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<SistemaAguaLog>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<SistemaAguaLog[]>> {
        const options = createRequestOption(req);
        return this.http.get<SistemaAguaLog[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<SistemaAguaLog[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: SistemaAguaLog = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<SistemaAguaLog[]>): HttpResponse<SistemaAguaLog[]> {
        const jsonResponse: SistemaAguaLog[] = res.body;
        const body: SistemaAguaLog[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to SistemaAguaLog.
     */
    private convertItemFromServer(sistemaAguaLog: SistemaAguaLog): SistemaAguaLog {
        const copy: SistemaAguaLog = Object.assign({}, sistemaAguaLog);
        copy.dtLog = this.dateUtils
            .convertLocalDateFromServer(sistemaAguaLog.dtLog);
        return copy;
    }

    /**
     * Convert a SistemaAguaLog to a JSON which can be sent to the server.
     */
    private convert(sistemaAguaLog: SistemaAguaLog): SistemaAguaLog {
        const copy: SistemaAguaLog = Object.assign({}, sistemaAguaLog);
        copy.dtLog = this.dateUtils
            .convertLocalDateToServer(sistemaAguaLog.dtLog);
        return copy;
    }
}
