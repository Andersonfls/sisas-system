import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Projectos } from './projectos.model';
import { ProjectosPopupService } from './projectos-popup.service';
import { ProjectosService } from './projectos.service';
import { Situacao, SituacaoService } from '../situacao';

@Component({
    selector: 'jhi-projectos-dialog',
    templateUrl: './projectos-dialog.component.html'
})
export class ProjectosDialogComponent implements OnInit {

    projectos: Projectos;
    isSaving: boolean;

    situacaos: Situacao[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private projectosService: ProjectosService,
        private situacaoService: SituacaoService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.situacaoService.query()
            .subscribe((res: HttpResponse<Situacao[]>) => { this.situacaos = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.projectos.id !== undefined) {
            this.subscribeToSaveResponse(
                this.projectosService.update(this.projectos));
        } else {
            this.subscribeToSaveResponse(
                this.projectosService.create(this.projectos));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Projectos>>) {
        result.subscribe((res: HttpResponse<Projectos>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Projectos) {
        this.eventManager.broadcast({ name: 'projectosListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackSituacaoById(index: number, item: Situacao) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-projectos-popup',
    template: ''
})
export class ProjectosPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private projectosPopupService: ProjectosPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.projectosPopupService
                    .open(ProjectosDialogComponent as Component, params['id']);
            } else {
                this.projectosPopupService
                    .open(ProjectosDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
