import { BaseEntity } from './../../shared';

export class ItemAssinatura implements BaseEntity {
    constructor(
        public id?: number,
        public nome?: string,
        public descricao?: string,
    ) {
    }
}
