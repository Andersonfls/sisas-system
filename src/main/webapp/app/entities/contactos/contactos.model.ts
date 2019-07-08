import { BaseEntity } from './../../shared';

export class Contactos implements BaseEntity {
    constructor(
        public id?: number,
        public idContactos?: number,
        public nmContactos?: string,
        public textoContactos?: string,
        public resumoTextoContactos?: string,
        public dtLancamento?: any,
        public dtUltimaAlteracao?: any,
        public idSituacaoId?: number,
        public inicios?: BaseEntity[],
    ) {
    }
}
