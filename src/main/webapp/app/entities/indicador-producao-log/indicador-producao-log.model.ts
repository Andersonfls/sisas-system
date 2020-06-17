import { BaseEntity } from './../../shared';
import {IndicadorProducao} from '../indicador-producao';

export class IndicadorProducaoLog implements BaseEntity {
    constructor(
        public id?: number,
        public acao?: string,
        public idUsuario?: number,
        public log?: string,
        public dtLog?: any,
        public idIndicadorProducao?: number,
    ) {
    }
}
