import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';
import { User } from './user.model';
import { createRequestOption } from '../model/request-util';

@Injectable()
export class UserService {

    private resourceUrl = SERVER_API_URL + 'api/users';
    private resourceUrl2 = SERVER_API_URL + 'api/users/dashboard';
    private resourceUrl3 = SERVER_API_URL + 'api/users/nomeOrdenado';
    constructor(private http: HttpClient) { }

    create(user: User): Observable<HttpResponse<User>> {
        return this.http.post<User>(this.resourceUrl, user, { observe: 'response' });
    }

    update(user: User): Observable<HttpResponse<User>> {
        return this.http.put<User>(this.resourceUrl, user, { observe: 'response' });
    }

    find(login: string): Observable<HttpResponse<User>> {
        return this.http.get<User>(`${this.resourceUrl}/${login}`, { observe: 'response' });
    }

    query(req?: any): Observable<HttpResponse<User[]>> {
        const options = createRequestOption(req);
        return this.http.get<User[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    queryOrderByName(req?: any): Observable<HttpResponse<User[]>> {
        const options = createRequestOption(req);
        return this.http.get<User[]>(this.resourceUrl3, { params: options, observe: 'response' });
    }

    queryUserDashboard(req?: any): Observable<HttpResponse<number>> {
        const options = createRequestOption(req);
        return this.http.get<number>(this.resourceUrl2, { params: options, observe: 'response' });
    }

    queryUserNome(req: any): Observable<HttpResponse<User[]>> {
        const params: HttpParams = createRequestOption(req);

        const requestURL = SERVER_API_URL + 'api/users/nomeFiltro';

        return this.http.get<User[]>(requestURL, {
            params,
            observe: 'response'
        });
    }

    delete(login: string): Observable<HttpResponse<any>> {
        return this.http.delete(`${this.resourceUrl}/${login}`, { observe: 'response' });
    }

    authorities(): Observable<string[]> {
        return this.http.get<string[]>(SERVER_API_URL + 'api/users/authorities');
    }

}
