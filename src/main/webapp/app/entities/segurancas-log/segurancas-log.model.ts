import { BaseEntity } from './../../shared';

export class SegurancasLog implements BaseEntity {
    constructor(
        public id?: number,
        public acao?: string,
        public idUsuario?: number,
        public idUsuarioAlterado?: number,
        public log?: string,
        public dtLog?: any,
    ) {
    }
}
