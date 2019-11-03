import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Concurso } from './concurso.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Concurso>;

@Injectable()
export class ConcursoService {

    private resourceUrl =  SERVER_API_URL + 'api/concursos';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(concurso: Concurso): Observable<EntityResponseType> {
        const copy = this.convert(concurso);
        return this.http.post<Concurso>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(concurso: Concurso): Observable<EntityResponseType> {
        const copy = this.convert(concurso);
        return this.http.put<Concurso>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Concurso>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    findByProgramasProjectos(id: number): Observable<EntityResponseType> {
        return this.http.get<Concurso>(`${this.resourceUrl}/programas-projectos/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Concurso[]>> {
        const options = createRequestOption(req);
        return this.http.get<Concurso[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Concurso[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Concurso = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Concurso[]>): HttpResponse<Concurso[]> {
        const jsonResponse: Concurso[] = res.body;
        const body: Concurso[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Concurso.
     */
    private convertItemFromServer(concurso: Concurso): Concurso {
        const copy: Concurso = Object.assign({}, concurso);
        copy.dtLancamento = this.dateUtils
            .convertLocalDateFromServer(concurso.dtLancamento);
        copy.dtUltimaAlteracao = this.dateUtils
            .convertLocalDateFromServer(concurso.dtUltimaAlteracao);
        copy.dtEntregaProposta = this.dateUtils
            .convertLocalDateFromServer(concurso.dtEntregaProposta);
        copy.dtAberturaProposta = this.dateUtils
            .convertLocalDateFromServer(concurso.dtAberturaProposta);
        copy.dtConclusaoAvaliacaoRelPrel = this.dateUtils
            .convertLocalDateFromServer(concurso.dtConclusaoAvaliacaoRelPrel);
        copy.dtNegociacao = this.dateUtils
            .convertLocalDateFromServer(concurso.dtNegociacao);
        copy.dtAprovRelAvalFinal = this.dateUtils
            .convertLocalDateFromServer(concurso.dtAprovRelAvalFinal);
        return copy;
    }

    /**
     * Convert a Concurso to a JSON which can be sent to the server.
     */
    private convert(concurso: Concurso): Concurso {
        const copy: Concurso = Object.assign({}, concurso);
        copy.dtLancamento = this.dateUtils
            .convertLocalDateToServer(concurso.dtLancamento);
        copy.dtUltimaAlteracao = this.dateUtils
            .convertLocalDateToServer(concurso.dtUltimaAlteracao);
        copy.dtEntregaProposta = this.dateUtils
            .convertLocalDateToServer(concurso.dtEntregaProposta);
        copy.dtAberturaProposta = this.dateUtils
            .convertLocalDateToServer(concurso.dtAberturaProposta);
        copy.dtConclusaoAvaliacaoRelPrel = this.dateUtils
            .convertLocalDateToServer(concurso.dtConclusaoAvaliacaoRelPrel);
        copy.dtNegociacao = this.dateUtils
            .convertLocalDateToServer(concurso.dtNegociacao);
        copy.dtAprovRelAvalFinal = this.dateUtils
            .convertLocalDateToServer(concurso.dtAprovRelAvalFinal);
        return copy;
    }
}
