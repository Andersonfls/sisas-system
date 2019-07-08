import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Contrato } from './contrato.model';
import { ContratoPopupService } from './contrato-popup.service';
import { ContratoService } from './contrato.service';
import { ProgramasProjectos, ProgramasProjectosService } from '../programas-projectos';
import { SistemaAgua, SistemaAguaService } from '../sistema-agua';

@Component({
    selector: 'jhi-contrato-dialog',
    templateUrl: './contrato-dialog.component.html'
})
export class ContratoDialogComponent implements OnInit {

    contrato: Contrato;
    isSaving: boolean;

    programasprojectos: ProgramasProjectos[];

    sistemaaguas: SistemaAgua[];
    dtLancamentoDp: any;
    dtAssinaturaDp: any;
    dtFinalizacaoProcessoHomologAprovDp: any;
    dtAdiantamentoDp: any;
    dtInicioDp: any;
    dtRecepcaoProvisoriaDp: any;
    dtRecepcaoDefinitivaDp: any;
    dtRecepcaoComicionamentoDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private contratoService: ContratoService,
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
        if (this.contrato.id !== undefined) {
            this.subscribeToSaveResponse(
                this.contratoService.update(this.contrato));
        } else {
            this.subscribeToSaveResponse(
                this.contratoService.create(this.contrato));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Contrato>>) {
        result.subscribe((res: HttpResponse<Contrato>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Contrato) {
        this.eventManager.broadcast({ name: 'contratoListModification', content: 'OK'});
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
    selector: 'jhi-contrato-popup',
    template: ''
})
export class ContratoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private contratoPopupService: ContratoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.contratoPopupService
                    .open(ContratoDialogComponent as Component, params['id']);
            } else {
                this.contratoPopupService
                    .open(ContratoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
