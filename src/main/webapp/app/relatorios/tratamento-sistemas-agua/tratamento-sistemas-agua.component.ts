import { Component, OnInit } from '@angular/core';
import { JhiAlertService } from 'ng-jhipster';
import { UserService } from '../../shared/user/user.service';
import { HttpResponse } from '@angular/common/http';
import { User } from '../../shared/user/user.model';
import { Principal } from '../../shared/auth/principal.service';
import * as CanvasJS from '../../../content/js/canvasjs.min.js';
import {TratamentoSistemaAguaDados} from './tratamentoSistemasAguaDados.model';
import {DadosRelatorio} from '../cobertura-sector-agua/dadosRelatorio.model';
import {RelatoriosService} from '../relatorios.service';
import * as jsPDF from 'jspdf';
import {TableUtil} from '../../shared/util/tableUtil';
import * as html2canvas from 'html2canvas';

@Component({
    selector: 'jhi-trat-sist-agua',
    templateUrl: './tratamento-sistemas-agua.component.html',
    styleUrls: [
        'tratamento-sistemas-agua.css'
    ]
})

export class TratamentoSistemasAguaComponent implements OnInit {

    user: User;
    listaTabela: TratamentoSistemaAguaDados[];
    predicate: any;
    reverse: any;
    chart: any;
    listaPadrao: DadosRelatorio[];
    listaBasico: DadosRelatorio[];
    listaNaoRealiza: DadosRelatorio[];

    totalSistemas = 0;
    totalPadrao = 0;
    totalBasico = 0;
    totalNaoRealiza = 0;
    totalOutros = 0;

    constructor(
        private jhiAlertService: JhiAlertService,
        private userService: UserService,
        private principal: Principal,
        private relatorioService: RelatoriosService,
    ) {}

    ngOnInit() {
        this.principal.identity().then((userIdentity) => {
            this.user = userIdentity;
        });
        this.buscaDadosTabela();
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

    buscaDadosTabela() {
        this.relatorioService.buscaDadosTratamentoSistemasAgua().subscribe(
            (res: HttpResponse<TratamentoSistemaAguaDados[]>) => {
                this.listaTabela = res.body;

                this.listaPadrao = Array<any>();
                this.listaBasico = Array<any>();
                this.listaNaoRealiza = Array<any>();

                this.listaTabela.forEach((p) => {
                    let item: DadosRelatorio = new DadosRelatorio();
                    item.label = p.nomeProvincia;
                    item.y = p.padrao;
                    this.listaPadrao.push(item);

                    item = new DadosRelatorio();
                    item.label = p.nomeProvincia;
                    item.y = p.basico;
                    this.listaBasico.push(item);

                    item = new DadosRelatorio();
                    item.label = p.nomeProvincia;
                    item.y = p.naoRealiza;
                    this.listaNaoRealiza.push(item);

                    this.totalSistemas += p.sistemasAgua;
                    this.totalPadrao += p.padrao;
                    this.totalBasico += p.basico;
                    this.totalNaoRealiza += p.naoRealiza;
                    this.totalOutros += p.outros;
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

    iniciarChart() {
        this.chart = new CanvasJS.Chart('chart', {
            animationEnabled: true,
            theme: 'light2',
            title: {
                text: 'Tratamento de Sistemas de Água'
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
                    name: 'PADRÃO',
                    showInLegend: true,
                    xValueFormatString: 'string',
                    yValueFormatString: '#',
                    dataPoints: this.listaPadrao
                },
                {
                    type: 'column',
                    name: 'BÁSICO',
                    showInLegend: true,
                    xValueFormatString: 'string',
                    yValueFormatString: '#',
                    dataPoints: this.listaBasico
                },
                {
                    type: 'column',
                    name: 'NÃO REALIZA',
                    showInLegend: true,
                    xValueFormatString: 'string',
                    yValueFormatString: '#',
                    dataPoints: this.listaNaoRealiza
                }]
        });
        this.chart.render();
    }

}
