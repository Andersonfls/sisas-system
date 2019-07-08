import { BaseEntity } from './../../shared';

export class Inicio implements BaseEntity {
    constructor(
        public id?: number,
        public idInicio?: number,
        public destaques?: number,
        public ultimasNoticias?: number,
        public publicacoes?: number,
        public url?: string,
        public alt?: string,
        public idSituacaoId?: number,
        public idSobreDnaId?: number,
        public idNoticiasId?: number,
        public idProjectosId?: number,
        public idPublicacaoId?: number,
        public idContactosId?: number,
    ) {
    }
}
