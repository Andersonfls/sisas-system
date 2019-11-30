import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Epas } from './epas.model';
import { EpasPopupService } from './epas-popup.service';
import { EpasService } from './epas.service';

@Component({
    selector: 'jhi-epas-delete-dialog',
    templateUrl: './epas-delete-dialog.component.html'
})
export class EpasDeleteDialogComponent {

    epas: Epas;

    constructor(
        private epasService: EpasService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.epasService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'epasListModification',
                content: 'Deleted an epas'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-epas-delete-popup',
    template: ''
})
export class EpasDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private epasPopupService: EpasPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.epasPopupService
                .open(EpasDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
