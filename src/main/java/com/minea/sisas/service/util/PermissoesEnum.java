package com.minea.sisas.service.util;

import java.util.List;
import java.util.Objects;

public enum PermissoesEnum {

    ADMINISTRADOR_GERAL("ROLE_ADMIN", null),
    ADMINISTRADOR_CABINDA("ADM_CABINDA", 1l),
    ADMINISTRADOR_ZAIRE("ADM_ZAIRE", 2l),
    ADMINISTRADOR_UIGE("ADM_UIGE", 3l),
    ADMINISTRADOR_LUANDA("ADM_LUANDA", 4l),
    ADMINISTRADOR_CUANZA_NORTE("ADM_CUANZA_NORTE", 5l),
    ADMINISTRADOR_CUANZA_SUL("ADM_CUANZA_SUL", 6l),
    ADMINISTRADOR_MALANJE("ADM_MALANJE", 7l),
    ADMINISTRADOR_LUNDA_NORTE("ADM_LUNDA_NORTE", 8l),
    ADMINISTRADOR_BENGUELA("ADM_BENGUELA", 9l),
    ADMINISTRADOR_HUAMBO("ADM_HUAMBO", 10l),
    ADMINISTRADOR_BIE("ADM_BIE", 11l),
    ADMINISTRADOR_MOXICO("ADM_MOXICO", 12l),
    ADMINISTRADOR_CUANDO_CUBANGO("ADM_CUANDO_CUBANGO", 13l),
    ADMINISTRADOR_NANIBE("ADM_NANIBE", 14l),
    ADMINISTRADOR_HUILA("ADM_HUILA", 15l),
    ADMINISTRADOR_CUNENE("ADM_CUNENE", 16l),
    ADMINISTRADOR_LUNDA_SUL("ADM_LUNDA_SUL", 17l),
    ADMINISTRADOR_BENGO("ADM_BENGO", 18l);

    String nomePermissao;
    Long idProvincia;

    PermissoesEnum(String nomePermissao, Long idProvincia) {
        this.nomePermissao = nomePermissao;
        this.idProvincia = idProvincia;
    }

    public boolean isAdministrador(List<String> itens) {
        if (Objects.nonNull(itens)) {
            for (String i: itens) {
                if (i.equals(ADMINISTRADOR_GERAL.nomePermissao)) {
                    return true;
                }
            }
        }
        return false;
    }

    public PermissoesEnum getPermissao(String item){
        PermissoesEnum[] permissoes = PermissoesEnum.values();

        for (PermissoesEnum permissao : permissoes){
            if (permissao.nomePermissao.equals(item)) {
                return permissao;
            }
        }
        return null;
        }

    public String getNomePermissao() {
        return nomePermissao;
    }

    public Long getIdProvincia() {
        return idProvincia;
    }
}
