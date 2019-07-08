import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { EntidadeGestora } from './entidade-gestora.model';
import { EntidadeGestoraPopupService } from './entidade-gestora-popup.service';
import { EntidadeGestoraService } from './entidade-gestora.service';
import { MunicipiosAtendidos, MunicipiosAtendidosService } from '../municipios-atendidos';

@Component({
    selector: 'jhi-entidade-gestora-dialog',
    templateUrl: './entidade-gestora-dialog.component.html'
})
export class EntidadeGestoraDialogComponent implements OnInit {

    entidadeGestora: EntidadeGestora;
    isSaving: boolean;

    municipiosatendidos: MunicipiosAtendidos[];
    dtConstituicaoDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private entidadeGestoraService: EntidadeGestoraService,
        private municipiosAtendidosService: MunicipiosAtendidosService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.municipiosAtendidosService.query()
            .subscribe((res: HttpResponse<MunicipiosAtendidos[]>) => { this.municipiosatendidos = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.entidadeGestora.id !== undefined) {
            this.subscribeToSaveResponse(
                this.entidadeGestoraService.update(this.entidadeGestora));
        } else {
            this.subscribeToSaveResponse(
                this.entidadeGestoraService.create(this.entidadeGestora));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<EntidadeGestora>>) {
        result.subscribe((res: HttpResponse<EntidadeGestora>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: EntidadeGestora) {
        this.eventManager.broadcast({ name: 'entidadeGestoraListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackMunicipiosAtendidosById(index: number, item: MunicipiosAtendidos) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-entidade-gestora-popup',
    template: ''
})
export class EntidadeGestoraPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private entidadeGestoraPopupService: EntidadeGestoraPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.entidadeGestoraPopupService
                    .open(EntidadeGestoraDialogComponent as Component, params['id']);
            } else {
                this.entidadeGestoraPopupService
                    .open(EntidadeGestoraDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
