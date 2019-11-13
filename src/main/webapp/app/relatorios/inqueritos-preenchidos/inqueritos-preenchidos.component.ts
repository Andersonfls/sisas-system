import { Component, OnInit } from '@angular/core';
import { JhiAlertService } from 'ng-jhipster';
import { UserService } from '../../shared/user/user.service';
import { HttpResponse } from '@angular/common/http';
import { User } from '../../shared/user/user.model';
import { Principal } from '../../shared/auth/principal.service';
import * as CanvasJS from '../../../content/js/canvasjs.min.js';
import {Provincia, ProvinciaService} from '../../entities/provincia';
import {InqueritosPreenchidosDados} from './InqueritosPreenchidosDados.model';
import {DadosRelatorio} from '../cobertura-sector-agua/dadosRelatorio.model';
import {RelatoriosService} from '../relatorios.service';

@Component({
    selector: 'jhi-sector-agua',
    templateUrl: './inqueritos-preenchidos.component.html',
    styleUrls: [
        'inqueritos-preenchidos.css'
    ]
})

export class InqueritosPreenchidosComponent implements OnInit {

    user: User;

    provincias: Provincia[];
    listaTabela: InqueritosPreenchidosDados[];
    predicate: any;
    reverse: any;
    chart: any;
    listaAguas: DadosRelatorio[];
    listaAguasNao: DadosRelatorio[];
    listaSaneamentoSim: DadosRelatorio[];
    listaSaneamentoNao: DadosRelatorio[];
    listaTotalInqueritos: DadosRelatorio[];
    tipoRelatorio: string;

    totalMunicipios = 0;
    totalComuna = 0;
    totalAguasSim = 0;
    totalAguasNao = 0;
    totalAguas = 0;
    totalSaneamentoSim = 0;
    totalSaneamentoNao = 0;
    totalSaneamento = 0;
    totalInqueritosSector = 0;
    total = 0;
    totalEscola = 0;
    totalHospitais = 0;
    totalFamilias = 0;
    totalGeral = 0;

    constructor(
        private jhiAlertService: JhiAlertService,
        private userService: UserService,
        private principal: Principal,
        private provinciaService: ProvinciaService,
        private relatorioService: RelatoriosService,
    ) {}

    ngOnInit() {
        this.principal.identity().then((userIdentity) => {
            this.user = userIdentity;
        });
        this.tipoRelatorio = null;
    }

    validaTipoRelatorio() {
        if (this.tipoRelatorio === 'Modelo 1') {
            this.buscaDadosTabelaModelo1();
        }

        if (this.tipoRelatorio === 'Modelo 2') {
            this.buscaDadosTabelaModelo2();
        }
    }

    buscaDadosTabelaModelo1() {
        this.relatorioService.buscaDadosInqueritosPreenchidos().subscribe(
            (res: HttpResponse<InqueritosPreenchidosDados[]>) => {
                this.listaTabela = res.body;
                this.listaAguas = Array<any>();
                this.listaAguasNao = Array<any>();
                this.listaSaneamentoSim = Array<any>();
                this.listaSaneamentoNao = Array<any>();

                this.listaTabela.forEach((p) => {
                    let item: DadosRelatorio = new DadosRelatorio();
                    item.label = p.nomeProvincia;
                    item.y = p.aguasSim;
                    this.listaAguas.push(item);

                    item = new DadosRelatorio();
                    item.label = p.nomeProvincia;
                    item.y = p.saneamentoSim;
                    this.listaSaneamentoSim.push(item);

                    this.totalMunicipios += p.municipios;
                    this.totalComuna += p.comunas;
                    this.totalAguas += p.totalAguas;
                    this.totalAguasSim += p.aguasSim;
                    this.totalAguasNao += p.aguasNao;
                    this.totalSaneamento += p.totalSaneamento;
                    this.totalSaneamentoSim += p.saneamentoSim;
                    this.totalSaneamentoNao += p.saneamentoNao;
                    this.totalInqueritosSector += p.totalInqueritoSector;
                });

                this.listaTabela.forEach((p) => {
                    let item: DadosRelatorio = new DadosRelatorio();
                    item.label = p.nomeProvincia;
                    item.y = p.aguasNao;
                    this.listaAguasNao.push(item);

                    item = new DadosRelatorio();
                    item.label = p.nomeProvincia;
                    item.y = p.saneamentoNao;

                    this.listaSaneamentoNao.push(item);
                });
                console.log(this.listaSaneamentoSim);
                console.log(this.listaSaneamentoNao);

                this.iniciarChartAgua();
                this.iniciarChartSaneamento();
            });
    }

