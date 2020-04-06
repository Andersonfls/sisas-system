import {Component, OnInit} from '@angular/core';
import {JhiAlertService} from 'ng-jhipster';
import {UserService} from '../../shared/user/user.service';
import {HttpResponse} from '@angular/common/http';
import {User} from '../../shared/user/user.model';
import {Principal} from '../../shared/auth/principal.service';
import {RelatoriosService} from '../relatorios.service';
import * as jsPDF from 'jspdf';
import {TableUtil} from '../../shared/util/tableUtil';
import * as html2canvas from 'html2canvas';
import {SistemaAguaSprOpcaoTecnicaComunal} from './sistema-agua-supr-opcao-tecnica-comunal.model';

@Component({
    selector: 'jhi-sistema-agua-super-opcao-tecnica-comunal',
    templateUrl: './sistema-agua-supr-opcao-tecnica-comunal.component.html',
    styleUrls: [
        'sistema-agua-supr-opcao-tecnica-comunal.css'
    ]
})

export class SistemaAguaSupeOpcaoTecnicaComunalComponent implements OnInit {

    user: User;
    listaTabela: SistemaAguaSprOpcaoTecnicaComunal[];
    tipoRelatorio: string;
    predicate: any;
    reverse: any;
    chart: any;

    totalSistemaTotal = 0;

    totalElectricaTotalSistema = 0;
    totalElectricaSistemaFunciona = 0;
    totalElectricaSistemaNaoFunciona = 0;

    totalDieselSistemaFunciona = 0;
    totalDieselSistemaNaoFunciona = 0;
    totalDieselTotalSistema = 0;

    totalGravidadeTotalSistema = 0;
    totalGravidadeSistemaFunciona = 0;
    totalGravidadeSistemaNaoFunciona = 0;

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
            pdf.text('Sistema de Água por fonte Superficial e por Opção Técnica (Nível Comunal)', 5, 7);
            pdf.addImage(contentDataURL, 'PNG', 2, 9, imgWidth, imgHeight);
            pdf.save('relatorio-sisas.pdf');
        }).catch(function(error) {
            // Error Handling
        });
    }

    exportTable(tabeId) {
        TableUtil.exportToExcel(tabeId);
    }

    buscaDadosTabelaComunal() {
        this.relatorioService.buscaDadosSistemaAguaSprOpcaoTecnicaComunal().subscribe(
            (res: HttpResponse<SistemaAguaSprOpcaoTecnicaComunal[]>) => {
                this.listaTabela = res.body;
                console.log(this.listaTabela);

                this.listaTabela.forEach( (i) => {
                    this.totalSistemaTotal += i.totalSistemas;
                    this.totalElectricaTotalSistema += i.electricaTotalSistema;
                    this.totalElectricaSistemaFunciona += i.electricaSistemaFunciona;
                    this.totalElectricaSistemaNaoFunciona += i.electricaSistemaNaoFunciona;
                    this.totalDieselTotalSistema += i.dieselTotalSistema;
                    this.totalDieselSistemaFunciona += i.dieselSistemaFunciona;
                    this.totalDieselSistemaNaoFunciona += i.dieselSistemaNaoFunciona;
                    this.totalGravidadeTotalSistema += i.gravidadeTotalSistema;
                    this.totalGravidadeSistemaFunciona += i.gravidadeSistemaFunciona;
                    this.totalGravidadeSistemaNaoFunciona += i.gravidadeSistemaNaoFunciona;
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
