import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { IndicadorProducao } from './indicador-producao.model';
import { IndicadorProducaoService } from './indicador-producao.service';

@Component({
    selector: 'jhi-indicador-producao-detail',
    templateUrl: './indicador-producao-detail.component.html'
})
export class IndicadorProducaoDetailComponent implements OnInit, OnDestroy {

    indicadorProducao: IndicadorProducao;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private indicadorProducaoService: IndicadorProducaoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInIndicadorProducaos();
    }

    load(id) {
        this.indicadorProducaoService.find(id)
            .subscribe((indicadorProducaoResponse: HttpResponse<IndicadorProducao>) => {
                this.indicadorProducao = indicadorProducaoResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInIndicadorProducaos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'indicadorProducaoListModification',
            (response) => this.load(this.indicadorProducao.id)
        );
    }
}
