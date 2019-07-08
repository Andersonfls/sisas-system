import { BaseEntity } from './../../shared';

export class Situacao implements BaseEntity {
    constructor(
        public id?: number,
        public nmSituacao?: string,
    ) {
    }
}
