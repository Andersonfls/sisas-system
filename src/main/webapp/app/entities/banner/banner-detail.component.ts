import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { BannerService } from './banner.service';
import {Banner} from './banner.model';

@Component({
    selector: 'jhi-produto-detail',
    templateUrl: './banner-detail.component.html'
})
export class BannerDetailComponent implements OnInit, OnDestroy {

    produto: Banner;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private produtoService: BannerService,
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
            .subscribe((produtoResponse: HttpResponse<Banner>) => {
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
