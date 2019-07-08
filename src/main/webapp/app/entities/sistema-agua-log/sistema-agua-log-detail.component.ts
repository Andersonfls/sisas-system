import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { SistemaAguaLog } from './sistema-agua-log.model';
import { SistemaAguaLogService } from './sistema-agua-log.service';

@Component({
    selector: 'jhi-sistema-agua-log-detail',
    templateUrl: './sistema-agua-log-detail.component.html'
})
export class SistemaAguaLogDetailComponent implements OnInit, OnDestroy {

    sistemaAguaLog: SistemaAguaLog;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private sistemaAguaLogService: SistemaAguaLogService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSistemaAguaLogs();
    }

    load(id) {
        this.sistemaAguaLogService.find(id)
            .subscribe((sistemaAguaLogResponse: HttpResponse<SistemaAguaLog>) => {
                this.sistemaAguaLog = sistemaAguaLogResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInSistemaAguaLogs() {
        this.eventSubscriber = this.eventManager.subscribe(
            'sistemaAguaLogListModification',
            (response) => this.load(this.sistemaAguaLog.id)
        );
    }
}
