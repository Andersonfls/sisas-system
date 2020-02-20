package com.minea.sisas.service;

import com.minea.sisas.domain.ArquivosPortal;
import com.minea.sisas.repository.ArquivosPortalRepository;
import com.minea.sisas.service.dto.ArquivosPortalDTO;
import com.minea.sisas.web.rest.util.TipoArquivoEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Service Implementation for managing ArquivoPortal.
 */
@Service
@Transactional
public class ArquivosPortalService {

    private final Logger log = LoggerFactory.getLogger(ArquivosPortalService.class);

    private final ArquivosPortalRepository arquivoPortalRepository;

    public ArquivosPortalService(ArquivosPortalRepository arquivoPortalRepository) {
        this.arquivoPortalRepository = arquivoPortalRepository;
    }

    /**
     * Save a Banner.
     *
     * @param arquivoPortal the entity to save
     * @return the persisted entity
     */
    public ArquivosPortal save(ArquivosPortalDTO arquivoPortal) {
        log.debug("Request to save ArquivoPortal : {}", arquivoPortal);

        ArquivosPortal ap = new ArquivosPortal();
        ap.setId(arquivoPortal.getId());
        ap.setDataAlteracao(arquivoPortal.getDataAlteracao());
        ap.setDataInclusao(arquivoPortal.getDataInclusao());
        ap.setDescricao(arquivoPortal.getDescricao());
        ap.setNomeArquivo(arquivoPortal.getNomeArquivo());
        ap.setStatus(arquivoPortal.getStatus());
        ap.setTipoArquivo(arquivoPortal.getTipoArquivo());

        return arquivoPortalRepository.save(ap);
    }

    private ArquivosPortalDTO arquivosPortalParaDto(ArquivosPortal arquivoPortal) {
        ArquivosPortalDTO dto = new ArquivosPortalDTO();
        if (Objects.nonNull(arquivoPortal)){
            dto.setId(arquivoPortal.getId());
            dto.setDataAlteracao(arquivoPortal.getDataAlteracao());
            dto.setDataInclusao(arquivoPortal.getDataInclusao());
            dto.setDescricao(arquivoPortal.getDescricao());
            dto.setNomeArquivo(arquivoPortal.getNomeArquivo());
            dto.setStatus(arquivoPortal.getStatus());
            dto.setTipoArquivo(arquivoPortal.getTipoArquivo());

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/downloadFile/").path(arquivoPortal.getId().toString()+".pdf").toUriString();
            dto.setUri(fileDownloadUri);
        }
        return dto;
    }
    /**
     * Get all the Banners.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ArquivosPortal> findAll(Pageable pageable) {
        log.debug("Request to get all ArquivoPortals");
        return arquivoPortalRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<ArquivosPortalDTO> findAllDto(Integer codigo) {
        log.debug("Request to get all ArquivoPortals");
        List<ArquivosPortalDTO> dtos = new ArrayList<>();
        List<ArquivosPortal> arquivosPortal = arquivoPortalRepository.findAllByTipoArquivo(codigo);
       if (Objects.nonNull(arquivosPortal)){
           arquivosPortal.stream().forEach(i ->{
               dtos.add(arquivosPortalParaDto(i));
           });
       }
        return dtos;
    }
    /**
     * Get one banner by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public ArquivosPortalDTO findOne(Long id) {
        log.debug("Request to get ArquivoPortal : {}", id);
        return arquivosPortalParaDto(arquivoPortalRepository.findOne(id));
    }

    /**
     * Delete the Banner by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ArquivoPortal : {}", id);
        arquivoPortalRepository.delete(id);
    }
}
