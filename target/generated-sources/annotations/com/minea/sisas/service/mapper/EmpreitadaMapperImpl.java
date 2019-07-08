package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.Contrato;
import com.minea.sisas.domain.Empreitada;
import com.minea.sisas.domain.ProgramasProjectos;
import com.minea.sisas.domain.SistemaAgua;
import com.minea.sisas.service.dto.EmpreitadaDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-07T15:41:48+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class EmpreitadaMapperImpl implements EmpreitadaMapper {

    @Autowired
    private ProgramasProjectosMapper programasProjectosMapper;
    @Autowired
    private SistemaAguaMapper sistemaAguaMapper;
    @Autowired
    private ContratoMapper contratoMapper;

    @Override
    public List<Empreitada> toEntity(List<EmpreitadaDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Empreitada> list = new ArrayList<Empreitada>( arg0.size() );
        for ( EmpreitadaDTO empreitadaDTO : arg0 ) {
            list.add( toEntity( empreitadaDTO ) );
        }

        return list;
    }

    @Override
    public List<EmpreitadaDTO> toDto(List<Empreitada> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<EmpreitadaDTO> list = new ArrayList<EmpreitadaDTO>( arg0.size() );
        for ( Empreitada empreitada : arg0 ) {
            list.add( toDto( empreitada ) );
        }

        return list;
    }

    @Override
    public EmpreitadaDTO toDto(Empreitada empreitada) {
        if ( empreitada == null ) {
            return null;
        }

        EmpreitadaDTO empreitadaDTO = new EmpreitadaDTO();

        Long id = empreitadaIdProgramasProjectosId( empreitada );
        if ( id != null ) {
            empreitadaDTO.setIdProgramasProjectosId( id );
        }
        Long id1 = empreitadaIdSistemaAguaId( empreitada );
        if ( id1 != null ) {
            empreitadaDTO.setIdSistemaAguaId( id1 );
        }
        Long id2 = empreitadaIdContratoId( empreitada );
        if ( id2 != null ) {
            empreitadaDTO.setIdContratoId( id2 );
        }
        empreitadaDTO.setId( empreitada.getId() );
        empreitadaDTO.setIdEmpreitada( empreitada.getIdEmpreitada() );
        empreitadaDTO.setTipoEmpreitada( empreitada.getTipoEmpreitada() );
        empreitadaDTO.setDtLancamento( empreitada.getDtLancamento() );
        empreitadaDTO.setNumCapacidadeCaptacao( empreitada.getNumCapacidadeCaptacao() );
        empreitadaDTO.setNumCapacidadeCaptacaoEta( empreitada.getNumCapacidadeCaptacaoEta() );
        empreitadaDTO.setNumExtensaoCondAdutMat( empreitada.getNumExtensaoCondAdutMat() );
        empreitadaDTO.setNumCaprmazenamento( empreitada.getNumCaprmazenamento() );
        empreitadaDTO.setNumExtensaoRedeMat( empreitada.getNumExtensaoRedeMat() );
        empreitadaDTO.setNumLigacoesDomiciliares( empreitada.getNumLigacoesDomiciliares() );
        empreitadaDTO.setNumLigacoesTorneiraQuintal( empreitada.getNumLigacoesTorneiraQuintal() );
        empreitadaDTO.setNumChafarisNovos( empreitada.getNumChafarisNovos() );
        empreitadaDTO.setNumChafarisReabilitar( empreitada.getNumChafarisReabilitar() );
        empreitadaDTO.setNumCapacidadeTratamentoEta( empreitada.getNumCapacidadeTratamentoEta() );
        empreitadaDTO.setNumExtensaoRedeMaterial( empreitada.getNumExtensaoRedeMaterial() );
        empreitadaDTO.setNumExtensaoCondutasElelMat( empreitada.getNumExtensaoCondutasElelMat() );
        empreitadaDTO.setNumLigacoes( empreitada.getNumLigacoes() );
        empreitadaDTO.setNumCaixasVisitas( empreitada.getNumCaixasVisitas() );
        empreitadaDTO.setNumEstacoesElevatorias( empreitada.getNumEstacoesElevatorias() );
        empreitadaDTO.setNumLatrinas( empreitada.getNumLatrinas() );

        return empreitadaDTO;
    }

    @Override
    public Empreitada toEntity(EmpreitadaDTO empreitadaDTO) {
        if ( empreitadaDTO == null ) {
            return null;
        }

        Empreitada empreitada = new Empreitada();

        empreitada.setIdSistemaAgua( sistemaAguaMapper.fromId( empreitadaDTO.getIdSistemaAguaId() ) );
        empreitada.setIdProgramasProjectos( programasProjectosMapper.fromId( empreitadaDTO.getIdProgramasProjectosId() ) );
        empreitada.setIdContrato( contratoMapper.fromId( empreitadaDTO.getIdContratoId() ) );
        empreitada.setId( empreitadaDTO.getId() );
        empreitada.setIdEmpreitada( empreitadaDTO.getIdEmpreitada() );
        empreitada.setTipoEmpreitada( empreitadaDTO.getTipoEmpreitada() );
        empreitada.setDtLancamento( empreitadaDTO.getDtLancamento() );
        empreitada.setNumCapacidadeCaptacao( empreitadaDTO.getNumCapacidadeCaptacao() );
        empreitada.setNumCapacidadeCaptacaoEta( empreitadaDTO.getNumCapacidadeCaptacaoEta() );
        empreitada.setNumExtensaoCondAdutMat( empreitadaDTO.getNumExtensaoCondAdutMat() );
        empreitada.setNumCaprmazenamento( empreitadaDTO.getNumCaprmazenamento() );
        empreitada.setNumExtensaoRedeMat( empreitadaDTO.getNumExtensaoRedeMat() );
        empreitada.setNumLigacoesDomiciliares( empreitadaDTO.getNumLigacoesDomiciliares() );
        empreitada.setNumLigacoesTorneiraQuintal( empreitadaDTO.getNumLigacoesTorneiraQuintal() );
        empreitada.setNumChafarisNovos( empreitadaDTO.getNumChafarisNovos() );
        empreitada.setNumChafarisReabilitar( empreitadaDTO.getNumChafarisReabilitar() );
        empreitada.setNumCapacidadeTratamentoEta( empreitadaDTO.getNumCapacidadeTratamentoEta() );
        empreitada.setNumExtensaoRedeMaterial( empreitadaDTO.getNumExtensaoRedeMaterial() );
        empreitada.setNumExtensaoCondutasElelMat( empreitadaDTO.getNumExtensaoCondutasElelMat() );
        empreitada.setNumLigacoes( empreitadaDTO.getNumLigacoes() );
        empreitada.setNumCaixasVisitas( empreitadaDTO.getNumCaixasVisitas() );
        empreitada.setNumEstacoesElevatorias( empreitadaDTO.getNumEstacoesElevatorias() );
        empreitada.setNumLatrinas( empreitadaDTO.getNumLatrinas() );

        return empreitada;
    }

    private Long empreitadaIdProgramasProjectosId(Empreitada empreitada) {
        if ( empreitada == null ) {
            return null;
        }
        ProgramasProjectos idProgramasProjectos = empreitada.getIdProgramasProjectos();
        if ( idProgramasProjectos == null ) {
            return null;
        }
        Long id = idProgramasProjectos.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long empreitadaIdSistemaAguaId(Empreitada empreitada) {
        if ( empreitada == null ) {
            return null;
        }
        SistemaAgua idSistemaAgua = empreitada.getIdSistemaAgua();
        if ( idSistemaAgua == null ) {
            return null;
        }
        Long id = idSistemaAgua.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long empreitadaIdContratoId(Empreitada empreitada) {
        if ( empreitada == null ) {
            return null;
        }
        Contrato idContrato = empreitada.getIdContrato();
        if ( idContrato == null ) {
            return null;
        }
        Long id = idContrato.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
