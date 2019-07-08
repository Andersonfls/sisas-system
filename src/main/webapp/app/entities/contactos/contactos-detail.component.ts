import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Contactos } from './contactos.model';
import { ContactosService } from './contactos.service';

@Component({
    selector: 'jhi-contactos-detail',
    templateUrl: './contactos-detail.component.html'
})
export class ContactosDetailComponent implements OnInit, OnDestroy {

    contactos: Contactos;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private contactosService: ContactosService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInContactos();
    }

    load(id) {
        this.contactosService.find(id)
            .subscribe((contactosResponse: HttpResponse<Contactos>) => {
                this.contactos = contactosResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInContactos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'contactosListModification',
            (response) => this.load(this.contactos.id)
        );
    }
}
