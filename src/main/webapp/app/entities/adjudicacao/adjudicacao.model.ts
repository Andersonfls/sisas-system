import { BaseEntity } from './../../shared';

export class Adjudicacao implements BaseEntity {
    constructor(
        public id?: number,
        public idAdjudicacao?: number,
        public tipoConcurso?: string,
        public dtLancamento?: any,
        public dtComunicaoAdjudicacao?: any,
        public dtPrestacaoGarantBoaExec?: any,
        public dtSubmissaoMinutContrato?: any,
        public idProgramasProjectosId?: number,
        public idSistemaAguaId?: number,
    ) {
    }
}
