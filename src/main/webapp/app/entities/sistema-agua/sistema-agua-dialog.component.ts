import {Component, OnInit, OnDestroy} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpResponse, HttpErrorResponse} from '@angular/common/http';

import {Observable} from 'rxjs/Observable';
import {JhiEventManager, JhiAlertService} from 'ng-jhipster';

import {SistemaAgua} from './sistema-agua.model';
import {SistemaAguaPopupService} from './sistema-agua-popup.service';
import {SistemaAguaService} from './sistema-agua.service';
import {Situacao, SituacaoService} from '../situacao';
import {Comuna, ComunaService} from '../comuna';
import {Provincia, ProvinciaService} from '../provincia';
import {Municipio, MunicipioService} from '../municipio';
import {AvariaSistemaAgua} from './avaria-sistema-agua.model';

@Component({
    selector: 'jhi-sistema-agua-dialog',
    templateUrl: './sistema-agua-dialog.component.html',
    styleUrls: [
        'sistemaAgua.css'
    ]
})
export class SistemaAguaDialogComponent implements OnInit {

    sistemaAgua: SistemaAgua;
    isSaving: boolean;
    situacaos: Situacao[];
    provincias: Provincia[];
    provincia: Provincia;
    municipios: Municipio[];
    municipio: Municipio;
    comunas: Comuna[];
    comuna: Comuna;
    dtLancamentoDp: any;
    public tipoComunaAldeias: Array<any> = ['Concentrada', 'Dispersa', 'Semi-Dispersa'];
    routeSub: any;
    listaAnos: number[];
    isConcluido: boolean;
    ShowFilter = false;

    dropdownSettings: any = {};
    tratamentoPadraoUtilizadoLista: Array<any> = [];
    tratamentoPadraoUtilizado: Array<any> = [];
    isTratamentoPadraoOutros: boolean;

    tratamentoBasicoUtilizadoLista: Array<any> = [];
    tratamentoBasicoUtilizado: Array<any> = [];
    isTratamentoBasicoOutros: boolean;

    motivosAusenciaTratamentoLista: Array<any> = [];
    motivosAusenciaTratamento: Array<any> = [];
    isMotivosAusenciaTratamentoOutros: boolean;

    equipamentosComAvariaLista: Array<any> = [];
    equipamentosComAvaria: Array<any> = [];
    isEquipamentosComAvariaOutros: boolean;

    materialUtilizadoLista: Array<any> = [];
    materialUtilizado: Array<any> = [];
    isMaterialUtilizadoOutros: boolean;

    constructor(
        private jhiAlertService: JhiAlertService,
        private sistemaAguaService: SistemaAguaService,
        private situacaoService: SituacaoService,
        private comunaService: ComunaService,
        private municipioService: MunicipioService,
        private provinciaService: ProvinciaService,
        private eventManager: JhiEventManager,
        private route: ActivatedRoute,
        private router: Router
    ) {
    }

    ngOnInit() {
        this.dropdownSettings = {
            singleSelection: false,
            idField: 'item_text',
            textField: 'item_text',
            selectAllText: 'Marcar Todos',
            unSelectAllText: 'Desmarcar Todos',
            itemsShowLimit: 3,
            allowSearchFilter: this.ShowFilter
        };

        this.isSaving = false;
        this.isConcluido = false;
        this.sistemaAgua = new SistemaAgua();

        this.routeSub = this.route.params.subscribe((params) => {
            if (params['id']) {
                this.load(params['id']);
            } else {
                this.sistemaAgua = new SistemaAgua();
                this.montaListaInicio();
            }
        });

        this.situacaoService.query().subscribe(
            (res: HttpResponse<Situacao[]>) => {
                this.situacaos = res.body;
            }, (res: HttpErrorResponse) => this.onError(res.message));

        this.montaListaAnos();
        this.carregarListasSelectsMultiplos();

        const now = new Date();
        this.sistemaAgua.dtLancamento = {year: now.getFullYear(), month: now.getMonth() + 1, day: now.getDate()};
    }