    buscaDadosTabelaModelo2() {
        this.relatorioService.buscaDadosInqueritosPreenchidos2().subscribe(
            (res: HttpResponse<InqueritosPreenchidosDados[]>) => {
                this.listaTabela = res.body;
                this.listaTotalInqueritos = Array<any>();

                this.listaTabela.forEach((p) => {
                    this.totalMunicipios += p.municipios;
                    this.totalComuna += p.comunas;
                    this.totalAguas += p.totalAguas;
                    this.totalAguasSim += p.aguasSim;
                    this.totalAguasNao += p.aguasNao;
                    this.totalSaneamento += p.totalSaneamento;
                    this.totalSaneamentoSim += p.saneamentoSim;
                    this.totalSaneamentoNao += p.saneamentoNao;
                    this.totalInqueritosSector += p.totalInqueritoSector;
                });

                let item: DadosRelatorio = new DadosRelatorio();
                item.label = 'Águas SIM';
               // item.y = this.totalAguasSim;
                item.y = 4186;
                this.listaTotalInqueritos.push(item);

                item = new DadosRelatorio();
                item.label = 'Águas NAO';
                // item.y = this.totalAguasNao;
                item.y = 13127;
                this.listaTotalInqueritos.push(item);

                item = new DadosRelatorio();
                item.label = 'Saneamento';
                // item.y = this.totalSaneamento;
                item.y = 844;
                this.listaTotalInqueritos.push(item);

                item = new DadosRelatorio();
                item.label = 'Escolas';
                // item.y = this.totalEscola;
                item.y = 3275;
                this.listaTotalInqueritos.push(item);

                item = new DadosRelatorio();
                item.label = 'Hospitais';
               // item.y = this.totalHospitais;
                item.y = 1457;
                this.listaTotalInqueritos.push(item);

                item = new DadosRelatorio();
                item.label = 'Famílias';
                // item.y = this.totalFamilias;
                item.y = 9197;
                this.listaTotalInqueritos.push(item);

                this.iniciarChartTotalInqueritos();
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

    iniciarChartAgua() {
        this.chart = new CanvasJS.Chart('chartAgua', {
            animationEnabled: true,
            theme: 'light2',
            title: {
                text: 'Inquéritos Preenchidos - Água'
            },
            axisX: {
                valueFormatString: 'string'
            },
            axisY: {
                suffix: ''
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
                    name: 'Águas (SIM)',
                    showInLegend: true,
                    xValueFormatString: 'string',
                    yValueFormatString: '#',
                    dataPoints: this.listaAguas
                },
                {
                    type: 'column',
                    name: 'Aguas (NÃO)',
                    showInLegend: true,
                    xValueFormatString: 'string',
                    yValueFormatString: '#',
                    dataPoints: this.listaAguasNao
                }]
        });
        this.chart.render();
    }

    iniciarChartSaneamento() {
        this.chart = new CanvasJS.Chart('chartSaneamento', {
            animationEnabled: true,
            theme: 'light2',
            title: {
                text: 'Inquéritos Preenchidos - Saneamento'
            },
            axisX: {
                valueFormatString: 'string'
            },
            axisY: {
                suffix: ''
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
                    name: 'Saneamento (SIM)',
                    showInLegend: true,
                    xValueFormatString: 'string',
                    yValueFormatString: '#',
                    dataPoints: this.listaSaneamentoSim
                },
                {
                    type: 'column',
                    name: 'Saneamento (NÃO)',
                    showInLegend: true,
                    xValueFormatString: 'string',
                    yValueFormatString: '#',
                    dataPoints: this.listaSaneamentoNao
                }]
        });
        this.chart.render();
    }

    iniciarChartTotalInqueritos() {
        this.chart = new CanvasJS.Chart('chartTotalInqueritos', {
            animationEnabled: true,
            theme: 'light2',
            title: {
                text: 'Total de Inquéritos'
            },
            axisX: {
                valueFormatString: 'string'
            },
            axisY: {
                suffix: ''
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
                    name: 'Total de Inqueritos',
                    showInLegend: true,
                    xValueFormatString: 'string',
                    yValueFormatString: '#',
                    dataPoints: this.listaTotalInqueritos
                }]
        });
        this.chart.render();
    }

}
