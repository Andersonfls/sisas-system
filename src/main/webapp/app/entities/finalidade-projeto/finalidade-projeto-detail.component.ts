import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { FinalidadeProjeto } from './finalidade-projeto.model';
import { FinalidadeProjetoService } from './finalidade-projeto.service';

@Component({
    selector: 'jhi-finalidade-projeto-detail',
    templateUrl: './finalidade-projeto-detail.component.html'
})
export class FinalidadeProjetoDetailComponent implements OnInit, OnDestroy {

    finalidadeProjeto: FinalidadeProjeto;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private finalidadeProjetoService: FinalidadeProjetoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInFinalidadeProjetos();
    }

    load(id) {
        this.finalidadeProjetoService.find(id)
            .subscribe((finalidadeProjetoResponse: HttpResponse<FinalidadeProjeto>) => {
                this.finalidadeProjeto = finalidadeProjetoResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInFinalidadeProjetos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'finalidadeProjetoListModification',
            (response) => this.load(this.finalidadeProjeto.id)
        );
    }
}
