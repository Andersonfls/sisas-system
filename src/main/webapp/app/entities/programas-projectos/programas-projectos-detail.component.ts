import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { ProgramasProjectos } from './programas-projectos.model';
import { ProgramasProjectosService } from './programas-projectos.service';

@Component({
    selector: 'jhi-programas-projectos-detail',
    templateUrl: './programas-projectos-detail.component.html'
})
export class ProgramasProjectosDetailComponent implements OnInit, OnDestroy {

    programasProjectos: ProgramasProjectos;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private programasProjectosService: ProgramasProjectosService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInProgramasProjectos();
    }

    load(id) {
        this.programasProjectosService.find(id)
            .subscribe((programasProjectosResponse: HttpResponse<ProgramasProjectos>) => {
                this.programasProjectos = programasProjectosResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInProgramasProjectos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'programasProjectosListModification',
            (response) => this.load(this.programasProjectos.id)
        );
    }
}
