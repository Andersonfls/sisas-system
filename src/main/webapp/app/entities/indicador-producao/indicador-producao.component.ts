import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs/Subscription';
import {JhiAlertService, JhiEventManager, JhiParseLinks} from 'ng-jhipster';

import {IndicadorProducao} from './indicador-producao.model';
import {IndicadorProducaoService} from './indicador-producao.service';
import {ITEMS_PER_PAGE, Principal} from '../../shared';
import {Provincia, ProvinciaService} from '../provincia';
import * as html2canvas from 'html2canvas';
import {TableUtil} from '../../shared/util/tableUtil';
import * as jsPDF from 'jspdf';
import {Comuna, ComunaService} from '../comuna';
import {Municipio, MunicipioService} from '../municipio';

@Component({
    selector: 'jhi-indicador-producao',
    templateUrl: './indicador-producao.component.html'
})
export class IndicadorProducaoComponent implements OnInit, OnDestroy {

    currentAccount: any;
    nome: string;
    indicadorProducaos: IndicadorProducao[];
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
    indicador: IndicadorProducao;
    anoFiltro: number;
    listaAnos: number[];
    comunas: Comuna[];
    provincias: Provincia[];
    municipios: Municipio[];
    constructor(
        private indicadorProducaoService: IndicadorProducaoService,
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
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInIndicadorProducaos();
        this.esconderFiltros = true;
        this.indicador = new IndicadorProducao();
        this.indicador.provincia = null;
        this.anoFiltro = null;
        this.montaListaAnos();
    }

    public captureScreen(elementId) {
        const data = document.getElementById(elementId);
        (html2canvas as any)(data).then((canvas) => {
            const imgWidth = 205;
            const imgHeight = canvas.height * imgWidth / canvas.width;
            const contentDataURL = canvas.toDataURL('image/png');
            const pdf = new jsPDF('p', 'mm', 'a4');
            pdf.text('Relatório Indicadores de Produção', 49, 7);
            pdf.addImage(contentDataURL, 'PNG', 3, 13, imgWidth, imgHeight);
            pdf.save('relatorio-indicadores.pdf');
        }).catch(function(error) {
        });
    }

    exportTable(tabeId) {
        TableUtil.exportToExcel(tabeId);
    }

    montaListaAnos() {
        this.listaAnos = new Array();
        const date = new Date();
        const ano = date.getFullYear();

        for (let i = ano; i >= 2015; i-- ) {
            this.listaAnos.push(i);
        }
    }

    loadAll() {
        this.indicadorProducaoService.query({
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort()
        }).subscribe(
            (res: HttpResponse<IndicadorProducao[]>) => this.onSuccess(res.body, res.headers),
            (res: HttpErrorResponse) => this.onError(res.message)
        );

        this.provinciaService.queryPorNivelUsuario().subscribe(
            (res: HttpResponse<Provincia[]>) => {
                this.provincias = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message));

    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/indicador-producao'], {
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
            this.indicadorProducaoService.queryUserNome({
                page: this.page - 1,
                size: this.itemsPerPage,
                nome: this.nome
            }).subscribe((res) => {
                this.indicadorProducaos = res.body;
                this.links = this.parseLinks.parse(res.headers.get('link'));
                this.totalItems = +res.headers.get('X-Total-Count');
                this.queryCount = this.totalItems;
            });
        }
    }

    mostrarFiltros() {
        this.esconderFiltros = !this.esconderFiltros;

        if (this.esconderFiltros) {
            this.loadAll();
        }
    }

    buscaPorAno() {
        if (this.anoFiltro === null) {
            alert('Selecione o Ano');
        } else {
            this.indicadorProducaoService.queryAno({
                page: this.page - 1,
                size: this.itemsPerPage,
                ano: this.anoFiltro
            }).subscribe((res) => {
                this.indicadorProducaos = res.body;
                this.links = this.parseLinks.parse(res.headers.get('link'));
                this.totalItems = +res.headers.get('X-Total-Count');
                this.queryCount = this.totalItems;
            });
        }
    }

    buscaPorProvincia() {
        if (this.indicador.provincia === null) {
            alert('Selecione uma Província');
        } else {
            this.indicadorProducaoService.queryProvincia({
                page: this.page - 1,
                size: this.itemsPerPage,
                nome: this.indicador.provincia.nmProvincia
            }).subscribe((res) => {
                this.indicadorProducaos = res.body;
                this.links = this.parseLinks.parse(res.headers.get('link'));
                this.totalItems = +res.headers.get('X-Total-Count');
                this.queryCount = this.totalItems;
            });
        }
    }

    clear() {
        this.page = 0;
        this.router.navigate(['/indicador-producao', {
            page: this.page,
            sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
        }]);
        this.loadAll();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IndicadorProducao) {
        return item.id;
    }

    registerChangeInIndicadorProducaos() {
        this.eventSubscriber = this.eventManager.subscribe('indicadorProducaoListModification', (response) => this.loadAll());
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
        this.indicadorProducaos = data;
        console.log(data);
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }

    onChangeMunicipios() {
        this.municipios = null;
        this.comunas = null;
        this.municipioService.queryMunicipioByProvinciaId({
            provinciaId: this.indicador.provincia.id
        }).subscribe((res) => {
            this.municipios = res.body;
        });
    }

    onChangeComunas() {
        this.comunas = null;
        this.comunaService.queryComunaByMunicipioId({
            municipioId: this.indicador.municipio.id
        }).subscribe((res) => {
                this.comunas = res.body;
            });
    }
}
