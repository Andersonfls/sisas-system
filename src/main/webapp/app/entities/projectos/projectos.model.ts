import { BaseEntity } from './../../shared';

export class Projectos implements BaseEntity {
    constructor(
        public id?: number,
        public idProjectos?: number,
        public nmProjectos?: string,
        public textoProjectos?: string,
        public resumoTextoProjectos?: string,
        public idSituacaoId?: number,
        public inicios?: BaseEntity[],
    ) {
    }
}
