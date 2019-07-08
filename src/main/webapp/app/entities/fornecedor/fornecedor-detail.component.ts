import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Fornecedor } from './fornecedor.model';
import { FornecedorService } from './fornecedor.service';

@Component({
    selector: 'jhi-fornecedor-detail',
    templateUrl: './fornecedor-detail.component.html'
})
export class FornecedorDetailComponent implements OnInit, OnDestroy {

    fornecedor: Fornecedor;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private fornecedorService: FornecedorService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInFornecedors();
    }

    load(id) {
        this.fornecedorService.find(id)
            .subscribe((fornecedorResponse: HttpResponse<Fornecedor>) => {
                this.fornecedor = fornecedorResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInFornecedors() {
        this.eventSubscriber = this.eventManager.subscribe(
            'fornecedorListModification',
            (response) => this.load(this.fornecedor.id)
        );
    }
}
