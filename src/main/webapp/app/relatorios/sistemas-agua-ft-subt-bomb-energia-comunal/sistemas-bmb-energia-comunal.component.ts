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
import {SistemasFtSubtBmbEnergia} from './sistemas-bmb-energia-comunal.model';

@Component({
    selector: 'jhi-benef-bmb-energia',
    templateUrl: './sistemas-bmb-energia-comunal.component.html',
    styleUrls: [
        'sistemas-bmb-energia-comunal.css'
    ]
})

export class BeneficiariosBombEnergiaComunalComponent implements OnInit {

    user: User;
    listaTabela: SistemasFtSubtBmbEnergia[];
    tipoRelatorio: string;
    predicate: any;
    reverse: any;
    chart: any;

    totalPocoMelhorado = 0;
    totalFuro = 0;
    totalNascente = 0;
    totalsistemas = 0;

    totalDieselSistemas = 0;
    totalDieselFunciona = 0;
    totalDieselNaoFunciona = 0;

    totalSolarSistemas = 0;
    totalSolarFunciona = 0;
    totalSolarNaoFunciona = 0;

    totalEolicaSistemas = 0;
    totalEolicaFunciona = 0;
    totalEolicaNaoFunciona = 0;

    totalElectraSistemas = 0;
    totalElectraFunciona = 0;
    totalElectraNaoFunciona = 0;

    totalOutroSistemas = 0;
    totalOutroFunciona = 0;
    totalOutroNaoFunciona = 0;

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

        this.buscaDadosTabelaComunal();
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

    buscaDadosTabelaComunal() {
        this.relatorioService.buscaDadosSistemasFtSubtBmbEnergiaComunal().subscribe(
            (res: HttpResponse<SistemasFtSubtBmbEnergia[]>) => {
                this.listaTabela = res.body;
                console.log(this.listaTabela);

                this.listaTabela.forEach( (i) => {
                    this.totalsistemas += i.totalSistemas;
                    this.totalPocoMelhorado += i.pocoMelhorado;
                    this.totalFuro += i.furo;
                    this.totalNascente += i.nascente;
                    this.totalDieselSistemas += i.dieselSistemas;
                    this.totalDieselFunciona += i.dieselSistemaFunciona;
                    this.totalDieselNaoFunciona += i.dieselSistemaNaoFunciona;
                    this.totalSolarSistemas += i.solarSistemas;
                    this.totalSolarFunciona += i.solarSistemaFunciona;
                    this.totalSolarNaoFunciona += i.solarSistemaNaoFunciona;
                    this.totalEolicaSistemas += i.eolicaSistemas;
                    this.totalEolicaFunciona += i.eolicaSistemaFunciona;
                    this.totalEolicaNaoFunciona += i.eolicaSistemaNaoFunciona;
                    this.totalElectraSistemas += i.electricaSistemas;
                    this.totalElectraFunciona += i.electricaSistemaFunciona;
                    this.totalElectraNaoFunciona += i.electricaSistemaNaoFunciona;
                    this.totalOutroSistemas += i.outroSistemas;
                    this.totalOutroFunciona += i.outroSistemaFunciona;
                    this.totalOutroNaoFunciona += i.outroSistemaNaoFunciona;
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
