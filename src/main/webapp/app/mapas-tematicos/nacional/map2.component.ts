import {Component, OnInit} from '@angular/core';
import {JhiAlertService, JhiParseLinks} from 'ng-jhipster';
import {UserService} from '../../shared/user/user.service';
import {Principal} from '../../shared/auth/principal.service';
import {ActivatedRoute} from '@angular/router';
import * as L from 'leaflet';
import {HttpClient} from '@angular/common/http';
import {UploadFileService} from './upload-file.service';

@Component({
    selector: 'jhi-map',
    templateUrl: './map2.component.html',
    styleUrls: [
        'style.css'
    ]
})

export class Map2Component implements OnInit {
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
        private http: HttpClient,
        private uploadService: UploadFileService
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

            const div = L.DomUtil.create('div', 'info legend'),
                grades = [ 1, 20, 40, 60, 80];

            div.innerHTML +=
                '<i></i> <span style="font-weight:bold"> **(% Funcionam )**</span>' + '<br>' ;
            // loop through our density intervals and generate a label with a colored square for each interval
            for (let i = 0; i < grades.length; i++) {
                let cor = '';
                const d = grades[i] + 1;

               cor = d > 80   ? '#bd7019' :
                        d > 60  ? '#b84ee3' :
                            d > 40  ? '#fc5794' :
                                    d > 20   ? '#5ce5fe' :
                                            '#feff1c';
                div.innerHTML +=
                    '<i style="background:' + cor + '"></i> ' +
                    grades[i] + (grades[i + 1] ? ' até ' + grades[i + 1] + '<br>' : ' até 100');
            }

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
        this.http.get('api/downloadFile/nacional.json').subscribe((json: any) => {
            console.log(json);
            geojson =  L.geoJSON(json, {
                style: (feature) => {
                    switch (feature.properties.code) {
                        case 1: return {color: 'white', weight: 2, opacity: 1, fillColor: '#BF8FF1', fillOpacity: 0.7};
                        case 2: return {color: 'white', weight: 2, opacity: 1, fillColor: '#FCCC9E', fillOpacity: 0.7};
                        case 3: return {color: 'white', weight: 2, opacity: 1, fillColor: '#FEFE9E', fillOpacity: 0.7};
                        case 4: return {color: 'white', weight: 2, opacity: 1, fillColor: '#9CCDFE', fillOpacity: 0.7};
                        case 5: return {color: 'white', weight: 2, opacity: 1, fillColor: '#FD9BCA', fillOpacity: 0.7};
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

    private onSuccess(data, headers) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = headers.get('X-Total-Count');
        this.queryCount = this.totalItems;
    }
}
