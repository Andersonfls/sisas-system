import { BaseEntity } from './../../shared';

export class Fases implements BaseEntity {
    constructor(
        public id?: number,
        public idFase?: number,
        public descricaoFase?: string,
    ) {
    }
}
