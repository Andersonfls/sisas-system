package com.minea.sisas.service.mapper;

import com.minea.sisas.service.mapper.EntityMapper;
import com.minea.sisas.service.dto.IndicadorProducaoDTO;

import com.minea.sisas.domain.IndicadorProducao;
import org.mapstruct.*;

/**
 * Mapper for the entity IndicadorProducao and its DTO IndicadorProducaoDTO.
 */
@Mapper(componentModel = "spring", uses = {SituacaoMapper.class, SistemaAguaMapper.class, ComunaMapper.class})
public interface IndicadorProducaoMapper extends EntityMapper<IndicadorProducaoDTO, IndicadorProducao> {

    @Mapping(source = "situacao", target = "situacao")
    IndicadorProducaoDTO toDto(IndicadorProducao indicadorProducao);

    @Mapping(source = "situacao", target = "situacao")
    IndicadorProducao toEntity(IndicadorProducaoDTO indicadorProducaoDTO);

    default IndicadorProducao fromId(Long id) {
        if (id == null) {
            return null;
        }
        IndicadorProducao indicadorProducao = new IndicadorProducao();
        indicadorProducao.setId(id);
        return indicadorProducao;
    }
}
