package com.minea.sisas.service.mapper;

import com.minea.sisas.service.mapper.EntityMapper;
import com.minea.sisas.service.dto.MunicipiosAtendidosDTO;

import com.minea.sisas.domain.MunicipiosAtendidos;
import org.mapstruct.*;

/**
 * Mapper for the entity MunicipiosAtendidos and its DTO MunicipiosAtendidosDTO.
 */
@Mapper(componentModel = "spring", uses = {MunicipioMapper.class})
public interface MunicipiosAtendidosMapper extends EntityMapper<MunicipiosAtendidosDTO, MunicipiosAtendidos> {

    @Mapping(source = "idMunicipio.id", target = "idMunicipioId")
    MunicipiosAtendidosDTO toDto(MunicipiosAtendidos municipiosAtendidos);

    @Mapping(source = "idMunicipioId", target = "id")
    @Mapping(target = "entidadeGestoras", ignore = true)
    MunicipiosAtendidos toEntity(MunicipiosAtendidosDTO municipiosAtendidosDTO);

    default MunicipiosAtendidos fromId(Long id) {
        if (id == null) {
            return null;
        }
        MunicipiosAtendidos municipiosAtendidos = new MunicipiosAtendidos();
        municipiosAtendidos.setId(id);
        return municipiosAtendidos;
    }
}
