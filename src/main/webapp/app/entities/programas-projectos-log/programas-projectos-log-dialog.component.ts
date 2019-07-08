import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ProgramasProjectosLog } from './programas-projectos-log.model';
import { ProgramasProjectosLogPopupService } from './programas-projectos-log-popup.service';
import { ProgramasProjectosLogService } from './programas-projectos-log.service';
import { ProgramasProjectos, ProgramasProjectosService } from '../programas-projectos';

@Component({
    selector: 'jhi-programas-projectos-log-dialog',
    templateUrl: './programas-projectos-log-dialog.component.html'
})
export class ProgramasProjectosLogDialogComponent implements OnInit {

    programasProjectosLog: ProgramasProjectosLog;
    isSaving: boolean;

    programasprojectos: ProgramasProjectos[];
    dtLogDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private programasProjectosLogService: ProgramasProjectosLogService,
        private programasProjectosService: ProgramasProjectosService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.programasProjectosService.query()
            .subscribe((res: HttpResponse<ProgramasProjectos[]>) => { this.programasprojectos = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.programasProjectosLog.id !== undefined) {
            this.subscribeToSaveResponse(
                this.programasProjectosLogService.update(this.programasProjectosLog));
        } else {
            this.subscribeToSaveResponse(
                this.programasProjectosLogService.create(this.programasProjectosLog));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ProgramasProjectosLog>>) {
        result.subscribe((res: HttpResponse<ProgramasProjectosLog>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: ProgramasProjectosLog) {
        this.eventManager.broadcast({ name: 'programasProjectosLogListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackProgramasProjectosById(index: number, item: ProgramasProjectos) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-programas-projectos-log-popup',
    template: ''
})
export class ProgramasProjectosLogPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private programasProjectosLogPopupService: ProgramasProjectosLogPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.programasProjectosLogPopupService
                    .open(ProgramasProjectosLogDialogComponent as Component, params['id']);
            } else {
                this.programasProjectosLogPopupService
                    .open(ProgramasProjectosLogDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
