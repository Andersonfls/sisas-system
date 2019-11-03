import { BaseEntity } from './../../shared';
import {ProgramasProjectos} from '../programas-projectos';

export class Concepcao implements BaseEntity {
    constructor(
        public id?: number,
        public idConcepcao?: number,
        public tipoConcurso?: string,
        public dtLancamento?: any,
        public dtUltimaAlteracao?: any,
        public dtElaboracaoCon?: any,
        public dtAprovacaoCon?: any,
        public programasProjectos?: ProgramasProjectos,
        public idSistemaAguaId?: number,
    ) {
        this.id = id ? id : null;
        this.programasProjectos = programasProjectos ? programasProjectos : null;
    }
}
