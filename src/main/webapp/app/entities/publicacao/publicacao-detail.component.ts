import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Publicacao } from './publicacao.model';
import { PublicacaoService } from './publicacao.service';

@Component({
    selector: 'jhi-publicacao-detail',
    templateUrl: './publicacao-detail.component.html'
})
export class PublicacaoDetailComponent implements OnInit, OnDestroy {

    publicacao: Publicacao;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private publicacaoService: PublicacaoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInPublicacaos();
    }

    load(id) {
        this.publicacaoService.find(id)
            .subscribe((publicacaoResponse: HttpResponse<Publicacao>) => {
                this.publicacao = publicacaoResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInPublicacaos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'publicacaoListModification',
            (response) => this.load(this.publicacao.id)
        );
    }
}
