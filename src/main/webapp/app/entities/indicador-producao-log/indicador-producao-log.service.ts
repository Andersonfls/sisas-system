import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { IndicadorProducaoLog } from './indicador-producao-log.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<IndicadorProducaoLog>;

@Injectable()
export class IndicadorProducaoLogService {

    private resourceUrl =  SERVER_API_URL + 'api/indicador-producao-logs';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(indicadorProducaoLog: IndicadorProducaoLog): Observable<EntityResponseType> {
        const copy = this.convert(indicadorProducaoLog);
        return this.http.post<IndicadorProducaoLog>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(indicadorProducaoLog: IndicadorProducaoLog): Observable<EntityResponseType> {
        const copy = this.convert(indicadorProducaoLog);
        return this.http.put<IndicadorProducaoLog>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IndicadorProducaoLog>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<IndicadorProducaoLog[]>> {
        const options = createRequestOption(req);
        return this.http.get<IndicadorProducaoLog[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<IndicadorProducaoLog[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: IndicadorProducaoLog = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<IndicadorProducaoLog[]>): HttpResponse<IndicadorProducaoLog[]> {
        const jsonResponse: IndicadorProducaoLog[] = res.body;
        const body: IndicadorProducaoLog[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to IndicadorProducaoLog.
     */
    private convertItemFromServer(indicadorProducaoLog: IndicadorProducaoLog): IndicadorProducaoLog {
        const copy: IndicadorProducaoLog = Object.assign({}, indicadorProducaoLog);
        copy.dtLog = this.dateUtils
            .convertLocalDateFromServer(indicadorProducaoLog.dtLog);
        return copy;
    }

    /**
     * Convert a IndicadorProducaoLog to a JSON which can be sent to the server.
     */
    private convert(indicadorProducaoLog: IndicadorProducaoLog): IndicadorProducaoLog {
        const copy: IndicadorProducaoLog = Object.assign({}, indicadorProducaoLog);
        copy.dtLog = this.dateUtils
            .convertLocalDateToServer(indicadorProducaoLog.dtLog);
        return copy;
    }
}
