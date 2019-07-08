import { BaseEntity } from './../../shared';
import {SistemaAgua} from '../sistema-agua';

export class SistemaAguaLog implements BaseEntity {
    constructor(
        public id?: number,
        public acao?: string,
        public idUsuario?: number,
        public log?: string,
        public dtLog?: any,
        public idSistemaAguaId?: SistemaAgua,
    ) {
    }
}
