import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { SobreDna } from './sobre-dna.model';
import { SobreDnaService } from './sobre-dna.service';

@Component({
    selector: 'jhi-sobre-dna-detail',
    templateUrl: './sobre-dna-detail.component.html'
})
export class SobreDnaDetailComponent implements OnInit, OnDestroy {

    sobreDna: SobreDna;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private sobreDnaService: SobreDnaService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSobreDnas();
    }

    load(id) {
        this.sobreDnaService.find(id)
            .subscribe((sobreDnaResponse: HttpResponse<SobreDna>) => {
                this.sobreDna = sobreDnaResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInSobreDnas() {
        this.eventSubscriber = this.eventManager.subscribe(
            'sobreDnaListModification',
            (response) => this.load(this.sobreDna.id)
        );
    }
}
