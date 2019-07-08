import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { PublicacaoLog } from './publicacao-log.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<PublicacaoLog>;

@Injectable()
export class PublicacaoLogService {

    private resourceUrl =  SERVER_API_URL + 'api/publicacao-logs';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(publicacaoLog: PublicacaoLog): Observable<EntityResponseType> {
        const copy = this.convert(publicacaoLog);
        return this.http.post<PublicacaoLog>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(publicacaoLog: PublicacaoLog): Observable<EntityResponseType> {
        const copy = this.convert(publicacaoLog);
        return this.http.put<PublicacaoLog>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<PublicacaoLog>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<PublicacaoLog[]>> {
        const options = createRequestOption(req);
        return this.http.get<PublicacaoLog[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<PublicacaoLog[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: PublicacaoLog = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<PublicacaoLog[]>): HttpResponse<PublicacaoLog[]> {
        const jsonResponse: PublicacaoLog[] = res.body;
        const body: PublicacaoLog[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to PublicacaoLog.
     */
    private convertItemFromServer(publicacaoLog: PublicacaoLog): PublicacaoLog {
        const copy: PublicacaoLog = Object.assign({}, publicacaoLog);
        copy.dtLog = this.dateUtils
            .convertLocalDateFromServer(publicacaoLog.dtLog);
        return copy;
    }

    /**
     * Convert a PublicacaoLog to a JSON which can be sent to the server.
     */
    private convert(publicacaoLog: PublicacaoLog): PublicacaoLog {
        const copy: PublicacaoLog = Object.assign({}, publicacaoLog);
        copy.dtLog = this.dateUtils
            .convertLocalDateToServer(publicacaoLog.dtLog);
        return copy;
    }
}
