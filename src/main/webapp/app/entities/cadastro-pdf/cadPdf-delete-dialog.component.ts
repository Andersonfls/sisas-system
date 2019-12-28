import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CadPdfPopupService } from './cadPdf-popup.service';
import { CadPdfService } from './cadPdf.service';
import {Banner} from './cadPdf.model';

@Component({
    selector: 'jhi-produto-delete-dialog',
    templateUrl: './cadPdf-delete-dialog.component.html'
})
export class CadPdfDeleteDialogComponent {

    produto: Banner;

    constructor(
        private bannerService: CadPdfService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.bannerService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'produtoListModification',
                content: 'Deleted an Banner'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-produto-delete-popup',
    template: ''
})
export class ProdutoDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private produtoPopupService: CadPdfPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.produtoPopupService
                .open(CadPdfDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
