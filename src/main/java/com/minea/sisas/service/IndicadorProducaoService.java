package com.minea.sisas.service;

import com.minea.sisas.domain.Authority;
import com.minea.sisas.domain.IndicadorProducao;
import com.minea.sisas.domain.SistemaAgua;
import com.minea.sisas.domain.User;
import com.minea.sisas.domain.enumeration.TipoAcao;
import com.minea.sisas.repository.IndicadorProducaoRepository;
import com.minea.sisas.repository.UserRepository;
import com.minea.sisas.service.dto.*;
import com.minea.sisas.service.mapper.IndicadorProducaoMapper;
import com.minea.sisas.service.util.PermissoesEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;


/**
 * Service Implementation for managing IndicadorProducao.
 */
@Service
@Transactional
public class IndicadorProducaoService {

    private final Logger log = LoggerFactory.getLogger(IndicadorProducaoService.class);

    private final IndicadorProducaoRepository indicadorProducaoRepository;

    private final IndicadorProducaoMapper indicadorProducaoMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired IndicadorProducaoLogService indicadorProducaoLogService;

    @Autowired
    private UserService userService;

    public IndicadorProducaoService(IndicadorProducaoRepository indicadorProducaoRepository, IndicadorProducaoMapper indicadorProducaoMapper,
                                    UserRepository userRepository, IndicadorProducaoLogService indicadorProducaoLogService,  UserService userService) {
        this.indicadorProducaoRepository = indicadorProducaoRepository;
        this.indicadorProducaoMapper = indicadorProducaoMapper;
        this.userRepository = userRepository;
        this.indicadorProducaoLogService = indicadorProducaoLogService;
        this.userService = userService;
    }

    /**
     * Save a indicadorProducao.
     *
     * @param indicadorProducaoDTO the entity to save
     * @return the persisted entity
     */
    public IndicadorProducaoDTO save(IndicadorProducaoDTO indicadorProducaoDTO) {
        log.debug("Request to save IndicadorProducao : {}", indicadorProducaoDTO);
        IndicadorProducao indicadorProducao = indicadorProducaoMapper.toEntity(indicadorProducaoDTO);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
            if (Objects.nonNull(username)) {
                indicadorProducao.setIdUsuario(this.userRepository.buscarUserIdByUsername(username));
            }
        }

        indicadorProducao = indicadorProducaoRepository.save(indicadorProducao);
        if (Objects.nonNull(indicadorProducaoDTO.getId())) {
            logSave(TipoAcao.ATUALIZACAO, indicadorProducao);
        } else {
            logSave(TipoAcao.INCLUSAO, indicadorProducao);
        }

