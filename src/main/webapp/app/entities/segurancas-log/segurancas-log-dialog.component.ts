import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { SegurancasLogPopupService } from './segurancas-log-popup.service';
import {SegurancasLog} from './segurancas-log.model';
import {SegurancasLogService} from './segurancas-log.service';

@Component({
    selector: 'jhi-relatorios-log-dialog',
    templateUrl: './segurancas-log-dialog.component.html'
})
export class SegurancasLogDialogComponent implements OnInit {

    segurancasLog: SegurancasLog;
    isSaving: boolean;
    dtLogDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private segurancasLogService: SegurancasLogService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.segurancasLog.id !== undefined) {
            this.subscribeToSaveResponse(
                this.segurancasLogService.update(this.segurancasLog));
        } else {
            this.subscribeToSaveResponse(
                this.segurancasLogService.create(this.segurancasLog));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<SegurancasLog>>) {
        result.subscribe((res: HttpResponse<SegurancasLog>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: SegurancasLog) {
        this.eventManager.broadcast({ name: 'relatoriosLogListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-relatorios-log-popup',
    template: ''
})
export class RelatoriosLogPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private segurancaLogPopUpService: SegurancasLogPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.segurancaLogPopUpService
                    .open(SegurancasLogDialogComponent as Component, params['id']);
            } else {
                this.segurancaLogPopUpService
                    .open(SegurancasLogDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
