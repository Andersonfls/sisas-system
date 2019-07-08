import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Provincia } from './provincia.model';
import { ProvinciaService } from './provincia.service';

@Component({
    selector: 'jhi-provincia-detail',
    templateUrl: './provincia-detail.component.html'
})
export class ProvinciaDetailComponent implements OnInit, OnDestroy {

    provincia: Provincia;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private provinciaService: ProvinciaService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInProvincias();
    }

    load(id) {
        this.provinciaService.find(id)
            .subscribe((provinciaResponse: HttpResponse<Provincia>) => {
                this.provincia = provinciaResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInProvincias() {
        this.eventSubscriber = this.eventManager.subscribe(
            'provinciaListModification',
            (response) => this.load(this.provincia.id)
        );
    }
}
