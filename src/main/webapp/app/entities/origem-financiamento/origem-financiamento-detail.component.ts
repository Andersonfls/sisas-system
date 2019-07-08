import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { OrigemFinanciamento } from './origem-financiamento.model';
import { OrigemFinanciamentoService } from './origem-financiamento.service';

@Component({
    selector: 'jhi-origem-financiamento-detail',
    templateUrl: './origem-financiamento-detail.component.html'
})
export class OrigemFinanciamentoDetailComponent implements OnInit, OnDestroy {

    origemFinanciamento: OrigemFinanciamento;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private origemFinanciamentoService: OrigemFinanciamentoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInOrigemFinanciamentos();
    }

    load(id) {
        this.origemFinanciamentoService.find(id)
            .subscribe((origemFinanciamentoResponse: HttpResponse<OrigemFinanciamento>) => {
                this.origemFinanciamento = origemFinanciamentoResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInOrigemFinanciamentos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'origemFinanciamentoListModification',
            (response) => this.load(this.origemFinanciamento.id)
        );
    }
}
