import {Component, OnInit} from '@angular/core';
import {JhiAlertService, JhiParseLinks} from 'ng-jhipster';
import {UserService} from '../../shared/user/user.service';
import {HttpClient} from '@angular/common/http';
import {Principal} from '../../shared/auth/principal.service';
import {ActivatedRoute} from '@angular/router';
import * as L from 'leaflet';

@Component({
    selector: 'jhi-map',
    templateUrl: './hulia.component.html',
    styleUrls: [
        'style.css'
    ]
})

export class HuliaComponent implements OnInit {
    currentAccount: any;
    routeData: any;

    page: any;
    previousPage: any;

    links: any;
    totalItems: any;
    queryCount: any;

    reverse: any;
    predicate: any;
    tipo: string;

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
        center: L.latLng(-15.614, 16.216)
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
        this.tipo = null;
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
                    case 1513: this._div.innerHTML += '<b>Valor: ' + 35 + '</b><br />'; break; // Cabinda
                    case 1523: this._div.innerHTML += '<b>Valor: ' + 41 + '</b><br />'; break; // Zaire
                    case 1521: this._div.innerHTML += '<b>Valor: ' + 35 + '</b><br />'; break; // Uige
                    case 1507: this._div.innerHTML += '<b>Valor: ' + 15 + '</b><br />'; break; // Luanda
                    case 1511: this._div.innerHTML += '<b>Valor: ' + 3 + '</b><br />'; break; // Kuanza Norte
                    case 1509: this._div.innerHTML += '<b>Valor: ' + 1 + '</b><br />'; break; // Kuanza Sul
                    case 1519: this._div.innerHTML += '<b>Valor: ' + 17 + '</b><br />'; break; // Malanje
                    case 1525: this._div.innerHTML += '<b>Valor: ' + 30 + '</b><br />'; break; // Lunda Norte
                    case 1517: this._div.innerHTML += '<b>Valor: ' + 36 + '</b><br />'; break; // Benguela
                    case 1515: this._div.innerHTML += '<b>Valor: ' + 46 + '</b><br />'; break; // Huambo
                    case 1501: this._div.innerHTML += '<b>Valor: ' + 15 + '</b><br />'; break; // Bie
                    case 1505: this._div.innerHTML += '<b>Valor: ' + 4 + '</b><br />'; break; // Moxico
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
        this.http.get('api/downloadFile/hulia.json').subscribe((json: any) => {
            console.log(json);
            geojson =  L.geoJSON(json, {
                style: (feature) => {
                    switch (feature.properties.code) {
                        case 1513: return {color: 'white', weight: 2, opacity: 1, fillColor: '#9CCDFE', fillOpacity: 0.7}; // Kuvango
                        case 1523: return {color: 'white', weight: 2, opacity: 1, fillColor: '#FD9BCA', fillOpacity: 0.7}; // Chipindo
                        case 1521: return {color: 'white', weight: 2, opacity: 1, fillColor: '#9CCDFE', fillOpacity: 0.7}; // Jamba
                        case 1507: return {color: 'white', weight: 2, opacity: 1, fillColor: '#feff1c', fillOpacity: 0.7}; // Caconda
                        case 1511: return {color: 'white', weight: 2, opacity: 1, fillColor: '#feff1c', fillOpacity: 0.7}; // Quilengues
                        case 1509: return {color: 'white', weight: 2, opacity: 1, fillColor: '#feff1c', fillOpacity: 0.7}; // Caluquembe
                        case 1519: return {color: 'white', weight: 2, opacity: 1, fillColor: '#feff1c', fillOpacity: 0.7}; // Chicomba
                        case 1525: return {color: 'white', weight: 2, opacity: 1, fillColor: '#9CCDFE', fillOpacity: 0.7}; // Gambos
                        case 1517: return {color: 'white', weight: 2, opacity: 1, fillColor: '#9CCDFE', fillOpacity: 0.7}; // Matala
                        case 1515: return {color: 'white', weight: 2, opacity: 1, fillColor: '#FD9BCA', fillOpacity: 0.7}; // Quipungo
                        case 1501: return {color: 'white', weight: 2, opacity: 1, fillColor: '#feff1c', fillOpacity: 0.7}; // lUBANGO
                        case 1505: return {color: 'white', weight: 2, opacity: 1, fillColor: '#feff1c', fillOpacity: 0.7}; // Chibia
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
