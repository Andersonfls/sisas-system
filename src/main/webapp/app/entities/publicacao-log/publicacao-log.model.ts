import { BaseEntity } from './../../shared';

export class PublicacaoLog implements BaseEntity {
    constructor(
        public id?: number,
        public idPublicacaoLog?: number,
        public acao?: string,
        public idUsuario?: number,
        public log?: string,
        public dtLog?: any,
    ) {
    }
}
