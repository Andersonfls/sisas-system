package com.minea.sisas.service.dto;


import java.io.Serializable;

/**
 * A DTO for the Projectos entity.
 */
public class InqueritosPreenchidosDadosDTO implements Serializable {

    private String nomeProvincia;
    private Integer municipios;
    private Integer comunas;
    private Integer aguasSim;
    private Integer aguasNao;
    private Integer totalAguas;
    private Integer saneamentoSim;
    private Integer saneamentoNao;
    private Integer totalSaneamento;
    private Integer totalInqueritoSector;

    // campos Inquerito 2
    private Integer saneamento;
    private Integer escolas;
    private Integer hospitais;
    private Integer familias;
    private Integer totalGeral;


    public InqueritosPreenchidosDadosDTO() {
    }

    public InqueritosPreenchidosDadosDTO(String nomeProvincia, Integer municipios, Integer comunas, Integer aguasSim, Integer aguasNao, Integer totalAguas, Integer saneamentoSim, Integer saneamentoNao, Integer totalSaneamento, Integer totalInqueritoSector, Integer saneamento, Integer escolas, Integer hospitais, Integer familias, Integer totalGeral) {
        this.nomeProvincia = nomeProvincia;
        this.municipios = municipios;
        this.comunas = comunas;
        this.aguasSim = aguasSim;
        this.aguasNao = aguasNao;
        this.totalAguas = totalAguas;
        this.saneamentoSim = saneamentoSim;
        this.saneamentoNao = saneamentoNao;
        this.totalSaneamento = totalSaneamento;
        this.totalInqueritoSector = totalInqueritoSector;
        this.saneamento = saneamento;
        this.escolas = escolas;
        this.hospitais = hospitais;
        this.familias = familias;
        this.totalGeral = totalGeral;
    }

    public String getNomeProvincia() {
        return nomeProvincia;
    }

    public void setNomeProvincia(String nomeProvincia) {
        this.nomeProvincia = nomeProvincia;
    }

    public Integer getMunicipios() {
        return municipios;
    }

    public void setMunicipios(Integer municipios) {
        this.municipios = municipios;
    }

    public Integer getComunas() {
        return comunas;
    }

    public void setComunas(Integer comunas) {
        this.comunas = comunas;
    }

    public Integer getAguasSim() {
        return aguasSim;
    }

    public void setAguasSim(Integer aguasSim) {
        this.aguasSim = aguasSim;
    }

    public Integer getAguasNao() {
        return aguasNao;
    }

    public void setAguasNao(Integer aguasNao) {
        this.aguasNao = aguasNao;
    }

    public Integer getTotalAguas() {
        return totalAguas;
    }

    public void setTotalAguas(Integer totalAguas) {
        this.totalAguas = totalAguas;
    }

    public Integer getSaneamentoSim() {
        return saneamentoSim;
    }

    public void setSaneamentoSim(Integer saneamentoSim) {
        this.saneamentoSim = saneamentoSim;
    }

    public Integer getSaneamentoNao() {
        return saneamentoNao;
    }

    public void setSaneamentoNao(Integer saneamentoNao) {
        this.saneamentoNao = saneamentoNao;
    }

    public Integer getTotalSaneamento() {
        return totalSaneamento;
    }

    public void setTotalSaneamento(Integer totalSaneamento) {
        this.totalSaneamento = totalSaneamento;
    }

    public Integer getTotalInqueritoSector() {
        return totalInqueritoSector;
    }

    public void setTotalInqueritoSector(Integer totalInqueritoSector) {
        this.totalInqueritoSector = totalInqueritoSector;
    }

    public Integer getSaneamento() {
        return saneamento;
    }

    public void setSaneamento(Integer saneamento) {
        this.saneamento = saneamento;
    }

    public Integer getEscolas() {
        return escolas;
    }

    public void setEscolas(Integer escolas) {
        this.escolas = escolas;
    }

    public Integer getHospitais() {
        return hospitais;
    }

    public void setHospitais(Integer hospitais) {
        this.hospitais = hospitais;
    }

    public Integer getFamilias() {
        return familias;
    }

    public void setFamilias(Integer familias) {
        this.familias = familias;
    }

    public Integer getTotalGeral() {
        return totalGeral;
    }

    public void setTotalGeral(Integer totalGeral) {
        this.totalGeral = totalGeral;
    }
}
