import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { MunicipiosAtendidos } from './municipios-atendidos.model';
import { MunicipiosAtendidosPopupService } from './municipios-atendidos-popup.service';
import { MunicipiosAtendidosService } from './municipios-atendidos.service';
import { Municipio, MunicipioService } from '../municipio';

@Component({
    selector: 'jhi-municipios-atendidos-dialog',
    templateUrl: './municipios-atendidos-dialog.component.html'
})
export class MunicipiosAtendidosDialogComponent implements OnInit {

    municipiosAtendidos: MunicipiosAtendidos;
    isSaving: boolean;

    municipios: Municipio[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private municipiosAtendidosService: MunicipiosAtendidosService,
        private municipioService: MunicipioService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.municipioService.query()
            .subscribe((res: HttpResponse<Municipio[]>) => { this.municipios = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.municipiosAtendidos.id !== undefined) {
            this.subscribeToSaveResponse(
                this.municipiosAtendidosService.update(this.municipiosAtendidos));
        } else {
            this.subscribeToSaveResponse(
                this.municipiosAtendidosService.create(this.municipiosAtendidos));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<MunicipiosAtendidos>>) {
        result.subscribe((res: HttpResponse<MunicipiosAtendidos>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: MunicipiosAtendidos) {
        this.eventManager.broadcast({ name: 'municipiosAtendidosListModification', content: 'OK'});
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
    selector: 'jhi-municipios-atendidos-popup',
    template: ''
})
export class MunicipiosAtendidosPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private municipiosAtendidosPopupService: MunicipiosAtendidosPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.municipiosAtendidosPopupService
                    .open(MunicipiosAtendidosDialogComponent as Component, params['id']);
            } else {
                this.municipiosAtendidosPopupService
                    .open(MunicipiosAtendidosDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
