import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ProgramasProjectosLog } from './programas-projectos-log.model';
import { ProgramasProjectosLogPopupService } from './programas-projectos-log-popup.service';
import { ProgramasProjectosLogService } from './programas-projectos-log.service';

@Component({
    selector: 'jhi-programas-projectos-log-delete-dialog',
    templateUrl: './programas-projectos-log-delete-dialog.component.html'
})
export class ProgramasProjectosLogDeleteDialogComponent {

    programasProjectosLog: ProgramasProjectosLog;

    constructor(
        private programasProjectosLogService: ProgramasProjectosLogService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.programasProjectosLogService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'programasProjectosLogListModification',
                content: 'Deleted an programasProjectosLog'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-programas-projectos-log-delete-popup',
    template: ''
})
export class ProgramasProjectosLogDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private programasProjectosLogPopupService: ProgramasProjectosLogPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.programasProjectosLogPopupService
                .open(ProgramasProjectosLogDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
