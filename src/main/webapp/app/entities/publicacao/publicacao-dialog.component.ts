import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Publicacao } from './publicacao.model';
import { PublicacaoPopupService } from './publicacao-popup.service';
import { PublicacaoService } from './publicacao.service';
import { Situacao, SituacaoService } from '../situacao';

@Component({
    selector: 'jhi-publicacao-dialog',
    templateUrl: './publicacao-dialog.component.html'
})
export class PublicacaoDialogComponent implements OnInit {

    publicacao: Publicacao;
    isSaving: boolean;

    situacaos: Situacao[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private publicacaoService: PublicacaoService,
        private situacaoService: SituacaoService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.situacaoService.query()
            .subscribe((res: HttpResponse<Situacao[]>) => { this.situacaos = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.publicacao.id !== undefined) {
            this.subscribeToSaveResponse(
                this.publicacaoService.update(this.publicacao));
        } else {
            this.subscribeToSaveResponse(
                this.publicacaoService.create(this.publicacao));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Publicacao>>) {
        result.subscribe((res: HttpResponse<Publicacao>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Publicacao) {
        this.eventManager.broadcast({ name: 'publicacaoListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackSituacaoById(index: number, item: Situacao) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-publicacao-popup',
    template: ''
})
export class PublicacaoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private publicacaoPopupService: PublicacaoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.publicacaoPopupService
                    .open(PublicacaoDialogComponent as Component, params['id']);
            } else {
                this.publicacaoPopupService
                    .open(PublicacaoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
