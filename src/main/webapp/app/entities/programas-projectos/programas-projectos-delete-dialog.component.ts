import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ProgramasProjectos } from './programas-projectos.model';
import { ProgramasProjectosPopupService } from './programas-projectos-popup.service';
import { ProgramasProjectosService } from './programas-projectos.service';

@Component({
    selector: 'jhi-programas-projectos-delete-dialog',
    templateUrl: './programas-projectos-delete-dialog.component.html'
})
export class ProgramasProjectosDeleteDialogComponent {

    programasProjectos: ProgramasProjectos;

    constructor(
        private programasProjectosService: ProgramasProjectosService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.programasProjectosService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'programasProjectosListModification',
                content: 'Deleted an programasProjectos'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-programas-projectos-delete-popup',
    template: ''
})
export class ProgramasProjectosDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private programasProjectosPopupService: ProgramasProjectosPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.programasProjectosPopupService
                .open(ProgramasProjectosDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
