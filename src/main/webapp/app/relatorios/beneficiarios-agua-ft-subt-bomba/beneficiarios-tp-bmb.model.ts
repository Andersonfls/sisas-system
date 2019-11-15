
export class BeneficiariosBmbMecanica {
    constructor(
        public nomeProvincia?: string,
        public nomeMunicipio?: string,
        public nomeComuna?: string,
        public populacao?: string,
        public pocoMelhorado?: number,
        public furo?: number,
        public nascente?: number,
        public afridev?: number,
        public afridevPopulacao?: number,
        public vergnet?: number,
        public vergnetPopulacao?: number,
        public volanta?: number,
        public volantaPopulacao?: number,
        public indiaMarck?: number,
        public indiaMarckPopulacao?: number,
        public outro?: number,
        public outroPopulacao?: number,
        public gravidade ?: number,
        public gravidadePopulacao?: number
    ) {
    }
}
