import { Component, OnInit } from '@angular/core';
import { JhiAlertService } from 'ng-jhipster';
import { UserService } from '../../shared/user/user.service';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { User } from '../../shared/user/user.model';
import { Principal } from '../../shared/auth/principal.service';
import * as CanvasJS from '../../../content/js/canvasjs.min.js';
import {DadosRelatorio} from '../../relatorios/cobertura-sector-agua/dadosRelatorio.model';
import {SectorAguaSaneamentoDados} from '../../relatorios/cobertura-sector-agua-saneamento/SectorAguaSaneamentoDados.model';
import {Provincia} from '../provincia/provincia.model';
import {RelatoriosService} from '../../relatorios/relatorios.service';

@Component({
    selector: 'jhi-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: [
        'dashboard.css'
    ]
})

export class DashboardComponent implements OnInit {

    user: User;

    provincias: Provincia[];
    listaTabela: SectorAguaSaneamentoDados[];
    predicate: any;
    reverse: any;
    chart: any;
    listaCobertura: DadosRelatorio[];
    listaSaneamento: DadosRelatorio[];
    tipoRelatorio: string;

    listaAgua: DadosRelatorio[];
    listaMedia: DadosRelatorio[];

    totalMunicipios = 0;
    totalComunas = 0;
    totalPopulacao = 0;
    totalBenefAgua = 0;
    totalBenefSaneamento = 0;
    totalCobertAgua = 0;
    totalCobertSaneamento = 0;

    constructor(
        private jhiAlertService: JhiAlertService,
        private userService: UserService,
        private principal: Principal,
        private relatorioService: RelatoriosService,
    ) {}

    ngOnInit() {
        this.principal.identity().then((userIdentity) => {
            this.user = userIdentity;

            if (this.user.authorities[0] === 'ROLE_ADMIN_LOCAL')
            {
                this.buscaDadosTabela();
                this.iniciarChartProvincial();
            } else {
                this.buscaDadosTabela();
            }
        });
    }

    buscaDadosTabela() {
        this.relatorioService.buscaDadosSectorAguaSaneamento().subscribe(
            (res: HttpResponse<SectorAguaSaneamentoDados[]>) => {
                this.listaTabela = res.body;
                console.log(this.listaTabela);

                this.listaCobertura = Array<any>();
                this.listaSaneamento = Array<any>();

                this.listaTabela.forEach((p) => {
                    const item: DadosRelatorio = new DadosRelatorio();
                    item.label = p.ambito;
                    item.y = p.habitantesPercent;

                    this.listaCobertura.push(item);
                });

                this.listaTabela.forEach((p) => {
                    const item: DadosRelatorio = new DadosRelatorio();
                    item.label = p.ambito;
                    item.y = p.habitantesSaneamentoPer;

                    this.listaSaneamento.push(item);
                });

                this.iniciarChart();
                this.iniciarChartSaneamento();
                this.iniciarChartAguaSaneamento();
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

    iniciarChartSaneamento() {
        this.chart = new CanvasJS.Chart('chartContainerSan', {
            animationEnabled: true,
            theme: 'light2',
            title: {
                text: 'Cobertura Serviços Saneamento'
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
                    dataPoints: this.listaSaneamento
                }]
        });
        this.chart.render();
    }

    iniciarChartAguaSaneamento() {
        this.chart = new CanvasJS.Chart('chartAguaSaneamento', {
            animationEnabled: true,
            theme: 'light2',
            title: {
                text: 'Cobertura de Serviços de Água e Saneamento (Nível nacional)'
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
                    dataPoints: this.listaCobertura
                },
                {
                    type: 'column',
                    name: 'Saneamento',
                    showInLegend: true,
                    xValueFormatString: 'string',
                    yValueFormatString: '#%',
                    dataPoints: this.listaSaneamento
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
