import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';

import {Observable} from 'rxjs/Observable';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {JhiAlertService, JhiEventManager} from 'ng-jhipster';

import {IndicadorProducao} from './indicador-producao.model';
import {IndicadorProducaoPopupService} from './indicador-producao-popup.service';
import {IndicadorProducaoService} from './indicador-producao.service';
import {Situacao, SituacaoService} from '../situacao';
import { UserService } from '../../shared/user/user.service';
import { User } from '../../shared/user/user.model';
import { Principal } from '../../shared/auth/principal.service';

import {Provincia, ProvinciaService} from '../provincia';

@Component({
    selector: 'jhi-indicador-producao-dialog',
    templateUrl: './indicador-producao-dialog.component.html'
})
export class IndicadorProducaoDialogComponent implements OnInit {

    indicadorProducao: IndicadorProducao;
    isSaving: boolean;
    situacaos: Situacao[];
    provincias: Provincia[];
    dtLancamentoDp: any;
    routeSub: any;
    user: User;

    constructor(
        private jhiAlertService: JhiAlertService,
        private indicadorProducaoService: IndicadorProducaoService,
        private situacaoService: SituacaoService,
        private provinciaService: ProvinciaService,
        private eventManager: JhiEventManager,
        private route: ActivatedRoute,
        private userService: UserService,
        private principal: Principal
    ) {
    }

    ngOnInit() {
        this.isSaving = false;

        this.routeSub = this.route.params.subscribe((params) => {
            if (params['id']) {
                this.load(params['id']);
            } else {
                this.indicadorProducao = new IndicadorProducao();
            }
        });

        this.principal.identity().then((userIdentity) => {
            this.user = userIdentity;
        });

        this.situacaoService.query()
            .subscribe((res: HttpResponse<Situacao[]>) => {
                this.situacaos = res.body;
            }, (res: HttpErrorResponse) => this.onError(res.message));

         this.findLastIndicador();

    }

    load(id) {
        this.indicadorProducaoService.find(id)
            .subscribe((indicadorResponse: HttpResponse<IndicadorProducao>) => {
                const indicadorProducao: IndicadorProducao = indicadorResponse.body;
                if (indicadorProducao.dtLancamento) {
                    indicadorProducao.dtLancamento = {
                        year: indicadorProducao.dtLancamento.getFullYear(),
                        month: indicadorProducao.dtLancamento.getMonth() + 1,
                        day: indicadorProducao.dtLancamento.getDate()
                    };
                }
                if (indicadorProducao.dtUltimaAlteracao) {
                    indicadorProducao.dtUltimaAlteracao = {
                        year: indicadorProducao.dtUltimaAlteracao.getFullYear(),
                        month: indicadorProducao.dtUltimaAlteracao.getMonth() + 1,
                        day: indicadorProducao.dtUltimaAlteracao.getDate()
                    };
                }
                this.indicadorProducao = indicadorProducao;
                console.log(this.indicadorProducao);
            });
    }

