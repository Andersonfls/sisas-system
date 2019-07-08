package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.EntidadeGestora;
import com.minea.sisas.domain.MunicipiosAtendidos;
import com.minea.sisas.service.dto.EntidadeGestoraDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-07T00:39:14+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class EntidadeGestoraMapperImpl implements EntidadeGestoraMapper {

    @Autowired
    private MunicipiosAtendidosMapper municipiosAtendidosMapper;

    @Override
    public List<EntidadeGestora> toEntity(List<EntidadeGestoraDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<EntidadeGestora> list = new ArrayList<EntidadeGestora>( dtoList.size() );
        for ( EntidadeGestoraDTO entidadeGestoraDTO : dtoList ) {
            list.add( toEntity( entidadeGestoraDTO ) );
        }

        return list;
    }

    @Override
    public List<EntidadeGestoraDTO> toDto(List<EntidadeGestora> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<EntidadeGestoraDTO> list = new ArrayList<EntidadeGestoraDTO>( entityList.size() );
        for ( EntidadeGestora entidadeGestora : entityList ) {
            list.add( toDto( entidadeGestora ) );
        }

        return list;
    }

    @Override
    public EntidadeGestoraDTO toDto(EntidadeGestora entidadeGestora) {
        if ( entidadeGestora == null ) {
            return null;
        }

        EntidadeGestoraDTO entidadeGestoraDTO = new EntidadeGestoraDTO();

        Long id = entidadeGestoraIdMunicipioAtendidoId( entidadeGestora );
        if ( id != null ) {
            entidadeGestoraDTO.setIdMunicipioAtendidoId( id );
        }
        entidadeGestoraDTO.setId( entidadeGestora.getId() );
        entidadeGestoraDTO.setIdEntidadeGestora( entidadeGestora.getIdEntidadeGestora() );
        entidadeGestoraDTO.setNmEntidadeGestora( entidadeGestora.getNmEntidadeGestora() );
        entidadeGestoraDTO.setTpFormaJuridica( entidadeGestora.getTpFormaJuridica() );
        entidadeGestoraDTO.setDtConstituicao( entidadeGestora.getDtConstituicao() );
        entidadeGestoraDTO.setEndereco( entidadeGestora.getEndereco() );
        entidadeGestoraDTO.setEmail( entidadeGestora.getEmail() );
        entidadeGestoraDTO.setContactos( entidadeGestora.getContactos() );
        entidadeGestoraDTO.setTpModeloGestao( entidadeGestora.getTpModeloGestao() );
        entidadeGestoraDTO.setNumRecursosHumanos( entidadeGestora.getNumRecursosHumanos() );
        entidadeGestoraDTO.setNumPopulacaoAreaAtendimento( entidadeGestora.getNumPopulacaoAreaAtendimento() );

        return entidadeGestoraDTO;
    }

    @Override
    public EntidadeGestora toEntity(EntidadeGestoraDTO entidadeGestoraDTO) {
        if ( entidadeGestoraDTO == null ) {
            return null;
        }

        EntidadeGestora entidadeGestora = new EntidadeGestora();

        entidadeGestora.setIdMunicipioAtendido( municipiosAtendidosMapper.fromId( entidadeGestoraDTO.getIdMunicipioAtendidoId() ) );
        entidadeGestora.setId( entidadeGestoraDTO.getId() );
        entidadeGestora.setIdEntidadeGestora( entidadeGestoraDTO.getIdEntidadeGestora() );
        entidadeGestora.setNmEntidadeGestora( entidadeGestoraDTO.getNmEntidadeGestora() );
        entidadeGestora.setTpFormaJuridica( entidadeGestoraDTO.getTpFormaJuridica() );
        entidadeGestora.setDtConstituicao( entidadeGestoraDTO.getDtConstituicao() );
        entidadeGestora.setEndereco( entidadeGestoraDTO.getEndereco() );
        entidadeGestora.setEmail( entidadeGestoraDTO.getEmail() );
        entidadeGestora.setContactos( entidadeGestoraDTO.getContactos() );
        entidadeGestora.setTpModeloGestao( entidadeGestoraDTO.getTpModeloGestao() );
        entidadeGestora.setNumRecursosHumanos( entidadeGestoraDTO.getNumRecursosHumanos() );
        entidadeGestora.setNumPopulacaoAreaAtendimento( entidadeGestoraDTO.getNumPopulacaoAreaAtendimento() );

        return entidadeGestora;
    }

    private Long entidadeGestoraIdMunicipioAtendidoId(EntidadeGestora entidadeGestora) {
        if ( entidadeGestora == null ) {
            return null;
        }
        MunicipiosAtendidos idMunicipioAtendido = entidadeGestora.getIdMunicipioAtendido();
        if ( idMunicipioAtendido == null ) {
            return null;
        }
        Long id = idMunicipioAtendido.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
