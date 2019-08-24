import {Component, OnInit, OnDestroy} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
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
    templateUrl: './sistema-agua-dialog.component.html'
})
export class SistemaAguaDialogComponent implements OnInit {

    sistemaAgua: SistemaAgua;
    isSaving: boolean;
    situacaos: Situacao[];
    comunas: Comuna[];
    provincias: Provincia[];
    municipios: Municipio[];
    dtLancamentoDp: any;
    dtUltimaAlteracaoDp: any;
    public tipoComunaAldeias: Array<any> = ['Concentrada', 'Dispersa', 'Semi-Dispersa'];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private sistemaAguaService: SistemaAguaService,
        private situacaoService: SituacaoService,
        private comunaService: ComunaService,
        private municipioService: MunicipioService,
        private provinciaService: ProvinciaService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
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

        this.inicializarCampos();

        const now = new Date();
        this.sistemaAgua.dtLancamento = {year: now.getFullYear(), month: now.getMonth() + 1, day: now.getDate()};
    }

    inicializarCampos() {
        this.sistemaAgua.situacao = null;
        this.sistemaAgua.comuna = null;
        this.sistemaAgua.municipio = null;
        this.sistemaAgua.provincia = null;
        this.sistemaAgua.nmTpComunaAldeia = null;
        this.sistemaAgua.nmTpArea = null;
        this.sistemaAgua.possuiSistemaAgua = null;
        this.sistemaAgua.nmTpFonte = null;
        this.sistemaAgua.nmFonteAgua = null;
        this.sistemaAgua.esquema = null;
        this.sistemaAgua.nmTipoBomba = null;
        this.sistemaAgua.nmTipoBomba = null;
        this.sistemaAgua.nmFonteAguaUtilizada = null;
        this.sistemaAgua.nmTpBombaEnergia = null;
        this.sistemaAgua.nmModeloBombaManualUtilizada = null;
        this.sistemaAgua.nmTpTratamentoAgua = null;
        this.sistemaAgua.nmTpTratamentoPadraoUtilizado = null;
        this.sistemaAgua.nmTpTratamentoBasicoUtilizado = null;
        this.sistemaAgua.existeAvariaSistemaTratamento = null;
        this.sistemaAgua.nmTpTratamentoBasicoUtilizado = null;
        this.sistemaAgua.existeMotivoAusenciaTratamento = null;
        this.sistemaAgua.nmEquipamentosComAvaria = null;
        this.sistemaAgua.nmTpAvariaSistema = null;
        this.sistemaAgua.causaAvariaSistema = null;
        this.sistemaAgua.statusResolucao = null;
        this.sistemaAgua.tempoServicoDisponivel = null;
    }

    clear() {
        this.activeModal.dismiss('cancel');
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
        this.activeModal.dismiss(result);
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
