import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { MunicipiosAtendidos } from './municipios-atendidos.model';
import { MunicipiosAtendidosService } from './municipios-atendidos.service';

@Component({
    selector: 'jhi-municipios-atendidos-detail',
    templateUrl: './municipios-atendidos-detail.component.html'
})
export class MunicipiosAtendidosDetailComponent implements OnInit, OnDestroy {

    municipiosAtendidos: MunicipiosAtendidos;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private municipiosAtendidosService: MunicipiosAtendidosService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInMunicipiosAtendidos();
    }

    load(id) {
        this.municipiosAtendidosService.find(id)
            .subscribe((municipiosAtendidosResponse: HttpResponse<MunicipiosAtendidos>) => {
                this.municipiosAtendidos = municipiosAtendidosResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInMunicipiosAtendidos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'municipiosAtendidosListModification',
            (response) => this.load(this.municipiosAtendidos.id)
        );
    }
}
