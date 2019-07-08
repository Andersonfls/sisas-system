import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { SistemaAgua } from './sistema-agua.model';
import { SistemaAguaPopupService } from './sistema-agua-popup.service';
import { SistemaAguaService } from './sistema-agua.service';

@Component({
    selector: 'jhi-sistema-agua-delete-dialog',
    templateUrl: './sistema-agua-delete-dialog.component.html'
})
export class SistemaAguaDeleteDialogComponent {

    sistemaAgua: SistemaAgua;

    constructor(
        private sistemaAguaService: SistemaAguaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sistemaAguaService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'sistemaAguaListModification',
                content: 'Deleted an sistemaAgua'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sistema-agua-delete-popup',
    template: ''
})
export class SistemaAguaDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private sistemaAguaPopupService: SistemaAguaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.sistemaAguaPopupService
                .open(SistemaAguaDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
