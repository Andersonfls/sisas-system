import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Noticias } from './noticias.model';
import { NoticiasPopupService } from './noticias-popup.service';
import { NoticiasService } from './noticias.service';

@Component({
    selector: 'jhi-noticias-delete-dialog',
    templateUrl: './noticias-delete-dialog.component.html'
})
export class NoticiasDeleteDialogComponent {

    noticias: Noticias;

    constructor(
        private noticiasService: NoticiasService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.noticiasService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'noticiasListModification',
                content: 'Deleted an noticias'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-noticias-delete-popup',
    template: ''
})
export class NoticiasDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private noticiasPopupService: NoticiasPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.noticiasPopupService
                .open(NoticiasDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
