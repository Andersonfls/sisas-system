import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { EntidadeGestora } from './entidade-gestora.model';
import { EntidadeGestoraService } from './entidade-gestora.service';

@Component({
    selector: 'jhi-entidade-gestora-detail',
    templateUrl: './entidade-gestora-detail.component.html'
})
export class EntidadeGestoraDetailComponent implements OnInit, OnDestroy {

    entidadeGestora: EntidadeGestora;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private entidadeGestoraService: EntidadeGestoraService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInEntidadeGestoras();
    }

    load(id) {
        this.entidadeGestoraService.find(id)
            .subscribe((entidadeGestoraResponse: HttpResponse<EntidadeGestora>) => {
                this.entidadeGestora = entidadeGestoraResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInEntidadeGestoras() {
        this.eventSubscriber = this.eventManager.subscribe(
            'entidadeGestoraListModification',
            (response) => this.load(this.entidadeGestora.id)
        );
    }
}
