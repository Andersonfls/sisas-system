import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Concepcao } from './concepcao.model';
import { ConcepcaoService } from './concepcao.service';

@Component({
    selector: 'jhi-concepcao-detail',
    templateUrl: './concepcao-detail.component.html'
})
export class ConcepcaoDetailComponent implements OnInit, OnDestroy {

    concepcao: Concepcao;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private concepcaoService: ConcepcaoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInConcepcaos();
    }

    load(id) {
        this.concepcaoService.find(id)
            .subscribe((concepcaoResponse: HttpResponse<Concepcao>) => {
                this.concepcao = concepcaoResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInConcepcaos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'concepcaoListModification',
            (response) => this.load(this.concepcao.id)
        );
    }
}
