import { Component, OnInit } from '@angular/core';
import { JhiAlertService } from 'ng-jhipster';
import { UserService } from '../../shared/user/user.service';
import { HttpResponse } from '@angular/common/http';
import { User } from '../../shared/user/user.model';
import { Principal } from '../../shared/auth/principal.service';
import {RelatoriosService} from '../relatorios.service';
import {BeneficiariosBmbEnergiaProvincial} from './beneficiarios-bmb-energia-provincial.model';
import * as jsPDF from 'jspdf';
import {TableUtil} from '../../shared/util/tableUtil';
import * as html2canvas from 'html2canvas';

@Component({
    selector: 'jhi-benef-bmb-energia-provincial',
    templateUrl: './beneficiarios-bmb-energia-provincial.component.html',
    styleUrls: [
        'beneficiarios-bmb-energia-provincial.css'
    ]
})

export class BeneficiariosBombEnergiaProvincialComponent implements OnInit {

    user: User;
    listaTabela: BeneficiariosBmbEnergiaProvincial[];
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

        this.buscaDadosTabelaProvincial();
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

    buscaDadosTabelaProvincial() {
        this.relatorioService.buscaDadosBeneficiariosBmbEnergiaProvincial().subscribe(
            (res: HttpResponse<BeneficiariosBmbEnergiaProvincial[]>) => {
                this.listaTabela = res.body;
                console.log(this.listaTabela);

                this.listaTabela.forEach( (i) => {
                    this.totalPopulacao += i.populacao;
                    this.totalPocoMelhorado += i.pocoMelhorado;
                    this.totalFuro += i.furo;
                    this.totalNascente += i.nascente;
                    this.totalDieselSistemas += i.dieselSistemas;
                    this.totalDieselPopulacao += i.dieselPopulacao;
                    this.totalDieselPerc += i.dieselPerc;
                    this.totalSolarSistemas += i.solarSistemas;
                    this.totalSolarPopulacao += i.solarPopulacao;
                    this.totalSolarPerc += i.solarPerc;
                    this.totalEolicaSistemas += i.eolicaSistemas;
                    this.totalEolicaPopulacao += i.eolicaPopulacao;
                    this.totalEolicaPerc += i.eolicaPerc;
                    this.totalElectraSistemas += i.electricaSistemas;
                    this.totalElectraPopulacao += i.electricaPopulacao;
                    this.totalElectraPerc += i.electricaPerc;
                    this.totalOutroSistemas += i.outroSistemas;
                    this.totalOutroPopulacao += i.outroPopulacao;
                    this.totalOutroPerc += i.outroPerc;
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
