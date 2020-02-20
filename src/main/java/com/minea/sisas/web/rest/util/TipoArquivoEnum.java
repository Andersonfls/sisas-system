package com.minea.sisas.web.rest.util;

public enum TipoArquivoEnum {
    PUBLICACAO_INICIAL(1),
    PUBLICACAO(2),
    PROJECTOS(3);

    public final Integer codigo;

    TipoArquivoEnum(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }
}
