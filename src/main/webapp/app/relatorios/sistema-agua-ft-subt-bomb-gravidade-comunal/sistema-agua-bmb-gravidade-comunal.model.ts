export class SistemaAguaBmbGravidadeComunal {
    constructor(
        public nomeProvincia?: string,
        public nomeMunicipio?: string,
        public nomeComuna?: string,
        public pocoMelhorado?: number,
        public furo?: number,
        public nascente?: number,
        public totalSistemas?: number,

        public gravidadeTotalSistema?: number,
        public gravidadeSistemaFunciona?: number,
        public gravidadeSistemaNaoFunciona?: number,

        public outroTotalSistema?: number,
        public outroSistemaFunciona?: number,
        public outroSistemaNaoFunciona?: number,

    ) {
    }
}
