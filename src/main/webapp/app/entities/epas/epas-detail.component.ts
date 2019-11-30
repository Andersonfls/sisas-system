import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Epas } from './epas.model';
import { EpasService } from './epas.service';

@Component({
    selector: 'jhi-epas-detail',
    templateUrl: './epas-detail.component.html'
})
export class EpasDetailComponent implements OnInit, OnDestroy {

    epas: Epas;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private epasService: EpasService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInEpass();
    }

    load(id) {
        this.epasService.find(id)
            .subscribe((epasResponse: HttpResponse<Epas>) => {
                this.epas = epasResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInEpass() {
        this.eventSubscriber = this.eventManager.subscribe(
            'epasListModification',
            (response) => this.load(this.epas.id)
        );
    }
}
