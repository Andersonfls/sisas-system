import { Component, OnInit } from '@angular/core';
import { JhiAlertService } from 'ng-jhipster';
import { UserService } from '../../shared/user/user.service';
import { HttpResponse } from '@angular/common/http';
import { User } from '../../shared/user/user.model';
import { Principal } from '../../shared/auth/principal.service';
import {RelatoriosService} from '../relatorios.service';
import {BeneficiariosBmbMecanica} from './beneficiarios-bmb-mecanica.model';
import * as jsPDF from 'jspdf';
import {TableUtil} from '../../shared/util/tableUtil';
import * as html2canvas from 'html2canvas';

@Component({
    selector: 'jhi-benef-opt-tecnica',
    templateUrl: './beneficiarios-bmb-mecanica.component.html',
    styleUrls: [
        'beneficiarios-bmb-mecanica.css'
    ]
})

export class BeneficiariosTpBombaComponent implements OnInit {

    user: User;
    listaTabela: BeneficiariosBmbMecanica[];
    tipoRelatorio: string;
    predicate: any;
    reverse: any;
    chart: any;

    totalPopulacao = 0;
    totalPoco = 0;
    totalFuro = 0;
    totalNascente = 0;
    totalDieselSistema = 0;
    totalDieselPopulacao = 0;
    totalSolarSistema = 0;
    totalSolarPopulacao = 0;
    totalEolicaSistema = 0;
    totalEolicaPopulacao = 0;
    totalElectricaSistema = 0;
    totalElectricaPopulacao = 0;
    totalOutroSistema = 0;
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
            const pdf = new jsPDF('l', 'mm', 'a4');
            pdf.text('Beneficiários de Água por Fonte Subterrânea e por Bomba Mecanica (Nível Municipal)', 38, 7);
            pdf.addImage(contentDataURL, 'PNG', 40, 13, imgWidth, imgHeight);
            pdf.save('relatorio-sisas.pdf');
        }).catch(function(error) {
            // Error Handling
        });
    }

    exportTable(tabeId) {
        TableUtil.exportToExcel(tabeId);
    }

    buscaDadosTabela() {
        this.relatorioService.buscaDadosBenFtSubtBbmMecanicaMunicipal().subscribe(
            (res: HttpResponse<BeneficiariosBmbMecanica[]>) => {
                this.listaTabela = res.body;
                console.log(this.listaTabela);

                this.listaTabela.forEach((i) => {
                    this.totalPopulacao += i.populacao;
                    this.totalPoco += i.pocoMelhorado;
                    this.totalFuro += i.furo;
                    this.totalNascente += i.nascente;
                    this.totalDieselSistema += i.dieselSistemas;
                    this.totalDieselPopulacao += i.dieselPopulacao;
                    this.totalSolarSistema += i.solarSistemas;
                    this.totalSolarPopulacao += i.solarPopulacao;
                    this.totalEolicaSistema += i.eolicaSistemas;
                    this.totalEolicaPopulacao += i.eolicaPopulacao;
                    this.totalElectricaSistema += i.electricaSistemas;
                    this.totalElectricaPopulacao += i.electricaPopulacao;
                    this.totalOutroSistema += i.outroSistemas;
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
