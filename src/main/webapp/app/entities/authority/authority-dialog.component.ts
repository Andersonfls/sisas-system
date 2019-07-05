import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { Authority } from './authority.model';
import { AuthorityPopupService } from './authority-popup.service';
import { AuthorityService } from './authority.service';

@Component({
    selector: 'jhi-authority-dialog',
    templateUrl: './authority-dialog.component.html'
})
export class AuthorityDialogComponent implements OnInit {

    authority: Authority;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private authorityService: AuthorityService,
        private eventManager: JhiEventManager
    ) {}

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.authority.name !== undefined) {
            this.subscribeToSaveResponse(
                this.authorityService.update(this.authority));
        } else {
            this.subscribeToSaveResponse(
                this.authorityService.create(this.authority));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Authority>>) {
        result.subscribe((res: HttpResponse<Authority>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Authority) {
        this.eventManager.broadcast({ name: 'authorityListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-authority-popup',
    template: ''
})
export class AuthorityPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private authorityPopupService: AuthorityPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['name'] ) {
                this.authorityPopupService
                    .open(AuthorityDialogComponent as Component, params['name']);
            } else {
                this.authorityPopupService
                    .open(AuthorityDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
