
export class InqueritosPreenchidosDados {
    constructor(
        public nomeProvincia?: string,
        public municipios?: number,
        public comunas?: number,
        public aguasSim?: number,
        public aguasNao?: number,
        public totalAguas?: number,
        public saneamentoSim?: number,
        public saneamentoNao?: number,
        public totalSaneamento?: number,
        public totalInqueritoSector?: number,
        public saneamento?: number,
        public escolas?: number,
        public hospitais?: number,
        public familias?: number,
        public totalGeral?: number
    ) {
    }
}
