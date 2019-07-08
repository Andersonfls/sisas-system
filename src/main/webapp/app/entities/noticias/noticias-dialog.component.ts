import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Noticias } from './noticias.model';
import { NoticiasPopupService } from './noticias-popup.service';
import { NoticiasService } from './noticias.service';
import { Situacao, SituacaoService } from '../situacao';

@Component({
    selector: 'jhi-noticias-dialog',
    templateUrl: './noticias-dialog.component.html'
})
export class NoticiasDialogComponent implements OnInit {

    noticias: Noticias;
    isSaving: boolean;

    situacaos: Situacao[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private noticiasService: NoticiasService,
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
        if (this.noticias.id !== undefined) {
            this.subscribeToSaveResponse(
                this.noticiasService.update(this.noticias));
        } else {
            this.subscribeToSaveResponse(
                this.noticiasService.create(this.noticias));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Noticias>>) {
        result.subscribe((res: HttpResponse<Noticias>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Noticias) {
        this.eventManager.broadcast({ name: 'noticiasListModification', content: 'OK'});
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
    selector: 'jhi-noticias-popup',
    template: ''
})
export class NoticiasPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private noticiasPopupService: NoticiasPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.noticiasPopupService
                    .open(NoticiasDialogComponent as Component, params['id']);
            } else {
                this.noticiasPopupService
                    .open(NoticiasDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
