
export class SectorAguaSaneamentoDados {
    constructor(
        public ambito?: string,
        public populacao?: number,
        public habitantes?: number,
        public habitantesPercent?: number,
        public habitantesSaneamento?: number,
        public habitantesSaneamentoPer?: number,
        public populacaoPercentage?: number,

        public provincia?: string,
        public municipios?: number,
        public nomeMunicipio?: string,
        public comunas?: number,
        public nomeComuna?: string,
        public populacaoTotal?: number,
        public populacaoBeneficiadaAgua?: number,
        public populacaoBeneficiadaSaneamento?: number,
        public coberturaAgua?: number,
        public coberturaSaneamento?: number,

        public municipio?: string,
        public comuna?: string,
        public numeroMunicipios?: number,
        public numeroComunas?: number,
        public numeroProvincias?: number
    ) {
    }
}
