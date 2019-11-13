package com.minea.sisas.service.dto;


import java.io.Serializable;

/**
 * A DTO for the Projectos entity.
 */
public class TratamentoSistemasAguaDadosDTO implements Serializable {

    private String nomeProvincia;
    private Integer sistemasAgua;
    private Integer padrao;
    private Integer basico;
    private Integer naoRealiza;
    private Integer outros;

    public TratamentoSistemasAguaDadosDTO() {
    }

    public TratamentoSistemasAguaDadosDTO(String nomeProvincia, Integer sistemasAgua, Integer padrao, Integer basico, Integer naoRealiza, Integer outros) {
        this.nomeProvincia = nomeProvincia;
        this.sistemasAgua = sistemasAgua;
        this.padrao = padrao;
        this.basico = basico;
        this.naoRealiza = naoRealiza;
        this.outros = outros;
    }

    public String getNomeProvincia() {
        return nomeProvincia;
    }

    public void setNomeProvincia(String nomeProvincia) {
        this.nomeProvincia = nomeProvincia;
    }

    public Integer getSistemasAgua() {
        return sistemasAgua;
    }

    public void setSistemasAgua(Integer sistemasAgua) {
        this.sistemasAgua = sistemasAgua;
    }

    public Integer getPadrao() {
        return padrao;
    }

    public void setPadrao(Integer padrao) {
        this.padrao = padrao;
    }

    public Integer getBasico() {
        return basico;
    }

    public void setBasico(Integer basico) {
        this.basico = basico;
    }

    public Integer getNaoRealiza() {
        return naoRealiza;
    }

    public void setNaoRealiza(Integer naoRealiza) {
        this.naoRealiza = naoRealiza;
    }

    public Integer getOutros() {
        return outros;
    }

    public void setOutros(Integer outros) {
        this.outros = outros;
    }
}
