package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;

import com.minea.sisas.domain.MunicipiosAtendidos;
import com.minea.sisas.domain.Municipio;
import com.minea.sisas.domain.EntidadeGestora;
import com.minea.sisas.repository.MunicipiosAtendidosRepository;
import com.minea.sisas.service.MunicipiosAtendidosService;
import com.minea.sisas.service.dto.MunicipiosAtendidosDTO;
import com.minea.sisas.service.mapper.MunicipiosAtendidosMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;
import com.minea.sisas.service.MunicipiosAtendidosQueryService;

import com.minea.sisas.web.rest.MunicipiosAtendidosResource;
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
 * Test class for the MunicipiosAtendidosResource REST controller.
 *
 * @see MunicipiosAtendidosResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class MunicipiosAtendidosResourceIntTest {

    private static final Long DEFAULT_ID_MUNICIPIO_ATENDIDO = 1L;
    private static final Long UPDATED_ID_MUNICIPIO_ATENDIDO = 2L;

    @Autowired
    private MunicipiosAtendidosRepository municipiosAtendidosRepository;

    @Autowired
    private MunicipiosAtendidosMapper municipiosAtendidosMapper;

    @Autowired
    private MunicipiosAtendidosService municipiosAtendidosService;

    @Autowired
    private MunicipiosAtendidosQueryService municipiosAtendidosQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMunicipiosAtendidosMockMvc;

    private MunicipiosAtendidos municipiosAtendidos;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MunicipiosAtendidosResource municipiosAtendidosResource = new MunicipiosAtendidosResource(municipiosAtendidosService, municipiosAtendidosQueryService);
        this.restMunicipiosAtendidosMockMvc = MockMvcBuilders.standaloneSetup(municipiosAtendidosResource)
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
    public static MunicipiosAtendidos createEntity(EntityManager em) {
        MunicipiosAtendidos municipiosAtendidos = new MunicipiosAtendidos();
        // Add required entity
        Municipio idMunicipio = MunicipioResourceIntTest.createEntity(em);
        em.persist(idMunicipio);
        em.flush();
        municipiosAtendidos.setIdMunicipio(idMunicipio);
        return municipiosAtendidos;
    }

    @Before
    public void initTest() {
        municipiosAtendidos = createEntity(em);
    }

    @Test
    @Transactional
    public void createMunicipiosAtendidos() throws Exception {
        int databaseSizeBeforeCreate = municipiosAtendidosRepository.findAll().size();

        // Create the MunicipiosAtendidos
        MunicipiosAtendidosDTO municipiosAtendidosDTO = municipiosAtendidosMapper.toDto(municipiosAtendidos);
        restMunicipiosAtendidosMockMvc.perform(post("/api/municipios-atendidos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(municipiosAtendidosDTO)))
            .andExpect(status().isCreated());

        // Validate the MunicipiosAtendidos in the database
        List<MunicipiosAtendidos> municipiosAtendidosList = municipiosAtendidosRepository.findAll();
        assertThat(municipiosAtendidosList).hasSize(databaseSizeBeforeCreate + 1);
        MunicipiosAtendidos testMunicipiosAtendidos = municipiosAtendidosList.get(municipiosAtendidosList.size() - 1);
    }

    @Test
    @Transactional
    public void createMunicipiosAtendidosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = municipiosAtendidosRepository.findAll().size();

        // Create the MunicipiosAtendidos with an existing ID
        municipiosAtendidos.setId(1L);
        MunicipiosAtendidosDTO municipiosAtendidosDTO = municipiosAtendidosMapper.toDto(municipiosAtendidos);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMunicipiosAtendidosMockMvc.perform(post("/api/municipios-atendidos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(municipiosAtendidosDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MunicipiosAtendidos in the database
        List<MunicipiosAtendidos> municipiosAtendidosList = municipiosAtendidosRepository.findAll();
        assertThat(municipiosAtendidosList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdMunicipioAtendidoIsRequired() throws Exception {
        int databaseSizeBeforeTest = municipiosAtendidosRepository.findAll().size();
        // set the field null

        // Create the MunicipiosAtendidos, which fails.
        MunicipiosAtendidosDTO municipiosAtendidosDTO = municipiosAtendidosMapper.toDto(municipiosAtendidos);

        restMunicipiosAtendidosMockMvc.perform(post("/api/municipios-atendidos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(municipiosAtendidosDTO)))
            .andExpect(status().isBadRequest());

        List<MunicipiosAtendidos> municipiosAtendidosList = municipiosAtendidosRepository.findAll();
        assertThat(municipiosAtendidosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMunicipiosAtendidos() throws Exception {
        // Initialize the database
        municipiosAtendidosRepository.saveAndFlush(municipiosAtendidos);

        // Get all the municipiosAtendidosList
        restMunicipiosAtendidosMockMvc.perform(get("/api/municipios-atendidos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(municipiosAtendidos.getId().intValue())))
            .andExpect(jsonPath("$.[*].idMunicipioAtendido").value(hasItem(DEFAULT_ID_MUNICIPIO_ATENDIDO.intValue())));
    }

    @Test
    @Transactional
    public void getMunicipiosAtendidos() throws Exception {
        // Initialize the database
        municipiosAtendidosRepository.saveAndFlush(municipiosAtendidos);

        // Get the municipiosAtendidos
        restMunicipiosAtendidosMockMvc.perform(get("/api/municipios-atendidos/{id}", municipiosAtendidos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(municipiosAtendidos.getId().intValue()))
            .andExpect(jsonPath("$.idMunicipioAtendido").value(DEFAULT_ID_MUNICIPIO_ATENDIDO.intValue()));
    }

    @Test
    @Transactional
    public void getAllMunicipiosAtendidosByIdMunicipioAtendidoIsEqualToSomething() throws Exception {
        // Initialize the database
        municipiosAtendidosRepository.saveAndFlush(municipiosAtendidos);

        // Get all the municipiosAtendidosList where idMunicipioAtendido equals to DEFAULT_ID_MUNICIPIO_ATENDIDO
        defaultMunicipiosAtendidosShouldBeFound("idMunicipioAtendido.equals=" + DEFAULT_ID_MUNICIPIO_ATENDIDO);

        // Get all the municipiosAtendidosList where idMunicipioAtendido equals to UPDATED_ID_MUNICIPIO_ATENDIDO
        defaultMunicipiosAtendidosShouldNotBeFound("idMunicipioAtendido.equals=" + UPDATED_ID_MUNICIPIO_ATENDIDO);
    }

    @Test
    @Transactional
    public void getAllMunicipiosAtendidosByIdMunicipioAtendidoIsInShouldWork() throws Exception {
        // Initialize the database
        municipiosAtendidosRepository.saveAndFlush(municipiosAtendidos);

        // Get all the municipiosAtendidosList where idMunicipioAtendido in DEFAULT_ID_MUNICIPIO_ATENDIDO or UPDATED_ID_MUNICIPIO_ATENDIDO
        defaultMunicipiosAtendidosShouldBeFound("idMunicipioAtendido.in=" + DEFAULT_ID_MUNICIPIO_ATENDIDO + "," + UPDATED_ID_MUNICIPIO_ATENDIDO);

        // Get all the municipiosAtendidosList where idMunicipioAtendido equals to UPDATED_ID_MUNICIPIO_ATENDIDO
        defaultMunicipiosAtendidosShouldNotBeFound("idMunicipioAtendido.in=" + UPDATED_ID_MUNICIPIO_ATENDIDO);
    }

    @Test
    @Transactional
    public void getAllMunicipiosAtendidosByIdMunicipioAtendidoIsNullOrNotNull() throws Exception {
        // Initialize the database
        municipiosAtendidosRepository.saveAndFlush(municipiosAtendidos);

        // Get all the municipiosAtendidosList where idMunicipioAtendido is not null
        defaultMunicipiosAtendidosShouldBeFound("idMunicipioAtendido.specified=true");

        // Get all the municipiosAtendidosList where idMunicipioAtendido is null
        defaultMunicipiosAtendidosShouldNotBeFound("idMunicipioAtendido.specified=false");
    }

    @Test
    @Transactional
    public void getAllMunicipiosAtendidosByIdMunicipioAtendidoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        municipiosAtendidosRepository.saveAndFlush(municipiosAtendidos);

        // Get all the municipiosAtendidosList where idMunicipioAtendido greater than or equals to DEFAULT_ID_MUNICIPIO_ATENDIDO
        defaultMunicipiosAtendidosShouldBeFound("idMunicipioAtendido.greaterOrEqualThan=" + DEFAULT_ID_MUNICIPIO_ATENDIDO);

        // Get all the municipiosAtendidosList where idMunicipioAtendido greater than or equals to UPDATED_ID_MUNICIPIO_ATENDIDO
        defaultMunicipiosAtendidosShouldNotBeFound("idMunicipioAtendido.greaterOrEqualThan=" + UPDATED_ID_MUNICIPIO_ATENDIDO);
    }

    @Test
    @Transactional
    public void getAllMunicipiosAtendidosByIdMunicipioAtendidoIsLessThanSomething() throws Exception {
        // Initialize the database
        municipiosAtendidosRepository.saveAndFlush(municipiosAtendidos);

        // Get all the municipiosAtendidosList where idMunicipioAtendido less than or equals to DEFAULT_ID_MUNICIPIO_ATENDIDO
        defaultMunicipiosAtendidosShouldNotBeFound("idMunicipioAtendido.lessThan=" + DEFAULT_ID_MUNICIPIO_ATENDIDO);

        // Get all the municipiosAtendidosList where idMunicipioAtendido less than or equals to UPDATED_ID_MUNICIPIO_ATENDIDO
        defaultMunicipiosAtendidosShouldBeFound("idMunicipioAtendido.lessThan=" + UPDATED_ID_MUNICIPIO_ATENDIDO);
    }


    @Test
    @Transactional
    public void getAllMunicipiosAtendidosByIdMunicipioIsEqualToSomething() throws Exception {
        // Initialize the database
        Municipio idMunicipio = MunicipioResourceIntTest.createEntity(em);
        em.persist(idMunicipio);
        em.flush();
        municipiosAtendidos.setIdMunicipio(idMunicipio);
        municipiosAtendidosRepository.saveAndFlush(municipiosAtendidos);
        Long idMunicipioId = idMunicipio.getId();

        // Get all the municipiosAtendidosList where idMunicipio equals to idMunicipioId
        defaultMunicipiosAtendidosShouldBeFound("idMunicipioId.equals=" + idMunicipioId);

        // Get all the municipiosAtendidosList where idMunicipio equals to idMunicipioId + 1
        defaultMunicipiosAtendidosShouldNotBeFound("idMunicipioId.equals=" + (idMunicipioId + 1));
    }


    @Test
    @Transactional
    public void getAllMunicipiosAtendidosByEntidadeGestoraIsEqualToSomething() throws Exception {
        // Initialize the database
        EntidadeGestora entidadeGestora = EntidadeGestoraResourceIntTest.createEntity(em);
        em.persist(entidadeGestora);
        em.flush();
        municipiosAtendidos.addEntidadeGestora(entidadeGestora);
        municipiosAtendidosRepository.saveAndFlush(municipiosAtendidos);
        Long entidadeGestoraId = entidadeGestora.getId();

        // Get all the municipiosAtendidosList where entidadeGestora equals to entidadeGestoraId
        defaultMunicipiosAtendidosShouldBeFound("entidadeGestoraId.equals=" + entidadeGestoraId);

        // Get all the municipiosAtendidosList where entidadeGestora equals to entidadeGestoraId + 1
        defaultMunicipiosAtendidosShouldNotBeFound("entidadeGestoraId.equals=" + (entidadeGestoraId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultMunicipiosAtendidosShouldBeFound(String filter) throws Exception {
        restMunicipiosAtendidosMockMvc.perform(get("/api/municipios-atendidos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(municipiosAtendidos.getId().intValue())))
            .andExpect(jsonPath("$.[*].idMunicipioAtendido").value(hasItem(DEFAULT_ID_MUNICIPIO_ATENDIDO.intValue())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultMunicipiosAtendidosShouldNotBeFound(String filter) throws Exception {
        restMunicipiosAtendidosMockMvc.perform(get("/api/municipios-atendidos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingMunicipiosAtendidos() throws Exception {
        // Get the municipiosAtendidos
        restMunicipiosAtendidosMockMvc.perform(get("/api/municipios-atendidos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMunicipiosAtendidos() throws Exception {
        // Initialize the database
        municipiosAtendidosRepository.saveAndFlush(municipiosAtendidos);
        int databaseSizeBeforeUpdate = municipiosAtendidosRepository.findAll().size();

        // Update the municipiosAtendidos
        MunicipiosAtendidos updatedMunicipiosAtendidos = municipiosAtendidosRepository.findOne(municipiosAtendidos.getId());
        // Disconnect from session so that the updates on updatedMunicipiosAtendidos are not directly saved in db
        em.detach(updatedMunicipiosAtendidos);
        MunicipiosAtendidosDTO municipiosAtendidosDTO = municipiosAtendidosMapper.toDto(updatedMunicipiosAtendidos);

        restMunicipiosAtendidosMockMvc.perform(put("/api/municipios-atendidos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(municipiosAtendidosDTO)))
            .andExpect(status().isOk());

        // Validate the MunicipiosAtendidos in the database
        List<MunicipiosAtendidos> municipiosAtendidosList = municipiosAtendidosRepository.findAll();
        assertThat(municipiosAtendidosList).hasSize(databaseSizeBeforeUpdate);
        MunicipiosAtendidos testMunicipiosAtendidos = municipiosAtendidosList.get(municipiosAtendidosList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingMunicipiosAtendidos() throws Exception {
        int databaseSizeBeforeUpdate = municipiosAtendidosRepository.findAll().size();

        // Create the MunicipiosAtendidos
        MunicipiosAtendidosDTO municipiosAtendidosDTO = municipiosAtendidosMapper.toDto(municipiosAtendidos);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMunicipiosAtendidosMockMvc.perform(put("/api/municipios-atendidos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(municipiosAtendidosDTO)))
            .andExpect(status().isCreated());

        // Validate the MunicipiosAtendidos in the database
        List<MunicipiosAtendidos> municipiosAtendidosList = municipiosAtendidosRepository.findAll();
        assertThat(municipiosAtendidosList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMunicipiosAtendidos() throws Exception {
        // Initialize the database
        municipiosAtendidosRepository.saveAndFlush(municipiosAtendidos);
        int databaseSizeBeforeDelete = municipiosAtendidosRepository.findAll().size();

        // Get the municipiosAtendidos
        restMunicipiosAtendidosMockMvc.perform(delete("/api/municipios-atendidos/{id}", municipiosAtendidos.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MunicipiosAtendidos> municipiosAtendidosList = municipiosAtendidosRepository.findAll();
        assertThat(municipiosAtendidosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MunicipiosAtendidos.class);
        MunicipiosAtendidos municipiosAtendidos1 = new MunicipiosAtendidos();
        municipiosAtendidos1.setId(1L);
        MunicipiosAtendidos municipiosAtendidos2 = new MunicipiosAtendidos();
        municipiosAtendidos2.setId(municipiosAtendidos1.getId());
        assertThat(municipiosAtendidos1).isEqualTo(municipiosAtendidos2);
        municipiosAtendidos2.setId(2L);
        assertThat(municipiosAtendidos1).isNotEqualTo(municipiosAtendidos2);
        municipiosAtendidos1.setId(null);
        assertThat(municipiosAtendidos1).isNotEqualTo(municipiosAtendidos2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MunicipiosAtendidosDTO.class);
        MunicipiosAtendidosDTO municipiosAtendidosDTO1 = new MunicipiosAtendidosDTO();
        municipiosAtendidosDTO1.setId(1L);
        MunicipiosAtendidosDTO municipiosAtendidosDTO2 = new MunicipiosAtendidosDTO();
        assertThat(municipiosAtendidosDTO1).isNotEqualTo(municipiosAtendidosDTO2);
        municipiosAtendidosDTO2.setId(municipiosAtendidosDTO1.getId());
        assertThat(municipiosAtendidosDTO1).isEqualTo(municipiosAtendidosDTO2);
        municipiosAtendidosDTO2.setId(2L);
        assertThat(municipiosAtendidosDTO1).isNotEqualTo(municipiosAtendidosDTO2);
        municipiosAtendidosDTO1.setId(null);
        assertThat(municipiosAtendidosDTO1).isNotEqualTo(municipiosAtendidosDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(municipiosAtendidosMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(municipiosAtendidosMapper.fromId(null)).isNull();
    }
}
