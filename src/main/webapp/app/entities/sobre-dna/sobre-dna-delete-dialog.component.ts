import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { SobreDna } from './sobre-dna.model';
import { SobreDnaPopupService } from './sobre-dna-popup.service';
import { SobreDnaService } from './sobre-dna.service';

@Component({
    selector: 'jhi-sobre-dna-delete-dialog',
    templateUrl: './sobre-dna-delete-dialog.component.html'
})
export class SobreDnaDeleteDialogComponent {

    sobreDna: SobreDna;

    constructor(
        private sobreDnaService: SobreDnaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sobreDnaService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'sobreDnaListModification',
                content: 'Deleted an sobreDna'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sobre-dna-delete-popup',
    template: ''
})
export class SobreDnaDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private sobreDnaPopupService: SobreDnaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.sobreDnaPopupService
                .open(SobreDnaDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
