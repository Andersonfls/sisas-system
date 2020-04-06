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
import {BeneAguaFtSubterraneaTpBomba} from '../beneficiarios-agua-ft-subt-tp-bmb/beneAguaFtSubterraneaTpBomba.model';

@Component({
    selector: 'jhi-trat-sist-agua',
    templateUrl: './tratamento-sistemas-agua.component.html',
    styleUrls: [
        'tratamento-sistemas-agua.css'
    ]
})

export class TratamentoSistemasAguaComponent implements OnInit {

    user: User;
    listaTabela: BeneAguaFtSubterraneaTpBomba[];
    predicate: any;
    reverse: any;
    chart: any;
    totalPocoMelhorado = 0;
    totalFuro = 0;
    totalNascente = 0;
    totalGravidade = 0;
    totalPopulacao = 0;
    totalOutro = 0;
    totalPopulacaoOutros = 0;

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
            const imgWidth = 207;
            const pageHeight = 295;
            const imgHeight = canvas.height * imgWidth / canvas.width;
            const heightLeft = imgHeight;
            const contentDataURL = canvas.toDataURL('image/png');
            const pdf = new jsPDF('p', 'mm', 'a4');
            pdf.text('Beneficiários de Água por Fonte Subterrânea e por Tipo de Bomba (Municipal)', 2, 7);
            pdf.addImage(contentDataURL, 'PNG', 2, 13, imgWidth, imgHeight);
            pdf.save('beneficiariosTpBomba.pdf');
        }).catch(function(error) {
            // Error Handling
        });
    }

    exportTable(tabeId) {
        TableUtil.exportToExcel(tabeId);
    }

    buscaDadosTabela() {
        this.relatorioService.buscaDadosBenfAguaSubtTipoBombaMunicipal().subscribe(
            (res: HttpResponse<BeneAguaFtSubterraneaTpBomba[]>) => {
                this.listaTabela = res.body;
                this.listaTabela.forEach((p) => {
                    this.totalPocoMelhorado += p.numeroPocoMelhorado;
                    this.totalFuro += p.furo;
                    this.totalNascente += p.nascente;
                    this.totalGravidade += p.totalBombaGravidade;
                    this.totalPopulacao += p.populacaoBeneficiadaGravidade;
                    this.totalOutro += p.totalTipoBombaOutros;
                    this.totalPopulacaoOutros += p.qtdPopulacaoOutros;
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
