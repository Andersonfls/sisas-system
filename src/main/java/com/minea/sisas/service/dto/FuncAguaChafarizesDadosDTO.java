package com.minea.sisas.service.dto;


import java.io.Serializable;

/**
 * A DTO for the Projectos entity.
 */
public class FuncAguaChafarizesDadosDTO implements Serializable {

    private String nomeProvincia;
    private Integer numeroSistemas;
    private Integer funcionamAgua;
    private Integer naoFuncionamAgua;
    private Integer funcionamAguaPerc;
    private Integer naoFuncionamAguaPerc;
    private Integer numeroChafarizes;

    private Integer funcionamChafariz;
    private Integer naoFuncionamChafariz;
    private Integer funcionamChafarizPerc;
    private Integer naoFuncionamChafarizPerc;

    public FuncAguaChafarizesDadosDTO() {
    }

    public FuncAguaChafarizesDadosDTO(String nomeProvincia, Integer numeroSistemas, Integer funcionamAgua, Integer naoFuncionamAgua, Integer funcionamAguaPerc, Integer naoFuncionamAguaPerc, Integer numeroChafarizes, Integer funcionamChafariz, Integer naoFuncionamChafariz, Integer funcionamChafarizPerc, Integer naoFuncionamChafarizPerc) {
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

    public Integer getFuncionamAguaPerc() {
        return funcionamAguaPerc;
    }

    public void setFuncionamAguaPerc(Integer funcionamAguaPerc) {
        this.funcionamAguaPerc = funcionamAguaPerc;
    }

    public Integer getNaoFuncionamAguaPerc() {
        return naoFuncionamAguaPerc;
    }

    public void setNaoFuncionamAguaPerc(Integer naoFuncionamAguaPerc) {
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

    public Integer getFuncionamChafarizPerc() {
        return funcionamChafarizPerc;
    }

    public void setFuncionamChafarizPerc(Integer funcionamChafarizPerc) {
        this.funcionamChafarizPerc = funcionamChafarizPerc;
    }

    public Integer getNaoFuncionamChafarizPerc() {
        return naoFuncionamChafarizPerc;
    }

    public void setNaoFuncionamChafarizPerc(Integer naoFuncionamChafarizPerc) {
        this.naoFuncionamChafarizPerc = naoFuncionamChafarizPerc;
    }
}
