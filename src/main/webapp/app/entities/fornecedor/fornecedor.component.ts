import {Component, OnInit, OnDestroy} from '@angular/core';
import {HttpResponse, HttpErrorResponse} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs/Subscription';
import {JhiEventManager, JhiParseLinks, JhiAlertService} from 'ng-jhipster';

import {Fornecedor} from './fornecedor.model';
import {FornecedorService} from './fornecedor.service';
import {ITEMS_PER_PAGE, Principal} from '../../shared';

@Component({
    selector: 'jhi-fornecedor',
    templateUrl: './fornecedor.component.html'
})
export class FornecedorComponent implements OnInit, OnDestroy {

    currentAccount: any;
    nome: string;
    fornecedors: Fornecedor[];
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
        private fornecedorService: FornecedorService,
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
        this.fornecedorService.query({
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort()
        }).subscribe(
            (res: HttpResponse<Fornecedor[]>) => this.onSuccess(res.body, res.headers),
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
        this.router.navigate(['/fornecedor'], {
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
            this.fornecedorService.queryUserNome({
                page: this.page - 1,
                size: this.itemsPerPage,
                nome: this.nome
            }).subscribe((res) => {
                this.fornecedors = res.body;
                this.links = this.parseLinks.parse(res.headers.get('link'));
                this.totalItems = +res.headers.get('X-Total-Count');
                this.queryCount = this.totalItems;
            });
        }
    }

    clear() {
        this.page = 0;
        this.router.navigate(['/fornecedor', {
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
        this.registerChangeInFornecedors();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Fornecedor) {
        return item.id;
    }

    registerChangeInFornecedors() {
        this.eventSubscriber = this.eventManager.subscribe('fornecedorListModification', (response) => this.loadAll());
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
        this.fornecedors = data;
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
