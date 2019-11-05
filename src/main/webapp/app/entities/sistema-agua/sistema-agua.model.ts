import { BaseEntity } from './../../shared';
import {Comuna} from '../comuna';
import {Situacao} from '../situacao';
import {Provincia} from '../provincia';
import {Municipio} from '../municipio';

export class SistemaAgua implements BaseEntity {
    constructor(
        public id?: number,
        public idUsuario?: number,
        public nmInqueridor?: string,
        public dtLancamento?: any,
        public dtUltimaAlteracao?: any,
        public nmLocalidade?: string,
        public qtdPopulacaoActual?: number,
        public qtdCasasLocalidade?: number,
        public nmTpComunaAldeia?: string,
        public nmTpArea?: string,
        public possuiSistemaAgua?: number,
        public nmSistemaAgua?: string,
        public nmFonteAgua?: string,
        public latitude?: string,
        public longitude?: string,
        public altitude?: string,
        public nmTpFonte?: string,
        public nmFonteAguaUtilizada?: string,
        public nmTipoBomba?: string,
        public qtdCasasAguaLigada?: number,
        public qtdChafarisesFuncionando?: number,
        public qtdContadoresLigados?: number,
        public qtdBebedouros?: number,
        public qtdHabitantesAcessoServicoAgua?: number,
        public anoConstrucaoSistema?: number,
        public nmTpAvariaSistema?: string,
        public causaAvariaSistema?: string,
        public statusResolucao?: string,
        public tempoServicoDisponivel?: string,
        public qtdDiametroCondutaAdutoraAguaBruta?: number,
        public qtdComprimentoCondutaAdutoraAguaBruta?: number,
        public qtdDiametroCondutaAdutoraAguaTratada?: number,
        public qtdComprimentoCondutaAdutoraAguaTratada?: number,
        public descMaterialUtilizadoCondutas?: string,
        public qtdReservatoriosApoiados?: number,
        public qtdCapacidadeReservatoriosApoiados?: number,
        public qtdReservatoriosElevados?: number,
        public qtdCapacidadeReservatoriosElevados?: number,
        public alturaReservatoriosElevados?: number,
        public nmTpTratamentoAgua?: string,
        public nmTpTratamentoPadraoUtilizado?: string,
        public nmTpTratamentoBasicoUtilizado?: string,
        public existeAvariaSistemaTratamento?: string,
        public existeMotivoAusenciaTratamento?: string,
        public nmEquipamentosComAvaria?: string,
        public caudalDoSistema?: number,
        public qtdConsumoPercaptaLitrosHomemDia?: number,
        public qtdDotacaoPercapta?: number,
        public qtdDiariaHorasServicoSistema?: number,
        public esquema?: string,
        public nmModeloBombaManualUtilizada?: string,
        public nmTpBombaEnergia?: string,
        public situacao?: Situacao,
        public comuna?: Comuna,
        public provincia?: Provincia,
        public municipio?: Municipio
    ) {
        this.id = id ? id : null;
        this.comuna = comuna ? comuna : null;
        this.situacao = situacao ? situacao : null;
        this.provincia = provincia ? provincia : null;
        this.municipio = municipio ? municipio : null;
        this.possuiSistemaAgua = possuiSistemaAgua ? possuiSistemaAgua : null;
        this.nmTpComunaAldeia = nmTpComunaAldeia ? nmTpComunaAldeia : null;
        this.nmTpArea = nmTpArea ? nmTpArea : null;
    }
}
