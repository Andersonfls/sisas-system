package com.minea.sisas.service.dto;


import java.io.Serializable;

/**
 * A DTO for the Projectos entity.
 */
public class SectorAguaSaneamentoDadosDTO implements Serializable {

    private String ambito;
    private Long populacao;
    private Long habitantes;
    private Integer habitantesPercent;
    private Long habitantesSaneamento;
    private Integer habitantesSaneamentoPer;
    private Integer populacaoPercentage;

    public SectorAguaSaneamentoDadosDTO() {
    }

    public SectorAguaSaneamentoDadosDTO(String ambito, Long populacao, Long habitantes, Integer habitantesPercent, Long habitantesSaneamento, Integer habitantesSaneamentoPer, Integer populacaoPercentage) {
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

    public Integer getHabitantesPercent() {
        return habitantesPercent;
    }

    public void setHabitantesPercent(Integer habitantesPercent) {
        this.habitantesPercent = habitantesPercent;
    }

    public Long getHabitantesSaneamento() {
        return habitantesSaneamento;
    }

    public void setHabitantesSaneamento(Long habitantesSaneamento) {
        this.habitantesSaneamento = habitantesSaneamento;
    }

    public Integer getHabitantesSaneamentoPer() {
        return habitantesSaneamentoPer;
    }

    public void setHabitantesSaneamentoPer(Integer habitantesSaneamentoPer) {
        this.habitantesSaneamentoPer = habitantesSaneamentoPer;
    }

    public Integer getPopulacaoPercentage() {
        return populacaoPercentage;
    }

    public void setPopulacaoPercentage(Integer populacaoPercentage) {
        this.populacaoPercentage = populacaoPercentage;
    }
}
