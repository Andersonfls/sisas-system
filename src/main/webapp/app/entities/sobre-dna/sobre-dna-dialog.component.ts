import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { SobreDna } from './sobre-dna.model';
import { SobreDnaPopupService } from './sobre-dna-popup.service';
import { SobreDnaService } from './sobre-dna.service';
import { Situacao, SituacaoService } from '../situacao';

@Component({
    selector: 'jhi-sobre-dna-dialog',
    templateUrl: './sobre-dna-dialog.component.html'
})
export class SobreDnaDialogComponent implements OnInit {

    sobreDna: SobreDna;
    isSaving: boolean;

    situacaos: Situacao[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private sobreDnaService: SobreDnaService,
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
        if (this.sobreDna.id !== undefined) {
            this.subscribeToSaveResponse(
                this.sobreDnaService.update(this.sobreDna));
        } else {
            this.subscribeToSaveResponse(
                this.sobreDnaService.create(this.sobreDna));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<SobreDna>>) {
        result.subscribe((res: HttpResponse<SobreDna>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: SobreDna) {
        this.eventManager.broadcast({ name: 'sobreDnaListModification', content: 'OK'});
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
    selector: 'jhi-sobre-dna-popup',
    template: ''
})
export class SobreDnaPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private sobreDnaPopupService: SobreDnaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.sobreDnaPopupService
                    .open(SobreDnaDialogComponent as Component, params['id']);
            } else {
                this.sobreDnaPopupService
                    .open(SobreDnaDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
