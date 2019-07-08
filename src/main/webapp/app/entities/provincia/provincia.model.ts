import { BaseEntity } from './../../shared';

export class Provincia implements BaseEntity {
    constructor(
        public id?: number,
        public idProvincia?: number,
        public nmProvincia?: string,
        public municipios?: BaseEntity[],
    ) {
    }
}
