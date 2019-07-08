import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IndicadorProducao } from './indicador-producao.model';
import { IndicadorProducaoPopupService } from './indicador-producao-popup.service';
import { IndicadorProducaoService } from './indicador-producao.service';

@Component({
    selector: 'jhi-indicador-producao-delete-dialog',
    templateUrl: './indicador-producao-delete-dialog.component.html'
})
export class IndicadorProducaoDeleteDialogComponent {

    indicadorProducao: IndicadorProducao;

    constructor(
        private indicadorProducaoService: IndicadorProducaoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.indicadorProducaoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'indicadorProducaoListModification',
                content: 'Deleted an indicadorProducao'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-indicador-producao-delete-popup',
    template: ''
})
export class IndicadorProducaoDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private indicadorProducaoPopupService: IndicadorProducaoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.indicadorProducaoPopupService
                .open(IndicadorProducaoDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
