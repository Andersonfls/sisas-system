import { BaseEntity } from './../../shared';

export class Banner implements BaseEntity {
    constructor(
        public id?: number,
        public nomeArquivo?: string,
        public descricao?: string,
        public status?: boolean,
        public dataInclusao?: Date,
        public dataAlteracao?: Date
    ) {
        this.status = false;
    }
}
