
export class BeneficiariosBmbManual {
    constructor(
        public nomeProvincia?: string,
        public nomeMunicipio?: string,
        public nomeComuna?: string,
        public pocoMelhorado?: number,
        public furo?: number,
        public nascente?: number,
        public totalSistemas?: number,
        public afridevTotalSistema?: number,
        public afridevSistemaFunciona?: number,
        public afridevSistemaNaoFunciona?: number,
        public vergnetTotalSistema?: number,
        public vergnetSistemaFunciona?: number,
        public vergnetSistemaNaoFunciona?: number,
        public volantTotalSistema?: number,
        public volantSistemaFunciona?: number,
        public volantSistemaNaoFunciona?: number,
        public indiaTotalSistema?: number,
        public indiaSistemaFunciona?: number,
        public indiaSistemaNaoFunciona?: number,
        public outroTotalSistema?: number,
        public outroSistemaFunciona?: number,
        public outroSistemaNaoFunciona?: number,

    ) {
    }
}
