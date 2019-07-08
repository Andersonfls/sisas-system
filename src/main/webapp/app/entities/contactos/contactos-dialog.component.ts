import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Contactos } from './contactos.model';
import { ContactosPopupService } from './contactos-popup.service';
import { ContactosService } from './contactos.service';
import { Situacao, SituacaoService } from '../situacao';

@Component({
    selector: 'jhi-contactos-dialog',
    templateUrl: './contactos-dialog.component.html'
})
export class ContactosDialogComponent implements OnInit {

    contactos: Contactos;
    isSaving: boolean;

    situacaos: Situacao[];
    dtLancamentoDp: any;
    dtUltimaAlteracaoDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private contactosService: ContactosService,
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
        if (this.contactos.id !== undefined) {
            this.subscribeToSaveResponse(
                this.contactosService.update(this.contactos));
        } else {
            this.subscribeToSaveResponse(
                this.contactosService.create(this.contactos));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Contactos>>) {
        result.subscribe((res: HttpResponse<Contactos>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Contactos) {
        this.eventManager.broadcast({ name: 'contactosListModification', content: 'OK'});
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
    selector: 'jhi-contactos-popup',
    template: ''
})
export class ContactosPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private contactosPopupService: ContactosPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.contactosPopupService
                    .open(ContactosDialogComponent as Component, params['id']);
            } else {
                this.contactosPopupService
                    .open(ContactosDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
