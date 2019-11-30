import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { FinalidadeProjeto } from './finalidade-projeto.model';
import { FinalidadeProjetoPopupService } from './finalidade-projeto-popup.service';
import { FinalidadeProjetoService } from './finalidade-projeto.service';

@Component({
    selector: 'jhi-finalidade-projeto-delete-dialog',
    templateUrl: './finalidade-projeto-delete-dialog.component.html'
})
export class FinalidadeProjetoDeleteDialogComponent {

    finalidadeProjeto: FinalidadeProjeto;

    constructor(
        private finalidadeProjetoService: FinalidadeProjetoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.finalidadeProjetoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'finalidadeProjetoListModification',
                content: 'Deleted an finalidadeProjeto'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-finalidade-projeto-delete-popup',
    template: ''
})
export class FinalidadeProjetoDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private finalidadeProjetoPopupService: FinalidadeProjetoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.finalidadeProjetoPopupService
                .open(FinalidadeProjetoDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
