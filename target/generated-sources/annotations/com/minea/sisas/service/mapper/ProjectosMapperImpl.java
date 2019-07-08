package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.Projectos;
import com.minea.sisas.domain.Situacao;
import com.minea.sisas.service.dto.ProjectosDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-07T15:41:48+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class ProjectosMapperImpl implements ProjectosMapper {

    @Override
    public List<Projectos> toEntity(List<ProjectosDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Projectos> list = new ArrayList<Projectos>( arg0.size() );
        for ( ProjectosDTO projectosDTO : arg0 ) {
            list.add( toEntity( projectosDTO ) );
        }

        return list;
    }

    @Override
    public List<ProjectosDTO> toDto(List<Projectos> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<ProjectosDTO> list = new ArrayList<ProjectosDTO>( arg0.size() );
        for ( Projectos projectos : arg0 ) {
            list.add( toDto( projectos ) );
        }

        return list;
    }

    @Override
    public ProjectosDTO toDto(Projectos projectos) {
        if ( projectos == null ) {
            return null;
        }

        ProjectosDTO projectosDTO = new ProjectosDTO();

        Long id = projectosIdSituacaoId( projectos );
        if ( id != null ) {
            projectosDTO.setIdSituacaoId( id );
        }
        projectosDTO.setId( projectos.getId() );
        projectosDTO.setNmProjectos( projectos.getNmProjectos() );
        projectosDTO.setTextoProjectos( projectos.getTextoProjectos() );
        projectosDTO.setResumoTextoProjectos( projectos.getResumoTextoProjectos() );

        return projectosDTO;
    }

    @Override
    public Projectos toEntity(ProjectosDTO projectosDTO) {
        if ( projectosDTO == null ) {
            return null;
        }

        Projectos projectos = new Projectos();

        projectos.setId( projectosDTO.getIdSituacaoId() );
        projectos.setNmProjectos( projectosDTO.getNmProjectos() );
        projectos.setTextoProjectos( projectosDTO.getTextoProjectos() );
        projectos.setResumoTextoProjectos( projectosDTO.getResumoTextoProjectos() );

        return projectos;
    }

    private Long projectosIdSituacaoId(Projectos projectos) {
        if ( projectos == null ) {
            return null;
        }
        Situacao idSituacao = projectos.getIdSituacao();
        if ( idSituacao == null ) {
            return null;
        }
        Long id = idSituacao.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
