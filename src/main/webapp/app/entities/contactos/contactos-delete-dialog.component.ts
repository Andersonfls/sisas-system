import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Contactos } from './contactos.model';
import { ContactosPopupService } from './contactos-popup.service';
import { ContactosService } from './contactos.service';

@Component({
    selector: 'jhi-contactos-delete-dialog',
    templateUrl: './contactos-delete-dialog.component.html'
})
export class ContactosDeleteDialogComponent {

    contactos: Contactos;

    constructor(
        private contactosService: ContactosService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.contactosService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'contactosListModification',
                content: 'Deleted an contactos'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-contactos-delete-popup',
    template: ''
})
export class ContactosDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private contactosPopupService: ContactosPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.contactosPopupService
                .open(ContactosDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
