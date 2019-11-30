import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { FinalidadeProjeto } from './finalidade-projeto.model';
import { FinalidadeProjetoPopupService } from './finalidade-projeto-popup.service';
import { FinalidadeProjetoService } from './finalidade-projeto.service';

@Component({
    selector: 'jhi-finalidade-projeto-dialog',
    templateUrl: './finalidade-projeto-dialog.component.html'
})
export class FinalidadeProjetoDialogComponent implements OnInit {

    finalidadeProjeto: FinalidadeProjeto;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private finalidadeProjetoService: FinalidadeProjetoService,
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
        if (this.finalidadeProjeto.id !== undefined) {
            this.subscribeToSaveResponse(
                this.finalidadeProjetoService.update(this.finalidadeProjeto));
        } else {
            this.subscribeToSaveResponse(
                this.finalidadeProjetoService.create(this.finalidadeProjeto));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<FinalidadeProjeto>>) {
        result.subscribe((res: HttpResponse<FinalidadeProjeto>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: FinalidadeProjeto) {
        this.eventManager.broadcast({ name: 'finalidadeProjetoListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-finalidade-projeto-popup',
    template: ''
})
export class FinalidadeProjetoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private finalidadeProjetoPopupService: FinalidadeProjetoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.finalidadeProjetoPopupService
                    .open(FinalidadeProjetoDialogComponent as Component, params['id']);
            } else {
                this.finalidadeProjetoPopupService
                    .open(FinalidadeProjetoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
