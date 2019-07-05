package com.minea.sisas.domain;

import com.minea.sisas.config.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.time.Instant;

/**
 * A user.
 */
@Entity
@Table(name = "jhi_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    private String login;

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60)
    private String password;

    @Column(name = "nome")
    private String nome;

    @Column(name = "endereco_cep")
    private String enderecoCep;

    @Column(name = "endereco_logadouro")
    private String enderecoLogadouro;

    @Column(name = "endereco_lote")
    private Integer enderecoLote;

    @Column(name = "endereco_complemento")
    private String enderecoComplemento;

    @Column(name = "endereco_bairro")
    private String enderecoBairro;

    @Column(name = "endereco_cidade")
    private String enderecoCidade;

    @Column(name = "endereco_uf")
    private String enderecoUf;

    @Column(name = "rf_associado")
    private String rfAssociado;

    @Column(name = "br_associado")
    private String brAssociado;

    @Column(name = "qr_associado")
    private String qrAssociado;

    @Column(name = "nf_associado")
    private String nfAssociado;

    @Column(name = "st_perfil_administrador")
    private Boolean perfilAdm;

    @Column(name = "st_mod_config")
    private Boolean modConfig;

    @Column(name = "st_mod_balcao")
    private Boolean modBalcao;

    @Column(name = "st_mod_toten")
    private Boolean modToten;

    @Column(name = "st_mod_mobile")
    private Boolean modMobile;

    @Column(name = "celular")
    private String celular;

    @Column(name = "telefone")
    private String telefone;

    @Email
    @Size(min = 5, max = 100)
    @Column(length = 100, unique = true)
    private String email;

    @NotNull
    @Column(nullable = false)
    private boolean activated = false;

    @Size(min = 2, max = 6)
    @Column(name = "lang_key", length = 6)
    private String langKey;

    @Size(max = 256)
    @Column(name = "image_url", length = 256)
    private String imageUrl;

    @Size(max = 20)
    @Column(name = "activation_key", length = 20)
    @JsonIgnore
    private String activationKey;

    @Size(max = 20)
    @Column(name = "reset_key", length = 20)
    @JsonIgnore
    private String resetKey;

    @Column(name = "reset_date")
    private Instant resetDate = null;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "jhi_user_authority",
        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    @BatchSize(size = 20)
    private Set<Authority> authorities = new HashSet<>();

    /*---------------------ID-----------------*/
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    /*----------------------------------------*/

    /*------------------------------------------Login----------------------------------------------*/
    public String getLogin() {
        return login;
    }
    // Lowercase the login before saving it in database
    public void setLogin(String login) {
        this.login = StringUtils.lowerCase(login, Locale.ENGLISH);
    }
    /*---------------------------------------------------------------------------------------------*/

    /*----------------------------Senha--------*/
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    /*------------------------------------------------------------------*/

    /*--------------------Nome------------------------*/
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    /*------------------------------------------------------------------*/

    /*-------------------------------CEP--------------------------------------------*/
    public String getEnderecoCep() {
        return enderecoCep;
    }
    public void setEnderecoCep(String enderecoCep) {
        this.enderecoCep = enderecoCep;
    }
    /*------------------------------------------------------------------------------*/

    /*------------------------------------------------LOGADOURO---------------------------------------------*/
    public String getEnderecoLogadouro() {
        return enderecoLogadouro;
    }
    public void setEnderecoLogadouro(String enderecoLogadouro) {
        this.enderecoLogadouro = enderecoLogadouro;
    }
    /*------------------------------------------------------------------------------------------------------*/

    /*--------------------------------LOTE-----------------------------------------------*/
    public Integer getEnderecoLote() {
        return enderecoLote;
    }
    public void setEnderecoLote(Integer enderecoLote) {
        this.enderecoLote = enderecoLote;
    }
    /*-----------------------------------------------------------------------------------*/

    /*--------------------------------------------COMPLEMENTO-------------------------------------------------------*/
    public String getEnderecoComplemento() {
        return enderecoComplemento;
    }
    public void setEnderecoComplemento(String enderecoComplemento) {
        this.enderecoComplemento = enderecoComplemento;
    }
    /*--------------------------------------------------------------------------------------------------------------*/

    /*-----------------------------------BAIRRO-------------------------------------------------*/
    public String getEnderocoBairro() {
        return enderecoBairro;
    }
    public void setEnderocoBairro(String enderecoBairro) {
        this.enderecoBairro = enderecoBairro;
    }
    /*------------------------------------------------------------------------------------------*/

    /*--------CIDADE-----*/
    public String getEnderecoCidade() {
        return enderecoCidade;
    }
    public void setEnderecoCidade(String enderecoCidade) {
        this.enderecoCidade = enderecoCidade;
    }
    /*------------------------------------------------------------------------------------------*/

    /*-----------------------------------------UF-------------------------------*/
    public String getEnderecoUf() {
        return enderecoUf;
    }
    public void setEnderecoUf(String enderecoUf) {
        this.enderecoUf = enderecoUf;
    }
    /*--------------------------------------------------------------------------*/

    /*----------------------------------RF------------------------------------------*/
    public String getRfAssociado() {
        return rfAssociado;
    }
    public void setRfAssociado(String rfAssociado) {
        this.rfAssociado = rfAssociado;
    }
    /*------------------------------------------------------------------------------*/

    /*-------------------------------------BR---------------------------------------*/
    public String getBrAssociado() {
        return brAssociado;
    }
    public void setBrAssociado(String brAssociado) {
        this.brAssociado = brAssociado;
    }
    /*------------------------------------------------------------------------------*/

    /*-----------------------------QR-----------------------------------------------*/
    public String getQrAssociado() {
        return qrAssociado;
    }
    public void setQrAssociado(String qrAssociado) {
        this.qrAssociado = qrAssociado;
    }
    /*------------------------------------------------------------------------------*/

    /*--------------------------------NF--------------------------------------------*/
    public String getNfAssociado() {
        return nfAssociado;
    }
    public void setNfAssociado(String nfAssociado) {
        this.nfAssociado = nfAssociado;
    }
    /*------------------------------------------------------------------------------*/

    /*------------------------Email-------------------------*/
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    /*------------------------------------------------------*/

    /*----------------------------ImageUrl------------------------------*/
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    /*------------------------------------------------------------------*/

    /*--------------------------------Activated------------------------------*/
    public boolean getActivated() {
        return activated;
    }
    public void setActivated(boolean activated) {
        this.activated = activated;
    }
    /*-----------------------------------------------------------------------*/

    /*-------------------------------Activation Key-----------------------------------------*/
    public String getActivationKey() {
        return activationKey;
    }
    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }
    /*--------------------------------------------------------------------------------------*/

    /*-------------------------------Reset Key--------------------------*/
    public String getResetKey() {
        return resetKey;
    }
    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }
    /*------------------------------------------------------------------*/

    /*--------------------------------Reset Date-----------------------------*/
    public Instant getResetDate() {
        return resetDate;
    }
    public void setResetDate(Instant resetDate) {
        this.resetDate = resetDate;
    }
    /*-----------------------------------------------------------------------*/

    /*---------------------------LangKey----------------------------*/
    public String getLangKey() {
        return langKey;
    }
    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }
    /*--------------------------------------------------------------*/


    /*--------------------------------Authority---------------------------------------------*/
    public Set<Authority> getAuthorities() {
        return authorities;
    }
    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
    /*--------------------------------------------------------------------------------------*/

    /*-------------------------------Perfil Adm------------------------------*/
    public Boolean getPerfilAdm() {
        return perfilAdm;
    }
    public void setPerfilAdm(Boolean perfilAdm) {
        this.perfilAdm = perfilAdm;
    }
    /*-----------------------------------------------------------------------*/

    /*--------------------------Mod Config-----------------------------------*/
    public Boolean getModConfig() {
        return modConfig;
    }
    public void setModConfig(Boolean modConfig) {
        this.modConfig = modConfig;
    }
    /*-----------------------------------------------------------------------*/

    /*---------------------------Mod Balcão----------------------------------*/
    public Boolean getModBalcao() {
        return modBalcao;
    }
    public void setModBalcao(Boolean modBalcao) {
        this.modBalcao = modBalcao;
    }
    /*-----------------------------------------------------------------------*/

    /*-------------------------Mod Toten---------------------------------*/
    public Boolean getModToten() {
        return modToten;
    }
    public void setModToten(Boolean modToten) {
        this.modToten = modToten;
    }
    /*-------------------------------------------------------------------*/

    /*-------------------------------Mod Mobile------------------------------*/
    public Boolean getModMobile() {
        return modMobile;
    }
    public void setModMobile(Boolean modMobile) {
        this.modMobile = modMobile;
    }
    /*-----------------------------------------------------------------------*/

    /*-----------------------CELULAR--------------------------------*/
    public String getCelular() {
        return celular;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }
    /*--------------------------------------------------------------*/

    /*----------------------------TELEFONE------------------------------*/
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    /*------------------------------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;
        return !(user.getId() == null || getId() == null) && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "User{" +
            "login='" + login + '\'' +
            ", id='" + id + '\'' +
            ", nome='" + nome + '\'' +
            ", enderecoCep='" + enderecoCep + '\'' +
            ", enderecoLogadouro='" + enderecoCep + '\'' +
            ", enderecoLote='" + enderecoLote + '\'' +
            ", enderecoComplemento='" + enderecoComplemento + '\'' +
            ", enderecoBairro='" + enderecoBairro + '\'' +
            ", enderecoCidade='" + enderecoCidade + '\'' +
            ", enderecoUf='" + enderecoUf + '\'' +
            ", rfAssociado='" + rfAssociado + '\'' +
            ", brAssociado='" + brAssociado + '\'' +
            ", qrAssociado='" + qrAssociado + '\'' +
            ", nfAssociado='" + nfAssociado + '\'' +
            ", perfilAdm='" + perfilAdm + '\'' +
            ", modConfig='" + modConfig + '\'' +
            ", modBalcao='" + modBalcao + '\'' +
            ", modToten='" + modToten + '\'' +
            ", modMobile='" + modMobile + '\'' +
            ", telefone='" + telefone + '\'' +
            "' celular='" + celular + '\'' +
            ", email='" + email + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            ", activated='" + activated + '\'' +
            ", langKey='" + langKey + '\'' +
            ", activationKey='" + activationKey + '\'' +
            "}";
    }
}
