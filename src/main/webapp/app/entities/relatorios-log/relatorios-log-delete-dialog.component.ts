import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { RelatoriosLog } from './relatorios-log.model';
import { RelatoriosLogPopupService } from './relatorios-log-popup.service';
import { RelatoriosLogService } from './relatorios-log.service';

@Component({
    selector: 'jhi-relatorios-log-delete-dialog',
    templateUrl: './relatorios-log-delete-dialog.component.html'
})
export class RelatoriosLogDeleteDialogComponent {

    relatoriosLog: RelatoriosLog;

    constructor(
        private relatoriosLogService: RelatoriosLogService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.relatoriosLogService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'relatoriosLogListModification',
                content: 'Deleted an relatoriosLog'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-relatorios-log-delete-popup',
    template: ''
})
export class RelatoriosLogDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private relatoriosLogPopupService: RelatoriosLogPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.relatoriosLogPopupService
                .open(RelatoriosLogDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
