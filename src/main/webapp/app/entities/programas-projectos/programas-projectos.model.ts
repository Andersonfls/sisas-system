import {BaseEntity, User} from './../../shared';
import {Comuna} from '../comuna';

export class ProgramasProjectos implements BaseEntity {
    constructor(
        public id?: number,
        public dtLancamento?: any,
        public dtUltimaAlteracao?: any,
        public usuario?: User,
        public nmDesignacaoProjeto?: string,
        public nmDescricaoProjeto?: string,
        public idSaaAssociado?: number,
        public tipoFinanciamento?: string,
        public especialidade?: string,
        public comuna?: Comuna,
        public associadoInquerito?: string
    ) {
        this.id = id ? id : null;
        this.comuna = comuna ? comuna : null;
        this.associadoInquerito = associadoInquerito ? associadoInquerito : null;
    }
}
