package com.minea.sisas.service.mapper;

import com.minea.sisas.service.mapper.EntityMapper;
import com.minea.sisas.service.dto.ProjectosDTO;

import com.minea.sisas.domain.Projectos;
import org.mapstruct.*;

/**
 * Mapper for the entity Projectos and its DTO ProjectosDTO.
 */
@Mapper(componentModel = "spring", uses = {SituacaoMapper.class})
public interface ProjectosMapper extends EntityMapper<ProjectosDTO, Projectos> {

    @Mapping(source = "idSituacao.id", target = "idSituacaoId")
    ProjectosDTO toDto(Projectos projectos);

    @Mapping(source = "idSituacaoId", target = "id")
    @Mapping(target = "inicios", ignore = true)
    Projectos toEntity(ProjectosDTO projectosDTO);

    default Projectos fromId(Long id) {
        if (id == null) {
            return null;
        }
        Projectos projectos = new Projectos();
        projectos.setId(id);
        return projectos;
    }
}
