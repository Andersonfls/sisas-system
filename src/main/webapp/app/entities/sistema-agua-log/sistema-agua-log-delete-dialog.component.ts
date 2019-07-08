import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { SistemaAguaLog } from './sistema-agua-log.model';
import { SistemaAguaLogPopupService } from './sistema-agua-log-popup.service';
import { SistemaAguaLogService } from './sistema-agua-log.service';

@Component({
    selector: 'jhi-sistema-agua-log-delete-dialog',
    templateUrl: './sistema-agua-log-delete-dialog.component.html'
})
export class SistemaAguaLogDeleteDialogComponent {

    sistemaAguaLog: SistemaAguaLog;

    constructor(
        private sistemaAguaLogService: SistemaAguaLogService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sistemaAguaLogService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'sistemaAguaLogListModification',
                content: 'Deleted an sistemaAguaLog'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sistema-agua-log-delete-popup',
    template: ''
})
export class SistemaAguaLogDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private sistemaAguaLogPopupService: SistemaAguaLogPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.sistemaAguaLogPopupService
                .open(SistemaAguaLogDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
