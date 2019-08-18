
export class CoberturaServicos {
    constructor(
        public ambito?: string,
        public populacao?: number,
        public popupacaoPorcentagem?: number,
        public habitantesAgua?: number,
        public habitantesAguaPercentagem?: number,
        public habitantesSaneamento?: number,
        public habitantesSaneamentoPercentagem?: number
    ) {
    }
}
