import { BaseEntity } from './../../shared';
import {ProgramasProjectos} from '../programas-projectos';

export class Contrato implements BaseEntity {
    constructor(
        public id?: number,
        public idContrato?: number,
        public tipoEmpreitada?: string,
        public dtLancamento?: any,
        public nmEmpresaAdjudicitaria?: string,
        public valorContrato?: number,
        public dtAssinatura?: any,
        public dtFinalizacaoProcessoHomologAprov?: any,
        public tipoMoeda?: string,
        public valorAdiantamento?: number,
        public dtAdiantamento?: any,
        public dtInicio?: any,
        public prazoExecucao?: number,
        public dtRecepcaoProvisoria?: any,
        public dtRecepcaoDefinitiva?: any,
        public dtRecepcaoComicionamento?: any,
        public nmResposavelAntProjeto?: string,
        public nmResposavelProjeto?: string,
        public programasProjectos?: ProgramasProjectos,
        public idSistemaAguaId?: number,
        public empreitadas?: BaseEntity[],
        public execucaos?: BaseEntity[],
        public tipoConcurso?: string,
        public dtVistoTribunalContas?: any,
        public dtPagamentoEmolumentos?: any,
        public dtPrazoGarantiaAditamento?: any,
        public dtPrazosVinculativos?: any,
        public prazoGarantiaAdiantamento?: number,
    ) {
        this.id = id ? id : null;
        this.programasProjectos = programasProjectos ? programasProjectos : null;
        this.tipoEmpreitada = tipoEmpreitada ? tipoEmpreitada : null;
    }
}
