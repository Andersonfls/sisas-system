package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;

import com.minea.sisas.domain.Publicacao;
import com.minea.sisas.domain.Situacao;
import com.minea.sisas.domain.Inicio;
import com.minea.sisas.repository.PublicacaoRepository;
import com.minea.sisas.service.PublicacaoService;
import com.minea.sisas.service.dto.PublicacaoDTO;
import com.minea.sisas.service.mapper.PublicacaoMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;
import com.minea.sisas.service.PublicacaoQueryService;

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
 * Test class for the PublicacaoResource REST controller.
 *
 * @see PublicacaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class PublicacaoResourceIntTest {

    private static final Long DEFAULT_ID_PUBLICACAO = 1L;
    private static final Long UPDATED_ID_PUBLICACAO = 2L;

    private static final String DEFAULT_TITULO_PUBLICACAO = "AAAAAAAAAA";
    private static final String UPDATED_TITULO_PUBLICACAO = "BBBBBBBBBB";

    private static final String DEFAULT_TEXTO_PUBLICACAO = "AAAAAAAAAA";
    private static final String UPDATED_TEXTO_PUBLICACAO = "BBBBBBBBBB";

    private static final String DEFAULT_RESUMO_TEXTO_PUBLICACAO = "AAAAAAAAAA";
    private static final String UPDATED_RESUMO_TEXTO_PUBLICACAO = "BBBBBBBBBB";

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private PublicacaoMapper publicacaoMapper;

    @Autowired
    private PublicacaoService publicacaoService;

    @Autowired
    private PublicacaoQueryService publicacaoQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPublicacaoMockMvc;

    private Publicacao publicacao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PublicacaoResource publicacaoResource = new PublicacaoResource(publicacaoService, publicacaoQueryService);
        this.restPublicacaoMockMvc = MockMvcBuilders.standaloneSetup(publicacaoResource)
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
    public static Publicacao createEntity(EntityManager em) {
        Publicacao publicacao = new Publicacao()
            .tituloPublicacao(DEFAULT_TITULO_PUBLICACAO)
            .textoPublicacao(DEFAULT_TEXTO_PUBLICACAO)
            .resumoTextoPublicacao(DEFAULT_RESUMO_TEXTO_PUBLICACAO);
        // Add required entity
        Situacao idSituacao = SituacaoResourceIntTest.createEntity(em);
        em.persist(idSituacao);
        em.flush();
        publicacao.setSituacao(idSituacao);
        return publicacao;
    }

    @Before
    public void initTest() {
        publicacao = createEntity(em);
    }

    @Test
    @Transactional
    public void createPublicacao() throws Exception {
        int databaseSizeBeforeCreate = publicacaoRepository.findAll().size();

        // Create the Publicacao
        PublicacaoDTO publicacaoDTO = publicacaoMapper.toDto(publicacao);
        restPublicacaoMockMvc.perform(post("/api/publicacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(publicacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Publicacao in the database
        List<Publicacao> publicacaoList = publicacaoRepository.findAll();
        assertThat(publicacaoList).hasSize(databaseSizeBeforeCreate + 1);
        Publicacao testPublicacao = publicacaoList.get(publicacaoList.size() - 1);
        assertThat(testPublicacao.getTituloPublicacao()).isEqualTo(DEFAULT_TITULO_PUBLICACAO);
        assertThat(testPublicacao.getTextoPublicacao()).isEqualTo(DEFAULT_TEXTO_PUBLICACAO);
        assertThat(testPublicacao.getResumoTextoPublicacao()).isEqualTo(DEFAULT_RESUMO_TEXTO_PUBLICACAO);
    }

    @Test
    @Transactional
    public void createPublicacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = publicacaoRepository.findAll().size();

        // Create the Publicacao with an existing ID
        publicacao.setId(1L);
        PublicacaoDTO publicacaoDTO = publicacaoMapper.toDto(publicacao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPublicacaoMockMvc.perform(post("/api/publicacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(publicacaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Publicacao in the database
        List<Publicacao> publicacaoList = publicacaoRepository.findAll();
        assertThat(publicacaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdPublicacaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = publicacaoRepository.findAll().size();
        // set the field null

        // Create the Publicacao, which fails.
        PublicacaoDTO publicacaoDTO = publicacaoMapper.toDto(publicacao);

        restPublicacaoMockMvc.perform(post("/api/publicacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(publicacaoDTO)))
            .andExpect(status().isBadRequest());

        List<Publicacao> publicacaoList = publicacaoRepository.findAll();
        assertThat(publicacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTituloPublicacaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = publicacaoRepository.findAll().size();
        // set the field null
        publicacao.setTituloPublicacao(null);

        // Create the Publicacao, which fails.
        PublicacaoDTO publicacaoDTO = publicacaoMapper.toDto(publicacao);

        restPublicacaoMockMvc.perform(post("/api/publicacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(publicacaoDTO)))
            .andExpect(status().isBadRequest());

        List<Publicacao> publicacaoList = publicacaoRepository.findAll();
        assertThat(publicacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTextoPublicacaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = publicacaoRepository.findAll().size();
        // set the field null
        publicacao.setTextoPublicacao(null);

        // Create the Publicacao, which fails.
        PublicacaoDTO publicacaoDTO = publicacaoMapper.toDto(publicacao);

        restPublicacaoMockMvc.perform(post("/api/publicacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(publicacaoDTO)))
            .andExpect(status().isBadRequest());

        List<Publicacao> publicacaoList = publicacaoRepository.findAll();
        assertThat(publicacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkResumoTextoPublicacaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = publicacaoRepository.findAll().size();
        // set the field null
        publicacao.setResumoTextoPublicacao(null);

        // Create the Publicacao, which fails.
        PublicacaoDTO publicacaoDTO = publicacaoMapper.toDto(publicacao);

        restPublicacaoMockMvc.perform(post("/api/publicacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(publicacaoDTO)))
            .andExpect(status().isBadRequest());

        List<Publicacao> publicacaoList = publicacaoRepository.findAll();
        assertThat(publicacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPublicacaos() throws Exception {
        // Initialize the database
        publicacaoRepository.saveAndFlush(publicacao);

        // Get all the publicacaoList
        restPublicacaoMockMvc.perform(get("/api/publicacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(publicacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPublicacao").value(hasItem(DEFAULT_ID_PUBLICACAO.intValue())))
            .andExpect(jsonPath("$.[*].tituloPublicacao").value(hasItem(DEFAULT_TITULO_PUBLICACAO.toString())))
            .andExpect(jsonPath("$.[*].textoPublicacao").value(hasItem(DEFAULT_TEXTO_PUBLICACAO.toString())))
            .andExpect(jsonPath("$.[*].resumoTextoPublicacao").value(hasItem(DEFAULT_RESUMO_TEXTO_PUBLICACAO.toString())));
    }

    @Test
    @Transactional
    public void getPublicacao() throws Exception {
        // Initialize the database
        publicacaoRepository.saveAndFlush(publicacao);

        // Get the publicacao
        restPublicacaoMockMvc.perform(get("/api/publicacaos/{id}", publicacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(publicacao.getId().intValue()))
            .andExpect(jsonPath("$.idPublicacao").value(DEFAULT_ID_PUBLICACAO.intValue()))
            .andExpect(jsonPath("$.tituloPublicacao").value(DEFAULT_TITULO_PUBLICACAO.toString()))
            .andExpect(jsonPath("$.textoPublicacao").value(DEFAULT_TEXTO_PUBLICACAO.toString()))
            .andExpect(jsonPath("$.resumoTextoPublicacao").value(DEFAULT_RESUMO_TEXTO_PUBLICACAO.toString()));
    }

    @Test
    @Transactional
    public void getAllPublicacaosByIdPublicacaoIsEqualToSomething() throws Exception {
        // Initialize the database
        publicacaoRepository.saveAndFlush(publicacao);

        // Get all the publicacaoList where idPublicacao equals to DEFAULT_ID_PUBLICACAO
        defaultPublicacaoShouldBeFound("idPublicacao.equals=" + DEFAULT_ID_PUBLICACAO);

        // Get all the publicacaoList where idPublicacao equals to UPDATED_ID_PUBLICACAO
        defaultPublicacaoShouldNotBeFound("idPublicacao.equals=" + UPDATED_ID_PUBLICACAO);
    }

    @Test
    @Transactional
    public void getAllPublicacaosByIdPublicacaoIsInShouldWork() throws Exception {
        // Initialize the database
        publicacaoRepository.saveAndFlush(publicacao);

        // Get all the publicacaoList where idPublicacao in DEFAULT_ID_PUBLICACAO or UPDATED_ID_PUBLICACAO
        defaultPublicacaoShouldBeFound("idPublicacao.in=" + DEFAULT_ID_PUBLICACAO + "," + UPDATED_ID_PUBLICACAO);

        // Get all the publicacaoList where idPublicacao equals to UPDATED_ID_PUBLICACAO
        defaultPublicacaoShouldNotBeFound("idPublicacao.in=" + UPDATED_ID_PUBLICACAO);
    }

    @Test
    @Transactional
    public void getAllPublicacaosByIdPublicacaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        publicacaoRepository.saveAndFlush(publicacao);

        // Get all the publicacaoList where idPublicacao is not null
        defaultPublicacaoShouldBeFound("idPublicacao.specified=true");

        // Get all the publicacaoList where idPublicacao is null
        defaultPublicacaoShouldNotBeFound("idPublicacao.specified=false");
    }

    @Test
    @Transactional
    public void getAllPublicacaosByIdPublicacaoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        publicacaoRepository.saveAndFlush(publicacao);

        // Get all the publicacaoList where idPublicacao greater than or equals to DEFAULT_ID_PUBLICACAO
        defaultPublicacaoShouldBeFound("idPublicacao.greaterOrEqualThan=" + DEFAULT_ID_PUBLICACAO);

        // Get all the publicacaoList where idPublicacao greater than or equals to UPDATED_ID_PUBLICACAO
        defaultPublicacaoShouldNotBeFound("idPublicacao.greaterOrEqualThan=" + UPDATED_ID_PUBLICACAO);
    }

    @Test
    @Transactional
    public void getAllPublicacaosByIdPublicacaoIsLessThanSomething() throws Exception {
        // Initialize the database
        publicacaoRepository.saveAndFlush(publicacao);

        // Get all the publicacaoList where idPublicacao less than or equals to DEFAULT_ID_PUBLICACAO
        defaultPublicacaoShouldNotBeFound("idPublicacao.lessThan=" + DEFAULT_ID_PUBLICACAO);

        // Get all the publicacaoList where idPublicacao less than or equals to UPDATED_ID_PUBLICACAO
        defaultPublicacaoShouldBeFound("idPublicacao.lessThan=" + UPDATED_ID_PUBLICACAO);
    }


    @Test
    @Transactional
    public void getAllPublicacaosByTituloPublicacaoIsEqualToSomething() throws Exception {
        // Initialize the database
        publicacaoRepository.saveAndFlush(publicacao);

        // Get all the publicacaoList where tituloPublicacao equals to DEFAULT_TITULO_PUBLICACAO
        defaultPublicacaoShouldBeFound("tituloPublicacao.equals=" + DEFAULT_TITULO_PUBLICACAO);

        // Get all the publicacaoList where tituloPublicacao equals to UPDATED_TITULO_PUBLICACAO
        defaultPublicacaoShouldNotBeFound("tituloPublicacao.equals=" + UPDATED_TITULO_PUBLICACAO);
    }

    @Test
    @Transactional
    public void getAllPublicacaosByTituloPublicacaoIsInShouldWork() throws Exception {
        // Initialize the database
        publicacaoRepository.saveAndFlush(publicacao);

        // Get all the publicacaoList where tituloPublicacao in DEFAULT_TITULO_PUBLICACAO or UPDATED_TITULO_PUBLICACAO
        defaultPublicacaoShouldBeFound("tituloPublicacao.in=" + DEFAULT_TITULO_PUBLICACAO + "," + UPDATED_TITULO_PUBLICACAO);

        // Get all the publicacaoList where tituloPublicacao equals to UPDATED_TITULO_PUBLICACAO
        defaultPublicacaoShouldNotBeFound("tituloPublicacao.in=" + UPDATED_TITULO_PUBLICACAO);
    }

    @Test
    @Transactional
    public void getAllPublicacaosByTituloPublicacaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        publicacaoRepository.saveAndFlush(publicacao);

        // Get all the publicacaoList where tituloPublicacao is not null
        defaultPublicacaoShouldBeFound("tituloPublicacao.specified=true");

        // Get all the publicacaoList where tituloPublicacao is null
        defaultPublicacaoShouldNotBeFound("tituloPublicacao.specified=false");
    }

    @Test
    @Transactional
    public void getAllPublicacaosByTextoPublicacaoIsEqualToSomething() throws Exception {
        // Initialize the database
        publicacaoRepository.saveAndFlush(publicacao);

        // Get all the publicacaoList where textoPublicacao equals to DEFAULT_TEXTO_PUBLICACAO
        defaultPublicacaoShouldBeFound("textoPublicacao.equals=" + DEFAULT_TEXTO_PUBLICACAO);

        // Get all the publicacaoList where textoPublicacao equals to UPDATED_TEXTO_PUBLICACAO
        defaultPublicacaoShouldNotBeFound("textoPublicacao.equals=" + UPDATED_TEXTO_PUBLICACAO);
    }

    @Test
    @Transactional
    public void getAllPublicacaosByTextoPublicacaoIsInShouldWork() throws Exception {
        // Initialize the database
        publicacaoRepository.saveAndFlush(publicacao);

        // Get all the publicacaoList where textoPublicacao in DEFAULT_TEXTO_PUBLICACAO or UPDATED_TEXTO_PUBLICACAO
        defaultPublicacaoShouldBeFound("textoPublicacao.in=" + DEFAULT_TEXTO_PUBLICACAO + "," + UPDATED_TEXTO_PUBLICACAO);

        // Get all the publicacaoList where textoPublicacao equals to UPDATED_TEXTO_PUBLICACAO
        defaultPublicacaoShouldNotBeFound("textoPublicacao.in=" + UPDATED_TEXTO_PUBLICACAO);
    }

    @Test
    @Transactional
    public void getAllPublicacaosByTextoPublicacaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        publicacaoRepository.saveAndFlush(publicacao);

        // Get all the publicacaoList where textoPublicacao is not null
        defaultPublicacaoShouldBeFound("textoPublicacao.specified=true");

        // Get all the publicacaoList where textoPublicacao is null
        defaultPublicacaoShouldNotBeFound("textoPublicacao.specified=false");
    }

    @Test
    @Transactional
    public void getAllPublicacaosByResumoTextoPublicacaoIsEqualToSomething() throws Exception {
        // Initialize the database
        publicacaoRepository.saveAndFlush(publicacao);

        // Get all the publicacaoList where resumoTextoPublicacao equals to DEFAULT_RESUMO_TEXTO_PUBLICACAO
        defaultPublicacaoShouldBeFound("resumoTextoPublicacao.equals=" + DEFAULT_RESUMO_TEXTO_PUBLICACAO);

        // Get all the publicacaoList where resumoTextoPublicacao equals to UPDATED_RESUMO_TEXTO_PUBLICACAO
        defaultPublicacaoShouldNotBeFound("resumoTextoPublicacao.equals=" + UPDATED_RESUMO_TEXTO_PUBLICACAO);
    }

    @Test
    @Transactional
    public void getAllPublicacaosByResumoTextoPublicacaoIsInShouldWork() throws Exception {
        // Initialize the database
        publicacaoRepository.saveAndFlush(publicacao);

        // Get all the publicacaoList where resumoTextoPublicacao in DEFAULT_RESUMO_TEXTO_PUBLICACAO or UPDATED_RESUMO_TEXTO_PUBLICACAO
        defaultPublicacaoShouldBeFound("resumoTextoPublicacao.in=" + DEFAULT_RESUMO_TEXTO_PUBLICACAO + "," + UPDATED_RESUMO_TEXTO_PUBLICACAO);

        // Get all the publicacaoList where resumoTextoPublicacao equals to UPDATED_RESUMO_TEXTO_PUBLICACAO
        defaultPublicacaoShouldNotBeFound("resumoTextoPublicacao.in=" + UPDATED_RESUMO_TEXTO_PUBLICACAO);
    }

    @Test
    @Transactional
    public void getAllPublicacaosByResumoTextoPublicacaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        publicacaoRepository.saveAndFlush(publicacao);

        // Get all the publicacaoList where resumoTextoPublicacao is not null
        defaultPublicacaoShouldBeFound("resumoTextoPublicacao.specified=true");

        // Get all the publicacaoList where resumoTextoPublicacao is null
        defaultPublicacaoShouldNotBeFound("resumoTextoPublicacao.specified=false");
    }

    @Test
    @Transactional
    public void getAllPublicacaosByIdSituacaoIsEqualToSomething() throws Exception {
        // Initialize the database
        Situacao idSituacao = SituacaoResourceIntTest.createEntity(em);
        em.persist(idSituacao);
        em.flush();
        publicacao.setSituacao(idSituacao);
        publicacaoRepository.saveAndFlush(publicacao);
        Long idSituacaoId = idSituacao.getId();

        // Get all the publicacaoList where idSituacao equals to situacao
        defaultPublicacaoShouldBeFound("situacao.equals=" + idSituacaoId);

        // Get all the publicacaoList where idSituacao equals to situacao + 1
        defaultPublicacaoShouldNotBeFound("situacao.equals=" + (idSituacaoId + 1));
    }


    @Test
    @Transactional
    public void getAllPublicacaosByInicioIsEqualToSomething() throws Exception {
        // Initialize the database
        Inicio inicio = InicioResourceIntTest.createEntity(em);
        em.persist(inicio);
        em.flush();
        publicacaoRepository.saveAndFlush(publicacao);
        Long inicioId = inicio.getId();

        // Get all the publicacaoList where inicio equals to inicioId
        defaultPublicacaoShouldBeFound("inicioId.equals=" + inicioId);

        // Get all the publicacaoList where inicio equals to inicioId + 1
        defaultPublicacaoShouldNotBeFound("inicioId.equals=" + (inicioId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultPublicacaoShouldBeFound(String filter) throws Exception {
        restPublicacaoMockMvc.perform(get("/api/publicacaos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(publicacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPublicacao").value(hasItem(DEFAULT_ID_PUBLICACAO.intValue())))
            .andExpect(jsonPath("$.[*].tituloPublicacao").value(hasItem(DEFAULT_TITULO_PUBLICACAO.toString())))
            .andExpect(jsonPath("$.[*].textoPublicacao").value(hasItem(DEFAULT_TEXTO_PUBLICACAO.toString())))
            .andExpect(jsonPath("$.[*].resumoTextoPublicacao").value(hasItem(DEFAULT_RESUMO_TEXTO_PUBLICACAO.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultPublicacaoShouldNotBeFound(String filter) throws Exception {
        restPublicacaoMockMvc.perform(get("/api/publicacaos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingPublicacao() throws Exception {
        // Get the publicacao
        restPublicacaoMockMvc.perform(get("/api/publicacaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePublicacao() throws Exception {
        // Initialize the database
        publicacaoRepository.saveAndFlush(publicacao);
        int databaseSizeBeforeUpdate = publicacaoRepository.findAll().size();

        // Update the publicacao
        Publicacao updatedPublicacao = publicacaoRepository.findOne(publicacao.getId());
        // Disconnect from session so that the updates on updatedPublicacao are not directly saved in db
        em.detach(updatedPublicacao);
        updatedPublicacao
            .tituloPublicacao(UPDATED_TITULO_PUBLICACAO)
            .textoPublicacao(UPDATED_TEXTO_PUBLICACAO)
            .resumoTextoPublicacao(UPDATED_RESUMO_TEXTO_PUBLICACAO);
        PublicacaoDTO publicacaoDTO = publicacaoMapper.toDto(updatedPublicacao);

        restPublicacaoMockMvc.perform(put("/api/publicacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(publicacaoDTO)))
            .andExpect(status().isOk());

        // Validate the Publicacao in the database
        List<Publicacao> publicacaoList = publicacaoRepository.findAll();
        assertThat(publicacaoList).hasSize(databaseSizeBeforeUpdate);
        Publicacao testPublicacao = publicacaoList.get(publicacaoList.size() - 1);
        assertThat(testPublicacao.getTituloPublicacao()).isEqualTo(UPDATED_TITULO_PUBLICACAO);
        assertThat(testPublicacao.getTextoPublicacao()).isEqualTo(UPDATED_TEXTO_PUBLICACAO);
        assertThat(testPublicacao.getResumoTextoPublicacao()).isEqualTo(UPDATED_RESUMO_TEXTO_PUBLICACAO);
    }

    @Test
    @Transactional
    public void updateNonExistingPublicacao() throws Exception {
        int databaseSizeBeforeUpdate = publicacaoRepository.findAll().size();

        // Create the Publicacao
        PublicacaoDTO publicacaoDTO = publicacaoMapper.toDto(publicacao);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPublicacaoMockMvc.perform(put("/api/publicacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(publicacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Publicacao in the database
        List<Publicacao> publicacaoList = publicacaoRepository.findAll();
        assertThat(publicacaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePublicacao() throws Exception {
        // Initialize the database
        publicacaoRepository.saveAndFlush(publicacao);
        int databaseSizeBeforeDelete = publicacaoRepository.findAll().size();

        // Get the publicacao
        restPublicacaoMockMvc.perform(delete("/api/publicacaos/{id}", publicacao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Publicacao> publicacaoList = publicacaoRepository.findAll();
        assertThat(publicacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Publicacao.class);
        Publicacao publicacao1 = new Publicacao();
        publicacao1.setId(1L);
        Publicacao publicacao2 = new Publicacao();
        publicacao2.setId(publicacao1.getId());
        assertThat(publicacao1).isEqualTo(publicacao2);
        publicacao2.setId(2L);
        assertThat(publicacao1).isNotEqualTo(publicacao2);
        publicacao1.setId(null);
        assertThat(publicacao1).isNotEqualTo(publicacao2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PublicacaoDTO.class);
        PublicacaoDTO publicacaoDTO1 = new PublicacaoDTO();
        publicacaoDTO1.setId(1L);
        PublicacaoDTO publicacaoDTO2 = new PublicacaoDTO();
        assertThat(publicacaoDTO1).isNotEqualTo(publicacaoDTO2);
        publicacaoDTO2.setId(publicacaoDTO1.getId());
        assertThat(publicacaoDTO1).isEqualTo(publicacaoDTO2);
        publicacaoDTO2.setId(2L);
        assertThat(publicacaoDTO1).isNotEqualTo(publicacaoDTO2);
        publicacaoDTO1.setId(null);
        assertThat(publicacaoDTO1).isNotEqualTo(publicacaoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(publicacaoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(publicacaoMapper.fromId(null)).isNull();
    }
}
