import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import { JhiAlertService } from 'ng-jhipster';
import { UserService } from '../../shared/user/user.service';
import { HttpResponse } from '@angular/common/http';
import { User } from '../../shared/user/user.model';
import { Principal } from '../../shared/auth/principal.service';
import * as CanvasJS from '../../../content/js/canvasjs.min.js';
import {Provincia, ProvinciaService} from '../../entities/provincia';
import {DashboardPrincipalDados} from './DashboardPrincipalDados.model';
import {RelatoriosService} from '../relatorios.service';
import * as jsPDF from 'jspdf';
import {TableUtil} from '../../shared/util/tableUtil';
import * as html2canvas from 'html2canvas';
import {DadosRelatorio} from '../cobertura-sector-agua-provincial/dadosRelatorio.model';
import {DadosRelatorioPie} from '../inqueritos-preenchidos/dadosPieRel.model';

@Component({
    selector: 'jhi-sector-agua',
    templateUrl: './dashboard-principal.component.html',
    styleUrls: [
        'dashboard-principal.css'
    ]
})

export class DashboardPrincipalComponent implements OnInit {

    user: User;
    predicate: any;
    reverse: any;
    chart: any;
    chartPie1: any;
    chartPie2: any;
    chartPie3: any;
    listaSistemas: DashboardPrincipalDados[];
    listaIndicadores: DashboardPrincipalDados[];
    listaCaptada: DadosRelatorio[];
    listaTratada: DadosRelatorio[];
    listaDistribuida: DadosRelatorio[];

    sistemasExixtentes: any;
    casasLigadas: any;
    acessoAgua: any;

    totalSistemas = 0;
    totalInqueritos = 0;
    totalCasasLigadas = 0;
    totalAcessoAgua = 0;

