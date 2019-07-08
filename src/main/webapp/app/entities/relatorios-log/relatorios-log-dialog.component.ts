import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { RelatoriosLog } from './relatorios-log.model';
import { RelatoriosLogPopupService } from './relatorios-log-popup.service';
import { RelatoriosLogService } from './relatorios-log.service';

@Component({
    selector: 'jhi-relatorios-log-dialog',
    templateUrl: './relatorios-log-dialog.component.html'
})
export class RelatoriosLogDialogComponent implements OnInit {

    relatoriosLog: RelatoriosLog;
    isSaving: boolean;
    dtLogDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private relatoriosLogService: RelatoriosLogService,
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
        if (this.relatoriosLog.id !== undefined) {
            this.subscribeToSaveResponse(
                this.relatoriosLogService.update(this.relatoriosLog));
        } else {
            this.subscribeToSaveResponse(
                this.relatoriosLogService.create(this.relatoriosLog));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<RelatoriosLog>>) {
        result.subscribe((res: HttpResponse<RelatoriosLog>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: RelatoriosLog) {
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
        private relatoriosLogPopupService: RelatoriosLogPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.relatoriosLogPopupService
                    .open(RelatoriosLogDialogComponent as Component, params['id']);
            } else {
                this.relatoriosLogPopupService
                    .open(RelatoriosLogDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
