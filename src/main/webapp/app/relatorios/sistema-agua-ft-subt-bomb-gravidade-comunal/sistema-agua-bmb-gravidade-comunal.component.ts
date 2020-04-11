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
import {SistemaAguaBmbGravidadeComunal} from './sistema-agua-bmb-gravidade-comunal.model';

@Component({
    selector: 'jhi-sistema-agua-bomba-gravidade-comunal',
    templateUrl: './sistema-agua-bmb-gravidade-comunal.component.html',
    styleUrls: [
        'sistema-agua-bmb-gravidade-comunal.css'
    ]
})

export class SistemaAguaBombGravidadeComunalComponent implements OnInit {

    user: User;
    listaTabela: SistemaAguaBmbGravidadeComunal[];
    tipoRelatorio: string;
    predicate: any;
    reverse: any;
    chart: any;

    totalPocoMelhorado = 0;
    totalFuro = 0;
    totalNascente = 0;
    totalSistemaTotal = 0;

    totalGravidadeTotalSistema = 0;
    totalGravidadeSistemaFunciona = 0;
    totalGravidadeSistemaNaoFunciona = 0;

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

        this.buscaDadosTabelaComunal();
    }

    public captureScreen(elementId) {
        const data = document.getElementById(elementId);
        (html2canvas as any)(data).then((canvas) => {
            const imgWidth = 207;
            const imgHeight = canvas.height * imgWidth / canvas.width;
            const contentDataURL = canvas.toDataURL('image/png');
            const pdf = new jsPDF('p', 'mm', 'a4');
            pdf.text('Sistema de Água por fonte Subterrânea e por Bomba Gravidade/Outros(Comunal)', 2, 7);
            pdf.addImage(contentDataURL, 'PNG', 1, 9, imgWidth, (imgHeight - 10));
            pdf.save('relatorio-sisas.pdf');
        }).catch(function(error) {
            // Error Handling
        });
    }

    exportTable(tabeId) {
        TableUtil.exportToExcel(tabeId);
    }

    buscaDadosTabelaComunal() {
        this.relatorioService.buscaDadosSistemaAguaBmbGravidadeComunal().subscribe(
            (res: HttpResponse<SistemaAguaBmbGravidadeComunal[]>) => {
                this.listaTabela = res.body;
                console.log(this.listaTabela);

                this.listaTabela.forEach( (i) => {
                    this.totalPocoMelhorado += i.pocoMelhorado;
                    this.totalFuro += i.furo;
                    this.totalNascente += i.nascente;
                    this.totalSistemaTotal += i.totalSistemas;
                    this.totalGravidadeTotalSistema += i.gravidadeTotalSistema;
                    this.totalGravidadeSistemaFunciona += i.gravidadeSistemaFunciona;
                    this.totalGravidadeSistemaNaoFunciona += i.gravidadeSistemaNaoFunciona;
                    this.totalOutroTotalSistema += i.outroTotalSistema;
                    this.totalOutroSistemaFunciona += i.outroSistemaFunciona;
                    this.totalOutroSistemaNaoFunciona += i.outroSistemaNaoFunciona;
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
