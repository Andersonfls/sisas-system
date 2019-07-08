import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { PublicacaoLog } from './publicacao-log.model';
import { PublicacaoLogPopupService } from './publicacao-log-popup.service';
import { PublicacaoLogService } from './publicacao-log.service';

@Component({
    selector: 'jhi-publicacao-log-delete-dialog',
    templateUrl: './publicacao-log-delete-dialog.component.html'
})
export class PublicacaoLogDeleteDialogComponent {

    publicacaoLog: PublicacaoLog;

    constructor(
        private publicacaoLogService: PublicacaoLogService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.publicacaoLogService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'publicacaoLogListModification',
                content: 'Deleted an publicacaoLog'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-publicacao-log-delete-popup',
    template: ''
})
export class PublicacaoLogDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private publicacaoLogPopupService: PublicacaoLogPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.publicacaoLogPopupService
                .open(PublicacaoLogDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
