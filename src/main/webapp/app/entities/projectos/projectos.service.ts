import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Projectos } from './projectos.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Projectos>;

@Injectable()
export class ProjectosService {

    private resourceUrl =  SERVER_API_URL + 'api/projectos';

    constructor(private http: HttpClient) { }

    create(projectos: Projectos): Observable<EntityResponseType> {
        const copy = this.convert(projectos);
        return this.http.post<Projectos>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(projectos: Projectos): Observable<EntityResponseType> {
        const copy = this.convert(projectos);
        return this.http.put<Projectos>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Projectos>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Projectos[]>> {
        const options = createRequestOption(req);
        return this.http.get<Projectos[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Projectos[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Projectos = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Projectos[]>): HttpResponse<Projectos[]> {
        const jsonResponse: Projectos[] = res.body;
        const body: Projectos[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Projectos.
     */
    private convertItemFromServer(projectos: Projectos): Projectos {
        const copy: Projectos = Object.assign({}, projectos);
        return copy;
    }

    /**
     * Convert a Projectos to a JSON which can be sent to the server.
     */
    private convert(projectos: Projectos): Projectos {
        const copy: Projectos = Object.assign({}, projectos);
        return copy;
    }
}
