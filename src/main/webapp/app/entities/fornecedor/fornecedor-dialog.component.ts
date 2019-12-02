import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import {JhiAlertService, JhiEventManager} from 'ng-jhipster';

import { Fornecedor } from './fornecedor.model';
import { FornecedorPopupService } from './fornecedor-popup.service';
import { FornecedorService } from './fornecedor.service';
import {Especialidades} from '../especialidades/especialidades.model';
import {EspecialidadesService} from '../especialidades/especialidades.service';

@Component({
    selector: 'jhi-fornecedor-dialog',
    templateUrl: './fornecedor-dialog.component.html'
})
export class FornecedorDialogComponent implements OnInit {

    fornecedor: Fornecedor;
    isSaving: boolean;

    especialidadess: Especialidades[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private fornecedorService: FornecedorService,
        private especialidadesService: EspecialidadesService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;

    this.especialidadesService.query()
        .subscribe((res: HttpResponse<Especialidades[]>) => { this.especialidadess = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    console.log(this.fornecedor);

    this.fornecedor.especialidades = null;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.fornecedor.id !== undefined) {
            this.subscribeToSaveResponse(
                this.fornecedorService.update(this.fornecedor));
        } else {
            this.subscribeToSaveResponse(
                this.fornecedorService.create(this.fornecedor));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Fornecedor>>) {
        result.subscribe((res: HttpResponse<Fornecedor>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Fornecedor) {
        this.eventManager.broadcast({ name: 'fornecedorListModification', content: 'OK'});
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
    selector: 'jhi-fornecedor-popup',
    template: ''
})
export class FornecedorPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private fornecedorPopupService: FornecedorPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.fornecedorPopupService
                    .open(FornecedorDialogComponent as Component, params['id']);
            } else {
                this.fornecedorPopupService
                    .open(FornecedorDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
