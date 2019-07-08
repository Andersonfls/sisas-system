import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IndicadorProducaoLog } from './indicador-producao-log.model';
import { IndicadorProducaoLogPopupService } from './indicador-producao-log-popup.service';
import { IndicadorProducaoLogService } from './indicador-producao-log.service';
import { IndicadorProducao, IndicadorProducaoService } from '../indicador-producao';

@Component({
    selector: 'jhi-indicador-producao-log-dialog',
    templateUrl: './indicador-producao-log-dialog.component.html'
})
export class IndicadorProducaoLogDialogComponent implements OnInit {

    indicadorProducaoLog: IndicadorProducaoLog;
    isSaving: boolean;

    indicadorproducaos: IndicadorProducao[];
    dtLogDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private indicadorProducaoLogService: IndicadorProducaoLogService,
        private indicadorProducaoService: IndicadorProducaoService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.indicadorProducaoService.query()
            .subscribe((res: HttpResponse<IndicadorProducao[]>) => { this.indicadorproducaos = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.indicadorProducaoLog.id !== undefined) {
            this.subscribeToSaveResponse(
                this.indicadorProducaoLogService.update(this.indicadorProducaoLog));
        } else {
            this.subscribeToSaveResponse(
                this.indicadorProducaoLogService.create(this.indicadorProducaoLog));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IndicadorProducaoLog>>) {
        result.subscribe((res: HttpResponse<IndicadorProducaoLog>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: IndicadorProducaoLog) {
        this.eventManager.broadcast({ name: 'indicadorProducaoLogListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackIndicadorProducaoById(index: number, item: IndicadorProducao) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-indicador-producao-log-popup',
    template: ''
})
export class IndicadorProducaoLogPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private indicadorProducaoLogPopupService: IndicadorProducaoLogPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.indicadorProducaoLogPopupService
                    .open(IndicadorProducaoLogDialogComponent as Component, params['id']);
            } else {
                this.indicadorProducaoLogPopupService
                    .open(IndicadorProducaoLogDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
