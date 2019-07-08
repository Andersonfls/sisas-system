import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Execucao } from './execucao.model';
import { ExecucaoPopupService } from './execucao-popup.service';
import { ExecucaoService } from './execucao.service';
import { Situacao, SituacaoService } from '../situacao';
import { ProgramasProjectos, ProgramasProjectosService } from '../programas-projectos';
import { SistemaAgua, SistemaAguaService } from '../sistema-agua';
import { Contrato, ContratoService } from '../contrato';

@Component({
    selector: 'jhi-execucao-dialog',
    templateUrl: './execucao-dialog.component.html'
})
export class ExecucaoDialogComponent implements OnInit {

    execucao: Execucao;
    isSaving: boolean;

    situacaos: Situacao[];

    programasprojectos: ProgramasProjectos[];

    sistemaaguas: SistemaAgua[];

    contratoes: Contrato[];
    dtLancamentoDp: any;
    dtPeridoReferenciaDp: any;
    dtFimReferenciaDp: any;
    dtFacturaDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private execucaoService: ExecucaoService,
        private situacaoService: SituacaoService,
        private programasProjectosService: ProgramasProjectosService,
        private sistemaAguaService: SistemaAguaService,
        private contratoService: ContratoService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.situacaoService.query()
            .subscribe((res: HttpResponse<Situacao[]>) => { this.situacaos = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.programasProjectosService.query()
            .subscribe((res: HttpResponse<ProgramasProjectos[]>) => { this.programasprojectos = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.sistemaAguaService.query()
            .subscribe((res: HttpResponse<SistemaAgua[]>) => { this.sistemaaguas = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.contratoService.query()
            .subscribe((res: HttpResponse<Contrato[]>) => { this.contratoes = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.execucao.id !== undefined) {
            this.subscribeToSaveResponse(
                this.execucaoService.update(this.execucao));
        } else {
            this.subscribeToSaveResponse(
                this.execucaoService.create(this.execucao));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Execucao>>) {
        result.subscribe((res: HttpResponse<Execucao>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Execucao) {
        this.eventManager.broadcast({ name: 'execucaoListModification', content: 'OK'});
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

    trackProgramasProjectosById(index: number, item: ProgramasProjectos) {
        return item.id;
    }

    trackSistemaAguaById(index: number, item: SistemaAgua) {
        return item.id;
    }

    trackContratoById(index: number, item: Contrato) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-execucao-popup',
    template: ''
})
export class ExecucaoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private execucaoPopupService: ExecucaoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.execucaoPopupService
                    .open(ExecucaoDialogComponent as Component, params['id']);
            } else {
                this.execucaoPopupService
                    .open(ExecucaoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
