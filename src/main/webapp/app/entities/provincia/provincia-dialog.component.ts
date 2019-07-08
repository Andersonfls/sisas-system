import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Provincia } from './provincia.model';
import { ProvinciaPopupService } from './provincia-popup.service';
import { ProvinciaService } from './provincia.service';

@Component({
    selector: 'jhi-provincia-dialog',
    templateUrl: './provincia-dialog.component.html'
})
export class ProvinciaDialogComponent implements OnInit {

    provincia: Provincia;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private provinciaService: ProvinciaService,
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
        if (this.provincia.id !== undefined) {
            this.subscribeToSaveResponse(
                this.provinciaService.update(this.provincia));
        } else {
            this.subscribeToSaveResponse(
                this.provinciaService.create(this.provincia));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Provincia>>) {
        result.subscribe((res: HttpResponse<Provincia>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Provincia) {
        this.eventManager.broadcast({ name: 'provinciaListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-provincia-popup',
    template: ''
})
export class ProvinciaPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private provinciaPopupService: ProvinciaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.provinciaPopupService
                    .open(ProvinciaDialogComponent as Component, params['id']);
            } else {
                this.provinciaPopupService
                    .open(ProvinciaDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
