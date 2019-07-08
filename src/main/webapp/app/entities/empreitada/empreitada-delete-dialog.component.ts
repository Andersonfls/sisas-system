import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Empreitada } from './empreitada.model';
import { EmpreitadaPopupService } from './empreitada-popup.service';
import { EmpreitadaService } from './empreitada.service';

@Component({
    selector: 'jhi-empreitada-delete-dialog',
    templateUrl: './empreitada-delete-dialog.component.html'
})
export class EmpreitadaDeleteDialogComponent {

    empreitada: Empreitada;

    constructor(
        private empreitadaService: EmpreitadaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.empreitadaService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'empreitadaListModification',
                content: 'Deleted an empreitada'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-empreitada-delete-popup',
    template: ''
})
export class EmpreitadaDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private empreitadaPopupService: EmpreitadaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.empreitadaPopupService
                .open(EmpreitadaDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
