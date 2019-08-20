package com.minea.sisas.service;

import com.minea.sisas.domain.*;
import com.minea.sisas.domain.IndicadorProducao;
import com.minea.sisas.repository.IndicadorProducaoRepository;
import com.minea.sisas.service.dto.IndicadorProducaoCriteria;
import com.minea.sisas.service.dto.IndicadorProducaoDTO;
import com.minea.sisas.service.mapper.IndicadorProducaoMapper;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for executing complex queries for IndicadorProducao entities in the database.
 * The main input is a {@link IndicadorProducaoCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link IndicadorProducaoDTO} or a {@link Page} of {@link IndicadorProducaoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class IndicadorProducaoQueryService extends QueryService<IndicadorProducao> {

    private final Logger log = LoggerFactory.getLogger(IndicadorProducaoQueryService.class);


    private final IndicadorProducaoRepository indicadorProducaoRepository;

    private final IndicadorProducaoMapper indicadorProducaoMapper;

    public IndicadorProducaoQueryService(IndicadorProducaoRepository indicadorProducaoRepository, IndicadorProducaoMapper indicadorProducaoMapper) {
        this.indicadorProducaoRepository = indicadorProducaoRepository;
        this.indicadorProducaoMapper = indicadorProducaoMapper;
    }

    /**
     * Return a {@link List} of {@link IndicadorProducaoDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<IndicadorProducaoDTO> findByCriteria(IndicadorProducaoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<IndicadorProducao> specification = createSpecification(criteria);
        return indicadorProducaoMapper.toDto(indicadorProducaoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link IndicadorProducaoDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<IndicadorProducaoDTO> findByCriteria(IndicadorProducaoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<IndicadorProducao> specification = createSpecification(criteria);
        final Page<IndicadorProducao> result = indicadorProducaoRepository.findAll(specification, page);
        return result.map(indicadorProducaoMapper::toDto);
    }

    public Page<IndicadorProducao> findAll(Pageable page) {
        log.debug("find by page: {}", page);
        final Page<IndicadorProducao> result = indicadorProducaoRepository.findAll(page);
        return result;
    }

    /**
     * Function to convert IndicadorProducaoCriteria to a {@link Specifications}
     */
    private Specifications<IndicadorProducao> createSpecification(IndicadorProducaoCriteria criteria) {
        Specifications<IndicadorProducao> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), IndicadorProducao_.id));
            }
            if (criteria.getIdUsuario() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuario(), IndicadorProducao_.idUsuario));
            }
            if (criteria.getDtLancamento() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtLancamento(), IndicadorProducao_.dtLancamento));
            }
            if (criteria.getDtUltimaAlteracao() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtUltimaAlteracao(), IndicadorProducao_.dtUltimaAlteracao));
            }
            if (criteria.getQtdPopulacaoCobertaInfraestrutura() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdPopulacaoCobertaInfraestrutura(), IndicadorProducao_.qtdPopulacaoCobertaInfraestrutura));
            }
            if (criteria.getQtdFontanariosChafarisesOperacionais() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdFontanariosChafarisesOperacionais(), IndicadorProducao_.qtdFontanariosChafarisesOperacionais));
            }
            if (criteria.getQtdMediaHorasDistribuicaoDiaria() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdMediaHorasDistribuicaoDiaria(), IndicadorProducao_.qtdMediaHorasDistribuicaoDiaria));
            }
            if (criteria.getQtdMediaHorasParagem() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdMediaHorasParagem(), IndicadorProducao_.qtdMediaHorasParagem));
            }
            if (criteria.getQtdMediaHorasInterrupcaoFaltaEnergia() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdMediaHorasInterrupcaoFaltaEnergia(), IndicadorProducao_.qtdMediaHorasInterrupcaoFaltaEnergia));
            }
            if (criteria.getQtdVolumeAguaCaptada() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdVolumeAguaCaptada(), IndicadorProducao_.qtdVolumeAguaCaptada));
            }
            if (criteria.getQtdVolumeAguaTratada() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdVolumeAguaTratada(), IndicadorProducao_.qtdVolumeAguaTratada));
            }
            if (criteria.getQtdVolumeAguaDistribuida() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdVolumeAguaDistribuida(), IndicadorProducao_.qtdVolumeAguaDistribuida));
            }
            if (criteria.getQtdConsumoEnergia() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdConsumoEnergia(), IndicadorProducao_.qtdConsumoEnergia));
            }
            if (criteria.getQtdConsumoCombustivel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdConsumoCombustivel(), IndicadorProducao_.qtdConsumoCombustivel));
            }
            if (criteria.getQtdConsumoHipocloritroCalcio() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdConsumoHipocloritroCalcio(), IndicadorProducao_.qtdConsumoHipocloritroCalcio));
            }
            if (criteria.getQtdConsumoSulfatoAluminio() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdConsumoSulfatoAluminio(), IndicadorProducao_.qtdConsumoSulfatoAluminio));
            }
            if (criteria.getQtdConsumoHidroxidoCalcio() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdConsumoHidroxidoCalcio(), IndicadorProducao_.qtdConsumoHidroxidoCalcio));
            }
            if (criteria.getQtdReparoCaptacaoEtas() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdReparoCaptacaoEtas(), IndicadorProducao_.qtdReparoCaptacaoEtas));
            }
            if (criteria.getQtdReparoAdutoras() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdReparoAdutoras(), IndicadorProducao_.qtdReparoAdutoras));
            }
            if (criteria.getQtdReparoRedeDistribuicao() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdReparoRedeDistribuicao(), IndicadorProducao_.qtdReparoRedeDistribuicao));
            }
            if (criteria.getQtdReparoRamais() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdReparoRamais(), IndicadorProducao_.qtdReparoRamais));
            }
            if (criteria.getQtdManutencaoCurativa() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdManutencaoCurativa(), IndicadorProducao_.qtdManutencaoCurativa));
            }
            if (criteria.getQtdManutencaoPreventiva() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdManutencaoPreventiva(), IndicadorProducao_.qtdManutencaoPreventiva));
            }
            if (criteria.getQtdManutencaoVerificadoSolicitado() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdManutencaoVerificadoSolicitado(), IndicadorProducao_.qtdManutencaoVerificadoSolicitado));
            }
            if (criteria.getQtdReservatorioLavado() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdReservatorioLavado(), IndicadorProducao_.qtdReservatorioLavado));
            }
            if (criteria.getQtdFuncionarios() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdFuncionarios(), IndicadorProducao_.qtdFuncionarios));
            }
            if (criteria.getQtdFuncionariosEfectivos() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdFuncionariosEfectivos(), IndicadorProducao_.qtdFuncionariosEfectivos));
            }
            if (criteria.getQtdFuncionariosContratados() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdFuncionariosContratados(), IndicadorProducao_.qtdFuncionariosContratados));
            }
            if (criteria.getQtdFuncionariosOutrasEntidades() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdFuncionariosOutrasEntidades(), IndicadorProducao_.qtdFuncionariosOutrasEntidades));
            }
            if (criteria.getQtdNovasLigacoesNovosContratos() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdNovasLigacoesNovosContratos(), IndicadorProducao_.qtdNovasLigacoesNovosContratos));
            }
            if (criteria.getQtdNovasLigacoesDomesticasNovosContratos() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdNovasLigacoesDomesticasNovosContratos(), IndicadorProducao_.qtdNovasLigacoesDomesticasNovosContratos));
            }
            if (criteria.getQtdLigacoesIlegaisRegularizadas() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdLigacoesIlegaisRegularizadas(), IndicadorProducao_.qtdLigacoesIlegaisRegularizadas));
            }
            if (criteria.getQtdLigacoesFechadas() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdLigacoesFechadas(), IndicadorProducao_.qtdLigacoesFechadas));
            }
            if (criteria.getQtdCortes() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdCortes(), IndicadorProducao_.qtdCortes));
            }
            if (criteria.getQtdReligacoes() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdReligacoes(), IndicadorProducao_.qtdReligacoes));
            }
            if (criteria.getQtdLigacoesActivas() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdLigacoesActivas(), IndicadorProducao_.qtdLigacoesActivas));
            }
            if (criteria.getQtdLigacoesDomesticasActivas() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdLigacoesDomesticasActivas(), IndicadorProducao_.qtdLigacoesDomesticasActivas));
            }
            if (criteria.getQtdLigacoesFacturadasBaseLeiturasReais() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdLigacoesFacturadasBaseLeiturasReais(), IndicadorProducao_.qtdLigacoesFacturadasBaseLeiturasReais));
            }
            if (criteria.getQtdLigacoesFacturadasBaseEstimativasAvenca() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdLigacoesFacturadasBaseEstimativasAvenca(), IndicadorProducao_.qtdLigacoesFacturadasBaseEstimativasAvenca));
            }
            if (criteria.getQtdVolumeAguaFacturada() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdVolumeAguaFacturada(), IndicadorProducao_.qtdVolumeAguaFacturada));
            }
            if (criteria.getQtdVolumeTotalFacturadaLigacoesDomesticas() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdVolumeTotalFacturadaLigacoesDomesticas(), IndicadorProducao_.qtdVolumeTotalFacturadaLigacoesDomesticas));
            }
            if (criteria.getQtdVolumeFacturadoBaseLeituraReais() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdVolumeFacturadoBaseLeituraReais(), IndicadorProducao_.qtdVolumeFacturadoBaseLeituraReais));
            }
            if (criteria.getVlrTotalFacturado() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVlrTotalFacturado(), IndicadorProducao_.vlrTotalFacturado));
            }
            if (criteria.getVlrFacturasCanceladasNotasCreditos() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVlrFacturasCanceladasNotasCreditos(), IndicadorProducao_.vlrFacturasCanceladasNotasCreditos));
            }
            if (criteria.getVlrRealFacturado() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVlrRealFacturado(), IndicadorProducao_.vlrRealFacturado));
            }
            if (criteria.getVlrTotalCobrado() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVlrTotalCobrado(), IndicadorProducao_.vlrTotalCobrado));
            }
            if (criteria.getQtdReclamacoes() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdReclamacoes(), IndicadorProducao_.qtdReclamacoes));
            }
            if (criteria.getQtdReclamacoesRespondidasMenorIgualCincoDias() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdReclamacoesRespondidasMenorIgualCincoDias(), IndicadorProducao_.qtdReclamacoesRespondidasMenorIgualCincoDias));
            }
            if (criteria.getQtdReclamacoesRespondidasMaisCincoMenosVinteDias() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdReclamacoesRespondidasMaisCincoMenosVinteDias(), IndicadorProducao_.qtdReclamacoesRespondidasMaisCincoMenosVinteDias));
            }
            if (criteria.getQtdReclamacoesRespondidasMaiorIgualVinteDias() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdReclamacoesRespondidasMaiorIgualVinteDias(), IndicadorProducao_.qtdReclamacoesRespondidasMaiorIgualVinteDias));
            }
            if (criteria.getVlrCustoPessoal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVlrCustoPessoal(), IndicadorProducao_.vlrCustoPessoal));
            }
            if (criteria.getVlrCustoFse() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVlrCustoFse(), IndicadorProducao_.vlrCustoFse));
            }
            if (criteria.getVlrCustoEnergia() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVlrCustoEnergia(), IndicadorProducao_.vlrCustoEnergia));
            }
            if (criteria.getVlrCustoManutencao() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVlrCustoManutencao(), IndicadorProducao_.vlrCustoManutencao));
            }
            if (criteria.getVlrCustoReagentes() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVlrCustoReagentes(), IndicadorProducao_.vlrCustoReagentes));
            }
            if (criteria.getVlrCustoDestinoFinalLamas() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVlrCustoDestinoFinalLamas(), IndicadorProducao_.vlrCustoDestinoFinalLamas));
            }
            if (criteria.getVlrCustoOperacionaisOpex() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVlrCustoOperacionaisOpex(), IndicadorProducao_.vlrCustoOperacionaisOpex));
            }
            if (criteria.getVlrCustoAmortizaAnualInvestOpCapex() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVlrCustoAmortizaAnualInvestOpCapex(), IndicadorProducao_.vlrCustoAmortizaAnualInvestOpCapex));
            }
            if (criteria.getVlrCustoTotaisCapexOpex() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVlrCustoTotaisCapexOpex(), IndicadorProducao_.vlrCustoTotaisCapexOpex));
            }
            if (criteria.getQtdCaptacoes() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdCaptacoes(), IndicadorProducao_.qtdCaptacoes));
            }
            if (criteria.getQtdEtas() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdEtas(), IndicadorProducao_.qtdEtas));
            }
            if (criteria.getQtdReservatorios() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdReservatorios(), IndicadorProducao_.qtdReservatorios));
            }
            if (criteria.getQtdEstacoesElevatorias() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdEstacoesElevatorias(), IndicadorProducao_.qtdEstacoesElevatorias));
            }
            if (criteria.getQtdComprimentoAdutoras() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdComprimentoAdutoras(), IndicadorProducao_.qtdComprimentoAdutoras));
            }
            if (criteria.getQtdComprimentoRedes() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdComprimentoRedes(), IndicadorProducao_.qtdComprimentoRedes));
            }
            if (criteria.getQtdComprimentoRamais() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdComprimentoRamais(), IndicadorProducao_.qtdComprimentoRamais));
            }
            if (criteria.getQtdAcoesFormacaoMoPlaneadas() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdAcoesFormacaoMoPlaneadas(), IndicadorProducao_.qtdAcoesFormacaoMoPlaneadas));
            }
            if (criteria.getQtdAcoesFormacaoMmsPlaneadas() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdAcoesFormacaoMmsPlaneadas(), IndicadorProducao_.qtdAcoesFormacaoMmsPlaneadas));
            }
            if (criteria.getQtdAcoesFormacaoCmpPlaneadas() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdAcoesFormacaoCmpPlaneadas(), IndicadorProducao_.qtdAcoesFormacaoCmpPlaneadas));
            }
            if (criteria.getQtdAcoesFormacaoSoftwareFornecidosPlanejadas() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdAcoesFormacaoSoftwareFornecidosPlanejadas(), IndicadorProducao_.qtdAcoesFormacaoSoftwareFornecidosPlanejadas));
            }
            if (criteria.getQtdAcoesFormacaoMoRealizadas() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdAcoesFormacaoMoRealizadas(), IndicadorProducao_.qtdAcoesFormacaoMoRealizadas));
            }
            if (criteria.getQtdAcoesFormacaoMmsRealizadas() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdAcoesFormacaoMmsRealizadas(), IndicadorProducao_.qtdAcoesFormacaoMmsRealizadas));
            }
            if (criteria.getQtdAcoesFormacaoCmpRealizadas() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdAcoesFormacaoCmpRealizadas(), IndicadorProducao_.qtdAcoesFormacaoCmpRealizadas));
            }
            if (criteria.getQtdAcoesFormacaoSoftwareFornecidosRealizadas() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdAcoesFormacaoSoftwareFornecidosRealizadas(), IndicadorProducao_.qtdAcoesFormacaoSoftwareFornecidosRealizadas));
            }
            if (criteria.getQtdAcoesFormacaoRealizadas() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdAcoesFormacaoRealizadas(), IndicadorProducao_.qtdAcoesFormacaoRealizadas));
            }
            if (criteria.getQtdManuaisMoPrevistos() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdManuaisMoPrevistos(), IndicadorProducao_.qtdManuaisMoPrevistos));
            }
            if (criteria.getQtdManuaisMmsPrevistos() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdManuaisMmsPrevistos(), IndicadorProducao_.qtdManuaisMmsPrevistos));
            }
            if (criteria.getQtdManuaisCmpPrevistos() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdManuaisCmpPrevistos(), IndicadorProducao_.qtdManuaisCmpPrevistos));
            }
            if (criteria.getQtdManuaisPrevistos() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdManuaisPrevistos(), IndicadorProducao_.qtdManuaisPrevistos));
            }
            if (criteria.getQtdAcoesManuaisMoRealizadas() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdAcoesManuaisMoRealizadas(), IndicadorProducao_.qtdAcoesManuaisMoRealizadas));
            }
            if (criteria.getQtdManuaisMmsRealizadas() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdManuaisMmsRealizadas(), IndicadorProducao_.qtdManuaisMmsRealizadas));
            }
            if (criteria.getQtdManuaisCmpRealizadas() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdManuaisCmpRealizadas(), IndicadorProducao_.qtdManuaisCmpRealizadas));
            }
            if (criteria.getQtdManuaisRealizados() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtdManuaisRealizados(), IndicadorProducao_.qtdManuaisRealizados));
            }
            if (criteria.getIdSituacaoId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getIdSituacaoId(), IndicadorProducao_.situacao, Situacao_.id));
            }
            if (criteria.getIdSistemaAguaId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getIdSistemaAguaId(), IndicadorProducao_.sistemaAgua, SistemaAgua_.id));
            }
            if (criteria.getIdComunaId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getIdComunaId(), IndicadorProducao_.comuna, Comuna_.id));
            }
        }
        return specification;
    }

}
