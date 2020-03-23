
export class BeneficiariosBmbEnergia {
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
        public dieselPerc?: number,
        public solarSistemas?: number,
        public solarPopulacao?: number,
        public solarPerc?: number,
        public eolicaSistemas?: number,
        public eolicaPopulacao?: number,
        public eolicaPerc?: number,
        public electricaSistemas?: number,
        public electricaPopulacao?: number,
        public electricaPerc?: number,
        public outroSistemas?: number,
        public outroPopulacao?: number,
        public outroPerc?: number,

    ) {
    }
}
