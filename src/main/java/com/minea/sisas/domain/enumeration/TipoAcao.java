package com.minea.sisas.domain.enumeration;

/**
 * The TipoAcao enumeration.
 */
public enum TipoAcao {
    INCLUSAO(1, "Inclusão","registro atualizado"),
    ATUALIZACAO(2, "Atualizacão", "registro atualizado"),
    INATIVACAO(3, "Inativacão", "registro atualizado"),
    REMOCAO(2, "Remocão", "registro atualizado");

    private final Integer codigo;
    private final String descricao;
    private final String log;

    TipoAcao(Integer vlcodigo, String valorDescricao, String vlog){
        codigo = vlcodigo;
        descricao = valorDescricao;
        log = vlog;
    }
    public Integer getCodigo(){
        return codigo;
    }

    public String getDescricao(){
        return descricao;
    }

    public String getLog() { return  log;}
}
