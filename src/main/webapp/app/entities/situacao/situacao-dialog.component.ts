import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Situacao } from './situacao.model';
import { SituacaoPopupService } from './situacao-popup.service';
import { SituacaoService } from './situacao.service';

@Component({
    selector: 'jhi-situacao-dialog',
    templateUrl: './situacao-dialog.component.html'
})
export class SituacaoDialogComponent implements OnInit {

    situacao: Situacao;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private situacaoService: SituacaoService,
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
        if (this.situacao.id !== undefined) {
            this.subscribeToSaveResponse(
                this.situacaoService.update(this.situacao));
        } else {
            this.subscribeToSaveResponse(
                this.situacaoService.create(this.situacao));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Situacao>>) {
        result.subscribe((res: HttpResponse<Situacao>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Situacao) {
        this.eventManager.broadcast({ name: 'situacaoListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-situacao-popup',
    template: ''
})
export class SituacaoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private situacaoPopupService: SituacaoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.situacaoPopupService
                    .open(SituacaoDialogComponent as Component, params['id']);
            } else {
                this.situacaoPopupService
                    .open(SituacaoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
