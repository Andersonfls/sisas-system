import { Component, OnInit } from '@angular/core';
import { JhiAlertService } from 'ng-jhipster';
import { UserService } from '../../shared/user/user.service';
import { HttpResponse } from '@angular/common/http';
import { User } from '../../shared/user/user.model';
import { Principal } from '../../shared/auth/principal.service';
import {Provincia} from '../../entities/provincia';
import {FuncAguaChafarizes} from './FuncAguaChafarizes.model';
import {RelatoriosService} from '../relatorios.service';
import * as jsPDF from 'jspdf';
import {TableUtil} from '../../shared/util/tableUtil';
import * as html2canvas from 'html2canvas';

@Component({
    selector: 'jhi-func-sist-agua-chafariz',
    templateUrl: './func-agua-chafarizes.component.html',
    styleUrls: [
        'func-agua-chafarizes.css'
    ]
})

export class FuncAguaChafarizesComponent implements OnInit {

    user: User;
    listaTabela: FuncAguaChafarizes[];
    predicate: any;
    reverse: any;

    totalnumeroSistemas = 0;
    totalfuncionamAgua = 0;
    totalnaoFuncionamAgua = 0;
    totalfuncionamAguaPerc = 0;
    totalnaoFuncionamAguaPerc = 0;
    totalnumeroChafarizes = 0;
    totalfuncionamChafariz = 0;
    totalnaoFuncionamChafariz = 0;
    totalfuncionamChafarizPerc = 0;
    totalnaoFuncionamChafarizPerc = 0;

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
            const imgWidth = 255;
            const imgHeight = canvas.height * imgWidth / canvas.width;
            const contentDataURL = canvas.toDataURL('image/png');
            const pdf = new jsPDF('l', 'mm', 'a4');
            pdf.text('Funcionamento de Sistemas de Água e Chafarizes (Nível Comunal)', 50, 7);
            pdf.addImage(contentDataURL, 'PNG', 22, 13, imgWidth, imgHeight + 10);
            pdf.save('funcionamento-aguas-chafarizes.pdf');
        }).catch(function(error) {
            // Error Handling
        });
    }
    exportTable(tabeId) {
        TableUtil.exportToExcel(tabeId);
    }

    buscaDadosTabela() {
        this.relatorioService.buscaDadosFuncAguaChafarizComunal().subscribe(
            (res: HttpResponse<FuncAguaChafarizes[]>) => {
                this.listaTabela = res.body;
                console.log(this.listaTabela);

                this.listaTabela.forEach( (i) => {
                    this.totalnumeroSistemas += i.numeroSistemas;
                    this.totalfuncionamAgua += i.funcionamAgua;
                    this.totalnaoFuncionamAgua += i.naoFuncionamAgua;
                    this.totalnumeroChafarizes += i.numeroChafarizes;
                    this.totalfuncionamChafariz += i.funcionamChafariz;
                    this.totalnaoFuncionamChafariz += i.naoFuncionamChafariz;
                    this.totalfuncionamChafarizPerc += i.funcionamChafarizPerc;
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

    trackId(index: number, item: Provincia) {
        return item.id;
    }

}
