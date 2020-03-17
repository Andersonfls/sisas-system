import { BaseEntity } from './../../shared';
import {Municipio} from '../municipio';

export class Comuna implements BaseEntity {
    constructor(
        public id?: number,
        public nmComuna?: string,
        public municipio?: Municipio,
        public populacao?: number
    ) {
    }
}
