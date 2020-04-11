package com.minea.sisas.service;

import com.minea.sisas.domain.Authority;
import com.minea.sisas.domain.Provincia;
import com.minea.sisas.domain.User;
import com.minea.sisas.repository.ProvinciaRepository;
import com.minea.sisas.repository.UserRepository;
import com.minea.sisas.service.dto.CoberturaSectorAguaDTO;
import com.minea.sisas.service.dto.IndicadorProducaoProvinciaDTO;
import com.minea.sisas.service.dto.ProvinciaDTO;
import com.minea.sisas.service.mapper.ProvinciaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Service Implementation for managing Provincia.
 */
@Service
@Transactional
public class ProvinciaService {

    private final Logger log = LoggerFactory.getLogger(ProvinciaService.class);

    private final ProvinciaRepository provinciaRepository;

    private final ProvinciaMapper provinciaMapper;

    @Autowired
    private UserRepository userRepository;

    public ProvinciaService(ProvinciaRepository provinciaRepository, ProvinciaMapper provinciaMapper) {
        this.provinciaRepository = provinciaRepository;
        this.provinciaMapper = provinciaMapper;
    }

    private User buscaUsuarioLogado() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = null;
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }

        if (Objects.nonNull(username)) {
            user = this.userRepository.findByLoginEquals(username);
        }

        return user;
    }

    private boolean isAdminGeral(User user){
        for (Authority permissao: user.getAuthorities() ) {
            if (permissao.getName().equals("ROLE_ADMIN")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Save a provincia.
     *
     * @param provinciaDTO the entity to save
     * @return the persisted entity
     */
    public ProvinciaDTO save(ProvinciaDTO provinciaDTO) {
        log.debug("Request to save Provincia : {}", provinciaDTO);
        Provincia provincia = provinciaMapper.toEntity(provinciaDTO);
        provincia = provinciaRepository.save(provincia);
        return provinciaMapper.toDto(provincia);
    }

    /**
     * Get all the provincias.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ProvinciaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Provincias");
        return provinciaRepository.findAll(pageable)
            .map(provinciaMapper::toDto);
    }

    public Page<ProvinciaDTO> findAllPorNivel(Pageable pageable) {
        log.debug("Request to get all Provincias By user Permission");
        User user = buscaUsuarioLogado();
        if (isAdminGeral(user)){
            return provinciaRepository.findAll(pageable)
                .map(provinciaMapper::toDto);
        } else {
            return provinciaRepository.findAllByIdEquals(user.getProvincia().getId(), pageable)
                .map(provinciaMapper::toDto);
        }
    }


    /**
     * Get one provincia by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public ProvinciaDTO findOne(Long id) {
        log.debug("Request to get Provincia : {}", id);
        Provincia provincia = provinciaRepository.findOne(id);
        return provinciaMapper.toDto(provincia);
    }

    @Transactional(readOnly = true)
    public List<IndicadorProducaoProvinciaDTO> getNomeCampo(String ano) {
        log.debug("Request to get Ano : {}", ano);
//        IndicadorProducaoProvinciaDTO indicadorProducaoProvinciaDTO = provinciaRepository.getNomeCampo(ano);
//        if(Objects.nonNull(ano)) {
//            indicadorProducaoProvinciaDTO.setNomeCampo();
//        }

        return new ArrayList<>();
    }

    /**
     * Delete the provincia by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Provincia : {}", id);
        provinciaRepository.delete(id);
    }
}
