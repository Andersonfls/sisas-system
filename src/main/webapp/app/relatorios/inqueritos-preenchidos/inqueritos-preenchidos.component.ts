import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
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
import * as jsPDF from 'jspdf';
import {TableUtil} from '../../shared/util/tableUtil';
import * as html2canvas from 'html2canvas';

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
    listaTotalInqueritos: DadosRelatorio[];
    tipoRelatorio: string;

    totalMunicipios = 0;
    totalComuna = 0;
    totalAguasSim = 0;
    totalAguasNao = 0;
    totalAguas = 0;
    totalInqueritosSector = 0;
    total = 0;
    totalGeral = 0;

    name = 'Angular Html To Pdf ';
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
        this.buscaDadosTabelaModelo();
    }

    validaTipoRelatorio() {
        if (this.tipoRelatorio === 'Modelo 2') {
            this.buscaDadosTabelaModelo();
        }
    }

    public captureScreen(elementId) {
        const data = document.getElementById(elementId);
        (html2canvas as any)(data).then((canvas) => {
            const imgWidth = 208;
            const pageHeight = 295;
            const imgHeight = canvas.height * imgWidth / canvas.width;
            const heightLeft = imgHeight;
            const contentDataURL = canvas.toDataURL('image/png');
            const pdf = new jsPDF('p', 'mm', 'a4');
            const position = 0;
            pdf.addImage(contentDataURL, 'PNG', 0, position, imgWidth, imgHeight);
            pdf.save('relatorio-sisas.pdf');
        }).catch(function(error) {
            // Error Handling
        });
    }

    exportTable(tabeId) {
        TableUtil.exportToExcel(tabeId);
    }

    buscaDadosTabelaModelo() {
        this.relatorioService.buscaDadosInqueritosPreenchidos().subscribe(
            (res: HttpResponse<InqueritosPreenchidosDados[]>) => {
                this.listaTabela = res.body;
                this.listaTotalInqueritos = Array<any>();

                this.listaAguas = Array<any>();
                this.listaAguasNao = Array<any>();

                this.listaTabela.forEach((p) => {
                    const item: DadosRelatorio = new DadosRelatorio();
                    item.label = p.nomeProvincia;
                    item.y = p.aguasSim;
                    this.listaAguas.push(item);
                });

                this.listaTabela.forEach((p) => {
                    const item: DadosRelatorio = new DadosRelatorio();
                    item.label = p.nomeProvincia;
                    item.y = p.aguasNao;
                    this.listaAguasNao.push(item);
                });

                this.listaTabela.forEach((p) => {
                    this.totalMunicipios += p.municipios;
                    this.totalComuna += p.comunas;
                    this.totalAguas += p.totalAguas;
                    this.totalAguasSim += p.aguasSim;
                    this.totalAguasNao += p.aguasNao;
                    this.totalInqueritosSector += p.totalInqueritoSector;
                });

                let item: DadosRelatorio = new DadosRelatorio();
                item.label = 'Águas SIM';
                item.y = this.totalAguasSim;
                this.listaTotalInqueritos.push(item);

                item = new DadosRelatorio();
                item.label = 'Águas NAO';
                item.y = this.totalAguasNao;
                this.listaTotalInqueritos.push(item);

                this.iniciarChartTotalInqueritos();
                this.iniciarChartAgua();
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
