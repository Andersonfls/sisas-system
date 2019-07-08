package com.minea.sisas.service.mapper;

import com.minea.sisas.service.mapper.EntityMapper;
import com.minea.sisas.service.dto.ProgramasProjectosLogDTO;

import com.minea.sisas.domain.ProgramasProjectosLog;
import org.mapstruct.*;

/**
 * Mapper for the entity ProgramasProjectosLog and its DTO ProgramasProjectosLogDTO.
 */
@Mapper(componentModel = "spring", uses = {ProgramasProjectosMapper.class})
public interface ProgramasProjectosLogMapper extends EntityMapper<ProgramasProjectosLogDTO, ProgramasProjectosLog> {

    @Mapping(source = "idProgramasProjectos.id", target = "idProgramasProjectosId")
    ProgramasProjectosLogDTO toDto(ProgramasProjectosLog programasProjectosLog);

    @Mapping(source = "idProgramasProjectosId", target = "id")
    ProgramasProjectosLog toEntity(ProgramasProjectosLogDTO programasProjectosLogDTO);

    default ProgramasProjectosLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProgramasProjectosLog programasProjectosLog = new ProgramasProjectosLog();
        programasProjectosLog.setId(id);
        return programasProjectosLog;
    }
}
