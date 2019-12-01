import {Component, OnInit} from '@angular/core';
import {JhiAlertService, JhiParseLinks} from 'ng-jhipster';
import {UserService} from '../../shared/user/user.service';
import {Principal} from '../../shared/auth/principal.service';
import {ActivatedRoute} from '@angular/router';
import {Municipio, MunicipioService} from '../../entities/municipio/index';
import {Comuna, ComunaService} from '../../entities/comuna/index';
import { latLng, Map, tileLayer} from 'leaflet';
import * as L from 'leaflet';
import {HttpClient} from '@angular/common/http';

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

    map: L.Map;
    json;
    options = {
        layers: [
            L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                maxZoom: 18,
                attribution: ''
            })
        ],
        zoom: 7,
        center: L.latLng(-6.435, 14.788)
    };

    constructor(
        private municipioService: MunicipioService,
        private comunaService: ComunaService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private userService: UserService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private http: HttpClient
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

    onMapReady(map: L.Map) {
        this.http.get('content/departements.json').subscribe((json: any) => {
            console.log(json);
            this.json = json;
            L.geoJSON(this.json, {style: this.style}).addTo(map);
        });

        const legend = new (L.Control.extend({
            options: { position: 'bottomright' }
        }));

        legend.onAdd = function() {

            const div = L.DomUtil.create('div', 'info legend'),
                grades = [ 1, 20, 40, 60, 80, 100];

            // loop through our density intervals and generate a label with a colored square for each interval
            for (let i = 0; i < grades.length; i++) {
                let cor = '';
                const d = grades[i] + 1;

               cor = d > 100  ? '#bd7727' :
                        d > 80  ? '#b84ee3' :
                            d > 60  ? '#fc93be' :
                                    d > 40   ? '#5ce5fe' :
                                            '#feff1c';
                div.innerHTML +=
                    '<i style="background:' + cor + '"></i> ' +
                    grades[i] + (grades[i + 1] ? '&ndash;' + grades[i + 1] + '<br>' : '+');
            }

            return div;
        };

        legend.addTo(map);

    }

    public style() {
        const collor = '#5ce5fe';
        return {
            fillColor: collor,
            weight: 2,
            opacity: 1,
            color: 'white',
            dashArray: '3',
            fillOpacity: 0.7
        };
    }

    public getColor(d) {
        return d > 1000 ? '#800026' :
            d > 500  ? '#BD0026' :
                d > 200  ? '#E31A1C' :
                    d > 100  ? '#FC4E2A' :
                        d > 50   ? '#FD8D3C' :
                            d > 20   ? '#FEB24C' :
                                d > 10   ? '#FED976' :
                                    '#FFEDA0';
    }

    onMapClick(map: Map) {
       console.log(map);
    }

    public sort() {
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
