import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Fases } from './fases.model';
import { FasesPopupService } from './fases-popup.service';
import { FasesService } from './fases.service';

@Component({
    selector: 'jhi-fases-dialog',
    templateUrl: './fases-dialog.component.html'
})
export class FasesDialogComponent implements OnInit {

    fases: Fases;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private fasesService: FasesService,
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
        if (this.fases.id !== undefined) {
            this.subscribeToSaveResponse(
                this.fasesService.update(this.fases));
        } else {
            this.subscribeToSaveResponse(
                this.fasesService.create(this.fases));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Fases>>) {
        result.subscribe((res: HttpResponse<Fases>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Fases) {
        this.eventManager.broadcast({ name: 'fasesListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-fases-popup',
    template: ''
})
export class FasesPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private fasesPopupService: FasesPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.fasesPopupService
                    .open(FasesDialogComponent as Component, params['id']);
            } else {
                this.fasesPopupService
                    .open(FasesDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
