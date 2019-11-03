import { BaseEntity } from './../../shared';
import {ProgramasProjectos} from '../programas-projectos';

export class Adjudicacao implements BaseEntity {
    constructor(
        public id?: number,
        public idAdjudicacao?: number,
        public tipoConcurso?: string,
        public dtLancamento?: any,
        public dtComunicaoAdjudicacao?: any,
        public dtPrestacaoGarantBoaExec?: any,
        public dtSubmissaoMinutContrato?: any,
        public programasProjectos?: ProgramasProjectos,
        public idSistemaAguaId?: number,
    ) {
        this.id = id ? id : null;
        this.programasProjectos = programasProjectos ? programasProjectos : null;
    }
}
