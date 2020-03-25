
export class BeneficiariosBmbMecanica {
    constructor(
        public nomeProvincia?: string,
        public nomeMunicipio?: string,
        public nomeComuna?: string,

        public populacao?: number,
        public pocoMelhorado?: number,
        public furo?: number,
        public nascente?: number,

        public dieselSistemas?: number,
        public dieselPopulacao?: number,
        public dieselPercent?: number,

        public solarSistemas?: number,
        public solarPopulacao?: number,
        public solarPercent?: number,

        public eolicaSistemas?: number,
        public eolicaPopulacao?: number,
        public eolicaPercent?: number,

        public electricaSistemas?: number,
        public electricaPopulacao?: number,
        public electricaPercent?: number,

        public outroSistemas?: number,
        public outroPopulacao?: number,
        public outroPercent?: number

    ) {
    }
}
