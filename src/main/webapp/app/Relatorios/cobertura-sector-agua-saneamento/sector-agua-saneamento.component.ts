import { Component, OnInit } from '@angular/core';
import { JhiAlertService } from 'ng-jhipster';
import { UserService } from '../../shared/user/user.service';
import { HttpResponse } from '@angular/common/http';
import { User } from '../../shared/user/user.model';
import { Principal } from '../../shared/auth/principal.service';
import * as CanvasJS from '../../../content/js/canvasjs.min.js';
import {Provincia, ProvinciaService} from '../../entities/provincia';
import {SectorAguaDados} from './SectorAguaDados.model';
import {DadosRelatorio} from '../cobertura-sector-agua/dadosRelatorio.model';

@Component({
    selector: 'jhi-sector-agua',
    templateUrl: './sector-agua.component.html',
    styleUrls: [
        'sector-agua.css'
    ]
})

export class CoberturaSectorAguaComponent implements OnInit {

    user: User;

    provincias: Provincia[];
    listaTabela: SectorAguaDados[];
    predicate: any;
    reverse: any;
    chart: any;
    listaMedia: DadosRelatorio[];
    listaCobertura: DadosRelatorio[];
    totalMunicipios = 0;
    totalComuna = 0;
    totalSistemas = 0;
    totalPopulacao = 0;
    totalBeneficiarios = 0;
    totalCobertura = 0;

    constructor(
        private jhiAlertService: JhiAlertService,
        private userService: UserService,
        private principal: Principal,
        private provinciaService: ProvinciaService,
    ) {}

    ngOnInit() {
        this.principal.identity().then((userIdentity) => {
            this.user = userIdentity;
        });
        this.buscaDadosTabela();
        this.totalCobertura = 35;
    }

    buscaDadosTabela() {
        this.provinciaService.buscaDadosSectorAgua().subscribe(
            (res: HttpResponse<SectorAguaDados[]>) => {
                this.listaTabela = res.body;
                console.log(this.listaTabela);

                this.listaMedia = Array<any>();
                this.listaCobertura = Array<any>();
                let media = 0;

                this.listaTabela.forEach((p) => {
                    const item: DadosRelatorio = new DadosRelatorio();
                    item.label = p.nomeProvincia;
                    item.y = p.cobertura;

                    this.listaCobertura.push(item);
                    media += p.cobertura;
                    this.totalMunicipios += p.municipios;
                    this.totalComuna += p.comunas;
                    this.totalSistemas += p.sistemasFuncionam;
                    this.totalPopulacao += p.populacaoTotal;
                    this.totalBeneficiarios += p.beneficiarios;
                });

                this.listaTabela.forEach((p) => {
                    const item: DadosRelatorio = new DadosRelatorio();
                    item.label = p.nomeProvincia;
                    item.y = media / this.listaTabela.length;
                    this.listaMedia.push(item);
                });

                this.iniciarChart();
            });
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    trackId(index: number, item: Provincia) {
        return item.id;
    }

    iniciarChart() {
        this.chart = new CanvasJS.Chart('chartContainer', {
            animationEnabled: true,
            theme: 'light2',
            title: {
                text: 'Cobertura no sector de Água'
            },
            axisX: {
                valueFormatString: 'string'
            },
            axisY: {
                suffix: '%'
            },
            toolTip: {
                shared: true
            },
            legend: {
                cursor: 'pointer'
            },
            data: [
                {
                    type: 'column',
                    name: 'Cobertura',
                    showInLegend: true,
                    xValueFormatString: 'string',
                    yValueFormatString: '#%',
                    dataPoints: this.listaCobertura
                },
                {
                    type: 'line',
                    name: 'Média',
                    showInLegend: true,
                    yValueFormatString: '#%',
                    dataPoints: this.listaMedia
                }]
        });
        this.chart.render();
    }

    addSymbols(e) {
        const suffixes = ['', 'K', 'M', 'B'];
        let order = Math.max(Math.floor(Math.log(e.value) / Math.log(1000)), 0);

        if (order > suffixes.length - 1) {
            order = suffixes.length - 1;
        }

        const suffix = suffixes[order];
        return CanvasJS.formatNumber(e.value / Math.pow(1000, order)) + suffix;
    }

    toggleDataSeries(e) {
        if (typeof (e.dataSeries.visible) === 'undefined' || e.dataSeries.visible) {
            e.dataSeries.visible = false;
        } else {
            e.dataSeries.visible = true;
        }
        e.chart.render();
    }
}
