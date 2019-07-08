import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IndicadorProducaoLog } from './indicador-producao-log.model';
import { IndicadorProducaoLogPopupService } from './indicador-producao-log-popup.service';
import { IndicadorProducaoLogService } from './indicador-producao-log.service';

@Component({
    selector: 'jhi-indicador-producao-log-delete-dialog',
    templateUrl: './indicador-producao-log-delete-dialog.component.html'
})
export class IndicadorProducaoLogDeleteDialogComponent {

    indicadorProducaoLog: IndicadorProducaoLog;

    constructor(
        private indicadorProducaoLogService: IndicadorProducaoLogService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.indicadorProducaoLogService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'indicadorProducaoLogListModification',
                content: 'Deleted an indicadorProducaoLog'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-indicador-producao-log-delete-popup',
    template: ''
})
export class IndicadorProducaoLogDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private indicadorProducaoLogPopupService: IndicadorProducaoLogPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.indicadorProducaoLogPopupService
                .open(IndicadorProducaoLogDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
