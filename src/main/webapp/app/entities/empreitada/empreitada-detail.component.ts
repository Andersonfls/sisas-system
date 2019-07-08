import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Empreitada } from './empreitada.model';
import { EmpreitadaService } from './empreitada.service';

@Component({
    selector: 'jhi-empreitada-detail',
    templateUrl: './empreitada-detail.component.html'
})
export class EmpreitadaDetailComponent implements OnInit, OnDestroy {

    empreitada: Empreitada;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private empreitadaService: EmpreitadaService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInEmpreitadas();
    }

    load(id) {
        this.empreitadaService.find(id)
            .subscribe((empreitadaResponse: HttpResponse<Empreitada>) => {
                this.empreitada = empreitadaResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInEmpreitadas() {
        this.eventSubscriber = this.eventManager.subscribe(
            'empreitadaListModification',
            (response) => this.load(this.empreitada.id)
        );
    }
}
