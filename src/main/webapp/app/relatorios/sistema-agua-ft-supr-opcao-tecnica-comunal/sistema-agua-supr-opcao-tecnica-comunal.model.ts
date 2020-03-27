export class SistemaAguaSprOpcaoTecnicaComunal {
    constructor(
        public nomeProvincia?: string,
        public nomeMunicipio?: string,
        public nomeComuna?: string,
        public totalSistemas?: number,

        public electricaTotalSistema?: number,
        public electricaSistemaFunciona?: number,
        public electricaSistemaNaoFunciona?: number,

        public dieselTotalSistema?: number,
        public dieselSistemaFunciona?: number,
        public dieselSistemaNaoFunciona?: number,

        public gravidadeTotalSistema?: number,
        public gravidadeSistemaFunciona?: number,
        public gravidadeSistemaNaoFunciona?: number,

    ) {
    }
}
