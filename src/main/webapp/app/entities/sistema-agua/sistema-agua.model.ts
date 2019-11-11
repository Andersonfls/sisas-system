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
        public municipio?: Municipio,
        public descMaterialUtilizadoCondutasObs?: string,
        public nmTpTratamentoPadraoUtilizadoObs?: string,
        public estadoFuncionamentoSistema?: string,
        public nmTpTratamentoBasicoUtilizadoObs?: string,
        public existeMotivoAusenciaTratamentoObs?: string,
        public nmEquipamentosComAvariaObs?: string,
        public nmModeloBombaManualUtilizadaObs?: string,
        public qtdChafarisesExistentes?: number

    ) {
        this.id = id ? id : null;
        this.comuna = comuna ? comuna : null;
        this.situacao = situacao ? situacao : null;
        this.provincia = provincia ? provincia : null;
        this.municipio = municipio ? municipio : null;
        this.possuiSistemaAgua = possuiSistemaAgua ? possuiSistemaAgua : null;
        this.nmTpComunaAldeia = nmTpComunaAldeia ? nmTpComunaAldeia : null;
        this.nmTpArea = nmTpArea ? nmTpArea : null;
        this.dtLancamento = dtLancamento ? dtLancamento : null;
        this.idUsuario = idUsuario ? idUsuario : null;
        this.nmInqueridor = nmInqueridor ? nmInqueridor : null;
        this.dtLancamento = dtLancamento ? dtLancamento : null;
        this.dtUltimaAlteracao = dtUltimaAlteracao ? dtUltimaAlteracao : null;
        this.nmLocalidade = nmLocalidade ? nmLocalidade : null;
        this.qtdPopulacaoActual = qtdPopulacaoActual ? qtdPopulacaoActual : null;
        this.qtdCasasLocalidade = qtdCasasLocalidade ? qtdCasasLocalidade : null;
        this.nmTpComunaAldeia = nmTpComunaAldeia ? nmTpComunaAldeia : null;
        this.nmTpArea = nmTpArea ? nmTpArea : null;
        this.possuiSistemaAgua = possuiSistemaAgua ? possuiSistemaAgua : null;
        this.nmSistemaAgua = nmSistemaAgua ? nmSistemaAgua : null;
        this.nmFonteAgua = nmFonteAgua ? nmFonteAgua : null;
        this.latitude = latitude ? latitude : null;
        this.longitude = longitude ? longitude : null;
        this.altitude = altitude ? altitude : null;
        this.nmTpFonte = nmTpFonte ? nmTpFonte : null;
        this.nmFonteAguaUtilizada = nmFonteAguaUtilizada ? nmFonteAguaUtilizada : null;
        this.nmTipoBomba = nmTipoBomba ? nmTipoBomba : null;
        this.qtdCasasAguaLigada = qtdCasasAguaLigada ? qtdCasasAguaLigada : null;
        this.qtdChafarisesFuncionando = qtdChafarisesFuncionando ? qtdChafarisesFuncionando : null;
        this.qtdContadoresLigados = qtdContadoresLigados ? qtdContadoresLigados : null;
        this.qtdBebedouros = qtdBebedouros ? qtdBebedouros : null;
        this.qtdHabitantesAcessoServicoAgua = qtdHabitantesAcessoServicoAgua ? qtdHabitantesAcessoServicoAgua : null;
        this.anoConstrucaoSistema = anoConstrucaoSistema ? anoConstrucaoSistema : null;
        this.nmTpAvariaSistema = nmTpAvariaSistema ? nmTpAvariaSistema : null;
        this.causaAvariaSistema = causaAvariaSistema ? causaAvariaSistema : null;
        this.statusResolucao = statusResolucao ? statusResolucao : null;
        this.tempoServicoDisponivel = tempoServicoDisponivel ? tempoServicoDisponivel : null;
        this.qtdDiametroCondutaAdutoraAguaBruta = qtdDiametroCondutaAdutoraAguaBruta ? qtdDiametroCondutaAdutoraAguaBruta : null;
        this.qtdComprimentoCondutaAdutoraAguaBruta = qtdComprimentoCondutaAdutoraAguaBruta ? qtdComprimentoCondutaAdutoraAguaBruta : null;
        this.qtdDiametroCondutaAdutoraAguaTratada = qtdDiametroCondutaAdutoraAguaTratada ? qtdDiametroCondutaAdutoraAguaTratada : null;
        this.qtdComprimentoCondutaAdutoraAguaTratada = qtdComprimentoCondutaAdutoraAguaTratada ? qtdComprimentoCondutaAdutoraAguaTratada : null;
        this.descMaterialUtilizadoCondutas = descMaterialUtilizadoCondutas ? descMaterialUtilizadoCondutas : null;
        this.qtdReservatoriosApoiados = qtdReservatoriosApoiados ? qtdReservatoriosApoiados : null;
        this.qtdCapacidadeReservatoriosApoiados = qtdCapacidadeReservatoriosApoiados ? qtdCapacidadeReservatoriosApoiados : null;
        this.qtdReservatoriosElevados = qtdReservatoriosElevados ? qtdReservatoriosElevados : null;
        this.qtdCapacidadeReservatoriosElevados = qtdCapacidadeReservatoriosElevados ? qtdCapacidadeReservatoriosElevados : null;
        this.alturaReservatoriosElevados = alturaReservatoriosElevados ? alturaReservatoriosElevados : null;
        this.nmTpTratamentoAgua = nmTpTratamentoAgua ? nmTpTratamentoAgua : null;
        this.nmTpTratamentoPadraoUtilizado = nmTpTratamentoPadraoUtilizado ? nmTpTratamentoPadraoUtilizado : null;
        this.nmTpTratamentoBasicoUtilizado = nmTpTratamentoBasicoUtilizado ? nmTpTratamentoBasicoUtilizado : null;
        this.existeAvariaSistemaTratamento = existeAvariaSistemaTratamento ? existeAvariaSistemaTratamento : null;
        this.existeMotivoAusenciaTratamento = existeMotivoAusenciaTratamento ? existeMotivoAusenciaTratamento : null;
        this.nmEquipamentosComAvaria = nmEquipamentosComAvaria ? nmEquipamentosComAvaria : null;
        this.caudalDoSistema = caudalDoSistema ? caudalDoSistema : null;
        this.qtdConsumoPercaptaLitrosHomemDia = qtdConsumoPercaptaLitrosHomemDia ? qtdConsumoPercaptaLitrosHomemDia : null;
        this.qtdDotacaoPercapta = qtdDotacaoPercapta ? qtdDotacaoPercapta : null;
        this.qtdDiariaHorasServicoSistema = qtdDiariaHorasServicoSistema ? qtdDiariaHorasServicoSistema : null;
        this.esquema = esquema ? esquema : null;
        this.nmModeloBombaManualUtilizada = nmModeloBombaManualUtilizada ? nmModeloBombaManualUtilizada : null;
        this.nmTpBombaEnergia = nmTpBombaEnergia ? nmTpBombaEnergia : null;
    }
}
