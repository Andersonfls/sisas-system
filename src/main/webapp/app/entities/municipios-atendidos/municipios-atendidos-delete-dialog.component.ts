import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { MunicipiosAtendidos } from './municipios-atendidos.model';
import { MunicipiosAtendidosPopupService } from './municipios-atendidos-popup.service';
import { MunicipiosAtendidosService } from './municipios-atendidos.service';

@Component({
    selector: 'jhi-municipios-atendidos-delete-dialog',
    templateUrl: './municipios-atendidos-delete-dialog.component.html'
})
export class MunicipiosAtendidosDeleteDialogComponent {

    municipiosAtendidos: MunicipiosAtendidos;

    constructor(
        private municipiosAtendidosService: MunicipiosAtendidosService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.municipiosAtendidosService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'municipiosAtendidosListModification',
                content: 'Deleted an municipiosAtendidos'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-municipios-atendidos-delete-popup',
    template: ''
})
export class MunicipiosAtendidosDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private municipiosAtendidosPopupService: MunicipiosAtendidosPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.municipiosAtendidosPopupService
                .open(MunicipiosAtendidosDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
