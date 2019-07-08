import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { SobreDna } from './sobre-dna.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<SobreDna>;

@Injectable()
export class SobreDnaService {

    private resourceUrl =  SERVER_API_URL + 'api/sobre-dnas';

    constructor(private http: HttpClient) { }

    create(sobreDna: SobreDna): Observable<EntityResponseType> {
        const copy = this.convert(sobreDna);
        return this.http.post<SobreDna>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(sobreDna: SobreDna): Observable<EntityResponseType> {
        const copy = this.convert(sobreDna);
        return this.http.put<SobreDna>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<SobreDna>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<SobreDna[]>> {
        const options = createRequestOption(req);
        return this.http.get<SobreDna[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<SobreDna[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: SobreDna = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<SobreDna[]>): HttpResponse<SobreDna[]> {
        const jsonResponse: SobreDna[] = res.body;
        const body: SobreDna[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to SobreDna.
     */
    private convertItemFromServer(sobreDna: SobreDna): SobreDna {
        const copy: SobreDna = Object.assign({}, sobreDna);
        return copy;
    }

    /**
     * Convert a SobreDna to a JSON which can be sent to the server.
     */
    private convert(sobreDna: SobreDna): SobreDna {
        const copy: SobreDna = Object.assign({}, sobreDna);
        return copy;
    }
}
