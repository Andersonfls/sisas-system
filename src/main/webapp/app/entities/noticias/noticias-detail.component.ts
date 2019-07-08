import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Noticias } from './noticias.model';
import { NoticiasService } from './noticias.service';

@Component({
    selector: 'jhi-noticias-detail',
    templateUrl: './noticias-detail.component.html'
})
export class NoticiasDetailComponent implements OnInit, OnDestroy {

    noticias: Noticias;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private noticiasService: NoticiasService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInNoticias();
    }

    load(id) {
        this.noticiasService.find(id)
            .subscribe((noticiasResponse: HttpResponse<Noticias>) => {
                this.noticias = noticiasResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInNoticias() {
        this.eventSubscriber = this.eventManager.subscribe(
            'noticiasListModification',
            (response) => this.load(this.noticias.id)
        );
    }
}
