package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.ProgramasProjectos;
import com.minea.sisas.service.dto.ProgramasProjectosDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity ProgramasProjectos and its DTO ProgramasProjectosDTO.
 */
@Mapper(componentModel = "spring", uses = {ComunaMapper.class})
public interface ProgramasProjectosMapper extends EntityMapper<ProgramasProjectosDTO, ProgramasProjectos> {

    @Mapping(source = "comuna", target = "comuna")
    ProgramasProjectosDTO toDto(ProgramasProjectos programasProjectos);

    @Mapping(source = "comuna", target = "comuna")
    ProgramasProjectos toEntity(ProgramasProjectosDTO programasProjectosDTO);

    default ProgramasProjectos fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProgramasProjectos programasProjectos = new ProgramasProjectos();
        programasProjectos.setId(id);
        return programasProjectos;
    }
}
