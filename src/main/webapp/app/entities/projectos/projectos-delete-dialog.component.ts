import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Projectos } from './projectos.model';
import { ProjectosPopupService } from './projectos-popup.service';
import { ProjectosService } from './projectos.service';

@Component({
    selector: 'jhi-projectos-delete-dialog',
    templateUrl: './projectos-delete-dialog.component.html'
})
export class ProjectosDeleteDialogComponent {

    projectos: Projectos;

    constructor(
        private projectosService: ProjectosService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.projectosService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'projectosListModification',
                content: 'Deleted an projectos'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-projectos-delete-popup',
    template: ''
})
export class ProjectosDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private projectosPopupService: ProjectosPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.projectosPopupService
                .open(ProjectosDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
