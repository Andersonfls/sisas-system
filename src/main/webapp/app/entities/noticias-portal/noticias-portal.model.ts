import { BaseEntity } from './../../shared';

export class NoticiasPortal implements BaseEntity {
    constructor(
        public id?: number,
        public titulo?: string,
        public texto?: any,
        public imagemCapaContentType?: string,
        public imagemCapa?: any,
        public dataCriacao?: any,
        public dataAlteracao?: any,
        public status?: boolean,
    ) {
        this.status = false;
    }
}
