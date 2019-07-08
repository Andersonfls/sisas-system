import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { OrigemFinanciamento } from './origem-financiamento.model';
import { OrigemFinanciamentoPopupService } from './origem-financiamento-popup.service';
import { OrigemFinanciamentoService } from './origem-financiamento.service';

@Component({
    selector: 'jhi-origem-financiamento-delete-dialog',
    templateUrl: './origem-financiamento-delete-dialog.component.html'
})
export class OrigemFinanciamentoDeleteDialogComponent {

    origemFinanciamento: OrigemFinanciamento;

    constructor(
        private origemFinanciamentoService: OrigemFinanciamentoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.origemFinanciamentoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'origemFinanciamentoListModification',
                content: 'Deleted an origemFinanciamento'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-origem-financiamento-delete-popup',
    template: ''
})
export class OrigemFinanciamentoDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private origemFinanciamentoPopupService: OrigemFinanciamentoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.origemFinanciamentoPopupService
                .open(OrigemFinanciamentoDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
