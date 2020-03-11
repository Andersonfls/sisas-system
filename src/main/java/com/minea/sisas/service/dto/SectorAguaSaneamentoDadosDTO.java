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

    private String provincia;
    private String municipio;
    private String comuna;
    private Long numeroMunicipios;
    private Long numeroComunas;
    private Long numeroProvincias;

    private Long aguasSim;
    private Long totalSector;
    private Long numeroFuncionam;

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

    public Long getNumeroMunicipios() {
        return numeroMunicipios;
    }

    public void setNumeroMunicipios(Long numeroMunicipios) {
        this.numeroMunicipios = numeroMunicipios;
    }

    public Long getNumeroComunas() {
        return numeroComunas;
    }

    public void setNumeroComunas(Long numeroComunas) {
        this.numeroComunas = numeroComunas;
    }

    public Long getNumeroProvincias() {
        return numeroProvincias;
    }

    public void setNumeroProvincias(Long numeroProvincias) {
        this.numeroProvincias = numeroProvincias;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
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

    public Long getAguasSim() {
        return aguasSim;
    }

    public void setAguasSim(Long aguasSim) {
        this.aguasSim = aguasSim;
    }

    public Long getTotalSector() {
        return totalSector;
    }

    public void setTotalSector(Long totalSector) {
        this.totalSector = totalSector;
    }

    public Long getNumeroFuncionam() {
        return numeroFuncionam;
    }

    public void setNumeroFuncionam(Long numeroFuncionam) {
        this.numeroFuncionam = numeroFuncionam;
    }
}
