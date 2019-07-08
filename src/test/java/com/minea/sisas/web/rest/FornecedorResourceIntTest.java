package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;

import com.minea.sisas.domain.Fornecedor;
import com.minea.sisas.repository.FornecedorRepository;
import com.minea.sisas.service.FornecedorService;
import com.minea.sisas.service.dto.FornecedorDTO;
import com.minea.sisas.service.mapper.FornecedorMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;
import com.minea.sisas.service.FornecedorQueryService;

import com.minea.sisas.web.rest.FornecedorResource;
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
 * Test class for the FornecedorResource REST controller.
 *
 * @see FornecedorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class FornecedorResourceIntTest {

    private static final Long DEFAULT_ID_FORNECEDOR = 1L;
    private static final Long UPDATED_ID_FORNECEDOR = 2L;

    private static final String DEFAULT_NM_FORNECEDOR = "AAAAAAAAAA";
    private static final String UPDATED_NM_FORNECEDOR = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_CONTRIBUINTE = "AAAAAAAAAA";
    private static final String UPDATED_NUM_CONTRIBUINTE = "BBBBBBBBBB";

    private static final String DEFAULT_ENDERECO = "AAAAAAAAAA";
    private static final String UPDATED_ENDERECO = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ESPECIALIDADE = "AAAAAAAAAA";
    private static final String UPDATED_ESPECIALIDADE = "BBBBBBBBBB";

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private FornecedorMapper fornecedorMapper;

    @Autowired
    private FornecedorService fornecedorService;

    @Autowired
    private FornecedorQueryService fornecedorQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFornecedorMockMvc;

    private Fornecedor fornecedor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FornecedorResource fornecedorResource = new FornecedorResource(fornecedorService, fornecedorQueryService);
        this.restFornecedorMockMvc = MockMvcBuilders.standaloneSetup(fornecedorResource)
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
    public static Fornecedor createEntity(EntityManager em) {
        Fornecedor fornecedor = new Fornecedor()
            .nmFornecedor(DEFAULT_NM_FORNECEDOR)
            .numContribuinte(DEFAULT_NUM_CONTRIBUINTE)
            .endereco(DEFAULT_ENDERECO)
            .email(DEFAULT_EMAIL)
            .especialidade(DEFAULT_ESPECIALIDADE);
        return fornecedor;
    }

    @Before
    public void initTest() {
        fornecedor = createEntity(em);
    }

    @Test
    @Transactional
    public void createFornecedor() throws Exception {
        int databaseSizeBeforeCreate = fornecedorRepository.findAll().size();

        // Create the Fornecedor
        FornecedorDTO fornecedorDTO = fornecedorMapper.toDto(fornecedor);
        restFornecedorMockMvc.perform(post("/api/fornecedors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fornecedorDTO)))
            .andExpect(status().isCreated());

        // Validate the Fornecedor in the database
        List<Fornecedor> fornecedorList = fornecedorRepository.findAll();
        assertThat(fornecedorList).hasSize(databaseSizeBeforeCreate + 1);
        Fornecedor testFornecedor = fornecedorList.get(fornecedorList.size() - 1);
        assertThat(testFornecedor.getNmFornecedor()).isEqualTo(DEFAULT_NM_FORNECEDOR);
        assertThat(testFornecedor.getNumContribuinte()).isEqualTo(DEFAULT_NUM_CONTRIBUINTE);
        assertThat(testFornecedor.getEndereco()).isEqualTo(DEFAULT_ENDERECO);
        assertThat(testFornecedor.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testFornecedor.getEspecialidade()).isEqualTo(DEFAULT_ESPECIALIDADE);
    }

    @Test
    @Transactional
    public void createFornecedorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fornecedorRepository.findAll().size();

        // Create the Fornecedor with an existing ID
        fornecedor.setId(1L);
        FornecedorDTO fornecedorDTO = fornecedorMapper.toDto(fornecedor);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFornecedorMockMvc.perform(post("/api/fornecedors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fornecedorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Fornecedor in the database
        List<Fornecedor> fornecedorList = fornecedorRepository.findAll();
        assertThat(fornecedorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdFornecedorIsRequired() throws Exception {
        int databaseSizeBeforeTest = fornecedorRepository.findAll().size();
        // set the field null

        // Create the Fornecedor, which fails.
        FornecedorDTO fornecedorDTO = fornecedorMapper.toDto(fornecedor);

        restFornecedorMockMvc.perform(post("/api/fornecedors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fornecedorDTO)))
            .andExpect(status().isBadRequest());

        List<Fornecedor> fornecedorList = fornecedorRepository.findAll();
        assertThat(fornecedorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNmFornecedorIsRequired() throws Exception {
        int databaseSizeBeforeTest = fornecedorRepository.findAll().size();
        // set the field null
        fornecedor.setNmFornecedor(null);

        // Create the Fornecedor, which fails.
        FornecedorDTO fornecedorDTO = fornecedorMapper.toDto(fornecedor);

        restFornecedorMockMvc.perform(post("/api/fornecedors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fornecedorDTO)))
            .andExpect(status().isBadRequest());

        List<Fornecedor> fornecedorList = fornecedorRepository.findAll();
        assertThat(fornecedorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumContribuinteIsRequired() throws Exception {
        int databaseSizeBeforeTest = fornecedorRepository.findAll().size();
        // set the field null
        fornecedor.setNumContribuinte(null);

        // Create the Fornecedor, which fails.
        FornecedorDTO fornecedorDTO = fornecedorMapper.toDto(fornecedor);

        restFornecedorMockMvc.perform(post("/api/fornecedors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fornecedorDTO)))
            .andExpect(status().isBadRequest());

        List<Fornecedor> fornecedorList = fornecedorRepository.findAll();
        assertThat(fornecedorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnderecoIsRequired() throws Exception {
        int databaseSizeBeforeTest = fornecedorRepository.findAll().size();
        // set the field null
        fornecedor.setEndereco(null);

        // Create the Fornecedor, which fails.
        FornecedorDTO fornecedorDTO = fornecedorMapper.toDto(fornecedor);

        restFornecedorMockMvc.perform(post("/api/fornecedors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fornecedorDTO)))
            .andExpect(status().isBadRequest());

        List<Fornecedor> fornecedorList = fornecedorRepository.findAll();
        assertThat(fornecedorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFornecedors() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList
        restFornecedorMockMvc.perform(get("/api/fornecedors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fornecedor.getId().intValue())))
            .andExpect(jsonPath("$.[*].idFornecedor").value(hasItem(DEFAULT_ID_FORNECEDOR.intValue())))
            .andExpect(jsonPath("$.[*].nmFornecedor").value(hasItem(DEFAULT_NM_FORNECEDOR.toString())))
            .andExpect(jsonPath("$.[*].numContribuinte").value(hasItem(DEFAULT_NUM_CONTRIBUINTE.toString())))
            .andExpect(jsonPath("$.[*].endereco").value(hasItem(DEFAULT_ENDERECO.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].especialidade").value(hasItem(DEFAULT_ESPECIALIDADE.toString())));
    }

    @Test
    @Transactional
    public void getFornecedor() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get the fornecedor
        restFornecedorMockMvc.perform(get("/api/fornecedors/{id}", fornecedor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fornecedor.getId().intValue()))
            .andExpect(jsonPath("$.idFornecedor").value(DEFAULT_ID_FORNECEDOR.intValue()))
            .andExpect(jsonPath("$.nmFornecedor").value(DEFAULT_NM_FORNECEDOR.toString()))
            .andExpect(jsonPath("$.numContribuinte").value(DEFAULT_NUM_CONTRIBUINTE.toString()))
            .andExpect(jsonPath("$.endereco").value(DEFAULT_ENDERECO.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.especialidade").value(DEFAULT_ESPECIALIDADE.toString()));
    }

    @Test
    @Transactional
    public void getAllFornecedorsByIdFornecedorIsEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where idFornecedor equals to DEFAULT_ID_FORNECEDOR
        defaultFornecedorShouldBeFound("idFornecedor.equals=" + DEFAULT_ID_FORNECEDOR);

        // Get all the fornecedorList where idFornecedor equals to UPDATED_ID_FORNECEDOR
        defaultFornecedorShouldNotBeFound("idFornecedor.equals=" + UPDATED_ID_FORNECEDOR);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByIdFornecedorIsInShouldWork() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where idFornecedor in DEFAULT_ID_FORNECEDOR or UPDATED_ID_FORNECEDOR
        defaultFornecedorShouldBeFound("idFornecedor.in=" + DEFAULT_ID_FORNECEDOR + "," + UPDATED_ID_FORNECEDOR);

        // Get all the fornecedorList where idFornecedor equals to UPDATED_ID_FORNECEDOR
        defaultFornecedorShouldNotBeFound("idFornecedor.in=" + UPDATED_ID_FORNECEDOR);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByIdFornecedorIsNullOrNotNull() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where idFornecedor is not null
        defaultFornecedorShouldBeFound("idFornecedor.specified=true");

        // Get all the fornecedorList where idFornecedor is null
        defaultFornecedorShouldNotBeFound("idFornecedor.specified=false");
    }

    @Test
    @Transactional
    public void getAllFornecedorsByIdFornecedorIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where idFornecedor greater than or equals to DEFAULT_ID_FORNECEDOR
        defaultFornecedorShouldBeFound("idFornecedor.greaterOrEqualThan=" + DEFAULT_ID_FORNECEDOR);

        // Get all the fornecedorList where idFornecedor greater than or equals to UPDATED_ID_FORNECEDOR
        defaultFornecedorShouldNotBeFound("idFornecedor.greaterOrEqualThan=" + UPDATED_ID_FORNECEDOR);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByIdFornecedorIsLessThanSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where idFornecedor less than or equals to DEFAULT_ID_FORNECEDOR
        defaultFornecedorShouldNotBeFound("idFornecedor.lessThan=" + DEFAULT_ID_FORNECEDOR);

        // Get all the fornecedorList where idFornecedor less than or equals to UPDATED_ID_FORNECEDOR
        defaultFornecedorShouldBeFound("idFornecedor.lessThan=" + UPDATED_ID_FORNECEDOR);
    }


    @Test
    @Transactional
    public void getAllFornecedorsByNmFornecedorIsEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where nmFornecedor equals to DEFAULT_NM_FORNECEDOR
        defaultFornecedorShouldBeFound("nmFornecedor.equals=" + DEFAULT_NM_FORNECEDOR);

        // Get all the fornecedorList where nmFornecedor equals to UPDATED_NM_FORNECEDOR
        defaultFornecedorShouldNotBeFound("nmFornecedor.equals=" + UPDATED_NM_FORNECEDOR);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByNmFornecedorIsInShouldWork() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where nmFornecedor in DEFAULT_NM_FORNECEDOR or UPDATED_NM_FORNECEDOR
        defaultFornecedorShouldBeFound("nmFornecedor.in=" + DEFAULT_NM_FORNECEDOR + "," + UPDATED_NM_FORNECEDOR);

        // Get all the fornecedorList where nmFornecedor equals to UPDATED_NM_FORNECEDOR
        defaultFornecedorShouldNotBeFound("nmFornecedor.in=" + UPDATED_NM_FORNECEDOR);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByNmFornecedorIsNullOrNotNull() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where nmFornecedor is not null
        defaultFornecedorShouldBeFound("nmFornecedor.specified=true");

        // Get all the fornecedorList where nmFornecedor is null
        defaultFornecedorShouldNotBeFound("nmFornecedor.specified=false");
    }

    @Test
    @Transactional
    public void getAllFornecedorsByNumContribuinteIsEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where numContribuinte equals to DEFAULT_NUM_CONTRIBUINTE
        defaultFornecedorShouldBeFound("numContribuinte.equals=" + DEFAULT_NUM_CONTRIBUINTE);

        // Get all the fornecedorList where numContribuinte equals to UPDATED_NUM_CONTRIBUINTE
        defaultFornecedorShouldNotBeFound("numContribuinte.equals=" + UPDATED_NUM_CONTRIBUINTE);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByNumContribuinteIsInShouldWork() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where numContribuinte in DEFAULT_NUM_CONTRIBUINTE or UPDATED_NUM_CONTRIBUINTE
        defaultFornecedorShouldBeFound("numContribuinte.in=" + DEFAULT_NUM_CONTRIBUINTE + "," + UPDATED_NUM_CONTRIBUINTE);

        // Get all the fornecedorList where numContribuinte equals to UPDATED_NUM_CONTRIBUINTE
        defaultFornecedorShouldNotBeFound("numContribuinte.in=" + UPDATED_NUM_CONTRIBUINTE);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByNumContribuinteIsNullOrNotNull() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where numContribuinte is not null
        defaultFornecedorShouldBeFound("numContribuinte.specified=true");

        // Get all the fornecedorList where numContribuinte is null
        defaultFornecedorShouldNotBeFound("numContribuinte.specified=false");
    }

    @Test
    @Transactional
    public void getAllFornecedorsByEnderecoIsEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where endereco equals to DEFAULT_ENDERECO
        defaultFornecedorShouldBeFound("endereco.equals=" + DEFAULT_ENDERECO);

        // Get all the fornecedorList where endereco equals to UPDATED_ENDERECO
        defaultFornecedorShouldNotBeFound("endereco.equals=" + UPDATED_ENDERECO);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByEnderecoIsInShouldWork() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where endereco in DEFAULT_ENDERECO or UPDATED_ENDERECO
        defaultFornecedorShouldBeFound("endereco.in=" + DEFAULT_ENDERECO + "," + UPDATED_ENDERECO);

        // Get all the fornecedorList where endereco equals to UPDATED_ENDERECO
        defaultFornecedorShouldNotBeFound("endereco.in=" + UPDATED_ENDERECO);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByEnderecoIsNullOrNotNull() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where endereco is not null
        defaultFornecedorShouldBeFound("endereco.specified=true");

        // Get all the fornecedorList where endereco is null
        defaultFornecedorShouldNotBeFound("endereco.specified=false");
    }

    @Test
    @Transactional
    public void getAllFornecedorsByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where email equals to DEFAULT_EMAIL
        defaultFornecedorShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the fornecedorList where email equals to UPDATED_EMAIL
        defaultFornecedorShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultFornecedorShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the fornecedorList where email equals to UPDATED_EMAIL
        defaultFornecedorShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where email is not null
        defaultFornecedorShouldBeFound("email.specified=true");

        // Get all the fornecedorList where email is null
        defaultFornecedorShouldNotBeFound("email.specified=false");
    }

    @Test
    @Transactional
    public void getAllFornecedorsByEspecialidadeIsEqualToSomething() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where especialidade equals to DEFAULT_ESPECIALIDADE
        defaultFornecedorShouldBeFound("especialidade.equals=" + DEFAULT_ESPECIALIDADE);

        // Get all the fornecedorList where especialidade equals to UPDATED_ESPECIALIDADE
        defaultFornecedorShouldNotBeFound("especialidade.equals=" + UPDATED_ESPECIALIDADE);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByEspecialidadeIsInShouldWork() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where especialidade in DEFAULT_ESPECIALIDADE or UPDATED_ESPECIALIDADE
        defaultFornecedorShouldBeFound("especialidade.in=" + DEFAULT_ESPECIALIDADE + "," + UPDATED_ESPECIALIDADE);

        // Get all the fornecedorList where especialidade equals to UPDATED_ESPECIALIDADE
        defaultFornecedorShouldNotBeFound("especialidade.in=" + UPDATED_ESPECIALIDADE);
    }

    @Test
    @Transactional
    public void getAllFornecedorsByEspecialidadeIsNullOrNotNull() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);

        // Get all the fornecedorList where especialidade is not null
        defaultFornecedorShouldBeFound("especialidade.specified=true");

        // Get all the fornecedorList where especialidade is null
        defaultFornecedorShouldNotBeFound("especialidade.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultFornecedorShouldBeFound(String filter) throws Exception {
        restFornecedorMockMvc.perform(get("/api/fornecedors?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fornecedor.getId().intValue())))
            .andExpect(jsonPath("$.[*].idFornecedor").value(hasItem(DEFAULT_ID_FORNECEDOR.intValue())))
            .andExpect(jsonPath("$.[*].nmFornecedor").value(hasItem(DEFAULT_NM_FORNECEDOR.toString())))
            .andExpect(jsonPath("$.[*].numContribuinte").value(hasItem(DEFAULT_NUM_CONTRIBUINTE.toString())))
            .andExpect(jsonPath("$.[*].endereco").value(hasItem(DEFAULT_ENDERECO.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].especialidade").value(hasItem(DEFAULT_ESPECIALIDADE.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultFornecedorShouldNotBeFound(String filter) throws Exception {
        restFornecedorMockMvc.perform(get("/api/fornecedors?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingFornecedor() throws Exception {
        // Get the fornecedor
        restFornecedorMockMvc.perform(get("/api/fornecedors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFornecedor() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);
        int databaseSizeBeforeUpdate = fornecedorRepository.findAll().size();

        // Update the fornecedor
        Fornecedor updatedFornecedor = fornecedorRepository.findOne(fornecedor.getId());
        // Disconnect from session so that the updates on updatedFornecedor are not directly saved in db
        em.detach(updatedFornecedor);
        updatedFornecedor
            .nmFornecedor(UPDATED_NM_FORNECEDOR)
            .numContribuinte(UPDATED_NUM_CONTRIBUINTE)
            .endereco(UPDATED_ENDERECO)
            .email(UPDATED_EMAIL)
            .especialidade(UPDATED_ESPECIALIDADE);
        FornecedorDTO fornecedorDTO = fornecedorMapper.toDto(updatedFornecedor);

        restFornecedorMockMvc.perform(put("/api/fornecedors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fornecedorDTO)))
            .andExpect(status().isOk());

        // Validate the Fornecedor in the database
        List<Fornecedor> fornecedorList = fornecedorRepository.findAll();
        assertThat(fornecedorList).hasSize(databaseSizeBeforeUpdate);
        Fornecedor testFornecedor = fornecedorList.get(fornecedorList.size() - 1);
        assertThat(testFornecedor.getNmFornecedor()).isEqualTo(UPDATED_NM_FORNECEDOR);
        assertThat(testFornecedor.getNumContribuinte()).isEqualTo(UPDATED_NUM_CONTRIBUINTE);
        assertThat(testFornecedor.getEndereco()).isEqualTo(UPDATED_ENDERECO);
        assertThat(testFornecedor.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testFornecedor.getEspecialidade()).isEqualTo(UPDATED_ESPECIALIDADE);
    }

    @Test
    @Transactional
    public void updateNonExistingFornecedor() throws Exception {
        int databaseSizeBeforeUpdate = fornecedorRepository.findAll().size();

        // Create the Fornecedor
        FornecedorDTO fornecedorDTO = fornecedorMapper.toDto(fornecedor);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFornecedorMockMvc.perform(put("/api/fornecedors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fornecedorDTO)))
            .andExpect(status().isCreated());

        // Validate the Fornecedor in the database
        List<Fornecedor> fornecedorList = fornecedorRepository.findAll();
        assertThat(fornecedorList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFornecedor() throws Exception {
        // Initialize the database
        fornecedorRepository.saveAndFlush(fornecedor);
        int databaseSizeBeforeDelete = fornecedorRepository.findAll().size();

        // Get the fornecedor
        restFornecedorMockMvc.perform(delete("/api/fornecedors/{id}", fornecedor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Fornecedor> fornecedorList = fornecedorRepository.findAll();
        assertThat(fornecedorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Fornecedor.class);
        Fornecedor fornecedor1 = new Fornecedor();
        fornecedor1.setId(1L);
        Fornecedor fornecedor2 = new Fornecedor();
        fornecedor2.setId(fornecedor1.getId());
        assertThat(fornecedor1).isEqualTo(fornecedor2);
        fornecedor2.setId(2L);
        assertThat(fornecedor1).isNotEqualTo(fornecedor2);
        fornecedor1.setId(null);
        assertThat(fornecedor1).isNotEqualTo(fornecedor2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FornecedorDTO.class);
        FornecedorDTO fornecedorDTO1 = new FornecedorDTO();
        fornecedorDTO1.setId(1L);
        FornecedorDTO fornecedorDTO2 = new FornecedorDTO();
        assertThat(fornecedorDTO1).isNotEqualTo(fornecedorDTO2);
        fornecedorDTO2.setId(fornecedorDTO1.getId());
        assertThat(fornecedorDTO1).isEqualTo(fornecedorDTO2);
        fornecedorDTO2.setId(2L);
        assertThat(fornecedorDTO1).isNotEqualTo(fornecedorDTO2);
        fornecedorDTO1.setId(null);
        assertThat(fornecedorDTO1).isNotEqualTo(fornecedorDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(fornecedorMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(fornecedorMapper.fromId(null)).isNull();
    }
}
