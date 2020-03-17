
export class CoberturaSectorAguaModel {
    constructor(
        public nomeProvincia?: string,
        public nomeMunicipio?: string,
        public nomeComuna?: string,
        public numeroMunicipios?: number,
        public numeroComunas?: number,
        public numeroSistemasFuncionam?: number,
        public populacao?: number,
        public beneficiarios?: number,
        public coberturaPercent?: number
    ) {
    }
}
