import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpResponse} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';
import * as data from '../map2/data.json';
import {Adjudicacao} from '../../entities/adjudicacao';
import {GeoJSON} from 'leaflet';

export type EntityResponseType = HttpResponse<any>;

@Injectable()
export class MapaService {

    private resourceUrl =  SERVER_API_URL + 'api/contratoes';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }


}
