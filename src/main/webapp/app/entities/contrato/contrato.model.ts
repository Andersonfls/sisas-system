import { BaseEntity } from './../../shared';

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
        public idProgramasProjectosId?: number,
        public idSistemaAguaId?: number,
        public empreitadas?: BaseEntity[],
        public execucaos?: BaseEntity[],
    ) {
    }
}
