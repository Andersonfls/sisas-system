import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Fases } from './fases.model';
import { FasesPopupService } from './fases-popup.service';
import { FasesService } from './fases.service';

@Component({
    selector: 'jhi-fases-delete-dialog',
    templateUrl: './fases-delete-dialog.component.html'
})
export class FasesDeleteDialogComponent {

    fases: Fases;

    constructor(
        private fasesService: FasesService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.fasesService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'fasesListModification',
                content: 'Deleted an fases'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-fases-delete-popup',
    template: ''
})
export class FasesDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private fasesPopupService: FasesPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.fasesPopupService
                .open(FasesDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
