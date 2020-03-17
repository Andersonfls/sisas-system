package com.minea.sisas.service;

import com.minea.sisas.domain.Comuna;
import com.minea.sisas.repository.ComunaRepository;
import com.minea.sisas.service.dto.ComunaDTO;
import com.minea.sisas.service.dto.UserDTO;
import com.minea.sisas.service.mapper.ComunaMapper;
import com.minea.sisas.service.util.PermissoesEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;


/**
 * Service Implementation for managing Comuna.
 */
@Service
@Transactional
public class ComunaService {

    private final Logger log = LoggerFactory.getLogger(ComunaService.class);

    private final ComunaRepository comunaRepository;

    private final ComunaMapper comunaMapper;

    @Autowired
    private UserService userService;

    public ComunaService(ComunaRepository comunaRepository, ComunaMapper comunaMapper, UserService userService) {
        this.comunaRepository = comunaRepository;
        this.comunaMapper = comunaMapper;
        this.userService = userService;
    }

    /**
     * Save a comuna.
     *
     * @param comunaDTO the entity to save
     * @return the persisted entity
     */
    public ComunaDTO save(ComunaDTO comunaDTO) {
        log.debug("Request to save Comuna : {}", comunaDTO);
        Comuna comuna = comunaMapper.toEntity(comunaDTO);
        comuna = comunaRepository.save(comuna);
        return comunaMapper.toDto(comuna);
    }

    /**
     * Get all the comunas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ComunaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Comunas");
        Page<ComunaDTO> retorno = null;
        UserDTO userDTO = this.userService.getUserWithAuthorities().map(UserDTO::new).orElse(null);

        if (Objects.nonNull(userDTO)) {
            if (isAdministradorOrUsuserProvincial(userDTO.getAuthorities())) {
                retorno =comunaRepository.findAllByMunicipioIdPg(userDTO.getMunicipio().getId(),pageable)
                    .map(comunaMapper::toDto);
            } else {
                retorno = comunaRepository.findAll(pageable)
                    .map(comunaMapper::toDto);
            }
        }
        return retorno;
    }

    private boolean isAdministradorOrUsuserProvincial(Set<String> permissoes ){
        for (String permisao: permissoes) {
            if (permisao.equals("ADMIN_PROVINCIAL") || permisao.equals("USUARIO_PROVINCIAL")) {
                return true;
            }
        }
        return false;
    }

    public boolean isAdministrador(Set<String> itens) {
        if (Objects.nonNull(itens)) {
            for (String i: itens) {
                if (i.equals(PermissoesEnum.ADMINISTRADOR_GERAL.getNomePermissao())) {
                    return true;
                }
            }
        }
        return false;
    }

    public PermissoesEnum getPermissao(String item){
        PermissoesEnum[] permissoes = PermissoesEnum.values();

        for (PermissoesEnum permissao : permissoes){
            if (permissao.getNomePermissao().equals(item)) {
                return permissao;
            }
        }
        return null;
    }

    /**
     * Get one comuna by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Comuna findOne(Long id) {
        log.debug("Request to get Comuna : {}", id);
        return comunaRepository.findOne(id);
    }

    /**
     * Delete the comuna by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Comuna : {}", id);
        comunaRepository.delete(id);
    }
}
