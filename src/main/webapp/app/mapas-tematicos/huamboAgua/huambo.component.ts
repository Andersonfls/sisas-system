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
        zoom: 8,
        center: L.latLng(-12.614, 16.216)
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
        });
        this.buscaDados();
    }

    buscaDados() {
        this.mapasService.buscaDadosPorcentagemCoberturaServicosAguaHuambo().subscribe(
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
        this.http.get('api/downloadFile/huambo.json').subscribe((json: any) => {
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

                    // switch (feature.properties.nome) {
                    //     case 'Caála': return {color: 'white', weight: 2, opacity: 1, fillColor: '#FEFE9E', fillOpacity: 0.7}; // Caála
                    //     case 'Longonjo': return {color: 'white', weight: 2, opacity: 1, fillColor: '#9CCDFE', fillOpacity: 0.7}; // Longonjo
                    //     case 'Tcninjenje': return {color: 'white', weight: 2, opacity: 1, fillColor: '#FEFE9E', fillOpacity: 0.7}; // Tcninjenje
                    //     case 'Ukuma': return {color: 'white', weight: 2, opacity: 1, fillColor: '#FEFE9E', fillOpacity: 0.7}; // Ukuma
                    //     case 'Londuimbali': return {color: 'white', weight: 2, opacity: 1, fillColor: '#9CCDFE', fillOpacity: 0.7}; // Libduimbali
                    //     case 'Ekuma': return {color: 'white', weight: 2, opacity: 1, fillColor: '#FEFE9E', fillOpacity: 0.7}; // Ekuma
                    //     case 'Bailundo': return {color: 'white', weight: 2, opacity: 1, fillColor: '#FEFE9E', fillOpacity: 0.7}; // Bailundo
                    //     case 'Mungo': return {color: 'white', weight: 2, opacity: 1, fillColor: '#FEFE9E', fillOpacity: 0.7}; // Mungo
                    //     case 'Kathihungo': return {color: 'white', weight: 2, opacity: 1, fillColor: '#FEFE9E', fillOpacity: 0.7}; // Kathihungo
                    //     case 'Huambo': return {color: 'white', weight: 2, opacity: 1, fillColor: '#FEFE9E', fillOpacity: 0.7}; // Huambo
                    //     case 'Tchikala-Tcholohanga': return {color: 'white', weight: 2, opacity: 1, fillColor: '#DAA7FE', fillOpacity: 0.7}; // Tchikala- Tcholohanga
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
