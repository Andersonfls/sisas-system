import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Inicio } from './inicio.model';
import { InicioService } from './inicio.service';

@Component({
    selector: 'jhi-inicio-detail',
    templateUrl: './inicio-detail.component.html'
})
export class InicioDetailComponent implements OnInit, OnDestroy {

    inicio: Inicio;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private inicioService: InicioService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInInicios();
    }

    load(id) {
        this.inicioService.find(id)
            .subscribe((inicioResponse: HttpResponse<Inicio>) => {
                this.inicio = inicioResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInInicios() {
        this.eventSubscriber = this.eventManager.subscribe(
            'inicioListModification',
            (response) => this.load(this.inicio.id)
        );
    }
}
