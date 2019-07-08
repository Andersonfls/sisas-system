import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ConfiguracoesLog } from './configuracoes-log.model';
import { ConfiguracoesLogPopupService } from './configuracoes-log-popup.service';
import { ConfiguracoesLogService } from './configuracoes-log.service';

@Component({
    selector: 'jhi-configuracoes-log-delete-dialog',
    templateUrl: './configuracoes-log-delete-dialog.component.html'
})
export class ConfiguracoesLogDeleteDialogComponent {

    configuracoesLog: ConfiguracoesLog;

    constructor(
        private configuracoesLogService: ConfiguracoesLogService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.configuracoesLogService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'configuracoesLogListModification',
                content: 'Deleted an configuracoesLog'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-configuracoes-log-delete-popup',
    template: ''
})
export class ConfiguracoesLogDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private configuracoesLogPopupService: ConfiguracoesLogPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.configuracoesLogPopupService
                .open(ConfiguracoesLogDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
