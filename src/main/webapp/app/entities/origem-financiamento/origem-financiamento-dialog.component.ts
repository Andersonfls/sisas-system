import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { OrigemFinanciamento } from './origem-financiamento.model';
import { OrigemFinanciamentoPopupService } from './origem-financiamento-popup.service';
import { OrigemFinanciamentoService } from './origem-financiamento.service';

@Component({
    selector: 'jhi-origem-financiamento-dialog',
    templateUrl: './origem-financiamento-dialog.component.html'
})
export class OrigemFinanciamentoDialogComponent implements OnInit {

    origemFinanciamento: OrigemFinanciamento;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private origemFinanciamentoService: OrigemFinanciamentoService,
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
        if (this.origemFinanciamento.id !== undefined) {
            this.subscribeToSaveResponse(
                this.origemFinanciamentoService.update(this.origemFinanciamento));
        } else {
            this.subscribeToSaveResponse(
                this.origemFinanciamentoService.create(this.origemFinanciamento));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<OrigemFinanciamento>>) {
        result.subscribe((res: HttpResponse<OrigemFinanciamento>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: OrigemFinanciamento) {
        this.eventManager.broadcast({ name: 'origemFinanciamentoListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-origem-financiamento-popup',
    template: ''
})
export class OrigemFinanciamentoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private origemFinanciamentoPopupService: OrigemFinanciamentoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.origemFinanciamentoPopupService
                    .open(OrigemFinanciamentoDialogComponent as Component, params['id']);
            } else {
                this.origemFinanciamentoPopupService
                    .open(OrigemFinanciamentoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
