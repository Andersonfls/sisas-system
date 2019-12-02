package com.minea.sisas.service.mapper;

import com.minea.sisas.service.mapper.EntityMapper;
import com.minea.sisas.service.dto.FornecedorDTO;

import com.minea.sisas.domain.Fornecedor;
import org.mapstruct.*;

/**
 * Mapper for the entity Fornecedor and its DTO FornecedorDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FornecedorMapper extends EntityMapper<FornecedorDTO, Fornecedor> {

    @Mapping(source = "especialidades", target = "especialidades")
    FornecedorDTO toDto(Fornecedor fornecedor);

    @Mapping(source = "especialidades", target = "especialidades")
    Fornecedor toEntity(FornecedorDTO fornecedorDTO);

    default Fornecedor fromId(Long id) {
        if (id == null) {
            return null;
        }
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(id);
        return fornecedor;
    }
}
