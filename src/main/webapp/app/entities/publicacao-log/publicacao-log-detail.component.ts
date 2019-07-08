import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { PublicacaoLog } from './publicacao-log.model';
import { PublicacaoLogService } from './publicacao-log.service';

@Component({
    selector: 'jhi-publicacao-log-detail',
    templateUrl: './publicacao-log-detail.component.html'
})
export class PublicacaoLogDetailComponent implements OnInit, OnDestroy {

    publicacaoLog: PublicacaoLog;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private publicacaoLogService: PublicacaoLogService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInPublicacaoLogs();
    }

    load(id) {
        this.publicacaoLogService.find(id)
            .subscribe((publicacaoLogResponse: HttpResponse<PublicacaoLog>) => {
                this.publicacaoLog = publicacaoLogResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInPublicacaoLogs() {
        this.eventSubscriber = this.eventManager.subscribe(
            'publicacaoLogListModification',
            (response) => this.load(this.publicacaoLog.id)
        );
    }
}
