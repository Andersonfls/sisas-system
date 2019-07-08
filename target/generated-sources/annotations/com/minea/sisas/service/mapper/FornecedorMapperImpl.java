package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.Fornecedor;
import com.minea.sisas.service.dto.FornecedorDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-07T00:39:16+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class FornecedorMapperImpl implements FornecedorMapper {

    @Override
    public Fornecedor toEntity(FornecedorDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Fornecedor fornecedor = new Fornecedor();

        fornecedor.setId( dto.getId() );
        fornecedor.setNmFornecedor( dto.getNmFornecedor() );
        fornecedor.setNumContribuinte( dto.getNumContribuinte() );
        fornecedor.setEndereco( dto.getEndereco() );
        fornecedor.setEmail( dto.getEmail() );
        fornecedor.setEspecialidade( dto.getEspecialidade() );

        return fornecedor;
    }

    @Override
    public FornecedorDTO toDto(Fornecedor entity) {
        if ( entity == null ) {
            return null;
        }

        FornecedorDTO fornecedorDTO = new FornecedorDTO();

        fornecedorDTO.setId( entity.getId() );
        fornecedorDTO.setNmFornecedor( entity.getNmFornecedor() );
        fornecedorDTO.setNumContribuinte( entity.getNumContribuinte() );
        fornecedorDTO.setEndereco( entity.getEndereco() );
        fornecedorDTO.setEmail( entity.getEmail() );
        fornecedorDTO.setEspecialidade( entity.getEspecialidade() );

        return fornecedorDTO;
    }

    @Override
    public List<Fornecedor> toEntity(List<FornecedorDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Fornecedor> list = new ArrayList<Fornecedor>( dtoList.size() );
        for ( FornecedorDTO fornecedorDTO : dtoList ) {
            list.add( toEntity( fornecedorDTO ) );
        }

        return list;
    }

    @Override
    public List<FornecedorDTO> toDto(List<Fornecedor> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<FornecedorDTO> list = new ArrayList<FornecedorDTO>( entityList.size() );
        for ( Fornecedor fornecedor : entityList ) {
            list.add( toDto( fornecedor ) );
        }

        return list;
    }
}
