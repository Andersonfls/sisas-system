import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { SegurancasLogPopupService } from './segurancas-log-popup.service';
import { SegurancasLogService } from './segurancas-log.service';
import {SegurancasLog} from './segurancas-log.model';

@Component({
    selector: 'jhi-relatorios-log-delete-dialog',
    templateUrl: './segurancas-log-delete-dialog.component.html'
})
export class SegurancasLogDeleteDialogComponent {

    relatoriosLog: SegurancasLog;

    constructor(
        private relatoriosLogService: SegurancasLogService,
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
        private relatoriosLogPopupService: SegurancasLogPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.relatoriosLogPopupService
                .open(SegurancasLogDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
