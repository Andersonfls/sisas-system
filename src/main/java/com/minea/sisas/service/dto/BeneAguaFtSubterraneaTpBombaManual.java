package com.minea.sisas.service.dto;


import java.io.Serializable;

/**
 * A DTO for the Projectos entity.
 */
public class BeneAguaFtSubterraneaTpBombaManual implements Serializable {

    private String nomeProvincia;
    private String nomeMunicipio;
    private String nomeComuna;

    private Integer populacao;
    private Integer numeroPocoMelhorado;
    private Integer furo;
    private Integer nascente;
    private Integer afridev;
    private Integer afridevPopulacao;
    private Integer vergnet;

    private Integer vergnetPopulacao;
    private Integer volanta;
    private Integer volantaPopulacao;
    private Integer indiaMarc;
    private Integer indiaMarcPopulacao;
    private Integer outro;
    private Integer outroPopulacao;

    public BeneAguaFtSubterraneaTpBombaManual() {
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

    public Integer getPopulacao() {
        return populacao;
    }

    public void setPopulacao(Integer populacao) {
        this.populacao = populacao;
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

    public Integer getAfridev() {
        return afridev;
    }

    public void setAfridev(Integer afridev) {
        this.afridev = afridev;
    }

    public Integer getAfridevPopulacao() {
        return afridevPopulacao;
    }

    public void setAfridevPopulacao(Integer afridevPopulacao) {
        this.afridevPopulacao = afridevPopulacao;
    }

    public Integer getVergnet() {
        return vergnet;
    }

    public void setVergnet(Integer vergnet) {
        this.vergnet = vergnet;
    }

    public Integer getVergnetPopulacao() {
        return vergnetPopulacao;
    }

    public void setVergnetPopulacao(Integer vergnetPopulacao) {
        this.vergnetPopulacao = vergnetPopulacao;
    }

    public Integer getVolanta() {
        return volanta;
    }

    public void setVolanta(Integer volanta) {
        this.volanta = volanta;
    }

    public Integer getVolantaPopulacao() {
        return volantaPopulacao;
    }

    public void setVolantaPopulacao(Integer volantaPopulacao) {
        this.volantaPopulacao = volantaPopulacao;
    }

    public Integer getIndiaMarc() {
        return indiaMarc;
    }

    public void setIndiaMarc(Integer indiaMarc) {
        this.indiaMarc = indiaMarc;
    }

    public Integer getIndiaMarcPopulacao() {
        return indiaMarcPopulacao;
    }

    public void setIndiaMarcPopulacao(Integer indiaMarcPopulacao) {
        this.indiaMarcPopulacao = indiaMarcPopulacao;
    }

    public Integer getOutro() {
        return outro;
    }

    public void setOutro(Integer outro) {
        this.outro = outro;
    }

    public Integer getOutroPopulacao() {
        return outroPopulacao;
    }

    public void setOutroPopulacao(Integer outroPopulacao) {
        this.outroPopulacao = outroPopulacao;
    }
}
