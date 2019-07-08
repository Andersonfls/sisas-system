import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Situacao } from './situacao.model';
import { SituacaoService } from './situacao.service';

@Component({
    selector: 'jhi-situacao-detail',
    templateUrl: './situacao-detail.component.html'
})
export class SituacaoDetailComponent implements OnInit, OnDestroy {

    situacao: Situacao;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private situacaoService: SituacaoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSituacaos();
    }

    load(id) {
        this.situacaoService.find(id)
            .subscribe((situacaoResponse: HttpResponse<Situacao>) => {
                this.situacao = situacaoResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInSituacaos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'situacaoListModification',
            (response) => this.load(this.situacao.id)
        );
    }
}
