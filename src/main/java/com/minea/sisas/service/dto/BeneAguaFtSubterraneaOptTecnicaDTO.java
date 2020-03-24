package com.minea.sisas.service.dto;


import java.io.Serializable;

/**
 * A DTO for the Projectos entity.
 */
public class BeneAguaFtSubterraneaTpBomba implements Serializable {

    private String nomeProvincia;
    private String nomeMunicipio;
    private String nomeComuna;
    private Integer numeroPocoMelhorado;
    private Integer furo;
    private Integer nascente;
    private Integer totalBombaGravidade;
    private Integer populacaoBeneficiadaGravidade;
    private Integer totalTipoBombaOutros;
    private Integer qtdPopulacaoOutros;

    public BeneAguaFtSubterraneaTpBomba() {
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

    public Integer getNumeroPocoMelhorado() {
        return numeroPocoMelhorado;
    }

    public void setNumeroPocoMelhorado(Integer numeroPocoMelhorado) {
        this.numeroPocoMelhorado = numeroPocoMelhorado;
    }

    public Integer getFuro() {
        return furo;
    }

    public void setFuro(Integer furo) {
        this.furo = furo;
    }

    public Integer getNascente() {
        return nascente;
    }

    public void setNascente(Integer nascente) {
        this.nascente = nascente;
    }

    public Integer getTotalBombaGravidade() {
        return totalBombaGravidade;
    }

    public void setTotalBombaGravidade(Integer totalBombaGravidade) {
        this.totalBombaGravidade = totalBombaGravidade;
    }

    public Integer getPopulacaoBeneficiadaGravidade() {
        return populacaoBeneficiadaGravidade;
    }

    public void setPopulacaoBeneficiadaGravidade(Integer populacaoBeneficiadaGravidade) {
        this.populacaoBeneficiadaGravidade = populacaoBeneficiadaGravidade;
    }

    public Integer getTotalTipoBombaOutros() {
        return totalTipoBombaOutros;
    }

    public void setTotalTipoBombaOutros(Integer totalTipoBombaOutros) {
        this.totalTipoBombaOutros = totalTipoBombaOutros;
    }

    public Integer getQtdPopulacaoOutros() {
        return qtdPopulacaoOutros;
    }

    public void setQtdPopulacaoOutros(Integer qtdPopulacaoOutros) {
        this.qtdPopulacaoOutros = qtdPopulacaoOutros;
    }
}
