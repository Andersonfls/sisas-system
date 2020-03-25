package com.minea.sisas.service.dto;


import java.io.Serializable;

/**
 * A DTO for the Projectos entity.
 */
public class BeneAguaFtSubterraneaOptTecnicaDTO implements Serializable {

    private String nomeProvincia;
    private String nomeMunicipio;
    private String nomeComuna;
    private String fonteAgua;
    private Integer populacao;

    private Integer electricaSistemas;
    private Integer electricaPopulacao;
    private Float electricaPerc;

    private Integer dieselSistemas;
    private Integer dieselPopulacao;
    private Float dieselPerc;

    private Integer gravidadeSistemas;
    private Integer gravidadePopulacao;
    private Float gravidadePerc;

    public BeneAguaFtSubterraneaOptTecnicaDTO() {
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

    public String getFonteAgua() {
        return fonteAgua;
    }

    public void setFonteAgua(String fonteAgua) {
        this.fonteAgua = fonteAgua;
    }

    public Integer getPopulacao() {
        return populacao;
    }

    public void setPopulacao(Integer populacao) {
        this.populacao = populacao;
    }

    public Integer getElectricaSistemas() {
        return electricaSistemas;
    }

    public void setElectricaSistemas(Integer electricaSistemas) {
        this.electricaSistemas = electricaSistemas;
    }

    public Integer getElectricaPopulacao() {
        return electricaPopulacao;
    }

    public void setElectricaPopulacao(Integer electricaPopulacao) {
        this.electricaPopulacao = electricaPopulacao;
    }

    public Float getElectricaPerc() {
        return electricaPerc;
    }

    public void setElectricaPerc(Float electricaPerc) {
        this.electricaPerc = electricaPerc;
    }

    public Integer getDieselSistemas() {
        return dieselSistemas;
    }

    public void setDieselSistemas(Integer dieselSistemas) {
        this.dieselSistemas = dieselSistemas;
    }

    public Integer getDieselPopulacao() {
        return dieselPopulacao;
    }

    public void setDieselPopulacao(Integer dieselPopulacao) {
        this.dieselPopulacao = dieselPopulacao;
    }

    public Float getDieselPerc() {
        return dieselPerc;
    }

    public void setDieselPerc(Float dieselPerc) {
        this.dieselPerc = dieselPerc;
    }

    public Integer getGravidadeSistemas() {
        return gravidadeSistemas;
    }

    public void setGravidadeSistemas(Integer gravidadeSistemas) {
        this.gravidadeSistemas = gravidadeSistemas;
    }

    public Integer getGravidadePopulacao() {
        return gravidadePopulacao;
    }

    public void setGravidadePopulacao(Integer gravidadePopulacao) {
        this.gravidadePopulacao = gravidadePopulacao;
    }

    public Float getGravidadePerc() {
        return gravidadePerc;
    }

    public void setGravidadePerc(Float gravidadePerc) {
        this.gravidadePerc = gravidadePerc;
    }
}
