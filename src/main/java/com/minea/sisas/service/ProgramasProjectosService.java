package com.minea.sisas.service;

import com.minea.sisas.domain.*;
import com.minea.sisas.domain.enumeration.TipoAcao;
import com.minea.sisas.repository.ProgramasProjectosRepository;
import com.minea.sisas.repository.UserRepository;
import com.minea.sisas.security.SecurityUtils;
import com.minea.sisas.service.dto.ProgramasProjectosDTO;
import com.minea.sisas.service.dto.ProgramasProjectosLogDTO;
import com.minea.sisas.service.dto.UserDTO;
import com.minea.sisas.service.mapper.ProgramasProjectosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;


/**
 * Service Implementation for managing ProgramasProjectos.
 */
@Service
@Transactional
public class ProgramasProjectosService {

    private final Logger log = LoggerFactory.getLogger(ProgramasProjectosService.class);

    private final ProgramasProjectosRepository programasProjectosRepository;

    private final ProgramasProjectosMapper programasProjectosMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProgramasProjectosLogService programasProjectosLogService;

    @Autowired
    private UserService userService;

    @Autowired
    private ConcepcaoService concepcaoService;

    @Autowired
    private ConcursoService concursoService;

    @Autowired
    private AdjudicacaoService adjudicacaoService;

    @Autowired
    private ContratoService contratoService;

    @Autowired
    private EmpreitadaService empreitadaService;

    @Autowired
    private ExecucaoService execucaoService;

    public ProgramasProjectosService(ProgramasProjectosRepository programasProjectosRepository, ProgramasProjectosMapper programasProjectosMapper,
                                     ProgramasProjectosLogService programasProjectosLogService,  UserService userService) {
        this.programasProjectosRepository = programasProjectosRepository;
        this.programasProjectosMapper = programasProjectosMapper;
        this.programasProjectosLogService = programasProjectosLogService;
        this.userService = userService;
    }

    /**
     * Save a programasProjectos.
     *
     * @param programasProjectos the entity to save
     * @return the persisted entity
     */
    public ProgramasProjectos save(ProgramasProjectos programasProjectos) {
        log.debug("Request to save ProgramasProjectos : {}", programasProjectos);

        if (Objects.isNull(programasProjectos.getUsuario()) || Objects.isNull(programasProjectos.getUsuario().getId())) {
            programasProjectos.setUsuario(userRepository.findByIdEquals(SecurityUtils.getCurrentUserId()));
        }
        programasProjectos.setDtUltimaAlteracao(LocalDate.now());
        programasProjectos = programasProjectosRepository.save(programasProjectos);

        if (Objects.nonNull(programasProjectos.getId())) {
            logSave(TipoAcao.ATUALIZACAO, programasProjectos);
        } else {
            logSave(TipoAcao.INCLUSAO, programasProjectos);
        }

        return programasProjectos;
    }

