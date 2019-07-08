import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Concurso } from './concurso.model';
import { ConcursoPopupService } from './concurso-popup.service';
import { ConcursoService } from './concurso.service';

@Component({
    selector: 'jhi-concurso-delete-dialog',
    templateUrl: './concurso-delete-dialog.component.html'
})
export class ConcursoDeleteDialogComponent {

    concurso: Concurso;

    constructor(
        private concursoService: ConcursoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.concursoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'concursoListModification',
                content: 'Deleted an concurso'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-concurso-delete-popup',
    template: ''
})
export class ConcursoDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private concursoPopupService: ConcursoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.concursoPopupService
                .open(ConcursoDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
