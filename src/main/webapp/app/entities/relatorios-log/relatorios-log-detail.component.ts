import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { RelatoriosLog } from './relatorios-log.model';
import { RelatoriosLogService } from './relatorios-log.service';

@Component({
    selector: 'jhi-relatorios-log-detail',
    templateUrl: './relatorios-log-detail.component.html'
})
export class RelatoriosLogDetailComponent implements OnInit, OnDestroy {

    relatoriosLog: RelatoriosLog;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private relatoriosLogService: RelatoriosLogService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInRelatoriosLogs();
    }

    load(id) {
        this.relatoriosLogService.find(id)
            .subscribe((relatoriosLogResponse: HttpResponse<RelatoriosLog>) => {
                this.relatoriosLog = relatoriosLogResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInRelatoriosLogs() {
        this.eventSubscriber = this.eventManager.subscribe(
            'relatoriosLogListModification',
            (response) => this.load(this.relatoriosLog.id)
        );
    }
}
