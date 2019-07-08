import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Concurso } from './concurso.model';
import { ConcursoPopupService } from './concurso-popup.service';
import { ConcursoService } from './concurso.service';
import { ProgramasProjectos, ProgramasProjectosService } from '../programas-projectos';
import { SistemaAgua, SistemaAguaService } from '../sistema-agua';

@Component({
    selector: 'jhi-concurso-dialog',
    templateUrl: './concurso-dialog.component.html'
})
export class ConcursoDialogComponent implements OnInit {

    concurso: Concurso;
    isSaving: boolean;

    programasprojectos: ProgramasProjectos[];

    sistemaaguas: SistemaAgua[];
    dtLancamentoDp: any;
    dtUltimaAlteracaoDp: any;
    dtEntregaPropostaDp: any;
    dtAberturaPropostaDp: any;
    dtConclusaoAvaliacaoRelPrelDp: any;
    dtNegociacaoDp: any;
    dtAprovRelAvalFinalDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private concursoService: ConcursoService,
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
        if (this.concurso.id !== undefined) {
            this.subscribeToSaveResponse(
                this.concursoService.update(this.concurso));
        } else {
            this.subscribeToSaveResponse(
                this.concursoService.create(this.concurso));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Concurso>>) {
        result.subscribe((res: HttpResponse<Concurso>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Concurso) {
        this.eventManager.broadcast({ name: 'concursoListModification', content: 'OK'});
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
    selector: 'jhi-concurso-popup',
    template: ''
})
export class ConcursoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private concursoPopupService: ConcursoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.concursoPopupService
                    .open(ConcursoDialogComponent as Component, params['id']);
            } else {
                this.concursoPopupService
                    .open(ConcursoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
