package com.minea.sisas.service;
import com.minea.sisas.domain.SistemaAgua;
import com.minea.sisas.domain.User;
import com.minea.sisas.domain.enumeration.TipoAcao;
import com.minea.sisas.repository.ProvinciaRepository;
import com.minea.sisas.repository.RelatorioRepository;
import com.minea.sisas.repository.UserRepository;
import com.minea.sisas.service.dto.*;
import com.minea.sisas.service.mapper.ProvinciaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
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

    private final RelatorioRepository relatorioRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    public RelatorioService(ProvinciaRepository provinciaRepository, ProvinciaMapper provinciaMapper, RelatorioRepository relatorioRepository, UserService userService) {
        this.provinciaRepository = provinciaRepository;
        this.provinciaMapper = provinciaMapper;
        this.relatorioRepository = relatorioRepository;
        this.userService = userService;
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

    //NOVOS RELATORIOS 16/03
    public List<CoberturaSectorAguaDTO> coberturaSectorAguasProvincial(){
        List<CoberturaSectorAguaDTO> retorno = new ArrayList<>();
        List<Object[]> list = this.relatorioRepository.coberturaServicosAguaProvincial();
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                CoberturaSectorAguaDTO dto = new CoberturaSectorAguaDTO();
                dto.setNomeProvincia(((String) i[0]));
                dto.setNumeroMunicipios(((BigInteger) i[1]).intValue());
                dto.setNumeroComunas(((BigInteger) i[2]).intValue());
                dto.setNumeroSistemasFuncionam(((BigInteger) i[3]).intValue());
                dto.setPopulacao(((BigInteger) i[4]).intValue());
                dto.setBeneficiarios(((BigDecimal) i[5]).intValue());
                dto.setCoberturaPercent(((BigDecimal) i[6]).floatValue());

                retorno.add(dto);
            });
        }
        return retorno;
    }

    public List<CoberturaSectorAguaDTO> coberturaSectorAguasMunicipal(){
        User userDTO = buscaUsuarioLogado();
        List<CoberturaSectorAguaDTO> retorno = new ArrayList<>();
        List<Object[]> list = this.relatorioRepository.coberturaServicosAguaMunicipal(userDTO.getProvincia().getId());
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                CoberturaSectorAguaDTO dto = new CoberturaSectorAguaDTO();
                dto.setNomeProvincia(((String) i[0]));
                dto.setNomeMunicipio(((String) i[1]));
                dto.setNumeroSistemasFuncionam(((BigInteger) i[2]).intValue());
                dto.setPopulacao(((BigInteger) i[3]).intValue());
                dto.setBeneficiarios(((BigDecimal) i[4]).intValue());
                dto.setCoberturaPercent(((BigDecimal) i[5]).floatValue());
                retorno.add(dto);
            });
        }
        return retorno;
    }

    public List<CoberturaSectorAguaDTO> coberturaSectorAguasComunal(){
        User userDTO = buscaUsuarioLogado();
        List<Object[]> list = null;
        List<CoberturaSectorAguaDTO> retorno = new ArrayList<>();
        if (Objects.nonNull(userDTO)) {
           list = this.relatorioRepository.coberturaServicosAguaComunal(userDTO.getProvincia().getId());
        }
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                CoberturaSectorAguaDTO dto = new CoberturaSectorAguaDTO();
                dto.setNomeProvincia(((String) i[0]));
                dto.setNomeMunicipio(((String) i[1]));
                dto.setNomeComuna(((String) i[2]));
                dto.setNumeroSistemasFuncionam(((BigInteger) i[3]).intValue());
                dto.setPopulacao(((BigInteger) i[4]).intValue());
                dto.setBeneficiarios(((BigDecimal) i[5]).intValue());
                dto.setCoberturaPercent(((BigDecimal) i[6]).floatValue());
                retorno.add(dto);
            });
        }
        return retorno;
    }

    //BENEFICIARIOS BOMBA ENERGIA - PROVINCIAL
    public List<BeneficiariosBmbEnergiaDTO> beneficiariosAguaBmbEnergiaProvincial() {
        List<BeneficiariosBmbEnergiaDTO> retorno = new ArrayList<>();
        List<Object[]> list = this.relatorioRepository.beneficiariosAguaBmbEnergiaProvincialQuery();
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                BeneficiariosBmbEnergiaDTO dto = new BeneficiariosBmbEnergiaDTO();


                dto.setNomeProvincia((String) i[0]);
                if (Objects.nonNull(i[1])) {
                    dto.setPopulacao(((BigInteger) i[1]).intValue());
                }
                if (Objects.nonNull(i[2])) {
                    dto.setPocoMelhorado(((BigInteger) i[2]).intValue());
                }
                if (Objects.nonNull(i[3])) {
                    dto.setFuro(((BigInteger) i[3]).intValue());
                }
                if (Objects.nonNull(i[4])) {
                    dto.setNascente(((BigInteger) i[4]).intValue());
                }
                if (Objects.nonNull(i[5])) {
                    dto.setDieselSistemas(((BigInteger) i[5]).intValue());
                }
                if (Objects.nonNull(i[6])) {
                    dto.setDieselPopulacao(((BigInteger) i[6]).intValue());
                }
                if (Objects.nonNull(i[7])) {
                    dto.setDieselPerc(((BigDecimal) i[7]).intValue());
                }
                if (Objects.nonNull(i[8])) {
                    dto.setSolarSistemas(((BigInteger) i[8]).intValue());
                }
                if (Objects.nonNull(i[9])) {
                    dto.setSolarPopulacao(((BigInteger) i[9]).intValue());
                }
                if (Objects.nonNull(i[10])) {
                    dto.setSolarPerc(((BigDecimal) i[10]).intValue());
                }
                if (Objects.nonNull(i[11])) {
                    dto.setEolicaSistemas(((BigInteger) i[11]).intValue());
                }
                if (Objects.nonNull(i[12])) {
                    dto.setEolicaPopulacao(((BigInteger) i[12]).intValue());
                }
                if (Objects.nonNull(i[13])) {
                    dto.setEolicaPerc(((BigDecimal) i[13]).intValue());
                }
                if (Objects.nonNull(i[14])) {
                    dto.setElectricaSistemas(((BigInteger) i[14]).intValue());
                }
                if (Objects.nonNull(i[15])) {
                    dto.setElectricaPopulacao(((BigInteger) i[15]).intValue());
                }
                if (Objects.nonNull(i[16])) {
                    dto.setElectricaPerc(((BigDecimal) i[16]).intValue());
                }
                if (Objects.nonNull(i[17])) {
                    dto.setOutroSistemas(((BigInteger) i[17]).intValue());
                }
                if (Objects.nonNull(i[18])) {
                    dto.setOutroPopulacao(((BigInteger) i[18]).intValue());
                }
                if (Objects.nonNull(i[19])) {
                    dto.setOutroPerc(((BigDecimal) i[19]).intValue());
                }

                retorno.add(dto);
            });
        }
        return retorno;
    }

    //BENEFICIARIOS BOMBA ENERGIA - Municipal
    public List<BeneficiariosBmbEnergiaDTO> beneficiariosAguaBmbEnergiaComunal() {
        List<BeneficiariosBmbEnergiaDTO> retorno = new ArrayList<>();
        List<Object[]> list = this.relatorioRepository.beneficiariosAguaBmbEnergiaComunalQuery();
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                BeneficiariosBmbEnergiaDTO dto = new BeneficiariosBmbEnergiaDTO();


                dto.setNomeProvincia((String) i[0]);
                dto.setNomeMunicipio((String) i[1]);
                dto.setNomeComuna((String) i[2]);

                if (Objects.nonNull(i[3])) {
                    dto.setPopulacao(((BigInteger) i[3]).intValue());
                }
                if (Objects.nonNull(i[4])) {
                    dto.setPocoMelhorado(((BigInteger) i[4]).intValue());
                }
                if (Objects.nonNull(i[5])) {
                    dto.setFuro(((BigInteger) i[5]).intValue());
                }
                if (Objects.nonNull(i[6])) {
                    dto.setNascente(((BigInteger) i[6]).intValue());
                }
                if (Objects.nonNull(i[7])) {
                    dto.setDieselSistemas(((BigInteger) i[7]).intValue());
                }
                if (Objects.nonNull(i[8])) {
                    dto.setDieselPopulacao(((BigInteger) i[8]).intValue());
                }
                if (Objects.nonNull(i[9])) {
                    dto.setDieselPerc(((BigDecimal) i[9]).intValue());
                }
                if (Objects.nonNull(i[10])) {
                    dto.setSolarSistemas(((BigInteger) i[10]).intValue());
                }
                if (Objects.nonNull(i[11])) {
                    dto.setSolarPopulacao(((BigInteger) i[11]).intValue());
                }
                if (Objects.nonNull(i[12])) {
                    dto.setSolarPerc(((BigDecimal) i[12]).intValue());
                }
                if (Objects.nonNull(i[13])) {
                    dto.setEolicaSistemas(((BigInteger) i[13]).intValue());
                }
                if (Objects.nonNull(i[14])) {
                    dto.setEolicaPopulacao(((BigInteger) i[14]).intValue());
                }
                if (Objects.nonNull(i[15])) {
                    dto.setEolicaPerc(((BigDecimal) i[15]).intValue());
                }
                if (Objects.nonNull(i[16])) {
                    dto.setElectricaSistemas(((BigInteger) i[16]).intValue());
                }
                if (Objects.nonNull(i[17])) {
                    dto.setElectricaPopulacao(((BigInteger) i[17]).intValue());
                }
                if (Objects.nonNull(i[18])) {
                    dto.setElectricaPerc(((BigDecimal) i[18]).intValue());
                }
                if (Objects.nonNull(i[19])) {
                    dto.setOutroSistemas(((BigInteger) i[19]).intValue());
                }
                if (Objects.nonNull(i[20])) {
                    dto.setOutroPopulacao(((BigInteger) i[20]).intValue());
                }
                if (Objects.nonNull(i[21])) {
                    dto.setOutroPerc(((BigDecimal) i[21]).intValue());
                }

                retorno.add(dto);
            });
        }
        return retorno;
    }

    //BENEFICIARIOS BOMBA MANUAL - Municipal
    public List<BeneficiariosBmbManualDTO> beneficiariosAguaBmbManualComunal() {
        List<BeneficiariosBmbManualDTO> retorno = new ArrayList<>();
        List<Object[]> list = this.relatorioRepository.beneficiariosAguaBmbManualComunalQuery();
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                BeneficiariosBmbManualDTO dto = new BeneficiariosBmbManualDTO();


                dto.setNomeProvincia((String) i[0]);
                dto.setNomeMunicipio((String) i[1]);
                dto.setNomeComuna((String) i[2]);

                if (Objects.nonNull(i[3])) {
                    dto.setPocoMelhorado(((BigInteger) i[3]).intValue());
                }
                if (Objects.nonNull(i[4])) {
                    dto.setFuro(((BigInteger) i[4]).intValue());
                }
                if (Objects.nonNull(i[5])) {
                    dto.setNascente(((BigInteger) i[5]).intValue());
                }
                if (Objects.nonNull(i[6])) {
                    dto.setTotalSistemas(((BigInteger) i[6]).intValue());
                }

                if (Objects.nonNull(i[7])) {
                    dto.setAfridevTotalSistema(((BigInteger) i[7]).intValue());
                }
                if (Objects.nonNull(i[8])) {
                    dto.setAfridevSistemaFunciona(((BigInteger) i[8]).intValue());
                }
                if (Objects.nonNull(i[9])) {
                    dto.setAfridevSistemaNaoFunciona(((BigInteger) i[9]).intValue());
                }

                if (Objects.nonNull(i[10])) {
                    dto.setVergnetTotalSistema(((BigInteger) i[10]).intValue());
                }
                if (Objects.nonNull(i[11])) {
                    dto.setVergnetSistemaFunciona(((BigInteger) i[11]).intValue());
                }
                if (Objects.nonNull(i[12])) {
                    dto.setVergnetSistemaNaoFunciona(((BigInteger) i[12]).intValue());
                }

                if (Objects.nonNull(i[13])) {
                    dto.setVolantTotalSistema(((BigInteger) i[13]).intValue());
                }
                if (Objects.nonNull(i[14])) {
                    dto.setVolantSistemaFunciona(((BigInteger) i[14]).intValue());
                }
                if (Objects.nonNull(i[15])) {
                    dto.setVolantSistemaNaoFunciona(((BigInteger) i[15]).intValue());
                }

                if (Objects.nonNull(i[16])) {
                    dto.setIndiaTotalSistema(((BigInteger) i[16]).intValue());
                }
                if (Objects.nonNull(i[17])) {
                    dto.setIndiaSistemaFunciona(((BigInteger) i[17]).intValue());
                }
                if (Objects.nonNull(i[18])) {
                    dto.setIndiaSistemaNaoFunciona(((BigInteger) i[18]).intValue());
                }

                if (Objects.nonNull(i[19])) {
                    dto.setOutroTotalSistema(((BigInteger) i[19]).intValue());
                }
                if (Objects.nonNull(i[20])) {
                    dto.setOutroSistemaFunciona(((BigInteger) i[20]).intValue());
                }
                if (Objects.nonNull(i[21])) {
                    dto.setOutroSistemaNaoFunciona(((BigInteger) i[21]).intValue());
                }

                retorno.add(dto);
            });
        }
        return retorno;
    }


    //FUNCIONAMENTO SERVICOS DE AGUA E CHAFARIZES - MUNICIPAL
    public List<FuncAguaChafarizesDadosDTO> funcionamentoServicosAguaChafarizesMunicipal() {
        User userDTO = buscaUsuarioLogado();
        List<FuncAguaChafarizesDadosDTO> retorno = new ArrayList<>();
        List<Object[]> list = this.relatorioRepository.funcionamentoAguaChafarizesMunicipal(userDTO.getProvincia().getId());
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                FuncAguaChafarizesDadosDTO dto = new FuncAguaChafarizesDadosDTO();

                dto.setNumeroSistemas(0);
                dto.setFuncionamAgua(0);
                dto.setNaoFuncionamAgua(0);
                dto.setFuncionamChafariz(0);
                dto.setNaoFuncionamChafariz(0);
                dto.setNumeroChafarizes(0);

                dto.setNomeProvincia((String) i[0]);
                dto.setNomeMunicipio((String) i[1]);
                if (Objects.nonNull(i[2])) {
                    dto.setNumeroSistemas(((BigInteger) i[2]).intValue());
                }
                if (Objects.nonNull(i[3])) {
                    dto.setFuncionamAgua(((BigInteger) i[3]).intValue());
                }
                if (Objects.nonNull(i[4])) {
                    dto.setNaoFuncionamAgua(((BigInteger) i[4]).intValue());
                }
                if (Objects.nonNull(i[5])) {
                    dto.setNumeroChafarizes(((BigDecimal) i[5]).intValue());
                }
                if (Objects.nonNull(i[6])) {
                    dto.setFuncionamChafariz(((BigDecimal) i[6]).intValue());
                }
                if (Objects.nonNull(i[7])) {
                    dto.setNaoFuncionamChafariz(((BigDecimal) i[7]).intValue());
                }

                dto.setFuncionamAguaPerc(0f);
                dto.setNaoFuncionamAguaPerc(0f);
                dto.setFuncionamChafarizPerc(0f);
                dto.setNaoFuncionamChafarizPerc(0f);

                //PERCENTAGEM Agua funcionam
                if (Objects.nonNull(dto.getFuncionamAgua()) && Objects.nonNull(dto.getNumeroSistemas())) {
                    if (dto.getFuncionamAgua() != 0 && dto.getNumeroSistemas() != 0) {
                        dto.setFuncionamAguaPerc((float) (dto.getFuncionamAgua()*100)/dto.getNumeroSistemas());
                    }
                }

                //PERCENTAGEM Agua nao funcionam
                if (Objects.nonNull(dto.getNaoFuncionamAgua()) && Objects.nonNull(dto.getNumeroSistemas())) {
                    if (dto.getNaoFuncionamAgua() != 0 && dto.getNumeroSistemas() != 0) {
                        dto.setFuncionamAguaPerc((float) (dto.getNaoFuncionamAgua()*100)/dto.getNumeroSistemas());
                    }
                }

                //PERCENTAGEM Chafariz funcionam
                if (Objects.nonNull(dto.getFuncionamChafariz()) && Objects.nonNull(dto.getNumeroChafarizes())) {
                    if (dto.getFuncionamChafariz() != 0 && dto.getNumeroChafarizes() != 0) {
                        dto.setFuncionamAguaPerc((float) (dto.getFuncionamChafariz()*100)/dto.getNumeroChafarizes());
                    }
                }

                //PERCENTAGEM Chafariz nao funcionam
                if (Objects.nonNull(dto.getNaoFuncionamChafariz()) && Objects.nonNull(dto.getNumeroChafarizes())) {
                    if (dto.getNaoFuncionamChafariz() != 0 && dto.getNumeroChafarizes() != 0) {
                        dto.setFuncionamAguaPerc((float) (dto.getNaoFuncionamChafariz()*100)/dto.getNumeroChafarizes());
                    }
                }

                retorno.add(dto);
            });
        }
        return retorno;
    }

    //DASHBOARD
    public List<DashboardDTO> dashboard(){
        User userDTO = buscaUsuarioLogado();
        List<DashboardDTO> retorno = new ArrayList<>();
        List<Object[]> list = this.relatorioRepository.dadosDashboard(userDTO.getProvincia().getId());

        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                DashboardDTO dto = new DashboardDTO();
                dto.setNomeProvincia(((String) i[0]));
                dto.setNumeroSistemas(((BigInteger) i[1]).intValue());
                dto.setNumeroSistemasFuncionam(((BigInteger) i[2]).intValue());
                if (Objects.nonNull(i[3])) {
                    dto.setSistemasFuncionamPerc(((BigDecimal) i[3]).floatValue());
                }
                dto.setNumeroSistemasNaoFuncionam(((BigInteger) i[4]).intValue());
                if (Objects.nonNull(i[5])) {
                    dto.setSistemasNaoFuncionamPerc(((BigDecimal) i[5]).floatValue());
                }

                retorno.add(dto);
            });
        }
        return retorno;
    }

    //TRATAMENTO SISTEMAS DE AGUA PROVINCIAL
    public List<TratamentoSistemasAguaDadosDTO> tratamentoSistemasAguasProvincial(){
        List<TratamentoSistemasAguaDadosDTO> retorno = new ArrayList<>();

        List<Object[]> list = this.relatorioRepository.buscaDadosTratamentoSistemasAguaProvincial();
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                TratamentoSistemasAguaDadosDTO dto = new TratamentoSistemasAguaDadosDTO();
                dto.setNomeProvincia((String) i[0]);
                dto.setSistemasAgua(((BigInteger) i[1]).intValue());
                dto.setPadrao(((BigInteger) i[2]).intValue());
                dto.setBasico(((BigInteger) i[3]).intValue());
                dto.setNaoRealiza(((BigInteger) i[4]).intValue());
                dto.setOutros(0);
                retorno.add(dto);
            });
        }
        return retorno;
    }
    //MUNICIPAL
    public List<TratamentoSistemasAguaDadosDTO> tratamentoSistemasAguasMunicipal(){
        User userDTO = buscaUsuarioLogado();
        List<TratamentoSistemasAguaDadosDTO> retorno = new ArrayList<>();
        List<Object[]> list = this.relatorioRepository.buscaDadosTratamentoSistemasAguaMunicipal(userDTO.getProvincia().getId());
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                TratamentoSistemasAguaDadosDTO dto = new TratamentoSistemasAguaDadosDTO();
                dto.setNomeProvincia((String) i[0]);
                dto.setNomeMunicipio((String) i[1]);
                dto.setSistemasAgua(((BigInteger) i[2]).intValue());
                dto.setPadrao(((BigInteger) i[3]).intValue());
                dto.setBasico(((BigInteger) i[4]).intValue());
                dto.setNaoRealiza(((BigInteger) i[5]).intValue());
                dto.setOutros(0);
                retorno.add(dto);
            });
        }
        return retorno;
    }

    //COMUNAL
    public List<TratamentoSistemasAguaDadosDTO> tratamentoSistemasAguasComunal(){
        User userDTO = buscaUsuarioLogado();
        List<TratamentoSistemasAguaDadosDTO> retorno = new ArrayList<>();
        List<Object[]> list = this.relatorioRepository.buscaDadosTratamentoSistemasAguaComunal(userDTO.getProvincia().getId());
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                TratamentoSistemasAguaDadosDTO dto = new TratamentoSistemasAguaDadosDTO();
                dto.setNomeProvincia((String) i[0]);
                dto.setNomeMunicipio((String) i[1]);
                dto.setNomeComuna((String) i[2]);
                dto.setSistemasAgua(((BigInteger) i[3]).intValue());
                dto.setPadrao(((BigInteger) i[4]).intValue());
                dto.setBasico(((BigInteger) i[5]).intValue());
                dto.setNaoRealiza(((BigInteger) i[6]).intValue());
                dto.setOutros(0);
                retorno.add(dto);
            });
        }
        return retorno;
    }

    //FUNCIONAMENTO DE SISTEMAS DE AGUA
    public List<FuncSistemasAguaDTO> funcionamentoServicosAguaProvincial() {
        List<FuncSistemasAguaDTO> retorno = new ArrayList<>();
        List<Object[]> list = this.relatorioRepository.buscaDadosFuncionamentoSistemasAguaProvincial();
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                FuncSistemasAguaDTO dto = new FuncSistemasAguaDTO();
                dto.setNomeProvincia((String) i[0]);
                dto.setBeneficiariosAgua(((BigDecimal) i[1]).intValue());
                dto.setNumeroSistemas(((BigInteger) i[2]).intValue());
                dto.setFuncionamAgua(((BigInteger) i[3]).intValue());
                dto.setNaoFuncionamAgua(((BigInteger) i[4]).floatValue());
                dto.setFuncionamAguaPerc(((BigDecimal) i[5]).floatValue());
                dto.setNaoFuncionamAguaPerc(((BigDecimal) i[6]).intValue());
                retorno.add(dto);
            });
        }
        return retorno;
    }

    //MUNICIPAL
    public List<FuncSistemasAguaDTO> funcionamentoServicosAguaMunicipal() {
        User userDTO = buscaUsuarioLogado();
        List<FuncSistemasAguaDTO> retorno = new ArrayList<>();
        List<Object[]> list = this.relatorioRepository.buscaDadosFuncionamentoSistemasAguaMunicipal(userDTO.getProvincia().getId());
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                FuncSistemasAguaDTO dto = new FuncSistemasAguaDTO();
                dto.setNomeProvincia((String) i[0]);
                dto.setNomeMunicipio((String) i[1]);
                dto.setBeneficiariosAgua(((BigDecimal) i[2]).intValue());
                dto.setNumeroSistemas(((BigInteger) i[3]).intValue());
                dto.setFuncionamAgua(((BigInteger) i[4]).intValue());
                dto.setNaoFuncionamAgua(((BigInteger) i[5]).floatValue());
                dto.setFuncionamAguaPerc(((BigDecimal) i[6]).floatValue());
                dto.setNaoFuncionamAguaPerc(((BigDecimal) i[7]).intValue());
                retorno.add(dto);
            });
        }
        return retorno;
    }
    //COMUNAL
    public List<FuncSistemasAguaDTO> funcionamentoServicosAguaComunal() {
        User userDTO = buscaUsuarioLogado();
        List<FuncSistemasAguaDTO> retorno = new ArrayList<>();
        List<Object[]> list = this.relatorioRepository.buscaDadosFuncionamentoSistemasAguaComunal(userDTO.getProvincia().getId());
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                FuncSistemasAguaDTO dto = new FuncSistemasAguaDTO();
                dto.setNomeProvincia((String) i[0]);
                dto.setNomeMunicipio((String) i[1]);
                dto.setNomeComuna((String) i[2]);
                dto.setBeneficiariosAgua(((BigDecimal) i[3]).intValue());
                dto.setNumeroSistemas(((BigInteger) i[4]).intValue());
                dto.setFuncionamAgua(((BigInteger) i[5]).intValue());
                dto.setNaoFuncionamAgua(((BigInteger) i[6]).floatValue());
                dto.setFuncionamAguaPerc(((BigDecimal) i[7]).floatValue());
                dto.setNaoFuncionamAguaPerc(((BigDecimal) i[8]).intValue());
                retorno.add(dto);
            });
        }
        return retorno;
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





}
