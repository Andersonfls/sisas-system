package com.minea.sisas.service.mapper;

import com.minea.sisas.service.mapper.EntityMapper;
import com.minea.sisas.service.dto.OrigemFinanciamentoDTO;

import com.minea.sisas.domain.OrigemFinanciamento;
import org.mapstruct.*;

/**
 * Mapper for the entity OrigemFinanciamento and its DTO OrigemFinanciamentoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrigemFinanciamentoMapper extends EntityMapper<OrigemFinanciamentoDTO, OrigemFinanciamento> {



    default OrigemFinanciamento fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrigemFinanciamento origemFinanciamento = new OrigemFinanciamento();
        origemFinanciamento.setId(id);
        return origemFinanciamento;
    }
}
