import {Component, OnInit, OnDestroy} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpResponse, HttpErrorResponse} from '@angular/common/http';

import {Observable} from 'rxjs/Observable';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {JhiEventManager, JhiAlertService} from 'ng-jhipster';

import {SistemaAgua} from './sistema-agua.model';
import {SistemaAguaPopupService} from './sistema-agua-popup.service';
import {SistemaAguaService} from './sistema-agua.service';
import {Situacao, SituacaoService} from '../situacao';
import {Comuna, ComunaService} from '../comuna';
import {Provincia, ProvinciaService} from '../provincia';
import {Municipio, MunicipioService} from '../municipio';

@Component({
    selector: 'jhi-sistema-agua-dialog',
    templateUrl: './sistema-agua-dialog.component.html',
    styleUrls: [
        'sistemaAgua.css'
    ]
})
export class SistemaAguaDialogComponent implements OnInit {

    sistemaAgua: SistemaAgua;
    isSaving: boolean;
    situacaos: Situacao[];
    provincias: Provincia[];
    provincia: Provincia;
    municipios: Municipio[];
    municipio: Municipio;
    comunas: Comuna[];
    comuna: Comuna;
    dtLancamentoDp: any;
    dtUltimaAlteracaoDp: any;
    public tipoComunaAldeias: Array<any> = ['Concentrada', 'Dispersa', 'Semi-Dispersa'];
    routeSub: any;
    listaAnos: number[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private sistemaAguaService: SistemaAguaService,
        private situacaoService: SituacaoService,
        private comunaService: ComunaService,
        private municipioService: MunicipioService,
        private provinciaService: ProvinciaService,
        private eventManager: JhiEventManager,
        private route: ActivatedRoute,
        private router: Router
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.sistemaAgua = new SistemaAgua();

        this.routeSub = this.route.params.subscribe((params) => {
            if (params['id']) {
                this.load(params['id']);
            } else {
                this.sistemaAgua = new SistemaAgua();
            }
        });

        this.situacaoService.query()
            .subscribe((res: HttpResponse<Situacao[]>) => {
                this.situacaos = res.body;
            }, (res: HttpErrorResponse) => this.onError(res.message));

        this.comunaService.query()
            .subscribe((res: HttpResponse<Comuna[]>) => {
                this.comunas = res.body;
            }, (res: HttpErrorResponse) => this.onError(res.message));

        this.municipioService.query().subscribe(
            (res: HttpResponse<Municipio[]>) => {
                this.municipios = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message));

        this.provinciaService.query().subscribe(
            (res: HttpResponse<Provincia[]>) => {
                this.provincias = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message));

        const now = new Date();
        this.sistemaAgua.dtLancamento = {year: now.getFullYear(), month: now.getMonth() + 1, day: now.getDate()};
        console.log(this.sistemaAgua);
        this.montaListaAnos();
    }

    verificaChafariz() {
        if (this.sistemaAgua.qtdChafarisesFuncionando > this.sistemaAgua.qtdChafarisesExistentes) {
            alert('A quantidade de Chafarizes Funcionando é maior que a Existente!!!');
            this.sistemaAgua.qtdChafarisesFuncionando = null;
        }
    }

    validaExclusaoDados() {
        console.log('Valor Possui sistema agua --', this.sistemaAgua.possuiSistemaAgua);
        if (this.sistemaAgua.possuiSistemaAgua === 0) {
            if (this.sistemaAgua.nmSistemaAgua !== null && this.sistemaAgua.nmSistemaAgua !== undefined) {
                if (confirm('Os dados digitados anteriormente serao apagados, deseja continuar?')) {
                    this.inicializarCampos();
                }
            }
          //  this.inicializarCampos();
        }
    }

    inicializarCampos() {
        const temp = this.sistemaAgua;

        this.sistemaAgua = new SistemaAgua();
        this.sistemaAgua.id = temp.id;
        this.sistemaAgua.nmInqueridor = temp.nmInqueridor;
        this.sistemaAgua.situacao = temp.situacao;
        this.sistemaAgua.provincia = temp.provincia;
        this.sistemaAgua.municipio = temp.municipio;
        this.sistemaAgua.comuna = temp.comuna;
        this.sistemaAgua.nmLocalidade = temp.nmLocalidade;
        this.sistemaAgua.qtdPopulacaoActual = temp.qtdPopulacaoActual;
        this.sistemaAgua.qtdCasasLocalidade = temp.qtdCasasLocalidade;
        this.sistemaAgua.nmTpComunaAldeia = temp.nmTpComunaAldeia;
        this.sistemaAgua.nmTpArea = temp.nmTpArea;
        this.sistemaAgua.possuiSistemaAgua = temp.possuiSistemaAgua;
    }

    load(id) {
        this.sistemaAguaService.find(id)
            .subscribe((sistemaAguaResponse: HttpResponse<SistemaAgua>) => {
                const sistemaAgua: SistemaAgua = sistemaAguaResponse.body;
                if (sistemaAgua.dtLancamento) {
                    sistemaAgua.dtLancamento = {
                        year: sistemaAgua.dtLancamento.getFullYear(),
                        month: sistemaAgua.dtLancamento.getMonth() + 1,
                        day: sistemaAgua.dtLancamento.getDate()
                    };
                }
                if (sistemaAgua.dtUltimaAlteracao) {
                    sistemaAgua.dtUltimaAlteracao = {
                        year: sistemaAgua.dtUltimaAlteracao.getFullYear(),
                        month: sistemaAgua.dtUltimaAlteracao.getMonth() + 1,
                        day: sistemaAgua.dtUltimaAlteracao.getDate()
                    };
                }
                this.sistemaAgua = sistemaAgua;
            });
    }

    clear() {
        // this.activeModal.dismiss('cancel');
    }

    montaListaAnos() {
        this.listaAnos = new Array();
        const ano = 2030;

        for (let i = ano; i >= 1973; i-- ) {
            this.listaAnos.push(i);
        }
    }

    save() {
        this.isSaving = true;
        if (this.sistemaAgua.id !== undefined) {
            this.subscribeToSaveResponse(
                this.sistemaAguaService.update(this.sistemaAgua));
        } else {
            this.subscribeToSaveResponse(
                this.sistemaAguaService.create(this.sistemaAgua));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<SistemaAgua>>) {
        result.subscribe((res: HttpResponse<SistemaAgua>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: SistemaAgua) {
        this.eventManager.broadcast({name: 'sistemaAguaListModification', content: 'OK'});
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackSituacaoById(index: number, item: Situacao) {
        return item.id;
    }

    trackComunaById(index: number, item: Comuna) {
        return item.id;
    }
    previousState() {
        this.router.navigate(['sistema-agua']);
    }

    onChangeMunicipios() {
        this.municipios = null;
        this.comunas = null;

        this.municipioService.queryMunicipioByProvinciaId({
            provinciaId: this.sistemaAgua.provincia.id
        })
            .subscribe((res) => {
                this.municipios = res.body;
            });
    }

    onChangeComunas() {
        this.comunas = null;

        this.comunaService.queryComunaByMunicipioId({
            municipioId: this.sistemaAgua.municipio.id
        })
            .subscribe((res) => {
                this.comunas = res.body;
            });
    }
}

@Component({
    selector: 'jhi-sistema-agua-popup',
    template: ''
})
export class SistemaAguaPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private sistemaAguaPopupService: SistemaAguaPopupService
    ) {
    }

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if (params['id']) {
                this.sistemaAguaPopupService
                    .open(SistemaAguaDialogComponent as Component, params['id']);
            } else {
                this.sistemaAguaPopupService
                    .open(SistemaAguaDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
