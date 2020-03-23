
export class BeneAguaFtSubterraneaTpBombaManual {
    constructor(
        public nomeProvincia?: string,
        public nomeMunicipio?: string,
        public nomeComuna?: string,

        public populacao?: number,
        public numeroPocoMelhorado?: number,
        public furo?: number,
        public nascente?: number,
        public afridev?: number,
        public afridevPopulacao?: number,
        public vergnet?: number,
        public vergnetPopulacao?: number,
        public volanta?: number,
        public volantaPopulacao?: number,
        public indiaMarc?: number,
        public indiaMarcPopulacao?: number,
        public outro?: number,
        public outroPopulacao?: number,
    ) {
    }
}
