package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;

import com.minea.sisas.domain.Comuna;
import com.minea.sisas.domain.Municipio;
import com.minea.sisas.domain.IndicadorProducao;
import com.minea.sisas.domain.ProgramasProjectos;
import com.minea.sisas.domain.SistemaAgua;
import com.minea.sisas.repository.ComunaRepository;
import com.minea.sisas.service.ComunaService;
import com.minea.sisas.service.dto.ComunaDTO;
import com.minea.sisas.service.mapper.ComunaMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;
import com.minea.sisas.service.ComunaQueryService;

import com.minea.sisas.web.rest.ComunaResource;
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
 * Test class for the ComunaResource REST controller.
 *
 * @see ComunaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class ComunaResourceIntTest {

    private static final Long DEFAULT_ID_COMUNA = 1L;
    private static final Long UPDATED_ID_COMUNA = 2L;

    private static final String DEFAULT_NM_COMUNA = "AAAAAAAAAA";
    private static final String UPDATED_NM_COMUNA = "BBBBBBBBBB";

    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private ComunaMapper comunaMapper;

    @Autowired
    private ComunaService comunaService;

    @Autowired
    private ComunaQueryService comunaQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restComunaMockMvc;

    private Comuna comuna;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ComunaResource comunaResource = new ComunaResource(comunaService, comunaQueryService,comunaRepository);
        this.restComunaMockMvc = MockMvcBuilders.standaloneSetup(comunaResource)
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
    public static Comuna createEntity(EntityManager em) {
        Comuna comuna = new Comuna()
            .nmComuna(DEFAULT_NM_COMUNA);
        // Add required entity
        Municipio idMunicipio = MunicipioResourceIntTest.createEntity(em);
        em.persist(idMunicipio);
        em.flush();
        comuna.setMunicipio(idMunicipio);
        return comuna;
    }

    @Before
    public void initTest() {
        comuna = createEntity(em);
    }

    @Test
    @Transactional
    public void createComuna() throws Exception {
        int databaseSizeBeforeCreate = comunaRepository.findAll().size();

        // Create the Comuna
        ComunaDTO comunaDTO = comunaMapper.toDto(comuna);
        restComunaMockMvc.perform(post("/api/comunas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comunaDTO)))
            .andExpect(status().isCreated());

        // Validate the Comuna in the database
        List<Comuna> comunaList = comunaRepository.findAll();
        assertThat(comunaList).hasSize(databaseSizeBeforeCreate + 1);
        Comuna testComuna = comunaList.get(comunaList.size() - 1);
        assertThat(testComuna.getNmComuna()).isEqualTo(DEFAULT_NM_COMUNA);
    }

    @Test
    @Transactional
    public void createComunaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = comunaRepository.findAll().size();

        // Create the Comuna with an existing ID
        comuna.setId(1L);
        ComunaDTO comunaDTO = comunaMapper.toDto(comuna);

        // An entity with an existing ID cannot be created, so this API call must fail
        restComunaMockMvc.perform(post("/api/comunas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comunaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Comuna in the database
        List<Comuna> comunaList = comunaRepository.findAll();
        assertThat(comunaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdComunaIsRequired() throws Exception {
        int databaseSizeBeforeTest = comunaRepository.findAll().size();
        // set the field null

        // Create the Comuna, which fails.
        ComunaDTO comunaDTO = comunaMapper.toDto(comuna);

        restComunaMockMvc.perform(post("/api/comunas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comunaDTO)))
            .andExpect(status().isBadRequest());

        List<Comuna> comunaList = comunaRepository.findAll();
        assertThat(comunaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNmComunaIsRequired() throws Exception {
        int databaseSizeBeforeTest = comunaRepository.findAll().size();
        // set the field null
        comuna.setNmComuna(null);

        // Create the Comuna, which fails.
        ComunaDTO comunaDTO = comunaMapper.toDto(comuna);

        restComunaMockMvc.perform(post("/api/comunas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comunaDTO)))
            .andExpect(status().isBadRequest());

        List<Comuna> comunaList = comunaRepository.findAll();
        assertThat(comunaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllComunas() throws Exception {
        // Initialize the database
        comunaRepository.saveAndFlush(comuna);

        // Get all the comunaList
        restComunaMockMvc.perform(get("/api/comunas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(comuna.getId().intValue())))
            .andExpect(jsonPath("$.[*].idComuna").value(hasItem(DEFAULT_ID_COMUNA.intValue())))
            .andExpect(jsonPath("$.[*].nmComuna").value(hasItem(DEFAULT_NM_COMUNA.toString())));
    }

    @Test
    @Transactional
    public void getComuna() throws Exception {
        // Initialize the database
        comunaRepository.saveAndFlush(comuna);

        // Get the comuna
        restComunaMockMvc.perform(get("/api/comunas/{id}", comuna.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(comuna.getId().intValue()))
            .andExpect(jsonPath("$.idComuna").value(DEFAULT_ID_COMUNA.intValue()))
            .andExpect(jsonPath("$.nmComuna").value(DEFAULT_NM_COMUNA.toString()));
    }

    @Test
    @Transactional
    public void getAllComunasByIdComunaIsEqualToSomething() throws Exception {
        // Initialize the database
        comunaRepository.saveAndFlush(comuna);

        // Get all the comunaList where idComuna equals to DEFAULT_ID_COMUNA
        defaultComunaShouldBeFound("idComuna.equals=" + DEFAULT_ID_COMUNA);

        // Get all the comunaList where idComuna equals to UPDATED_ID_COMUNA
        defaultComunaShouldNotBeFound("idComuna.equals=" + UPDATED_ID_COMUNA);
    }

    @Test
    @Transactional
    public void getAllComunasByIdComunaIsInShouldWork() throws Exception {
        // Initialize the database
        comunaRepository.saveAndFlush(comuna);

        // Get all the comunaList where idComuna in DEFAULT_ID_COMUNA or UPDATED_ID_COMUNA
        defaultComunaShouldBeFound("idComuna.in=" + DEFAULT_ID_COMUNA + "," + UPDATED_ID_COMUNA);

        // Get all the comunaList where idComuna equals to UPDATED_ID_COMUNA
        defaultComunaShouldNotBeFound("idComuna.in=" + UPDATED_ID_COMUNA);
    }

    @Test
    @Transactional
    public void getAllComunasByIdComunaIsNullOrNotNull() throws Exception {
        // Initialize the database
        comunaRepository.saveAndFlush(comuna);

        // Get all the comunaList where idComuna is not null
        defaultComunaShouldBeFound("idComuna.specified=true");

        // Get all the comunaList where idComuna is null
        defaultComunaShouldNotBeFound("idComuna.specified=false");
    }

    @Test
    @Transactional
    public void getAllComunasByIdComunaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        comunaRepository.saveAndFlush(comuna);

        // Get all the comunaList where idComuna greater than or equals to DEFAULT_ID_COMUNA
        defaultComunaShouldBeFound("idComuna.greaterOrEqualThan=" + DEFAULT_ID_COMUNA);

        // Get all the comunaList where idComuna greater than or equals to UPDATED_ID_COMUNA
        defaultComunaShouldNotBeFound("idComuna.greaterOrEqualThan=" + UPDATED_ID_COMUNA);
    }

    @Test
    @Transactional
    public void getAllComunasByIdComunaIsLessThanSomething() throws Exception {
        // Initialize the database
        comunaRepository.saveAndFlush(comuna);

        // Get all the comunaList where idComuna less than or equals to DEFAULT_ID_COMUNA
        defaultComunaShouldNotBeFound("idComuna.lessThan=" + DEFAULT_ID_COMUNA);

        // Get all the comunaList where idComuna less than or equals to UPDATED_ID_COMUNA
        defaultComunaShouldBeFound("idComuna.lessThan=" + UPDATED_ID_COMUNA);
    }


    @Test
    @Transactional
    public void getAllComunasByNmComunaIsEqualToSomething() throws Exception {
        // Initialize the database
        comunaRepository.saveAndFlush(comuna);

        // Get all the comunaList where nmComuna equals to DEFAULT_NM_COMUNA
        defaultComunaShouldBeFound("nmComuna.equals=" + DEFAULT_NM_COMUNA);

        // Get all the comunaList where nmComuna equals to UPDATED_NM_COMUNA
        defaultComunaShouldNotBeFound("nmComuna.equals=" + UPDATED_NM_COMUNA);
    }

    @Test
    @Transactional
    public void getAllComunasByNmComunaIsInShouldWork() throws Exception {
        // Initialize the database
        comunaRepository.saveAndFlush(comuna);

        // Get all the comunaList where nmComuna in DEFAULT_NM_COMUNA or UPDATED_NM_COMUNA
        defaultComunaShouldBeFound("nmComuna.in=" + DEFAULT_NM_COMUNA + "," + UPDATED_NM_COMUNA);

        // Get all the comunaList where nmComuna equals to UPDATED_NM_COMUNA
        defaultComunaShouldNotBeFound("nmComuna.in=" + UPDATED_NM_COMUNA);
    }

    @Test
    @Transactional
    public void getAllComunasByNmComunaIsNullOrNotNull() throws Exception {
        // Initialize the database
        comunaRepository.saveAndFlush(comuna);

        // Get all the comunaList where nmComuna is not null
        defaultComunaShouldBeFound("nmComuna.specified=true");

        // Get all the comunaList where nmComuna is null
        defaultComunaShouldNotBeFound("nmComuna.specified=false");
    }

    @Test
    @Transactional
    public void getAllComunasByIdMunicipioIsEqualToSomething() throws Exception {
        // Initialize the database
        Municipio idMunicipio = MunicipioResourceIntTest.createEntity(em);
        em.persist(idMunicipio);
        em.flush();
        comuna.setMunicipio(idMunicipio);
        comunaRepository.saveAndFlush(comuna);
        Long idMunicipioId = idMunicipio.getId();

        // Get all the comunaList where idMunicipio equals to idMunicipioId
        defaultComunaShouldBeFound("idMunicipioId.equals=" + idMunicipioId);

        // Get all the comunaList where idMunicipio equals to idMunicipioId + 1
        defaultComunaShouldNotBeFound("idMunicipioId.equals=" + (idMunicipioId + 1));
    }


    @Test
    @Transactional
    public void getAllComunasByIndicadorProducaoIsEqualToSomething() throws Exception {
        // Initialize the database
        IndicadorProducao indicadorProducao = IndicadorProducaoResourceIntTest.createEntity(em);
        em.persist(indicadorProducao);
        em.flush();
        comunaRepository.saveAndFlush(comuna);
        Long indicadorProducaoId = indicadorProducao.getId();

        // Get all the comunaList where indicadorProducao equals to indicadorProducaoId
        defaultComunaShouldBeFound("indicadorProducaoId.equals=" + indicadorProducaoId);

        // Get all the comunaList where indicadorProducao equals to indicadorProducaoId + 1
        defaultComunaShouldNotBeFound("indicadorProducaoId.equals=" + (indicadorProducaoId + 1));
    }


    @Test
    @Transactional
    public void getAllComunasByProgramasProjectosIsEqualToSomething() throws Exception {
        // Initialize the database
        ProgramasProjectos programasProjectos = ProgramasProjectosResourceIntTest.createEntity(em);
        em.persist(programasProjectos);
        em.flush();
        comunaRepository.saveAndFlush(comuna);
        Long programasProjectosId = programasProjectos.getId();

        // Get all the comunaList where programasProjectos equals to programasProjectosId
        defaultComunaShouldBeFound("programasProjectosId.equals=" + programasProjectosId);

        // Get all the comunaList where programasProjectos equals to programasProjectosId + 1
        defaultComunaShouldNotBeFound("programasProjectosId.equals=" + (programasProjectosId + 1));
    }


    @Test
    @Transactional
    public void getAllComunasBySistemaAguaIsEqualToSomething() throws Exception {
        // Initialize the database
        SistemaAgua sistemaAgua = SistemaAguaResourceIntTest.createEntity(em);
        em.persist(sistemaAgua);
        em.flush();
        comunaRepository.saveAndFlush(comuna);
        Long sistemaAguaId = sistemaAgua.getId();

        // Get all the comunaList where sistemaAgua equals to sistemaAguaId
        defaultComunaShouldBeFound("sistemaAguaId.equals=" + sistemaAguaId);

        // Get all the comunaList where sistemaAgua equals to sistemaAguaId + 1
        defaultComunaShouldNotBeFound("sistemaAguaId.equals=" + (sistemaAguaId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultComunaShouldBeFound(String filter) throws Exception {
        restComunaMockMvc.perform(get("/api/comunas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(comuna.getId().intValue())))
            .andExpect(jsonPath("$.[*].idComuna").value(hasItem(DEFAULT_ID_COMUNA.intValue())))
            .andExpect(jsonPath("$.[*].nmComuna").value(hasItem(DEFAULT_NM_COMUNA.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultComunaShouldNotBeFound(String filter) throws Exception {
        restComunaMockMvc.perform(get("/api/comunas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingComuna() throws Exception {
        // Get the comuna
        restComunaMockMvc.perform(get("/api/comunas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateComuna() throws Exception {
        // Initialize the database
        comunaRepository.saveAndFlush(comuna);
        int databaseSizeBeforeUpdate = comunaRepository.findAll().size();

        // Update the comuna
        Comuna updatedComuna = comunaRepository.findOne(comuna.getId());
        // Disconnect from session so that the updates on updatedComuna are not directly saved in db
        em.detach(updatedComuna);
        updatedComuna
            .nmComuna(UPDATED_NM_COMUNA);
        ComunaDTO comunaDTO = comunaMapper.toDto(updatedComuna);

        restComunaMockMvc.perform(put("/api/comunas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comunaDTO)))
            .andExpect(status().isOk());

        // Validate the Comuna in the database
        List<Comuna> comunaList = comunaRepository.findAll();
        assertThat(comunaList).hasSize(databaseSizeBeforeUpdate);
        Comuna testComuna = comunaList.get(comunaList.size() - 1);
        assertThat(testComuna.getNmComuna()).isEqualTo(UPDATED_NM_COMUNA);
    }

    @Test
    @Transactional
    public void updateNonExistingComuna() throws Exception {
        int databaseSizeBeforeUpdate = comunaRepository.findAll().size();

        // Create the Comuna
        ComunaDTO comunaDTO = comunaMapper.toDto(comuna);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restComunaMockMvc.perform(put("/api/comunas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comunaDTO)))
            .andExpect(status().isCreated());

        // Validate the Comuna in the database
        List<Comuna> comunaList = comunaRepository.findAll();
        assertThat(comunaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteComuna() throws Exception {
        // Initialize the database
        comunaRepository.saveAndFlush(comuna);
        int databaseSizeBeforeDelete = comunaRepository.findAll().size();

        // Get the comuna
        restComunaMockMvc.perform(delete("/api/comunas/{id}", comuna.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Comuna> comunaList = comunaRepository.findAll();
        assertThat(comunaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Comuna.class);
        Comuna comuna1 = new Comuna();
        comuna1.setId(1L);
        Comuna comuna2 = new Comuna();
        comuna2.setId(comuna1.getId());
        assertThat(comuna1).isEqualTo(comuna2);
        comuna2.setId(2L);
        assertThat(comuna1).isNotEqualTo(comuna2);
        comuna1.setId(null);
        assertThat(comuna1).isNotEqualTo(comuna2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComunaDTO.class);
        ComunaDTO comunaDTO1 = new ComunaDTO();
        comunaDTO1.setId(1L);
        ComunaDTO comunaDTO2 = new ComunaDTO();
        assertThat(comunaDTO1).isNotEqualTo(comunaDTO2);
        comunaDTO2.setId(comunaDTO1.getId());
        assertThat(comunaDTO1).isEqualTo(comunaDTO2);
        comunaDTO2.setId(2L);
        assertThat(comunaDTO1).isNotEqualTo(comunaDTO2);
        comunaDTO1.setId(null);
        assertThat(comunaDTO1).isNotEqualTo(comunaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(comunaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(comunaMapper.fromId(null)).isNull();
    }
}
