import { Component, OnInit } from '@angular/core';
import { JhiAlertService } from 'ng-jhipster';
import { UserService } from '../../shared/user/user.service';
import { HttpResponse } from '@angular/common/http';
import { User } from '../../shared/user/user.model';
import { Principal } from '../../shared/auth/principal.service';
import {RelatoriosService} from '../relatorios.service';
import {BeneficiariosBmbEnergia} from './beneficiarios-bmb-energia.model';
import * as jsPDF from 'jspdf';
import {TableUtil} from '../../shared/util/tableUtil';
import * as html2canvas from 'html2canvas';
import {DadosRelatorio} from '../cobertura-sector-agua-provincial/dadosRelatorio.model';

@Component({
    selector: 'jhi-benef-bmb-energia',
    templateUrl: './beneficiarios-bmb-energia.component.html',
    styleUrls: [
        'beneficiarios-bmb-energia.css'
    ]
})

export class BeneficiariosBombEnergiaComponent implements OnInit {

    user: User;
    listaTabela: BeneficiariosBmbEnergia[];
    tipoRelatorio: string;
    predicate: any;
    reverse: any;
    chart: any;

    totalPopulacao = 0;
    totalPocoMelhorado = 0;
    totalFuro = 0;
    totalNascente = 0;

    totalDieselSistemas = 0;
    totalDieselPopulacao = 0;
    totalDieselPerc = 0;

    totalSolarSistemas = 0;
    totalSolarPopulacao = 0;
    totalSolarPerc = 0;

    totalEolicaSistemas = 0;
    totalEolicaPopulacao = 0;
    totalEolicaPerc = 0;

    totalElectraSistemas = 0;
    totalElectraPopulacao = 0;
    totalElectraPerc = 0;

    totalOutroSistemas = 0;
    totalOutroPopulacao = 0;
    totalOutroPerc = 0;

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
        if (this.tipoRelatorio === 'Nível Provincial') {
            this.buscaDadosTabelaProvincial();
        }
/*        if (this.tipoRelatorio === 'Nível Municipal') {
            this.buscaDadosTabelaMunicipal();
        }*/
        if (this.tipoRelatorio === 'Nível Comunal') {
            this.buscaDadosTabelaComunal();
        }
    }

    voltarEscolha() {
        this.tipoRelatorio = null;
    }

    buscaDadosTabelaProvincial() {
        this.relatorioService.buscaDadosBeneficiariosBmbEnergiaProvincial().subscribe(
            (res: HttpResponse<BeneficiariosBmbEnergia[]>) => {
                this.listaTabela = res.body;
                console.log(this.listaTabela);

                this.listaTabela.forEach( (i) => {
                    const item: DadosRelatorio = new DadosRelatorio();
                });
            });
    }

    buscaDadosTabelaComunal() {
        this.relatorioService.buscaDadosBeneficiariosBmbEnergiaComunal().subscribe(
            (res: HttpResponse<BeneficiariosBmbEnergia[]>) => {
                this.listaTabela = res.body;
                console.log(this.listaTabela);

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