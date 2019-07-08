import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Concepcao } from './concepcao.model';
import { ConcepcaoPopupService } from './concepcao-popup.service';
import { ConcepcaoService } from './concepcao.service';
import { ProgramasProjectos, ProgramasProjectosService } from '../programas-projectos';
import { SistemaAgua, SistemaAguaService } from '../sistema-agua';

@Component({
    selector: 'jhi-concepcao-dialog',
    templateUrl: './concepcao-dialog.component.html'
})
export class ConcepcaoDialogComponent implements OnInit {

    concepcao: Concepcao;
    isSaving: boolean;

    programasprojectos: ProgramasProjectos[];

    sistemaaguas: SistemaAgua[];
    dtLancamentoDp: any;
    dtUltimaAlteracaoDp: any;
    dtElaboracaoConDp: any;
    dtAprovacaoConDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private concepcaoService: ConcepcaoService,
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
        if (this.concepcao.id !== undefined) {
            this.subscribeToSaveResponse(
                this.concepcaoService.update(this.concepcao));
        } else {
            this.subscribeToSaveResponse(
                this.concepcaoService.create(this.concepcao));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Concepcao>>) {
        result.subscribe((res: HttpResponse<Concepcao>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Concepcao) {
        this.eventManager.broadcast({ name: 'concepcaoListModification', content: 'OK'});
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
    selector: 'jhi-concepcao-popup',
    template: ''
})
export class ConcepcaoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private concepcaoPopupService: ConcepcaoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.concepcaoPopupService
                    .open(ConcepcaoDialogComponent as Component, params['id']);
            } else {
                this.concepcaoPopupService
                    .open(ConcepcaoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
