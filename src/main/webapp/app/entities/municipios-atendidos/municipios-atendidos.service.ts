import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { MunicipiosAtendidos } from './municipios-atendidos.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<MunicipiosAtendidos>;

@Injectable()
export class MunicipiosAtendidosService {

    private resourceUrl =  SERVER_API_URL + 'api/municipios-atendidos';

    constructor(private http: HttpClient) { }

    create(municipiosAtendidos: MunicipiosAtendidos): Observable<EntityResponseType> {
        const copy = this.convert(municipiosAtendidos);
        return this.http.post<MunicipiosAtendidos>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(municipiosAtendidos: MunicipiosAtendidos): Observable<EntityResponseType> {
        const copy = this.convert(municipiosAtendidos);
        return this.http.put<MunicipiosAtendidos>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<MunicipiosAtendidos>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<MunicipiosAtendidos[]>> {
        const options = createRequestOption(req);
        return this.http.get<MunicipiosAtendidos[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<MunicipiosAtendidos[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: MunicipiosAtendidos = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<MunicipiosAtendidos[]>): HttpResponse<MunicipiosAtendidos[]> {
        const jsonResponse: MunicipiosAtendidos[] = res.body;
        const body: MunicipiosAtendidos[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to MunicipiosAtendidos.
     */
    private convertItemFromServer(municipiosAtendidos: MunicipiosAtendidos): MunicipiosAtendidos {
        const copy: MunicipiosAtendidos = Object.assign({}, municipiosAtendidos);
        return copy;
    }

    /**
     * Convert a MunicipiosAtendidos to a JSON which can be sent to the server.
     */
    private convert(municipiosAtendidos: MunicipiosAtendidos): MunicipiosAtendidos {
        const copy: MunicipiosAtendidos = Object.assign({}, municipiosAtendidos);
        return copy;
    }
}
