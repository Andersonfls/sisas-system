import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { PublicacaoLog } from './publicacao-log.model';
import { PublicacaoLogPopupService } from './publicacao-log-popup.service';
import { PublicacaoLogService } from './publicacao-log.service';

@Component({
    selector: 'jhi-publicacao-log-dialog',
    templateUrl: './publicacao-log-dialog.component.html'
})
export class PublicacaoLogDialogComponent implements OnInit {

    publicacaoLog: PublicacaoLog;
    isSaving: boolean;
    dtLogDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private publicacaoLogService: PublicacaoLogService,
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
        if (this.publicacaoLog.id !== undefined) {
            this.subscribeToSaveResponse(
                this.publicacaoLogService.update(this.publicacaoLog));
        } else {
            this.subscribeToSaveResponse(
                this.publicacaoLogService.create(this.publicacaoLog));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<PublicacaoLog>>) {
        result.subscribe((res: HttpResponse<PublicacaoLog>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: PublicacaoLog) {
        this.eventManager.broadcast({ name: 'publicacaoLogListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-publicacao-log-popup',
    template: ''
})
export class PublicacaoLogPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private publicacaoLogPopupService: PublicacaoLogPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.publicacaoLogPopupService
                    .open(PublicacaoLogDialogComponent as Component, params['id']);
            } else {
                this.publicacaoLogPopupService
                    .open(PublicacaoLogDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