        return indicadorProducaoMapper.toDto(indicadorProducao);
    }


    private void logSave(TipoAcao acao, IndicadorProducao indicadorProducao) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }

        IndicadorProducaoLogDTO dto = new IndicadorProducaoLogDTO();
        dto.setAcao(acao.getDescricao());
        dto.setDtLog(LocalDate.now());
        dto.setIdIndicadorProducao(indicadorProducao.getId());
        if (Objects.nonNull(username)) {
            dto.setIdUsuario(this.userRepository.buscarUserIdByUsername(username));
        }
        dto.setLog(acao.getLog());

        this.indicadorProducaoLogService.save(dto);
    }
    /**
     * Get all the indicadorProducaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
//    public Page<IndicadorProducaoDTO> findAll(Pageable pageable) {
//        log.debug("Request to get all IndicadorProducaos");
//        return indicadorProducaoRepository.findAll(pageable)
//            .map(indicadorProducaoMapper::toDto);
//    }

    public Page<IndicadorProducaoDTO> findAllStatusTrue(Pageable pageable) {
        log.debug("Request to get all ProgramasProjectos");
        Page<IndicadorProducaoDTO> retorno = null;
        UserDTO userDTO = this.userService.getUserWithAuthorities().map(UserDTO::new).orElse(null);

        if (Objects.nonNull(userDTO) ) {
            if (isAdministradorOrUsuserProvincial(userDTO.getAuthorities())) {
                retorno = indicadorProducaoRepository.findAllByStatusIsTrueAndProvinciaId(userDTO.getProvincia().getId(),pageable)
                    .map(indicadorProducaoMapper::toDto);
            } else {
                retorno = indicadorProducaoRepository.findAllByStatusIsTrue(pageable)
                    .map(indicadorProducaoMapper::toDto);
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
     * Get one indicadorProducao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public IndicadorProducaoDTO findOne(Long id) {
        log.debug("Request to get IndicadorProducao : {}", id);
        IndicadorProducao indicadorProducao = indicadorProducaoRepository.findByIdAndStatusIsTrue(id);
        return indicadorProducaoMapper.toDto(indicadorProducao);
    }

    /**
     * Delete the indicadorProducao by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete IndicadorProducao : {}", id);
        logSave(TipoAcao.REMOCAO, indicadorProducaoRepository.findOne(id));
        indicadorProducaoRepository.delete(id);
    }

    // REGRA DE NEGÓCIO(TEMPORÁRIO)
    public IndicadorProducaoDTO findLast(Long provinciaId){
        log.debug("Request to get last IndicadorProducao");

        IndicadorProducao indicadorProducao = null;
        IndicadorProducao indicadorProducao2 = new IndicadorProducao();

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        int mes  = c.get(Calendar.MONTH);

        indicadorProducao = this.indicadorProducaoRepository.getLastByMonthAndProvincia(mes + 1, provinciaId);

        // INFORMAÇÕES QUE DEVEM VIM DO ULTIMO MÊS(Por enquanto ultimo registro)
        if (Objects.nonNull(indicadorProducao)) {

            int popCoberta= indicadorProducao.getQtdPopulacaoCobertaInfraestrutura().intValue();
            int numFontanariosChafarises=indicadorProducao.getQtdFontanariosChafarisesOperacionais().intValue();
            int totalFunc= indicadorProducao.getQtdFuncionarios().intValue();
            int totalFunEfetivos=indicadorProducao.getQtdFuncionariosEfectivos().intValue();
            int totalFunContratados= indicadorProducao.getQtdFuncionariosContratados().intValue();
            int totalFuncOutrasEnt=indicadorProducao.getQtdFuncionariosOutrasEntidades().intValue();
            int totalLigaAtivas=indicadorProducao.getQtdLigacoesActivas().intValue();
            int NumDeLigacoesDomes=indicadorProducao.getQtdLigacoesDomesticasActivas().intValue();
            int NumCapitacoes=indicadorProducao.getQtdCaptacoes().intValue();
            int NumDeETA=indicadorProducao.getQtdEtas().intValue();
            int NumDeReservatorios=indicadorProducao.getQtdReservatorios().intValue();
            int NumDeEstacoes=indicadorProducao.getQtdEstacoesElevatorias().intValue();
            int ComprimentoDasAdutoras=indicadorProducao.getQtdComprimentoAdutoras().intValue();
            int ComprimentoDasRedes=indicadorProducao.getQtdComprimentoRedes().intValue();
            int ComprimentoDosRamais=indicadorProducao.getQtdComprimentoRamais().intValue();
            LocalDate data=indicadorProducao.getDtUltimaAlteracao();

            Long manuaisMoPrevistros = indicadorProducao.getQtdManuaisMoPrevistos();
            Long manuaisMmsPrevistros = indicadorProducao.getQtdManuaisMmsPrevistos();
            Long accoesManuaisMoRealizados = indicadorProducao.getQtdAcoesManuaisMoRealizadas();
            Long manuaisMmsRealizados = indicadorProducao.getQtdManuaisMmsRealizadas();
            Long manuaisCmpRealizados = indicadorProducao.getQtdManuaisCmpRealizadas();

            indicadorProducao2.setQtdPopulacaoCobertaInfraestrutura(BigDecimal.valueOf(popCoberta));
            indicadorProducao2.setQtdFontanariosChafarisesOperacionais(BigDecimal.valueOf(numFontanariosChafarises));
            indicadorProducao2.setQtdFuncionarios(Long.valueOf(totalFunc));
            indicadorProducao2.setQtdFuncionariosEfectivos(Long.valueOf(totalFunEfetivos));
            indicadorProducao2.setQtdFuncionariosContratados(Long.valueOf(totalFunContratados));
            indicadorProducao2.setQtdFuncionariosOutrasEntidades(Long.valueOf(totalFuncOutrasEnt));
            indicadorProducao2.setQtdLigacoesActivas(Long.valueOf(totalLigaAtivas));
            indicadorProducao2.setQtdLigacoesDomesticasActivas(Long.valueOf(NumDeLigacoesDomes));
            indicadorProducao2.setQtdCaptacoes(Long.valueOf(NumCapitacoes));
            indicadorProducao2.setQtdEtas(Long.valueOf(NumDeETA));
            indicadorProducao2.setQtdReservatorios(Long.valueOf(NumDeReservatorios));
            indicadorProducao2.setQtdEstacoesElevatorias(Long.valueOf(NumDeEstacoes));
            indicadorProducao2.setQtdComprimentoAdutoras(BigDecimal.valueOf(ComprimentoDasAdutoras));
            indicadorProducao2.setQtdComprimentoRedes(BigDecimal.valueOf(ComprimentoDasRedes));
            indicadorProducao2.setQtdComprimentoRedes(BigDecimal.valueOf(ComprimentoDosRamais));
            indicadorProducao2.setDtUltimaAlteracao(data);
            indicadorProducao2.setQtdManuaisMoPrevistos(manuaisMoPrevistros);
            indicadorProducao2.setQtdManuaisMmsPrevistos(manuaisMmsPrevistros);
            indicadorProducao2.setQtdAcoesManuaisMoRealizadas(accoesManuaisMoRealizados);
            indicadorProducao2.setQtdManuaisMmsRealizadas(manuaisMmsRealizados);
            indicadorProducao2.setQtdManuaisCmpRealizadas(manuaisCmpRealizados);
            indicadorProducao2.setId(null);
        } else {
            indicadorProducao2.setQtdPopulacaoCobertaInfraestrutura(new BigDecimal(0));
            indicadorProducao2.setQtdFontanariosChafarisesOperacionais(new BigDecimal(0));
            indicadorProducao2.setQtdFuncionarios(0l);
            indicadorProducao2.setQtdFuncionariosEfectivos(0l);
            indicadorProducao2.setQtdFuncionariosContratados(0l);
            indicadorProducao2.setQtdFuncionariosOutrasEntidades(0l);
            indicadorProducao2.setQtdLigacoesActivas(0l);
            indicadorProducao2.setQtdLigacoesDomesticasActivas(0l);
            indicadorProducao2.setQtdCaptacoes(0l);
            indicadorProducao2.setQtdEtas(0l);
            indicadorProducao2.setQtdReservatorios(0l);
            indicadorProducao2.setQtdEstacoesElevatorias(0l);
            indicadorProducao2.setQtdComprimentoAdutoras(new BigDecimal(0));
            indicadorProducao2.setQtdComprimentoRedes(new BigDecimal(0));
            indicadorProducao2.setQtdComprimentoRedes(new BigDecimal(0));
            indicadorProducao2.setDtUltimaAlteracao(null);
            indicadorProducao2.setId(null);
        }

        return indicadorProducaoMapper.toDto(indicadorProducao2);
    }
}
