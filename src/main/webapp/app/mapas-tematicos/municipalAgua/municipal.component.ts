import {Component, OnInit} from '@angular/core';
import {JhiAlertService, JhiParseLinks} from 'ng-jhipster';
import {UserService} from '../../shared/user/user.service';
import {Principal} from '../../shared/auth/principal.service';
import {ActivatedRoute} from '@angular/router';
import * as L from 'leaflet';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Municipio, MunicipioService} from '../../entities/municipio';

@Component({
    selector: 'jhi-map',
    templateUrl: './municipal.component.html',
    styleUrls: [
        'style.css'
    ]
})

export class MunicipalComponent implements OnInit {
    currentAccount: any;
    routeData: any;

    page: any;
    previousPage: any;

    links: any;
    totalItems: any;
    queryCount: any;

    reverse: any;
    predicate: any;
    municipios: Municipio[];

    map: L.Map;
    json;
    options = {
        layers: [
            L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                maxZoom: 18,
                attribution: ''
            })
        ],
        zoom: 6,
        center: L.latLng(-11.114, 18.716)
    };

    constructor(
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private userService: UserService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private http: HttpClient,
        private municipioService: MunicipioService
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

        this.municipioService.query().subscribe(
            (res: HttpResponse<Municipio[]>) => this.municipios = res.body
        );

        let geojson;

        // LEGENDA
        const legend = new (L.Control.extend({
            options: { position: 'bottomright' }
        }));

        legend.onAdd = function() {
            const div = L.DomUtil.create('div', 'info legend');
            div.innerHTML += '<i></i> <span style="font-weight:bold"> **Legenda**</span>' + '<br>' ;
            div.innerHTML += '<i style="background:#FEFE9E"></i>  0 até 20 <br>';
            div.innerHTML += '<i style="background:#A2D1FB"></i>  21 até 40 <br>';
            div.innerHTML += '<i style="background:#F693C7"></i>  41 até 60 <br>';
            div.innerHTML += '<i style="background:#DAA7FE"></i>  61 até 80 <br>';
            div.innerHTML += '<i style="background:#F6CD9D"></i>  81 até 100 <br>';
            return div;
        };

        legend.addTo(map);

        // INFO TOP MAPA
        let info;

        info = new L.Control();

        info.onAdd = function() {
            this._div = L.DomUtil.create('div', 'info');
            this.update();
            return this._div;
        };

        info.update = function(props) {
            this._div.innerHTML = '<h4>Informações</h4>' +  (props ?   '<b>' + props.nome + '</b><br />'
                : '');
        };

        info.addTo(map);

        // FUNCOES DE HIGHT LIGHT DE INFORMACOES
        function resetHighlight(e) {
            geojson.resetStyle(e.target);
            info.update();
        }

        function zoomToFeature(e) {
            map.fitBounds(e.target.getBounds());
        }

        function highlightFeature(e) {
            const layer = e.target;

            layer.setStyle({
                weight: 5,
                color: '#666',
                dashArray: '',
                fillOpacity: 0.2
            });

            if (!L.Browser.ie &&  !L.Browser.edge) {
                layer.bringToFront();
            }

            info.update(layer.feature.properties);
        }

        // INICIALIZAZAO DO MAPA
        this.http.get('api/downloadFile/municipal.json').subscribe((json: any) => {
            console.log(json);
            geojson =  L.geoJSON(json, {
                style: (feature) => {
                    let muni = new Municipio();
                    // this.municipioService.findByName(feature.properties.nome).subscribe(
                    //     (res: HttpResponse<Municipio>) => muni = res.body
                    // );

                    this.municipios.forEach((item) => {
                        if (item.nmMunicipio === feature.properties.nome) {
                            muni = item;
                            console.log(muni);
                        }
                    });

                    if (muni.valor > 0 && muni.valor < 42) {
                        return {color: 'white', weight: 2, opacity: 1, fillColor: '#FEFE9E', fillOpacity: 0.7};
                    } else if (muni.valor > 41 && muni.valor < 83) {
                        return {color: 'white', weight: 2, opacity: 1, fillColor: '#9CCDFE', fillOpacity: 0.7};
                    } else if (muni.valor > 82 && muni.valor < 124) {
                        return {color: 'white', weight: 2, opacity: 1, fillColor: '#FD9BCA', fillOpacity: 0.7};
                    } else if (muni.valor > 123 && muni.valor < 165) {
                        return {color: 'white', weight: 2, opacity: 1, fillColor: '#BF8FF1', fillOpacity: 0.7};
                    } else if (muni.valor > 164 && muni.valor < 206) {
                        return {color: 'white', weight: 2, opacity: 1, fillColor: '#FCCC9E', fillOpacity: 0.7};
                    } else {
                        return {color: 'white', weight: 2, opacity: 1, fillColor: '', fillOpacity: 0.7};
                    }
                    // if (feature.properties.value > 0 && feature.properties.code < 42){
                    //     return {color: 'white', weight: 2, opacity: 1, fillColor: '#FEFE9E', fillOpacity: 0.7};
                    // } else if (feature.properties.value > 41 && feature.properties.code < 83){
                    //     return {color: 'white', weight: 2, opacity: 1, fillColor: '#9CCDFE', fillOpacity: 0.7};
                    // } else if (feature.properties.value > 82 && feature.properties.code < 124){
                    //     return {color: 'white', weight: 2, opacity: 1, fillColor: '#FD9BCA', fillOpacity: 0.7};
                    // } else if (feature.properties.value > 123 && feature.properties.code < 165){
                    //     return {color: 'white', weight: 2, opacity: 1, fillColor: '#BF8FF1', fillOpacity: 0.7};
                    // } else if (feature.properties.value > 164 && feature.properties.code < 206){
                    //     return {color: 'white', weight: 2, opacity: 1, fillColor: '#FCCC9E', fillOpacity: 0.7};
                    // } else {
                    //     return {color: 'white', weight: 2, opacity: 1, fillColor: '', fillOpacity: 0.7};
                    // }

                },
                onEachFeature: function onEachFeature(feature, layer) {
                    layer.on({
                        mouseover: highlightFeature,
                        mouseout: resetHighlight,
                        click: zoomToFeature
                    });
                }
            }).addTo(map);
        });

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

}
