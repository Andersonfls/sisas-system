package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;

import com.minea.sisas.domain.Municipio;
import com.minea.sisas.domain.Provincia;
import com.minea.sisas.domain.Comuna;
import com.minea.sisas.domain.MunicipiosAtendidos;
import com.minea.sisas.repository.MunicipioRepository;
import com.minea.sisas.service.MunicipioService;
import com.minea.sisas.service.dto.MunicipioDTO;
import com.minea.sisas.service.mapper.MunicipioMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;
import com.minea.sisas.service.MunicipioQueryService;

import com.minea.sisas.web.rest.MunicipioResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.minea.sisas.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the MunicipioResource REST controller.
 *
 * @see MunicipioResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class MunicipioResourceIntTest {

    private static final Long DEFAULT_ID_MUNICIPIO = 1L;
    private static final Long UPDATED_ID_MUNICIPIO = 2L;

    private static final String DEFAULT_NM_MUNICIPIO = "AAAAAAAAAA";
    private static final String UPDATED_NM_MUNICIPIO = "BBBBBBBBBB";

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private MunicipioMapper municipioMapper;

    @Autowired
    private MunicipioService municipioService;

    @Autowired
    private MunicipioQueryService municipioQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMunicipioMockMvc;

    private Municipio municipio;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MunicipioResource municipioResource = new MunicipioResource(municipioService, municipioQueryService,municipioRepository);
        this.restMunicipioMockMvc = MockMvcBuilders.standaloneSetup(municipioResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Municipio createEntity(EntityManager em) {
        Municipio municipio = new Municipio()
            .nmMunicipio(DEFAULT_NM_MUNICIPIO);
        // Add required entity
        Provincia idProvincia = ProvinciaResourceIntTest.createEntity(em);
        em.persist(idProvincia);
        em.flush();
        municipio.setProvincia(idProvincia);
        return municipio;
    }

    @Before
    public void initTest() {
        municipio = createEntity(em);
    }

    @Test
    @Transactional
    public void createMunicipio() throws Exception {
        int databaseSizeBeforeCreate = municipioRepository.findAll().size();

        // Create the Municipio
        MunicipioDTO municipioDTO = municipioMapper.toDto(municipio);
        restMunicipioMockMvc.perform(post("/api/municipios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(municipioDTO)))
            .andExpect(status().isCreated());

        // Validate the Municipio in the database
        List<Municipio> municipioList = municipioRepository.findAll();
        assertThat(municipioList).hasSize(databaseSizeBeforeCreate + 1);
        Municipio testMunicipio = municipioList.get(municipioList.size() - 1);
        assertThat(testMunicipio.getNmMunicipio()).isEqualTo(DEFAULT_NM_MUNICIPIO);
    }

    @Test
    @Transactional
    public void createMunicipioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = municipioRepository.findAll().size();

        // Create the Municipio with an existing ID
        municipio.setId(1L);
        MunicipioDTO municipioDTO = municipioMapper.toDto(municipio);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMunicipioMockMvc.perform(post("/api/municipios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(municipioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Municipio in the database
        List<Municipio> municipioList = municipioRepository.findAll();
        assertThat(municipioList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdMunicipioIsRequired() throws Exception {
        int databaseSizeBeforeTest = municipioRepository.findAll().size();
        // set the field null

        // Create the Municipio, which fails.
        MunicipioDTO municipioDTO = municipioMapper.toDto(municipio);

        restMunicipioMockMvc.perform(post("/api/municipios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(municipioDTO)))
            .andExpect(status().isBadRequest());

        List<Municipio> municipioList = municipioRepository.findAll();
        assertThat(municipioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNmMunicipioIsRequired() throws Exception {
        int databaseSizeBeforeTest = municipioRepository.findAll().size();
        // set the field null
        municipio.setNmMunicipio(null);

        // Create the Municipio, which fails.
        MunicipioDTO municipioDTO = municipioMapper.toDto(municipio);

        restMunicipioMockMvc.perform(post("/api/municipios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(municipioDTO)))
            .andExpect(status().isBadRequest());

        List<Municipio> municipioList = municipioRepository.findAll();
        assertThat(municipioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMunicipios() throws Exception {
        // Initialize the database
        municipioRepository.saveAndFlush(municipio);

        // Get all the municipioList
        restMunicipioMockMvc.perform(get("/api/municipios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(municipio.getId().intValue())))
      //      .andExpect(jsonPath("$.[*].idMunicipio").value(hasItem(DEFAULT_ID_MUNICIPIO.intValue())))
            .andExpect(jsonPath("$.[*].nmMunicipio").value(hasItem(DEFAULT_NM_MUNICIPIO.toString())));
    }

    @Test
    @Transactional
    public void getMunicipio() throws Exception {
        // Initialize the database
        municipioRepository.saveAndFlush(municipio);

        // Get the municipio
        restMunicipioMockMvc.perform(get("/api/municipios/{id}", municipio.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(municipio.getId().intValue()))
         //   .andExpect(jsonPath("$.idMunicipio").value(DEFAULT_ID_MUNICIPIO.intValue()))
            .andExpect(jsonPath("$.nmMunicipio").value(DEFAULT_NM_MUNICIPIO.toString()));
    }

    @Test
    @Transactional
    public void getAllMunicipiosByIdMunicipioIsEqualToSomething() throws Exception {
        // Initialize the database
        municipioRepository.saveAndFlush(municipio);

        // Get all the municipioList where idMunicipio equals to DEFAULT_ID_MUNICIPIO
        defaultMunicipioShouldBeFound("idMunicipio.equals=" + DEFAULT_ID_MUNICIPIO);

        // Get all the municipioList where idMunicipio equals to UPDATED_ID_MUNICIPIO
        defaultMunicipioShouldNotBeFound("idMunicipio.equals=" + UPDATED_ID_MUNICIPIO);
    }

    @Test
    @Transactional
    public void getAllMunicipiosByIdMunicipioIsInShouldWork() throws Exception {
        // Initialize the database
        municipioRepository.saveAndFlush(municipio);

        // Get all the municipioList where idMunicipio in DEFAULT_ID_MUNICIPIO or UPDATED_ID_MUNICIPIO
        defaultMunicipioShouldBeFound("idMunicipio.in=" + DEFAULT_ID_MUNICIPIO + "," + UPDATED_ID_MUNICIPIO);

        // Get all the municipioList where idMunicipio equals to UPDATED_ID_MUNICIPIO
        defaultMunicipioShouldNotBeFound("idMunicipio.in=" + UPDATED_ID_MUNICIPIO);
    }

    @Test
    @Transactional
    public void getAllMunicipiosByIdMunicipioIsNullOrNotNull() throws Exception {
        // Initialize the database
        municipioRepository.saveAndFlush(municipio);

        // Get all the municipioList where idMunicipio is not null
        defaultMunicipioShouldBeFound("idMunicipio.specified=true");

        // Get all the municipioList where idMunicipio is null
        defaultMunicipioShouldNotBeFound("idMunicipio.specified=false");
    }

    @Test
    @Transactional
    public void getAllMunicipiosByIdMunicipioIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        municipioRepository.saveAndFlush(municipio);

        // Get all the municipioList where idMunicipio greater than or equals to DEFAULT_ID_MUNICIPIO
        defaultMunicipioShouldBeFound("idMunicipio.greaterOrEqualThan=" + DEFAULT_ID_MUNICIPIO);

        // Get all the municipioList where idMunicipio greater than or equals to UPDATED_ID_MUNICIPIO
        defaultMunicipioShouldNotBeFound("idMunicipio.greaterOrEqualThan=" + UPDATED_ID_MUNICIPIO);
    }

    @Test
    @Transactional
    public void getAllMunicipiosByIdMunicipioIsLessThanSomething() throws Exception {
        // Initialize the database
        municipioRepository.saveAndFlush(municipio);

        // Get all the municipioList where idMunicipio less than or equals to DEFAULT_ID_MUNICIPIO
        defaultMunicipioShouldNotBeFound("idMunicipio.lessThan=" + DEFAULT_ID_MUNICIPIO);

        // Get all the municipioList where idMunicipio less than or equals to UPDATED_ID_MUNICIPIO
        defaultMunicipioShouldBeFound("idMunicipio.lessThan=" + UPDATED_ID_MUNICIPIO);
    }


    @Test
    @Transactional
    public void getAllMunicipiosByNmMunicipioIsEqualToSomething() throws Exception {
        // Initialize the database
        municipioRepository.saveAndFlush(municipio);

        // Get all the municipioList where nmMunicipio equals to DEFAULT_NM_MUNICIPIO
        defaultMunicipioShouldBeFound("nmMunicipio.equals=" + DEFAULT_NM_MUNICIPIO);

        // Get all the municipioList where nmMunicipio equals to UPDATED_NM_MUNICIPIO
        defaultMunicipioShouldNotBeFound("nmMunicipio.equals=" + UPDATED_NM_MUNICIPIO);
    }

    @Test
    @Transactional
    public void getAllMunicipiosByNmMunicipioIsInShouldWork() throws Exception {
        // Initialize the database
        municipioRepository.saveAndFlush(municipio);

        // Get all the municipioList where nmMunicipio in DEFAULT_NM_MUNICIPIO or UPDATED_NM_MUNICIPIO
        defaultMunicipioShouldBeFound("nmMunicipio.in=" + DEFAULT_NM_MUNICIPIO + "," + UPDATED_NM_MUNICIPIO);

        // Get all the municipioList where nmMunicipio equals to UPDATED_NM_MUNICIPIO
        defaultMunicipioShouldNotBeFound("nmMunicipio.in=" + UPDATED_NM_MUNICIPIO);
    }

    @Test
    @Transactional
    public void getAllMunicipiosByNmMunicipioIsNullOrNotNull() throws Exception {
        // Initialize the database
        municipioRepository.saveAndFlush(municipio);

        // Get all the municipioList where nmMunicipio is not null
        defaultMunicipioShouldBeFound("nmMunicipio.specified=true");

        // Get all the municipioList where nmMunicipio is null
        defaultMunicipioShouldNotBeFound("nmMunicipio.specified=false");
    }

    @Test
    @Transactional
    public void getAllMunicipiosByIdProvinciaIsEqualToSomething() throws Exception {
        // Initialize the database
        Provincia idProvincia = ProvinciaResourceIntTest.createEntity(em);
        em.persist(idProvincia);
        em.flush();
        municipio.setProvincia(idProvincia);
        municipioRepository.saveAndFlush(municipio);
        Long idProvinciaId = idProvincia.getId();

        // Get all the municipioList where provincia equals to idProvinciaId
        defaultMunicipioShouldBeFound("idProvinciaId.equals=" + idProvinciaId);

        // Get all the municipioList where provincia equals to idProvinciaId + 1
        defaultMunicipioShouldNotBeFound("idProvinciaId.equals=" + (idProvinciaId + 1));
    }


    @Test
    @Transactional
    public void getAllMunicipiosByComunaIsEqualToSomething() throws Exception {
        // Initialize the database
        Comuna comuna = ComunaResourceIntTest.createEntity(em);
        em.persist(comuna);
        em.flush();
        municipioRepository.saveAndFlush(municipio);
        Long comunaId = comuna.getId();

        // Get all the municipioList where comuna equals to comunaId
        defaultMunicipioShouldBeFound("comunaId.equals=" + comunaId);

        // Get all the municipioList where comuna equals to comunaId + 1
        defaultMunicipioShouldNotBeFound("comunaId.equals=" + (comunaId + 1));
    }


    @Test
    @Transactional
    public void getAllMunicipiosByMunicipiosAtendidosIsEqualToSomething() throws Exception {
        // Initialize the database
        MunicipiosAtendidos municipiosAtendidos = MunicipiosAtendidosResourceIntTest.createEntity(em);
        em.persist(municipiosAtendidos);
        em.flush();
        municipioRepository.saveAndFlush(municipio);
        Long municipiosAtendidosId = municipiosAtendidos.getId();

        // Get all the municipioList where municipiosAtendidos equals to municipiosAtendidosId
        defaultMunicipioShouldBeFound("municipiosAtendidosId.equals=" + municipiosAtendidosId);

        // Get all the municipioList where municipiosAtendidos equals to municipiosAtendidosId + 1
        defaultMunicipioShouldNotBeFound("municipiosAtendidosId.equals=" + (municipiosAtendidosId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultMunicipioShouldBeFound(String filter) throws Exception {
        restMunicipioMockMvc.perform(get("/api/municipios?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(municipio.getId().intValue())))
       //     .andExpect(jsonPath("$.[*].idMunicipio").value(hasItem(DEFAULT_ID_MUNICIPIO.intValue())))
            .andExpect(jsonPath("$.[*].nmMunicipio").value(hasItem(DEFAULT_NM_MUNICIPIO.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultMunicipioShouldNotBeFound(String filter) throws Exception {
        restMunicipioMockMvc.perform(get("/api/municipios?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingMunicipio() throws Exception {
        // Get the municipio
        restMunicipioMockMvc.perform(get("/api/municipios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMunicipio() throws Exception {
        // Initialize the database
        municipioRepository.saveAndFlush(municipio);
        int databaseSizeBeforeUpdate = municipioRepository.findAll().size();

        // Update the municipio
        Municipio updatedMunicipio = municipioRepository.findOne(municipio.getId());
        // Disconnect from session so that the updates on updatedMunicipio are not directly saved in db
        em.detach(updatedMunicipio);
        updatedMunicipio
            .nmMunicipio(UPDATED_NM_MUNICIPIO);
        MunicipioDTO municipioDTO = municipioMapper.toDto(updatedMunicipio);

        restMunicipioMockMvc.perform(put("/api/municipios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(municipioDTO)))
            .andExpect(status().isOk());

        // Validate the Municipio in the database
        List<Municipio> municipioList = municipioRepository.findAll();
        assertThat(municipioList).hasSize(databaseSizeBeforeUpdate);
        Municipio testMunicipio = municipioList.get(municipioList.size() - 1);
        assertThat(testMunicipio.getNmMunicipio()).isEqualTo(UPDATED_NM_MUNICIPIO);
    }

    @Test
    @Transactional
    public void updateNonExistingMunicipio() throws Exception {
        int databaseSizeBeforeUpdate = municipioRepository.findAll().size();

        // Create the Municipio
        MunicipioDTO municipioDTO = municipioMapper.toDto(municipio);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMunicipioMockMvc.perform(put("/api/municipios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(municipioDTO)))
            .andExpect(status().isCreated());

        // Validate the Municipio in the database
        List<Municipio> municipioList = municipioRepository.findAll();
        assertThat(municipioList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMunicipio() throws Exception {
        // Initialize the database
        municipioRepository.saveAndFlush(municipio);
        int databaseSizeBeforeDelete = municipioRepository.findAll().size();

        // Get the municipio
        restMunicipioMockMvc.perform(delete("/api/municipios/{id}", municipio.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Municipio> municipioList = municipioRepository.findAll();
        assertThat(municipioList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Municipio.class);
        Municipio municipio1 = new Municipio();
        municipio1.setId(1L);
        Municipio municipio2 = new Municipio();
        municipio2.setId(municipio1.getId());
        assertThat(municipio1).isEqualTo(municipio2);
        municipio2.setId(2L);
        assertThat(municipio1).isNotEqualTo(municipio2);
        municipio1.setId(null);
        assertThat(municipio1).isNotEqualTo(municipio2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MunicipioDTO.class);
        MunicipioDTO municipioDTO1 = new MunicipioDTO();
        municipioDTO1.setId(1L);
        MunicipioDTO municipioDTO2 = new MunicipioDTO();
        assertThat(municipioDTO1).isNotEqualTo(municipioDTO2);
        municipioDTO2.setId(municipioDTO1.getId());
        assertThat(municipioDTO1).isEqualTo(municipioDTO2);
        municipioDTO2.setId(2L);
        assertThat(municipioDTO1).isNotEqualTo(municipioDTO2);
        municipioDTO1.setId(null);
        assertThat(municipioDTO1).isNotEqualTo(municipioDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(municipioMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(municipioMapper.fromId(null)).isNull();
    }
}
