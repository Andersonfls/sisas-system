import { BaseEntity } from './../../shared';

export class Especialidades implements BaseEntity {
    constructor(
        public id?: number,
        public nmEspecialidade?: string,
    ) {
    }
}
