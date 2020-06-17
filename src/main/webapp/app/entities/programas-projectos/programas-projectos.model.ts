import {BaseEntity, User} from './../../shared';
import {Comuna} from '../comuna';
import {Provincia} from '../provincia';
import {Municipio} from '../municipio';
import {Especialidades} from '../especialidades';
import {Concepcao} from '../concepcao';
import {Concurso} from '../concurso';
import {Adjudicacao} from '../adjudicacao';
import {Contrato} from '../contrato';
import {Empreitada} from '../empreitada';
import {Execucao} from '../execucao';

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
        public finalidadeProjeto?: string,
        public concepcao?: Concepcao,
        public concurso?: Concurso,
        public adjudicacao?: Adjudicacao,
        public contrato?: Contrato,
        public empreitada?: Empreitada,
        public execucao?: Execucao
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

        this.concepcao = this.concepcao ? concepcao : null;
        this.concurso = this.concurso ? concurso : null;
        this.adjudicacao = this.adjudicacao ? adjudicacao : null;
        this.contrato = this.contrato ? contrato : null;
        this.empreitada = this.empreitada ? empreitada : null;
        this.execucao = this.execucao ? execucao : null;
    }
}
