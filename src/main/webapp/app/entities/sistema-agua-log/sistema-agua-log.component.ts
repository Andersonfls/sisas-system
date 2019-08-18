import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs/Subscription';
import {JhiAlertService, JhiEventManager, JhiParseLinks} from 'ng-jhipster';

import {SistemaAguaLog} from './sistema-agua-log.model';
import {SistemaAguaLogService} from './sistema-agua-log.service';
import {ITEMS_PER_PAGE, Principal} from '../../shared';

@Component({
    selector: 'jhi-sistema-agua-log',
    templateUrl: './sistema-agua-log.component.html'
})
export class SistemaAguaLogComponent implements OnInit, OnDestroy {

    currentAccount: any;
    nome: string;
    sistemaAguaLogs: SistemaAguaLog[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;

    constructor(
        private sistemaAguaLogService: SistemaAguaLogService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
    }

    loadAll() {
        this.sistemaAguaLogService.query({
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort()
        }).subscribe(
            (res: HttpResponse<SistemaAguaLog[]>) => this.onSuccess(res.body, res.headers),
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/sistema-agua-log'], {
            queryParams:
                {
                    page: this.page,
                    size: this.itemsPerPage,
                    sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
                }
        });
        this.loadAll();
    }

    onChangeNome() {
        if (this.nome === undefined) {
            this.loadAll();
        } else {
            this.sistemaAguaLogService.queryUserNome({
                page: this.page - 1,
                size: this.itemsPerPage,
                nome: this.nome
            }).subscribe((res) => {
                this.sistemaAguaLogs = res.body;
                this.links = this.parseLinks.parse(res.headers.get('link'));
                this.totalItems = +res.headers.get('X-Total-Count');
                this.queryCount = this.totalItems;
            });
        }
    }


    clear() {
        this.page = 0;
        this.router.navigate(['/sistema-agua-log', {
            page: this.page,
            sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
        }]);
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInSistemaAguaLogs();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: SistemaAguaLog) {
        return item.id;
    }

    registerChangeInSistemaAguaLogs() {
        this.eventSubscriber = this.eventManager.subscribe('sistemaAguaLogListModification', (response) => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private onSuccess(data, headers) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = headers.get('X-Total-Count');
        this.queryCount = this.totalItems;
        // this.page = pagingParams.page;
        this.sistemaAguaLogs = data;
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
