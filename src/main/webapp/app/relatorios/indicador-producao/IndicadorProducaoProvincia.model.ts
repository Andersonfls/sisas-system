
import {Provincia} from '../../entities/provincia/provincia.model';

export class IndicadorProducaoProvincia {
    constructor(
        public provincia?: Provincia,
        public ano?: string,
        public nomeCampo ?: string,
        public unidade ?: string,
        public janeiro ?: number,
        public fevereiro ?: number,
        public marco ?: number,
        public abril ?: number,
        public maio ?: number,
        public junho ?: number,
        public julho ?: number,
        public agosto ?: number,
        public setembro ?: number,
        public outubro ?: number,
        public novembro ?: number,
        public dezembro ?: number,
    ) {
    }
}
