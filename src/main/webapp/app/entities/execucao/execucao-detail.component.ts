import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Execucao } from './execucao.model';
import { ExecucaoService } from './execucao.service';

@Component({
    selector: 'jhi-execucao-detail',
    templateUrl: './execucao-detail.component.html'
})
export class ExecucaoDetailComponent implements OnInit, OnDestroy {

    execucao: Execucao;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private execucaoService: ExecucaoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInExecucaos();
    }

    load(id) {
        this.execucaoService.find(id)
            .subscribe((execucaoResponse: HttpResponse<Execucao>) => {
                this.execucao = execucaoResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInExecucaos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'execucaoListModification',
            (response) => this.load(this.execucao.id)
        );
    }
}
