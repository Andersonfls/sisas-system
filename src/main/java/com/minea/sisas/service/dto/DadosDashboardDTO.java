package com.minea.sisas.service.dto;



import java.io.Serializable;

/**
 * A DTO for the DadosDashboardDTO entity.
 */
public class DadosDashboardDTO implements Serializable {

    private Integer sistemaAguaExistentes;
    private Integer totalCasasLigadas;
    private Integer totalPessoasAceesoAgua;
    private Integer mes;
    private Integer ano;

    private Integer totalIndicadores;
    private Integer totalAguaCaptada;
    private Integer totalAguaTratada;
    private Integer totalVolumeAguaDistribuida;

    public DadosDashboardDTO() {
    }

    public Integer getSistemaAguaExistentes() {
        return sistemaAguaExistentes;
    }

    public void setSistemaAguaExistentes(Integer sistemaAguaExistentes) {
        this.sistemaAguaExistentes = sistemaAguaExistentes;
    }

    public Integer getTotalCasasLigadas() {
        return totalCasasLigadas;
    }

    public void setTotalCasasLigadas(Integer totalCasasLigadas) {
        this.totalCasasLigadas = totalCasasLigadas;
    }

    public Integer getTotalPessoasAceesoAgua() {
        return totalPessoasAceesoAgua;
    }

    public void setTotalPessoasAceesoAgua(Integer totalPessoasAceesoAgua) {
        this.totalPessoasAceesoAgua = totalPessoasAceesoAgua;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getTotalIndicadores() {
        return totalIndicadores;
    }

    public void setTotalIndicadores(Integer totalIndicadores) {
        this.totalIndicadores = totalIndicadores;
    }

    public Integer getTotalAguaCaptada() {
        return totalAguaCaptada;
    }

    public void setTotalAguaCaptada(Integer totalAguaCaptada) {
        this.totalAguaCaptada = totalAguaCaptada;
    }

    public Integer getTotalAguaTratada() {
        return totalAguaTratada;
    }

    public void setTotalAguaTratada(Integer totalAguaTratada) {
        this.totalAguaTratada = totalAguaTratada;
    }

    public Integer getTotalVolumeAguaDistribuida() {
        return totalVolumeAguaDistribuida;
    }

    public void setTotalVolumeAguaDistribuida(Integer totalVolumeAguaDistribuida) {
        this.totalVolumeAguaDistribuida = totalVolumeAguaDistribuida;
    }
}
