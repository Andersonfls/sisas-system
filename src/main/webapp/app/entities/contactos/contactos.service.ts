import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Contactos } from './contactos.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Contactos>;

@Injectable()
export class ContactosService {

    private resourceUrl =  SERVER_API_URL + 'api/contactos';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(contactos: Contactos): Observable<EntityResponseType> {
        const copy = this.convert(contactos);
        return this.http.post<Contactos>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(contactos: Contactos): Observable<EntityResponseType> {
        const copy = this.convert(contactos);
        return this.http.put<Contactos>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Contactos>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Contactos[]>> {
        const options = createRequestOption(req);
        return this.http.get<Contactos[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Contactos[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Contactos = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Contactos[]>): HttpResponse<Contactos[]> {
        const jsonResponse: Contactos[] = res.body;
        const body: Contactos[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Contactos.
     */
    private convertItemFromServer(contactos: Contactos): Contactos {
        const copy: Contactos = Object.assign({}, contactos);
        copy.dtLancamento = this.dateUtils
            .convertLocalDateFromServer(contactos.dtLancamento);
        copy.dtUltimaAlteracao = this.dateUtils
            .convertLocalDateFromServer(contactos.dtUltimaAlteracao);
        return copy;
    }

    /**
     * Convert a Contactos to a JSON which can be sent to the server.
     */
    private convert(contactos: Contactos): Contactos {
        const copy: Contactos = Object.assign({}, contactos);
        copy.dtLancamento = this.dateUtils
            .convertLocalDateToServer(contactos.dtLancamento);
        copy.dtUltimaAlteracao = this.dateUtils
            .convertLocalDateToServer(contactos.dtUltimaAlteracao);
        return copy;
    }
}
