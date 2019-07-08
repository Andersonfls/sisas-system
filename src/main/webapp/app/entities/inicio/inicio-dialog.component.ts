import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Inicio } from './inicio.model';
import { InicioPopupService } from './inicio-popup.service';
import { InicioService } from './inicio.service';
import { Situacao, SituacaoService } from '../situacao';
import { SobreDna, SobreDnaService } from '../sobre-dna';
import { Noticias, NoticiasService } from '../noticias';
import { Projectos, ProjectosService } from '../projectos';
import { Publicacao, PublicacaoService } from '../publicacao';
import { Contactos, ContactosService } from '../contactos';

@Component({
    selector: 'jhi-inicio-dialog',
    templateUrl: './inicio-dialog.component.html'
})
export class InicioDialogComponent implements OnInit {

    inicio: Inicio;
    isSaving: boolean;

    situacaos: Situacao[];

    sobrednas: SobreDna[];

    noticias: Noticias[];

    projectos: Projectos[];

    publicacaos: Publicacao[];

    contactos: Contactos[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private inicioService: InicioService,
        private situacaoService: SituacaoService,
        private sobreDnaService: SobreDnaService,
        private noticiasService: NoticiasService,
        private projectosService: ProjectosService,
        private publicacaoService: PublicacaoService,
        private contactosService: ContactosService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.situacaoService.query()
            .subscribe((res: HttpResponse<Situacao[]>) => { this.situacaos = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.sobreDnaService.query()
            .subscribe((res: HttpResponse<SobreDna[]>) => { this.sobrednas = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.noticiasService.query()
            .subscribe((res: HttpResponse<Noticias[]>) => { this.noticias = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.projectosService.query()
            .subscribe((res: HttpResponse<Projectos[]>) => { this.projectos = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.publicacaoService.query()
            .subscribe((res: HttpResponse<Publicacao[]>) => { this.publicacaos = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.contactosService.query()
            .subscribe((res: HttpResponse<Contactos[]>) => { this.contactos = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.inicio.id !== undefined) {
            this.subscribeToSaveResponse(
                this.inicioService.update(this.inicio));
        } else {
            this.subscribeToSaveResponse(
                this.inicioService.create(this.inicio));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Inicio>>) {
        result.subscribe((res: HttpResponse<Inicio>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Inicio) {
        this.eventManager.broadcast({ name: 'inicioListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackSituacaoById(index: number, item: Situacao) {
        return item.id;
    }

    trackSobreDnaById(index: number, item: SobreDna) {
        return item.id;
    }

    trackNoticiasById(index: number, item: Noticias) {
        return item.id;
    }

    trackProjectosById(index: number, item: Projectos) {
        return item.id;
    }

    trackPublicacaoById(index: number, item: Publicacao) {
        return item.id;
    }

    trackContactosById(index: number, item: Contactos) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-inicio-popup',
    template: ''
})
export class InicioPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private inicioPopupService: InicioPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.inicioPopupService
                    .open(InicioDialogComponent as Component, params['id']);
            } else {
                this.inicioPopupService
                    .open(InicioDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
