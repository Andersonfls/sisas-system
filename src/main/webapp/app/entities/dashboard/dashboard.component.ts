import { Component, OnInit } from '@angular/core';
import { JhiAlertService } from 'ng-jhipster';
import { UserService } from '../../shared/user/user.service';
import { HttpResponse } from '@angular/common/http';
import { User } from '../../shared/user/user.model';
import { Principal } from '../../shared/auth/principal.service';
import * as CanvasJS from '../../../content/js/canvasjs.min.js';
import {Provincia} from '../provincia/provincia.model';
import {DashboardService} from './dashboard.service';
import {DadosDashboardModel} from './dadosDashboard.model';
import {DashboardModel} from './dashboard.model';

@Component({
    selector: 'jhi-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: [
        'dashboard.css'
    ]
})

export class DashboardComponent implements OnInit {
    user: User;
    listaTabela: DashboardModel[];
    predicate: any;
    reverse: any;
    chart: any;
    listaCobertura: DadosDashboardModel[];
    listaSaneamento: DadosDashboardModel[];
    listaAgua: DadosDashboardModel[];
    listaMedia: DadosDashboardModel[];

    totalSistemas = 0;
    totalFuncionam = 0;
    totalFuncionamPerc = 0;
    totalNaoFuncionam = 0;
    totalNaoFuncionamPerc = 0;

    constructor(
        private jhiAlertService: JhiAlertService,
        private userService: UserService,
        private principal: Principal,
        private relatorioService: DashboardService,
    ) {}

    ngOnInit() {
        this.principal.identity().then((userIdentity) => {
            this.user = userIdentity;
            this.buscaDadosTabela();
            // this.iniciarChartProvincial();
        });
    }

    buscaDadosTabela() {
        this.relatorioService.buscaDadosDashboard().subscribe(
            (res: HttpResponse<DashboardModel[]>) => {
                this.listaTabela = res.body;
                this.listaCobertura = Array<any>();
                this.listaSaneamento = Array<any>();

                this.listaTabela.forEach((p) => {
                    const item: DadosDashboardModel = new DadosDashboardModel();
                    item.label = p.nomeProvincia;
                    item.y = p.numeroSistemas;

                    this.totalSistemas += p.numeroSistemas;
                    this.totalFuncionam += p.numeroSistemasFuncionam;
                    this.totalFuncionamPerc += p.sistemasFuncionamPerc;
                    this.totalNaoFuncionam += p.numeroSistemasNaoFuncionam;
                    this.totalNaoFuncionamPerc += p.sistemasNaoFuncionamPerc;

                    this.listaCobertura.push(item);
                });
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
                text: 'Cobertura Serviços de Água'
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
                    name: 'Porcentagem',
                    showInLegend: true,
                    xValueFormatString: 'string',
                    yValueFormatString: '#%',
                    dataPoints: this.listaCobertura
                }]
        });
        this.chart.render();
    }

    iniciarChartProvincial() {
        this.chart = new CanvasJS.Chart('chartProvincial', {
            animationEnabled: true,
            theme: 'light2',
            title: {
                text: 'Cobertura no sector de Água e Saneamento'
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
                    name: 'Água',
                    showInLegend: true,
                    xValueFormatString: 'string',
                    yValueFormatString: '#%',
                    dataPoints: this.listaAgua
                },
                {
                    type: 'column',
                    name: 'Saneamento',
                    showInLegend: true,
                    xValueFormatString: 'string',
                    yValueFormatString: '#%',
                    dataPoints: this.listaSaneamento
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
}
