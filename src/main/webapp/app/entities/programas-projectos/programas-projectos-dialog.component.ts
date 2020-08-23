import {Component, OnInit, OnDestroy, ViewChild, ElementRef} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ProgramasProjectos } from './programas-projectos.model';
import { ProgramasProjectosPopupService } from './programas-projectos-popup.service';
import { ProgramasProjectosService } from './programas-projectos.service';
import { Comuna, ComunaService } from '../comuna';
import {Subscription} from 'rxjs';
import {Concepcao, ConcepcaoService} from '../concepcao';
import {SistemaAgua, SistemaAguaService} from '../sistema-agua';
import {Concurso, ConcursoService} from '../concurso';
import {Adjudicacao, AdjudicacaoService} from '../adjudicacao';
import {Contrato, ContratoService} from '../contrato';
import {Provincia, ProvinciaService} from '../provincia';
import {Municipio, MunicipioService} from '../municipio';
import {Execucao, ExecucaoService} from '../execucao';
import {Empreitada, EmpreitadaService} from '../empreitada';
import {Situacao, SituacaoService} from '../situacao';
import {Especialidades} from '../especialidades/especialidades.model';
import {EspecialidadesService} from '../especialidades/especialidades.service';
import {Fornecedor, FornecedorService} from '../fornecedor';

@Component({
    selector: 'jhi-programas-projectos-dialog',
    templateUrl: './programas-projectos-dialog.component.html'
})

export class ProgramasProjectosDialogComponent implements OnInit {

    isSaving: boolean;
    programasProjectos: ProgramasProjectos;
    provincias: Provincia[];
    provincia: Provincia;
    municipios: Municipio[];
    municipio: Municipio;
    comunas: Comuna[];
    comuna: Comuna;
    situacaos: Situacao[];
    sistemaaguas: SistemaAgua[];
    especialidadess: Especialidades[];
    fornecedores: Fornecedor[];
    private subscription: Subscription;
    moeda: string;
    controleSessoes: string;

    // Concepcao
    concepcao: Concepcao;
    @ViewChild('closeModal') private closeModal: ElementRef;
    // Concurso
    concurso: Concurso;
    @ViewChild('closeModalConcurso') private closeModalConcurso: ElementRef;
    // Adjacao
    adjudicacao: Adjudicacao;
    @ViewChild('closeModalAdj') private closeModalAdj: ElementRef;
    // CONTRATO
    contrato: Contrato;
    @ViewChild('closeModalContrato') private closeModalContrato: ElementRef;
    // EMPREITADA
    empreitada: Empreitada;
    @ViewChild('closeModalEmpreitada') private closeModalEmpreitada: ElementRef;
    // EXECUCAO
    execucao: Execucao;
    @ViewChild('closeModalExecucao') private closeModalExecucao: ElementRef;

