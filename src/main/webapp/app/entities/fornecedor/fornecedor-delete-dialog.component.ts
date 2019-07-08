import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Fornecedor } from './fornecedor.model';
import { FornecedorPopupService } from './fornecedor-popup.service';
import { FornecedorService } from './fornecedor.service';

@Component({
    selector: 'jhi-fornecedor-delete-dialog',
    templateUrl: './fornecedor-delete-dialog.component.html'
})
export class FornecedorDeleteDialogComponent {

    fornecedor: Fornecedor;

    constructor(
        private fornecedorService: FornecedorService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.fornecedorService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'fornecedorListModification',
                content: 'Deleted an fornecedor'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-fornecedor-delete-popup',
    template: ''
})
export class FornecedorDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private fornecedorPopupService: FornecedorPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.fornecedorPopupService
                .open(FornecedorDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
