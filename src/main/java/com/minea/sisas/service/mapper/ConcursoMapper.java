package com.minea.sisas.service.mapper;

import com.minea.sisas.service.dto.ConcursoDTO;

import com.minea.sisas.domain.Concurso;
import org.mapstruct.*;

/**
 * Mapper for the entity Concurso and its DTO ConcursoDTO.
 */
@Mapper(componentModel = "spring", uses = {ProgramasProjectosMapper.class, SistemaAguaMapper.class})
public interface ConcursoMapper extends EntityMapper<ConcursoDTO, Concurso> {

    @Mapping(source = "programasProjectos", target = "programasProjectos")
    @Mapping(source = "idSistemaAgua.id", target = "idSistemaAguaId")
    ConcursoDTO toDto(Concurso concurso);

    Concurso toEntity(ConcursoDTO concursoDTO);

    default Concurso fromId(Long id) {
        if (id == null) {
            return null;
        }
        Concurso concurso = new Concurso();
        concurso.setId(id);
        return concurso;
    }
}
