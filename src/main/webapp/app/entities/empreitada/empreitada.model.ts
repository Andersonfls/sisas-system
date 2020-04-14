import { BaseEntity } from './../../shared';
import {ProgramasProjectos} from '../programas-projectos';

export class Empreitada implements BaseEntity {
    constructor(
        public id?: number,
        public idEmpreitada?: number,
        public tipoEmpreitada?: string,
        public dtLancamento?: any,
        public numCapacidadeCaptacao?: number,
        public numCapacidadeCaptacaoEta?: number,
        public numExtensaoCondAdutMat?: number,
        public numCaprmazenamento?: number,
        public numExtensaoRedeMat?: number,
        public numLigacoesDomiciliares?: number,
        public numLigacoesTorneiraQuintal?: number,
        public numChafarisNovos?: number,
        public numChafarisReabilitar?: number,
        public numCapacidadeTratamentoEta?: number,
        public numExtensaoRedeMaterial?: number,
        public numExtensaoCondutasElelMat?: number,
        public numLigacoes?: number,
        public numCaixasVisitas?: number,
        public numEstacoesElevatorias?: number,
        public numLatrinas?: number,
        public idProgramasProjectosId?: ProgramasProjectos,
        public idSistemaAguaId?: number,
        public idContratoId?: number,
    ) {
    }
}
