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
import {FuncAgua} from '../funcionamento-agua/FuncAgua.model';

@Component({
    selector: 'jhi-func-sist-agua',
    templateUrl: './func-agua.component.html',
    styleUrls: [
        'func-agua.css'
    ]
})

export class FuncAguaComponent implements OnInit {

    user: User;
    listaTabela: FuncAgua[];
    predicate: any;
    reverse: any;
    chart: any;

    totalnumeroSistemas = 0;
    totalBeneficiarios = 0;
    totalfuncionamAgua = 0;
    totalnaoFuncionamAgua = 0;
    totalfuncionamAguaPerc = 0;
    totalnaoFuncionamAguaPerc = 0;

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
            pdf.save('relatorio-sisas.pdf');
        }).catch(function(error) {
            // Error Handling
        });
    }

    exportTable(tabeId) {
        TableUtil.exportToExcel(tabeId);
    }

    buscaDadosTabela() {
        this.relatorioService.buscaDadosFuncSistAguaMunicipal().subscribe(
            (res: HttpResponse<FuncAgua[]>) => {
                this.listaTabela = res.body;
                console.log(this.listaTabela);

                this.listaTabela.forEach( (i) => {
                    this.totalnumeroSistemas += i.numeroSistemas;
                    this.totalfuncionamAgua += i.funcionamAgua;
                    this.totalnaoFuncionamAgua += i.naoFuncionamAgua;
                    this.totalBeneficiarios += i.beneficiariosAgua;
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
