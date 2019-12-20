package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.domain.Banner;
import com.minea.sisas.service.BannerService;
import com.minea.sisas.service.BannerService;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Banner.
 */
@RestController
@RequestMapping("/api")
public class BannerResource {

    private final Logger log = LoggerFactory.getLogger(BannerResource.class);

    private static final String ENTITY_NAME = "banner";

    private final BannerService bannerService;

    public BannerResource(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    /**
     * POST  /banners : Create a new banner.
     *
     * @param banner the banner to create
     * @return the ResponseEntity with status 201 (Created) and with body the new banner, or with status 400 (Bad Request) if the banner has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/banners")
    @Timed
    public ResponseEntity<Banner> createBanner(@RequestBody Banner banner) throws URISyntaxException {
        log.debug("REST request to save Banner : {}", banner);
        if (banner.getId() != null) {
            throw new BadRequestAlertException("A new Banner cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Banner result = bannerService.save(banner);
        return ResponseEntity.created(new URI("/api/banners/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /banners : Updates an existing Banner.
     *
     * @param banner the Banner to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated Banner,
     * or with status 400 (Bad Request) if the Banner is not valid,
     * or with status 500 (Internal Server Error) if the Banner couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/banners")
    @Timed
    public ResponseEntity<Banner> updateBanner(@RequestBody Banner banner) throws URISyntaxException {
        log.debug("REST request to update Banner : {}", banner);
        if (banner.getId() == null) {
            return createBanner(banner);
        }
        Banner result = bannerService.save(banner);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, banner.getId().toString()))
            .body(result);
    }

    /**
     * GET  /banners : get all the Banners.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of Banners in body
     */
    @GetMapping("/banners")
    @Timed
    public ResponseEntity<List<Banner>> getAllBanners(Pageable pageable) {
        log.debug("REST request to get a page of Banners");
        Page<Banner> page = bannerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/banners");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /banners/:id : get the "id" Banner.
     *
     * @param id the id of the Banner to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the Banner, or with status 404 (Not Found)
     */
    @GetMapping("/banners/{id}")
    @Timed
    public ResponseEntity<Banner> getBanner(@PathVariable Long id) {
        log.debug("REST request to get Banner : {}", id);
        Banner banner = bannerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(banner));
    }

    /**
     * DELETE  /banners/:id : delete the "id" Banner.
     *
     * @param id the id of the Banner to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/banners/{id}")
    @Timed
    public ResponseEntity<Void> deleteBanner(@PathVariable Long id) {
        log.debug("REST request to delete Banner : {}", id);
        bannerService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
