import { BaseEntity } from './../../shared';

export class Publicacao implements BaseEntity {
    constructor(
        public id?: number,
        public idPublicacao?: number,
        public tituloPublicacao?: string,
        public textoPublicacao?: string,
        public resumoTextoPublicacao?: string,
        public idSituacaoId?: number,
        public inicios?: BaseEntity[],
    ) {
    }
}
