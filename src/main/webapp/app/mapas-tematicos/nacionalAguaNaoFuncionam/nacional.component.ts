import {Component, OnInit} from '@angular/core';
import {JhiAlertService, JhiParseLinks} from 'ng-jhipster';
import {UserService} from '../../shared/user/user.service';
import {Principal} from '../../shared/auth/principal.service';
import {ActivatedRoute} from '@angular/router';
import * as L from 'leaflet';
import {HttpClient} from '@angular/common/http';

@Component({
    selector: 'jhi-map',
    templateUrl: './nacional.component.html',
    styleUrls: [
        'style.css'
    ]
})

export class NacionalComponent implements OnInit {
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
        zoom: 6,
        center: L.latLng(-11.114, 18.716)
    };

    constructor(
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
            this._div.innerHTML = '<h4>Informações</h4>' +  (props ?   '<b>' + props.nome + '</b><br />' : '');
            this._div.innerHTML += props ? '<b>Codigo: ' + props.code + '</b><br />' : '';
            if (props) {
                switch (props.code) {
                    case 1: this._div.innerHTML += '<b>Valor: ' + 55 + '% </b><br />'; break; // Cabinda
                    case 2: this._div.innerHTML += '<b>Valor: ' + 30 + '% </b><br />'; break; // Zaire
                    case 3: this._div.innerHTML += '<b>Valor: ' + 93 + '% </b><br />'; break; // Uige
                    case 4: this._div.innerHTML += '<b>Valor: ' + 3 + '% </b><br />'; break; // Luanda
                    case 5: this._div.innerHTML += '<b>Valor: ' + 58 + '% </b><br />'; break; // Kuanza Norte
                    case 6: this._div.innerHTML += '<b>Valor: ' + 63 + '% </b><br />'; break; // Kuanza Sul
                    case 7: this._div.innerHTML += '<b>Valor: ' + 35 + '% </b><br />'; break; // Malanje
                    case 8: this._div.innerHTML += '<b>Valor: ' + 71 + '% </b><br />'; break; // Lunda Norte
                    case 9: this._div.innerHTML += '<b>Valor: ' + 45 + '% </b><br />'; break; // Benguela
                    case 10: this._div.innerHTML += '<b>Valor: ' + 46 + '% </b><br />'; break; // Huambo
                    case 11: this._div.innerHTML += '<b>Valor: ' + 49 + '% </b><br />'; break; // Bie
                    case 12: this._div.innerHTML += '<b>Valor: ' + 68 + '% </b><br />'; break; // Moxico
                    case 13: this._div.innerHTML += '<b>Valor: ' + 60 + '% </b><br />'; break; // Kuando Kubango
                    case 14: this._div.innerHTML += '<b>Valor: ' + 41 + '% </b><br />'; break; // Namide
                    case 15: this._div.innerHTML += '<b>Valor: ' + 20 + '% </b><br />'; break; // Hulia
                    case 16: this._div.innerHTML += '<b>Valor: ' + 38 + '% </b><br />'; break; // Cunene
                    case 17: this._div.innerHTML += '<b>Valor: ' + 79 + '% </b><br />'; break; // Luanda Sul
                    case 18 : this._div.innerHTML += '<b>Valor: ' + 36 + '% </b><br />'; break; // Bengo
                }
            }
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
        this.http.get('api/downloadFile/nacional.json').subscribe((json: any) => {
            console.log(json);
            geojson =  L.geoJSON(json, {
                style: (feature) => {
                    switch (feature.properties.code) {
                        case 2: return {color: 'white', weight: 2, opacity: 1, fillColor: '#9CCDFE', fillOpacity: 0.7}; // Zaire
                        case 3: return {color: 'white', weight: 2, opacity: 1, fillColor: '#FCCC9E', fillOpacity: 0.7}; // Uige
                        case 4: return {color: 'white', weight: 2, opacity: 1, fillColor: '#FEFE9E', fillOpacity: 0.7}; // Luanda
                        case 18: return {color: 'white', weight: 2, opacity: 1, fillColor: '#9CCDFE', fillOpacity: 0.7}; // Bengo
                        case 5: return {color: 'white', weight: 2, opacity: 1, fillColor: '#FD9BCA', fillOpacity: 0.7}; // Kuanza Norte
                        case 7: return {color: 'white', weight: 2, opacity: 1, fillColor: '#9CCDFE', fillOpacity: 0.7}; // Malanje
                        case 8: return {color: 'white', weight: 2, opacity: 1, fillColor: '#BF8FF1', fillOpacity: 0.7}; // Lunda Norte
                        case 17: return {color: 'white', weight: 2, opacity: 1, fillColor: '#BF8FF1', fillOpacity: 0.7}; // Luanda Sul
                        case 12: return {color: 'white', weight: 2, opacity: 1, fillColor: '#BF8FF1', fillOpacity: 0.7}; // Moxico
                        case 11: return {color: 'white', weight: 2, opacity: 1, fillColor: '#FD9BCA', fillOpacity: 0.7}; // Bie
                        case 10: return {color: 'white', weight: 2, opacity: 1, fillColor: '#FD9BCA', fillOpacity: 0.7}; // Huambo
                        case 6: return {color: 'white', weight: 2, opacity: 1, fillColor: '#BF8FF1', fillOpacity: 0.7}; // Kuanza Sul
                        case 9: return {color: 'white', weight: 2, opacity: 1, fillColor: '#FD9BCA', fillOpacity: 0.7}; // Benguela
                        case 14: return {color: 'white', weight: 2, opacity: 1, fillColor: '#FD9BCA', fillOpacity: 0.7}; // Namide
                        case 15: return {color: 'white', weight: 2, opacity: 1, fillColor: '#FEFE9E', fillOpacity: 0.7}; // Hulia
                        case 16: return {color: 'white', weight: 2, opacity: 1, fillColor: '#9CCDFE', fillOpacity: 0.7}; // Cunene
                        case 13: return {color: 'white', weight: 2, opacity: 1, fillColor: '#FD9BCA', fillOpacity: 0.7}; // Kuando Kubango
                        case 1: return {color: 'white', weight: 2, opacity: 1, fillColor: '#FD9BCA', fillOpacity: 0.7}; // Cabinda
                    }
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