    @ViewChild('pdfTable') pdfTable: ElementRef;

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
        this.buscaDadosIndicadores();
        this.buscaDadosSistemas();
    }

    buscaDadosSistemas() {
        this.relatorioService.buscaDadosSistemas().subscribe(
            (res: HttpResponse<DashboardPrincipalDados[]>) => {
                this.listaSistemas = res.body;

                this.sistemasExixtentes = [];
                this.casasLigadas = [];
                this.acessoAgua = [];

                this.listaSistemas.forEach((p) => {
                    this.totalSistemas += p.sistemaAguaExistentes;
                    this.totalCasasLigadas += p.totalCasasLigadas;
                    this.totalAcessoAgua += p.totalPessoasAceesoAgua;
                });

                this.listaSistemas.forEach((p) => {
                    this.sistemasExixtentes.push({y: p.sistemaAguaExistentes, name: this.getMonth(p.mes)});

                });

                this.listaSistemas.forEach((p) => {
                    this.casasLigadas.push({y: p.totalCasasLigadas, name: this.getMonth(p.mes)});
                });

                this.listaSistemas.forEach((p) => {
                    this.acessoAgua.push({y: p.totalPessoasAceesoAgua, name: this.getMonth(p.mes)});
                });

                this.iniciarChartPieSistemas();
                this.iniciarChartPieCasasLigadas();
                this.iniciarChartPieCasasAcessoAgua();
            });
    }

    buscaDadosIndicadores() {
        this.relatorioService.buscaDadosInqueritos().subscribe(
            (res: HttpResponse<DashboardPrincipalDados[]>) => {
                this.listaIndicadores = res.body;

                this.listaCaptada = Array<any>();
                this.listaTratada = Array<any>();
                this.listaDistribuida = Array<any>();

                this.listaIndicadores.forEach((p) => {
                    const i: DadosRelatorio = new DadosRelatorio();
                    i.label = this.getMonth(p.mes);
                    i.y = p.totalAguaCaptada;
                    this.listaCaptada.push(i);

                    this.totalInqueritos += p.totalIndicadores;
                    this.totalCasasLigadas += p.totalCasasLigadas;
                    this.totalAcessoAgua += p.totalPessoasAceesoAgua;
                });

                this.listaIndicadores.forEach((p) => {
                    const i: DadosRelatorio = new DadosRelatorio();
                    i.label = this.getMonth(p.mes);
                    i.y = p.totalAguaTratada;
                    this.listaTratada.push(i);
                });

                this.listaIndicadores.forEach((p) => {
                    const i: DadosRelatorio = new DadosRelatorio();
                    i.label = this.getMonth(p.mes);
                    i.y = p.totalVolumeAguaDistribuida;
                    this.listaDistribuida.push(i);
                });

                this.iniciarChartTratamentoAgua();
            });
    }

    iniciarChartPieCasasAcessoAgua() {
        this.chartPie1 = new CanvasJS.Chart('chartPieAcesso', {
            theme: 'light2',
            animationEnabled: true,
            exportEnabled: false,
            title: {
                text: 'Sistemas de Água- Acesso a Água'
            },
            data: [{
                type: 'pie',
                showInLegend: true,
                toolTipContent: '<b>{name}</b>: ${y} (#percent%)',
                indexLabel: '{name} - {y}',
                dataPoints: this.acessoAgua
            }]
        });

        this.chartPie1.render();
    }

    iniciarChartPieCasasLigadas() {
        this.chartPie2 = new CanvasJS.Chart('chartPieCasas', {
            theme: 'light2',
            animationEnabled: true,
            exportEnabled: false,
            title: {
                text: 'Sistemas de Água - Casas Ligadas'
            },
            data: [{
                type: 'pie',
                showInLegend: true,
                toolTipContent: '<b>{name}</b>: ${y} (#percent%)',
                indexLabel: '{name} - {y}',
                dataPoints: this.casasLigadas
            }]
        });

        this.chartPie2.render();
    }

    iniciarChartPieSistemas() {
        console.log(this.sistemasExixtentes);
        this.chartPie3 = new CanvasJS.Chart('chartPieSistemas', {
            theme: 'light2',
            animationEnabled: true,
            exportEnabled: false,
            title: {
                text: 'Sistemas de Água - Sistemas Existentes'
            },
            data: [{
                type: 'pie',
                showInLegend: true,
                toolTipContent: '<b>{name}</b>: ${y} (#percent%)',
                indexLabel: '{name} - {y}',
                indexLabelPlacement: 'outside',
                dataPoints: this.sistemasExixtentes
            }]
        });

        this.chartPie3.render();
    }

    iniciarChartTratamentoAgua() {
        this.chart = new CanvasJS.Chart('chartAgua', {
            animationEnabled: true,
            theme: 'light2',
            title: {
                text: 'Inquéritos / Inventários'
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
                    name: 'Água Captada',
                    showInLegend: true,
                    xValueFormatString: 'string',
                    yValueFormatString: '#',
                    dataPoints: this.listaCaptada
                },
                {
                    type: 'column',
                    name: 'Agua Tratada',
                    showInLegend: true,
                    xValueFormatString: 'string',
                    yValueFormatString: '#',
                    dataPoints: this.listaTratada
                },
                {
                    type: 'column',
                    name: 'Água Distribuida',
                    showInLegend: true,
                    xValueFormatString: 'string',
                    yValueFormatString: '#',
                    dataPoints: this.listaDistribuida
                }]
        });
        this.chart.render();
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

    public captureScreen(elementId) {
        const data = document.getElementById(elementId);
        (html2canvas as any)(data).then((canvas) => {
            const imgWidth = 204;
            const pageHeight = 295;
            let imgHeight = canvas.height * imgWidth / canvas.width;
            if (imgHeight > pageHeight) {
                imgHeight = pageHeight - 4;
            }
            const contentDataURL = canvas.toDataURL('image/png');
            const pdf = new jsPDF('p', 'mm', 'a4');
            pdf.text('Estatística de Inquéritos Preenchidos', 55, 5);
            pdf.addImage(contentDataURL, 'PNG', 3, 8, imgWidth, imgHeight);
            pdf.save('dashboard.pdf');
        }).catch(function(error) {
            // Error Handling
        });
    }

    exportTable(tabeId) {
        TableUtil.exportToExcel(tabeId);
    }

    getMonth(valor: number) {
        if (valor === 1) {
            return 'Janeiro';
        } else if (valor === 2) {
            return 'Fevereiro';
        } else if (valor === 3) {
            return 'Março';
        } else if (valor === 4) {
            return 'Abril';
        } else if (valor === 5) {
            return 'Maio';
        } else if (valor === 6) {
            return 'Junho';
        } else if (valor === 7) {
            return 'Julho';
        } else if (valor === 8) {
            return 'Agosto';
        } else if (valor === 9) {
            return 'Setembro';
        } else if (valor === 10) {
            return 'Outubro';
        } else if (valor === 11) {
            return 'Novembro';
        } else if (valor === 22) {
            return 'Dezembro';
        }

        return valor;
    }
}
