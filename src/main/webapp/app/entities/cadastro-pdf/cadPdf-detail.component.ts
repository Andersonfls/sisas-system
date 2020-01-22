import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { CadPdfService } from './cadPdf.service';
import {ArquivosPortal} from './cadPdf.model';

@Component({
    selector: 'jhi-produto-detail',
    templateUrl: './cadPdf-detail.component.html'
})
export class CadPdfDetailComponent implements OnInit, OnDestroy {

    produto: ArquivosPortal;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private produtoService: CadPdfService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInProdutos();
    }

    load(id) {
        this.produtoService.find(id)
            .subscribe((produtoResponse: HttpResponse<ArquivosPortal>) => {
                this.produto = produtoResponse.body;
            });
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInProdutos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'produtoListModification',
            (response) => this.load(this.produto.id)
        );
    }
}
