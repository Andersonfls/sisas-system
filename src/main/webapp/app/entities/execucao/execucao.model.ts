import { BaseEntity } from './../../shared';
import {ProgramasProjectos} from '../programas-projectos';

export class Execucao implements BaseEntity {
    constructor(
        public id?: number,
        public idExecucao?: number,
        public tipoEmpreitada?: string,
        public dtLancamento?: any,
        public dtPeridoReferencia?: any,
        public dtFimReferencia?: any,
        public valorFacturadoPeriodo?: number,
        public dtFactura?: any,
        public numFactura?: string,
        public txCambio?: number,
        public constrangimento?: string,
        public valorPagoPeriodo?: number,
        public idSituacaoId?: number,
        public idProgramasProjectosId?: ProgramasProjectos,
        public idSistemaAguaId?: number,
        public idContratoId?: number,
    ) {
    }
}
