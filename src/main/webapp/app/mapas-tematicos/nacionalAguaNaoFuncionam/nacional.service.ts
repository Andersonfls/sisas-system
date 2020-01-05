import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

export type EntityResponseType = HttpResponse<any>;

@Injectable()
export class NacionalService {

    private resourceUrl =  SERVER_API_URL + 'api/contratoes';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }


}
