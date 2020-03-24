
export class BeneficiariosOptTecnica {
    constructor(
        public nomeProvincia?: string,
        public nomeMunicipio?: string,
        public nomeComuna?: string,
        public fonteAgua?: string,
        public populacao?: string,
        public electricaSistemas?: number,
        public electricaFunciona?: number,
        public electricaPopulacao?: number,
        public electricaPerc?: number,
        public electricaNaoFunciona?: number,
        public dieselSistemas?: number,
        public dieselFunciona?: number,
        public dieselNaoFunciona?: number,
        public dieselPopulacao?: number,
        public dieselPerc?: number,
        public gravidadeSistemas?: number,
        public gravidadeFunciona?: number,
        public gravidadeNaoFunciona?: number,
        public gravidadePopulacao?: number,
        public gravidadePerc?: number,
    ) {
    }
}
