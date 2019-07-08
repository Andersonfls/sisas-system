import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Projectos } from './projectos.model';
import { ProjectosService } from './projectos.service';

@Component({
    selector: 'jhi-projectos-detail',
    templateUrl: './projectos-detail.component.html'
})
export class ProjectosDetailComponent implements OnInit, OnDestroy {

    projectos: Projectos;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private projectosService: ProjectosService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInProjectos();
    }

    load(id) {
        this.projectosService.find(id)
            .subscribe((projectosResponse: HttpResponse<Projectos>) => {
                this.projectos = projectosResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInProjectos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'projectosListModification',
            (response) => this.load(this.projectos.id)
        );
    }
}
