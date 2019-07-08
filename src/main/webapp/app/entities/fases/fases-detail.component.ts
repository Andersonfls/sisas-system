import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Fases } from './fases.model';
import { FasesService } from './fases.service';

@Component({
    selector: 'jhi-fases-detail',
    templateUrl: './fases-detail.component.html'
})
export class FasesDetailComponent implements OnInit, OnDestroy {

    fases: Fases;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private fasesService: FasesService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInFases();
    }

    load(id) {
        this.fasesService.find(id)
            .subscribe((fasesResponse: HttpResponse<Fases>) => {
                this.fases = fasesResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInFases() {
        this.eventSubscriber = this.eventManager.subscribe(
            'fasesListModification',
            (response) => this.load(this.fases.id)
        );
    }
}
