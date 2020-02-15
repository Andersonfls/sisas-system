import {Component, OnInit} from '@angular/core';
import {JhiAlertService, JhiParseLinks} from 'ng-jhipster';
import {UserService} from '../../shared/user/user.service';
import {HttpClient} from '@angular/common/http';
import {Principal} from '../../shared/auth/principal.service';
import {ActivatedRoute} from '@angular/router';
import * as L from 'leaflet';

@Component({
    selector: 'jhi-map',
    templateUrl: './kuanzaNorte.component.html',
    styleUrls: [
        'style.css'
    ]
})

export class KuanzaNorteComponent implements OnInit {
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
        zoom: 8,
        center: L.latLng(-8.614, 14.816)
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
            div.innerHTML += '<i style="background:#FEFE9E"></i>  0 até 21 <br>';
            div.innerHTML += '<i style="background:#A2D1FB"></i>  22 até 42 <br>';
            div.innerHTML += '<i style="background:#F693C7"></i>  43 até 63 <br>';
            div.innerHTML += '<i style="background:#DAA7FE"></i>  64 até 84 <br>';
            div.innerHTML += '<i style="background:#F6CD9D"></i>  86 até 105 <br>';
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
                    case 509: this._div.innerHTML += '<b>Valor: ' + 78 + '% </b><br />'; break; // Cabinda
                    case 513: this._div.innerHTML += '<b>Valor: ' + 83 + '% </b><br />'; break; // Zaire
                    case 517: this._div.innerHTML += '<b>Valor: ' + 25 + '% </b><br />'; break; // Uige
                    case 511: this._div.innerHTML += '<b>Valor: ' + 95 + '% </b><br />'; break; // Luanda
                    case 507: this._div.innerHTML += '<b>Valor: ' + 67 + '% </b><br />'; break; // Kuanza Norte
                    case 503: this._div.innerHTML += '<b>Valor: ' + 11 + '% </b><br />'; break; // Kuanza Sul
                    case 501: this._div.innerHTML += '<b>Valor: ' + 29 + '% </b><br />'; break; // Malanje
                    case 505: this._div.innerHTML += '<b>Valor: ' + 15 + '% </b><br />'; break; // Lunda Norte
                    case 515: this._div.innerHTML += '<b>Valor: ' + 25 + '% </b><br />'; break; // Benguela
                    case 519: this._div.innerHTML += '<b>Valor: ' + 8 + '% </b><br />'; break; // Huambo
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
            this.http.get('api/downloadFile/kuanzaNorte.json').subscribe((json: any) => {
                    geojson =  L.geoJSON(json, {
                        style: (feature) => {
                            switch (feature.properties.code) {
                                case 509: return {color: 'white', weight: 2, opacity: 1, fillColor: '#96D1FF', fillOpacity: 0.7};
                                case 513: return {color: 'white', weight: 2, opacity: 1, fillColor: '#FEFDA1', fillOpacity: 0.7};
                                case 517: return {color: 'white', weight: 2, opacity: 1, fillColor: '#DAA7FE', fillOpacity: 0.7};
                                case 511: return {color: 'white', weight: 2, opacity: 1, fillColor: '#FEFDA1', fillOpacity: 0.7};
                                case 507: return {color: 'white', weight: 2, opacity: 1, fillColor: '#96D1FF', fillOpacity: 0.7};
                                case 503: return {color: 'white', weight: 2, opacity: 1, fillColor: '#FCC9AE', fillOpacity: 0.7};
                                case 501: return {color: 'white', weight: 2, opacity: 1, fillColor: '#DAA7FE', fillOpacity: 0.7};
                                case 505: return {color: 'white', weight: 2, opacity: 1, fillColor: '#FCC9AE', fillOpacity: 0.7};
                                case 515: return {color: 'white', weight: 2, opacity: 1, fillColor: '#DAA7FE', fillOpacity: 0.7};
                                case 519: return {color: 'white', weight: 2, opacity: 1, fillColor: '#FCC9AE', fillOpacity: 0.7};
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
