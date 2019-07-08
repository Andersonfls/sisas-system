import { BaseEntity } from './../../shared';

export class RelatoriosLog implements BaseEntity {
    constructor(
        public id?: number,
        public idRelatoriosLog?: number,
        public acao?: string,
        public idUsuario?: number,
        public log?: string,
        public dtLog?: any,
    ) {
    }
}
