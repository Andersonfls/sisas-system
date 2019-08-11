import { BaseEntity } from './../../shared';

export class Fornecedor implements BaseEntity {
    constructor(
        public id?: number,
        public nmFornecedor?: string,
        public numContribuinte?: string,
        public endereco?: string,
        public email?: string,
        public especialidade?: string,
        public contato?: string,
    ) {
    }
}
