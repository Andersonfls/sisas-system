package com.minea.sisas.service.dto;


import java.io.Serializable;

/**
 * A DTO for the Projectos entity.
 */
public class SectorAguaSaneamentoDadosDTO implements Serializable {

    private String ambito;
    private Long populacao;
    private Long habitantes;
    private Float habitantesPercent;
    private Long habitantesSaneamento;
    private Float habitantesSaneamentoPer;
    private Float populacaoPercentage;

    public SectorAguaSaneamentoDadosDTO() {
    }

    public SectorAguaSaneamentoDadosDTO(String ambito, Long populacao, Long habitantes, Float habitantesPercent, Long habitantesSaneamento, Float habitantesSaneamentoPer, Float populacaoPercentage) {
        this.ambito = ambito;
        this.populacao = populacao;
        this.habitantes = habitantes;
        this.habitantesPercent = habitantesPercent;
        this.habitantesSaneamento = habitantesSaneamento;
        this.habitantesSaneamentoPer = habitantesSaneamentoPer;
        this.populacaoPercentage = populacaoPercentage;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public Long getPopulacao() {
        return populacao;
    }

    public void setPopulacao(Long populacao) {
        this.populacao = populacao;
    }

    public Long getHabitantes() {
        return habitantes;
    }

    public void setHabitantes(Long habitantes) {
        this.habitantes = habitantes;
    }

    public Float getHabitantesPercent() {
        return habitantesPercent;
    }

    public void setHabitantesPercent(Float habitantesPercent) {
        this.habitantesPercent = habitantesPercent;
    }

    public Long getHabitantesSaneamento() {
        return habitantesSaneamento;
    }

    public void setHabitantesSaneamento(Long habitantesSaneamento) {
        this.habitantesSaneamento = habitantesSaneamento;
    }

    public Float getHabitantesSaneamentoPer() {
        return habitantesSaneamentoPer;
    }

    public void setHabitantesSaneamentoPer(Float habitantesSaneamentoPer) {
        this.habitantesSaneamentoPer = habitantesSaneamentoPer;
    }

    public Float getPopulacaoPercentage() {
        return populacaoPercentage;
    }

    public void setPopulacaoPercentage(Float populacaoPercentage) {
        this.populacaoPercentage = populacaoPercentage;
    }
}
