import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Epas } from './epas.model';
import { EpasPopupService } from './epas-popup.service';
import { EpasService } from './epas.service';
import {Provincia} from '../provincia/provincia.model';
import {Municipio} from '../municipio/municipio.model';
import {Comuna} from '../comuna/comuna.model';
import {ProvinciaService} from '../provincia/provincia.service';
import {MunicipioService} from '../municipio/municipio.service';
import {ComunaService} from '../comuna/comuna.service';

@Component({
    selector: 'jhi-epas-dialog',
    templateUrl: './epas-dialog.component.html'
})
export class EpasDialogComponent implements OnInit {

    epas: Epas;
    isSaving: boolean;

    provincias: Provincia[];
    provincia: Provincia;
    municipios: Municipio[];
    municipio: Municipio;
    comunas: Comuna[];
    comuna: Comuna;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private epasService: EpasService,
        private provinciaService: ProvinciaService,
        private municipioService: MunicipioService,
        private comunaService: ComunaService,
        private eventManager: JhiEventManager,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;

        this.comunaService.query()
            .subscribe((res: HttpResponse<Comuna[]>) => {
                this.comunas = res.body;
            }, (res: HttpErrorResponse) => this.onError(res.message));

        this.municipioService.query().subscribe(
            (res: HttpResponse<Municipio[]>) => {
                this.municipios = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message));

        this.provinciaService.query().subscribe(
            (res: HttpResponse<Provincia[]>) => {
                this.provincias = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.epas.id !== undefined) {
            this.subscribeToSaveResponse(
                this.epasService.update(this.epas));
        } else {
            this.subscribeToSaveResponse(
                this.epasService.create(this.epas));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Epas>>) {
        result.subscribe((res: HttpResponse<Epas>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Epas) {
        this.eventManager.broadcast({ name: 'epasListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackMunicipioById(index: number, item: Municipio) {
        return item.id;
    }

    onChangeMunicipios() {
        this.municipios = null;
        this.comunas = null;

        this.municipioService.queryMunicipioByProvinciaId({
            provinciaId: this.epas.provincia.id
        })
            .subscribe((res) => {
                this.municipios = res.body;
            });
    }

    onChangeComunas() {
        this.comunas = null;

        this.comunaService.queryComunaByMunicipioId({
            municipioId: this.epas.municipio.id
        })
            .subscribe((res) => {
                this.comunas = res.body;
            });
    }
}

@Component({
    selector: 'jhi-epas-popup',
    template: ''
})
export class EpasPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private epasPopupService: EpasPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.epasPopupService
                    .open(EpasDialogComponent as Component, params['id']);
            } else {
                this.epasPopupService
                    .open(EpasDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
