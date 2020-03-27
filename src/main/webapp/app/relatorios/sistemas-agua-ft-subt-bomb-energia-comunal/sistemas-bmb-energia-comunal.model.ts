
export class SistemasFtSubtBmbEnergia {
    constructor(
        public nomeProvincia?: string,
        public nomeMunicipio?: string,
        public nomeComuna?: string,

        public totalSistemas?: number,
        public pocoMelhorado?: number,
        public furo?: number,
        public nascente?: number,

        public dieselSistemas?: number,
        public dieselSistemaFunciona?: number,
        public dieselSistemaNaoFunciona?: number,

        public solarSistemas?: number,
        public solarSistemaFunciona?: number,
        public solarSistemaNaoFunciona?: number,

        public eolicaSistemas?: number,
        public eolicaSistemaFunciona?: number,
        public eolicaSistemaNaoFunciona?: number,

        public electricaSistemas?: number,
        public electricaSistemaFunciona?: number,
        public electricaSistemaNaoFunciona?: number,

        public outroSistemas?: number,
        public outroSistemaFunciona?: number,
        public outroSistemaNaoFunciona?: number,
    ) {
    }
}
