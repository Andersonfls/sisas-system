import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Contrato } from './contrato.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Contrato>;

@Injectable()
export class ContratoService {

    private resourceUrl =  SERVER_API_URL + 'api/contratoes';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(contrato: Contrato): Observable<EntityResponseType> {
        const copy = this.convert(contrato);
        return this.http.post<Contrato>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(contrato: Contrato): Observable<EntityResponseType> {
        const copy = this.convert(contrato);
        return this.http.put<Contrato>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Contrato>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    findByProgramasProjectos(id: number): Observable<EntityResponseType> {
        return this.http.get<Contrato>(`${this.resourceUrl}/programas-projectos/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Contrato[]>> {
        const options = createRequestOption(req);
        return this.http.get<Contrato[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Contrato[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Contrato = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Contrato[]>): HttpResponse<Contrato[]> {
        const jsonResponse: Contrato[] = res.body;
        const body: Contrato[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Contrato.
     */
    private convertItemFromServer(contrato: Contrato): Contrato {
        const copy: Contrato = Object.assign({}, contrato);
        copy.dtLancamento = this.dateUtils
            .convertLocalDateFromServer(contrato.dtLancamento);
        copy.dtAssinatura = this.dateUtils
            .convertLocalDateFromServer(contrato.dtAssinatura);
        copy.dtFinalizacaoProcessoHomologAprov = this.dateUtils
            .convertLocalDateFromServer(contrato.dtFinalizacaoProcessoHomologAprov);
        copy.dtAdiantamento = this.dateUtils
            .convertLocalDateFromServer(contrato.dtAdiantamento);
        copy.dtInicio = this.dateUtils
            .convertLocalDateFromServer(contrato.dtInicio);
        copy.dtRecepcaoProvisoria = this.dateUtils
            .convertLocalDateFromServer(contrato.dtRecepcaoProvisoria);
        copy.dtRecepcaoDefinitiva = this.dateUtils
            .convertLocalDateFromServer(contrato.dtRecepcaoDefinitiva);
        copy.dtRecepcaoComicionamento = this.dateUtils
            .convertLocalDateFromServer(contrato.dtRecepcaoComicionamento);
        return copy;
    }

    /**
     * Convert a Contrato to a JSON which can be sent to the server.
     */
    private convert(contrato: Contrato): Contrato {
        const copy: Contrato = Object.assign({}, contrato);
        copy.dtLancamento = this.dateUtils
            .convertLocalDateToServer(contrato.dtLancamento);
        copy.dtAssinatura = this.dateUtils
            .convertLocalDateToServer(contrato.dtAssinatura);
        copy.dtFinalizacaoProcessoHomologAprov = this.dateUtils
            .convertLocalDateToServer(contrato.dtFinalizacaoProcessoHomologAprov);
        copy.dtAdiantamento = this.dateUtils
            .convertLocalDateToServer(contrato.dtAdiantamento);
        copy.dtInicio = this.dateUtils
            .convertLocalDateToServer(contrato.dtInicio);
        copy.dtRecepcaoProvisoria = this.dateUtils
            .convertLocalDateToServer(contrato.dtRecepcaoProvisoria);
        copy.dtRecepcaoDefinitiva = this.dateUtils
            .convertLocalDateToServer(contrato.dtRecepcaoDefinitiva);
        copy.dtRecepcaoComicionamento = this.dateUtils
            .convertLocalDateToServer(contrato.dtRecepcaoComicionamento);
        return copy;
    }
}
