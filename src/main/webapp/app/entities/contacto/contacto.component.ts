import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {Observable} from 'rxjs';
import {SobreDna, SobreDnaService} from '../sobre-dna';

@Component({
    selector: 'jhi-sobre-dna',
    templateUrl: './contacto.component.html'
})
export class ContactoComponent implements OnInit, OnDestroy {

currentAccount: any;
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    contacto: SobreDna;

    constructor(
        private sobreDnaService: SobreDnaService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInSobreDnas();
    }

    loadAll() {
        this.sobreDnaService.find(2).subscribe(
                (res: HttpResponse<SobreDna>) => {
                    this.contacto = res.body;
                    console.log(this.contacto);
                }
        );
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
        }
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInSobreDnas() {
        this.eventSubscriber = this.eventManager.subscribe('sobreDnaListModification', (response) => this.loadAll());
    }

    save() {
        this.contacto.id = 2;
        this.contacto.tipoPagina = 2;
        if (this.contacto.id !== undefined) {
            this.subscribeToSaveResponse(
                this.sobreDnaService.update(this.contacto));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<SobreDna>>) {
        result.subscribe((res: HttpResponse<SobreDna>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: SobreDna) {
        this.eventManager.broadcast({ name: 'sobreListModification', content: 'OK'});
        alert('Salvo com sucesso!!');
    }

    private onSaveError() {
        alert('Erro ao salvar, por favor, tente novamente!!');
    }

    previousState() {
        window.history.back();
    }
}
