import { BaseEntity } from './../../shared';

export class Concurso implements BaseEntity {
    constructor(
        public id?: number,
        public idConcurso?: number,
        public tipoConcurso?: string,
        public dtLancamento?: any,
        public dtUltimaAlteracao?: any,
        public dtEntregaProposta?: any,
        public dtAberturaProposta?: any,
        public dtConclusaoAvaliacaoRelPrel?: any,
        public dtNegociacao?: any,
        public dtAprovRelAvalFinal?: any,
        public idProgramasProjectosId?: number,
        public idSistemaAguaId?: number,
    ) {
    }
}
