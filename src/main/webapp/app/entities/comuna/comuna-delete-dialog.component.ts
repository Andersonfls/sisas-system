import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Comuna } from './comuna.model';
import { ComunaPopupService } from './comuna-popup.service';
import { ComunaService } from './comuna.service';

@Component({
    selector: 'jhi-comuna-delete-dialog',
    templateUrl: './comuna-delete-dialog.component.html'
})
export class ComunaDeleteDialogComponent {

    comuna: Comuna;

    constructor(
        private comunaService: ComunaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.comunaService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'comunaListModification',
                content: 'Deleted an comuna'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-comuna-delete-popup',
    template: ''
})
export class ComunaDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private comunaPopupService: ComunaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.comunaPopupService
                .open(ComunaDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
