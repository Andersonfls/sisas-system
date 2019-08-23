import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Comuna } from './comuna.model';
import { ComunaPopupService } from './comuna-popup.service';
import { ComunaService } from './comuna.service';
import { Municipio, MunicipioService } from '../municipio';

@Component({
    selector: 'jhi-comuna-dialog',
    templateUrl: './comuna-dialog.component.html'
})
export class ComunaDialogComponent implements OnInit {

    comuna: Comuna;
    isSaving: boolean;

    municipios: Municipio[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private comunaService: ComunaService,
        private municipioService: MunicipioService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.municipioService.query()
            .subscribe((res: HttpResponse<Municipio[]>) => { this.municipios = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        console.log(this.comuna);

        this.comuna.municipio = null;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.comuna.id !== undefined) {
            this.subscribeToSaveResponse(
                this.comunaService.update(this.comuna));
        } else {
            this.subscribeToSaveResponse(
                this.comunaService.create(this.comuna));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Comuna>>) {
        result.subscribe((res: HttpResponse<Comuna>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Comuna) {
        this.eventManager.broadcast({ name: 'comunaListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackMunicipioById(index: number, item: Municipio) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-comuna-popup',
    template: ''
})
export class ComunaPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private comunaPopupService: ComunaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.comunaPopupService
                    .open(ComunaDialogComponent as Component, params['id']);
            } else {
                this.comunaPopupService
                    .open(ComunaDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
