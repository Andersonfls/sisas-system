import { BaseEntity } from './../../shared';
import {ProgramasProjectos} from '../programas-projectos';

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
        public programasProjectos?: ProgramasProjectos,
        public idSistemaAguaId?: number,
    ) {
        this.id = id ? id : null;
        this.programasProjectos = programasProjectos ? programasProjectos : null;
    }
}
