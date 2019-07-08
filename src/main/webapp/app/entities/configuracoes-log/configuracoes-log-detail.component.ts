import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { ConfiguracoesLog } from './configuracoes-log.model';
import { ConfiguracoesLogService } from './configuracoes-log.service';

@Component({
    selector: 'jhi-configuracoes-log-detail',
    templateUrl: './configuracoes-log-detail.component.html'
})
export class ConfiguracoesLogDetailComponent implements OnInit, OnDestroy {

    configuracoesLog: ConfiguracoesLog;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private configuracoesLogService: ConfiguracoesLogService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInConfiguracoesLogs();
    }

    load(id) {
        this.configuracoesLogService.find(id)
            .subscribe((configuracoesLogResponse: HttpResponse<ConfiguracoesLog>) => {
                this.configuracoesLog = configuracoesLogResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInConfiguracoesLogs() {
        this.eventSubscriber = this.eventManager.subscribe(
            'configuracoesLogListModification',
            (response) => this.load(this.configuracoesLog.id)
        );
    }
}
