import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { NoticiasPortal } from './noticias-portal.model';
import { NoticiasPortalPopupService } from './noticias-portal-popup.service';
import { NoticiasPortalService } from './noticias-portal.service';

@Component({
    selector: 'jhi-noticias-portal-dialog',
    templateUrl: './noticias-portal-dialog.component.html'
})
export class NoticiasPortalDialogComponent implements OnInit {

    noticiasPortal: NoticiasPortal;
    isSaving: boolean;
    dataCriacaoDp: any;
    dataAlteracaoDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private noticiasPortalService: NoticiasPortalService,
        private elementRef: ElementRef,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.noticiasPortal, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        console.log(this.noticiasPortal);
        if (this.noticiasPortal.id !== undefined) {
            this.subscribeToSaveResponse(
                this.noticiasPortalService.update(this.noticiasPortal));
        } else {
            this.subscribeToSaveResponse(
                this.noticiasPortalService.create(this.noticiasPortal));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<NoticiasPortal>>) {
        result.subscribe((res: HttpResponse<NoticiasPortal>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: NoticiasPortal) {
        this.eventManager.broadcast({ name: 'noticiasPortalListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-noticias-portal-popup',
    template: ''
})
export class NoticiasPortalPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private noticiasPortalPopupService: NoticiasPortalPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.noticiasPortalPopupService
                    .open(NoticiasPortalDialogComponent as Component, params['id']);
            } else {
                this.noticiasPortalPopupService
                    .open(NoticiasPortalDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
