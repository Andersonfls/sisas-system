import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { NoticiasPortal } from './noticias-portal.model';
import { NoticiasPortalService } from './noticias-portal.service';

@Component({
    selector: 'jhi-noticias-portal-detail',
    templateUrl: './noticias-portal-detail.component.html'
})
export class NoticiasPortalDetailComponent implements OnInit, OnDestroy {

    noticiasPortal: NoticiasPortal;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private noticiasPortalService: NoticiasPortalService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInNoticiasPortals();
    }

    load(id) {
        this.noticiasPortalService.find(id)
            .subscribe((noticiasPortalResponse: HttpResponse<NoticiasPortal>) => {
                this.noticiasPortal = noticiasPortalResponse.body;
            });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
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

    registerChangeInNoticiasPortals() {
        this.eventSubscriber = this.eventManager.subscribe(
            'noticiasPortalListModification',
            (response) => this.load(this.noticiasPortal.id)
        );
    }
}
