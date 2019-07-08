import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { SistemaAgua } from './sistema-agua.model';
import { SistemaAguaService } from './sistema-agua.service';

@Component({
    selector: 'jhi-sistema-agua-detail',
    templateUrl: './sistema-agua-detail.component.html'
})
export class SistemaAguaDetailComponent implements OnInit, OnDestroy {

    sistemaAgua: SistemaAgua;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private sistemaAguaService: SistemaAguaService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSistemaAguas();
    }

    load(id) {
        this.sistemaAguaService.find(id)
            .subscribe((sistemaAguaResponse: HttpResponse<SistemaAgua>) => {
                this.sistemaAgua = sistemaAguaResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInSistemaAguas() {
        this.eventSubscriber = this.eventManager.subscribe(
            'sistemaAguaListModification',
            (response) => this.load(this.sistemaAgua.id)
        );
    }
}
