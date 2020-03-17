import { Component, OnInit } from '@angular/core';
import { JhiAlertService } from 'ng-jhipster';
import { UserService } from '../../shared/user/user.service';
import { HttpResponse } from '@angular/common/http';
import { User } from '../../shared/user/user.model';
import { Principal } from '../../shared/auth/principal.service';
import * as CanvasJS from '../../../content/js/canvasjs.min.js';
import {Provincia} from '../../entities/provincia';
import {DadosRelatorio} from './dadosRelatorio.model';
import {SectorAguaDados} from './SectorAguaDados.model';
import {RelatoriosService} from '../relatorios.service';
import * as jsPDF from 'jspdf';
import {TableUtil} from '../../shared/util/tableUtil';
import * as html2canvas from 'html2canvas';

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
    tipoRelatorio: string;
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
        private relatorioService: RelatoriosService,
    ) {}

    ngOnInit() {
        this.principal.identity().then((userIdentity) => {
            this.user = userIdentity;
        });
        this.totalCobertura = 35;
        this.tipoRelatorio = null;
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

    validaTipoRelatorio() {
        if (this.tipoRelatorio === 'Nível Provincial') {
            this.buscaDadosTabelaProvincia();
        }
        if (this.tipoRelatorio === 'Nível Municipal') {
            this.buscaDadosTabelaMunicipio();
        }
        if (this.tipoRelatorio === 'Nível Comunal') {
            this.buscaDadosTabelaComuna();
        }
    }

    voltarEscolha() {
        this.tipoRelatorio = null;
    }

    buscaDadosTabelaAmbito() {
        this.relatorioService.buscaDadosSectorAguaAmbito().subscribe(
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

    buscaDadosTabelaProvincia() {
        this.relatorioService.buscaDadosSectorAguaProvincia().subscribe(
            (res: HttpResponse<SectorAguaDados[]>) => {
                this.listaTabela = res.body;
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

    buscaDadosTabelaMunicipio() {
        this.relatorioService.buscaDadosSectorAguaMunicipio().subscribe(
            (res: HttpResponse<SectorAguaDados[]>) => {
                this.listaTabela = res.body;
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

    buscaDadosTabelaComuna() {
        this.relatorioService.buscaDadosSectorAguaComuna().subscribe(
            (res: HttpResponse<SectorAguaDados[]>) => {
                this.listaTabela = res.body;
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

}
