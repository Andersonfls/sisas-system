import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ConfiguracoesLog } from './configuracoes-log.model';
import { ConfiguracoesLogPopupService } from './configuracoes-log-popup.service';
import { ConfiguracoesLogService } from './configuracoes-log.service';

@Component({
    selector: 'jhi-configuracoes-log-dialog',
    templateUrl: './configuracoes-log-dialog.component.html'
})
export class ConfiguracoesLogDialogComponent implements OnInit {

    configuracoesLog: ConfiguracoesLog;
    isSaving: boolean;
    dtLogDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private configuracoesLogService: ConfiguracoesLogService,
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
        if (this.configuracoesLog.id !== undefined) {
            this.subscribeToSaveResponse(
                this.configuracoesLogService.update(this.configuracoesLog));
        } else {
            this.subscribeToSaveResponse(
                this.configuracoesLogService.create(this.configuracoesLog));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ConfiguracoesLog>>) {
        result.subscribe((res: HttpResponse<ConfiguracoesLog>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: ConfiguracoesLog) {
        this.eventManager.broadcast({ name: 'configuracoesLogListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-configuracoes-log-popup',
    template: ''
})
export class ConfiguracoesLogPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private configuracoesLogPopupService: ConfiguracoesLogPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.configuracoesLogPopupService
                    .open(ConfiguracoesLogDialogComponent as Component, params['id']);
            } else {
                this.configuracoesLogPopupService
                    .open(ConfiguracoesLogDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
