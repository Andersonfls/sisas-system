import { BaseEntity } from './../../shared';

export class SobreDna implements BaseEntity {
    constructor(
        public id?: number,
        public idSobreDna?: number,
        public tituloSobreDna?: string,
        public textoSobreDna?: string,
        public resumoTextoSobreDna?: string,
        public idSituacaoId?: number,
        public inicios?: BaseEntity[],
    ) {
    }
}