    save() {
        this.isSaving = true;
        if (this.indicadorProducao.id !== undefined) {
            this.subscribeToSaveResponse(
                this.indicadorProducaoService.update(this.indicadorProducao));
        } else {
            this.indicadorProducao.situacao = new Situacao();
            this.indicadorProducao.situacao.id = 1;
            this.subscribeToSaveResponse(
                this.indicadorProducaoService.create(this.indicadorProducao));
        }

    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IndicadorProducao>>) {
        result.subscribe((res: HttpResponse<IndicadorProducao>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: IndicadorProducao) {
        this.eventManager.broadcast({name: 'indicadorProducaoListModification', content: 'OK'});
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackSituacaoById(index: number, item: Situacao) {
        return item.id;
    }

    // VERDE = Recebe o resultado do último mês(Mês Anterior):
    // V1, V2, V22 AO V25,V32,V33, V56 AO V62, V73, V74, V77 AO 79
    findLastIndicador() {
        this.indicadorProducaoService.findLast().subscribe(
            (res: HttpResponse<IndicadorProducao>) => {
                const indicadorMesAnterior = res.body;
                if (indicadorMesAnterior) {
                    this.indicadorProducao.qtdCaptacoes = indicadorMesAnterior.qtdCaptacoes ? indicadorMesAnterior.qtdCaptacoes : 0;  // 56
                    this.indicadorProducao.qtdEtas = indicadorMesAnterior.qtdEtas ? indicadorMesAnterior.qtdEtas : 0; // 57
                    this.indicadorProducao.qtdReservatorios = indicadorMesAnterior.qtdReservatorios ? indicadorMesAnterior.qtdReservatorios : 0; // 58
                    this.indicadorProducao.qtdEstacoesElevatorias = indicadorMesAnterior.qtdEstacoesElevatorias ? indicadorMesAnterior.qtdEstacoesElevatorias : 0; // 59
                    this.indicadorProducao.qtdComprimentoAdutoras = indicadorMesAnterior.qtdComprimentoAdutoras ? indicadorMesAnterior.qtdComprimentoAdutoras : 0; // 60
                    this.indicadorProducao.qtdComprimentoRedes = indicadorMesAnterior.qtdComprimentoRedes ? indicadorMesAnterior.qtdComprimentoRedes : 0; // 61
                    this.indicadorProducao.qtdComprimentoRamais = indicadorMesAnterior.qtdComprimentoRamais ? indicadorMesAnterior.qtdComprimentoRamais : 0; // 62
                    this.indicadorProducao.qtdManuaisMoPrevistos = indicadorMesAnterior.qtdManuaisMoPrevistos ? indicadorMesAnterior.qtdManuaisMoPrevistos : 0; // 73
                    this.indicadorProducao.qtdManuaisMmsPrevistos = indicadorMesAnterior.qtdManuaisMmsPrevistos ? indicadorMesAnterior.qtdManuaisMmsPrevistos : 0;  // 74
                    this.indicadorProducao.qtdAcoesManuaisMoRealizadas = indicadorMesAnterior.qtdAcoesManuaisMoRealizadas ? indicadorMesAnterior.qtdAcoesManuaisMoRealizadas : 0; // 77
                    this.indicadorProducao.qtdManuaisMmsRealizadas = indicadorMesAnterior.qtdManuaisMmsRealizadas ? indicadorMesAnterior.qtdManuaisMmsRealizadas : 0;  // 78
                    this.indicadorProducao.qtdManuaisCmpRealizadas = indicadorMesAnterior.qtdManuaisCmpRealizadas ? indicadorMesAnterior.qtdManuaisCmpRealizadas : 0; // 79
                }
                this.somaCampos();
            },
            (error1) => {
                (this.onError(error1));
            });
    }

    // AZUL = Recebe a soma dos campos(Não precisa digitar):
    // V55, V67, V72, V76, V80
    somaCampos() {
        this.indicadorProducao.vlrCustoTotaisCapexOpex = this.indicadorProducao.vlrCustoOperacionaisOpex + this.indicadorProducao.vlrCustoAmortizaAnualInvestOpCapex; // 55
       // this.indicadorProducao.// 67
        this.indicadorProducao.qtdAcoesFormacaoRealizadas = this.indicadorProducao.qtdAcoesFormacaoMoRealizadas
            + this.indicadorProducao.qtdAcoesFormacaoMmsRealizadas + this.indicadorProducao.qtdAcoesFormacaoCmpRealizadas
            + this.indicadorProducao.qtdAcoesFormacaoSoftwareFornecidosRealizadas; // 72
        this.indicadorProducao.qtdManuaisPrevistos = this.indicadorProducao.qtdManuaisMoPrevistos
            + this.indicadorProducao.qtdManuaisMmsPrevistos + this.indicadorProducao.qtdManuaisCmpPrevistos; // 76
        this.indicadorProducao.qtdManuaisRealizados = this.indicadorProducao.qtdAcoesManuaisMoRealizadas
            + this.indicadorProducao.qtdManuaisMmsRealizadas + this.indicadorProducao.qtdManuaisCmpRealizadas; // 80
    }

    previousState() {
        window.history.back();
    }
}

@Component({
    selector: 'jhi-indicador-producao-popup',
    template: ''
})
export class IndicadorProducaoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private indicadorProducaoPopupService: IndicadorProducaoPopupService
    ) {
    }

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if (params['id']) {
                this.indicadorProducaoPopupService
                    .open(IndicadorProducaoDialogComponent as Component, params['id']);
            } else {
                this.indicadorProducaoPopupService
                    .open(IndicadorProducaoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
