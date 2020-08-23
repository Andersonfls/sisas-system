package com.minea.sisas.service.dto;

import com.minea.sisas.config.Constants;

import com.minea.sisas.domain.*;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO representing a user, with his authorities.
 */
public class UserDTO {

    private Long id;

    @NotBlank
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String login;

    private String nome;

    private String enderecoCep;

    private String enderecoLogadouro;

    private Integer enderecoLote;

    private String enderecoComplemento;

    private String enderecoBairro;

    private String enderecoCidade;

    private String enderecoUf;

    private String rfAssociado;

    private String brAssociado;

    private String qrAssociado;

    private String nfAssociado;

    private Boolean perfilAdm;

    private Boolean modConfig;

    private Boolean modBalcao;

    private Boolean modToten;

    private Boolean modMobile;

    private String celular;

    private String telefone;

    @Email
    @Size(min = 5, max = 100)
    private String email;

    private Provincia provincia;

    private Municipio municipio;

    private Comuna comuna;

    @Size(max = 256)
    private String imageUrl;

    private boolean activated = false;

    @Size(min = 2, max = 6)
    private String langKey;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    private Set<String> authorities;

    public UserDTO() {
        // Empty constructor needed for Jackson.
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.nome = user.getNome();
        this.enderecoCep = user.getEnderecoCep();
        this.enderecoLogadouro = user.getEnderecoLogadouro();
        this.enderecoLote = user.getEnderecoLote();
        this.enderecoComplemento = user.getEnderecoComplemento();
        this.enderecoBairro = user.getEnderocoBairro();
        this.enderecoCidade = user.getEnderecoCidade();
        this.rfAssociado = user.getRfAssociado();
        this.brAssociado = user.getBrAssociado();
        this.qrAssociado = user.getQrAssociado();
        this.nfAssociado = user.getNfAssociado();
        this.perfilAdm = user.getPerfilAdm();
        this.modConfig = user.getModConfig();
        this.modBalcao = user.getModBalcao();
        this.modToten = user.getModToten();
        this.modMobile = user.getModMobile();
        this.enderecoUf = user.getEnderecoUf();
        this.celular = user.getCelular();
        this.telefone = user.getTelefone();
        this.email = user.getEmail();
        this.provincia = user.getProvincia();
        this.municipio = user.getMunicipio();
        this.comuna = user.getComuna();
        this.activated = user.getActivated();
        this.imageUrl = user.getImageUrl();
        this.langKey = user.getLangKey();
        this.createdBy = user.getCreatedBy();
        this.createdDate = user.getCreatedDate();
        this.lastModifiedBy = user.getLastModifiedBy();
        this.lastModifiedDate = user.getLastModifiedDate();
        this.authorities = user.getAuthorities().stream()
            .map(Authority::getName)
            .collect(Collectors.toSet());
    }

    /*-----------Id Usuario------------------*/
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    /*----------------------------------------*/

    /*----------------------Login---------------------------*/
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    /*------------------------------------------------------*/

    /*-------------------------Nome-------------------------------*/
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    /*----------------------------------------------------------------------*/

    /*------------------------------------Cep---------------------------------------*/
    public String getEnderecoCep() {
        return enderecoCep;
    }
    public void setEnderecoCep(String enderecoCep) {
        this.enderecoCep = enderecoCep;
    }
    /*------------------------------------------------------------------------------*/

    public String getEnderecoLogadouro() {
        return enderecoLogadouro;
    }
    public void setEnderecoLogadouro(String enderecoLogadouro) {
        this.enderecoLogadouro = enderecoLogadouro;
    }
    /*------------------------------------------------------------------------------*/

    /*--------------------------------------enderecoLote---------------------------------*/
    public Integer getEnderecoLote() {
        return enderecoLote;
    }
    public void setEnderecoLote(Integer enderecoLote) {
        this.enderecoLote = enderecoLote;
    }
    /*-----------------------------------------------------------------------------------*/

    /*-------------------------------------------------EnderecoComplemento------------------------------------------*/
    public String getEnderecoComplemento() {
        return enderecoComplemento;
    }
    public void setEnderecoComplemento(String enderecoComplemento) {
        this.enderecoComplemento = enderecoComplemento;
    }
    /*--------------------------------------------------------------------------------------------------------------*/

    /*----------------------------------EnderecoBairro------------------------------------------*/
    public String getEnderecoBairro() {
        return enderecoBairro;
    }
    public void setEnderecoBairro(String enderecoBairro) {
        this.enderecoBairro = enderecoBairro;
    }
    /*------------------------------------------------------------------------------------------*/

    /*-----------------------------------EnderecoCidade-----------------------------------------*/
    public String getEnderecoCidade() {
        return enderecoCidade;
    }
    public void setEnderecoCidade(String enderecoCidade) {
        this.enderecoCidade = enderecoCidade;
    }
    /*------------------------------------------------------------------------------------------*/

    /*-------------------------EnderecoUf---------------------------------------*/
    public String getEnderecoUf() {
        return enderecoUf;
    }
    public void setEnderecoUf(String enderecoUf) {
        this.enderecoUf = enderecoUf;
    }
    /*--------------------------------------------------------------------------*/

    /*-------------------------------RfAssociado------------------------------------*/
    public String getRfAssociado() {
        return rfAssociado;
    }
    public void setRfAssociado(String rfAssociado) {
        this.rfAssociado = rfAssociado;
    }
    /*------------------------------------------------------------------------------*/

    /*-------------------------------BrAssociado------------------------------------*/
    public String getBrAssociado() {
        return brAssociado;
    }
    public void setBrAssociado(String brAssociado) {
        this.brAssociado = brAssociado;
    }
    /*------------------------------------------------------------------------------*/

