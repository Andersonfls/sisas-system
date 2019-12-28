import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { BannerPopupService } from './banner-popup.service';
import { BannerService } from './banner.service';
import {Banner} from './banner.model';

@Component({
    selector: 'jhi-produto-delete-dialog',
    templateUrl: './banner-delete-dialog.component.html'
})
export class BannerDeleteDialogComponent {

    produto: Banner;

    constructor(
        private bannerService: BannerService,
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
        private produtoPopupService: BannerPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.produtoPopupService
                .open(BannerDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