    carregarListasSelectsMultiplos() {
        this.tratamentoPadraoUtilizadoLista = [
            { item_text: 'Pré-tratamento' },
            { item_text: 'Coagulação, floculação, decantação, filtração' },
            { item_text: 'Desinfestação' },
            { item_text: 'Outros' }
        ];

        this.tratamentoBasicoUtilizadoLista = [
            { item_text: 'Cloro gasoso' },
            { item_text: 'Cloro líquido' },
            { item_text: 'Cloro em pó' },
            { item_text: 'Outros' }
        ];

        this.motivosAusenciaTratamentoLista = [
            { item_text: 'Falta de instalações' },
            { item_text: 'Falta de equipamento' },
            { item_text: 'Falta de reagentes Químicos' },
            { item_text: 'Falta de conhecimento' },
            { item_text: 'Outros' }
        ];

        this.equipamentosComAvariaLista = [
            { item_text: 'Equipamento de dosagem' },
            { item_text: 'Electroagitadores' },
            { item_text: 'Compressores' },
            { item_text: 'Outros' }
        ];

        this.materialUtilizadoLista = [
            { item_text: 'Fibro cimento' },
            { item_text: 'Ferro galvanizado' },
            { item_text: 'Ferro fundido' },
            { item_text: 'Ferro dúctil' },
            { item_text: 'Polietileno' },
            { item_text: 'PVC' },
            { item_text: 'Outros' }
        ];
    }
    // VERIFICO SE A OPCAO OUTROS FOI SELECIONADA, CASO SIM, ATUALIZO TODOS OS COMBOS
    onItemSelect(item: any) {
        console.log(item);
        this.verificaOpcaoOutrosSelecionada();
    }

    onSelectAll(items: any) {
        console.log('onSelectAll', items);
    }

    adicionarAvaria() {
        const av = new AvariaSistemaAgua();
        av.causaAvariaSistema = this.sistemaAgua.causaAvariaSistema;
        av.nmTpAvariaSistema = this.sistemaAgua.nmTpAvariaSistema;
        av.statusResolucao = this.sistemaAgua.statusResolucao;
        av.tempoServicoDisponivel = this.sistemaAgua.tempoServicoDisponivel;

        if (!this.sistemaAgua.avariaSistemaAguas) {
            this.sistemaAgua.avariaSistemaAguas = new Array<any>();
        }
        this.sistemaAgua.avariaSistemaAguas.push(av);
    }

    removerAvaria(index) {
        if (confirm('Confirma a Excusão da avaria?')) {
            this.sistemaAgua.avariaSistemaAguas.splice(index, 1);
        }
    }

