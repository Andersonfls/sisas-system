import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Provincia } from './provincia.model';
import { ProvinciaPopupService } from './provincia-popup.service';
import { ProvinciaService } from './provincia.service';

@Component({
    selector: 'jhi-provincia-delete-dialog',
    templateUrl: './provincia-delete-dialog.component.html'
})
export class ProvinciaDeleteDialogComponent {

    provincia: Provincia;

    constructor(
        private provinciaService: ProvinciaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.provinciaService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'provinciaListModification',
                content: 'Deleted an provincia'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-provincia-delete-popup',
    template: ''
})
export class ProvinciaDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private provinciaPopupService: ProvinciaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.provinciaPopupService
                .open(ProvinciaDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
