import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Empreitada } from './empreitada.model';
import { EmpreitadaPopupService } from './empreitada-popup.service';
import { EmpreitadaService } from './empreitada.service';
import { ProgramasProjectos, ProgramasProjectosService } from '../programas-projectos';
import { SistemaAgua, SistemaAguaService } from '../sistema-agua';
import { Contrato, ContratoService } from '../contrato';

@Component({
    selector: 'jhi-empreitada-dialog',
    templateUrl: './empreitada-dialog.component.html'
})
export class EmpreitadaDialogComponent implements OnInit {

    empreitada: Empreitada;
    isSaving: boolean;

    programasprojectos: ProgramasProjectos[];

    sistemaaguas: SistemaAgua[];

    contratoes: Contrato[];
    dtLancamentoDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private empreitadaService: EmpreitadaService,
        private programasProjectosService: ProgramasProjectosService,
        private sistemaAguaService: SistemaAguaService,
        private contratoService: ContratoService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
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
        if (this.empreitada.id !== undefined) {
            this.subscribeToSaveResponse(
                this.empreitadaService.update(this.empreitada));
        } else {
            this.subscribeToSaveResponse(
                this.empreitadaService.create(this.empreitada));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Empreitada>>) {
        result.subscribe((res: HttpResponse<Empreitada>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Empreitada) {
        this.eventManager.broadcast({ name: 'empreitadaListModification', content: 'OK'});
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

    trackContratoById(index: number, item: Contrato) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-empreitada-popup',
    template: ''
})
export class EmpreitadaPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private empreitadaPopupService: EmpreitadaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.empreitadaPopupService
                    .open(EmpreitadaDialogComponent as Component, params['id']);
            } else {
                this.empreitadaPopupService
                    .open(EmpreitadaDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
