import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';

import {Observable} from 'rxjs/Observable';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {JhiAlertService, JhiEventManager} from 'ng-jhipster';

import {IndicadorProducao} from './indicador-producao.model';
import {IndicadorProducaoPopupService} from './indicador-producao-popup.service';
import {IndicadorProducaoService} from './indicador-producao.service';
import {Situacao, SituacaoService} from '../situacao';
import {SistemaAgua, SistemaAguaService} from '../sistema-agua';
import {Comuna, ComunaService} from '../comuna';

@Component({
    selector: 'jhi-indicador-producao-dialog',
    templateUrl: './indicador-producao-dialog.component.html'
})
export class IndicadorProducaoDialogComponent implements OnInit {

    indicadorProducao: IndicadorProducao;

    isSaving: boolean;

    situacaos: Situacao[];

    sistemaaguas: SistemaAgua[];

    comunas: Comuna[];
    dtLancamentoDp: any;
    dtUltimaAlteracaoDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private indicadorProducaoService: IndicadorProducaoService,
        private situacaoService: SituacaoService,
        private sistemaAguaService: SistemaAguaService,
        private comunaService: ComunaService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.situacaoService.query()
            .subscribe((res: HttpResponse<Situacao[]>) => {
                this.situacaos = res.body;
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.sistemaAguaService.query()
            .subscribe((res: HttpResponse<SistemaAgua[]>) => {
                this.sistemaaguas = res.body;
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.comunaService.query()
            .subscribe((res: HttpResponse<Comuna[]>) => {
                this.comunas = res.body;
            }, (res: HttpErrorResponse) => this.onError(res.message));
        // this.findLastIndicador();

    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.indicadorProducao.id !== undefined) {
            this.subscribeToSaveResponse(
                this.indicadorProducaoService.update(this.indicadorProducao));
        } else {
            this.subscribeToSaveResponse(
                this.indicadorProducaoService.create(this.indicadorProducao));
        }

    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IndicadorProducao>>) {
        result.subscribe((res: HttpResponse<IndicadorProducao>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: IndicadorProducao) {
        this.eventManager.broadcast({name: 'indicadorProducaoListModification', content: 'OK'});
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

    trackSistemaAguaById(index: number, item: SistemaAgua) {
        return item.id;
    }

    trackComunaById(index: number, item: Comuna) {
        return item.id;
    }

    findLastIndicador() {
        this.indicadorProducaoService.findLast().subscribe(
            (res: HttpResponse<IndicadorProducao>) => {
                (this.indicadorProducao = res.body);
            },
            (error1) => {
                (this.onError(error1));
            });
    }
}

@Component({
    selector: 'jhi-indicador-producao-popup',
    template: ''
})
export class IndicadorProducaoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private indicadorProducaoPopupService: IndicadorProducaoPopupService
    ) {
    }

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if (params['id']) {
                this.indicadorProducaoPopupService
                    .open(IndicadorProducaoDialogComponent as Component, params['id']);
            } else {
                this.indicadorProducaoPopupService
                    .open(IndicadorProducaoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
