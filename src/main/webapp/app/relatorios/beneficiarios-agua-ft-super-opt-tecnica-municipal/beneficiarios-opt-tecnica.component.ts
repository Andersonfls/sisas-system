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
import {BeneficiariosOptTecnica} from '../beneficiarios-agua-ft-super-opt-tecnica-comunal/BeneficiariosOptTecnica.model';

@Component({
    selector: 'jhi-benef-opt-tecnica',
    templateUrl: './beneficiarios-opt-tecnica.component.html',
    styleUrls: [
        'beneficiarios-opt-tecnica.css'
    ]
})

export class BeneficiariosOptTecnicaComponent implements OnInit {

    user: User;
    listaTabela: BeneficiariosOptTecnica[];
    predicate: any;
    reverse: any;
    chart: any;

    totalPopulacao = 0;
    totalElectraSistemas = 0;
    totalElectraPopulacao = 0;
    totalDieselSistemas = 0;
    totalDieselPopulacao = 0;
    totalGravidadeSistemas = 0;
    totalGravidadePopulacao = 0;

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
            pdf.text('Beneficiários de Água por Fonte Superficial e por Opcão Técnica (Nível Municipal)', 2, 7);
            pdf.addImage(contentDataURL, 'PNG', 2, 13, imgWidth, imgHeight);
            pdf.save('relatorio-sisas.pdf');
        }).catch(function(error) {
            // Error Handling
        });
    }

    exportTable(tabeId) {
        TableUtil.exportToExcel(tabeId);
    }

    buscaDadosTabela() {
        this.relatorioService.buscaDadosBenOptTecnicaMunicipal().subscribe(
            (res: HttpResponse<BeneficiariosOptTecnica[]>) => {
                this.listaTabela = res.body;

                this.listaTabela.forEach( (i) => {
                    this.totalPopulacao += i.populacao;
                    this.totalElectraSistemas += i.electricaSistemas;
                    this.totalElectraPopulacao += i.electricaPopulacao;
                    this.totalDieselSistemas += i.dieselSistemas;
                    this.totalDieselPopulacao += i.dieselPopulacao;
                    this.totalGravidadeSistemas += i.gravidadeSistemas;
                    this.totalGravidadePopulacao += i.gravidadePopulacao;

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
