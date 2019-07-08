package com.minea.sisas.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.minea.sisas.domain.SistemaAgua;
import com.minea.sisas.domain.*; // for static metamodels
import com.minea.sisas.repository.SistemaAguaRepository;
import com.minea.sisas.service.dto.SistemaAguaCriteria;

import com.minea.sisas.service.dto.SistemaAguaDTO;
import com.minea.sisas.service.mapper.SistemaAguaMapper;

/**
 * Service for executing complex queries for SistemaAgua entities in the database.
 * The main input is a {@link SistemaAguaCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SistemaAguaDTO} or a {@link Page} of {@link SistemaAguaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SistemaAguaQueryService extends QueryService<SistemaAgua> {

    private final Logger log = LoggerFactory.getLogger(SistemaAguaQueryService.class);


    private final SistemaAguaRepository sistemaAguaRepository;

    private final SistemaAguaMapper sistemaAguaMapper;

    public SistemaAguaQueryService(SistemaAguaRepository sistemaAguaRepository, SistemaAguaMapper sistemaAguaMapper) {
        this.sistemaAguaRepository = sistemaAguaRepository;
        this.sistemaAguaMapper = sistemaAguaMapper;
    }

    /**
     * Return a {@link List} of {@link SistemaAguaDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SistemaAguaDTO> findByCriteria(SistemaAguaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<SistemaAgua> specification = createSpecification(criteria);
        return sistemaAguaMapper.toDto(sistemaAguaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SistemaAguaDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SistemaAguaDTO> findByCriteria(SistemaAguaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<SistemaAgua> specification = createSpecification(criteria);
        final Page<SistemaAgua> result = sistemaAguaRepository.findAll(specification, page);
        return result.map(sistemaAguaMapper::toDto);
    }

    /**
     * Function to convert SistemaAguaCriteria to a {@link Specifications}
     */
    private Specifications<SistemaAgua> createSpecification(SistemaAguaCriteria criteria) {
        Specifications<SistemaAgua> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SistemaAgua_.id));
            }
            if (criteria.getIdUsuario() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuario(), SistemaAgua_.idUsuario));
            }
            if (criteria.getNmInqueridor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmInqueridor(), SistemaAgua_.nmInqueridor));
            }
            if (criteria.getDtLancamento() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtLancamento(), SistemaAgua_.dtLancamento));
            }
            if (criteria.getDtUltimaAlteracao() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtUltimaAlteracao(), SistemaAgua_.dtUltimaAlteracao));
            }
            if (criteria.getNmLocalidade() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmLocalidade(), SistemaAgua_.nmLocalidade));
            }
            if (criteria.getQtdPopulacaoActual() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdPopulacaoActual(), SistemaAgua_.qtdPopulacaoActual));
            }
            if (criteria.getQtdCasasLocalidade() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdCasasLocalidade(), SistemaAgua_.qtdCasasLocalidade));
            }
            if (criteria.getNmTpComunaAldeia() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmTpComunaAldeia(), SistemaAgua_.nmTpComunaAldeia));
            }
            if (criteria.getNmTpArea() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmTpArea(), SistemaAgua_.nmTpArea));
            }
            if (criteria.getPossuiSistemaAgua() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPossuiSistemaAgua(), SistemaAgua_.possuiSistemaAgua));
            }
            if (criteria.getNmSistemaAgua() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmSistemaAgua(), SistemaAgua_.nmSistemaAgua));
            }
            if (criteria.getNmFonteAgua() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmFonteAgua(), SistemaAgua_.nmFonteAgua));
            }
            if (criteria.getLatitude() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLatitude(), SistemaAgua_.latitude));
            }
            if (criteria.getLongitude() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLongitude(), SistemaAgua_.longitude));
            }
            if (criteria.getAltitude() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAltitude(), SistemaAgua_.altitude));
            }
            if (criteria.getNmTpFonte() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmTpFonte(), SistemaAgua_.nmTpFonte));
            }
            if (criteria.getNmFonteAguaUtilizada() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmFonteAguaUtilizada(), SistemaAgua_.nmFonteAguaUtilizada));
            }
            if (criteria.getNmTipoBomba() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmTipoBomba(), SistemaAgua_.nmTipoBomba));
            }
            if (criteria.getQtdCasasAguaLigada() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdCasasAguaLigada(), SistemaAgua_.qtdCasasAguaLigada));
            }
            if (criteria.getQtdChafarisesFuncionando() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdChafarisesFuncionando(), SistemaAgua_.qtdChafarisesFuncionando));
            }
            if (criteria.getQtdContadoresLigados() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdContadoresLigados(), SistemaAgua_.qtdContadoresLigados));
            }
            if (criteria.getQtdBebedouros() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdBebedouros(), SistemaAgua_.qtdBebedouros));
            }
            if (criteria.getQtdHabitantesAcessoServicoAgua() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdHabitantesAcessoServicoAgua(), SistemaAgua_.qtdHabitantesAcessoServicoAgua));
            }
            if (criteria.getAnoConstrucaoSistema() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAnoConstrucaoSistema(), SistemaAgua_.anoConstrucaoSistema));
            }
            if (criteria.getNmTpAvariaSistema() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmTpAvariaSistema(), SistemaAgua_.nmTpAvariaSistema));
            }
            if (criteria.getCausaAvariaSistema() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCausaAvariaSistema(), SistemaAgua_.causaAvariaSistema));
            }
            if (criteria.getStatusResolucao() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatusResolucao(), SistemaAgua_.statusResolucao));
            }
            if (criteria.getTempoServicoDisponivel() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTempoServicoDisponivel(), SistemaAgua_.tempoServicoDisponivel));
            }
            if (criteria.getQtdDiametroCondutaAdutoraAguaBruta() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdDiametroCondutaAdutoraAguaBruta(), SistemaAgua_.qtdDiametroCondutaAdutoraAguaBruta));
            }
            if (criteria.getQtdComprimentoCondutaAdutoraAguaBruta() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdComprimentoCondutaAdutoraAguaBruta(), SistemaAgua_.qtdComprimentoCondutaAdutoraAguaBruta));
            }
            if (criteria.getQtdDiametroCondutaAdutoraAguaTratada() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdDiametroCondutaAdutoraAguaTratada(), SistemaAgua_.qtdDiametroCondutaAdutoraAguaTratada));
            }
            if (criteria.getQtdComprimentoCondutaAdutoraAguaTratada() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdComprimentoCondutaAdutoraAguaTratada(), SistemaAgua_.qtdComprimentoCondutaAdutoraAguaTratada));
            }
            if (criteria.getDescMaterialUtilizadoCondutas() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescMaterialUtilizadoCondutas(), SistemaAgua_.descMaterialUtilizadoCondutas));
            }
            if (criteria.getQtdReservatoriosApoiados() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdReservatoriosApoiados(), SistemaAgua_.qtdReservatoriosApoiados));
            }
            if (criteria.getQtdCapacidadeReservatoriosApoiados() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdCapacidadeReservatoriosApoiados(), SistemaAgua_.qtdCapacidadeReservatoriosApoiados));
            }
            if (criteria.getQtdReservatoriosElevados() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdReservatoriosElevados(), SistemaAgua_.qtdReservatoriosElevados));
            }
            if (criteria.getQtdCapacidadeReservatoriosElevados() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdCapacidadeReservatoriosElevados(), SistemaAgua_.qtdCapacidadeReservatoriosElevados));
            }
            if (criteria.getAlturaReservatoriosElevados() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAlturaReservatoriosElevados(), SistemaAgua_.alturaReservatoriosElevados));
            }
            if (criteria.getNmTpTratamentoAgua() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmTpTratamentoAgua(), SistemaAgua_.nmTpTratamentoAgua));
            }
            if (criteria.getNmTpTratamentoPadraoUtilizado() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmTpTratamentoPadraoUtilizado(), SistemaAgua_.nmTpTratamentoPadraoUtilizado));
            }
            if (criteria.getNmTpTratamentoBasicoUtilizado() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmTpTratamentoBasicoUtilizado(), SistemaAgua_.nmTpTratamentoBasicoUtilizado));
            }
            if (criteria.getExisteAvariaSistemaTratamento() != null) {
                specification = specification.and(buildStringSpecification(criteria.getExisteAvariaSistemaTratamento(), SistemaAgua_.existeAvariaSistemaTratamento));
            }
            if (criteria.getExisteMotivoAusenciaTratamento() != null) {
                specification = specification.and(buildStringSpecification(criteria.getExisteMotivoAusenciaTratamento(), SistemaAgua_.existeMotivoAusenciaTratamento));
            }
            if (criteria.getNmEquipamentosComAvaria() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmEquipamentosComAvaria(), SistemaAgua_.nmEquipamentosComAvaria));
            }
            if (criteria.getCaudalDoSistema() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCaudalDoSistema(), SistemaAgua_.caudalDoSistema));
            }
            if (criteria.getQtdConsumoPercaptaLitrosHomemDia() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdConsumoPercaptaLitrosHomemDia(), SistemaAgua_.qtdConsumoPercaptaLitrosHomemDia));
            }
            if (criteria.getQtdDotacaoPercapta() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdDotacaoPercapta(), SistemaAgua_.qtdDotacaoPercapta));
            }
            if (criteria.getQtdDiariaHorasServicoSistema() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdDiariaHorasServicoSistema(), SistemaAgua_.qtdDiariaHorasServicoSistema));
            }
            if (criteria.getEsquema() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEsquema(), SistemaAgua_.esquema));
            }
            if (criteria.getNmModeloBombaManualUtilizada() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmModeloBombaManualUtilizada(), SistemaAgua_.nmModeloBombaManualUtilizada));
            }
            if (criteria.getNmTpBombaEnergia() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmTpBombaEnergia(), SistemaAgua_.nmTpBombaEnergia));
            }
        }
        return specification;
    }

}
