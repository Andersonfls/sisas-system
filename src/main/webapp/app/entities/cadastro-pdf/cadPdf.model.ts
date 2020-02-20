import { BaseEntity } from './../../shared';

export class ArquivosPortal implements BaseEntity {
    constructor(
        public id?: number,
        public nomeArquivo?: string,
        public descricao?: string,
        public status?: boolean,
        public dataInclusao?: Date,
        public dataAlteracao?: Date,
        public uri?: string,
        public tipoArquivo?: number
    ) {
        this.status = false;
    }
}
