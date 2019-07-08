import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Inicio } from './inicio.model';
import { InicioPopupService } from './inicio-popup.service';
import { InicioService } from './inicio.service';

@Component({
    selector: 'jhi-inicio-delete-dialog',
    templateUrl: './inicio-delete-dialog.component.html'
})
export class InicioDeleteDialogComponent {

    inicio: Inicio;

    constructor(
        private inicioService: InicioService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.inicioService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'inicioListModification',
                content: 'Deleted an inicio'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-inicio-delete-popup',
    template: ''
})
export class InicioDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private inicioPopupService: InicioPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.inicioPopupService
                .open(InicioDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
