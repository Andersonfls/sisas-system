import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {SERVER_API_URL} from '../../app.constants';
import {SectorAguaSaneamentoDados} from '../../relatorios/cobertura-sector-agua-saneamento/SectorAguaSaneamentoDados.model';
import {createRequestOption} from '../../shared';

@Injectable()
export class DashboardService {

    private resourceUrl = SERVER_API_URL + 'api/relatorios';

    constructor(private http: HttpClient) {
    }

    buscaDadosSectorAguaSaneamento(req?: any): Observable<HttpResponse<SectorAguaSaneamentoDados[]>> {
        const options = createRequestOption(req);
        return this.http.get<SectorAguaSaneamentoDados[]>(this.resourceUrl + '/provincias/relatorio-saneamento', {params: options, observe: 'response'});
    }

}
