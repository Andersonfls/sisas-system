import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Concurso } from './concurso.model';
import { ConcursoService } from './concurso.service';

@Component({
    selector: 'jhi-concurso-detail',
    templateUrl: './concurso-detail.component.html'
})
export class ConcursoDetailComponent implements OnInit, OnDestroy {

    concurso: Concurso;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private concursoService: ConcursoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInConcursos();
    }

    load(id) {
        this.concursoService.find(id)
            .subscribe((concursoResponse: HttpResponse<Concurso>) => {
                this.concurso = concursoResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInConcursos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'concursoListModification',
            (response) => this.load(this.concurso.id)
        );
    }
}