    private void logSave(TipoAcao acao, ProgramasProjectos programasProjectos) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }

        ProgramasProjectosLogDTO dto = new ProgramasProjectosLogDTO();
        dto.setAcao(acao.getDescricao());
        dto.setDtLog(LocalDate.now());
        dto.setProgramasProjectos(programasProjectos);
        if (Objects.nonNull(username)) {
            Long id = this.userRepository.buscarUserIdByUsername(username);
            if (Objects.nonNull(id)){
                dto.setUsuario(this.userRepository.findOne(id));
            }
        }
        dto.setLog(acao.getLog());

        this.programasProjectosLogService.save(dto);
    }
    /**
     * Get all the programasProjectos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ProgramasProjectosDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProgramasProjectos");
        return programasProjectosRepository.findAll(pageable)
            .map(programasProjectosMapper::toDto);
    }

    public Page<ProgramasProjectosDTO> findAllStatusTrue(Pageable pageable) {
        log.debug("Request to get all ProgramasProjectos");
        Page<ProgramasProjectosDTO> retorno = null;
        UserDTO userDTO = this.userService.getUserWithAuthorities().map(UserDTO::new).orElse(null);

        if (Objects.nonNull(userDTO) ) {
            if (isAdministradorOrUsuserProvincial(userDTO.getAuthorities())) {
                retorno = programasProjectosRepository.findAllByStatusIsTrueAndProvinciaId(userDTO.getProvincia().getId(), pageable)
                    .map(programasProjectosMapper::toDto);
            } else {
                retorno = programasProjectosRepository.findAllByStatusIsTrue(pageable)
                    .map(programasProjectosMapper::toDto);
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
     * Get one programasProjectos by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public ProgramasProjectos findOne(Long id) {
        log.debug("Request to get ProgramasProjectos : {}", id);
        ProgramasProjectos programasProjectos = programasProjectosRepository.findOne(id);
        return programasProjectos;
    }

    /**
     * Delete the programasProjectos by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ProgramasProjectos : {}", id);
        logSave(TipoAcao.REMOCAO, programasProjectosRepository.findOne(id));
        programasProjectosRepository.delete(id);
    }

    public List<ProgramasProjectosDTO> getByPeriodo(String dtInicial, String dtFinal) {
        List<ProgramasProjectos> page = programasProjectosRepository.getAllBetweenDates(LocalDate.parse(dtInicial), LocalDate.parse(dtFinal));
        return buscarTabelasAuxiliares(page);
    }

    public List<ProgramasProjectosDTO> getByNome(String nome) {
        List<ProgramasProjectos> page = programasProjectosRepository.buscarPorNome(nome);
        return buscarTabelasAuxiliares(page);
    }

    public List<ProgramasProjectosDTO> getByMunicipio(String nome) {
        List<ProgramasProjectos> page = programasProjectosRepository.findAllByMunicipioNmMunicipioEquals(nome);
        return buscarTabelasAuxiliares(page);
    }

    public List<ProgramasProjectosDTO> getByProvincia(String nome) {
        List<ProgramasProjectos> page = programasProjectosRepository.findAllByProvinciaNmProvinciaEquals(nome);
        return buscarTabelasAuxiliares(page);
    }

    public List<ProgramasProjectosDTO> getByComuna(String nome) {
        List<ProgramasProjectos> page = programasProjectosRepository.findAllByComunaNmComunaEquals(nome);
        return buscarTabelasAuxiliares(page);
    }

    private List<ProgramasProjectosDTO> buscarTabelasAuxiliares(List<ProgramasProjectos> lista) {

        List<ProgramasProjectosDTO> listaDto = new ArrayList<>();

        lista.stream().forEach(i -> {
            ProgramasProjectosDTO dto = new ProgramasProjectosDTO();
            dto.setId(i.getId());
            dto.setDtLancamento(i.getDtLancamento());
            dto.setDtUltimaAlteracao(i.getDtUltimaAlteracao());
            dto.setUsuario(i.getUsuario());
            dto.setNmDesignacaoProjeto(i.getNmDesignacaoProjeto());
            dto.setNmDescricaoProjeto(i.getNmDescricaoProjeto());
            dto.setIdSaaAssociado(i.getIdSaaAssociado());
            dto.setTipoFinanciamento(i.getTipoFinanciamento());
            dto.setEspecialidade(i.getEspecialidade());
            dto.setComuna(i.getComuna());
            dto.setProvincia(i.getProvincia());
            dto.setComuna(i.getComuna());
            dto.setNmLocalidade(i.getNmLocalidade());
            dto.setFinalidadeProjeto(i.getFinalidadeProjeto());
            dto.setConcepcao(this.concepcaoService.findOneByProgramaProjectoId(i.getId()));
            dto.setConcurso(this.concursoService.findOneByProgramasProjectos(i.getId()));
            dto.setAdjudicacao(this.adjudicacaoService.findOneByProgramasProjectos(i.getId()));
            dto.setContrato(this.contratoService.findOneByProgramasProjectos(i.getId()));
            dto.setEmpreitada(this.empreitadaService.findOneByProgramasProjectos(i.getId()));
            dto.setExecucao(this.execucaoService.findOneByProgramasProjectos(i.getId()));
            listaDto.add(dto);
        });

        return listaDto;
    }
}
