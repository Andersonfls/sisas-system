import { BaseEntity } from './../../shared';

export class Banner implements BaseEntity {
    constructor(
        public id?: number,
        public titulo?: string,
        public descricao?: string,
        public uri?: string,
        public contentType?: string,
        public imagemBanner?: any,
        public status?: boolean
    ) {
        this.status = false;
    }
}
