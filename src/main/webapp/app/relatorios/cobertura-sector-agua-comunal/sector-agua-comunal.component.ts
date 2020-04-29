import { Component, OnInit } from '@angular/core';
import { JhiAlertService } from 'ng-jhipster';
import { UserService } from '../../shared/user/user.service';
import { HttpResponse } from '@angular/common/http';
import { User } from '../../shared/user/user.model';
import { Principal } from '../../shared/auth/principal.service';
import {Provincia} from '../../entities/provincia';
import {RelatoriosService} from '../relatorios.service';
import * as jsPDF from 'jspdf';
import {TableUtil} from '../../shared/util/tableUtil';
import * as html2canvas from 'html2canvas';
import {CoberturaSectorAguaModel} from '../cobertura-sector-agua-provincial/coberturaSectorAgua.model';

@Component({
    selector: 'jhi-sector-agua',
    templateUrl: './sector-agua.component.html',
    styleUrls: [
        'sector-agua.css'
    ]
})

export class CoberturaSectorAguaProvincialComponent implements OnInit {
    user: User;

    provincias: Provincia[];
    listaTabela: CoberturaSectorAguaModel[];
    predicate: any;
    reverse: any;
    totalMunicipios = 0;
    totalComuna = 0;
    totalSistemas = 0;
    totalPopulacao= 0;
    totalBeneficiarios = 0;
    totalCobertura = 0;

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
        this.buscaDadosTabelaComunal();
    }

    public captureScreen(elementId) {
        const data = document.getElementById(elementId);
        (html2canvas as any)(data).then((canvas) => {
            const imgWidth = 204;
            const pageHeight = 295;
            const imgHeight = canvas.height * imgWidth / canvas.width;
            let heightLeft = imgHeight;
            const contentDataURL = canvas.toDataURL('image/png');
            const pdf = new jsPDF('p', 'mm', 'a4');
            let position = 12;

            pdf.text('Cobertura no Sector de Água (Nível Comunal)', 45, 10);
            pdf.addImage(contentDataURL, 'PNG', 3, position, imgWidth, (imgHeight - 13));
            heightLeft -= pageHeight;

            while (heightLeft >= 0) {
                position = heightLeft - imgHeight;
                pdf.addPage();
                pdf.text('', 2, 5);
                pdf.addImage(contentDataURL, 'PNG', 3, (position + 13), imgWidth, (imgHeight - 10));
                heightLeft -= pageHeight;
            }

            pdf.save('relatorio-sector-aguas.pdf');
        }).catch(function(error) {
            // Error Handling
        });
    }

    exportTable(tabeId) {
        TableUtil.exportToExcel(tabeId);
    }

    buscaDadosTabelaComunal() {
        this.relatorioService.buscaDadosCoberturaSectorAguaComunal().subscribe(
            (res: HttpResponse<CoberturaSectorAguaModel[]>) => {
                this.listaTabela = res.body;

                this.listaTabela.forEach((p) => {
                    this.totalMunicipios += p.numeroMunicipios;
                    this.totalComuna += p.numeroComunas;
                    this.totalSistemas += p.numeroSistemasFuncionam;
                    this.totalPopulacao += p.populacao;
                    this.totalBeneficiarios += p.beneficiarios;
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
