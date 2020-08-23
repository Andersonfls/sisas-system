import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';

import {Observable} from 'rxjs/Observable';
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
                 this.findLastIndicador();
                this.verificaCamposObrigatorios();
            }
        });

        this.principal.identity().then((userIdentity) => {
            this.user = userIdentity;
        });

        this.situacaoService.query()
            .subscribe((res: HttpResponse<Situacao[]>) => {
                this.situacaos = res.body;
            }, (res: HttpErrorResponse) => this.onError(res.message));

        this.provinciaService.queryPorNivelUsuario().subscribe(
            (res: HttpResponse<Provincia[]>) => {
                this.provincias = res.body;
            }, (res: HttpErrorResponse) => this.onError(res.message));

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
                this.findLastIndicador();
                this.somaCampos();
            });
    }

    save() {
        this.isSaving = true;
        console.log(this.indicadorProducao);
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
        if (!this.indicadorProducao.id && this.indicadorProducao.provincia) {
            this.indicadorProducaoService.findLast(this.indicadorProducao.provincia.id).subscribe(
                (res: HttpResponse<IndicadorProducao>) => {
                    const indicadorMesAnterior = res.body;
                    if (indicadorMesAnterior) {
                        this.indicadorProducao.qtdPopulacaoCobertaInfraestrutura = indicadorMesAnterior.qtdPopulacaoCobertaInfraestrutura;
                        this.indicadorProducao.qtdFontanariosChafarisesOperacionais = indicadorMesAnterior.qtdFontanariosChafarisesOperacionais;
                        this.indicadorProducao.qtdFuncionarios = indicadorMesAnterior.qtdFuncionarios;
                        this.indicadorProducao.qtdFuncionariosEfectivos = indicadorMesAnterior.qtdFuncionariosEfectivos;
                        this.indicadorProducao.qtdFuncionariosContratados = indicadorMesAnterior.qtdFuncionariosContratados;
                        this.indicadorProducao.qtdFuncionariosOutrasEntidades = indicadorMesAnterior.qtdFuncionariosOutrasEntidades;
                        this.indicadorProducao.qtdLigacoesActivas = indicadorMesAnterior.qtdLigacoesActivas;
                        this.indicadorProducao.qtdLigacoesDomesticasActivas = indicadorMesAnterior.qtdLigacoesDomesticasActivas;
                        this.indicadorProducao.qtdCaptacoes = indicadorMesAnterior.qtdCaptacoes;
                        this.indicadorProducao.qtdEtas = indicadorMesAnterior.qtdEtas;
                        this.indicadorProducao.qtdReservatorios = indicadorMesAnterior.qtdReservatorios;
                        this.indicadorProducao.qtdEstacoesElevatorias = indicadorMesAnterior.qtdEstacoesElevatorias;
                        this.indicadorProducao.qtdComprimentoAdutoras = indicadorMesAnterior.qtdComprimentoAdutoras;
                        this.indicadorProducao.qtdComprimentoRedes = indicadorMesAnterior.qtdComprimentoRedes;
                        this.indicadorProducao.qtdManuaisMoPrevistos = indicadorMesAnterior.qtdManuaisMoPrevistos;
                        this.indicadorProducao.qtdManuaisMmsPrevistos = indicadorMesAnterior.qtdManuaisMmsPrevistos;
                        this.indicadorProducao.qtdAcoesManuaisMoRealizadas = indicadorMesAnterior.qtdAcoesManuaisMoRealizadas;
                        this.indicadorProducao.qtdManuaisMmsRealizadas = indicadorMesAnterior.qtdManuaisMmsRealizadas;
                        this.indicadorProducao.qtdManuaisCmpRealizadas = indicadorMesAnterior.qtdManuaisCmpRealizadas;

                        console.log('Indicador mes anterior', indicadorMesAnterior);
                    }
                    this.somaCampos();
                    this.verificaCamposObrigatorios();
                },
                (error1) => {
                    (this.onError(error1));
                });
        }
    }

    verificaCamposObrigatorios() {
        if (!this.indicadorProducao.qtdComprimentoRedes || this.indicadorProducao.qtdComprimentoRedes < 0 ) {
            this.indicadorProducao.qtdComprimentoRedes = 0;
        }
        if (!this.indicadorProducao.qtdManuaisMoPrevistos || this.indicadorProducao.qtdManuaisMoPrevistos < 0 ) {
            this.indicadorProducao.qtdManuaisMoPrevistos = 0;
        }

        if (!this.indicadorProducao.qtdManuaisMmsPrevistos || this.indicadorProducao.qtdManuaisMmsPrevistos < 0 ) {
            this.indicadorProducao.qtdManuaisMmsPrevistos = 0;
        }
        if (!this.indicadorProducao.qtdAcoesManuaisMoRealizadas || this.indicadorProducao.qtdAcoesManuaisMoRealizadas < 0 ) {
            this.indicadorProducao.qtdAcoesManuaisMoRealizadas = 0;
        }
        if (!this.indicadorProducao.qtdManuaisMmsRealizadas || this.indicadorProducao.qtdManuaisMmsRealizadas < 0 ) {
            this.indicadorProducao.qtdManuaisMmsRealizadas = 0;
        }
        if (!this.indicadorProducao.qtdManuaisCmpRealizadas || this.indicadorProducao.qtdManuaisCmpRealizadas < 0 ) {
            this.indicadorProducao.qtdManuaisCmpRealizadas = 0;
        }

        if (!this.indicadorProducao.qtdComprimentoRamais || this.indicadorProducao.qtdComprimentoRamais < 0 ) {
            this.indicadorProducao.qtdComprimentoRamais = 0;
        }

        if (!this.indicadorProducao.vlrCustoTotaisCapexOpex || this.indicadorProducao.vlrCustoTotaisCapexOpex < 0 ) {
            this.indicadorProducao.vlrCustoTotaisCapexOpex = 0;
        }
    }
    // AZUL = Recebe a soma dos campos(Não precisa digitar):
    // V55, V67, V72, V76, V80
    somaCampos() {
       this.somaVariavel55();
       this.somaVariavel67();
       this.somaVariavel72();
       this.somaVariavel76();
       this.somaVariavel80();
    }

    somaVariavel55() {
        this.indicadorProducao.vlrCustoTotaisCapexOpex = 0;
        this.indicadorProducao.vlrCustoTotaisCapexOpex = this.indicadorProducao.vlrCustoOperacionaisOpex + this.indicadorProducao.vlrCustoAmortizaAnualInvestOpCapex; // 55
    }

    somaVariavel67() {
        this.indicadorProducao.totalAcoesFormacaoPlanejadas = 0;
        this.indicadorProducao.totalAcoesFormacaoPlanejadas = this.indicadorProducao.qtdAcoesFormacaoMmsPlaneadas + this.indicadorProducao.qtdAcoesFormacaoMoPlaneadas
            + this.indicadorProducao.qtdAcoesFormacaoCmpPlaneadas + this.indicadorProducao.qtdAcoesFormacaoSoftwareFornecidosPlanejadas; // 67
    }

    somaVariavel72() {
        this.indicadorProducao.qtdAcoesFormacaoRealizadas =  0;
        this.indicadorProducao.qtdAcoesFormacaoRealizadas = this.indicadorProducao.qtdAcoesFormacaoMoRealizadas
            + this.indicadorProducao.qtdAcoesFormacaoMmsRealizadas + this.indicadorProducao.qtdAcoesFormacaoCmpRealizadas
            + this.indicadorProducao.qtdAcoesFormacaoSoftwareFornecidosRealizadas; // 72
    }

    somaVariavel76() {
        this.indicadorProducao.qtdManuaisPrevistos =  0;
        this.indicadorProducao.qtdManuaisPrevistos = this.indicadorProducao.qtdManuaisMoPrevistos
            + this.indicadorProducao.qtdManuaisMmsPrevistos + this.indicadorProducao.qtdManuaisCmpPrevistos; // 76
    }

    somaVariavel80() {
        this.indicadorProducao.qtdManuaisRealizados =  0;
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
