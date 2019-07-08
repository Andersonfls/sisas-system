import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Execucao } from './execucao.model';
import { ExecucaoPopupService } from './execucao-popup.service';
import { ExecucaoService } from './execucao.service';

@Component({
    selector: 'jhi-execucao-delete-dialog',
    templateUrl: './execucao-delete-dialog.component.html'
})
export class ExecucaoDeleteDialogComponent {

    execucao: Execucao;

    constructor(
        private execucaoService: ExecucaoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.execucaoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'execucaoListModification',
                content: 'Deleted an execucao'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-execucao-delete-popup',
    template: ''
})
export class ExecucaoDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private execucaoPopupService: ExecucaoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.execucaoPopupService
                .open(ExecucaoDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
