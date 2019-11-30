import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {JhiAlertService, JhiParseLinks} from 'ng-jhipster';
import {UserService} from '../../shared/user/user.service';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Principal} from '../../shared/auth/principal.service';
import * as CanvasJS from '../../../content/js/canvasjs.min.js';
import {ActivatedRoute} from '@angular/router';
import {Municipio, MunicipioService} from '../../entities/municipio/index';
import {Comuna, ComunaService} from '../../entities/comuna/index';
import { icon, latLng, Map, marker, point, polyline, tileLayer } from 'leaflet';
import * as L from 'leaflet';

@Component({
    selector: 'jhi-map',
    templateUrl: './map2.component.html',
    styleUrls: [
        'style.css'
    ]
})

export class Map2Component implements OnInit {
    municipios: Municipio[];
    comunas: Comuna[];
    currentAccount: any;
    routeData: any;

    page: any;
    previousPage: any;

    links: any;
    totalItems: any;
    queryCount: any;

    reverse: any;
    predicate: any;

    private map;
    mostrar: boolean;

    streetMaps = tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        detectRetina: true,
        attribution: '&amp;copy; &lt;a href="https://www.openstreetmap.org/copyright"&gt;OpenStreetMap&lt;/a&gt; contributors'
    });
    wMaps = tileLayer('http://maps.wikimedia.org/osm-intl/{z}/{x}/{y}.png', {
        detectRetina: true,
        attribution: '&amp;copy; &lt;a href="https://www.openstreetmap.org/copyright"&gt;OpenStreetMap&lt;/a&gt; contributors'
    });

    summit = marker([ 46.8523, -121.7603 ], {
        icon: icon({
            iconSize: [ 25, 41 ],
            iconAnchor: [ 13, 41 ],
            iconUrl: 'leaflet/marker-icon.png',
            shadowUrl: 'leaflet/marker-shadow.png'
        })
    });
    paradise = marker([ 46.78465227596462, -121.74141269177198 ], {
        icon: icon({
            iconSize: [ 25, 41 ],
            iconAnchor: [ 13, 41 ],
            iconUrl: 'leaflet/marker-icon.png',
            shadowUrl: 'leaflet/marker-shadow.png'
        })
    });

    popup: any;

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
        this.initMap();
    }

    private initMap(): void {
        this.map = L.map('map', {
            center: [ 11.882, 16.711 ],
            zoom: 6
        });

        const tiles = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            maxZoom: 19,
            attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
        });

        tiles.addTo(this.map);

        this.popup = L.popup();
    }

    onMapClick(map: Map) {
       console.log(map.getBounds());
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

    /*-----------------------------------------BarChart-Reserva-------------------------------------*/

    public chartClickedReserva(e: any): void {
        console.log(e);
    }
    public chartHoveredReserva(e: any): void {
        console.log(e);
    }
    /*----------------------------------------------------------------------------------------*/

    /*------------------------------------------Donut--------------------------------------------*/
    public chartClickedDonut(e: any): void {
        console.log(e);
    }
    public chartHoveredDonut(e: any): void {
        console.log(e);
    }
    public chartClickedPieChart(e: any): void {
        console.log(e);
    }
    public chartHoveredPieChart(e: any): void {
        console.log(e);
    }
    public chartClickedLineChart(e: any): void {
        console.log(e);
    }
    public chartHoveredLineChart(e: any): void {
        console.log(e);
    }
}
