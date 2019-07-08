import { BaseEntity } from './../../shared';

export class ProgramasProjectosLog implements BaseEntity {
    constructor(
        public id?: number,
        public idProgramasProjectosLog?: number,
        public acao?: string,
        public idUsuario?: number,
        public log?: string,
        public dtLog?: any,
        public idProgramasProjectosId?: number,
    ) {
    }
}
