import { BaseEntity } from './../../shared';

export class Noticias implements BaseEntity {
    constructor(
        public id?: number,
        public idNoticias?: number,
        public tituloNoticias?: string,
        public textoNoticias?: string,
        public resumoTextoNoticias?: string,
        public idSituacaoId?: number,
        public inicios?: BaseEntity[],
    ) {
    }
}