    /*------------------------------QrAssociado-------------------------------------*/
    public String getQrAssociado() {
        return qrAssociado;
    }
    public void setQrAssociado(String qrAssociado) {
        this.qrAssociado = qrAssociado;
    }
    /*------------------------------------------------------------------------------*/

    /*-----------------------------NfAssociado--------------------------------------*/
    public String getNfAssociado() {
        return nfAssociado;
    }
    public void setNfAssociado(String nfAssociado) {
        this.nfAssociado = nfAssociado;
    }
    /*------------------------------------------------------------------------------*/

    /*----------------------------PerfilAdm----------------------------------*/
    public Boolean getPerfilAdm() {
        return perfilAdm;
    }
    public void setPerfilAdm(Boolean perfilAdm) {
        this.perfilAdm = perfilAdm;
    }
    /*-----------------------------------------------------------------------*/

    /*-------------------------------ModConfig-------------------------------*/
    public Boolean getModConfig() {
        return modConfig;
    }
    public void setModConfig(Boolean modConfig) {
        this.modConfig = modConfig;
    }
    /*-----------------------------------------------------------------------*/

    /*------------------------------ModBalcao--------------------------------*/
    public Boolean getModBalcao() {
        return modBalcao;
    }
    public void setModBalcao(Boolean modBalcao) {
        this.modBalcao = modBalcao;
    }
    /*-----------------------------------------------------------------------*/

    /*----------------------------ModToten-------------------------------*/
    public Boolean getModToten() {
        return modToten;
    }
    public void setModToten(Boolean modToten) {
        this.modToten = modToten;
    }
    /*-------------------------------------------------------------------*/

    /*-----------------------------ModMobile---------------------------------*/
    public Boolean getModMobile() {
        return modMobile;
    }
    public void setModMobile(Boolean modMobile) {
        this.modMobile = modMobile;
    }
    /*-----------------------------------------------------------------------*/

    /*---------------------------Celular----------------------------*/
    public String getCelular() {
        return celular;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }
    /*--------------------------------------------------------------*/

    /*-----------------------------Telefone-----------------------------*/
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    /*------------------------------------------------------------------*/

    /*---------------------------Email----------------------*/
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    /*------------------------------------------------------*/

    /*---------------------------Provincia----------------------*/
    public Provincia getProvincia() {
        return provincia;
    }
    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
    /*---------------------------Provincia--------------------------*/

    /*---------------------------Municipio----------------------*/
    public Municipio getMunicipio() {
        return municipio;
    }
    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
    /*---------------------------Municipio--------------------------*/

    /*---------------------------Comuna----------------------*/
    public Comuna getComuna() {
        return comuna;
    }
    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
    }
    /*---------------------------Comuna----------------------*/

    /*--------------------------ImageURl--------------------------------*/
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    /*------------------------------------------------------------------*/

    /*-----------------------------Activated---------------------------------*/
    public boolean isActivated() {
        return activated;
    }
    public void setActivated(boolean activated) {
        this.activated = activated;
    }
    /*-----------------------------------------------------------------------*/

    /*-----------------------------LangKey--------------------------*/
    public String getLangKey() {
        return langKey;
    }
    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }
    /*--------------------------------------------------------------*/

    /*--------------------------------CreateBy------------------------------*/
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    /*----------------------------------------------------------------------*/

    /*----------------------------------------CreateDate-----------------------------*/
    public Instant getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }
    /*-------------------------------------------------------------------------------*/

    /*-----------------------------------LasModifiedBy------------------------------------------*/
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
    /*------------------------------------------------------------------------------------------*/

    /*---------------------------------------LastModifiedDate--------------------------------------------*/
    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }
    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
    /*---------------------------------------------------------------------------------------------------*/

    /*----------------------------------Authorities--------------------------------------*/
    public Set<String> getAuthorities() {
        return authorities;
    }
    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }
    /*-----------------------------------------------------------------------------------*/

    @Override
    public String toString() {
        return "UserDTO{" +
            "login='" + login + '\'' +
            ", id='" + id + '\'' +
            ", nome='" + nome + '\'' +
            ", enderecoCep='" + enderecoCep + '\'' +
            ", enderecoLogadouro'" + enderecoLogadouro + '\'' +
            ", enderecoLote'" + enderecoLote + '\'' +
            ", enderecoComplemento'" + enderecoComplemento + '\'' +
            ", enderecoBairro'" + enderecoBairro + '\'' +
            ", enderecoCidade'" + enderecoCidade + '\'' +
            ", enderecoUf'" + enderecoUf + '\'' +
            ", rfAssociado'" + rfAssociado + '\'' +
            ", brAssociado'" + brAssociado + '\'' +
            ", qrAsssociado'" + qrAssociado + '\'' +
            ", nfAssociado'" + nfAssociado + '\'' +
            ", perfilAdm'" + perfilAdm + '\'' +
            ", modConfig'" + modConfig + '\'' +
            ", modBalcao'" + modBalcao + '\'' +
            ", modToten'" + modToten + '\'' +
            ", modMobiela'" + modMobile + '\'' +
            ", celular'" + celular + '\'' +
            ", telefone'" + telefone + '\'' +
            ", email='" + email + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            ", activated=" + activated +
            ", langKey='" + langKey + '\'' +
            ", createdBy=" + createdBy +
            ", createdDate=" + createdDate +
            ", lastModifiedBy='" + lastModifiedBy + '\'' +
            ", lastModifiedDate=" + lastModifiedDate +
            ", authorities=" + authorities +
            "}";
    }
}
