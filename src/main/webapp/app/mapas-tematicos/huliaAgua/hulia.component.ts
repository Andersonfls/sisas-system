import {Component, OnInit} from '@angular/core';
import {JhiAlertService, JhiParseLinks} from 'ng-jhipster';
import {UserService} from '../../shared/user/user.service';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Principal} from '../../shared/auth/principal.service';
import {ActivatedRoute} from '@angular/router';
import * as L from 'leaflet';
import {MapasDados} from '../mapasDados.model';
import {MapasService} from '../mapas.service';

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
    dadosMapa: MapasDados[];

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
        private http: HttpClient,
        private mapasService: MapasService
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
            this.buscaDados();
        });
    }

    buscaDados() {
        this.mapasService.buscaDadosPorcentagemCoberturaServicosAguaHuila().subscribe(
            (res: HttpResponse<MapasDados[]>) => {
                this.dadosMapa = res.body;
                console.log(this.dadosMapa);
            });
    }

    onMapReady(map: L.Map) {
        setTimeout(() => {
            map.invalidateSize(true  );
        }, 1000 );
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
                this._div.innerHTML += '<b>Valor: ' + props.valor + '% </b><br />';
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
            for (let i = 0; i < json.features.length; i++) {
                if (this.dadosMapa) {
                    this.dadosMapa.forEach((item) => {
                        if (item.idProvincia === json.features[i].properties.code) {
                            json.features[i].properties.valor = item.porcentagemCobertura.toFixed(2);
                        }
                    });
                }
                console.log(json.features[i]);
            }
            geojson =  L.geoJSON(json, {
                style: (feature) => {
                    let retorno = {color: '', weight: 2, opacity: 1, fillColor: '', fillOpacity: 0.7};
                    if (feature.properties.valor < 21) {
                        retorno = {color: 'white', weight: 2, opacity: 1, fillColor: '#FEFE9E', fillOpacity: 0.7};
                    } else if (feature.properties.valor < 41) {
                        retorno = {color: 'white', weight: 2, opacity: 1, fillColor: '#A2D1FB', fillOpacity: 0.7};
                    }else if (feature.properties.valor < 61) {
                        retorno = {color: 'white', weight: 2, opacity: 1, fillColor: '#F693C7', fillOpacity: 0.7};
                    }else if (feature.properties.valor < 81) {
                        retorno = {color: 'white', weight: 2, opacity: 1, fillColor: '#DAA7FE', fillOpacity: 0.7};
                    } else if (feature.properties.valor > 81) {
                        retorno = {color: 'white', weight: 2, opacity: 1, fillColor: '#F6CD9D', fillOpacity: 0.7};
                    }
                    return retorno;

                    // switch (feature.properties.code) {
                    //     case 1513: return {color: 'white', weight: 2, opacity: 1, fillColor: '#9CCDFE', fillOpacity: 0.7}; // Kuvango
                    //     case 1523: return {color: 'white', weight: 2, opacity: 1, fillColor: '#FD9BCA', fillOpacity: 0.7}; // Chipindo
                    //     case 1521: return {color: 'white', weight: 2, opacity: 1, fillColor: '#9CCDFE', fillOpacity: 0.7}; // Jamba
                    //     case 1507: return {color: 'white', weight: 2, opacity: 1, fillColor: '#feff1c', fillOpacity: 0.7}; // Caconda
                    //     case 1511: return {color: 'white', weight: 2, opacity: 1, fillColor: '#feff1c', fillOpacity: 0.7}; // Quilengues
                    //     case 1509: return {color: 'white', weight: 2, opacity: 1, fillColor: '#feff1c', fillOpacity: 0.7}; // Caluquembe
                    //     case 1519: return {color: 'white', weight: 2, opacity: 1, fillColor: '#feff1c', fillOpacity: 0.7}; // Chicomba
                    //     case 1525: return {color: 'white', weight: 2, opacity: 1, fillColor: '#9CCDFE', fillOpacity: 0.7}; // Gambos
                    //     case 1517: return {color: 'white', weight: 2, opacity: 1, fillColor: '#9CCDFE', fillOpacity: 0.7}; // Matala
                    //     case 1515: return {color: 'white', weight: 2, opacity: 1, fillColor: '#FD9BCA', fillOpacity: 0.7}; // Quipungo
                    //     case 1501: return {color: 'white', weight: 2, opacity: 1, fillColor: '#feff1c', fillOpacity: 0.7}; // lUBANGO
                    //     case 1505: return {color: 'white', weight: 2, opacity: 1, fillColor: '#feff1c', fillOpacity: 0.7}; // Chibia
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
}
