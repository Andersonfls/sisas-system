import {Component, OnInit} from '@angular/core';
import {JhiAlertService, JhiParseLinks} from 'ng-jhipster';
import {UserService} from '../../shared/user/user.service';
import {HttpClient} from '@angular/common/http';
import {Principal} from '../../shared/auth/principal.service';
import {ActivatedRoute} from '@angular/router';
import * as L from 'leaflet';

@Component({
    selector: 'jhi-map',
    templateUrl: './huambo.component.html',
    styleUrls: [
        'style.css'
    ]
})

export class HuamboComponent implements OnInit {
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
        zoom: 8,
        center: L.latLng(-12.614, 16.216)
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
                    case 1009 :  this._div.innerHTML += '<b>Valor: ' + 0 + '% </b><br />'; break; // Caála
                    case 1015 :  this._div.innerHTML += '<b>Valor: ' + 2 + '% </b><br />'; break; // Longonjo
                    case 1021: this._div.innerHTML += '<b>Valor: ' + 0 + '% </b><br />'; break; // Tcninjenje
                    case 1011: this._div.innerHTML += '<b>Valor: ' + 0 + '% </b><br />'; break; // Ukuma
                    case 1019 : this._div.innerHTML += '<b>Valor: ' + 0 + '% </b><br />'; break; // Libduimbali
                    case 1013: this._div.innerHTML += '<b>Valor: ' + 0 + '% </b><br />'; break; // Ekuma
                    case 1007: this._div.innerHTML += '<b>Valor: ' + 21 + '% </b><br />'; break; // Mungo
                    case 1005: this._div.innerHTML += '<b>Valor: ' + 0 + '% </b><br />'; break; // Kathihungo
                    case 1001:  this._div.innerHTML += '<b>Valor: ' + 32 + '% </b><br />';  break; // Huambo
                    case 1003 : this._div.innerHTML += '<b>Valor: ' + 18 + '% </b><br />'; break; // Tchikala- Tcholohanga
                    case 1017 : this._div.innerHTML += '<b>Valor: ' + 89 + '% </b><br />'; break; // Tchikala- Tcholohanga
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
        this.http.get('api/downloadFile/huambo.json').subscribe((json: any) => {
            console.log(json);
            geojson =  L.geoJSON(json, {
                style: (feature) => {
                    switch (feature.properties.nome) {
                        case 'Caála': return {color: 'white', weight: 2, opacity: 1, fillColor: '#FEFE9E', fillOpacity: 0.7}; // Caála
                        case 'Longonjo': return {color: 'white', weight: 2, opacity: 1, fillColor: '#FEFE9E', fillOpacity: 0.7}; // Longonjo
                        case 'Tcninjenje': return {color: 'white', weight: 2, opacity: 1, fillColor: '#FEFE9E', fillOpacity: 0.7}; // Tcninjenje
                        case 'Ukuma': return {color: 'white', weight: 2, opacity: 1, fillColor: '#FEFE9E', fillOpacity: 0.7}; // Ukuma
                        case 'Londuimbali': return {color: 'white', weight: 2, opacity: 1, fillColor: '#FEFE9E', fillOpacity: 0.7}; // Libduimbali
                        case 'Ekuma': return {color: 'white', weight: 2, opacity: 1, fillColor: '#FEFE9E', fillOpacity: 0.7}; // Ekuma
                        case 'Bailundo': return {color: 'white', weight: 2, opacity: 1, fillColor: '#9CCDFE', fillOpacity: 0.7}; // Bailundo
                        case 'Mungo': return {color: 'white', weight: 2, opacity: 1, fillColor: '#F6CD9D', fillOpacity: 0.7}; // Mungo
                        case 'Kathihungo': return {color: 'white', weight: 2, opacity: 1, fillColor: '#FEFE9E', fillOpacity: 0.7}; // Kathihungo
                        case 'Huambo': return {color: 'white', weight: 2, opacity: 1, fillColor: '#9CCDFE', fillOpacity: 0.7}; // Huambo
                        case 'Tchikala-Tcholohanga': return {color: 'white', weight: 2, opacity: 1, fillColor: '#FEFE9E', fillOpacity: 0.7}; // Tchikala- Tcholohanga
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
