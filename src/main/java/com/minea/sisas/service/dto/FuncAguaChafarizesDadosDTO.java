package com.minea.sisas.service.dto;


import java.io.Serializable;

/**
 * A DTO for the Projectos entity.
 */
public class FuncAguaChafarizesDadosDTO implements Serializable {

    private String nomeProvincia;
    private String nomeMunicipio;
    private String nomeComuna;

    private Integer numeroSistemas;
    private Integer funcionamAgua;
    private Integer naoFuncionamAgua;
    private Float funcionamAguaPerc;
    private Float naoFuncionamAguaPerc;
    private Integer numeroChafarizes;

    private Integer funcionamChafariz;
    private Integer naoFuncionamChafariz;
    private Float funcionamChafarizPerc;
    private Float naoFuncionamChafarizPerc;

    private Integer totalSistemas;
    private Integer electricaSistemas;
    private Integer electricaFuncionam;
    private Integer electricaNaoFuncionam;

    private Integer dieselSistemas;
    private Integer dieselFuncionam;
    private Integer dieselNaoFuncionam;

    private Integer gravidadeSistemas;
    private Integer gravidadeFuncionam;
    private Integer gravidadeNaoFuncionam;


    public FuncAguaChafarizesDadosDTO() {
    }

    public FuncAguaChafarizesDadosDTO(String nomeProvincia, Integer numeroSistemas, Integer funcionamAgua, Integer naoFuncionamAgua, Float funcionamAguaPerc, Float naoFuncionamAguaPerc, Integer numeroChafarizes, Integer funcionamChafariz, Integer naoFuncionamChafariz, Float funcionamChafarizPerc, Float naoFuncionamChafarizPerc) {
        this.nomeProvincia = nomeProvincia;
        this.numeroSistemas = numeroSistemas;
        this.funcionamAgua = funcionamAgua;
        this.naoFuncionamAgua = naoFuncionamAgua;
        this.funcionamAguaPerc = funcionamAguaPerc;
        this.naoFuncionamAguaPerc = naoFuncionamAguaPerc;
        this.numeroChafarizes = numeroChafarizes;
        this.funcionamChafariz = funcionamChafariz;
        this.naoFuncionamChafariz = naoFuncionamChafariz;
        this.funcionamChafarizPerc = funcionamChafarizPerc;
        this.naoFuncionamChafarizPerc = naoFuncionamChafarizPerc;
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

    public Integer getFuncionamAgua() {
        return funcionamAgua;
    }

    public void setFuncionamAgua(Integer funcionamAgua) {
        this.funcionamAgua = funcionamAgua;
    }

    public Integer getNaoFuncionamAgua() {
        return naoFuncionamAgua;
    }

    public void setNaoFuncionamAgua(Integer naoFuncionamAgua) {
        this.naoFuncionamAgua = naoFuncionamAgua;
    }

    public Float getFuncionamAguaPerc() {
        return funcionamAguaPerc;
    }

    public void setFuncionamAguaPerc(Float funcionamAguaPerc) {
        this.funcionamAguaPerc = funcionamAguaPerc;
    }

    public Float getNaoFuncionamAguaPerc() {
        return naoFuncionamAguaPerc;
    }

    public void setNaoFuncionamAguaPerc(Float naoFuncionamAguaPerc) {
        this.naoFuncionamAguaPerc = naoFuncionamAguaPerc;
    }

    public Integer getNumeroChafarizes() {
        return numeroChafarizes;
    }

    public void setNumeroChafarizes(Integer numeroChafarizes) {
        this.numeroChafarizes = numeroChafarizes;
    }

    public Integer getFuncionamChafariz() {
        return funcionamChafariz;
    }

    public void setFuncionamChafariz(Integer funcionamChafariz) {
        this.funcionamChafariz = funcionamChafariz;
    }

    public Integer getNaoFuncionamChafariz() {
        return naoFuncionamChafariz;
    }

    public void setNaoFuncionamChafariz(Integer naoFuncionamChafariz) {
        this.naoFuncionamChafariz = naoFuncionamChafariz;
    }

    public Float getFuncionamChafarizPerc() {
        return funcionamChafarizPerc;
    }

    public void setFuncionamChafarizPerc(Float funcionamChafarizPerc) {
        this.funcionamChafarizPerc = funcionamChafarizPerc;
    }

    public Float getNaoFuncionamChafarizPerc() {
        return naoFuncionamChafarizPerc;
    }

    public void setNaoFuncionamChafarizPerc(Float naoFuncionamChafarizPerc) {
        this.naoFuncionamChafarizPerc = naoFuncionamChafarizPerc;
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

    public Integer getTotalSistemas() {
        return totalSistemas;
    }

    public void setTotalSistemas(Integer totalSistemas) {
        this.totalSistemas = totalSistemas;
    }

    public Integer getElectricaSistemas() {
        return electricaSistemas;
    }

    public void setElectricaSistemas(Integer electricaSistemas) {
        this.electricaSistemas = electricaSistemas;
    }

    public Integer getElectricaFuncionam() {
        return electricaFuncionam;
    }

    public void setElectricaFuncionam(Integer electricaFuncionam) {
        this.electricaFuncionam = electricaFuncionam;
    }

    public Integer getElectricaNaoFuncionam() {
        return electricaNaoFuncionam;
    }

    public void setElectricaNaoFuncionam(Integer electricaNaoFuncionam) {
        this.electricaNaoFuncionam = electricaNaoFuncionam;
    }

    public Integer getDieselSistemas() {
        return dieselSistemas;
    }

    public void setDieselSistemas(Integer dieselSistemas) {
        this.dieselSistemas = dieselSistemas;
    }

    public Integer getDieselFuncionam() {
        return dieselFuncionam;
    }

    public void setDieselFuncionam(Integer dieselFuncionam) {
        this.dieselFuncionam = dieselFuncionam;
    }

    public Integer getDieselNaoFuncionam() {
        return dieselNaoFuncionam;
    }

    public void setDieselNaoFuncionam(Integer dieselNaoFuncionam) {
        this.dieselNaoFuncionam = dieselNaoFuncionam;
    }

    public Integer getGravidadeSistemas() {
        return gravidadeSistemas;
    }

    public void setGravidadeSistemas(Integer gravidadeSistemas) {
        this.gravidadeSistemas = gravidadeSistemas;
    }

    public Integer getGravidadeFuncionam() {
        return gravidadeFuncionam;
    }

    public void setGravidadeFuncionam(Integer gravidadeFuncionam) {
        this.gravidadeFuncionam = gravidadeFuncionam;
    }

    public Integer getGravidadeNaoFuncionam() {
        return gravidadeNaoFuncionam;
    }

    public void setGravidadeNaoFuncionam(Integer gravidadeNaoFuncionam) {
        this.gravidadeNaoFuncionam = gravidadeNaoFuncionam;
    }
}
