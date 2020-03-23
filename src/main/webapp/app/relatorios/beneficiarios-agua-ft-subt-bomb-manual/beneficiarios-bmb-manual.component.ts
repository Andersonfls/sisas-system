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
import {DadosRelatorio} from '../cobertura-sector-agua-provincial/dadosRelatorio.model';
import {BeneficiariosBmbManual} from './beneficiarios-bmb-manual.model';

@Component({
    selector: 'jhi-benef-opt-tecnica',
    templateUrl: './beneficiarios-bmb-manual.component.html',
    styleUrls: [
        'beneficiarios-bmb-manual.css'
    ]
})

export class BeneficiariosBombManualComponent implements OnInit {

    user: User;
    listaTabela: BeneficiariosBmbManual[];
    tipoRelatorio: string;
    predicate: any;
    reverse: any;
    chart: any;
    listaFuncionam: DadosRelatorio[];
    listaNaoFuncionam: DadosRelatorio[];
    listaNumSistemas: DadosRelatorio[];

    totalPopulacao = 0;
    totalPocoMelhorado = 0;
    totalFuro = 0;
    totalNascente = 0;
    totalSistemaTotal = 0;

    totalAfridevTotalSistema = 0;
    totalAfridevSistemaFunciona = 0;
    totalAfridevSistemaNaoFunciona = 0;

    totalVergnetAfridevSistemaFunciona = 0;
    totalVergnetAfridevSistemaNaoFunciona = 0;
    totalVergnetAfridevTotalSistema = 0;

    totalVolantaTotalSistema = 0;
    totalVolantaSistemaFunciona = 0;
    totalVolantaSistemaNaoFunciona = 0;

    totalIndiaTotalSistema = 0;
    totalIndiaSistemaFunciona = 0;
    totalIndiaSistemaNaoFunciona = 0;

    totalOutroTotalSistema = 0;
    totalOutroSistemaFunciona = 0;
    totalOutroSistemaNaoFunciona = 0;

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
/*        if (this.tipoRelatorio === 'Nível Provincial') {
            this.buscaDadosTabelaProvincial();
        }
        if (this.tipoRelatorio === 'Nível Municipal') {
            this.buscaDadosTabelaMunicipal();
        }*/
        if (this.tipoRelatorio === 'Nível Comunal') {
            this.buscaDadosTabelaComunal();
        }
    }

    voltarEscolha() {
        this.tipoRelatorio = null;
    }

    buscaDadosTabelaComunal() {
        this.relatorioService.buscaDadosBeneficiariosBmbManualComunal().subscribe(
            (res: HttpResponse<BeneficiariosBmbManual[]>) => {
                this.listaTabela = res.body;
                console.log(this.listaTabela);

                this.listaNaoFuncionam = new Array();
                this.listaFuncionam = new Array();
                this.listaNumSistemas = new Array();

                this.listaTabela.forEach( (i) => {
                    const item: DadosRelatorio = new DadosRelatorio();
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
