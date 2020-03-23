import { Component, OnInit } from '@angular/core';
import { JhiAlertService } from 'ng-jhipster';
import { UserService } from '../../shared/user/user.service';
import { HttpResponse } from '@angular/common/http';
import { User } from '../../shared/user/user.model';
import { Principal } from '../../shared/auth/principal.service';
import {RelatoriosService} from '../relatorios.service';
import * as jsPDF from 'jspdf';
import {TableUtil} from '../../shared/util/tableUtil';
import * as html2canvas from 'html2canvas';
import {BeneAguaFtSubterraneaTpBombaManual} from './beneAguaFtSubterraneaTpBomba.model';

@Component({
    selector: 'jhi-trat-sist-agua',
    templateUrl: './tratamento-sistemas-agua.component.html',
    styleUrls: [
        'tratamento-sistemas-agua.css'
    ]
})

export class TratamentoSistemasAguaComponent implements OnInit {

    user: User;
    listaTabela: BeneAguaFtSubterraneaTpBombaManual[];
    predicate: any;
    reverse: any;
    chart: any;

    totalPopulacao = 0;
    totalPocoMelhorado = 0;
    totalFuro = 0;
    totalNascente = 0;
    totalAfridev = 0;
    totalAfridevPopulacao = 0;
    totalVergnet = 0;
    totalVergnetPopulacao = 0;
    totalVolanta = 0;
    totalVolantaPopulacao = 0;
    totalIndiaMarc = 0;
    totalIndiaMarcPopulacao = 0;
    totalOutro = 0;
    totalOutroPopulacao = 0;

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
            pdf.save('tratamentoSistemasAguas.pdf');
        }).catch(function(error) {
            // Error Handling
        });
    }

    exportTable(tabeId) {
        TableUtil.exportToExcel(tabeId);
    }

    buscaDadosTabela() {
        this.relatorioService.buscaDadosBenfAguaSubtTipoBombaManualPro().subscribe(
            (res: HttpResponse<BeneAguaFtSubterraneaTpBombaManual[]>) => {
                this.listaTabela = res.body;
                this.listaTabela.forEach((i) => {
                    this.totalPopulacao += i.populacao;
                    this.totalPocoMelhorado += i.numeroPocoMelhorado;
                    this.totalFuro += i.furo;
                    this.totalNascente += i.nascente;
                    this.totalAfridev += i.afridev;
                    this.totalAfridevPopulacao += i.afridevPopulacao;
                    this.totalVergnet += i.vergnet;
                    this.totalVergnetPopulacao += i.vergnetPopulacao;
                    this.totalVolanta += i.volanta;
                    this.totalVolantaPopulacao += i.volantaPopulacao;
                    this.totalIndiaMarc += i.indiaMarc;
                    this.totalIndiaMarcPopulacao += i.indiaMarcPopulacao;
                    this.totalOutro += i.outro;
                    this.totalOutroPopulacao += i.outroPopulacao;
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

}
