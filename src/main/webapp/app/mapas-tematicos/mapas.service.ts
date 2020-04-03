import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {SERVER_API_URL} from '../app.constants';
import {createRequestOption} from '../shared';
import {MapasDados} from './mapasDados.model';

export type EntityResponseType = HttpResponse<MapasDados>;

@Injectable()
export class MapasService {

    private resourceUrl = SERVER_API_URL + 'api/mapas';

    constructor(private http: HttpClient) {
    }

    buscaDadosPorcentagemCoberturaServicosAguaProvincial(req?: any): Observable<HttpResponse<MapasDados[]>> {
        const options = createRequestOption(req);
        return this.http.get<MapasDados[]>(this.resourceUrl + '/cobertura-servicos-agua-provincial', {params: options, observe: 'response'});
    }
    buscaDadosPorcentagemSistemasAguaProvincial(req?: any): Observable<HttpResponse<MapasDados[]>> {
        const options = createRequestOption(req);
        return this.http.get<MapasDados[]>(this.resourceUrl + '/sistemas-agua-provincial', {params: options, observe: 'response'});
    }

}
