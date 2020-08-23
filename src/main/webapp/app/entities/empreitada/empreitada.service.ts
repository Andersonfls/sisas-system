import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Empreitada } from './empreitada.model';
import { createRequestOption } from '../../shared';
import {Adjudicacao} from '../adjudicacao';

export type EntityResponseType = HttpResponse<Empreitada>;

@Injectable()
export class EmpreitadaService {

    private resourceUrl =  SERVER_API_URL + 'api/empreitadas';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(empreitada: Empreitada): Observable<EntityResponseType> {
        const copy = this.convert(empreitada);
        return this.http.post<Empreitada>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(empreitada: Empreitada): Observable<EntityResponseType> {
        const copy = this.convert(empreitada);
        return this.http.put<Empreitada>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Empreitada>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    findByProgramasProjectos(id: number): Observable<EntityResponseType> {
        return this.http.get<Empreitada>(`${this.resourceUrl}/programas-projectos/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Empreitada[]>> {
        const options = createRequestOption(req);
        return this.http.get<Empreitada[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Empreitada[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Empreitada = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Empreitada[]>): HttpResponse<Empreitada[]> {
        const jsonResponse: Empreitada[] = res.body;
        const body: Empreitada[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Empreitada.
     */
    private convertItemFromServer(empreitada: Empreitada): Empreitada {
        const copy: Empreitada = Object.assign({}, empreitada);
        // copy.dtLancamento = this.dateUtils
        //     .convertLocalDateFromServer(empreitada.dtLancamento);
        return copy;
    }

    /**
     * Convert a Empreitada to a JSON which can be sent to the server.
     */
    private convert(empreitada: Empreitada): Empreitada {
        const copy: Empreitada = Object.assign({}, empreitada);
        // copy.dtLancamento = this.dateUtils
        //     .convertLocalDateToServer(empreitada.dtLancamento);
        return copy;
    }
}
