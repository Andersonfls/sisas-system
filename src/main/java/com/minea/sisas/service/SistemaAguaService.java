package com.minea.sisas.service;

import com.minea.sisas.domain.AvariaSistemaAgua;
import com.minea.sisas.domain.SistemaAgua;
import com.minea.sisas.domain.enumeration.TipoAcao;
import com.minea.sisas.repository.AvariaSistemaAguaRepository;
import com.minea.sisas.repository.SistemaAguaRepository;
import com.minea.sisas.repository.UserRepository;
import com.minea.sisas.security.SecurityUtils;
import com.minea.sisas.service.dto.SistemaAguaDTO;
import com.minea.sisas.service.dto.SistemaAguaLogDTO;
import com.minea.sisas.service.dto.UserDTO;
import com.minea.sisas.service.mapper.SistemaAguaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;


/**
 * Service Implementation for managing SistemaAgua.
 */
@Service
@Transactional
public class SistemaAguaService {

    private final Logger log = LoggerFactory.getLogger(SistemaAguaService.class);

    private final SistemaAguaRepository sistemaAguaRepository;

    private final SistemaAguaMapper sistemaAguaMapper;

    @Autowired
    private SistemaAguaLogService sistemaAguaLogService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AvariaSistemaAguaRepository avariaSistemaAguaRepository;

    public SistemaAguaService(SistemaAguaRepository sistemaAguaRepository, SistemaAguaMapper sistemaAguaMapper, SistemaAguaLogService sistemaAguaLogService,
                              UserRepository userRepository, UserService userService, AvariaSistemaAguaRepository avariaSistemaAguaRepository) {
        this.sistemaAguaRepository = sistemaAguaRepository;
        this.sistemaAguaMapper = sistemaAguaMapper;
        this.sistemaAguaLogService = sistemaAguaLogService;
        this.userRepository = userRepository;
        this.userService = userService;
        this.avariaSistemaAguaRepository = avariaSistemaAguaRepository;
    }

    /**
     * Save a sistemaAgua.
     *
     * @param sistemaAguaDTO the entity to save
     * @return the persisted entity
     */
    public SistemaAguaDTO save(SistemaAguaDTO sistemaAguaDTO) {
        log.debug("Request to save SistemaAgua : {}", sistemaAguaDTO);
        SistemaAgua sistemaAgua = sistemaAguaMapper.toEntity(sistemaAguaDTO);
        if(Objects.isNull(sistemaAgua.getIdUsuario()))
            sistemaAgua.setIdUsuario(SecurityUtils.getCurrentUserId());
        sistemaAgua.setDtUltimaAlteracao(LocalDate.now());
        sistemaAgua.setStatus(true);
        sistemaAgua = sistemaAguaRepository.save(sistemaAgua);
        saveAvariasSistema(sistemaAguaDTO.getAvariaSistemaAguas(), sistemaAgua.getId());

        if (Objects.nonNull(sistemaAguaDTO.getId())) {
            logSave(TipoAcao.ATUALIZACAO, sistemaAgua);
        } else {
            logSave(TipoAcao.INCLUSAO, sistemaAgua);
        }
        SistemaAguaDTO response = sistemaAguaMapper.toDto(sistemaAgua);
        response.setAvariaSistemaAguas(this.avariaSistemaAguaRepository.findAllBySistemAguaId(response.getId()));
        return response;
    }

    private void saveAvariasSistema(List<AvariaSistemaAgua> avarias,  Long idSistemaAgua) {
        List<AvariaSistemaAgua> avariasAntigas = this.avariaSistemaAguaRepository.findAllBySistemAguaId(idSistemaAgua);

        //ALL THE PREVIOUS AVARIAS DELETED
        if (Objects.nonNull(avariasAntigas)) {
            avariasAntigas.stream().forEach( av -> {
                this.avariaSistemaAguaRepository.delete(av.getId());
            });
        }

        //INSERTING THE NEW ONES
        if (Objects.nonNull(avarias)) {
            avarias.stream().forEach(item ->{
                item.setIdSistemaAgua(idSistemaAgua);
                this.avariaSistemaAguaRepository.save(item);
            });
        }
    }

    private void logSave(TipoAcao acao, SistemaAgua agua) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
             username = ((UserDetails)principal).getUsername();
        }

        SistemaAguaLogDTO dto = new SistemaAguaLogDTO();
        dto.setAcao(acao.getDescricao());
        dto.setDtLog(LocalDate.now());
        dto.setIdSistemaAguaId(agua);
        if (Objects.nonNull(username)) {
            dto.setIdUsuario(this.userRepository.buscarUserIdByUsername(username));
        }
        dto.setLog(acao.getLog());

        this.sistemaAguaLogService.save(dto);
    }

    /**
     * Get all the sistemaAguas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<SistemaAguaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SistemaAguas");
        Page<SistemaAguaDTO> retorno = null;
        UserDTO userDTO = this.userService.getUserWithAuthorities().map(UserDTO::new).orElse(null);

        if (Objects.nonNull(userDTO) ) {
            if (isAdministradorOrUsuserProvincial(userDTO.getAuthorities())) {
                retorno = sistemaAguaRepository.findAllByStatusIsTrueAndProvinciaId(userDTO.getProvincia().getId(), pageable)
                    .map(sistemaAguaMapper::toDto);
            } else {
                retorno = sistemaAguaRepository.findAllByStatusIsTrue(pageable)
                    .map(sistemaAguaMapper::toDto);
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
    /**
     * Get one sistemaAgua by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public SistemaAguaDTO findOne(Long id) {
        log.debug("Request to get SistemaAgua : {}", id);
        SistemaAgua sistemaAgua = sistemaAguaRepository.findOne(id);

        SistemaAguaDTO response = sistemaAguaMapper.toDto(sistemaAgua);
        response.setAvariaSistemaAguas(this.avariaSistemaAguaRepository.findAllBySistemAguaId(response.getId()));
        return response;
    }

    /**
     * Delete the sistemaAgua by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete SistemaAgua : {}", id);
        logSave(TipoAcao.REMOCAO, sistemaAguaRepository.findOne(id));
        sistemaAguaRepository.delete(id);
    }
}
