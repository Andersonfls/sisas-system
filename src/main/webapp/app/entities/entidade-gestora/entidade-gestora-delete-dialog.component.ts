import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { EntidadeGestora } from './entidade-gestora.model';
import { EntidadeGestoraPopupService } from './entidade-gestora-popup.service';
import { EntidadeGestoraService } from './entidade-gestora.service';

@Component({
    selector: 'jhi-entidade-gestora-delete-dialog',
    templateUrl: './entidade-gestora-delete-dialog.component.html'
})
export class EntidadeGestoraDeleteDialogComponent {

    entidadeGestora: EntidadeGestora;

    constructor(
        private entidadeGestoraService: EntidadeGestoraService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.entidadeGestoraService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'entidadeGestoraListModification',
                content: 'Deleted an entidadeGestora'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-entidade-gestora-delete-popup',
    template: ''
})
export class EntidadeGestoraDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private entidadeGestoraPopupService: EntidadeGestoraPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.entidadeGestoraPopupService
                .open(EntidadeGestoraDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
