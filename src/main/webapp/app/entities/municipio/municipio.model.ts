import { BaseEntity } from './../../shared';
import {Provincia} from '../provincia';

export class Municipio implements BaseEntity {
    constructor(
        public id?: number,
        public nmMunicipio?: string,
        public provincia?: Provincia,
        public valor?: number,
        public populacao?: number
    ) {
    }
}
