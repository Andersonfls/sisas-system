import { BaseEntity } from './../../shared';

export class ConfiguracoesLog implements BaseEntity {
    constructor(
        public id?: number,
        public idConfiguracoesLog?: number,
        public acao?: string,
        public idUsuario?: number,
        public log?: string,
        public dtLog?: any,
    ) {
    }
}
