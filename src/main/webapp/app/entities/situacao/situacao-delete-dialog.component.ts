import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Situacao } from './situacao.model';
import { SituacaoPopupService } from './situacao-popup.service';
import { SituacaoService } from './situacao.service';

@Component({
    selector: 'jhi-situacao-delete-dialog',
    templateUrl: './situacao-delete-dialog.component.html'
})
export class SituacaoDeleteDialogComponent {

    situacao: Situacao;

    constructor(
        private situacaoService: SituacaoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.situacaoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'situacaoListModification',
                content: 'Deleted an situacao'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-situacao-delete-popup',
    template: ''
})
export class SituacaoDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private situacaoPopupService: SituacaoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.situacaoPopupService
                .open(SituacaoDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
