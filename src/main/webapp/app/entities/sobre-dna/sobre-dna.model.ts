import { BaseEntity } from './../../shared';

export class SobreDna implements BaseEntity {
    constructor(
        public id?: number,
        public idSobreDna?: number,
        public tituloSobreDna?: string,
        public textoSobreDna?: string,
        public resumoTextoSobreDna?: string,
        public tipoPagina?: number,
        public inicios?: BaseEntity[],
        public email?: string,
        public telemovel?: string,
        public endereco?: string,
        public descricao?: string,
        public primeiroParagrafo?: string,
        public segundoParagrafo?: string,
        public terceiroParagrafo?: string,
        public quartoParagrafo?: string,
    ) {
    }
}
