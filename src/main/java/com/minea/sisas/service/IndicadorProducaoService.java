package com.minea.sisas.service;

import com.minea.sisas.domain.IndicadorProducao;
import com.minea.sisas.repository.IndicadorProducaoRepository;
import com.minea.sisas.service.dto.IndicadorProducaoDTO;
import com.minea.sisas.service.mapper.IndicadorProducaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


/**
 * Service Implementation for managing IndicadorProducao.
 */
@Service
@Transactional
public class IndicadorProducaoService {

    private final Logger log = LoggerFactory.getLogger(IndicadorProducaoService.class);

    private final IndicadorProducaoRepository indicadorProducaoRepository;

    private final IndicadorProducaoMapper indicadorProducaoMapper;

    public IndicadorProducaoService(IndicadorProducaoRepository indicadorProducaoRepository, IndicadorProducaoMapper indicadorProducaoMapper) {
        this.indicadorProducaoRepository = indicadorProducaoRepository;
        this.indicadorProducaoMapper = indicadorProducaoMapper;
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
        indicadorProducao = indicadorProducaoRepository.save(indicadorProducao);
        return indicadorProducaoMapper.toDto(indicadorProducao);
    }

    /**
     * Get all the indicadorProducaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<IndicadorProducaoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all IndicadorProducaos");
        return indicadorProducaoRepository.findAll(pageable)
            .map(indicadorProducaoMapper::toDto);
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
        IndicadorProducao indicadorProducao = indicadorProducaoRepository.findOne(id);
        return indicadorProducaoMapper.toDto(indicadorProducao);
    }

    /**
     * Delete the indicadorProducao by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete IndicadorProducao : {}", id);
        indicadorProducaoRepository.delete(id);
    }

    // REGRA DE NEGÓCIO(TEMPORÁRIO)
    public IndicadorProducaoDTO findLast(){
        log.debug("Request to get last IndicadorProducao");
        IndicadorProducao indicadorProducao, indicadorProducao2;

        // TODAS OS INDICADORES
        List<IndicadorProducao> indicadores= indicadorProducaoRepository.findAll();

        indicadorProducao2= new IndicadorProducao();
        indicadorProducao= indicadores.get(indicadores.size()-1); // ULTIMO INDICADOR de PRODUÇÃO REGISTRADO

        //  LocalDateTime dataAtual= LocalDateTime.now(); IRIA SERVIR PARA BUSCAR E COMPARAR A DATA MAIS RECENTE

        // INFORMAÇÕES QUE DEVEM VIM DO ULTIMO MÊS(Por enquanto ultimo registro)
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
        indicadorProducao2.setId(null);


        return indicadorProducaoMapper.toDto(indicadorProducao2);
    }
}
