import {BaseEntity, User} from './../../shared';
import {Comuna} from '../comuna';
import {Provincia} from '../provincia';
import {Municipio} from '../municipio';
import {Especialidades} from '../especialidades';

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
        public especialidade?: Especialidades,
        public comuna?: Comuna,
        public provincia?: Provincia,
        public municipio?: Municipio,
        public nmLocalidade?: string,
        public associadoInquerito?: string,
        public finalidadeProjeto?: string
    ) {
        this.id = id ? id : null;
        this.comuna = comuna ? comuna : null;
        this.associadoInquerito = associadoInquerito ? associadoInquerito : null;
        this.comuna = comuna ? comuna : null;
        this.provincia = provincia ? provincia : null;
        this.municipio = municipio ? municipio : null;
        this.nmLocalidade = nmLocalidade ? nmLocalidade : null;
        this.idSaaAssociado = idSaaAssociado ? idSaaAssociado : null;
        this.finalidadeProjeto = this.finalidadeProjeto ? finalidadeProjeto : null;
        this.especialidade = this.especialidade ? especialidade : null;
    }
}
