import { BaseEntity } from './../../shared';

export class EntidadeGestora implements BaseEntity {
    constructor(
        public id?: number,
        public idEntidadeGestora?: number,
        public nmEntidadeGestora?: string,
        public tpFormaJuridica?: number,
        public dtConstituicao?: any,
        public endereco?: string,
        public email?: string,
        public contactos?: string,
        public tpModeloGestao?: number,
        public numRecursosHumanos?: number,
        public numPopulacaoAreaAtendimento?: number,
        public idMunicipioAtendidoId?: number,
    ) {
    }
}
