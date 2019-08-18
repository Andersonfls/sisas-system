import {Component, OnInit} from '@angular/core';
import {JhiAlertService, JhiParseLinks} from 'ng-jhipster';
import {UserService} from '../../shared/user/user.service';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {User} from '../../shared/user/user.model';
import {Principal} from '../../shared/auth/principal.service';
import * as CanvasJS from '../../../content/js/canvasjs.min.js';
import {ActivatedRoute} from "@angular/router";
import {Municipio, MunicipioService} from "../municipio";
import {Comuna, ComunaService} from "../comuna";

@Component({
    selector: 'jhi-map',
    templateUrl: './map.component.html',
    styleUrls: [
        'style.css'
    ]
})

export class MapComponent implements OnInit {
    municipios: Municipio[];
    comunas: Comuna[];
    currentAccount: any;
    routeData: any;

    page: any;
    previousPage: any;

    links:any;
    totalItems:any;
    queryCount:any;

    reverse: any;
    predicate: any;

    constructor(
        private municipioService: MunicipioService,
        private comunaService: ComunaService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private userService: UserService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
    ) {
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });

    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    carregaMunicipios() {
        this.municipioService.query({
            page: this.page - 1,
            sort: this.sort()
        }).subscribe(
            (res: HttpResponse<Municipio[]>) => this.onSuccess(res.body, res.headers),
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    carregaComunas() {

    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private onSuccess(data, headers) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = headers.get('X-Total-Count');
        this.queryCount = this.totalItems;
        // this.page = pagingParams.page;
        this.municipios = data;
    }

}
