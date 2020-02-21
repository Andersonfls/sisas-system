import { Component, OnInit } from '@angular/core';
import { JhiAlertService } from 'ng-jhipster';
import { UserService } from '../../shared/user/user.service';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import { User } from '../../shared/user/user.model';
import { Principal } from '../../shared/auth/principal.service';
import {Provincia} from '../../entities/provincia';
import {IndicadorProducaoProvincia} from './IndicadorProducaoProvincia.model';
import {RelatoriosService} from '../relatorios.service';
import * as jsPDF from 'jspdf';
import {TableUtil} from '../../shared/util/tableUtil';
import * as html2canvas from 'html2canvas';
import {ProvinciaService} from '../../entities/provincia/provincia.service';

@Component({
    selector: 'jhi-indicador-producao-provincia',
    templateUrl: './indicador-producao-provincia.component.html',
    styleUrls: [
        'indicador-producao-provincia.css'
    ]
})

export class IndicadorProducaoProvinciaComponent implements OnInit {

    user: User;
    indicadorProducaoProvincia: IndicadorProducaoProvincia;
    indicadorProducaoProvincias: IndicadorProducaoProvincia[];
    predicate: any;
    reverse: any;
    provincias: Provincia[];
    provincia: Provincia;

    constructor(
        private jhiAlertService: JhiAlertService,
        private userService: UserService,
        private principal: Principal,
        private relatorioService: RelatoriosService,
        private provinciaService: ProvinciaService,
    ) {}

    ngOnInit() {
        this.indicadorProducaoProvincia = new IndicadorProducaoProvincia();
        this.indicadorProducaoProvincias = new Array();

        this.indicadorProducaoProvincia.ano = null;
        this.indicadorProducaoProvincia.provincia = null;

        this.principal.identity().then((userIdentity) => {
            this.user = userIdentity;
        });

        this.provinciaService.query().subscribe(
            (res: HttpResponse<Provincia[]>) => {
                this.provincias = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message));

        this.relatorioService.buscaNomeCampos().subscribe(
            (res: HttpResponse<IndicadorProducaoProvincia[]>) => {
                this.indicadorProducaoProvincias = res.body;
            }
        )

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

/*    buscaDadosTabela() {
        this.relatorioService.buscaDadosIndicadorProducaoProvincia().subscribe(
            (res: HttpResponse<IndicadorProducaoProvincia[]>) => {
                this.indicadorProducaoProvincias = res.body;
                console.log(this.indicadorProducaoProvincia);

                this.indicadorProducaoProvincias.forEach( (i) => {
                    this.totalnumeroSistemas += i.numeroSistemas;
                    this.totalfuncionamAgua += i.funcionamAgua;
                    this.totalnaoFuncionamAgua += i.naoFuncionamAgua;
                    this.totalfuncionamAguaPerc = 76;
                    this.totalnaoFuncionamAguaPerc = 24;
                    this.totalnumeroChafarizes += i.numeroChafarizes;
                    this.totalfuncionamChafariz += i.funcionamChafariz;
                    this.totalnaoFuncionamChafariz += i.naoFuncionamChafariz;
                    this.totalfuncionamChafarizPerc += i.funcionamChafarizPerc;
                    this.totalnaoFuncionamChafarizPerc = 25;
                });
            });
    }*/

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackId(index: number, item: Provincia) {
        return item.id;
    }

}