    constructor(
        private jhiAlertService: JhiAlertService,
        private programasProjectosService: ProgramasProjectosService,
        private comunaService: ComunaService,
        private municipioService: MunicipioService,
        private provinciaService: ProvinciaService,
        private especialidadesService: EspecialidadesService,
        private eventManager: JhiEventManager,
        private route: ActivatedRoute,
        private concepcaoService: ConcepcaoService,
        private concursoService: ConcursoService,
        private adjService: AdjudicacaoService,
        private contratoService: ContratoService,
        private sistemaAguaService: SistemaAguaService,
        private empreitadaService: EmpreitadaService,
        private execucaoService: ExecucaoService,
        private situacaoService: SituacaoService,
        private fornecedorService: FornecedorService,
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.moeda = 'Kz';
        this.programasProjectos = new ProgramasProjectos();
        this.subscription = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.load(params['id']);
            } else {
                this.montaListaInicio();
            }
        });
        this.concepcao = new Concepcao();
        this.concurso = new Concurso();
        this.adjudicacao = new Adjudicacao();
        this.contrato = new Contrato();
        this.empreitada = new Empreitada();
        this.execucao = new Execucao();
        this.loadAllSisstemasAgua();

        this.situacaoService.query().subscribe( (res: HttpResponse<Situacao[]>) => { this.situacaos = res.body; },
            (res: HttpErrorResponse) => this.onError(res.message));
        this.especialidadesService.query().subscribe( (res: HttpResponse<Especialidades[]>) => { this.especialidadess = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message));
        this.fornecedorService.query().subscribe( (res: HttpResponse<Fornecedor[]>) => { this.fornecedores = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message));
    }

    loadAllSisstemasAgua() {
        this.sistemaAguaService.query().subscribe(
            (res: HttpResponse<SistemaAgua[]>) => this.sistemaaguas = res.body,
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    load(id) {
        this.programasProjectosService.find(id)
            .subscribe((programasProjectosResponse: HttpResponse<ProgramasProjectos>) => {
                this.programasProjectos = this.converteDatasProgramasProjectos(programasProjectosResponse.body);
                this.loadConcepcao(this.programasProjectos.id);
                this.loadConcurso(this.programasProjectos.id);
                this.loadAdjudicacao(this.programasProjectos.id);
                this.loadContrato(this.programasProjectos.id);
                this.loadEmpreitada(this.programasProjectos.id);
                this.loadExecucao(this.programasProjectos.id);

                this.montaListaInicio();
                if (this.programasProjectos.provincia) {
                    this.onChangeMunicipios();
                }
                if (this.programasProjectos.municipio) {
                    this.onChangeComunas();
                }
            });
    }

    async loadConcepcao(id) {
        this.concepcaoService.findByProgramasProjectos(id)
            .subscribe((concepcaoResponse: HttpResponse<Concepcao>) => {
                const concepcao: Concepcao = concepcaoResponse.body;
                // if (concepcao.dtLancamento) {
                //     concepcao.dtLancamento = {
                //         year: concepcao.dtLancamento.getFullYear(),
                //         month: concepcao.dtLancamento.getMonth() + 1,
                //         day: concepcao.dtLancamento.getDate()
                //     };
                // }
                if (concepcao.dtUltimaAlteracao) {
                    concepcao.dtUltimaAlteracao = {
                        year: concepcao.dtUltimaAlteracao.getFullYear(),
                        month: concepcao.dtUltimaAlteracao.getMonth() + 1,
                        day: concepcao.dtUltimaAlteracao.getDate()
                    };
                }
                if (concepcao.dtAprovacaoCon) {
                    concepcao.dtAprovacaoCon = {
                        year: concepcao.dtAprovacaoCon.getFullYear(),
                        month: concepcao.dtAprovacaoCon.getMonth() + 1,
                        day: concepcao.dtAprovacaoCon.getDate()
                    };
                }
                if (concepcao.dtElaboracaoCon) {
                    concepcao.dtElaboracaoCon = {
                        year: concepcao.dtElaboracaoCon.getFullYear(),
                        month: concepcao.dtElaboracaoCon.getMonth() + 1,
                        day: concepcao.dtElaboracaoCon.getDate()
                    };
                }
                this.concepcao = concepcao;
            });
    }

    async loadConcurso(id) {
        this.concursoService.findByProgramasProjectos(id)
            .subscribe((concursoResponse: HttpResponse<Concurso>) => {
                const concurso: Concurso = concursoResponse.body;
                if (concurso.dtLancamento) {
                    concurso.dtLancamento = {
                        year: concurso.dtLancamento.getFullYear(),
                        month: concurso.dtLancamento.getMonth() + 1,
                        day: concurso.dtLancamento.getDate()
                    };
                }
                if (concurso.dtUltimaAlteracao) {
                    concurso.dtUltimaAlteracao = {
                        year: concurso.dtUltimaAlteracao.getFullYear(),
                        month: concurso.dtUltimaAlteracao.getMonth() + 1,
                        day: concurso.dtUltimaAlteracao.getDate()
                    };
                }
                if (concurso.dtAberturaProposta) {
                    concurso.dtAberturaProposta = {
                        year: concurso.dtAberturaProposta.getFullYear(),
                        month: concurso.dtAberturaProposta.getMonth() + 1,
                        day: concurso.dtAberturaProposta.getDate()
                    };
                }
                if (concurso.dtAprovRelAvalFinal) {
                    concurso.dtAprovRelAvalFinal = {
                        year: concurso.dtAprovRelAvalFinal.getFullYear(),
                        month: concurso.dtAprovRelAvalFinal.getMonth() + 1,
                        day: concurso.dtAprovRelAvalFinal.getDate()
                    };
                }
                if (concurso.dtConclusaoAvaliacaoRelPrel) {
                    concurso.dtConclusaoAvaliacaoRelPrel = {
                        year: concurso.dtConclusaoAvaliacaoRelPrel.getFullYear(),
                        month: concurso.dtConclusaoAvaliacaoRelPrel.getMonth() + 1,
                        day: concurso.dtConclusaoAvaliacaoRelPrel.getDate()
                    };
                }
                if (concurso.dtEntregaProposta) {
                    concurso.dtEntregaProposta = {
                        year: concurso.dtEntregaProposta.getFullYear(),
                        month: concurso.dtEntregaProposta.getMonth() + 1,
                        day: concurso.dtEntregaProposta.getDate()
                    };
                }
                if (concurso.dtNegociacao) {
                    concurso.dtNegociacao = {
                        year: concurso.dtNegociacao.getFullYear(),
                        month: concurso.dtNegociacao.getMonth() + 1,
                        day: concurso.dtNegociacao.getDate()
                    };
                }
                this.concurso = concurso;
                this.concurso.tipoConcurso = this.concepcao.tipoConcurso;
                this.validaTipoMoeda();
            });
    }

    async loadAdjudicacao(id) {
        this.adjService.findByProgramasProjectos(id)
            .subscribe((adjResponse: HttpResponse<Adjudicacao>) => {
                const adjudicacao: Adjudicacao = adjResponse.body;
                // if (adjudicacao.dtLancamento) {
                //     adjudicacao.dtLancamento = {
                //         year: adjudicacao.dtLancamento.getFullYear(),
                //         month: adjudicacao.dtLancamento.getMonth() + 1,
                //         day: adjudicacao.dtLancamento.getDate()
                //     };
                // }
                if (adjudicacao.dtComunicaoAdjudicacao) {
                    adjudicacao.dtComunicaoAdjudicacao = {
                        year: adjudicacao.dtComunicaoAdjudicacao.getFullYear(),
                        month: adjudicacao.dtComunicaoAdjudicacao.getMonth() + 1,
                        day: adjudicacao.dtComunicaoAdjudicacao.getDate()
                    };
                }
                if (adjudicacao.dtPrestacaoGarantBoaExec) {
                    adjudicacao.dtPrestacaoGarantBoaExec = {
                        year: adjudicacao.dtPrestacaoGarantBoaExec.getFullYear(),
                        month: adjudicacao.dtPrestacaoGarantBoaExec.getMonth() + 1,
                        day: adjudicacao.dtPrestacaoGarantBoaExec.getDate()
                    };
                }
                if (adjudicacao.dtSubmissaoMinutContrato) {
                    adjudicacao.dtSubmissaoMinutContrato = {
                        year: adjudicacao.dtSubmissaoMinutContrato.getFullYear(),
                        month: adjudicacao.dtSubmissaoMinutContrato.getMonth() + 1,
                        day: adjudicacao.dtSubmissaoMinutContrato.getDate()
                    };
                }

                this.adjudicacao = adjudicacao;
                this.adjudicacao.tipoConcurso = this.concepcao.tipoConcurso;
            });
    }

    async loadContrato(id) {
        this.contratoService.findByProgramasProjectos(id)
            .subscribe((contratoResponse: HttpResponse<Contrato>) => {
                console.log(contratoResponse);
                const contrato: Contrato = contratoResponse.body;
                // if (contrato.dtLancamento) {
                //     contrato.dtLancamento = {
                //         year: contrato.dtLancamento.getFullYear(),
                //         month: contrato.dtLancamento.getMonth() + 1,
                //         day: contrato.dtLancamento.getDate()
                //     };
                // }
                if (contrato.dtAdiantamento) {
                    contrato.dtAdiantamento = {
                        year: contrato.dtAdiantamento.getFullYear(),
                        month: contrato.dtAdiantamento.getMonth() + 1,
                        day: contrato.dtAdiantamento.getDate()
                    };
                }
                if (contrato.dtAssinatura) {
                    contrato.dtAssinatura = {
                        year: contrato.dtAssinatura.getFullYear(),
                        month: contrato.dtAssinatura.getMonth() + 1,
                        day: contrato.dtAssinatura.getDate()
                    };
                }
                if (contrato.dtFinalizacaoProcessoHomologAprov) {
                    contrato.dtFinalizacaoProcessoHomologAprov = {
                        year: contrato.dtFinalizacaoProcessoHomologAprov.getFullYear(),
                        month: contrato.dtFinalizacaoProcessoHomologAprov.getMonth() + 1,
                        day: contrato.dtFinalizacaoProcessoHomologAprov.getDate()
                    };
                }
                if (contrato.dtInicio) {
                    contrato.dtInicio = {
                        year: contrato.dtInicio.getFullYear(),
                        month: contrato.dtInicio.getMonth() + 1,
                        day: contrato.dtInicio.getDate()
                    };
                }
                if (contrato.dtRecepcaoComicionamento) {
                    contrato.dtRecepcaoComicionamento = {
                        year: contrato.dtRecepcaoComicionamento.getFullYear(),
                        month: contrato.dtRecepcaoComicionamento.getMonth() + 1,
                        day: contrato.dtRecepcaoComicionamento.getDate()
                    };
                }
                if (contrato.dtRecepcaoDefinitiva) {
                    contrato.dtRecepcaoDefinitiva = {
                        year: contrato.dtRecepcaoDefinitiva.getFullYear(),
                        month: contrato.dtRecepcaoDefinitiva.getMonth() + 1,
                        day: contrato.dtRecepcaoDefinitiva.getDate()
                    };
                }
                if (contrato.dtRecepcaoProvisoria) {
                    contrato.dtRecepcaoProvisoria = {
                        year: contrato.dtRecepcaoProvisoria.getFullYear(),
                        month: contrato.dtRecepcaoProvisoria.getMonth() + 1,
                        day: contrato.dtRecepcaoProvisoria.getDate()
                    };
                }
                if (contrato.dtVistoTribunalContas) {
                    contrato.dtVistoTribunalContas = {
                        year: contrato.dtVistoTribunalContas.getFullYear(),
                        month: contrato.dtVistoTribunalContas.getMonth() + 1,
                        day: contrato.dtVistoTribunalContas.getDate()
                    };
                }

                if (contrato.dtPagamentoEmolumentos) {
                    contrato.dtPagamentoEmolumentos = {
                        year: contrato.dtPagamentoEmolumentos.getFullYear(),
                        month: contrato.dtPagamentoEmolumentos.getMonth() + 1,
                        day: contrato.dtPagamentoEmolumentos.getDate()
                    };
                }

                if (contrato.dtPrazoGarantiaAditamento) {
                    contrato.dtPrazoGarantiaAditamento = {
                        year: contrato.dtPrazoGarantiaAditamento.getFullYear(),
                        month: contrato.dtPrazoGarantiaAditamento.getMonth() + 1,
                        day: contrato.dtPrazoGarantiaAditamento.getDate()
                    };
                }

                if (contrato.dtPrazosVinculativos) {
                    contrato.dtPrazosVinculativos = {
                        year: contrato.dtPrazosVinculativos.getFullYear(),
                        month: contrato.dtPrazosVinculativos.getMonth() + 1,
                        day: contrato.dtPrazosVinculativos.getDate()
                    };
                }

                this.contrato = contrato;
                this.contrato.tipoConcurso = this.concepcao.tipoConcurso;
            });
    }

    async loadExecucao(id) {
        this.execucaoService.findByProgramasProjectos(id)
            .subscribe((execucaoResponse: HttpResponse<Execucao>) => {
                const execucao: Execucao = execucaoResponse.body;
                // if (execucao.dtLancamento) {
                //     execucao.dtLancamento = {
                //         year: execucao.dtLancamento.getFullYear(),
                //         month: execucao.dtLancamento.getMonth() + 1,
                //         day: execucao.dtLancamento.getDate()
                //     };
                // }
                if (execucao.dtPeridoReferencia) {
                    execucao.dtPeridoReferencia = {
                        year: execucao.dtPeridoReferencia.getFullYear(),
                        month: execucao.dtPeridoReferencia.getMonth() + 1,
                        day: execucao.dtPeridoReferencia.getDate()
                    };
                }
                if (execucao.dtFimReferencia) {
                    execucao.dtFimReferencia = {
                        year: execucao.dtFimReferencia.getFullYear(),
                        month: execucao.dtFimReferencia.getMonth() + 1,
                        day: execucao.dtFimReferencia.getDate()
                    };
                }
                if (execucao.dtFactura) {
                    execucao.dtFactura = {
                        year: execucao.dtFactura.getFullYear(),
                        month: execucao.dtFactura.getMonth() + 1,
                        day: execucao.dtFactura.getDate()
                    };
                }
                this.execucao = execucao;
                this.execucao.tipoEmpreitada = this.contrato.tipoEmpreitada;
            });
    }

    async loadEmpreitada(id) {
        this.empreitadaService.findByProgramasProjectos(id)
            .subscribe((empreitadaResponse: HttpResponse<Empreitada>) => {
                const empreitada: Empreitada = empreitadaResponse.body;
                // if (empreitada.dtLancamento) {
                //     empreitada.dtLancamento = {
                //         year: empreitada.dtLancamento.getFullYear(),
                //         month: empreitada.dtLancamento.getMonth() + 1,
                //         day: empreitada.dtLancamento.getDate()
                //     };
                // }
                this.empreitada = empreitada;
                this.empreitada.tipoEmpreitada = this.contrato.tipoEmpreitada;
            });
    }

    converteDatasProgramasProjectos(data: ProgramasProjectos) {
        const programasProjectos: ProgramasProjectos = data;
        // if (programasProjectos.dtLancamento) {
        //     programasProjectos.dtLancamento = {
        //         year: programasProjectos.dtLancamento.getFullYear(),
        //         month: programasProjectos.dtLancamento.getMonth() + 1,
        //         day: programasProjectos.dtLancamento.getDate()
        //     };
        // }
        if (programasProjectos.dtUltimaAlteracao) {
            programasProjectos.dtUltimaAlteracao = {
                year: programasProjectos.dtUltimaAlteracao.getFullYear(),
                month: programasProjectos.dtUltimaAlteracao.getMonth() + 1,
                day: programasProjectos.dtUltimaAlteracao.getDate()
            };
        }
        return programasProjectos;
    }

    montaListaInicio() {
        this.provinciaService.queryPorNivelUsuario().subscribe(
            (res: HttpResponse<Provincia[]>) => {
                this.provincias = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message));

        this.municipioService.query().subscribe(
            (res: HttpResponse<Municipio[]>) => {
                this.municipios = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message));

        this.comunaService.query().subscribe(
            (res: HttpResponse<Comuna[]>) => {
                this.comunas = res.body;
            }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    validaData() {
        const aprovacao: Date = new Date(this.concepcao.dtAprovacaoCon.year, this.concepcao.dtAprovacaoCon.month - 1, this.concepcao.dtAprovacaoCon.day);
        const elaboracao = new Date(this.concepcao.dtElaboracaoCon.year, this.concepcao.dtElaboracaoCon.month - 1, this.concepcao.dtElaboracaoCon.day );
        if (aprovacao > elaboracao ) {
            alert('A data de aprovação não pode ser maior que a data de elaboração');
            this.concepcao.dtAprovacaoCon = null;
        }
    }

    validaDataAberturaProposta() {
        const entrega: Date = new Date(this.concurso.dtEntregaProposta.year, this.concurso.dtEntregaProposta.month - 1, this.concurso.dtEntregaProposta.day);
        const abertura = new Date(this.concurso.dtAberturaProposta.year, this.concurso.dtAberturaProposta.month - 1, this.concurso.dtAberturaProposta.day );
        if (abertura > entrega ) {
            alert('A data de abertura não pode ser maior que a data de entrega');
            this.concurso.dtAberturaProposta = null;
        }
    }

    validaDataConclusaoAvaliacao() {
        const entrega: Date = new Date(this.concurso.dtEntregaProposta.year, this.concurso.dtEntregaProposta.month - 1, this.concurso.dtEntregaProposta.day);
        const conclusao = new Date(this.concurso.dtConclusaoAvaliacaoRelPrel.year, this.concurso.dtConclusaoAvaliacaoRelPrel.month - 1, this.concurso.dtConclusaoAvaliacaoRelPrel.day );
        if (conclusao > entrega ) {
            alert('A data de conclusão não pode ser maior que a data de entrega');
            this.concurso.dtConclusaoAvaliacaoRelPrel = null;
        }
    }

    validaDataNegociacao() {
        const entrega: Date = new Date(this.concurso.dtEntregaProposta.year, this.concurso.dtEntregaProposta.month - 1, this.concurso.dtEntregaProposta.day);
        const negociacao = new Date(this.concurso.dtNegociacao.year, this.concurso.dtNegociacao.month - 1, this.concurso.dtNegociacao.day );
        if (negociacao > entrega ) {
            alert('A data de negociação não pode ser maior que a data de entrega');
            this.concurso.dtNegociacao = null;
        }
    }

    validaDataAprovacaoFinal() {
        const entrega: Date = new Date(this.concurso.dtEntregaProposta.year, this.concurso.dtEntregaProposta.month - 1, this.concurso.dtEntregaProposta.day);
        const aprovacaoFinal = new Date(this.concurso.dtAprovRelAvalFinal.year, this.concurso.dtAprovRelAvalFinal.month - 1, this.concurso.dtAprovRelAvalFinal.day );
        if (aprovacaoFinal > entrega ) {
            alert('A data de aprovacão rel. aval. final não pode ser maior que a data de entrega');
            this.concurso.dtAprovRelAvalFinal = null;
        }
    }

    validaDataAdjudicacao(data: any, tipo) {
        const dataLancamento: Date = new Date(this.adjudicacao.dtLancamento.year, this.concurso.dtLancamento.month - 1, this.concurso.dtLancamento.day);
        const dt = new Date(data.year, data.month - 1, data.day );
        if (dt > dataLancamento ) {
            alert('A data informada não pode ser maior que a data de lançamento');
            if (tipo === 'submissao') {
                this.adjudicacao.dtSubmissaoMinutContrato = null;
            } else if (tipo === 'prestacao') {
                this.adjudicacao.dtPrestacaoGarantBoaExec = null;
            } else if (tipo === 'comunicacao') {
                this.adjudicacao.dtComunicaoAdjudicacao = null;
            }
        }
    }

    validaDataContrato(data: any, tipo) {
        const dataAssinatura: Date = new Date(this.contrato.dtAssinatura.year, this.contrato.dtAssinatura.month - 1, this.contrato.dtAssinatura.day);
        const dt = new Date(data.year, data.month - 1, data.day );
        if (dt > dataAssinatura ) {
            alert('A data informada não pode ser maior que a data de assinatura!');
            if (tipo === 'inicio') {
                this.contrato.dtInicio = null;
            } else if (tipo === 'recepcao') {
                this.contrato.dtRecepcaoProvisoria = null;
            } else if (tipo === 'finalizacao') {
                this.contrato.dtFinalizacaoProcessoHomologAprov = null;
            } else if (tipo === 'adiantamento') {
                this.contrato.dtAdiantamento = null;
            } else if (tipo === 'definitiva') {
                this.contrato.dtRecepcaoDefinitiva = null;
            } else if (tipo === 'comissionamento') {
                this.contrato.dtRecepcaoComicionamento = null;
            }
        }
    }

    validaTipoMoeda() {
        if (this.contrato.tipoMoeda === 'EUR') {
            this.moeda = '€ ';
        } else if (this.contrato.tipoMoeda === 'USD') {
            this.moeda = '$ ';
        } else {
            this.moeda = 'Kz ';
        }
    }

    validaConcepcao() {
        if (this.programasProjectos.id === undefined || this.programasProjectos.id === null) {
            this.programasProjectos.dtLancamento = new Date();
            this.programasProjectosService.create(this.programasProjectos).subscribe( (resp) => {
                this.programasProjectos = this.converteDatasProgramasProjectos(resp.body);
                console.log(resp);
                this.concepcao.programasProjectos = new ProgramasProjectos();
                this.concepcao.programasProjectos.id = this.programasProjectos.id;
                this.salvarConcepcao();
            });
        } else {
            this.concepcao.programasProjectos = new ProgramasProjectos();
            this.concepcao.programasProjectos.id = this.programasProjectos.id;
            this.salvarConcepcao();
        }
    }

    salvarConcepcao() {
        if (this.concepcao.id !== undefined && this.concepcao.id !== null) {
                this.concepcaoService.update(this.concepcao).subscribe( (event) => {
                    alert('Concepção foi atualizado com sucesso');
                    console.log(event);
                    this.hideModalConcepcao();
                    this.concepcao = event.body;
                });
        } else {
            this.concepcao.dtLancamento = new Date();
            this.concepcaoService.create(this.concepcao).subscribe( (event) => {
                alert('Concepção foi criado com sucesso');
                console.log(event);
                this.hideModalConcepcao();
                this.concepcao = event.body;
            });
        }
    }

    // CONCURSO
    validaConcurso() {
        this.concurso.programasProjectos = new ProgramasProjectos();
        this.concurso.programasProjectos.id = this.programasProjectos.id;
        this.salvarConcurso();
    }

    salvarConcurso() {
        if (this.concurso.id !== undefined && this.concurso.id !== null) {
            this.concursoService.update(this.concurso).subscribe( (event) => {
                alert('Concurso foi atualizado com sucesso');
                this.hideModalConcurso();
                this.concurso = event.body;
                this.adjudicacao.tipoConcurso = this.concepcao.tipoConcurso;
            });
        } else {
            this.concursoService.create(this.concurso).subscribe( (event) => {
                alert('Concurso foi criado com sucesso');
                this.hideModalConcurso();
                this.concurso = event.body;
                this.adjudicacao.tipoConcurso = this.concepcao.tipoConcurso;
            });
        }
    }

    // ADJUDICACAO
    validaAdjudicacao() {
        this.adjudicacao.programasProjectos = new ProgramasProjectos();
        this.adjudicacao.programasProjectos.id = this.programasProjectos.id;
        this.salvarAdjudicacao();
    }

    salvarAdjudicacao() {
        if (this.adjudicacao.id !== undefined) {
            this.adjService.update(this.adjudicacao).subscribe( (event) => {
                alert('Adjudicacao foi atualizado com sucesso');
                console.log(event);
                this.hideModalAdj();
                this.adjudicacao = event.body;
            });
        } else {
            this.adjudicacao.dtLancamento = new Date();
            this.adjService.create(this.adjudicacao).subscribe( (event) => {
                alert('Adjudicacao foi criado com sucesso');
                console.log(event);
                this.hideModalAdj();
                this.adjudicacao = event.body;
            });
        }
    }

    // CONTRATO
    validaContrato() {
        this.contrato.programasProjectos = new ProgramasProjectos();
        this.contrato.programasProjectos.id = this.programasProjectos.id;
        this.salvarContrato();
    }

    salvarContrato() {
        if (this.contrato.id !== undefined && this.contrato.id !== null) {
            this.contratoService.update(this.contrato).subscribe( (event) => {
                alert('Contrato foi atualizado com sucesso');
                console.log(event);
                this.hideModalContrato();
                this.contrato = event.body;
            });
        } else {
            this.contrato.dtLancamento = new Date();
            this.contratoService.create(this.contrato).subscribe( (event) => {
                alert('Contrato foi criado com sucesso');
                console.log(event);
                this.hideModalContrato();
                this.contrato = event.body;
            });
        }
    }

    // EMPREITADA
    validaEmpreitada() {
        this.empreitada.idProgramasProjectosId = new ProgramasProjectos();
        this.empreitada.idProgramasProjectosId.id = this.programasProjectos.id;
        this.empreitada.idContratoId = this.contrato.id;
        this.salvarEmpreitada();
    }

    salvarEmpreitada() {
        if (this.empreitada.id !== undefined && this.empreitada.id !== null) {
            this.empreitadaService.update(this.empreitada).subscribe( (event) => {
                alert('EMPREITADA foi atualizada com sucesso!');
                this.hideModalEmpreitada();
                this.empreitada = event.body;
            });
        } else {
            this.empreitada.dtLancamento = new Date();
            this.empreitadaService.create(this.empreitada).subscribe( (event) => {
                alert('EMPREITADA foi criada com sucesso!');
                this.hideModalEmpreitada();
                this.empreitada = event.body;
            });
        }
    }

    // EXECUCAO
    validaExecucao() {
        this.execucao.idProgramasProjectosId = new ProgramasProjectos();
        this.execucao.idProgramasProjectosId.id = this.programasProjectos.id;
        this.execucao.idContratoId = this.contrato.id;
        this.salvarExecucao();
    }

    salvarExecucao() {
        if (this.execucao.id !== undefined && this.execucao.id !== null) {
            this.execucaoService.update(this.execucao).subscribe( (event) => {
                alert('Execução foi atualizada com sucesso!');
                console.log(event);
                this.hideModalExecucao();
                this.execucao = event.body;
            });
        } else {
            this.execucao.dtLancamento = new Date();
            this.execucaoService.create(this.execucao).subscribe( (event) => {
                alert('Execução foi criada com sucesso!');
                console.log(event);
                this.hideModalExecucao();
                this.execucao = event.body;
            });
        }
    }

    // SALVA PROGRAMAS E PROJECTOS
    save() {
        this.isSaving = true;
        console.log(this.programasProjectos);
        if (this.programasProjectos.id !== undefined) {
            this.subscribeToSaveResponse(
                this.programasProjectosService.update(this.programasProjectos));
        } else {
            this.subscribeToSaveResponse(
                this.programasProjectosService.create(this.programasProjectos));
        }
    }

    async onChangeMunicipios() {
        this.municipios = null;
        this.comunas = null;

        this.municipioService.queryMunicipioByProvinciaId({
            provinciaId: this.programasProjectos.provincia.id
        }).subscribe((res) => {
            this.municipios = res.body;
        });
    }

    async onChangeComunas() {
        this.comunas = null;

        this.comunaService.queryComunaByMunicipioId({
            municipioId: this.programasProjectos.municipio.id
        }).subscribe((res) => {
            this.comunas = res.body;
        });
    }

    public async habilitarTela(valor) {
        this.controleSessoes = valor;
        if (valor === 'Concepcao') {
            if (this.programasProjectos.id) {
                await this.loadConcepcao(this.programasProjectos.id);
            }
        } else if (valor === 'Concurso') {
            await this.loadConcurso(this.programasProjectos.id);
            this.concurso.tipoConcurso = this.concepcao.tipoConcurso;
        } else if (valor === 'Adjudicacao') {
            await this.loadAdjudicacao(this.programasProjectos.id);
            this.adjudicacao.tipoConcurso = this.concepcao.tipoConcurso;
        } else if (valor === 'Contrato') {
            await this.loadContrato(this.programasProjectos.id);
            this.contrato.tipoConcurso = this.concepcao.tipoConcurso;
        } else  if (valor === 'Empreitada') {
            await this.loadEmpreitada(this.programasProjectos.id);
            this.empreitada.tipoEmpreitada = this.contrato.tipoEmpreitada;
        }  else if (valor === 'Execucao') {
            await this.loadExecucao(this.programasProjectos.id);
            this.execucao.tipoEmpreitada = this.contrato.tipoEmpreitada;
        }
    }

    public hideModalConcurso() {
        this.closeModalConcurso.nativeElement.click();
    }

    public hideModalAdj() {
        this.closeModalAdj.nativeElement.click();
    }

    public hideModalExecucao() {
        this.closeModalExecucao.nativeElement.click();
    }

    public hideModalEmpreitada() {
        this.closeModalEmpreitada.nativeElement.click();
    }
    public hideModalContrato() {
        this.closeModalContrato.nativeElement.click();
    }

    public hideModalConcepcao() {
        this.closeModal.nativeElement.click();
    }

    // SOLICITADO EM 15/06/2020
    // NAO PERMITIR QUE UMA DATA SEJA MAIOR QUE A OUTRA, DE ACORDO COM A SEQUENCIA DE PREENCHIMENTO
    // Nenhuma destas datas deve ser superior a(s) data(s) anterior(es)
    // 2ª Data de aprovação dos documentos de concurso;
    validaData2() {
        if (this.isData1MenorQueData2(this.concepcao.dtAprovacaoCon, this.concepcao.dtElaboracaoCon )) {
            alert('A Data de Aprovação não pode ser menor que a data de Elaboração');
            this.concepcao.dtAprovacaoCon = null;
        }
    }
    // 3ª Data de lançamento do concurso;
    validaData3() {
        if (this.isData1MenorQueData2(this.concurso.dtLancamento, this.concepcao.dtAprovacaoCon)) {
            alert('A Data de Lançamento não pode ser menor que a data de Aprovação da concepção');
            this.concurso.dtLancamento = null;
        }
    }
    // 4ª Data de entrega das propostas;
    validaData4() {
        if (this.isData1MenorQueData2(this.concurso.dtEntregaProposta, this.concurso.dtLancamento)) {
            alert('A Data de Entrega da proposta não pode ser menor que a data de Lançamento');
            this.concurso.dtEntregaProposta = null;
        }
    }
    // 5ª Data de abertura das propostas;
    validaData5() {
        if (this.isData1MenorQueData2(this.concurso.dtAberturaProposta, this.concurso.dtEntregaProposta)) {
            alert('A Data de Abertura não pode ser menor que a data de Entrega');
            this.concurso.dtAberturaProposta = null;
        }
    }
    // 6ª Data de conclusão da avaliação e relatório preliminar;
    validaData6() {
        if (this.isData1MenorQueData2(this.concurso.dtConclusaoAvaliacaoRelPrel, this.concurso.dtAberturaProposta)) {
            alert('A Data de conclusão da avaliação e relatório preliminar não pode ser menor que a data de Abertura');
            this.concurso.dtConclusaoAvaliacaoRelPrel = null;
        }
    }
    // 7ª Data de negociação
    validaData7() {
        if (this.isData1MenorQueData2(this.concurso.dtNegociacao, this.concurso.dtConclusaoAvaliacaoRelPrel)) {
            alert('A Data de negociação não pode ser menor que a Data de conclusão da avaliação');
            this.concurso.dtNegociacao = null;
        }
    }
    // 8ª Data de aprovação do relatório de avaliação final;
    validaData8() {
        if (this.isData1MenorQueData2(this.concurso.dtAprovRelAvalFinal, this.concurso.dtNegociacao)) {
            alert('A Data de aprovação do relatório de avaliação final não pode ser menor que a data de Negociação');
            this.concurso.dtAprovRelAvalFinal = null;
        }
    }
    // 9ª Data de comunicação da adjudicação
    validaData9() {
        if (this.isData1MenorQueData2(this.adjudicacao.dtComunicaoAdjudicacao, this.concurso.dtAprovRelAvalFinal)) {
            alert('A Data de comunicação da adjudicação não pode ser menor que a Data de aprovação do relatório de avaliação final');
            this.adjudicacao.dtComunicaoAdjudicacao = null;
        }
    }

    // 10ª Data de prestação da garantia de boa execução
    validaData10() {
        if (this.isData1MenorQueData2(this.adjudicacao.dtPrestacaoGarantBoaExec, this.adjudicacao.dtComunicaoAdjudicacao)) {
            alert('A Data de prestação da garantia de boa execução não pode ser menor que a Data de comunicação da adjudicação');
            this.adjudicacao.dtPrestacaoGarantBoaExec = null;
        }
    }
    // 11ª Data de submissão da minuta de contracto
    validaData11() {
        if (this.isData1MenorQueData2(this.adjudicacao.dtSubmissaoMinutContrato, this.adjudicacao.dtPrestacaoGarantBoaExec)) {
            alert('A Data de submissão da minuta de contracto não pode ser menor que a Data de prestação da garantia de boa execução');
            this.adjudicacao.dtSubmissaoMinutContrato = null;
        }
    }

    // 12ª Data de assinatura do contracto;
    validaData12() {
        if (this.isData1MenorQueData2(this.contrato.dtAssinatura, this.adjudicacao.dtSubmissaoMinutContrato)) {
            alert('A Data de assinatura do contracto não pode ser menor que a Data de submissão da minuta de contracto');
            this.contrato.dtAssinatura = null;
        }
    }

    // 13ª Data de finalização do processo de homologação;
    validaData13() {
        if (this.isData1MenorQueData2(this.contrato.dtFinalizacaoProcessoHomologAprov, this.contrato.dtAssinatura)) {
            alert('A Data de finalização do processo de homologação não pode ser menor que a Data de assinatura');
            this.contrato.dtFinalizacaoProcessoHomologAprov = null;
        }
    }
    // 14ª Data do visto do Tribunal de Contas;
    validaData14() {
        if (this.isData1MenorQueData2(this.contrato.dtVistoTribunalContas, this.contrato.dtFinalizacaoProcessoHomologAprov)) {
            alert('A Data do visto do Tribunal de Contas não pode ser menor que a Data de finalização do processo de homologação');
            this.contrato.dtVistoTribunalContas = null;
        }
    }
    // 15ª Data de pagamento emolumentos;
    validaData15() {
        if (this.isData1MenorQueData2(this.contrato.dtPagamentoEmolumentos, this.contrato.dtVistoTribunalContas)) {
            alert('A Data de pagamento emolumentos não pode ser menor que a Data do visto do Tribunal de Contas');
            this.contrato.dtPagamentoEmolumentos = null;
        }
    }
    // 16º Data de pagamento do adiantamento;
    validaData16() {
        if (this.isData1MenorQueData2(this.contrato.dtAdiantamento, this.contrato.dtPagamentoEmolumentos)) {
            alert('A Data de pagamento do adiantamento não pode ser menor que a Data de pagamento emolumentos');
            this.contrato.dtAdiantamento = null;
        }
    }
    // 17ª Data do prazo de garantia do adiantamento;
    validaData17() {
        if (this.isData1MenorQueData2(this.contrato.dtPrazoGarantiaAditamento, this.contrato.dtAdiantamento)) {
            alert('A Data do prazo de garantia do adiantamento não pode ser menor que a Data de pagamento do adiantamento');
            this.contrato.dtPrazoGarantiaAditamento = null;
        }
    }
    // 18ª Data de início;
    validaData18() {
        if (this.isData1MenorQueData2(this.contrato.dtInicio, this.contrato.dtPrazoGarantiaAditamento)) {
            alert('A Data de Início não pode ser menor que a ata do prazo de garantia do adiantamento');
            this.contrato.dtInicio = null;
        }
    }
    // 19ª Data dos prazos vinculativos;
    validaData19() {
        if (this.isData1MenorQueData2(this.contrato.dtPrazosVinculativos, this.contrato.dtInicio)) {
            alert('A Data dos prazos vinculativos não pode ser menor que a Data de início');
            this.contrato.dtPrazosVinculativos = null;
        }
    }
    // 20ª Data de recepção provisória;
    validaData20() {
        if (this.isData1MenorQueData2(this.contrato.dtRecepcaoProvisoria, this.contrato.dtPrazosVinculativos)) {
            alert('A Data de recepção provisória não pode ser menor que a Data dos prazos vinculativos');
            this.contrato.dtRecepcaoProvisoria = null;
        }
    }

    // 21ª Data de recepção definitiva;
    validaData21() {
        if (this.isData1MenorQueData2(this.contrato.dtRecepcaoDefinitiva, this.contrato.dtRecepcaoProvisoria)) {
            alert('A Data de recepção definitiva não pode ser menor que a Data de recepção provisória');
            this.contrato.dtRecepcaoDefinitiva = null;
        }
    }

    isData1MenorQueData2(data1: any, data2: any) {
        const dt1: Date = new Date(data1.year, data1.month - 1, data1.day);
        const dt2 = new Date(data2.year, data2.month - 1, data2.day );
        if (dt1 < dt2) {
            return true;
        } else {
            return false;
        }
    }
    private subscribeToSaveResponse(result: Observable<HttpResponse<ProgramasProjectos>>) {
        result.subscribe((res: HttpResponse<ProgramasProjectos>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: ProgramasProjectos) {
        this.eventManager.broadcast({ name: 'programasProjectosListModification', content: 'OK'});
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    previousState() {
        window.history.back();
    }

}

@Component({
    selector: 'jhi-programas-projectos-popup',
    template: ''
})
export class ProgramasProjectosPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private programasProjectosPopupService: ProgramasProjectosPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.programasProjectosPopupService
                    .open(ProgramasProjectosDialogComponent as Component, params['id']);
            } else {
                this.programasProjectosPopupService
                    .open(ProgramasProjectosDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
