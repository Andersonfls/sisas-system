package com.minea.sisas.service.dto;


import com.minea.sisas.domain.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the IndicadorProducao entity.
 */
public class IndicadorProducaoProvinciaDTO implements Serializable {

    private String nomeCampo;

    private String unidade;

    private Integer janeiro;

    private Integer fevereiro;

    private Integer marco;

    private Integer abril;

    private Integer maio;

    private Integer junho;

    private Integer julho;

    private Integer agosto;

    private Integer setembro;

    private Integer outubro;

    private Integer novembro;

    private Integer dezembro;

    public String getNomeCampo() {
        return nomeCampo;
    }

    public void setNomeCampo(String nomeCampo) {
        this.nomeCampo = nomeCampo;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Integer getJaneiro() {
        return janeiro;
    }

    public void setJaneiro(Integer janeiro) {
        this.janeiro = janeiro;
    }

    public Integer getFevereiro() {
        return fevereiro;
    }

    public void setFevereiro(Integer fevereiro) {
        this.fevereiro = fevereiro;
    }

    public Integer getMarco() {
        return marco;
    }

    public void setMarco(Integer marco) {
        this.marco = marco;
    }

    public Integer getAbril() {
        return abril;
    }

    public void setAbril(Integer abril) {
        this.abril = abril;
    }

    public Integer getMaio() {
        return maio;
    }

    public void setMaio(Integer maio) {
        this.maio = maio;
    }

    public Integer getJunho() {
        return junho;
    }

    public void setJunho(Integer junho) {
        this.junho = junho;
    }

    public Integer getJulho() {
        return julho;
    }

    public void setJulho(Integer julho) {
        this.julho = julho;
    }

    public Integer getAgosto() {
        return agosto;
    }

    public void setAgosto(Integer agosto) {
        this.agosto = agosto;
    }

    public Integer getSetembro() {
        return setembro;
    }

    public void setSetembro(Integer setembro) {
        this.setembro = setembro;
    }

    public Integer getOutubro() {
        return outubro;
    }

    public void setOutubro(Integer outubro) {
        this.outubro = outubro;
    }

    public Integer getNovembro() {
        return novembro;
    }

    public void setNovembro(Integer novembro) {
        this.novembro = novembro;
    }

    public Integer getDezembro() {
        return dezembro;
    }

    public void setDezembro(Integer dezembro) {
        this.dezembro = dezembro;
    }
}
