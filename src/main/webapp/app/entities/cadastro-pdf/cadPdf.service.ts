import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { createRequestOption } from '../../shared';
import {ArquivosPortal} from './cadPdf.model';

export type EntityResponseType = HttpResponse<ArquivosPortal>;

@Injectable()
export class CadPdfService {

    // private resourceUrl =  SERVER_API_URL + 'api/banners';
     private resourceUrl =  SERVER_API_URL + 'api/arquivos-portals';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(banner: ArquivosPortal): Observable<EntityResponseType> {
        const copy = this.convert(banner);
        return this.http.post<ArquivosPortal>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(banner: ArquivosPortal): Observable<EntityResponseType> {
        const copy = this.convert(banner);
        return this.http.put<ArquivosPortal>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ArquivosPortal>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<ArquivosPortal[]>> {
        const options = createRequestOption(req);
        return this.http.get<ArquivosPortal[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<ArquivosPortal[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: ArquivosPortal = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<ArquivosPortal[]>): HttpResponse<ArquivosPortal[]> {
        const jsonResponse: ArquivosPortal[] = res.body;
        const body: ArquivosPortal[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to NoticiasPortal.
     */
    private convertItemFromServer(banner: ArquivosPortal): ArquivosPortal {
        const copy: ArquivosPortal = Object.assign({}, banner);
        return copy;
    }

    /**
     * Convert a NoticiasPortal to a JSON which can be sent to the server.
     */
    private convert(banner: ArquivosPortal): ArquivosPortal {
        const copy: ArquivosPortal = Object.assign({}, banner);
        return copy;
    }
}
