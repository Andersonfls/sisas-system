import {Component, OnInit, OnDestroy} from '@angular/core';
import {HttpResponse, HttpErrorResponse} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs/Subscription';
import {JhiEventManager, JhiParseLinks, JhiAlertService} from 'ng-jhipster';

import {ITEMS_PER_PAGE, Principal} from '../../shared';
import * as html2canvas from 'html2canvas';
import {TableUtil} from '../../shared/util/tableUtil';
import * as jsPDF from 'jspdf';
import {ProgramasProjectos, ProgramasProjectosService} from '../../entities/programas-projectos';
import {Comuna, ComunaService} from '../../entities/comuna';
import {Provincia, ProvinciaService} from '../../entities/provincia';
import {Municipio, MunicipioService} from '../../entities/municipio';
import {SistemaAgua} from '../../entities/sistema-agua';

@Component({
    selector: 'jhi-programas-projectos',
    templateUrl: './programas-projectos.component.html'
})
export class ProgramasProjectosComponent implements OnInit, OnDestroy {

    currentAccount: any;
    nome: string;
    programasProjectos: ProgramasProjectos[];
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
    programasProjecto: ProgramasProjectos;
    dataInicialBusca: Date;
    dataFinalBusca: Date;
    comunas: Comuna[];
    provincias: Provincia[];
    municipios: Municipio[];

    constructor(
        private programasProjectosService: ProgramasProjectosService,
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

    loadAll() {
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/programas-projectos'], {
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
            this.programasProjectosService.queryUserNome({
                page: this.page - 1,
                size: this.itemsPerPage,
                nome: this.nome
            }).subscribe((res) => {
                this.programasProjectos = res.body;
                this.links = this.parseLinks.parse(res.headers.get('link'));
                this.totalItems = +res.headers.get('X-Total-Count');
                this.queryCount = this.totalItems;
            });
        }
    }

    clear() {
        this.page = 0;
        this.router.navigate(['/programas-projectos', {
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

    public captureScreen(elementId) {
        const data = document.getElementById(elementId);
        (html2canvas as any)(data).then((canvas) => {
            const imgWidth = 205;
            const imgHeight = canvas.height * imgWidth / canvas.width;
            const contentDataURL = canvas.toDataURL('image/png');
            const pdf = new jsPDF('p', 'mm', 'a4');
            pdf.text('Relatório Programas e Projectos', 55, 7);
            pdf.addImage(contentDataURL, 'PNG', 3, 13, imgWidth, imgHeight);
            pdf.save('relatorio-projectos.pdf');
        }).catch(function(error) {
            // Error Handling
        });
    }

    exportTable(tabeId) {
        TableUtil.exportToExcel(tabeId);
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInProgramasProjectos();
        this.esconderFiltros = false;
        this.programasProjecto = new SistemaAgua();
        this.programasProjecto.comuna = null;
        this.programasProjecto.provincia = null;
        this.programasProjecto.municipio = null;

        this.provinciaService.queryPorNivelUsuario().subscribe(
            (res: HttpResponse<Provincia[]>) => {
                this.provincias = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message));
    }

    buscaPorMunicipio() {
        if (this.programasProjecto.municipio === null) {
            alert('Selecione um município');
        } else {
            this.programasProjectosService.queryMunicipio({
                page: this.page - 1,
                size: this.itemsPerPage,
                nome: this.programasProjecto.municipio.nmMunicipio
            }).subscribe((res) => {
                this.programasProjectos = res.body;
                this.links = this.parseLinks.parse(res.headers.get('link'));
                this.totalItems = +res.headers.get('X-Total-Count');
                this.queryCount = this.totalItems;
            });
        }
    }

    buscaPorProvincia() {
        if (this.programasProjecto.provincia === null) {
            alert('Selecione uma Província');
        } else {
            this.programasProjectosService.queryProvincia({
                page: this.page - 1,
                size: this.itemsPerPage,
                nome: this.programasProjecto.provincia.nmProvincia
            }).subscribe((res) => {
                this.programasProjectos = res.body;
                this.links = this.parseLinks.parse(res.headers.get('link'));
                this.totalItems = +res.headers.get('X-Total-Count');
                this.queryCount = this.totalItems;
            });
        }
    }

    buscaPorComuna() {
        if (this.programasProjecto.comuna === null) {
            alert('Selecione uma Comuna');
        } else {
            this.programasProjectosService.queryComuna({
                page: this.page - 1,
                size: this.itemsPerPage,
                nome: this.programasProjecto.comuna.nmComuna
            }).subscribe((res) => {
                this.programasProjectos = res.body;
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
            this.programasProjectosService.queryPeríodo({
                page: this.page - 1,
                size: this.itemsPerPage,
                dtInicial: this.dataInicialBusca,
                dtFinal: this.dataFinalBusca
            }).subscribe((res) => {
                this.programasProjectos = res.body;
                this.links = this.parseLinks.parse(res.headers.get('link'));
                this.totalItems = +res.headers.get('X-Total-Count');
                this.queryCount = this.totalItems;
            });
        }
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ProgramasProjectos) {
        return item.id;
    }

    registerChangeInProgramasProjectos() {
        this.eventSubscriber = this.eventManager.subscribe('programasProjectosListModification', (response) => this.loadAll());
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
        this.programasProjectos = data;
        console.log(this.programasProjectos);
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }

    onChangeMunicipios() {
        this.municipios = null;
        this.comunas = null;

        this.municipioService.queryMunicipioByProvinciaId({
            provinciaId: this.programasProjecto.provincia.id
        }).subscribe((res) => {
            this.municipios = res.body;
        });
    }

    onChangeComunas() {
        this.comunas = null;
        this.comunaService.queryComunaByMunicipioId({
            municipioId: this.programasProjecto.municipio.id
        }).subscribe((res) => {
                this.comunas = res.body;
            });
    }
}
