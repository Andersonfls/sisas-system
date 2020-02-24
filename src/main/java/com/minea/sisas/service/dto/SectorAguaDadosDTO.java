package com.minea.sisas.service.dto;


import java.io.Serializable;

/**
 * A DTO for the Projectos entity.
 */
public class SectorAguaDadosDTO implements Serializable {

    private String nomeProvincia;
    private Integer municipios;
    private String nomeMunicipio;
    private Integer comunas;
    private String nomeComuna;
    private Integer sistemasFuncionam;
    private Long populacaoTotal;
    private Long beneficiarios;
    private Float cobertura;

    public SectorAguaDadosDTO() {
    }

    public SectorAguaDadosDTO(String nomeProvincia, Integer municipios, Integer comunas, Integer sistemasFuncionam, Long populacaoTotal, Long beneficiarios, Float cobertura) {
        this.nomeProvincia = nomeProvincia;
        this.municipios = municipios;
        this.comunas = comunas;
        this.sistemasFuncionam = sistemasFuncionam;
        this.populacaoTotal = populacaoTotal;
        this.beneficiarios = beneficiarios;
        this.cobertura = cobertura;
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

    public Integer getSistemasFuncionam() {
        return sistemasFuncionam;
    }

    public void setSistemasFuncionam(Integer sistemasFuncionam) {
        this.sistemasFuncionam = sistemasFuncionam;
    }

    public Long getPopulacaoTotal() {
        return populacaoTotal;
    }

    public void setPopulacaoTotal(Long populacaoTotal) {
        this.populacaoTotal = populacaoTotal;
    }

    public Long getBeneficiarios() {
        return beneficiarios;
    }

    public void setBeneficiarios(Long beneficiarios) {
        this.beneficiarios = beneficiarios;
    }

    public Float getCobertura() {
        return cobertura;
    }

    public void setCobertura(Float cobertura) {
        this.cobertura = cobertura;
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
}
