package com.minea.sisas.service;
import com.minea.sisas.repository.ProvinciaRepository;
import com.minea.sisas.service.dto.*;
import com.minea.sisas.service.mapper.ProvinciaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Service Implementation for managing Provincia.
 */
@Service
@Transactional
public class RelatorioService {

    private final Logger log = LoggerFactory.getLogger(RelatorioService.class);

    private final ProvinciaRepository provinciaRepository;

    private final ProvinciaMapper provinciaMapper;

    public RelatorioService(ProvinciaRepository provinciaRepository, ProvinciaMapper provinciaMapper) {
        this.provinciaRepository = provinciaRepository;
        this.provinciaMapper = provinciaMapper;
    }

    public List<SectorAguaDadosDTO> montaListaEstáticaParaTeste(){
        List<SectorAguaDadosDTO> retorno = new ArrayList<>();

      //  retorno.add(new SectorAguaDadosDTO("Bengo", 8, 20, 140, 271942l, 104045l, 38f));

        List<Object[]> list = this.provinciaRepository.buscaDadosSectorAgua();
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                SectorAguaDadosDTO dto = new SectorAguaDadosDTO();
                dto.setNomeProvincia((String) i[0]);
                dto.setMunicipios(((BigInteger) i[1]).intValue());
                dto.setComunas(((BigInteger) i[2]).intValue());
                dto.setSistemasFuncionam(((BigInteger) i[3]).intValue());
                dto.setPopulacaoTotal(((BigInteger) i[4]).longValue());
                dto.setBeneficiarios(((BigInteger) i[6]).longValue());

                dto.setCobertura((float) (dto.getBeneficiarios()*100)/dto.getPopulacaoTotal());

                retorno.add(dto);
            });
        }

        return retorno;
    }

    public List<SectorAguaSaneamentoDadosDTO> montaListaDadosPorAmbito(){
        List<SectorAguaSaneamentoDadosDTO> retorno = new ArrayList<>();
        Long populacaoTotal = 0l;
        // retorno.add(new SectorAguaSaneamentoDadosDTO("NACIONAL", 16367878l, 6033618l, 37, 3791035l, 23, 100));

        List<Object[]> list = this.provinciaRepository.buscaDadosInqueritoPorAmbito();
        if (Objects.nonNull(list)) {
            for (Object[] i : list) {
                populacaoTotal += ((BigDecimal) i[1]).longValue();
                retorno.add(new SectorAguaSaneamentoDadosDTO((String) i[0], ((BigDecimal) i[1]).longValue(), ((BigDecimal) i[2]).longValue(), 0f, 0l, 0f, 0f));
            }
        }

        if (Objects.nonNull(retorno)) {
            for (SectorAguaSaneamentoDadosDTO item: retorno) {
                if (item.getAmbito().equals("Nacional")) {
                    item.setPopulacaoPercentage(new Float(100));
                } else {
                    item.setPopulacaoPercentage((float) (item.getPopulacao()*100)/populacaoTotal);
                }
                item.setHabitantesPercent((float) (item.getHabitantes()*100)/item.getPopulacao());
            }
        }
        return retorno;
    }


    public List<FuncAguaChafarizesDadosDTO> montaListaEstáticaParaTesteAguaChafarizes(){
        List<FuncAguaChafarizesDadosDTO> retorno = new ArrayList<>();
       // retorno.add(new FuncAguaChafarizesDadosDTO("Bengo", 140, 98, 42, 100, 0, 226, 188, 38, 0, 0));


//        SELECT p.NM_PROVINCIA as provincia,
//            COUNT(sa.ID_SISTEMA_AGUA) as numero_sistemas,
//        SUM(CASE WHEN sa.estado_funcionamento_sistema = 'Está em funcionamento (Bom)' THEN 1 ELSE 0 END) as funcionam,
//        SUM(CASE WHEN sa.estado_funcionamento_sistema = 'Não está em funcionamento' THEN 1 ELSE 0 END) as nao_funcionam,
//        SUM(sa.qtd_chafarises_existentes ) as numero_chafariz,
//        SUM(sa.QTD_CHAFARISES_FUNCIONANDO) as chafariz_funcionam,
//        SUM(sa.qtd_chafarises_existentes - sa.QTD_CHAFARISES_FUNCIONANDO) as chafariz_nao_funcionam

        List<Object[]> list = this.provinciaRepository.buscaDadosSectorAguaChafarizes();
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                FuncAguaChafarizesDadosDTO dto = new FuncAguaChafarizesDadosDTO();
                dto.setNomeProvincia((String) i[0]);
                dto.setNumeroSistemas(((BigInteger) i[1]).intValue());
                dto.setFuncionamAgua(((BigInteger) i[2]).intValue());
                dto.setNaoFuncionamAgua(((BigInteger) i[2]).intValue());
                dto.setNumeroChafarizes(((BigInteger) i[2]).intValue());
                dto.setFuncionamChafariz(((BigInteger) i[2]).intValue());
                dto.setNaoFuncionamChafariz(((BigInteger) i[2]).intValue());
                dto.setFuncionamAguaPerc((float) (dto.getFuncionamAgua()*100)/dto.getNumeroSistemas());
                dto.setNaoFuncionamAguaPerc((float) (dto.getNaoFuncionamAgua()*100)/dto.getNumeroSistemas());
                dto.setFuncionamChafarizPerc((float) (dto.getFuncionamChafariz()*100)/dto.getNumeroChafarizes());
                dto.setNaoFuncionamChafarizPerc((float) (dto.getNaoFuncionamChafariz()*100)/dto.getNumeroChafarizes());

                retorno.add(dto);
            });
        }

        return retorno;
    }


    public List<InqueritosPreenchidosDadosDTO> montaListaEstáticaParaTesteInqueritoAguas(){
        List<InqueritosPreenchidosDadosDTO> retorno = new ArrayList<>();
       // retorno.add(new InqueritosPreenchidosDadosDTO("Huambo", 11, 36, 551, 1266, 1817, 23, 0, 23, 1840,0, 0, 0, 0, 0));

        List<Object[]> list = this.provinciaRepository.buscaDadosInqueritoAguas();
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                retorno.add(new InqueritosPreenchidosDadosDTO((String) i[0], ((BigInteger) i[1]).intValue(), ((BigInteger) i[2]).intValue(), ((BigInteger) i[3]).intValue(), ((BigInteger) i[4]).intValue(), ((BigInteger) i[5]).intValue(), 0, 0, 0, ((BigInteger) i[5]).intValue(),0, 0, 0, 0, 0));
            });
        }
        return retorno;
    }

    public List<TratamentoSistemasAguaDadosDTO> montaListaTratamentoSistemasAguas(){
        List<TratamentoSistemasAguaDadosDTO> retorno = new ArrayList<>();

       // retorno.add(new TratamentoSistemasAguaDadosDTO("Bengo", 140, 10, 3, 127, 0));

        List<Object[]> list = this.provinciaRepository.buscaDadosTratamentoSistemasAgua();
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                retorno.add(new TratamentoSistemasAguaDadosDTO((String) i[0], ((BigInteger) i[1]).intValue(), ((BigInteger) i[2]).intValue(), ((BigInteger) i[3]).intValue(), ((BigInteger) i[4]).intValue(), ((BigInteger) i[5]).intValue()));
            });
        }
        return retorno;
    }



}