    concluiQuestionario() {
        this.isConcluido = true;
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

    // Nº de habitantes com acesso ao serviço de água" não pode ser "maior >"  que  "População atual"
    verificaNumeroHabitantes() {
        if (this.sistemaAgua.qtdHabitantesAcessoServicoAgua > this.sistemaAgua.qtdPopulacaoActual) {
            alert('Nº de habitantes com acesso ao serviço de água não pode ser maior  que  "População atual"!');
            this.sistemaAgua.qtdHabitantesAcessoServicoAgua = null;
        }
    }

    // O número de casas ligadas não deverá  ser superior ao número de casas existentes na localidade
    verificaCasasLigadas() {
        if (this.sistemaAgua.qtdCasasAguaLigada > this.sistemaAgua.qtdCasasLocalidade) {
            alert('A quantidade de casas ligadas é maior que casas existentes!!!');
            this.sistemaAgua.qtdCasasAguaLigada = null;
        }
    }

    //  O número de contadores não deve ser superior ao número de casa ligadas
    verificaContadoresCasasLigadas() {
        if (this.sistemaAgua.qtdContadoresLigados > this.sistemaAgua.qtdCasasAguaLigada) {
            alert('A quantidade de contadores ligados é maior que o número de casas ligadas!!!');
            this.sistemaAgua.qtdContadoresLigados = null;
        }
    }

    verificaChafariz() {
        if (this.sistemaAgua.qtdChafarisesFuncionando > this.sistemaAgua.qtdChafarisesExistentes) {
            alert('A quantidade de chafarizes funcionando é maior que a existente!!!');
            this.sistemaAgua.qtdChafarisesFuncionando = null;
        }
    }

    validaExclusaoDados() {
        console.log('Valor Possui sistema agua --', this.sistemaAgua.possuiSistemaAgua);
        if (this.sistemaAgua.possuiSistemaAgua === 0) {
            if (this.sistemaAgua.nmSistemaAgua !== null && this.sistemaAgua.nmSistemaAgua !== undefined) {
                if (confirm('Os dados digitados anteriormente serão apagados, deseja continuar?')) {
                    this.inicializarCampos();
                }
            }
        }
    }

    inicializarCampos() {
        const temp = this.sistemaAgua;

        this.sistemaAgua = new SistemaAgua();
        this.sistemaAgua.id = temp.id;
        this.sistemaAgua.nmInqueridor = temp.nmInqueridor;
        this.sistemaAgua.situacao = temp.situacao;
        this.sistemaAgua.provincia = temp.provincia;
        this.sistemaAgua.municipio = temp.municipio;
        this.sistemaAgua.comuna = temp.comuna;
        this.sistemaAgua.nmLocalidade = temp.nmLocalidade;
        this.sistemaAgua.qtdPopulacaoActual = temp.qtdPopulacaoActual;
        this.sistemaAgua.qtdCasasLocalidade = temp.qtdCasasLocalidade;
        this.sistemaAgua.nmTpComunaAldeia = temp.nmTpComunaAldeia;
        this.sistemaAgua.nmTpArea = temp.nmTpArea;
        this.sistemaAgua.possuiSistemaAgua = temp.possuiSistemaAgua;
        const now = new Date();
        this.sistemaAgua.dtLancamento = {year: now.getFullYear(), month: now.getMonth() + 1, day: now.getDate()};
    }

    load(id) {
        this.sistemaAguaService.find(id)
            .subscribe((sistemaAguaResponse: HttpResponse<SistemaAgua>) => {
                const sistemaAgua: SistemaAgua = sistemaAguaResponse.body;
                if (sistemaAgua.dtLancamento) {
                    sistemaAgua.dtLancamento = {
                        year: sistemaAgua.dtLancamento.getFullYear(),
                        month: sistemaAgua.dtLancamento.getMonth() + 1,
                        day: sistemaAgua.dtLancamento.getDate()
                    };
                }
                if (sistemaAgua.dtUltimaAlteracao) {
                    sistemaAgua.dtUltimaAlteracao = {
                        year: sistemaAgua.dtUltimaAlteracao.getFullYear(),
                        month: sistemaAgua.dtUltimaAlteracao.getMonth() + 1,
                        day: sistemaAgua.dtUltimaAlteracao.getDate()
                    };
                }
                this.sistemaAgua = sistemaAgua;

                if (this.sistemaAgua.possuiSistemaAgua === 1) {
                    this.concluiQuestionario();
                }

                this.montaListaInicio();
                if (this.sistemaAgua.provincia) {
                    this.onChangeMunicipios();
                }
                if (this.sistemaAgua.municipio) {
                    this.onChangeComunas();
                }
                console.log(this.sistemaAgua);
                this.converteSelectsMultiplosStringEmArray();
                this.verificaOpcaoOutrosSelecionada();
            });
    }

    clear() {
        // this.activeModal.dismiss('cancel');
    }

    montaListaAnos() {
        this.listaAnos = new Array();
        const date = new Date();
        const ano = date.getFullYear();

        for (let i = ano; i >= 1973; i-- ) {
            this.listaAnos.push(i);
        }
    }

    save() {
        this.isSaving = true;
        console.log(this.sistemaAgua);
        this.converteSelectsMultiplosArrayEmString(); // CONVERTE ARRAYS EM STRING
        if (this.isEstadoFuncionamentoPreenchido()) {
            if (this.sistemaAgua.id !== undefined) {
                this.subscribeToSaveResponse(
                    this.sistemaAguaService.update(this.sistemaAgua));
            } else {
                this.subscribeToSaveResponse(
                    this.sistemaAguaService.create(this.sistemaAgua));
            }
        } else {
            alert('Preencha o Estado de funcionamento do Sistema.');
        }
    }

    converteSelectsMultiplosArrayEmString() {
        if (this.sistemaAgua.possuiSistemaAgua) {

            this.sistemaAgua.nmTpTratamentoPadraoUtilizado = this.converteArrayEmString(this.tratamentoPadraoUtilizado);
            this.sistemaAgua.nmTpTratamentoBasicoUtilizado = this.converteArrayEmString(this.tratamentoBasicoUtilizado);
            this.sistemaAgua.existeMotivoAusenciaTratamento = this.converteArrayEmString(this.motivosAusenciaTratamento);
            this.sistemaAgua.nmEquipamentosComAvaria = this.converteArrayEmString(this.equipamentosComAvaria);
            this.sistemaAgua.descMaterialUtilizadoCondutas = this.converteArrayEmString(this.materialUtilizado);
        }
    }

    converteArrayEmString(array: Array<any>) {
        let emString = '';
        for (let i = 0; i < array.length ; i++) {
            if (i > 0) {
                emString += ';';
            }
            emString += array[i].item_text;
        }
        return emString;
    }

    isOpcaoOutrosSelecionada(array: Array<any>) {
        for (let i = 0; i < array.length ; i++) {
            if (array[i].item_text === 'Outros') {
                return true;
            }
        }
        return false;
    }

    verificaOpcaoOutrosSelecionada() {
        this.isTratamentoPadraoOutros = this.isOpcaoOutrosSelecionada(this.tratamentoPadraoUtilizado);
        this.isTratamentoBasicoOutros = this.isOpcaoOutrosSelecionada(this.tratamentoBasicoUtilizado);
        this.isMotivosAusenciaTratamentoOutros = this.isOpcaoOutrosSelecionada(this.motivosAusenciaTratamento);
        this.isEquipamentosComAvariaOutros = this.isOpcaoOutrosSelecionada(this.equipamentosComAvaria);
        this.isMaterialUtilizadoOutros = this.isOpcaoOutrosSelecionada(this.materialUtilizado);
    }

    converteSelectsMultiplosStringEmArray() {
        // TRATAMENTO PADRAO
        this.tratamentoPadraoUtilizado = this.converteStringEmArray(this.sistemaAgua.nmTpTratamentoPadraoUtilizado);
        // TRATAMENTO BASICO
        this.tratamentoBasicoUtilizado = this.converteStringEmArray(this.sistemaAgua.nmTpTratamentoBasicoUtilizado);
        // MOTIVOS AUSENCIA TRATAMENTO
        this.motivosAusenciaTratamento = this.converteStringEmArray(this.sistemaAgua.existeMotivoAusenciaTratamento);
        // EQUIPAMENTOS COM AVARIA
        this.equipamentosComAvaria = this.converteStringEmArray(this.sistemaAgua.nmEquipamentosComAvaria);
        // MATERIAL UTILIZADO CONDUTA
        this.materialUtilizado = this.converteStringEmArray(this.sistemaAgua.descMaterialUtilizadoCondutas);
    }

    converteStringEmArray(emString: string) {
        const array: string[] = emString.split(';');
        const response: Array<any> = new Array<any>();
        for (let i = 0; i < array.length ; i++) {
            response.push({ item_text: array[i] });
        }
        return response;
    }

    isEstadoFuncionamentoPreenchido() {
        if (this.sistemaAgua.possuiSistemaAgua) {
            if (this.sistemaAgua.estadoFuncionamentoSistema === 'Está em funcionamento (Bom)' || this.sistemaAgua.estadoFuncionamentoSistema === 'Não está em funcionamento') {
                return true;
            }else {
                return false;
            }
        } else {
            return true;
        }
    }

    isNumeroChafarizesMenorNumeroFuncionam() {
        if (this.sistemaAgua.possuiSistemaAgua) {
            if (this.sistemaAgua.qtdChafarisesExistentes < this.sistemaAgua.qtdChafarisesFuncionando) {
                return true;
            }
        }
        return false;
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<SistemaAgua>>) {
        result.subscribe((res: HttpResponse<SistemaAgua>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: SistemaAgua) {
        this.eventManager.broadcast({name: 'sistemaAguaListModification', content: 'OK'});
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

    previousState() {
        this.router.navigate(['sistema-agua']);
        window.scrollTo(0, 0);
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

@Component({
    selector: 'jhi-sistema-agua-popup',
    template: ''
})
export class SistemaAguaPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private sistemaAguaPopupService: SistemaAguaPopupService
    ) {
    }

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if (params['id']) {
                this.sistemaAguaPopupService
                    .open(SistemaAguaDialogComponent as Component, params['id']);
            } else {
                this.sistemaAguaPopupService
                    .open(SistemaAguaDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
