import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Municipio } from './municipio.model';
import { MunicipioService } from './municipio.service';

@Component({
    selector: 'jhi-municipio-detail',
    templateUrl: './municipio-detail.component.html'
})
export class MunicipioDetailComponent implements OnInit, OnDestroy {

    municipio: Municipio;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private municipioService: MunicipioService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInMunicipios();
    }

    load(id) {
        this.municipioService.find(id)
            .subscribe((municipioResponse: HttpResponse<Municipio>) => {
                this.municipio = municipioResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInMunicipios() {
        this.eventSubscriber = this.eventManager.subscribe(
            'municipioListModification',
            (response) => this.load(this.municipio.id)
        );
    }
}
