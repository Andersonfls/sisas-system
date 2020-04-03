package com.minea.sisas.service;

import com.minea.sisas.domain.Authority;
import com.minea.sisas.domain.User;
import com.minea.sisas.repository.*;
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
public class MapasTematicosService {

    private final Logger log = LoggerFactory.getLogger(MapasTematicosService.class);

    @Autowired
    private MapasTematicosRepository mapasTematicosRepository;

    @Autowired
    private UserRepository userRepository;

    public MapasTematicosService(MapasTematicosRepository mapasTematicosRepository) {
        this.mapasTematicosRepository = mapasTematicosRepository;
    }

//    private User buscaUsuarioLogado() {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = null;
//        String username = null;
//        if (principal instanceof UserDetails) {
//            username = ((UserDetails)principal).getUsername();
//        }
//        if (Objects.nonNull(username)) {
//            user = this.userRepository.findByLoginEquals(username);
//        }
//        return user;
//    }

    private boolean isAdminGeral(User user){
        for (Authority permissao: user.getAuthorities() ) {
            if (permissao.getName().equals("ROLE_ADMIN")) {
                return true;
            }
        }
        return false;
    }

    public List<MapasDTO> montaListaDadosPorcentagemCoberturaServicosAguaProvincial(){
        List<MapasDTO> retorno = new ArrayList<>();
        List<Object[]> list = this.mapasTematicosRepository.porcentagemCoberturaServicosAguaProvincial();
        if (Objects.nonNull(list)) {
            for (Object[] i : list) {
                MapasDTO dto = new MapasDTO();
                dto.setIdProvincia(((BigInteger) i[0]).longValue());
                dto.setBeneficiariosSistemaAgua(((BigDecimal) i[1]).intValue());
                dto.setPorcentagemCobertura(((BigDecimal) i[2]).floatValue());
                retorno.add(dto);
            }
        }
        return retorno;
    }

    public List<MapasDTO> montaListaDadosPorcentagemSistemasAguaProvincial(){
        List<MapasDTO> retorno = new ArrayList<>();
        List<Object[]> list = this.mapasTematicosRepository.porcentagemSistemasAguaProvincial();
        if (Objects.nonNull(list)) {
            for (Object[] i : list) {
                MapasDTO dto = new MapasDTO();
                dto.setIdProvincia(((BigInteger) i[0]).longValue());
                dto.setPorcentagemFuncionam(((BigDecimal) i[1]).floatValue());
                dto.setPorcentagemNaoFuncionam(((BigDecimal) i[2]).floatValue());
                retorno.add(dto);
            }
        }
        return retorno;
    }
}
