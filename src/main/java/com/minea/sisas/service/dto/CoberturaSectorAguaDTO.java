package com.minea.sisas.service.dto;


import java.io.Serializable;

/**
 * A DTO for the Projectos entity.
 */
public class CoberturaSectorAguaDTO implements Serializable {

    private String nomeProvincia;
    private String nomeMunicipio;
    private String nomeComuna;
    private Integer numeroMunicipios;
    private Integer numeroComunas;
    private Integer numeroSistemasFuncionam;
    private Integer populacao;
    private Integer populacaoTotal;
    private Integer beneficiarios;
    private Float coberturaPercent;

    public CoberturaSectorAguaDTO() {
    }

    public CoberturaSectorAguaDTO(String nomeProvincia, Integer numeroMunicipios, Integer numeroComunas, Integer numeroSistemasFuncionam, Integer populacao, Integer populacaoTotal, Integer beneficiarios, Float coberturaPercent) {
        this.nomeProvincia = nomeProvincia;
        this.numeroMunicipios = numeroMunicipios;
        this.numeroComunas = numeroComunas;
        this.numeroSistemasFuncionam = numeroSistemasFuncionam;
        this.populacao = populacao;
        this.populacaoTotal = populacaoTotal;
        this.beneficiarios = beneficiarios;
        this.coberturaPercent = coberturaPercent;
    }

    public String getNomeProvincia() {
        return nomeProvincia;
    }

    public void setNomeProvincia(String nomeProvincia) {
        this.nomeProvincia = nomeProvincia;
    }

    public Integer getNumeroMunicipios() {
        return numeroMunicipios;
    }

    public void setNumeroMunicipios(Integer numeroMunicipios) {
        this.numeroMunicipios = numeroMunicipios;
    }

    public Integer getNumeroComunas() {
        return numeroComunas;
    }

    public void setNumeroComunas(Integer numeroComunas) {
        this.numeroComunas = numeroComunas;
    }

    public Integer getNumeroSistemasFuncionam() {
        return numeroSistemasFuncionam;
    }

    public void setNumeroSistemasFuncionam(Integer numeroSistemasFuncionam) {
        this.numeroSistemasFuncionam = numeroSistemasFuncionam;
    }

    public Integer getPopulacaoTotal() {
        return populacaoTotal;
    }

    public void setPopulacaoTotal(Integer populacaoTotal) {
        this.populacaoTotal = populacaoTotal;
    }

    public Integer getBeneficiarios() {
        return beneficiarios;
    }

    public void setBeneficiarios(Integer beneficiarios) {
        this.beneficiarios = beneficiarios;
    }

    public Float getCoberturaPercent() {
        return coberturaPercent;
    }

    public void setCoberturaPercent(Float coberturaPercent) {
        this.coberturaPercent = coberturaPercent;
    }

    public Integer getPopulacao() {
        return populacao;
    }

    public void setPopulacao(Integer populacao) {
        this.populacao = populacao;
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
}
