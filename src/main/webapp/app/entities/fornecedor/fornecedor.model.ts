import { BaseEntity } from './../../shared';
import {Especialidades} from '../especialidades/especialidades.model';

export class Fornecedor implements BaseEntity {
    constructor(
        public id?: number,
        public nmFornecedor?: string,
        public numContribuinte?: string,
        public endereco?: string,
        public email?: string,
        public especialidades?: Especialidades,
        public contato?: string,
    ) {
    }
}
