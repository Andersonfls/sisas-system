import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Adjudicacao } from './adjudicacao.model';
import { AdjudicacaoPopupService } from './adjudicacao-popup.service';
import { AdjudicacaoService } from './adjudicacao.service';
import { ProgramasProjectos, ProgramasProjectosService } from '../programas-projectos';
import { SistemaAgua, SistemaAguaService } from '../sistema-agua';

@Component({
    selector: 'jhi-adjudicacao-dialog',
    templateUrl: './adjudicacao-dialog.component.html'
})
export class AdjudicacaoDialogComponent implements OnInit {

    adjudicacao: Adjudicacao;
    isSaving: boolean;

    programasprojectos: ProgramasProjectos[];

    sistemaaguas: SistemaAgua[];
    dtLancamentoDp: any;
    dtComunicaoAdjudicacaoDp: any;
    dtPrestacaoGarantBoaExecDp: any;
    dtSubmissaoMinutContratoDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private adjudicacaoService: AdjudicacaoService,
        private programasProjectosService: ProgramasProjectosService,
        private sistemaAguaService: SistemaAguaService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.programasProjectosService.query()
            .subscribe((res: HttpResponse<ProgramasProjectos[]>) => { this.programasprojectos = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.sistemaAguaService.query()
            .subscribe((res: HttpResponse<SistemaAgua[]>) => { this.sistemaaguas = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.adjudicacao.id !== undefined) {
            this.subscribeToSaveResponse(
                this.adjudicacaoService.update(this.adjudicacao));
        } else {
            this.subscribeToSaveResponse(
                this.adjudicacaoService.create(this.adjudicacao));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Adjudicacao>>) {
        result.subscribe((res: HttpResponse<Adjudicacao>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Adjudicacao) {
        this.eventManager.broadcast({ name: 'adjudicacaoListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackProgramasProjectosById(index: number, item: ProgramasProjectos) {
        return item.id;
    }

    trackSistemaAguaById(index: number, item: SistemaAgua) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-adjudicacao-popup',
    template: ''
})
export class AdjudicacaoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private adjudicacaoPopupService: AdjudicacaoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.adjudicacaoPopupService
                    .open(AdjudicacaoDialogComponent as Component, params['id']);
            } else {
                this.adjudicacaoPopupService
                    .open(AdjudicacaoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
