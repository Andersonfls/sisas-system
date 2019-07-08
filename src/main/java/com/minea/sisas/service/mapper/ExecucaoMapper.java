package com.minea.sisas.service.mapper;

import com.minea.sisas.service.mapper.EntityMapper;
import com.minea.sisas.service.dto.ExecucaoDTO;

import com.minea.sisas.domain.Execucao;
import org.mapstruct.*;

/**
 * Mapper for the entity Execucao and its DTO ExecucaoDTO.
 */
@Mapper(componentModel = "spring", uses = {SituacaoMapper.class, ProgramasProjectosMapper.class, SistemaAguaMapper.class, ContratoMapper.class})
public interface ExecucaoMapper extends EntityMapper<ExecucaoDTO, Execucao> {

    @Mapping(source = "idSituacao.id", target = "idSituacaoId")
    @Mapping(source = "idProgramasProjectos.id", target = "idProgramasProjectosId")
    @Mapping(source = "idSistemaAgua.id", target = "idSistemaAguaId")
    @Mapping(source = "idContrato.id", target = "idContratoId")
    ExecucaoDTO toDto(Execucao execucao);

    @Mapping(source = "idSituacaoId", target = "id")
    Execucao toEntity(ExecucaoDTO execucaoDTO);

    default Execucao fromId(Long id) {
        if (id == null) {
            return null;
        }
        Execucao execucao = new Execucao();
        execucao.setId(id);
        return execucao;
    }
}
