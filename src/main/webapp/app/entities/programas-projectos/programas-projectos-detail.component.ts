import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { ProgramasProjectos } from './programas-projectos.model';
import { ProgramasProjectosService } from './programas-projectos.service';
import {Concepcao, ConcepcaoService} from '../concepcao';
import {Concurso, ConcursoService} from '../concurso';
import {Adjudicacao, AdjudicacaoService} from '../adjudicacao';
import {Contrato, ContratoService} from '../contrato';
import {SistemaAguaService} from '../sistema-agua';
import {Empreitada, EmpreitadaService} from '../empreitada';
import {Execucao, ExecucaoService} from '../execucao';

@Component({
    selector: 'jhi-programas-projectos-detail',
    templateUrl: './programas-projectos-detail.component.html'
})
export class ProgramasProjectosDetailComponent implements OnInit, OnDestroy {

    programasProjectos: ProgramasProjectos;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    concepcao: Concepcao;
    concurso: Concurso;
    adjudicacao: Adjudicacao;
    contrato: Contrato;
    empreitada: Empreitada;
    execucao: Execucao;

    constructor(
        private eventManager: JhiEventManager,
        private programasProjectosService: ProgramasProjectosService,
        private route: ActivatedRoute,
        private concepcaoService: ConcepcaoService,
        private concursoService: ConcursoService,
        private adjService: AdjudicacaoService,
        private contratoService: ContratoService,
        private sistemaAguaService: SistemaAguaService,
        private empreitadaService: EmpreitadaService,
        private execucaoService: ExecucaoService,
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.concepcao = new Concepcao();
        this.concurso = new Concurso();
        this.adjudicacao = new Adjudicacao();
        this.contrato = new Contrato();
        this.empreitada = new Empreitada();
        this.execucao = new Execucao();
        this.registerChangeInProgramasProjectos();
    }

    load(id) {
        this.programasProjectosService.find(id)
            .subscribe((programasProjectosResponse: HttpResponse<ProgramasProjectos>) => {
                this.programasProjectos = programasProjectosResponse.body;
                this.loadConcepcao(this.programasProjectos.id);
                this.loadConcurso(this.programasProjectos.id);
                this.loadAdjudicacao(this.programasProjectos.id);
                this.loadContrato(this.programasProjectos.id);
                this.loadEmpreitada(this.programasProjectos.id);
                this.loadExecucao(this.programasProjectos.id);
            });
    }

    async loadConcepcao(id) {
        this.concepcaoService.findByProgramasProjectos(id)
            .subscribe((concepcaoResponse: HttpResponse<Concepcao>) => {
                const concepcao: Concepcao = concepcaoResponse.body;
                this.concepcao = concepcao;
            });
    }

    async loadConcurso(id) {
        this.concursoService.findByProgramasProjectos(id)
            .subscribe((concursoResponse: HttpResponse<Concurso>) => {
                const concurso: Concurso = concursoResponse.body;
                this.concurso = concurso;
            });
    }

    async loadAdjudicacao(id) {
        this.adjService.findByProgramasProjectos(id)
            .subscribe((adjResponse: HttpResponse<Adjudicacao>) => {
                const adjudicacao: Adjudicacao = adjResponse.body;
                this.adjudicacao = adjudicacao;
            });
    }

    async loadContrato(id) {
        this.contratoService.findByProgramasProjectos(id)
            .subscribe((contratoResponse: HttpResponse<Contrato>) => {
                console.log(contratoResponse);
                const contrato: Contrato = contratoResponse.body;
                this.contrato = contrato;
            });
    }

    async loadExecucao(id) {
        this.execucaoService.findByProgramasProjectos(id)
            .subscribe((execucaoResponse: HttpResponse<Execucao>) => {
                const execucao: Execucao = execucaoResponse.body;
                this.execucao = execucao;
            });
    }

    async loadEmpreitada(id) {
        this.empreitadaService.findByProgramasProjectos(id)
            .subscribe((empreitadaResponse: HttpResponse<Empreitada>) => {
                const empreitada: Empreitada = empreitadaResponse.body;
                this.empreitada = empreitada;
            });
    }

    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInProgramasProjectos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'programasProjectosListModification',
            (response) => this.load(this.programasProjectos.id)
        );
    }
}
