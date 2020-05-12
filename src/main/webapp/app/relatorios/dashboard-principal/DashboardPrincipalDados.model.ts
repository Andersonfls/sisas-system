
export class DashboardPrincipalDados {
    constructor(
        public sistemaAguaExistentes?: number,
        public totalCasasLigadas?: number,
        public totalPessoasAceesoAgua?: number,
        public mes?: number,
        public ano?: number,
        public totalIndicadores?: number,
        public totalAguaCaptada?: number,
        public totalAguaTratada?: number,
        public totalVolumeAguaDistribuida?: number
    ) {
    }
}
