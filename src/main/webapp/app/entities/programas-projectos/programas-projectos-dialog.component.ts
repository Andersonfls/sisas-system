import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ProgramasProjectos } from './programas-projectos.model';
import { ProgramasProjectosPopupService } from './programas-projectos-popup.service';
import { ProgramasProjectosService } from './programas-projectos.service';
import { Comuna, ComunaService } from '../comuna';

@Component({
    selector: 'jhi-programas-projectos-dialog',
    templateUrl: './programas-projectos-dialog.component.html'
})
export class ProgramasProjectosDialogComponent implements OnInit {

    programasProjectos: ProgramasProjectos;
    isSaving: boolean;

    comunas: Comuna[];
    dtLancamentoDp: any;
    dtUltimaAlteracaoDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private programasProjectosService: ProgramasProjectosService,
        private comunaService: ComunaService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.comunaService.query()
            .subscribe((res: HttpResponse<Comuna[]>) => { this.comunas = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.programasProjectos.id !== undefined) {
            this.subscribeToSaveResponse(
                this.programasProjectosService.update(this.programasProjectos));
        } else {
            this.subscribeToSaveResponse(
                this.programasProjectosService.create(this.programasProjectos));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ProgramasProjectos>>) {
        result.subscribe((res: HttpResponse<ProgramasProjectos>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: ProgramasProjectos) {
        this.eventManager.broadcast({ name: 'programasProjectosListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackComunaById(index: number, item: Comuna) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-programas-projectos-popup',
    template: ''
})
export class ProgramasProjectosPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private programasProjectosPopupService: ProgramasProjectosPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.programasProjectosPopupService
                    .open(ProgramasProjectosDialogComponent as Component, params['id']);
            } else {
                this.programasProjectosPopupService
                    .open(ProgramasProjectosDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
