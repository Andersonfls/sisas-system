import { BaseEntity } from './../../shared';

export class AvariaSistemaAgua implements BaseEntity {
    constructor(
        public id?: number,
        public idSistemaAgua?: number,
        public nmTpAvariaSistema?: string,
        public causaAvariaSistema?: string,
        public statusResolucao?: string,
        public tempoServicoDisponivel?: string,
    ) {
        this.id = id ? id : null;
        this.nmTpAvariaSistema = nmTpAvariaSistema ? nmTpAvariaSistema : null;
        this.causaAvariaSistema = causaAvariaSistema ? causaAvariaSistema : null;
        this.statusResolucao = statusResolucao ? statusResolucao : null;
        this.tempoServicoDisponivel = tempoServicoDisponivel ? tempoServicoDisponivel : null;
    }
}
