
export class TratamentoSistemaAguaDados {
    constructor(
        public nomeProvincia?: string,
        public nomeMunicipio?: string,
        public nomeComuna?: string,
        public sistemasAgua?: number,
        public padrao?: number,
        public basico?: number,
        public naoRealiza?: number,
        public outros?: number
    ) {
    }
}
