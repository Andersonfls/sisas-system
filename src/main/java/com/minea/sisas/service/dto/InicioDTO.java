package com.minea.sisas.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Inicio entity.
 */
public class InicioDTO implements Serializable {

    private Long id;

    @NotNull
    private Long destaques;

    @NotNull
    private Long ultimasNoticias;

    @NotNull
    private Long publicacoes;

    @NotNull
    @Size(max = 500)
    private String url;

    @NotNull
    @Size(max = 150)
    private String alt;

    private Long idSituacaoId;

    private Long idSobreDnaId;

    private Long idNoticiasId;

    private Long idProjectosId;

    private Long idPublicacaoId;

    private Long idContactosId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDestaques() {
        return destaques;
    }

    public void setDestaques(Long destaques) {
        this.destaques = destaques;
    }

    public Long getUltimasNoticias() {
        return ultimasNoticias;
    }

    public void setUltimasNoticias(Long ultimasNoticias) {
        this.ultimasNoticias = ultimasNoticias;
    }

    public Long getPublicacoes() {
        return publicacoes;
    }

    public void setPublicacoes(Long publicacoes) {
        this.publicacoes = publicacoes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Long getIdSituacaoId() {
        return idSituacaoId;
    }

    public void setIdSituacaoId(Long situacaoId) {
        this.idSituacaoId = situacaoId;
    }

    public Long getIdSobreDnaId() {
        return idSobreDnaId;
    }

    public void setIdSobreDnaId(Long sobreDnaId) {
        this.idSobreDnaId = sobreDnaId;
    }

    public Long getIdNoticiasId() {
        return idNoticiasId;
    }

    public void setIdNoticiasId(Long noticiasId) {
        this.idNoticiasId = noticiasId;
    }

    public Long getIdProjectosId() {
        return idProjectosId;
    }

    public void setIdProjectosId(Long projectosId) {
        this.idProjectosId = projectosId;
    }

    public Long getIdPublicacaoId() {
        return idPublicacaoId;
    }

    public void setIdPublicacaoId(Long publicacaoId) {
        this.idPublicacaoId = publicacaoId;
    }

    public Long getIdContactosId() {
        return idContactosId;
    }

    public void setIdContactosId(Long contactosId) {
        this.idContactosId = contactosId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InicioDTO inicioDTO = (InicioDTO) o;
        if(inicioDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), inicioDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InicioDTO{" +
            "id=" + getId() +
            ", destaques=" + getDestaques() +
            ", ultimasNoticias=" + getUltimasNoticias() +
            ", publicacoes=" + getPublicacoes() +
            ", url='" + getUrl() + "'" +
            ", alt='" + getAlt() + "'" +
            "}";
    }
}
