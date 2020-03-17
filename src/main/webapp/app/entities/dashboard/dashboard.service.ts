import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {SERVER_API_URL} from '../../app.constants';
import {createRequestOption} from '../../shared';
import {DashboardModel} from './dashboard.model';

@Injectable()
export class DashboardService {

    private resourceUrl = SERVER_API_URL + 'api/relatorios';

    constructor(private http: HttpClient) {
    }

    buscaDadosDashboard(req?: any): Observable<HttpResponse<DashboardModel[]>> {
        const options = createRequestOption(req);
        return this.http.get<DashboardModel[]>(this.resourceUrl + '/dashboard-principal', {params: options, observe: 'response'});
    }

}
