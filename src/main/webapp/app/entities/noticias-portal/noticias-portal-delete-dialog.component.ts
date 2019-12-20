import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { NoticiasPortal } from './noticias-portal.model';
import { NoticiasPortalPopupService } from './noticias-portal-popup.service';
import { NoticiasPortalService } from './noticias-portal.service';

@Component({
    selector: 'jhi-noticias-portal-delete-dialog',
    templateUrl: './noticias-portal-delete-dialog.component.html'
})
export class NoticiasPortalDeleteDialogComponent {

    noticiasPortal: NoticiasPortal;

    constructor(
        private noticiasPortalService: NoticiasPortalService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.noticiasPortalService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'noticiasPortalListModification',
                content: 'Deleted an noticiasPortal'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-noticias-portal-delete-popup',
    template: ''
})
export class NoticiasPortalDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private noticiasPortalPopupService: NoticiasPortalPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.noticiasPortalPopupService
                .open(NoticiasPortalDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
