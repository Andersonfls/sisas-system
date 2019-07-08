import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Contrato } from './contrato.model';
import { ContratoPopupService } from './contrato-popup.service';
import { ContratoService } from './contrato.service';

@Component({
    selector: 'jhi-contrato-delete-dialog',
    templateUrl: './contrato-delete-dialog.component.html'
})
export class ContratoDeleteDialogComponent {

    contrato: Contrato;

    constructor(
        private contratoService: ContratoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.contratoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'contratoListModification',
                content: 'Deleted an contrato'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-contrato-delete-popup',
    template: ''
})
export class ContratoDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private contratoPopupService: ContratoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.contratoPopupService
                .open(ContratoDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
