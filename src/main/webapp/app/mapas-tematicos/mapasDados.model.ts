
export class MapasDados {
    constructor(
        public idProvincia?: number,
        public idMunicipio?: number,
        public idComuna?: number,
        public beneficiariosSistemaAgua?: number,
        public porcentagemCobertura?: number,
        public porcentagemFuncionam?: number,
        public porcentagemNaoFuncionam?: number
    ) {
    }
}
