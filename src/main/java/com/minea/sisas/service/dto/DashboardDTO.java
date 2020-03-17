package com.minea.sisas.service.dto;


import java.io.Serializable;

/**
 * A DTO for the Projectos entity.
 */
public class DashboardDTO implements Serializable {

    private String nomeProvincia;
    private Integer numeroSistemas;
    private Integer numeroSistemasFuncionam;
    private Float sistemasFuncionamPerc;
    private Integer numeroSistemasNaoFuncionam;
    private Float sistemasNaoFuncionamPerc;

    public DashboardDTO() {
    }

    public String getNomeProvincia() {
        return nomeProvincia;
    }

    public void setNomeProvincia(String nomeProvincia) {
        this.nomeProvincia = nomeProvincia;
    }

    public Integer getNumeroSistemas() {
        return numeroSistemas;
    }

    public void setNumeroSistemas(Integer numeroSistemas) {
        this.numeroSistemas = numeroSistemas;
    }

    public Integer getNumeroSistemasFuncionam() {
        return numeroSistemasFuncionam;
    }

    public void setNumeroSistemasFuncionam(Integer numeroSistemasFuncionam) {
        this.numeroSistemasFuncionam = numeroSistemasFuncionam;
    }

    public Float getSistemasFuncionamPerc() {
        return sistemasFuncionamPerc;
    }

    public void setSistemasFuncionamPerc(Float sistemasFuncionamPerc) {
        this.sistemasFuncionamPerc = sistemasFuncionamPerc;
    }

    public Integer getNumeroSistemasNaoFuncionam() {
        return numeroSistemasNaoFuncionam;
    }

    public void setNumeroSistemasNaoFuncionam(Integer numeroSistemasNaoFuncionam) {
        this.numeroSistemasNaoFuncionam = numeroSistemasNaoFuncionam;
    }

    public Float getSistemasNaoFuncionamPerc() {
        return sistemasNaoFuncionamPerc;
    }

    public void setSistemasNaoFuncionamPerc(Float sistemasNaoFuncionamPerc) {
        this.sistemasNaoFuncionamPerc = sistemasNaoFuncionamPerc;
    }
}
