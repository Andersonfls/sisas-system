import { Component, OnInit } from '@angular/core';
import { JhiAlertService } from 'ng-jhipster';
import { UserService } from '../../shared/user/user.service';
import { HttpResponse } from '@angular/common/http';
import { User } from '../../shared/user/user.model';
import { Principal } from '../../shared/auth/principal.service';
import {RelatoriosService} from '../relatorios.service';
import {DadosRelatorio} from '../cobertura-sector-agua/dadosRelatorio.model';
import {BeneficiariosOptTecnica} from './BeneficiariosOptTecnica.model';

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
    tipoRelatorio: string;
    predicate: any;
    reverse: any;
    chart: any;
    listaFuncionam: DadosRelatorio[];
    listaNaoFuncionam: DadosRelatorio[];
    listaNumSistemas: DadosRelatorio[];

    totalSistemas = 0;
    totalElectraSistemas = 0;
    totalElectrafuncionam = 0;
    totalElectranaoFuncionam = 0;
    totalElectraPopulacao = 0;
    totalElectraPerc = 0;

    totalDieselSistemas = 0;
    totalDieselfuncionam = 0;
    totalDieselnaoFuncionam = 0;
    totalDieselPopulacao = 0;
    totalDieselPerc = 0;

    totalGravidadeSistemas = 0;
    totalGravidadefuncionam = 0;
    totalGravidadenaoFuncionam = 0;
    totalGravidadePopulacao = 0;
    totalGravidadePerc = 0;

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
    }

    validaTipoRelatorio() {
        if (this.tipoRelatorio === 'Nível Provincial') {
            this.buscaDadosTabela();
        }
        if (this.tipoRelatorio === 'Nível Municipal') {
            this.buscaDadosTabela();
        }
        if (this.tipoRelatorio === 'Nível Comunal') {
            this.buscaDadosTabela();
        }
    }

    voltarEscolha() {
        this.tipoRelatorio = null;
    }

    buscaDadosTabela() {
        this.relatorioService.buscaDadosFuncAguaChafariz().subscribe(
            (res: HttpResponse<BeneficiariosOptTecnica[]>) => {
                this.listaTabela = res.body;
                console.log(this.listaTabela);

                this.listaNaoFuncionam = new Array();
                this.listaFuncionam = new Array();
                this.listaNumSistemas = new Array();

                this.listaTabela.forEach( (i) => {
                    const item: DadosRelatorio = new DadosRelatorio();
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
