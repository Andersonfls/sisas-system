import { BaseEntity } from './../../shared';

export class Authority /*implements BaseEntity*/ {
    constructor(
        public name?: string,
        public oldName?: string
    ) {}
}
