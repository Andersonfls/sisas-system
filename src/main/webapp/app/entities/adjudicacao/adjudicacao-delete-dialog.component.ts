import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Adjudicacao } from './adjudicacao.model';
import { AdjudicacaoPopupService } from './adjudicacao-popup.service';
import { AdjudicacaoService } from './adjudicacao.service';

@Component({
    selector: 'jhi-adjudicacao-delete-dialog',
    templateUrl: './adjudicacao-delete-dialog.component.html'
})
export class AdjudicacaoDeleteDialogComponent {

    adjudicacao: Adjudicacao;

    constructor(
        private adjudicacaoService: AdjudicacaoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.adjudicacaoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'adjudicacaoListModification',
                content: 'Deleted an adjudicacao'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-adjudicacao-delete-popup',
    template: ''
})
export class AdjudicacaoDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private adjudicacaoPopupService: AdjudicacaoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.adjudicacaoPopupService
                .open(AdjudicacaoDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
