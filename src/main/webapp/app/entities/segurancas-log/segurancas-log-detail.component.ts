import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';
import { SegurancasLogService } from './segurancas-log.service';
import {SegurancasLog} from './segurancas-log.model';

@Component({
    selector: 'jhi-relatorios-log-detail',
    templateUrl: './segurancas-log-detail.component.html'
})
export class SegurancasLogDetailComponent implements OnInit, OnDestroy {

    segurancasLog: SegurancasLog;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private relatoriosLogService: SegurancasLogService,
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
            .subscribe((relatoriosLogResponse: HttpResponse<SegurancasLog>) => {
                this.segurancasLog = relatoriosLogResponse.body;
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
            (response) => this.load(this.segurancasLog.id)
        );
    }
}
