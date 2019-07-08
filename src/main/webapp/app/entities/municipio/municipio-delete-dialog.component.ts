import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Municipio } from './municipio.model';
import { MunicipioPopupService } from './municipio-popup.service';
import { MunicipioService } from './municipio.service';

@Component({
    selector: 'jhi-municipio-delete-dialog',
    templateUrl: './municipio-delete-dialog.component.html'
})
export class MunicipioDeleteDialogComponent {

    municipio: Municipio;

    constructor(
        private municipioService: MunicipioService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.municipioService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'municipioListModification',
                content: 'Deleted an municipio'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-municipio-delete-popup',
    template: ''
})
export class MunicipioDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private municipioPopupService: MunicipioPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.municipioPopupService
                .open(MunicipioDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
