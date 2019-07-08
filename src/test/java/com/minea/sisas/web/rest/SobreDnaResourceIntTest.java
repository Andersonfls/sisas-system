package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;

import com.minea.sisas.domain.SobreDna;
import com.minea.sisas.domain.Situacao;
import com.minea.sisas.domain.Inicio;
import com.minea.sisas.repository.SobreDnaRepository;
import com.minea.sisas.service.SobreDnaService;
import com.minea.sisas.service.dto.SobreDnaDTO;
import com.minea.sisas.service.mapper.SobreDnaMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;
import com.minea.sisas.service.SobreDnaQueryService;

import com.minea.sisas.web.rest.SobreDnaResource;
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
 * Test class for the SobreDnaResource REST controller.
 *
 * @see SobreDnaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class SobreDnaResourceIntTest {

    private static final Long DEFAULT_ID_SOBRE_DNA = 1L;
    private static final Long UPDATED_ID_SOBRE_DNA = 2L;

    private static final String DEFAULT_TITULO_SOBRE_DNA = "AAAAAAAAAA";
    private static final String UPDATED_TITULO_SOBRE_DNA = "BBBBBBBBBB";

    private static final String DEFAULT_TEXTO_SOBRE_DNA = "AAAAAAAAAA";
    private static final String UPDATED_TEXTO_SOBRE_DNA = "BBBBBBBBBB";

    private static final String DEFAULT_RESUMO_TEXTO_SOBRE_DNA = "AAAAAAAAAA";
    private static final String UPDATED_RESUMO_TEXTO_SOBRE_DNA = "BBBBBBBBBB";

    @Autowired
    private SobreDnaRepository sobreDnaRepository;

    @Autowired
    private SobreDnaMapper sobreDnaMapper;

    @Autowired
    private SobreDnaService sobreDnaService;

    @Autowired
    private SobreDnaQueryService sobreDnaQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSobreDnaMockMvc;

    private SobreDna sobreDna;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SobreDnaResource sobreDnaResource = new SobreDnaResource(sobreDnaService, sobreDnaQueryService);
        this.restSobreDnaMockMvc = MockMvcBuilders.standaloneSetup(sobreDnaResource)
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
    public static SobreDna createEntity(EntityManager em) {
        SobreDna sobreDna = new SobreDna()
            .tituloSobreDna(DEFAULT_TITULO_SOBRE_DNA)
            .textoSobreDna(DEFAULT_TEXTO_SOBRE_DNA)
            .resumoTextoSobreDna(DEFAULT_RESUMO_TEXTO_SOBRE_DNA);
        // Add required entity
        Situacao idSituacao = SituacaoResourceIntTest.createEntity(em);
        em.persist(idSituacao);
        em.flush();
        return sobreDna;
    }

    @Before
    public void initTest() {
        sobreDna = createEntity(em);
    }

    @Test
    @Transactional
    public void createSobreDna() throws Exception {
        int databaseSizeBeforeCreate = sobreDnaRepository.findAll().size();

        // Create the SobreDna
        SobreDnaDTO sobreDnaDTO = sobreDnaMapper.toDto(sobreDna);
        restSobreDnaMockMvc.perform(post("/api/sobre-dnas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sobreDnaDTO)))
            .andExpect(status().isCreated());

        // Validate the SobreDna in the database
        List<SobreDna> sobreDnaList = sobreDnaRepository.findAll();
        assertThat(sobreDnaList).hasSize(databaseSizeBeforeCreate + 1);
        SobreDna testSobreDna = sobreDnaList.get(sobreDnaList.size() - 1);
        assertThat(testSobreDna.getId()).isEqualTo(DEFAULT_ID_SOBRE_DNA);
        assertThat(testSobreDna.getTituloSobreDna()).isEqualTo(DEFAULT_TITULO_SOBRE_DNA);
        assertThat(testSobreDna.getTextoSobreDna()).isEqualTo(DEFAULT_TEXTO_SOBRE_DNA);
        assertThat(testSobreDna.getResumoTextoSobreDna()).isEqualTo(DEFAULT_RESUMO_TEXTO_SOBRE_DNA);
    }

    @Test
    @Transactional
    public void createSobreDnaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sobreDnaRepository.findAll().size();

        // Create the SobreDna with an existing ID
        sobreDna.setId(1L);
        SobreDnaDTO sobreDnaDTO = sobreDnaMapper.toDto(sobreDna);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSobreDnaMockMvc.perform(post("/api/sobre-dnas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sobreDnaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SobreDna in the database
        List<SobreDna> sobreDnaList = sobreDnaRepository.findAll();
        assertThat(sobreDnaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdSobreDnaIsRequired() throws Exception {
        int databaseSizeBeforeTest = sobreDnaRepository.findAll().size();
        // set the field null
        sobreDna.setId(null);

        // Create the SobreDna, which fails.
        SobreDnaDTO sobreDnaDTO = sobreDnaMapper.toDto(sobreDna);

        restSobreDnaMockMvc.perform(post("/api/sobre-dnas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sobreDnaDTO)))
            .andExpect(status().isBadRequest());

        List<SobreDna> sobreDnaList = sobreDnaRepository.findAll();
        assertThat(sobreDnaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTituloSobreDnaIsRequired() throws Exception {
        int databaseSizeBeforeTest = sobreDnaRepository.findAll().size();
        // set the field null
        sobreDna.setTituloSobreDna(null);

        // Create the SobreDna, which fails.
        SobreDnaDTO sobreDnaDTO = sobreDnaMapper.toDto(sobreDna);

        restSobreDnaMockMvc.perform(post("/api/sobre-dnas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sobreDnaDTO)))
            .andExpect(status().isBadRequest());

        List<SobreDna> sobreDnaList = sobreDnaRepository.findAll();
        assertThat(sobreDnaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTextoSobreDnaIsRequired() throws Exception {
        int databaseSizeBeforeTest = sobreDnaRepository.findAll().size();
        // set the field null
        sobreDna.setTextoSobreDna(null);

        // Create the SobreDna, which fails.
        SobreDnaDTO sobreDnaDTO = sobreDnaMapper.toDto(sobreDna);

        restSobreDnaMockMvc.perform(post("/api/sobre-dnas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sobreDnaDTO)))
            .andExpect(status().isBadRequest());

        List<SobreDna> sobreDnaList = sobreDnaRepository.findAll();
        assertThat(sobreDnaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkResumoTextoSobreDnaIsRequired() throws Exception {
        int databaseSizeBeforeTest = sobreDnaRepository.findAll().size();
        // set the field null
        sobreDna.setResumoTextoSobreDna(null);

        // Create the SobreDna, which fails.
        SobreDnaDTO sobreDnaDTO = sobreDnaMapper.toDto(sobreDna);

        restSobreDnaMockMvc.perform(post("/api/sobre-dnas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sobreDnaDTO)))
            .andExpect(status().isBadRequest());

        List<SobreDna> sobreDnaList = sobreDnaRepository.findAll();
        assertThat(sobreDnaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSobreDnas() throws Exception {
        // Initialize the database
        sobreDnaRepository.saveAndFlush(sobreDna);

        // Get all the sobreDnaList
        restSobreDnaMockMvc.perform(get("/api/sobre-dnas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sobreDna.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSobreDna").value(hasItem(DEFAULT_ID_SOBRE_DNA.intValue())))
            .andExpect(jsonPath("$.[*].tituloSobreDna").value(hasItem(DEFAULT_TITULO_SOBRE_DNA.toString())))
            .andExpect(jsonPath("$.[*].textoSobreDna").value(hasItem(DEFAULT_TEXTO_SOBRE_DNA.toString())))
            .andExpect(jsonPath("$.[*].resumoTextoSobreDna").value(hasItem(DEFAULT_RESUMO_TEXTO_SOBRE_DNA.toString())));
    }

    @Test
    @Transactional
    public void getSobreDna() throws Exception {
        // Initialize the database
        sobreDnaRepository.saveAndFlush(sobreDna);

        // Get the sobreDna
        restSobreDnaMockMvc.perform(get("/api/sobre-dnas/{id}", sobreDna.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sobreDna.getId().intValue()))
            .andExpect(jsonPath("$.idSobreDna").value(DEFAULT_ID_SOBRE_DNA.intValue()))
            .andExpect(jsonPath("$.tituloSobreDna").value(DEFAULT_TITULO_SOBRE_DNA.toString()))
            .andExpect(jsonPath("$.textoSobreDna").value(DEFAULT_TEXTO_SOBRE_DNA.toString()))
            .andExpect(jsonPath("$.resumoTextoSobreDna").value(DEFAULT_RESUMO_TEXTO_SOBRE_DNA.toString()));
    }

    @Test
    @Transactional
    public void getAllSobreDnasByIdSobreDnaIsEqualToSomething() throws Exception {
        // Initialize the database
        sobreDnaRepository.saveAndFlush(sobreDna);

        // Get all the sobreDnaList where idSobreDna equals to DEFAULT_ID_SOBRE_DNA
        defaultSobreDnaShouldBeFound("idSobreDna.equals=" + DEFAULT_ID_SOBRE_DNA);

        // Get all the sobreDnaList where idSobreDna equals to UPDATED_ID_SOBRE_DNA
        defaultSobreDnaShouldNotBeFound("idSobreDna.equals=" + UPDATED_ID_SOBRE_DNA);
    }

    @Test
    @Transactional
    public void getAllSobreDnasByIdSobreDnaIsInShouldWork() throws Exception {
        // Initialize the database
        sobreDnaRepository.saveAndFlush(sobreDna);

        // Get all the sobreDnaList where idSobreDna in DEFAULT_ID_SOBRE_DNA or UPDATED_ID_SOBRE_DNA
        defaultSobreDnaShouldBeFound("idSobreDna.in=" + DEFAULT_ID_SOBRE_DNA + "," + UPDATED_ID_SOBRE_DNA);

        // Get all the sobreDnaList where idSobreDna equals to UPDATED_ID_SOBRE_DNA
        defaultSobreDnaShouldNotBeFound("idSobreDna.in=" + UPDATED_ID_SOBRE_DNA);
    }

    @Test
    @Transactional
    public void getAllSobreDnasByIdSobreDnaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sobreDnaRepository.saveAndFlush(sobreDna);

        // Get all the sobreDnaList where idSobreDna is not null
        defaultSobreDnaShouldBeFound("idSobreDna.specified=true");

        // Get all the sobreDnaList where idSobreDna is null
        defaultSobreDnaShouldNotBeFound("idSobreDna.specified=false");
    }

    @Test
    @Transactional
    public void getAllSobreDnasByIdSobreDnaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sobreDnaRepository.saveAndFlush(sobreDna);

        // Get all the sobreDnaList where idSobreDna greater than or equals to DEFAULT_ID_SOBRE_DNA
        defaultSobreDnaShouldBeFound("idSobreDna.greaterOrEqualThan=" + DEFAULT_ID_SOBRE_DNA);

        // Get all the sobreDnaList where idSobreDna greater than or equals to UPDATED_ID_SOBRE_DNA
        defaultSobreDnaShouldNotBeFound("idSobreDna.greaterOrEqualThan=" + UPDATED_ID_SOBRE_DNA);
    }

    @Test
    @Transactional
    public void getAllSobreDnasByIdSobreDnaIsLessThanSomething() throws Exception {
        // Initialize the database
        sobreDnaRepository.saveAndFlush(sobreDna);

        // Get all the sobreDnaList where idSobreDna less than or equals to DEFAULT_ID_SOBRE_DNA
        defaultSobreDnaShouldNotBeFound("idSobreDna.lessThan=" + DEFAULT_ID_SOBRE_DNA);

        // Get all the sobreDnaList where idSobreDna less than or equals to UPDATED_ID_SOBRE_DNA
        defaultSobreDnaShouldBeFound("idSobreDna.lessThan=" + UPDATED_ID_SOBRE_DNA);
    }


    @Test
    @Transactional
    public void getAllSobreDnasByTituloSobreDnaIsEqualToSomething() throws Exception {
        // Initialize the database
        sobreDnaRepository.saveAndFlush(sobreDna);

        // Get all the sobreDnaList where tituloSobreDna equals to DEFAULT_TITULO_SOBRE_DNA
        defaultSobreDnaShouldBeFound("tituloSobreDna.equals=" + DEFAULT_TITULO_SOBRE_DNA);

        // Get all the sobreDnaList where tituloSobreDna equals to UPDATED_TITULO_SOBRE_DNA
        defaultSobreDnaShouldNotBeFound("tituloSobreDna.equals=" + UPDATED_TITULO_SOBRE_DNA);
    }

    @Test
    @Transactional
    public void getAllSobreDnasByTituloSobreDnaIsInShouldWork() throws Exception {
        // Initialize the database
        sobreDnaRepository.saveAndFlush(sobreDna);

        // Get all the sobreDnaList where tituloSobreDna in DEFAULT_TITULO_SOBRE_DNA or UPDATED_TITULO_SOBRE_DNA
        defaultSobreDnaShouldBeFound("tituloSobreDna.in=" + DEFAULT_TITULO_SOBRE_DNA + "," + UPDATED_TITULO_SOBRE_DNA);

        // Get all the sobreDnaList where tituloSobreDna equals to UPDATED_TITULO_SOBRE_DNA
        defaultSobreDnaShouldNotBeFound("tituloSobreDna.in=" + UPDATED_TITULO_SOBRE_DNA);
    }

    @Test
    @Transactional
    public void getAllSobreDnasByTituloSobreDnaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sobreDnaRepository.saveAndFlush(sobreDna);

        // Get all the sobreDnaList where tituloSobreDna is not null
        defaultSobreDnaShouldBeFound("tituloSobreDna.specified=true");

        // Get all the sobreDnaList where tituloSobreDna is null
        defaultSobreDnaShouldNotBeFound("tituloSobreDna.specified=false");
    }

    @Test
    @Transactional
    public void getAllSobreDnasByTextoSobreDnaIsEqualToSomething() throws Exception {
        // Initialize the database
        sobreDnaRepository.saveAndFlush(sobreDna);

        // Get all the sobreDnaList where textoSobreDna equals to DEFAULT_TEXTO_SOBRE_DNA
        defaultSobreDnaShouldBeFound("textoSobreDna.equals=" + DEFAULT_TEXTO_SOBRE_DNA);

        // Get all the sobreDnaList where textoSobreDna equals to UPDATED_TEXTO_SOBRE_DNA
        defaultSobreDnaShouldNotBeFound("textoSobreDna.equals=" + UPDATED_TEXTO_SOBRE_DNA);
    }

    @Test
    @Transactional
    public void getAllSobreDnasByTextoSobreDnaIsInShouldWork() throws Exception {
        // Initialize the database
        sobreDnaRepository.saveAndFlush(sobreDna);

        // Get all the sobreDnaList where textoSobreDna in DEFAULT_TEXTO_SOBRE_DNA or UPDATED_TEXTO_SOBRE_DNA
        defaultSobreDnaShouldBeFound("textoSobreDna.in=" + DEFAULT_TEXTO_SOBRE_DNA + "," + UPDATED_TEXTO_SOBRE_DNA);

        // Get all the sobreDnaList where textoSobreDna equals to UPDATED_TEXTO_SOBRE_DNA
        defaultSobreDnaShouldNotBeFound("textoSobreDna.in=" + UPDATED_TEXTO_SOBRE_DNA);
    }

    @Test
    @Transactional
    public void getAllSobreDnasByTextoSobreDnaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sobreDnaRepository.saveAndFlush(sobreDna);

        // Get all the sobreDnaList where textoSobreDna is not null
        defaultSobreDnaShouldBeFound("textoSobreDna.specified=true");

        // Get all the sobreDnaList where textoSobreDna is null
        defaultSobreDnaShouldNotBeFound("textoSobreDna.specified=false");
    }

    @Test
    @Transactional
    public void getAllSobreDnasByResumoTextoSobreDnaIsEqualToSomething() throws Exception {
        // Initialize the database
        sobreDnaRepository.saveAndFlush(sobreDna);

        // Get all the sobreDnaList where resumoTextoSobreDna equals to DEFAULT_RESUMO_TEXTO_SOBRE_DNA
        defaultSobreDnaShouldBeFound("resumoTextoSobreDna.equals=" + DEFAULT_RESUMO_TEXTO_SOBRE_DNA);

        // Get all the sobreDnaList where resumoTextoSobreDna equals to UPDATED_RESUMO_TEXTO_SOBRE_DNA
        defaultSobreDnaShouldNotBeFound("resumoTextoSobreDna.equals=" + UPDATED_RESUMO_TEXTO_SOBRE_DNA);
    }

    @Test
    @Transactional
    public void getAllSobreDnasByResumoTextoSobreDnaIsInShouldWork() throws Exception {
        // Initialize the database
        sobreDnaRepository.saveAndFlush(sobreDna);

        // Get all the sobreDnaList where resumoTextoSobreDna in DEFAULT_RESUMO_TEXTO_SOBRE_DNA or UPDATED_RESUMO_TEXTO_SOBRE_DNA
        defaultSobreDnaShouldBeFound("resumoTextoSobreDna.in=" + DEFAULT_RESUMO_TEXTO_SOBRE_DNA + "," + UPDATED_RESUMO_TEXTO_SOBRE_DNA);

        // Get all the sobreDnaList where resumoTextoSobreDna equals to UPDATED_RESUMO_TEXTO_SOBRE_DNA
        defaultSobreDnaShouldNotBeFound("resumoTextoSobreDna.in=" + UPDATED_RESUMO_TEXTO_SOBRE_DNA);
    }

    @Test
    @Transactional
    public void getAllSobreDnasByResumoTextoSobreDnaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sobreDnaRepository.saveAndFlush(sobreDna);

        // Get all the sobreDnaList where resumoTextoSobreDna is not null
        defaultSobreDnaShouldBeFound("resumoTextoSobreDna.specified=true");

        // Get all the sobreDnaList where resumoTextoSobreDna is null
        defaultSobreDnaShouldNotBeFound("resumoTextoSobreDna.specified=false");
    }

    @Test
    @Transactional
    public void getAllSobreDnasByIdSituacaoIsEqualToSomething() throws Exception {
        // Initialize the database
        Situacao idSituacao = SituacaoResourceIntTest.createEntity(em);
        em.persist(idSituacao);
        em.flush();
        sobreDnaRepository.saveAndFlush(sobreDna);
        Long idSituacaoId = idSituacao.getId();

        // Get all the sobreDnaList where idSituacao equals to situacao
        defaultSobreDnaShouldBeFound("situacao.equals=" + idSituacaoId);

        // Get all the sobreDnaList where idSituacao equals to situacao + 1
        defaultSobreDnaShouldNotBeFound("situacao.equals=" + (idSituacaoId + 1));
    }


    @Test
    @Transactional
    public void getAllSobreDnasByInicioIsEqualToSomething() throws Exception {
        // Initialize the database
        Inicio inicio = InicioResourceIntTest.createEntity(em);
        em.persist(inicio);
        em.flush();
        sobreDnaRepository.saveAndFlush(sobreDna);
        Long inicioId = inicio.getId();

        // Get all the sobreDnaList where inicio equals to inicioId
        defaultSobreDnaShouldBeFound("inicioId.equals=" + inicioId);

        // Get all the sobreDnaList where inicio equals to inicioId + 1
        defaultSobreDnaShouldNotBeFound("inicioId.equals=" + (inicioId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultSobreDnaShouldBeFound(String filter) throws Exception {
        restSobreDnaMockMvc.perform(get("/api/sobre-dnas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sobreDna.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSobreDna").value(hasItem(DEFAULT_ID_SOBRE_DNA.intValue())))
            .andExpect(jsonPath("$.[*].tituloSobreDna").value(hasItem(DEFAULT_TITULO_SOBRE_DNA.toString())))
            .andExpect(jsonPath("$.[*].textoSobreDna").value(hasItem(DEFAULT_TEXTO_SOBRE_DNA.toString())))
            .andExpect(jsonPath("$.[*].resumoTextoSobreDna").value(hasItem(DEFAULT_RESUMO_TEXTO_SOBRE_DNA.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultSobreDnaShouldNotBeFound(String filter) throws Exception {
        restSobreDnaMockMvc.perform(get("/api/sobre-dnas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingSobreDna() throws Exception {
        // Get the sobreDna
        restSobreDnaMockMvc.perform(get("/api/sobre-dnas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSobreDna() throws Exception {
        // Initialize the database
        sobreDnaRepository.saveAndFlush(sobreDna);
        int databaseSizeBeforeUpdate = sobreDnaRepository.findAll().size();

        // Update the sobreDna
        SobreDna updatedSobreDna = sobreDnaRepository.findOne(sobreDna.getId());
        // Disconnect from session so that the updates on updatedSobreDna are not directly saved in db
        em.detach(updatedSobreDna);
        updatedSobreDna
            .tituloSobreDna(UPDATED_TITULO_SOBRE_DNA)
            .textoSobreDna(UPDATED_TEXTO_SOBRE_DNA)
            .resumoTextoSobreDna(UPDATED_RESUMO_TEXTO_SOBRE_DNA);
        SobreDnaDTO sobreDnaDTO = sobreDnaMapper.toDto(updatedSobreDna);

        restSobreDnaMockMvc.perform(put("/api/sobre-dnas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sobreDnaDTO)))
            .andExpect(status().isOk());

        // Validate the SobreDna in the database
        List<SobreDna> sobreDnaList = sobreDnaRepository.findAll();
        assertThat(sobreDnaList).hasSize(databaseSizeBeforeUpdate);
        SobreDna testSobreDna = sobreDnaList.get(sobreDnaList.size() - 1);
        assertThat(testSobreDna.getId()).isEqualTo(UPDATED_ID_SOBRE_DNA);
        assertThat(testSobreDna.getTituloSobreDna()).isEqualTo(UPDATED_TITULO_SOBRE_DNA);
        assertThat(testSobreDna.getTextoSobreDna()).isEqualTo(UPDATED_TEXTO_SOBRE_DNA);
        assertThat(testSobreDna.getResumoTextoSobreDna()).isEqualTo(UPDATED_RESUMO_TEXTO_SOBRE_DNA);
    }

    @Test
    @Transactional
    public void updateNonExistingSobreDna() throws Exception {
        int databaseSizeBeforeUpdate = sobreDnaRepository.findAll().size();

        // Create the SobreDna
        SobreDnaDTO sobreDnaDTO = sobreDnaMapper.toDto(sobreDna);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSobreDnaMockMvc.perform(put("/api/sobre-dnas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sobreDnaDTO)))
            .andExpect(status().isCreated());

        // Validate the SobreDna in the database
        List<SobreDna> sobreDnaList = sobreDnaRepository.findAll();
        assertThat(sobreDnaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSobreDna() throws Exception {
        // Initialize the database
        sobreDnaRepository.saveAndFlush(sobreDna);
        int databaseSizeBeforeDelete = sobreDnaRepository.findAll().size();

        // Get the sobreDna
        restSobreDnaMockMvc.perform(delete("/api/sobre-dnas/{id}", sobreDna.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SobreDna> sobreDnaList = sobreDnaRepository.findAll();
        assertThat(sobreDnaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SobreDna.class);
        SobreDna sobreDna1 = new SobreDna();
        sobreDna1.setId(1L);
        SobreDna sobreDna2 = new SobreDna();
        sobreDna2.setId(sobreDna1.getId());
        assertThat(sobreDna1).isEqualTo(sobreDna2);
        sobreDna2.setId(2L);
        assertThat(sobreDna1).isNotEqualTo(sobreDna2);
        sobreDna1.setId(null);
        assertThat(sobreDna1).isNotEqualTo(sobreDna2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SobreDnaDTO.class);
        SobreDnaDTO sobreDnaDTO1 = new SobreDnaDTO();
        sobreDnaDTO1.setId(1L);
        SobreDnaDTO sobreDnaDTO2 = new SobreDnaDTO();
        assertThat(sobreDnaDTO1).isNotEqualTo(sobreDnaDTO2);
        sobreDnaDTO2.setId(sobreDnaDTO1.getId());
        assertThat(sobreDnaDTO1).isEqualTo(sobreDnaDTO2);
        sobreDnaDTO2.setId(2L);
        assertThat(sobreDnaDTO1).isNotEqualTo(sobreDnaDTO2);
        sobreDnaDTO1.setId(null);
        assertThat(sobreDnaDTO1).isNotEqualTo(sobreDnaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sobreDnaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sobreDnaMapper.fromId(null)).isNull();
    }
}
