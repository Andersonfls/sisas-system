import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { NoticiasPortal } from './noticias-portal.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<NoticiasPortal>;

@Injectable()
export class NoticiasPortalService {

    private resourceUrl =  SERVER_API_URL + 'api/noticias-portals';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(noticiasPortal: NoticiasPortal): Observable<EntityResponseType> {
        const copy = this.convert(noticiasPortal);
        return this.http.post<NoticiasPortal>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(noticiasPortal: NoticiasPortal): Observable<EntityResponseType> {
        const copy = this.convert(noticiasPortal);
        return this.http.put<NoticiasPortal>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<NoticiasPortal>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<NoticiasPortal[]>> {
        const options = createRequestOption(req);
        return this.http.get<NoticiasPortal[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<NoticiasPortal[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: NoticiasPortal = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<NoticiasPortal[]>): HttpResponse<NoticiasPortal[]> {
        const jsonResponse: NoticiasPortal[] = res.body;
        const body: NoticiasPortal[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to NoticiasPortal.
     */
    private convertItemFromServer(noticiasPortal: NoticiasPortal): NoticiasPortal {
        const copy: NoticiasPortal = Object.assign({}, noticiasPortal);
        copy.dataCriacao = this.dateUtils
            .convertLocalDateFromServer(noticiasPortal.dataCriacao);
        copy.dataAlteracao = this.dateUtils
            .convertLocalDateFromServer(noticiasPortal.dataAlteracao);
        return copy;
    }

    /**
     * Convert a NoticiasPortal to a JSON which can be sent to the server.
     */
    private convert(noticiasPortal: NoticiasPortal): NoticiasPortal {
        const copy: NoticiasPortal = Object.assign({}, noticiasPortal);
        copy.dataCriacao = this.dateUtils
            .convertLocalDateToServer(noticiasPortal.dataCriacao);
        copy.dataAlteracao = this.dateUtils
            .convertLocalDateToServer(noticiasPortal.dataAlteracao);
        return copy;
    }
}
