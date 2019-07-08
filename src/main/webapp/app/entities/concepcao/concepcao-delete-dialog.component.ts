import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Concepcao } from './concepcao.model';
import { ConcepcaoPopupService } from './concepcao-popup.service';
import { ConcepcaoService } from './concepcao.service';

@Component({
    selector: 'jhi-concepcao-delete-dialog',
    templateUrl: './concepcao-delete-dialog.component.html'
})
export class ConcepcaoDeleteDialogComponent {

    concepcao: Concepcao;

    constructor(
        private concepcaoService: ConcepcaoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.concepcaoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'concepcaoListModification',
                content: 'Deleted an concepcao'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-concepcao-delete-popup',
    template: ''
})
export class ConcepcaoDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private concepcaoPopupService: ConcepcaoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.concepcaoPopupService
                .open(ConcepcaoDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
