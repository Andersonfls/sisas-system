import { BaseEntity } from './../../shared';

export class Concepcao implements BaseEntity {
    constructor(
        public id?: number,
        public idConcepcao?: number,
        public tipoConcurso?: string,
        public dtLancamento?: any,
        public dtUltimaAlteracao?: any,
        public dtElaboracaoCon?: any,
        public dtAprovacaoCon?: any,
        public idProgramasProjectosId?: number,
        public idSistemaAguaId?: number,
    ) {
    }
}
