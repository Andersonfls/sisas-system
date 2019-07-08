import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Publicacao } from './publicacao.model';
import { PublicacaoPopupService } from './publicacao-popup.service';
import { PublicacaoService } from './publicacao.service';

@Component({
    selector: 'jhi-publicacao-delete-dialog',
    templateUrl: './publicacao-delete-dialog.component.html'
})
export class PublicacaoDeleteDialogComponent {

    publicacao: Publicacao;

    constructor(
        private publicacaoService: PublicacaoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.publicacaoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'publicacaoListModification',
                content: 'Deleted an publicacao'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-publicacao-delete-popup',
    template: ''
})
export class PublicacaoDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private publicacaoPopupService: PublicacaoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.publicacaoPopupService
                .open(PublicacaoDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
