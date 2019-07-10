import {BaseEntity, User} from './../../shared';
import {ProgramasProjectos} from '../programas-projectos';

export class ProgramasProjectosLog implements BaseEntity {
    constructor(
        public id?: number,
        public acao?: string,
        public usuario?: User,
        public log?: string,
        public dtLog?: any,
        public programasProjectos?: ProgramasProjectos,
    ) {
    }
}
