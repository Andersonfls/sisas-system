import { BaseEntity } from './../../shared';
import {Provincia} from '../provincia/provincia.model';
import {Municipio} from '../municipio/municipio.model';
import {Comuna} from '../comuna/comuna.model';

export class Epas implements BaseEntity {
    constructor(
        public id?: number,
        public nmEpas?: string,
        public nrContribuinte?: string,
        public provincia?: Provincia,
        public municipio?: Municipio,
        public comuna?: Comuna,
        public nmLocalidade?: string,
    ) {
    }
}
