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
import {SistemaAgua, SistemaAguaService} from '../../entities/sistema-agua';
import {Concepcao, ConcepcaoService} from '../../entities/concepcao';
import {Concurso, ConcursoService} from '../../entities/concurso';
import {Adjudicacao, AdjudicacaoService} from '../../entities/adjudicacao';
import {Contrato, ContratoService} from '../../entities/contrato';
import {Execucao, ExecucaoService} from '../../entities/execucao';
import {Empreitada, EmpreitadaService} from '../../entities/empreitada';

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
    isLoaded: boolean;

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
        // private concepcaoService: ConcepcaoService,
        // private concursoService: ConcursoService,
        // private adjService: AdjudicacaoService,
        // private contratoService: ContratoService,
        // private sistemaAguaService: SistemaAguaService,
        // private empreitadaService: EmpreitadaService,
        // private execucaoService: ExecucaoService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
            this.isLoaded = false;
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

        this.provinciaService.query().subscribe(
            (res: HttpResponse<Provincia[]>) => {
                this.provincias = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message));
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
            });
        }
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
            });
        }
    }

    buscaPorProvincia() {
        if (this.programasProjecto.provincia === null) {
            this.programasProjectosService.query().subscribe((res) => {
                this.programasProjectos = res.body;
                console.log(this.programasProjectos);
            });
        } else {
            this.programasProjectosService.queryProvincia({
                page: this.page - 1,
                size: this.itemsPerPage,
                nome: this.programasProjecto.provincia.nmProvincia
            }).subscribe((res) => {
                this.programasProjectos = res.body;
                console.log(this.programasProjectos);
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
            });
        }
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

    // async carregaTodosDadosTabelasAuxiliares() {
    //     if (this.programasProjectos) {
    //         this.programasProjectos.forEach((item) => {
    //             this.loadConcepcao(item);
    //             this.loadConcurso(item);
    //             this.loadAdjudicacao(item);
    //             this.loadContrato(item);
    //             this.loadEmpreitada(item);
    //             this.loadExecucao(item);
    //         });
    //     }
    //
    // }

    // async loadConcepcao(item: ProgramasProjectos) {
    //     item.concepcao = new Concepcao();
    //     this.concepcaoService.findByProgramasProjectos(item.id)
    //         .subscribe((concepcaoResponse: HttpResponse<Concepcao>) => {
    //             const concepcao: Concepcao = concepcaoResponse.body;
    //             item.concepcao = concepcao;
    //         });
    // }
    //
    // async loadConcurso(item: ProgramasProjectos) {
    //     item.concurso = new Concurso();
    //     this.concursoService.findByProgramasProjectos(item.id)
    //         .subscribe((concursoResponse: HttpResponse<Concurso>) => {
    //             const concurso: Concurso = concursoResponse.body;
    //             item.concurso = concurso;
    //         });
    // }
    //
    // async loadAdjudicacao(item: ProgramasProjectos) {
    //     item.adjudicacao = new Adjudicacao();
    //     this.adjService.findByProgramasProjectos(item.id)
    //         .subscribe((adjResponse: HttpResponse<Adjudicacao>) => {
    //             const adjudicacao: Adjudicacao = adjResponse.body;
    //             item.adjudicacao = adjudicacao;
    //         });
    // }
    //
    // async loadContrato(item: ProgramasProjectos) {
    //     item.contrato = new Contrato();
    //     this.contratoService.findByProgramasProjectos(item.id)
    //         .subscribe((contratoResponse: HttpResponse<Contrato>) => {
    //             const contrato: Contrato = contratoResponse.body;
    //             item.contrato = contrato;
    //         });
    // }
    //
    // async loadExecucao(item: ProgramasProjectos) {
    //     item.execucao = new Execucao();
    //     this.execucaoService.findByProgramasProjectos(item.id)
    //         .subscribe((execucaoResponse: HttpResponse<Execucao>) => {
    //             const execucao: Execucao = execucaoResponse.body;
    //             item.execucao = execucao;
    //         });
    // }
    //
    // async loadEmpreitada(item: ProgramasProjectos) {
    //     item.empreitada = new Empreitada();
    //     this.empreitadaService.findByProgramasProjectos(item.id)
    //         .subscribe((empreitadaResponse: HttpResponse<Empreitada>) => {
    //             const empreitada: Empreitada = empreitadaResponse.body;
    //             item.empreitada = empreitada;
    //         });
    // }
}
