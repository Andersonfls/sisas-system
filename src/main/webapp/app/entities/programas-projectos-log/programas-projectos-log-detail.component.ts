import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { ProgramasProjectosLog } from './programas-projectos-log.model';
import { ProgramasProjectosLogService } from './programas-projectos-log.service';

@Component({
    selector: 'jhi-programas-projectos-log-detail',
    templateUrl: './programas-projectos-log-detail.component.html'
})
export class ProgramasProjectosLogDetailComponent implements OnInit, OnDestroy {

    programasProjectosLog: ProgramasProjectosLog;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private programasProjectosLogService: ProgramasProjectosLogService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInProgramasProjectosLogs();
    }

    load(id) {
        this.programasProjectosLogService.find(id)
            .subscribe((programasProjectosLogResponse: HttpResponse<ProgramasProjectosLog>) => {
                this.programasProjectosLog = programasProjectosLogResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInProgramasProjectosLogs() {
        this.eventSubscriber = this.eventManager.subscribe(
            'programasProjectosLogListModification',
            (response) => this.load(this.programasProjectosLog.id)
        );
    }
}
