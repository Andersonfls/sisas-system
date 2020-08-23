import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Execucao } from './execucao.model';
import { createRequestOption } from '../../shared';
import {Adjudicacao} from '../adjudicacao';

export type EntityResponseType = HttpResponse<Execucao>;

@Injectable()
export class ExecucaoService {

    private resourceUrl =  SERVER_API_URL + 'api/execucaos';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(execucao: Execucao): Observable<EntityResponseType> {
        const copy = this.convert(execucao);
        return this.http.post<Execucao>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(execucao: Execucao): Observable<EntityResponseType> {
        const copy = this.convert(execucao);
        return this.http.put<Execucao>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Execucao>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    findByProgramasProjectos(id: number): Observable<EntityResponseType> {
        return this.http.get<Execucao>(`${this.resourceUrl}/programas-projectos/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Execucao[]>> {
        const options = createRequestOption(req);
        return this.http.get<Execucao[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Execucao[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Execucao = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Execucao[]>): HttpResponse<Execucao[]> {
        const jsonResponse: Execucao[] = res.body;
        const body: Execucao[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Execucao.
     */
    private convertItemFromServer(execucao: Execucao): Execucao {
        const copy: Execucao = Object.assign({}, execucao);
        // copy.dtLancamento = this.dateUtils
        //     .convertLocalDateFromServer(execucao.dtLancamento);
        copy.dtPeridoReferencia = this.dateUtils
            .convertLocalDateFromServer(execucao.dtPeridoReferencia);
        copy.dtFimReferencia = this.dateUtils
            .convertLocalDateFromServer(execucao.dtFimReferencia);
        copy.dtFactura = this.dateUtils
            .convertLocalDateFromServer(execucao.dtFactura);
        return copy;
    }

    /**
     * Convert a Execucao to a JSON which can be sent to the server.
     */
    private convert(execucao: Execucao): Execucao {
        const copy: Execucao = Object.assign({}, execucao);
        // copy.dtLancamento = this.dateUtils
        //     .convertLocalDateToServer(execucao.dtLancamento);
        copy.dtPeridoReferencia = this.dateUtils
            .convertLocalDateToServer(execucao.dtPeridoReferencia);
        copy.dtFimReferencia = this.dateUtils
            .convertLocalDateToServer(execucao.dtFimReferencia);
        copy.dtFactura = this.dateUtils
            .convertLocalDateToServer(execucao.dtFactura);
        return copy;
    }
}
