import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ITEMS_PER_PAGE, Principal } from '../../shared';
import * as html2canvas from 'html2canvas';
import {TableUtil} from '../../shared/util/tableUtil';
import * as jsPDF from 'jspdf';
import {SistemaAgua, SistemaAguaService} from '../../entities/sistema-agua';
import {Comuna, ComunaService} from '../../entities/comuna';
import {Provincia, ProvinciaService} from '../../entities/provincia';
import {Municipio, MunicipioService} from '../../entities/municipio';

@Component({
    selector: 'jhi-sistema-agua',
    templateUrl: './sistema-agua.component.html',
})
export class SistemaAguaComponent implements OnInit, OnDestroy {

    currentAccount: any;
    nome: string;
    sistemaAguas: SistemaAgua[];
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
    esconderFiltros: boolean;
    sistemaAgua: SistemaAgua;
    comunas: Comuna[];
    provincias: Provincia[];
    municipios: Municipio[];
    dataInicialBusca: Date;
    dataFinalBusca: Date;

    constructor(
        private sistemaAguaService: SistemaAguaService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private comunaService: ComunaService,
        private municipioService: MunicipioService,
        private provinciaService: ProvinciaService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInSistemaAguas();
        this.esconderFiltros = false;
        this.sistemaAgua = new SistemaAgua();
        this.sistemaAgua.comuna = null;
        this.sistemaAgua.provincia = null;
        this.sistemaAgua.municipio = null;

        this.provinciaService.queryPorNivelUsuario().subscribe(
            (res: HttpResponse<Provincia[]>) => {
                this.provincias = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message));
    }

    loadAll() {
    }

    public captureScreen(elementId) {
        const data = document.getElementById(elementId);
        (html2canvas as any)(data).then((canvas) => {
            const imgWidth = 275;
            const imgHeight = canvas.height * imgWidth / canvas.width;
            const contentDataURL = canvas.toDataURL('image/png');
            const pdf = new jsPDF('l', 'mm', 'a4');
            pdf.text('Relatório Sistemas de Águas', 55, 7);
            pdf.addImage(contentDataURL, 'PNG', 3, 13, imgWidth, imgHeight);
            pdf.save('relatorio-sistemas.pdf');
        }).catch(function(error) {
            // Error Handling
        });
    }

    exportTable(tabeId) {
        TableUtil.exportToExcel(tabeId);
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }
    transition() {
        this.router.navigate(['/sistema-agua'], {queryParams:
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
            this.sistemaAguaService.queryUserNome({
                page: this.page - 1,
                size: this.itemsPerPage,
                nome: this.nome
            }).subscribe((res) => {
                this.sistemaAguas = res.body;
                this.links = this.parseLinks.parse(res.headers.get('link'));
                this.totalItems = +res.headers.get('X-Total-Count');
                this.queryCount = this.totalItems;
            });
        }
    }

    buscaPorMunicipio() {
        if (this.sistemaAgua.municipio === null) {
            alert('Selecione um município');
        } else {
            this.sistemaAguaService.queryMunicipio({
                page: this.page - 1,
                size: this.itemsPerPage,
                nome: this.sistemaAgua.municipio.nmMunicipio
            }).subscribe((res) => {
                this.sistemaAguas = res.body;
                this.links = this.parseLinks.parse(res.headers.get('link'));
                this.totalItems = +res.headers.get('X-Total-Count');
                this.queryCount = this.totalItems;
            });
        }
    }

    buscaPorProvincia() {
        if (this.sistemaAgua.provincia === null) {
            this.sistemaAguaService.query().subscribe((res) => {
                this.sistemaAguas = res.body;
                this.links = this.parseLinks.parse(res.headers.get('link'));
                this.totalItems = +res.headers.get('X-Total-Count');
                this.queryCount = this.totalItems;
            });
        } else {
            this.sistemaAguaService.queryProvincia({
                page: this.page - 1,
                size: this.itemsPerPage,
                nome: this.sistemaAgua.provincia.nmProvincia
            }).subscribe((res) => {
                this.sistemaAguas = res.body;
                this.links = this.parseLinks.parse(res.headers.get('link'));
                this.totalItems = +res.headers.get('X-Total-Count');
                this.queryCount = this.totalItems;
            });
        }
    }

    buscaPorComuna() {
        if (this.sistemaAgua.comuna === null) {
            alert('Selecione uma Comuna');
        } else {
            this.sistemaAguaService.queryComuna({
                page: this.page - 1,
                size: this.itemsPerPage,
                nome: this.sistemaAgua.comuna.nmComuna
            }).subscribe((res) => {
                this.sistemaAguas = res.body;
                this.links = this.parseLinks.parse(res.headers.get('link'));
                this.totalItems = +res.headers.get('X-Total-Count');
                this.queryCount = this.totalItems;
            });
        }
    }

    buscaPorPeriodo() {
        console.log(this.dataInicialBusca);
        console.log(this.dataFinalBusca);
        if (this.dataInicialBusca === null || this.dataFinalBusca === null) {
            alert('Digite o Período (Data Inicial e Data Final)');
        } else {
            this.sistemaAguaService.queryPeríodo({
                page: this.page - 1,
                size: this.itemsPerPage,
                dtInicial: this.dataInicialBusca,
                dtFinal: this.dataFinalBusca
            }).subscribe((res) => {
                this.sistemaAguas = res.body;
                this.links = this.parseLinks.parse(res.headers.get('link'));
                this.totalItems = +res.headers.get('X-Total-Count');
                this.queryCount = this.totalItems;
            });
        }
    }

    clear() {
        this.page = 0;
        this.router.navigate(['/sistema-agua', {
            page: this.page,
            sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
        }]);
        this.loadAll();
    }

    mostrarFiltros() {
        this.esconderFiltros = !this.esconderFiltros;

        if (this.esconderFiltros) {
            this.loadAll();
        }
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: SistemaAgua) {
        return item.id;
    }
    registerChangeInSistemaAguas() {
        this.eventSubscriber = this.eventManager.subscribe('sistemaAguaListModification', (response) => this.loadAll());
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
        this.sistemaAguas = data;
    }
    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }

    onChangeMunicipios() {
        this.municipios = null;
        this.comunas = null;

        this.municipioService.queryMunicipioByProvinciaId({
            provinciaId: this.sistemaAgua.provincia.id
        }).subscribe((res) => {
            this.municipios = res.body;
        });
    }

    onChangeComunas() {
        this.comunas = null;

        this.comunaService.queryComunaByMunicipioId({
            municipioId: this.sistemaAgua.municipio.id
        })
            .subscribe((res) => {
                this.comunas = res.body;
            });
    }
}
