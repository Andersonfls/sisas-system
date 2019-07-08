package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;
import com.minea.sisas.domain.Fases;
import com.minea.sisas.repository.FasesRepository;
import com.minea.sisas.service.FasesQueryService;
import com.minea.sisas.service.FasesService;
import com.minea.sisas.service.dto.FasesDTO;
import com.minea.sisas.service.mapper.FasesMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;
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
 * Test class for the FasesResource REST controller.
 *
 * @see FasesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class FasesResourceIntTest {

    private static final Long DEFAULT_ID_FASE = 1L;
    private static final Long UPDATED_ID_FASE = 2L;

    private static final String DEFAULT_DESCRICAO_FASE = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO_FASE = "BBBBBBBBBB";

    @Autowired
    private FasesRepository fasesRepository;

    @Autowired
    private FasesMapper fasesMapper;

    @Autowired
    private FasesService fasesService;

    @Autowired
    private FasesQueryService fasesQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFasesMockMvc;

    private Fases fases;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FasesResource fasesResource = new FasesResource(fasesService, fasesQueryService);
        this.restFasesMockMvc = MockMvcBuilders.standaloneSetup(fasesResource)
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
    public static Fases createEntity(EntityManager em) {
        Fases fases = new Fases()
            .descricaoFase(DEFAULT_DESCRICAO_FASE);
        return fases;
    }

    @Before
    public void initTest() {
        fases = createEntity(em);
    }

    @Test
    @Transactional
    public void createFases() throws Exception {
        int databaseSizeBeforeCreate = fasesRepository.findAll().size();

        // Create the Fases
        FasesDTO fasesDTO = fasesMapper.toDto(fases);
        restFasesMockMvc.perform(post("/api/fases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fasesDTO)))
            .andExpect(status().isCreated());

        // Validate the Fases in the database
        List<Fases> fasesList = fasesRepository.findAll();
        assertThat(fasesList).hasSize(databaseSizeBeforeCreate + 1);
        Fases testFases = fasesList.get(fasesList.size() - 1);
        assertThat(testFases.getDescricaoFase()).isEqualTo(DEFAULT_DESCRICAO_FASE);
    }

    @Test
    @Transactional
    public void createFasesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fasesRepository.findAll().size();

        // Create the Fases with an existing ID
        fases.setId(1L);
        FasesDTO fasesDTO = fasesMapper.toDto(fases);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFasesMockMvc.perform(post("/api/fases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fasesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Fases in the database
        List<Fases> fasesList = fasesRepository.findAll();
        assertThat(fasesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdFaseIsRequired() throws Exception {
        int databaseSizeBeforeTest = fasesRepository.findAll().size();
        // set the field null

        // Create the Fases, which fails.
        FasesDTO fasesDTO = fasesMapper.toDto(fases);

        restFasesMockMvc.perform(post("/api/fases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fasesDTO)))
            .andExpect(status().isBadRequest());

        List<Fases> fasesList = fasesRepository.findAll();
        assertThat(fasesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescricaoFaseIsRequired() throws Exception {
        int databaseSizeBeforeTest = fasesRepository.findAll().size();
        // set the field null
        fases.setDescricaoFase(null);

        // Create the Fases, which fails.
        FasesDTO fasesDTO = fasesMapper.toDto(fases);

        restFasesMockMvc.perform(post("/api/fases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fasesDTO)))
            .andExpect(status().isBadRequest());

        List<Fases> fasesList = fasesRepository.findAll();
        assertThat(fasesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFases() throws Exception {
        // Initialize the database
        fasesRepository.saveAndFlush(fases);

        // Get all the fasesList
        restFasesMockMvc.perform(get("/api/fases?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fases.getId().intValue())))
            .andExpect(jsonPath("$.[*].idFase").value(hasItem(DEFAULT_ID_FASE.intValue())))
            .andExpect(jsonPath("$.[*].descricaoFase").value(hasItem(DEFAULT_DESCRICAO_FASE.toString())));
    }

    @Test
    @Transactional
    public void getFases() throws Exception {
        // Initialize the database
        fasesRepository.saveAndFlush(fases);

        // Get the fases
        restFasesMockMvc.perform(get("/api/fases/{id}", fases.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fases.getId().intValue()))
            .andExpect(jsonPath("$.idFase").value(DEFAULT_ID_FASE.intValue()))
            .andExpect(jsonPath("$.descricaoFase").value(DEFAULT_DESCRICAO_FASE.toString()));
    }

    @Test
    @Transactional
    public void getAllFasesByIdFaseIsEqualToSomething() throws Exception {
        // Initialize the database
        fasesRepository.saveAndFlush(fases);

        // Get all the fasesList where idFase equals to DEFAULT_ID_FASE
        defaultFasesShouldBeFound("idFase.equals=" + DEFAULT_ID_FASE);

        // Get all the fasesList where idFase equals to UPDATED_ID_FASE
        defaultFasesShouldNotBeFound("idFase.equals=" + UPDATED_ID_FASE);
    }

    @Test
    @Transactional
    public void getAllFasesByIdFaseIsInShouldWork() throws Exception {
        // Initialize the database
        fasesRepository.saveAndFlush(fases);

        // Get all the fasesList where idFase in DEFAULT_ID_FASE or UPDATED_ID_FASE
        defaultFasesShouldBeFound("idFase.in=" + DEFAULT_ID_FASE + "," + UPDATED_ID_FASE);

        // Get all the fasesList where idFase equals to UPDATED_ID_FASE
        defaultFasesShouldNotBeFound("idFase.in=" + UPDATED_ID_FASE);
    }

    @Test
    @Transactional
    public void getAllFasesByIdFaseIsNullOrNotNull() throws Exception {
        // Initialize the database
        fasesRepository.saveAndFlush(fases);

        // Get all the fasesList where idFase is not null
        defaultFasesShouldBeFound("idFase.specified=true");

        // Get all the fasesList where idFase is null
        defaultFasesShouldNotBeFound("idFase.specified=false");
    }

    @Test
    @Transactional
    public void getAllFasesByIdFaseIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        fasesRepository.saveAndFlush(fases);

        // Get all the fasesList where idFase greater than or equals to DEFAULT_ID_FASE
        defaultFasesShouldBeFound("idFase.greaterOrEqualThan=" + DEFAULT_ID_FASE);

        // Get all the fasesList where idFase greater than or equals to UPDATED_ID_FASE
        defaultFasesShouldNotBeFound("idFase.greaterOrEqualThan=" + UPDATED_ID_FASE);
    }

    @Test
    @Transactional
    public void getAllFasesByIdFaseIsLessThanSomething() throws Exception {
        // Initialize the database
        fasesRepository.saveAndFlush(fases);

        // Get all the fasesList where idFase less than or equals to DEFAULT_ID_FASE
        defaultFasesShouldNotBeFound("idFase.lessThan=" + DEFAULT_ID_FASE);

        // Get all the fasesList where idFase less than or equals to UPDATED_ID_FASE
        defaultFasesShouldBeFound("idFase.lessThan=" + UPDATED_ID_FASE);
    }


    @Test
    @Transactional
    public void getAllFasesByDescricaoFaseIsEqualToSomething() throws Exception {
        // Initialize the database
        fasesRepository.saveAndFlush(fases);

        // Get all the fasesList where descricaoFase equals to DEFAULT_DESCRICAO_FASE
        defaultFasesShouldBeFound("descricaoFase.equals=" + DEFAULT_DESCRICAO_FASE);

        // Get all the fasesList where descricaoFase equals to UPDATED_DESCRICAO_FASE
        defaultFasesShouldNotBeFound("descricaoFase.equals=" + UPDATED_DESCRICAO_FASE);
    }

    @Test
    @Transactional
    public void getAllFasesByDescricaoFaseIsInShouldWork() throws Exception {
        // Initialize the database
        fasesRepository.saveAndFlush(fases);

        // Get all the fasesList where descricaoFase in DEFAULT_DESCRICAO_FASE or UPDATED_DESCRICAO_FASE
        defaultFasesShouldBeFound("descricaoFase.in=" + DEFAULT_DESCRICAO_FASE + "," + UPDATED_DESCRICAO_FASE);

        // Get all the fasesList where descricaoFase equals to UPDATED_DESCRICAO_FASE
        defaultFasesShouldNotBeFound("descricaoFase.in=" + UPDATED_DESCRICAO_FASE);
    }

    @Test
    @Transactional
    public void getAllFasesByDescricaoFaseIsNullOrNotNull() throws Exception {
        // Initialize the database
        fasesRepository.saveAndFlush(fases);

        // Get all the fasesList where descricaoFase is not null
        defaultFasesShouldBeFound("descricaoFase.specified=true");

        // Get all the fasesList where descricaoFase is null
        defaultFasesShouldNotBeFound("descricaoFase.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultFasesShouldBeFound(String filter) throws Exception {
        restFasesMockMvc.perform(get("/api/fases?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fases.getId().intValue())))
            .andExpect(jsonPath("$.[*].idFase").value(hasItem(DEFAULT_ID_FASE.intValue())))
            .andExpect(jsonPath("$.[*].descricaoFase").value(hasItem(DEFAULT_DESCRICAO_FASE.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultFasesShouldNotBeFound(String filter) throws Exception {
        restFasesMockMvc.perform(get("/api/fases?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingFases() throws Exception {
        // Get the fases
        restFasesMockMvc.perform(get("/api/fases/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFases() throws Exception {
        // Initialize the database
        fasesRepository.saveAndFlush(fases);
        int databaseSizeBeforeUpdate = fasesRepository.findAll().size();

        // Update the fases
        Fases updatedFases = fasesRepository.findOne(fases.getId());
        // Disconnect from session so that the updates on updatedFases are not directly saved in db
        em.detach(updatedFases);
        updatedFases
            .descricaoFase(UPDATED_DESCRICAO_FASE);
        FasesDTO fasesDTO = fasesMapper.toDto(updatedFases);

        restFasesMockMvc.perform(put("/api/fases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fasesDTO)))
            .andExpect(status().isOk());

        // Validate the Fases in the database
        List<Fases> fasesList = fasesRepository.findAll();
        assertThat(fasesList).hasSize(databaseSizeBeforeUpdate);
        Fases testFases = fasesList.get(fasesList.size() - 1);
        assertThat(testFases.getDescricaoFase()).isEqualTo(UPDATED_DESCRICAO_FASE);
    }

    @Test
    @Transactional
    public void updateNonExistingFases() throws Exception {
        int databaseSizeBeforeUpdate = fasesRepository.findAll().size();

        // Create the Fases
        FasesDTO fasesDTO = fasesMapper.toDto(fases);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFasesMockMvc.perform(put("/api/fases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fasesDTO)))
            .andExpect(status().isCreated());

        // Validate the Fases in the database
        List<Fases> fasesList = fasesRepository.findAll();
        assertThat(fasesList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFases() throws Exception {
        // Initialize the database
        fasesRepository.saveAndFlush(fases);
        int databaseSizeBeforeDelete = fasesRepository.findAll().size();

        // Get the fases
        restFasesMockMvc.perform(delete("/api/fases/{id}", fases.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Fases> fasesList = fasesRepository.findAll();
        assertThat(fasesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Fases.class);
        Fases fases1 = new Fases();
        fases1.setId(1L);
        Fases fases2 = new Fases();
        fases2.setId(fases1.getId());
        assertThat(fases1).isEqualTo(fases2);
        fases2.setId(2L);
        assertThat(fases1).isNotEqualTo(fases2);
        fases1.setId(null);
        assertThat(fases1).isNotEqualTo(fases2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FasesDTO.class);
        FasesDTO fasesDTO1 = new FasesDTO();
        fasesDTO1.setId(1L);
        FasesDTO fasesDTO2 = new FasesDTO();
        assertThat(fasesDTO1).isNotEqualTo(fasesDTO2);
        fasesDTO2.setId(fasesDTO1.getId());
        assertThat(fasesDTO1).isEqualTo(fasesDTO2);
        fasesDTO2.setId(2L);
        assertThat(fasesDTO1).isNotEqualTo(fasesDTO2);
        fasesDTO1.setId(null);
        assertThat(fasesDTO1).isNotEqualTo(fasesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(fasesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(fasesMapper.fromId(null)).isNull();
    }
}
