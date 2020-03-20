package com.minea.sisas.service.dto;


import java.io.Serializable;

/**
 * A DTO for the Projectos entity.
 */
public class FuncSistemasAguaDTO implements Serializable {

    private String nomeProvincia;
    private String nomeMunicipio;
    private String nomeComuna;
    private Integer beneficiariosAgua;
    private Integer numeroSistemas;
    private Integer funcionamAgua;
    private Float naoFuncionamAgua;
    private Float funcionamAguaPerc;
    private Integer naoFuncionamAguaPerc;

    public FuncSistemasAguaDTO() {
    }

    public String getNomeProvincia() {
        return nomeProvincia;
    }

    public void setNomeProvincia(String nomeProvincia) {
        this.nomeProvincia = nomeProvincia;
    }

    public String getNomeMunicipio() {
        return nomeMunicipio;
    }

    public void setNomeMunicipio(String nomeMunicipio) {
        this.nomeMunicipio = nomeMunicipio;
    }

    public String getNomeComuna() {
        return nomeComuna;
    }

    public void setNomeComuna(String nomeComuna) {
        this.nomeComuna = nomeComuna;
    }

    public Integer getBeneficiariosAgua() {
        return beneficiariosAgua;
    }

    public void setBeneficiariosAgua(Integer beneficiariosAgua) {
        this.beneficiariosAgua = beneficiariosAgua;
    }

    public Integer getNumeroSistemas() {
        return numeroSistemas;
    }

    public void setNumeroSistemas(Integer numeroSistemas) {
        this.numeroSistemas = numeroSistemas;
    }

    public Integer getFuncionamAgua() {
        return funcionamAgua;
    }

    public void setFuncionamAgua(Integer funcionamAgua) {
        this.funcionamAgua = funcionamAgua;
    }

    public Float getNaoFuncionamAgua() {
        return naoFuncionamAgua;
    }

    public void setNaoFuncionamAgua(Float naoFuncionamAgua) {
        this.naoFuncionamAgua = naoFuncionamAgua;
    }

    public Float getFuncionamAguaPerc() {
        return funcionamAguaPerc;
    }

    public void setFuncionamAguaPerc(Float funcionamAguaPerc) {
        this.funcionamAguaPerc = funcionamAguaPerc;
    }

    public Integer getNaoFuncionamAguaPerc() {
        return naoFuncionamAguaPerc;
    }

    public void setNaoFuncionamAguaPerc(Integer naoFuncionamAguaPerc) {
        this.naoFuncionamAguaPerc = naoFuncionamAguaPerc;
    }
}
