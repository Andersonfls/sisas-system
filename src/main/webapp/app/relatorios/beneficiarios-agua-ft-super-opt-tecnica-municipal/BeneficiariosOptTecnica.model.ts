
export class BeneficiariosOptTecnica {
    constructor(
        public nomeProvincia?: string,
        public nomeMunicipio?: string,
        public nomeComuna?: string,
        public fonteAgua?: string,
        public populacao?: number,

        public electricaSistemas?: number,
        public electricaPopulacao?: number,
        public electricaPerc?: number,

        public dieselSistemas?: number,
        public dieselPopulacao?: number,
        public dieselPerc?: number,

        public gravidadeSistemas?: number,
        public gravidadePopulacao?: number,
        public gravidadePerc?: number,
    ) {
    }
}
