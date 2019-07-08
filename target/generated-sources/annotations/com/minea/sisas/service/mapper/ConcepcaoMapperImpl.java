package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.Concepcao;
import com.minea.sisas.domain.ProgramasProjectos;
import com.minea.sisas.domain.SistemaAgua;
import com.minea.sisas.service.dto.ConcepcaoDTO;
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
public class ConcepcaoMapperImpl implements ConcepcaoMapper {

    @Override
    public List<Concepcao> toEntity(List<ConcepcaoDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Concepcao> list = new ArrayList<Concepcao>( arg0.size() );
        for ( ConcepcaoDTO concepcaoDTO : arg0 ) {
            list.add( toEntity( concepcaoDTO ) );
        }

        return list;
    }

    @Override
    public List<ConcepcaoDTO> toDto(List<Concepcao> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<ConcepcaoDTO> list = new ArrayList<ConcepcaoDTO>( arg0.size() );
        for ( Concepcao concepcao : arg0 ) {
            list.add( toDto( concepcao ) );
        }

        return list;
    }

    @Override
    public ConcepcaoDTO toDto(Concepcao concepcao) {
        if ( concepcao == null ) {
            return null;
        }

        ConcepcaoDTO concepcaoDTO = new ConcepcaoDTO();

        Long id = concepcaoIdProgramasProjectosId( concepcao );
        if ( id != null ) {
            concepcaoDTO.setIdProgramasProjectosId( id );
        }
        Long id1 = concepcaoIdSistemaAguaId( concepcao );
        if ( id1 != null ) {
            concepcaoDTO.setIdSistemaAguaId( id1 );
        }
        concepcaoDTO.setId( concepcao.getId() );
        concepcaoDTO.setTipoConcurso( concepcao.getTipoConcurso() );
        concepcaoDTO.setDtLancamento( concepcao.getDtLancamento() );
        concepcaoDTO.setDtUltimaAlteracao( concepcao.getDtUltimaAlteracao() );
        concepcaoDTO.setDtElaboracaoCon( concepcao.getDtElaboracaoCon() );
        concepcaoDTO.setDtAprovacaoCon( concepcao.getDtAprovacaoCon() );

        return concepcaoDTO;
    }

    @Override
    public Concepcao toEntity(ConcepcaoDTO concepcaoDTO) {
        if ( concepcaoDTO == null ) {
            return null;
        }

        Concepcao concepcao = new Concepcao();

        concepcao.setId( concepcaoDTO.getIdProgramasProjectosId() );
        concepcao.setTipoConcurso( concepcaoDTO.getTipoConcurso() );
        concepcao.setDtLancamento( concepcaoDTO.getDtLancamento() );
        concepcao.setDtUltimaAlteracao( concepcaoDTO.getDtUltimaAlteracao() );
        concepcao.setDtElaboracaoCon( concepcaoDTO.getDtElaboracaoCon() );
        concepcao.setDtAprovacaoCon( concepcaoDTO.getDtAprovacaoCon() );

        return concepcao;
    }

    private Long concepcaoIdProgramasProjectosId(Concepcao concepcao) {
        if ( concepcao == null ) {
            return null;
        }
        ProgramasProjectos idProgramasProjectos = concepcao.getIdProgramasProjectos();
        if ( idProgramasProjectos == null ) {
            return null;
        }
        Long id = idProgramasProjectos.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long concepcaoIdSistemaAguaId(Concepcao concepcao) {
        if ( concepcao == null ) {
            return null;
        }
        SistemaAgua idSistemaAgua = concepcao.getIdSistemaAgua();
        if ( idSistemaAgua == null ) {
            return null;
        }
        Long id = idSistemaAgua.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
