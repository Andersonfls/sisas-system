import { BaseEntity } from './../../shared';

export class FinalidadeProjeto implements BaseEntity {
    constructor(
        public id?: number,
        public nmFinalidade?: string,
    ) {
    }
}
