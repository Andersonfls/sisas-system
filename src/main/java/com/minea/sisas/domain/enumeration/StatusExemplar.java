package com.minea.sisas.domain.enumeration;


/**
 * The StatusProcesso enumeration.
 */
public enum StatusExemplar {
    DISPONIVEL(0, "Disponivel"),
    EMPRESTADO(1, "Emprestado"),
    RESERVADO(2, "Reservado"),
    CANCELADO(3, "Cancelado");

    private final Integer valor;
    private final String descricao;

    StatusExemplar(Integer valorOpcao, String valorDescricao){
        valor = valorOpcao;
        descricao = valorDescricao;
    }
    public Integer getValor(){
        return valor;
    }

    public String getDescricao(){
        return descricao;
    }

}
