import { BaseEntity } from './../../shared';

export class MunicipiosAtendidos implements BaseEntity {
    constructor(
        public id?: number,
        public idMunicipioAtendido?: number,
        public idMunicipioId?: number,
        public entidadeGestoras?: BaseEntity[],
    ) {
    }
}
