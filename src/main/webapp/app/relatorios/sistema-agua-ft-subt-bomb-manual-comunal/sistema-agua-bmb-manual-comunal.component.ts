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
import {SistemaAguaBmbManualComunal} from './sistema-agua-bmb-manual-comunal.model';

@Component({
    selector: 'jhi-sistema-agua-bomba-manual-comunal',
    templateUrl: './sistema-agua-bmb-manual-comunal.component.html',
    styleUrls: [
        'sistema-agua-bmb-manual-comunal.css'
    ]
})

export class SistemaAguaBombManualComunalComponent implements OnInit {

    user: User;
    listaTabela: SistemaAguaBmbManualComunal[];
    tipoRelatorio: string;
    predicate: any;
    reverse: any;
    chart: any;

    totalPocoMelhorado = 0;
    totalFuro = 0;
    totalNascente = 0;
    totalSistemaTotal = 0;

    totalAfridevTotalSistema = 0;
    totalAfridevSistemaFunciona = 0;
    totalAfridevSistemaNaoFunciona = 0;

    totalVergnetSistemaFunciona = 0;
    totalVergnetSistemaNaoFunciona = 0;
    totalVergnetTotalSistema = 0;

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
            const pdf = new jsPDF('l', 'mm', 'a4');
            pdf.text('Sistema de Água por fonte Subterrânea e por Bomba Manual (Nível Comunal)', 45, 7);
            pdf.addImage(contentDataURL, 'PNG', 40, 9, imgWidth, (imgHeight - 10));
            pdf.save('relatorio-sisas.pdf');
        }).catch(function(error) {
            // Error Handling
        });
    }

    exportTable(tabeId) {
        TableUtil.exportToExcel(tabeId);
    }

    buscaDadosTabelaComunal() {
        this.relatorioService.buscaDadosSistemaAguaBmbManualComunal().subscribe(
            (res: HttpResponse<SistemaAguaBmbManualComunal[]>) => {
                this.listaTabela = res.body;
                console.log(this.listaTabela);

                this.listaTabela.forEach( (i) => {
                    this.totalPocoMelhorado += i.pocoMelhorado;
                    this.totalFuro += i.furo;
                    this.totalNascente += i.nascente;
                    this.totalSistemaTotal += i.totalSistemas;
                    this.totalAfridevTotalSistema += i.afridevTotalSistema;
                    this.totalAfridevSistemaFunciona += i.afridevSistemaFunciona;
                    this.totalAfridevSistemaNaoFunciona += i.afridevSistemaNaoFunciona;
                    this.totalVergnetTotalSistema += i.vergnetTotalSistema;
                    this.totalVergnetSistemaFunciona += i.vergnetSistemaFunciona;
                    this.totalVergnetSistemaNaoFunciona += i.vergnetSistemaNaoFunciona;
                    this.totalVolantaTotalSistema += i.volantaTotalSistema;
                    this.totalVolantaSistemaFunciona += i.volantaSistemaFunciona;
                    this.totalVolantaSistemaNaoFunciona += i.volantaSistemaNaoFunciona;
                    this.totalIndiaTotalSistema += i.indiaTotalSistema;
                    this.totalIndiaSistemaFunciona += i.indiaSistemaFunciona;
                    this.totalIndiaSistemaNaoFunciona += i.indiaSistemaNaoFunciona;
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
