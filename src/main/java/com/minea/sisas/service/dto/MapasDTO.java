package com.minea.sisas.service.dto;

public class MapasDTO {

    private Long idProvincia;
    private Long idMunicipio;
    private Long idComuna;
    private Integer beneficiariosSistemaAgua;
    private Float porcentagemCobertura;
    private Float porcentagemFuncionam;
    private Float porcentagemNaoFuncionam;

    public MapasDTO() {
    }

    public Long getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Long idProvincia) {
        this.idProvincia = idProvincia;
    }

    public Long getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Long idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Long getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(Long idComuna) {
        this.idComuna = idComuna;
    }

    public Integer getBeneficiariosSistemaAgua() {
        return beneficiariosSistemaAgua;
    }

    public void setBeneficiariosSistemaAgua(Integer beneficiariosSistemaAgua) {
        this.beneficiariosSistemaAgua = beneficiariosSistemaAgua;
    }

    public Float getPorcentagemCobertura() {
        return porcentagemCobertura;
    }

    public void setPorcentagemCobertura(Float porcentagemCobertura) {
        this.porcentagemCobertura = porcentagemCobertura;
    }

    public Float getPorcentagemFuncionam() {
        return porcentagemFuncionam;
    }

    public void setPorcentagemFuncionam(Float porcentagemFuncionam) {
        this.porcentagemFuncionam = porcentagemFuncionam;
    }

    public Float getPorcentagemNaoFuncionam() {
        return porcentagemNaoFuncionam;
    }

    public void setPorcentagemNaoFuncionam(Float porcentagemNaoFuncionam) {
        this.porcentagemNaoFuncionam = porcentagemNaoFuncionam;
    }
}
