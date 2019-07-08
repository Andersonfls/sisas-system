import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { IndicadorProducaoLog } from './indicador-producao-log.model';
import { IndicadorProducaoLogService } from './indicador-producao-log.service';

@Component({
    selector: 'jhi-indicador-producao-log-detail',
    templateUrl: './indicador-producao-log-detail.component.html'
})
export class IndicadorProducaoLogDetailComponent implements OnInit, OnDestroy {

    indicadorProducaoLog: IndicadorProducaoLog;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private indicadorProducaoLogService: IndicadorProducaoLogService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInIndicadorProducaoLogs();
    }

    load(id) {
        this.indicadorProducaoLogService.find(id)
            .subscribe((indicadorProducaoLogResponse: HttpResponse<IndicadorProducaoLog>) => {
                this.indicadorProducaoLog = indicadorProducaoLogResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInIndicadorProducaoLogs() {
        this.eventSubscriber = this.eventManager.subscribe(
            'indicadorProducaoLogListModification',
            (response) => this.load(this.indicadorProducaoLog.id)
        );
    }
}
