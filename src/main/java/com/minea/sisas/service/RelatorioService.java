package com.minea.sisas.service;
import com.minea.sisas.domain.Authority;
import com.minea.sisas.domain.User;
import com.minea.sisas.repository.ProvinciaRepository;
import com.minea.sisas.repository.RelatorioAdminRepository;
import com.minea.sisas.repository.RelatorioRepository;
import com.minea.sisas.repository.UserRepository;
import com.minea.sisas.service.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    private final RelatorioRepository relatorioRepository;

    @Autowired
    private final RelatorioAdminRepository relatorioAdminRepository;

    @Autowired
    private UserRepository userRepository;

    public RelatorioService(ProvinciaRepository provinciaRepository, RelatorioRepository relatorioRepository, RelatorioAdminRepository relatorioAdminRepository) {
        this.provinciaRepository = provinciaRepository;
        this.relatorioRepository = relatorioRepository;
        this.relatorioAdminRepository = relatorioAdminRepository;
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

    private String setWhereClausule(User user){
        StringBuffer sb = new StringBuffer();
        boolean isAdminGeral = false;
        sb.append(" WHERE s.POSSUI_SISTEMA_AGUA = 1");

        for (Authority permissao: user.getAuthorities() ) {
            if (permissao.getName().equals("ROLE_ADMIN")) {
                isAdminGeral = true;
            }
        }
        if (!isAdminGeral) {
            sb.append(" AND s.ID_PROVINCIA = ").append(user.getProvincia().getId());
        }

        return sb.toString();
    }

    private boolean isAdminGeral(User user){
        if (Objects.nonNull(user) && Objects.nonNull(user.getAuthorities())) {
            for (Authority permissao: user.getAuthorities() ) {
                if (permissao.getName().equals("ROLE_ADMIN")) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<SectorAguaSaneamentoDadosDTO> montaListaDadosPorAmbito(){
        List<SectorAguaSaneamentoDadosDTO> retorno = new ArrayList<>();
        Long populacaoTotal = 0l;
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

    public List<InqueritosPreenchidosDadosDTO> montaListaInqueritoAguas(){
        List<InqueritosPreenchidosDadosDTO> retorno = new ArrayList<>();
        List<Object[]> list = this.provinciaRepository.buscaDadosEstatisticaInqueritosPreenchidos();
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                InqueritosPreenchidosDadosDTO dto = new InqueritosPreenchidosDadosDTO();
                dto.setNomeProvincia((String) i[0]);
                dto.setMunicipios(((BigInteger) i[1]).intValue());
                dto.setComunas(((BigInteger) i[2]).intValue());
                dto.setAguasSim(((BigInteger) i[3]).intValue());
                dto.setAguasNao(((BigInteger) i[4]).intValue());
                dto.setTotalInqueritoSector(((BigInteger) i[5]).intValue());
                retorno.add(dto);
            });
        }
        return retorno;
    }

    //NOVOS RELATORIOS 16/03
    public List<CoberturaSectorAguaDTO> coberturaSectorAguasProvincial(){
        User user = buscaUsuarioLogado();
        List<CoberturaSectorAguaDTO> retorno = new ArrayList<>();
        List<Object[]> list;
        if (isAdminGeral(user)) {
            list = this.relatorioAdminRepository.coberturaServicosAguaProvincial();
        } else {
            list = this.relatorioRepository.coberturaServicosAguaProvincial(user.getProvincia().getId());
        }
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
        User user = buscaUsuarioLogado();
        List<Object[]> list;
        List<CoberturaSectorAguaDTO> retorno = new ArrayList<>();
        if (isAdminGeral(user)){
            list = this.relatorioAdminRepository.coberturaServicosAguaMunicipal();
        } else {
            list = this.relatorioRepository.coberturaServicosAguaMunicipal(user.getProvincia().getId());
        }
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
        List<Object[]> list;
        List<CoberturaSectorAguaDTO> retorno = new ArrayList<>();
        if (isAdminGeral(userDTO)) {
           list = this.relatorioAdminRepository.coberturaServicosAguaComunal();
        } else {
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
        User userDTO = buscaUsuarioLogado();
        List<Object[]> list;
        List<BeneficiariosBmbEnergiaDTO> retorno = new ArrayList<>();
        if (isAdminGeral(userDTO)) {
            list = this.relatorioAdminRepository.beneficiariosAguaBmbEnergiaProvincialQuery();
        } else {
            list = this.relatorioRepository.beneficiariosAguaBmbEnergiaProvincialQuery(userDTO.getProvincia().getId());
        }

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
                    dto.setDieselPopulacao(((BigDecimal) i[6]).intValue());
                }
                if (Objects.nonNull(i[7])) {
                    dto.setDieselPerc(((BigDecimal) i[7]).intValue());
                }
                if (Objects.nonNull(i[8])) {
                    dto.setSolarSistemas(((BigInteger) i[8]).intValue());
                }
                if (Objects.nonNull(i[9])) {
                    dto.setSolarPopulacao(((BigDecimal) i[9]).intValue());
                }
                if (Objects.nonNull(i[10])) {
                    dto.setSolarPerc(((BigDecimal) i[10]).intValue());
                }
                if (Objects.nonNull(i[11])) {
                    dto.setEolicaSistemas(((BigInteger) i[11]).intValue());
                }
                if (Objects.nonNull(i[12])) {
                    dto.setEolicaPopulacao(((BigDecimal) i[12]).intValue());
                }
                if (Objects.nonNull(i[13])) {
                    dto.setEolicaPerc(((BigDecimal) i[13]).intValue());
                }
                if (Objects.nonNull(i[14])) {
                    dto.setElectricaSistemas(((BigInteger) i[14]).intValue());
                }
                if (Objects.nonNull(i[15])) {
                    dto.setElectricaPopulacao(((BigDecimal) i[15]).intValue());
                }
                if (Objects.nonNull(i[16])) {
                    dto.setElectricaPerc(((BigDecimal) i[16]).intValue());
                }
                if (Objects.nonNull(i[17])) {
                    dto.setOutroSistemas(((BigInteger) i[17]).intValue());
                }
                if (Objects.nonNull(i[18])) {
                    dto.setOutroPopulacao(((BigDecimal) i[18]).intValue());
                }
                if (Objects.nonNull(i[19])) {
                    dto.setOutroPerc(((BigDecimal) i[19]).intValue());
                }

                retorno.add(dto);
            });
        }
        return retorno;
    }

    //BENEFICIARIOS BOMBA ENERGIA - Comunal
    public List<BeneficiariosBmbEnergiaDTO> beneficiariosAguaBmbEnergiaComunal() {
        User userDTO = buscaUsuarioLogado();
        List<Object[]> list;
        List<BeneficiariosBmbEnergiaDTO> retorno = new ArrayList<>();
        if (isAdminGeral(userDTO)) {
            list = this.relatorioAdminRepository.beneficiariosAguaBmbEnergiaComunalQuery();
        } else {
            list = this.relatorioRepository.beneficiariosAguaBmbEnergiaComunalQuery(userDTO.getProvincia().getId());
        }

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
                    dto.setDieselPopulacao(((BigDecimal) i[8]).intValue());
                }
                if (Objects.nonNull(i[9])) {
                    dto.setDieselPerc(((BigDecimal) i[9]).intValue());
                }
                if (Objects.nonNull(i[10])) {
                    dto.setSolarSistemas(((BigInteger) i[10]).intValue());
                }
                if (Objects.nonNull(i[11])) {
                    dto.setSolarPopulacao(((BigDecimal) i[11]).intValue());
                }
                if (Objects.nonNull(i[12])) {
                    dto.setSolarPerc(((BigDecimal) i[12]).intValue());
                }
                if (Objects.nonNull(i[13])) {
                    dto.setEolicaSistemas(((BigInteger) i[13]).intValue());
                }
                if (Objects.nonNull(i[14])) {
                    dto.setEolicaPopulacao(((BigDecimal) i[14]).intValue());
                }
                if (Objects.nonNull(i[15])) {
                    dto.setEolicaPerc(((BigDecimal) i[15]).intValue());
                }
                if (Objects.nonNull(i[16])) {
                    dto.setElectricaSistemas(((BigInteger) i[16]).intValue());
                }
                if (Objects.nonNull(i[17])) {
                    dto.setElectricaPopulacao(((BigDecimal) i[17]).intValue());
                }
                if (Objects.nonNull(i[18])) {
                    dto.setElectricaPerc(((BigDecimal) i[18]).intValue());
                }
                if (Objects.nonNull(i[19])) {
                    dto.setOutroSistemas(((BigInteger) i[19]).intValue());
                }
                if (Objects.nonNull(i[20])) {
                    dto.setOutroPopulacao(((BigDecimal) i[20]).intValue());
                }
                if (Objects.nonNull(i[21])) {
                    dto.setOutroPerc(((BigDecimal) i[21]).intValue());
                }

                retorno.add(dto);
            });
        }
        return retorno;
    }

    //SISTEMA AGUA BOMBA MANUAL - Comunal
    public List<SistemaAguaBmbManualDTO> sistemaAguaBmbManualComunal() {
        List<SistemaAguaBmbManualDTO> retorno = new ArrayList<>();
        List<Object[]> list = this.relatorioRepository.sistemaAguaBmbManualComunalQuery();
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                SistemaAguaBmbManualDTO dto = new SistemaAguaBmbManualDTO();

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

    //SISTEMA AGUA BOMBA GRAVIDADE - Comunal
    public List<SistemaAguaBmbGravidadeDTO> sistemaAguaBmbGravidadeComunal() {
        User user = buscaUsuarioLogado();
        List<Object[]> list;
        List<SistemaAguaBmbGravidadeDTO> retorno = new ArrayList<>();
        if (isAdminGeral(user)) {
            list = this.relatorioAdminRepository.sistemaAguaBmbGravidadeComunal();
        } else {
            list = this.relatorioRepository.sistemaAguaBmbGravidadeComunal(user.getProvincia().getId());
        }
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                SistemaAguaBmbGravidadeDTO dto = new SistemaAguaBmbGravidadeDTO();
                dto.setNomeProvincia((String) i[0]);
                dto.setNomeMunicipio((String) i[1]);
                dto.setNomeComuna((String) i[2]);
                dto.setPocoMelhorado(((BigInteger) i[3]).intValue());
                dto.setFuro(((BigInteger) i[4]).intValue());
                dto.setNascente(((BigInteger) i[5]).intValue());
                dto.setTotalSistemas(((BigInteger) i[6]).intValue());
                dto.setGravidadeTotalSistema(((BigInteger) i[7]).intValue());
                dto.setGravidadeSistemaFunciona(((BigInteger) i[8]).intValue());
                dto.setGravidadeSistemaNaoFunciona(((BigInteger) i[9]).intValue());
                dto.setOutroTotalSistema(((BigInteger) i[10]).intValue());
                dto.setOutroSistemaFunciona(((BigInteger) i[11]).intValue());
                dto.setOutroSistemaNaoFunciona(((BigInteger) i[12]).intValue());

                retorno.add(dto);
            });
        }
        return retorno;
    }

    //SISTEMA AGUA BOMBA SUPERCIFICIAL OPCAO TECNICA - Comunal
    public List<SistemaAguaSuperOpcaoTecnicaDTO> sistemaAguaSuperOpcaoTecnicaComunal() {
        User user = buscaUsuarioLogado();
        List<Object[]> list;
        List<SistemaAguaSuperOpcaoTecnicaDTO> retorno = new ArrayList<>();
        if (isAdminGeral(user)) {
            list = this.relatorioAdminRepository.sistemaAguaSuperOpcaoTecnicaComunal();
        } else {
            list = this.relatorioRepository.sistemaAguaSuperOpcaoTecnicaComunal(user.getProvincia().getId());
        }
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                SistemaAguaSuperOpcaoTecnicaDTO dto = new SistemaAguaSuperOpcaoTecnicaDTO();
                dto.setNomeProvincia((String) i[0]);
                dto.setNomeMunicipio((String) i[1]);
                dto.setNomeComuna((String) i[2]);
                dto.setTotalSistemas(((BigInteger) i[3]).intValue());
                dto.setElectricaTotalSistema(((BigInteger) i[4]).intValue());
                dto.setElectricaSistemaFunciona(((BigInteger) i[5]).intValue());
                dto.setElectricaSistemaNaoFunciona(((BigInteger) i[6]).intValue());
                dto.setDieselTotalSistema(((BigInteger) i[7]).intValue());
                dto.setDieselSistemaFunciona(((BigInteger) i[8]).intValue());
                dto.setDieselSistemaNaoFunciona(((BigInteger) i[9]).intValue());
                dto.setGravidadeTotalSistema(((BigInteger) i[10]).intValue());
                dto.setGravidadeSistemaFunciona(((BigInteger) i[11]).intValue());
                dto.setGravidadeSistemaNaoFunciona(((BigInteger) i[12]).intValue());
                retorno.add(dto);
            });
        }
        return retorno;
    }

    //FUNCIONAMENTO SERVICOS DE AGUA E CHAFARIZES
    public List<FuncAguaChafarizesDadosDTO> funcionamentoServicosAguaChafarizesProvincial() {
        User user = buscaUsuarioLogado();
        List<Object[]> list;
        List<FuncAguaChafarizesDadosDTO> retorno = new ArrayList<>();
        if (isAdminGeral(user)) {
            list = this.relatorioAdminRepository.funcionamentoAguaChafarizesProvincial();
        } else {
            list = this.relatorioRepository.funcionamentoAguaChafarizesProvincial(user.getProvincia().getId());
        }
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                FuncAguaChafarizesDadosDTO dto = new FuncAguaChafarizesDadosDTO();
                dto.setNomeProvincia((String) i[0]);
                dto.setNumeroSistemas(((BigInteger) i[1]).intValue());
                dto.setFuncionamAgua(((BigInteger) i[2]).intValue());
                dto.setNaoFuncionamAgua(((BigInteger) i[3]).intValue());
                dto.setFuncionamAguaPerc(((BigDecimal) i[4]).floatValue());
                dto.setNumeroChafarizes(((BigDecimal) i[5]).intValue());
                dto.setFuncionamChafariz(((BigDecimal) i[6]).intValue());
                dto.setNaoFuncionamChafariz(((BigDecimal) i[7]).intValue());
                dto.setNaoFuncionamChafarizPerc(((Double) i[8]).floatValue());
                retorno.add(dto);
            });
        }
        return retorno;
    }

    public List<FuncAguaChafarizesDadosDTO> funcionamentoServicosAguaChafarizesMunicipal() {
        User user = buscaUsuarioLogado();
        List<Object[]> list;
        List<FuncAguaChafarizesDadosDTO> retorno = new ArrayList<>();
        if (isAdminGeral(user)) {
            list = this.relatorioAdminRepository.funcionamentoAguaChafarizesMunicipal();
        } else {
            list = this.relatorioRepository.funcionamentoAguaChafarizesMunicipal(user.getProvincia().getId());
        }
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                FuncAguaChafarizesDadosDTO dto = new FuncAguaChafarizesDadosDTO();
                dto.setNomeProvincia((String) i[0]);
                dto.setNomeMunicipio((String) i[1]);
                dto.setNumeroSistemas(((BigInteger) i[2]).intValue());
                dto.setFuncionamAgua(((BigInteger) i[3]).intValue());
                dto.setNaoFuncionamAgua(((BigInteger) i[4]).intValue());
                dto.setFuncionamAguaPerc(((BigDecimal) i[5]).floatValue());
                dto.setNumeroChafarizes(((BigDecimal) i[6]).intValue());
                dto.setFuncionamChafariz(((BigDecimal) i[7]).intValue());
                dto.setNaoFuncionamChafariz(((BigDecimal) i[8]).intValue());
                if (Objects.nonNull(i[9])){
                    dto.setNaoFuncionamChafarizPerc(((Double) i[9]).floatValue());
                }
                retorno.add(dto);
            });
        }
        return retorno;
    }

    public List<FuncAguaChafarizesDadosDTO> funcionamentoServicosAguaChafarizesComunal() {
        User user = buscaUsuarioLogado();
        List<Object[]> list;
        List<FuncAguaChafarizesDadosDTO> retorno = new ArrayList<>();
        if (isAdminGeral(user)) {
            list = this.relatorioAdminRepository.funcionamentoAguaChafarizesComunal();
        } else {
            list = this.relatorioRepository.funcionamentoAguaChafarizesComunal(user.getProvincia().getId());
        }
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                FuncAguaChafarizesDadosDTO dto = new FuncAguaChafarizesDadosDTO();
                dto.setNomeProvincia((String) i[0]);
                dto.setNomeMunicipio((String) i[1]);
                dto.setNomeComuna((String) i[2]);
                dto.setNumeroSistemas(Integer.valueOf(((String) i[3])));
                dto.setFuncionamAgua(((BigInteger) i[4]).intValue());
                dto.setNaoFuncionamAgua(((BigInteger) i[5]).intValue());
                dto.setFuncionamAguaPerc(((Double) i[6]).floatValue());
                dto.setNumeroChafarizes(((BigDecimal) i[7]).intValue());
                dto.setFuncionamChafariz(((BigDecimal) i[8]).intValue());
                dto.setNaoFuncionamChafariz(((BigDecimal) i[9]).intValue());
                if (Objects.nonNull(i[10])){
                    dto.setNaoFuncionamChafarizPerc(((Double) i[10]).floatValue());
                }
                retorno.add(dto);
            });
        }
        return retorno;
    }

    //DASHBOARD
    public List<DashboardDTO> dashboard(){
        User user = buscaUsuarioLogado();
        List<DashboardDTO> retorno = new ArrayList<>();
        List<Object[]> list;
        if (isAdminGeral(user)) {
            list = this.relatorioAdminRepository.dadosDashboard();
        } else {
            list = this.relatorioRepository.dadosDashboard(user.getProvincia().getId());
        }
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
        User user = buscaUsuarioLogado();
        List<Object[]> list;
        List<TratamentoSistemasAguaDadosDTO> retorno = new ArrayList<>();

        if (isAdminGeral(user)) {
            list = this.relatorioAdminRepository.buscaDadosTratamentoSistemasAguaProvincial();
        } else {
            list = this.relatorioRepository.buscaDadosTratamentoSistemasAguaProvincial(user.getProvincia().getId());
        }
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
        User user = buscaUsuarioLogado();
        List<Object[]> list;
        List<TratamentoSistemasAguaDadosDTO> retorno = new ArrayList<>();
        if (isAdminGeral(user)) {
            list = this.relatorioAdminRepository.buscaDadosTratamentoSistemasAguaMunicipal();
        } else {
            list = this.relatorioRepository.buscaDadosTratamentoSistemasAguaMunicipal(user.getProvincia().getId());
        }
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
        User user = buscaUsuarioLogado();
        List<Object[]> list;
        List<TratamentoSistemasAguaDadosDTO> retorno = new ArrayList<>();
        if (isAdminGeral(user)) {
            list = this.relatorioAdminRepository.buscaDadosTratamentoSistemasAguaComunal();
        } else {
            list = this.relatorioRepository.buscaDadosTratamentoSistemasAguaComunal(user.getProvincia().getId());
        }
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
        User user = buscaUsuarioLogado();
        List<Object[]> list;
        List<FuncSistemasAguaDTO> retorno = new ArrayList<>();
        if (isAdminGeral(user)) {
            list = this.relatorioAdminRepository.buscaDadosFuncionamentoSistemasAguaProvincial();
        } else {
            list = this.relatorioRepository.buscaDadosFuncionamentoSistemasAguaProvincial(user.getProvincia().getId());
        }
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
        User user = buscaUsuarioLogado();
        List<Object[]> list;
        List<FuncSistemasAguaDTO> retorno = new ArrayList<>();
        if (isAdminGeral(user)) {
            list = this.relatorioAdminRepository.buscaDadosFuncionamentoSistemasAguaMunicipal();
        } else {
            list = this.relatorioRepository.buscaDadosFuncionamentoSistemasAguaMunicipal(user.getProvincia().getId());
        }
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
        User user = buscaUsuarioLogado();
        List<Object[]> list;
        List<FuncSistemasAguaDTO> retorno = new ArrayList<>();
        if (isAdminGeral(user)) {
            list = this.relatorioAdminRepository.buscaDadosFuncionamentoSistemasAguaComunal();
        } else {
            list = this.relatorioRepository.buscaDadosFuncionamentoSistemasAguaComunal(user.getProvincia().getId());
        }
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


    // BENEFICIARIOS DE AGUA POR FONTE SUBTERRANEA E POR TIPO DE BOMBA
    public List<BeneAguaFtSubterraneaTpBomba> beneficiariosFtSubtTpBombaComunal() {
        User user = buscaUsuarioLogado();
        List<Object[]> list;
        List<BeneAguaFtSubterraneaTpBomba> retorno = new ArrayList<>();
        if (isAdminGeral(user)) {
            list = this.relatorioAdminRepository.buscaDadosBenefAguaFonteSubterraneaTipoBombaComunal();
        } else {
            list = this.relatorioRepository.buscaDadosBenefAguaFonteSubterraneaTipoBombaComunal(user.getProvincia().getId());
        }
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                BeneAguaFtSubterraneaTpBomba dto = new BeneAguaFtSubterraneaTpBomba();
                dto.setNomeProvincia((String) i[0]);
                dto.setNomeMunicipio((String) i[1]);
                dto.setNomeComuna((String) i[2]);
                dto.setNumeroPocoMelhorado(((BigInteger) i[3]).intValue());
                dto.setFuro(((BigInteger) i[4]).intValue());
                dto.setNascente(((BigInteger) i[5]).intValue());
                dto.setTotalBombaGravidade(((BigInteger) i[6]).intValue());
                dto.setPopulacaoBeneficiadaGravidade(((BigDecimal) i[7]).intValue());
                dto.setTotalTipoBombaOutros(((BigInteger) i[8]).intValue());
                dto.setQtdPopulacaoOutros(((BigDecimal) i[9]).intValue());
                retorno.add(dto);
            });
        }
        return retorno;
    }

    public List<BeneAguaFtSubterraneaTpBomba> beneficiariosFtSubtTpBombaMunicipal() {
        User user = buscaUsuarioLogado();
        List<Object[]> list;
        List<BeneAguaFtSubterraneaTpBomba> retorno = new ArrayList<>();
        if (isAdminGeral(user)) {
           list = this.relatorioAdminRepository.buscaDadosBenefAguaFonteSubterraneaTipoBombaMunicipal();
        } else {
           list = this.relatorioRepository.buscaDadosBenefAguaFonteSubterraneaTipoBombaMunicipal(user.getProvincia().getId());
        }
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                BeneAguaFtSubterraneaTpBomba dto = new BeneAguaFtSubterraneaTpBomba();
                dto.setNascente(0);
                dto.setNomeProvincia((String) i[0]);
                dto.setNomeMunicipio((String) i[1]);
                dto.setNumeroPocoMelhorado(((BigInteger) i[2]).intValue());
                dto.setFuro(((BigInteger) i[3]).intValue());
                if (Objects.nonNull(i[4])) {
                    dto.setNascente(((BigInteger) i[4]).intValue());
                }
                dto.setTotalBombaGravidade(((BigInteger) i[5]).intValue());
                dto.setPopulacaoBeneficiadaGravidade(((BigInteger) i[6]).intValue());
                dto.setTotalTipoBombaOutros(((BigDecimal) i[7]).intValue());
                dto.setQtdPopulacaoOutros(((BigInteger) i[8]).intValue());
                retorno.add(dto);
            });
        }
        return retorno;
    }

    //BENEFICIARIOS FONTE SUBTERRANE E TIPO DE BOMBA MANUAL
    public List<BeneAguaFtSubterraneaTpBombaManual> beneficiariosFtSubtTpBombaManualProvincial() {
        User user = buscaUsuarioLogado();
        List<Object[]> list;
        List<BeneAguaFtSubterraneaTpBombaManual> retorno = new ArrayList<>();
        if (isAdminGeral(user)) {
            list = this.relatorioAdminRepository.buscaDadosBenefAguaFonteSubterraneaTipoBombaManualProvincial();
        } else {
            list = this.relatorioRepository.buscaDadosBenefAguaFonteSubterraneaTipoBombaManualProvincial(user.getProvincia().getId());
        }
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                BeneAguaFtSubterraneaTpBombaManual dto = new BeneAguaFtSubterraneaTpBombaManual();
                dto.setNomeProvincia((String) i[0]);
                dto.setPopulacao(((BigInteger) i[1]).intValue());
                dto.setNumeroPocoMelhorado(((BigInteger) i[2]).intValue());
                dto.setFuro(((BigInteger) i[3]).intValue());
                dto.setNascente(((BigInteger) i[4]).intValue());
                dto.setAfridev(((BigInteger) i[5]).intValue());
                dto.setAfridevPopulacao(((BigDecimal) i[6]).intValue());
                dto.setVergnet(((BigInteger) i[7]).intValue());
                dto.setVergnetPopulacao(((BigDecimal) i[8]).intValue());
                dto.setVolanta(((BigInteger) i[9]).intValue());
                dto.setVolantaPopulacao(((BigDecimal) i[10]).intValue());
                dto.setIndiaMarc(((BigInteger) i[11]).intValue());
                dto.setIndiaMarcPopulacao(((BigDecimal) i[12]).intValue());
                dto.setOutro(((BigInteger) i[13]).intValue());
                dto.setOutroPopulacao(((BigDecimal) i[14]).intValue());
                retorno.add(dto);
            });
        }
        return retorno;
    }

    public List<BeneAguaFtSubterraneaTpBombaManual> beneficiariosFtSubtTpBombaManualMunicipal() {
        User user = buscaUsuarioLogado();
        List<Object[]> list;
        List<BeneAguaFtSubterraneaTpBombaManual> retorno = new ArrayList<>();
        if (isAdminGeral(user)) {
            list = this.relatorioAdminRepository.buscaDadosBenefAguaFonteSubterraneaTipoBombaManualMunicipal();
        } else {
            list = this.relatorioRepository.buscaDadosBenefAguaFonteSubterraneaTipoBombaManualMunicipal(user.getProvincia().getId());
        }
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                BeneAguaFtSubterraneaTpBombaManual dto = new BeneAguaFtSubterraneaTpBombaManual();
                dto.setNomeProvincia((String) i[0]);
                dto.setNomeMunicipio((String) i[1]);
                dto.setPopulacao(((BigInteger) i[2]).intValue());
                dto.setNumeroPocoMelhorado(((BigInteger) i[3]).intValue());
                dto.setFuro(((BigInteger) i[4]).intValue());
                dto.setNascente(((BigInteger) i[5]).intValue());
                dto.setAfridev(((BigInteger) i[6]).intValue());
                dto.setAfridevPopulacao(((BigDecimal) i[7]).intValue());
                dto.setVergnet(((BigInteger) i[8]).intValue());
                dto.setVergnetPopulacao(((BigDecimal) i[9]).intValue());
                dto.setVolanta(((BigInteger) i[10]).intValue());
                dto.setVolantaPopulacao(((BigDecimal) i[11]).intValue());
                dto.setIndiaMarc(((BigInteger) i[12]).intValue());
                dto.setIndiaMarcPopulacao(((BigDecimal) i[13]).intValue());
                dto.setOutro(((BigInteger) i[14]).intValue());
                dto.setOutroPopulacao(((BigDecimal) i[15]).intValue());
                retorno.add(dto);
            });
        }
        return retorno;
    }

    //BENEFICIARIOS DE AGUA POR FONTE SUPERFICIAL E POR OPCAO TECNICA

    public List<BeneAguaFtSubterraneaOptTecnicaDTO> beneficiariosFtSubtOptTecnicaProvincial() {
        User user = buscaUsuarioLogado();
        List<Object[]> list;
        List<BeneAguaFtSubterraneaOptTecnicaDTO> retorno = new ArrayList<>();
        if (isAdminGeral(user)) {
            list = this.relatorioAdminRepository.buscaDadosBenefFtSubtOptTecnicaProvincial();
        } else {
            list = this.relatorioRepository.buscaDadosBenefFtSubtOptTecnicaProvincial(user.getProvincia().getId());
        }
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                BeneAguaFtSubterraneaOptTecnicaDTO dto = new BeneAguaFtSubterraneaOptTecnicaDTO();
                dto.setNomeProvincia((String) i[0]);
                if (Objects.nonNull(i[1])) {
                    dto.setFonteAgua(((String) i[1]));
                }
                dto.setPopulacao(((BigInteger) i[2]).intValue());
                dto.setElectricaSistemas(((BigInteger) i[3]).intValue());
                dto.setElectricaPopulacao(((BigDecimal) i[4]).intValue());
                dto.setElectricaPerc(((BigDecimal) i[5]).floatValue());
                dto.setDieselSistemas(((BigInteger) i[6]).intValue());
                dto.setDieselPopulacao(((BigDecimal) i[7]).intValue());
                dto.setDieselPerc(((BigDecimal) i[8]).floatValue());
                dto.setGravidadeSistemas(((BigInteger) i[9]).intValue());
                dto.setGravidadePopulacao(((BigDecimal) i[10]).intValue());
                dto.setGravidadePerc(((BigDecimal) i[11]).floatValue());

                retorno.add(dto);
            });
        }
        return retorno;
    }

    public List<BeneAguaFtSubterraneaOptTecnicaDTO> beneficiariosFtSubtOptTecnicaMunicipal() {
        User user = buscaUsuarioLogado();
        List<Object[]> list;
        List<BeneAguaFtSubterraneaOptTecnicaDTO> retorno = new ArrayList<>();
        if (isAdminGeral(user)) {
            list = this.relatorioAdminRepository.buscaDadosBenefFtSubtOptTecnicaMunicipal();
        } else {
            list = this.relatorioRepository.buscaDadosBenefFtSubtOptTecnicaMunicipal(user.getProvincia().getId());
        }
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                BeneAguaFtSubterraneaOptTecnicaDTO dto = new BeneAguaFtSubterraneaOptTecnicaDTO();
                dto.setNomeProvincia((String) i[0]);
                dto.setNomeMunicipio((String) i[1]);
                if (Objects.nonNull(i[2])) {
                    dto.setFonteAgua(((String) i[2]));
                }
                dto.setPopulacao(((BigInteger) i[3]).intValue());
                dto.setElectricaSistemas(((BigInteger) i[4]).intValue());
                dto.setElectricaPopulacao(((BigDecimal) i[5]).intValue());
                dto.setElectricaPerc(((BigDecimal) i[6]).floatValue());
                dto.setDieselSistemas(((BigInteger) i[7]).intValue());
                dto.setDieselPopulacao(((BigDecimal) i[8]).intValue());
                dto.setDieselPerc(((BigDecimal) i[9]).floatValue());
                dto.setGravidadeSistemas(((BigInteger) i[10]).intValue());
                dto.setGravidadePopulacao(((BigDecimal) i[11]).intValue());
                dto.setGravidadePerc(((BigDecimal) i[12]).floatValue());

                retorno.add(dto);
            });
        }
        return retorno;
    }

    public List<BeneAguaFtSubterraneaOptTecnicaDTO> beneficiariosFtSubtOptTecnicaComunal() {
        User user = buscaUsuarioLogado();
        List<Object[]> list;
        List<BeneAguaFtSubterraneaOptTecnicaDTO> retorno = new ArrayList<>();
        if (isAdminGeral(user)) {
            list = this.relatorioAdminRepository.buscaDadosBenefFtSubtOptTecnicaComunal();
        } else {
            list = this.relatorioRepository.buscaDadosBenefFtSubtOptTecnicaComunal(user.getProvincia().getId());
        }
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                BeneAguaFtSubterraneaOptTecnicaDTO dto = new BeneAguaFtSubterraneaOptTecnicaDTO();
                dto.setNomeProvincia((String) i[0]);
                dto.setNomeMunicipio((String) i[1]);
                dto.setNomeComuna((String) i[2]);
                if (Objects.nonNull(i[3])) {
                    dto.setFonteAgua(((String) i[3]));
                }
                dto.setPopulacao(((BigInteger) i[4]).intValue());
                dto.setElectricaSistemas(((BigInteger) i[5]).intValue());
                dto.setElectricaPopulacao(((BigDecimal) i[6]).intValue());
                dto.setElectricaPerc(((BigDecimal) i[7]).floatValue());
                dto.setDieselSistemas(((BigInteger) i[8]).intValue());
                dto.setDieselPopulacao(((BigDecimal) i[9]).intValue());
                dto.setDieselPerc(((BigDecimal) i[10]).floatValue());
                dto.setGravidadeSistemas(((BigInteger) i[11]).intValue());
                dto.setGravidadePopulacao(((BigDecimal) i[12]).intValue());
                dto.setGravidadePerc(((BigDecimal) i[13]).floatValue());

                retorno.add(dto);
            });
        }
        return retorno;
    }

    //BOMBA MECANICA
    public List<BeneficiariosBmbMecanicaDTO> beneficiariosFtSubtBmbMecanicaMunicipal() {
        User user = buscaUsuarioLogado();
        List<Object[]> list;
        List<BeneficiariosBmbMecanicaDTO> retorno = new ArrayList<>();
        if (isAdminGeral(user)) {
            list = this.relatorioAdminRepository.buscaDadosBenefFtSubtBmbMecanicaMunicipal();
        } else {
            list = this.relatorioRepository.buscaDadosBenefFtSubtBmbMecanicaMunicipal(user.getProvincia().getId());
        }
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                BeneficiariosBmbMecanicaDTO dto = new BeneficiariosBmbMecanicaDTO();
                dto.setNomeProvincia((String) i[0]);
                dto.setNomeMunicipio((String) i[1]);
                dto.setPopulacao(((BigInteger) i[2]).intValue());
                dto.setPocoMelhorado(((BigInteger) i[3]).intValue());
                dto.setFuro(((BigInteger) i[4]).intValue());
                dto.setNascente(((BigInteger) i[5]).intValue());
                dto.setDieselSistemas(((BigInteger) i[6]).intValue());
                dto.setDieselPopulacao(((BigDecimal) i[7]).intValue());
                dto.setDieselPercent(((BigDecimal) i[8]).floatValue());
                dto.setSolarSistemas(((BigInteger) i[9]).intValue());
                dto.setSolarPopulacao(((BigDecimal) i[10]).intValue());
                dto.setSolarPercent(((BigDecimal) i[11]).floatValue());
                dto.setEolicaSistemas(((BigInteger) i[12]).intValue());
                dto.setEolicaPopulacao(((BigDecimal) i[13]).intValue());
                dto.setEolicaPercent(((BigDecimal) i[14]).floatValue());
                dto.setElectricaSistemas(((BigInteger) i[15]).intValue());
                dto.setElectricaPopulacao(((BigDecimal) i[16]).intValue());
                dto.setElectricaPercent(((BigDecimal) i[17]).floatValue());
                dto.setOutroSistemas(((BigInteger) i[18]).intValue());
                dto.setOutroPopulacao(((BigDecimal) i[19]).intValue());
                dto.setOutroPercent(((BigDecimal) i[20]).floatValue());

                retorno.add(dto);
            });
        }
        return retorno;
    }

    public List<BeneficiariosBmbMecanicaDTO> beneficiariosFtSubtBmbMecanicaComunal() {
        User user = buscaUsuarioLogado();
        List<Object[]> list;
        List<BeneficiariosBmbMecanicaDTO> retorno = new ArrayList<>();
        if (isAdminGeral(user)) {
            list = this.relatorioAdminRepository.buscaDadosBenefFtSubtBmbMecanicaComunal();
        } else {
            list = this.relatorioRepository.buscaDadosBenefFtSubtBmbMecanicaComunal(user.getProvincia().getId());
        }
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                BeneficiariosBmbMecanicaDTO dto = new BeneficiariosBmbMecanicaDTO();
                dto.setNomeProvincia((String) i[0]);
                dto.setNomeMunicipio((String) i[1]);
                dto.setNomeComuna((String) i[2]);
                dto.setPopulacao(((BigInteger) i[3]).intValue());
                dto.setPocoMelhorado(((BigInteger) i[4]).intValue());
                dto.setFuro(((BigInteger) i[5]).intValue());
                dto.setNascente(((BigInteger) i[6]).intValue());
                dto.setDieselSistemas(((BigInteger) i[7]).intValue());
                dto.setDieselPopulacao(((BigDecimal) i[8]).intValue());
                dto.setDieselPercent(((BigDecimal) i[9]).floatValue());
                dto.setSolarSistemas(((BigInteger) i[10]).intValue());
                dto.setSolarPopulacao(((BigDecimal) i[11]).intValue());
                dto.setSolarPercent(((BigDecimal) i[12]).floatValue());
                dto.setEolicaSistemas(((BigInteger) i[13]).intValue());
                dto.setEolicaPopulacao(((BigDecimal) i[14]).intValue());
                dto.setEolicaPercent(((BigDecimal) i[15]).floatValue());
                dto.setElectricaSistemas(((BigInteger) i[16]).intValue());
                dto.setElectricaPopulacao(((BigDecimal) i[17]).intValue());
                dto.setElectricaPercent(((BigDecimal) i[18]).floatValue());
                dto.setOutroSistemas(((BigInteger) i[19]).intValue());
                dto.setOutroPopulacao(((BigDecimal) i[20]).intValue());
                dto.setOutroPercent(((BigDecimal) i[21]).floatValue());

                retorno.add(dto);
            });
        }
        return retorno;
    }

    //SISTEMAS DE AGUA POR FONTE SUBTERRANE E BOMBA DE ENERGIA
    public List<SistemasAguaFtSubtBmbEnergiaDTO> sistemasAguaFtSubtBmbEnergiaComunal() {
        User user = buscaUsuarioLogado();
        List<Object[]> list;
        List<SistemasAguaFtSubtBmbEnergiaDTO> retorno = new ArrayList<>();
        if (isAdminGeral(user)) {
            list = this.relatorioAdminRepository.buscaDadosSistAguafFtSubtBmbEnergiaMunicipal();
        } else {
            list = this.relatorioRepository.buscaDadosSistAguafFtSubtBmbEnergiaMunicipal(user.getProvincia().getId());
        }
        if (Objects.nonNull(list)) {
            list.stream().forEach(i -> {
                SistemasAguaFtSubtBmbEnergiaDTO dto = new SistemasAguaFtSubtBmbEnergiaDTO();
                dto.setNomeProvincia((String) i[0]);
                dto.setNomeMunicipio((String) i[1]);
                dto.setNomeComuna((String) i[2]);

                dto.setPocoMelhorado(((BigInteger)i[3]).intValue());
                dto.setFuro(((BigInteger) i[4]).intValue());
                dto.setNascente(((BigInteger) i[5]).intValue());
                dto.setTotalSistemas(((BigInteger) i[6]).intValue());

                dto.setDieselSistemas(((BigInteger) i[7]).intValue());
                dto.setDieselSistemaFunciona(((BigInteger) i[8]).intValue());
                dto.setDieselSistemaNaoFunciona(((BigInteger) i[9]).floatValue());

                dto.setSolarSistemas(((BigInteger) i[10]).intValue());
                dto.setSolarSistemaFunciona(((BigInteger) i[11]).intValue());
                dto.setSolarSistemaNaoFunciona(((BigInteger) i[12]).floatValue());

                dto.setEolicaSistemas(((BigInteger) i[13]).intValue());
                dto.setEolicaSistemaFunciona(((BigInteger) i[14]).intValue());
                dto.setEolicaSistemaNaoFunciona(((BigInteger) i[15]).floatValue());

                dto.setElectricaSistemas(((BigInteger) i[16]).intValue());
                dto.setElectricaSistemaFunciona(((BigInteger) i[17]).intValue());
                dto.setElectricaSistemaNaoFunciona(((BigInteger) i[18]).floatValue());

                dto.setOutroSistemas(((BigInteger) i[19]).intValue());
                dto.setOutroSistemaFunciona(((BigInteger) i[20]).intValue());
                dto.setOutroSistemaNaoFunciona(((BigInteger) i[21]).floatValue());

                retorno.add(dto);
            });
        }
        return retorno;
    }
}
