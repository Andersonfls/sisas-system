import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { SistemaAguaLog } from './sistema-agua-log.model';
import { SistemaAguaLogPopupService } from './sistema-agua-log-popup.service';
import { SistemaAguaLogService } from './sistema-agua-log.service';
import { SistemaAgua, SistemaAguaService } from '../sistema-agua';

@Component({
    selector: 'jhi-sistema-agua-log-dialog',
    templateUrl: './sistema-agua-log-dialog.component.html'
})
export class SistemaAguaLogDialogComponent implements OnInit {

    sistemaAguaLog: SistemaAguaLog;
    isSaving: boolean;

    sistemaaguas: SistemaAgua[];
    dtLogDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private sistemaAguaLogService: SistemaAguaLogService,
        private sistemaAguaService: SistemaAguaService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.sistemaAguaService.query()
            .subscribe((res: HttpResponse<SistemaAgua[]>) => { this.sistemaaguas = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.sistemaAguaLog.id !== undefined) {
            this.subscribeToSaveResponse(
                this.sistemaAguaLogService.update(this.sistemaAguaLog));
        } else {
            this.subscribeToSaveResponse(
                this.sistemaAguaLogService.create(this.sistemaAguaLog));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<SistemaAguaLog>>) {
        result.subscribe((res: HttpResponse<SistemaAguaLog>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: SistemaAguaLog) {
        this.eventManager.broadcast({ name: 'sistemaAguaLogListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackSistemaAguaById(index: number, item: SistemaAgua) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-sistema-agua-log-popup',
    template: ''
})
export class SistemaAguaLogPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private sistemaAguaLogPopupService: SistemaAguaLogPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.sistemaAguaLogPopupService
                    .open(SistemaAguaLogDialogComponent as Component, params['id']);
            } else {
                this.sistemaAguaLogPopupService
                    .open(SistemaAguaLogDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
