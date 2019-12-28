import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { createRequestOption } from '../../shared';
import {Banner} from './cadPdf.model';

export type EntityResponseType = HttpResponse<Banner>;

@Injectable()
export class CadPdfService {

    private resourceUrl =  SERVER_API_URL + 'api/banners';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(banner: Banner): Observable<EntityResponseType> {
        const copy = this.convert(banner);
        return this.http.post<Banner>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(banner: Banner): Observable<EntityResponseType> {
        const copy = this.convert(banner);
        return this.http.put<Banner>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Banner>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Banner[]>> {
        const options = createRequestOption(req);
        return this.http.get<Banner[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Banner[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Banner = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Banner[]>): HttpResponse<Banner[]> {
        const jsonResponse: Banner[] = res.body;
        const body: Banner[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to NoticiasPortal.
     */
    private convertItemFromServer(banner: Banner): Banner {
        const copy: Banner = Object.assign({}, banner);
        return copy;
    }

    /**
     * Convert a NoticiasPortal to a JSON which can be sent to the server.
     */
    private convert(banner: Banner): Banner {
        const copy: Banner = Object.assign({}, banner);
        return copy;
    }
}
