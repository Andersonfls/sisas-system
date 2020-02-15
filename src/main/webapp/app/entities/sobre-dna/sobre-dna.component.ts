import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { SobreDna } from './sobre-dna.model';
import { SobreDnaService } from './sobre-dna.service';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {Observable} from 'rxjs';

@Component({
    selector: 'jhi-sobre-dna',
    templateUrl: './sobre-dna.component.html'
})
export class SobreDnaComponent implements OnInit, OnDestroy {

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
    sobre: SobreDna;

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

    loadAll() {
        this.sobreDnaService.find(1).subscribe(
                (res: HttpResponse<SobreDna>) => {
                    this.sobre = res.body;
                    console.log(this.sobre);
                }
        );
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
        }
    }

    ngOnInit() {
        this.sobre = new SobreDna();
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInSobreDnas();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInSobreDnas() {
        this.eventSubscriber = this.eventManager.subscribe('sobreDnaListModification', (response) => this.loadAll());
    }

    save() {
        this.sobre.id = 1;
        this.sobre.tipoPagina = 1;
        this.sobre.resumoTextoSobreDna = 'sisas';
        if (this.sobre.id !== undefined) {
            this.subscribeToSaveResponse(
                this.sobreDnaService.update(this.sobre));
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
