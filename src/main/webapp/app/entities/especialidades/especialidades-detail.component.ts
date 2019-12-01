import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Especialidades } from './especialidades.model';
import { EspecialidadesService } from './especialidades.service';

@Component({
    selector: 'jhi-especialidades-detail',
    templateUrl: './especialidades-detail.component.html'
})
export class EspecialidadesDetailComponent implements OnInit, OnDestroy {

    especialidades: Especialidades;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private especialidadesService: EspecialidadesService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInEspecialidadess();
    }

    load(id) {
        this.especialidadesService.find(id)
            .subscribe((especialidadesResponse: HttpResponse<Especialidades>) => {
                this.especialidades = especialidadesResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInEspecialidadess() {
        this.eventSubscriber = this.eventManager.subscribe(
            'especialidadesListModification',
            (response) => this.load(this.especialidades.id)
        );
    }
}
