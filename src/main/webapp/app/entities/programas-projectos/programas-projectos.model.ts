import { BaseEntity } from './../../shared';

export class ProgramasProjectos implements BaseEntity {
    constructor(
        public id?: number,
        public idProgramasProjectos?: number,
        public dtLancamento?: any,
        public dtUltimaAlteracao?: any,
        public idUsuario?: number,
        public nmDesignacaoProjeto?: string,
        public nmDescricaoProjeto?: string,
        public idSaaAssociado?: number,
        public tipoFinanciamento?: string,
        public especialidade?: string,
        public idComunaId?: number,
        public adjudicacaos?: BaseEntity[],
        public concepcaos?: BaseEntity[],
        public concursos?: BaseEntity[],
        public contratoes?: BaseEntity[],
        public empreitadas?: BaseEntity[],
        public execucaos?: BaseEntity[],
        public programasProjectosLogs?: BaseEntity[],
    ) {
    }
}
