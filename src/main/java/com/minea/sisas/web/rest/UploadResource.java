package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.domain.Obra;
import com.minea.sisas.repository.ObraRepository;
import com.minea.sisas.service.StorageService;
import com.minea.sisas.service.dto.UploadFileResponseDTO;
import com.minea.sisas.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * REST controller for managing Storage.
 */
@RestController
@RequestMapping("/api")
public class UploadResource {

    @Autowired
    StorageService storageService;

    @Autowired
    ObraRepository obraRepository;

    private final Logger log = LoggerFactory.getLogger(UploadResource.class);
    private static final String ENTITY_NAME = "arquivo";

    /**
     * POST  /arquivos : Create a new processo.
     * @param obraId
     * @param file to be uploaded
     * @return the ResponseEntity with status 201 (Created) and with body the new UploadFileResponseDTO
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/arquivos/{obraId}")
    public ResponseEntity<UploadFileResponseDTO> upload(@RequestParam MultipartFile file, @PathVariable  Long obraId) {
        try {
            String fileName = storageService.storeFile(file);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/downloadFile/").path(fileName).toUriString();

            Obra obra = obraRepository.findOne(obraId);
            obra.setNomeArquivo(fileName);
            obraRepository.save(obra);

            return ResponseEntity.ok().body(
                new UploadFileResponseDTO(fileName,fileDownloadUri,file.getContentType(),file.getSize(),obraId));
        } catch (Exception e) {
            log.error("FAIL to upload " + file.getOriginalFilename() + ": " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "fileExists", e.getMessage())).build();
        }
    }

    private UploadFileResponseDTO uploadFile(MultipartFile file, Long obraId){
        try {
            String fileName = storageService.storeFile(file);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/downloadFile/").path(fileName).toUriString();

            Obra obra = obraRepository.findOne(obraId);

            obra.setNomeArquivo(fileName);
            this.obraRepository.save(obra);

            return new UploadFileResponseDTO(fileName,fileDownloadUri,file.getContentType(),file.getSize(),obra.getId());
        } catch (Exception e) {
            log.error("FAIL to upload " + file.getOriginalFilename() + "!");
            throw e;
        }
    }
    /**
     * GET  /downloadFile/:fileName : get the "fileName" of file.
     *
     * @param fileName the id of the fileName to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the resource, or with status 404 (Not Found)
     */
    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = storageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
            .body(resource);
    }

    /**
     * DELETE  /arquivos/:id : delete the "id" document.
     *
     * @param id the id of the documentoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/arquivos/{id}")
    @Timed
    public ResponseEntity<Void> deleteFileDocumento(@PathVariable Long id) {
        log.debug("REST request to delete Documento and File : {}", id);
        Obra obra = obraRepository.findOne(id);

        obra.setNomeArquivo(null);
        obraRepository.save(obra);

        if(obra.getNomeArquivo() != null){
            this.storageService.deleteFile(obra.getNomeArquivo());
        }

        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
