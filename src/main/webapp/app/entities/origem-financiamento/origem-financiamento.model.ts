import { BaseEntity } from './../../shared';

export class OrigemFinanciamento implements BaseEntity {
    constructor(
        public id?: number,
        public idOrigemFinanciamento?: number,
        public descricaoOrigemFinanciamento?: string,
    ) {
    }
}
