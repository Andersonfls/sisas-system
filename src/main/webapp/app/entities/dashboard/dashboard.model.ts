
export class DashboardModel {
    constructor(
        public nomeProvincia?: string,
        public numeroSistemas?: number,
        public numeroSistemasFuncionam?: number,
        public sistemasFuncionamPerc?: number,
        public numeroSistemasNaoFuncionam?: number,
        public sistemasNaoFuncionamPerc?: number
    ) {
    }
}
