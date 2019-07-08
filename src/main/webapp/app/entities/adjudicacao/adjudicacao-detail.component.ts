import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Adjudicacao } from './adjudicacao.model';
import { AdjudicacaoService } from './adjudicacao.service';

@Component({
    selector: 'jhi-adjudicacao-detail',
    templateUrl: './adjudicacao-detail.component.html'
})
export class AdjudicacaoDetailComponent implements OnInit, OnDestroy {

    adjudicacao: Adjudicacao;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private adjudicacaoService: AdjudicacaoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAdjudicacaos();
    }

    load(id) {
        this.adjudicacaoService.find(id)
            .subscribe((adjudicacaoResponse: HttpResponse<Adjudicacao>) => {
                this.adjudicacao = adjudicacaoResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAdjudicacaos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'adjudicacaoListModification',
            (response) => this.load(this.adjudicacao.id)
        );
    }
}
