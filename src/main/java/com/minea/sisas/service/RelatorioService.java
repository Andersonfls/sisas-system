package com.minea.sisas.service;
import com.minea.sisas.repository.ProvinciaRepository;
import com.minea.sisas.service.dto.*;
import com.minea.sisas.service.mapper.ProvinciaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Query;
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
                dto.setSistemasFuncionam(((BigDecimal) i[3]).intValue());
                dto.setPopulacaoTotal(((BigDecimal) i[4]).longValue());
               // dto.setBeneficiarios(((BigDecimal) i[5]).longValue());
                dto.setBeneficiarios(0l);

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

    public List<SectorAguaSaneamentoDadosDTO> montaListaDadosPorProvincia(){
        List<SectorAguaSaneamentoDadosDTO> retorno = new ArrayList<>();
        List<Object[]> list = this.provinciaRepository.buscaDadosInqueritoPorProvincia();
        if (Objects.nonNull(list)) {
            for (Object[] i : list) {
                SectorAguaSaneamentoDadosDTO dto = new SectorAguaSaneamentoDadosDTO();
                dto.setProvincia((String) i[0]);
                dto.setNumeroMunicipios(((BigInteger) i[1]).longValue());
                dto.setNumeroComunas(((BigInteger) i[2]).longValue());
                dto.setAguasSim(((BigInteger) i[3]).longValue());
                dto.setTotalSector(((BigInteger) i[4]).longValue());
                if (Objects.nonNull(i[5])) {
                    dto.setNumeroFuncionam(((BigDecimal) i[5]).longValue());
                }

                retorno.add(dto);
            }
        }
        return retorno;
    }

    //COBERTURA SERVICOS DE AGUA POR MUNICIPIO
    public List<SectorAguaSaneamentoDadosDTO> montaListaDadosPorMunicipio(){
        List<SectorAguaSaneamentoDadosDTO> retorno = new ArrayList<>();
        Long populacaoTotal = 0l;

        List<Object[]> list = this.provinciaRepository.buscaDadosInqueritoPorMunicipio();
        if (Objects.nonNull(list)) {
            for (Object[] i : list) {
                populacaoTotal += ((BigDecimal) i[2]).longValue();
                SectorAguaSaneamentoDadosDTO dto = new SectorAguaSaneamentoDadosDTO();
                dto.setMunicipio((String) i[0]);
                dto.setNumeroComunas(((BigInteger) i[1]).longValue());
                dto.setPopulacao(((BigDecimal) i[2]).longValue());
                if(Objects.nonNull(((BigDecimal) i[3]))) {
                    dto.setHabitantes(((BigDecimal) i[3]).longValue());
                }
                retorno.add(dto);
            }
        }

        if (Objects.nonNull(retorno)) {
            for (SectorAguaSaneamentoDadosDTO item: retorno) {
                item.setPopulacaoPercentage((float) (item.getPopulacao()*100)/populacaoTotal);
                if (Objects.nonNull(item.getHabitantes())){
                    item.setHabitantesPercent((float) (item.getHabitantes()*100)/item.getPopulacao());
                }
            }
        }
        return retorno;
    }

    //COBERTURA SERVICOS DE AGUA POR COMUNA
    public List<SectorAguaSaneamentoDadosDTO> montaListaDadosPorComuna(){
        List<SectorAguaSaneamentoDadosDTO> retorno = new ArrayList<>();
        Long populacaoTotal = 0l;

        List<Object[]> list = this.provinciaRepository.buscaDadosInqueritoPorComuna();
        if (Objects.nonNull(list)) {
            for (Object[] i : list) {
                populacaoTotal += ((BigDecimal) i[1]).longValue();
                SectorAguaSaneamentoDadosDTO dto = new SectorAguaSaneamentoDadosDTO();
                dto.setComuna((String) i[0]);
                dto.setPopulacao(((BigDecimal) i[1]).longValue());
                if(Objects.nonNull(((BigDecimal) i[2]))) {
                    dto.setHabitantes(((BigDecimal) i[2]).longValue());
                }
                retorno.add(dto);
            }
        }

        if (Objects.nonNull(retorno)) {
            for (SectorAguaSaneamentoDadosDTO item: retorno) {
                item.setPopulacaoPercentage((float) (item.getPopulacao()*100)/populacaoTotal);
                if (Objects.nonNull(item.getHabitantes())){
                    item.setHabitantesPercent((float) (item.getHabitantes()*100)/item.getPopulacao());
                }
            }
        }
        return retorno;
    }

    public List<FuncAguaChafarizesDadosDTO> montaListaEstáticaParaTesteAguaChafarizes(){
        List<FuncAguaChafarizesDadosDTO> retorno = new ArrayList<>();
       // retorno.add(new FuncAguaChafarizesDadosDTO("Bengo", 140, 98, 42, 100, 0, 226, 188, 38, 0, 0));

        List<Object[]> list = this.provinciaRepository.buscaDadosFonteSubterraneaBombaMecanica();
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                FuncAguaChafarizesDadosDTO dto = new FuncAguaChafarizesDadosDTO();
                dto.setNomeProvincia((String) i[0]);
                dto.setNomeMunicipio((String) i[1]);
                dto.setNomeComuna((String) i[2]);
                dto.setTotalSistemas(((BigInteger) i[3]).intValue());
                dto.setElectricaSistemas(((BigInteger) i[4]).intValue());
                dto.setElectricaFuncionam(((BigInteger) i[5]).intValue());
                dto.setElectricaNaoFuncionam(((BigInteger) i[6]).intValue());
                dto.setDieselSistemas(((BigInteger) i[7]).intValue());
                dto.setDieselFuncionam(((BigInteger) i[8]).intValue());
                dto.setDieselNaoFuncionam(((BigInteger) i[9]).intValue());
                dto.setGravidadeSistemas(((BigInteger) i[10]).intValue());
                dto.setGravidadeFuncionam(((BigInteger) i[11]).intValue());
                dto.setGravidadeNaoFuncionam(((BigInteger) i[12]).intValue());
                retorno.add(dto);
            });
        }

        return retorno;
    }


    public List<InqueritosPreenchidosDadosDTO> montaListaInqueritoAguas(){
        List<InqueritosPreenchidosDadosDTO> retorno = new ArrayList<>();
        List<Object[]> list = this.provinciaRepository.buscaDadosInqueritoAguas();
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                retorno.add(new InqueritosPreenchidosDadosDTO((String) i[0],
                    ((BigInteger) i[1]).intValue(),
                    ((BigInteger) i[2]).intValue(),
                    ((BigInteger) i[3]).intValue(),
                    ((BigInteger) i[4]).intValue(),
                    ((BigInteger) i[5]).intValue(),
                    0, 0, 0,
                    ((BigInteger) i[5]).intValue(),0, 0, 0, 0, 0));
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
                TratamentoSistemasAguaDadosDTO dto = new TratamentoSistemasAguaDadosDTO();
                    dto.setSistemasAgua(((BigInteger) i[1]).intValue());
                    dto.setNomeProvincia((String) i[0]);
                    dto.setPadrao(((BigDecimal) i[2]).intValue());
                    dto.setBasico(((BigDecimal) i[3]).intValue());
                    dto.setNaoRealiza(((BigDecimal) i[4]).intValue());
                    dto.setOutros(((BigDecimal) i[5]).intValue());

                    retorno.add(dto);
            });
        }
        return retorno;
    }



}
