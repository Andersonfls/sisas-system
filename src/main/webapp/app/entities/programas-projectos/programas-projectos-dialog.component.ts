import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ProgramasProjectos } from './programas-projectos.model';
import { ProgramasProjectosPopupService } from './programas-projectos-popup.service';
import { ProgramasProjectosService } from './programas-projectos.service';
import { Comuna, ComunaService } from '../comuna';
import {Subscription} from 'rxjs';
import {Concepcao} from '../concepcao';
import {SistemaAgua} from '../sistema-agua';
import {Concurso} from '../concurso';
import {Adjudicacao} from '../adjudicacao';
import {Contrato} from '../contrato';

@Component({
    selector: 'jhi-programas-projectos-dialog',
    templateUrl: './programas-projectos-dialog.component.html'
})

export class ProgramasProjectosDialogComponent implements OnInit {

    programasProjectos: ProgramasProjectos = new ProgramasProjectos();
    isSaving: boolean;

    comunas: Comuna[];
    dtLancamentoDp: any;
    dtUltimaAlteracaoDp: any;
    controleSessoes: string;
    private subscription: Subscription;

    // Concepcao
    concepcao: Concepcao;
    programasprojectos: ProgramasProjectos[];
    sistemaaguas: SistemaAgua[];

    // Concurso
    concurso: Concurso;

    // Adjacao
    adjudicacao: Adjudicacao;

    // CONTRATO
    contrato: Contrato;

    constructor(
        private jhiAlertService: JhiAlertService,
        private programasProjectosService: ProgramasProjectosService,
        private comunaService: ComunaService,
        private eventManager: JhiEventManager,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.subscription = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                    this.load(params['id']);
            } else {
                this.programasProjectos = new ProgramasProjectos();
            }
        });

        this.concepcao = new Concepcao();
        this.concurso = new Concurso();
        this.adjudicacao = new Adjudicacao();
        this.contrato = new Contrato();
        this.comunaService.query()
            .subscribe((res: HttpResponse<Comuna[]>) => { this.comunas = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));

    }

    load(id) {
        this.programasProjectosService.find(id)
            .subscribe((programasProjectosResponse: HttpResponse<ProgramasProjectos>) => {
                const programasProjectos: ProgramasProjectos = programasProjectosResponse.body;
                if (programasProjectos.dtLancamento) {
                    programasProjectos.dtLancamento = {
                        year: programasProjectos.dtLancamento.getFullYear(),
                        month: programasProjectos.dtLancamento.getMonth() + 1,
                        day: programasProjectos.dtLancamento.getDate()
                    };
                }
                if (programasProjectos.dtUltimaAlteracao) {
                    programasProjectos.dtUltimaAlteracao = {
                        year: programasProjectos.dtUltimaAlteracao.getFullYear(),
                        month: programasProjectos.dtUltimaAlteracao.getMonth() + 1,
                        day: programasProjectos.dtUltimaAlteracao.getDate()
                    };
                }
                this.programasProjectos = programasProjectos;
            });
    }

    clear() {
    }

    save() {
        this.isSaving = true;
        if (this.programasProjectos.id !== undefined) {
            this.subscribeToSaveResponse(
                this.programasProjectosService.update(this.programasProjectos));
        } else {
            this.subscribeToSaveResponse(
                this.programasProjectosService.create(this.programasProjectos));
        }
    }

    habilitarTela(valor) {
        this.controleSessoes = valor;
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ProgramasProjectos>>) {
        result.subscribe((res: HttpResponse<ProgramasProjectos>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: ProgramasProjectos) {
        this.eventManager.broadcast({ name: 'programasProjectosListModification', content: 'OK'});
        this.isSaving = false;
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackComunaById(index: number, item: Comuna) {
        return item.id;
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
