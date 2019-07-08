package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;

import com.minea.sisas.domain.Noticias;
import com.minea.sisas.domain.Situacao;
import com.minea.sisas.domain.Inicio;
import com.minea.sisas.repository.NoticiasRepository;
import com.minea.sisas.service.NoticiasService;
import com.minea.sisas.service.dto.NoticiasDTO;
import com.minea.sisas.service.mapper.NoticiasMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;
import com.minea.sisas.service.NoticiasQueryService;

import com.minea.sisas.web.rest.NoticiasResource;
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
 * Test class for the NoticiasResource REST controller.
 *
 * @see NoticiasResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class NoticiasResourceIntTest {

    private static final Long DEFAULT_ID_NOTICIAS = 1L;
    private static final Long UPDATED_ID_NOTICIAS = 2L;

    private static final String DEFAULT_TITULO_NOTICIAS = "AAAAAAAAAA";
    private static final String UPDATED_TITULO_NOTICIAS = "BBBBBBBBBB";

    private static final String DEFAULT_TEXTO_NOTICIAS = "AAAAAAAAAA";
    private static final String UPDATED_TEXTO_NOTICIAS = "BBBBBBBBBB";

    private static final String DEFAULT_RESUMO_TEXTO_NOTICIAS = "AAAAAAAAAA";
    private static final String UPDATED_RESUMO_TEXTO_NOTICIAS = "BBBBBBBBBB";

    @Autowired
    private NoticiasRepository noticiasRepository;

    @Autowired
    private NoticiasMapper noticiasMapper;

    @Autowired
    private NoticiasService noticiasService;

    @Autowired
    private NoticiasQueryService noticiasQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNoticiasMockMvc;

    private Noticias noticias;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NoticiasResource noticiasResource = new NoticiasResource(noticiasService, noticiasQueryService);
        this.restNoticiasMockMvc = MockMvcBuilders.standaloneSetup(noticiasResource)
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
    public static Noticias createEntity(EntityManager em) {
        Noticias noticias = new Noticias()
            .tituloNoticias(DEFAULT_TITULO_NOTICIAS)
            .textoNoticias(DEFAULT_TEXTO_NOTICIAS)
            .resumoTextoNoticias(DEFAULT_RESUMO_TEXTO_NOTICIAS);
        // Add required entity
        Situacao idSituacao = SituacaoResourceIntTest.createEntity(em);
        em.persist(idSituacao);
        em.flush();
        noticias.setIdSituacao(idSituacao);
        return noticias;
    }

    @Before
    public void initTest() {
        noticias = createEntity(em);
    }

    @Test
    @Transactional
    public void createNoticias() throws Exception {
        int databaseSizeBeforeCreate = noticiasRepository.findAll().size();

        // Create the Noticias
        NoticiasDTO noticiasDTO = noticiasMapper.toDto(noticias);
        restNoticiasMockMvc.perform(post("/api/noticias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noticiasDTO)))
            .andExpect(status().isCreated());

        // Validate the Noticias in the database
        List<Noticias> noticiasList = noticiasRepository.findAll();
        assertThat(noticiasList).hasSize(databaseSizeBeforeCreate + 1);
        Noticias testNoticias = noticiasList.get(noticiasList.size() - 1);
        assertThat(testNoticias.getTituloNoticias()).isEqualTo(DEFAULT_TITULO_NOTICIAS);
        assertThat(testNoticias.getTextoNoticias()).isEqualTo(DEFAULT_TEXTO_NOTICIAS);
        assertThat(testNoticias.getResumoTextoNoticias()).isEqualTo(DEFAULT_RESUMO_TEXTO_NOTICIAS);
    }

    @Test
    @Transactional
    public void createNoticiasWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = noticiasRepository.findAll().size();

        // Create the Noticias with an existing ID
        noticias.setId(1L);
        NoticiasDTO noticiasDTO = noticiasMapper.toDto(noticias);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNoticiasMockMvc.perform(post("/api/noticias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noticiasDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Noticias in the database
        List<Noticias> noticiasList = noticiasRepository.findAll();
        assertThat(noticiasList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdNoticiasIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticiasRepository.findAll().size();
        // set the field null

        // Create the Noticias, which fails.
        NoticiasDTO noticiasDTO = noticiasMapper.toDto(noticias);

        restNoticiasMockMvc.perform(post("/api/noticias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noticiasDTO)))
            .andExpect(status().isBadRequest());

        List<Noticias> noticiasList = noticiasRepository.findAll();
        assertThat(noticiasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTituloNoticiasIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticiasRepository.findAll().size();
        // set the field null
        noticias.setTituloNoticias(null);

        // Create the Noticias, which fails.
        NoticiasDTO noticiasDTO = noticiasMapper.toDto(noticias);

        restNoticiasMockMvc.perform(post("/api/noticias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noticiasDTO)))
            .andExpect(status().isBadRequest());

        List<Noticias> noticiasList = noticiasRepository.findAll();
        assertThat(noticiasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTextoNoticiasIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticiasRepository.findAll().size();
        // set the field null
        noticias.setTextoNoticias(null);

        // Create the Noticias, which fails.
        NoticiasDTO noticiasDTO = noticiasMapper.toDto(noticias);

        restNoticiasMockMvc.perform(post("/api/noticias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noticiasDTO)))
            .andExpect(status().isBadRequest());

        List<Noticias> noticiasList = noticiasRepository.findAll();
        assertThat(noticiasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkResumoTextoNoticiasIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticiasRepository.findAll().size();
        // set the field null
        noticias.setResumoTextoNoticias(null);

        // Create the Noticias, which fails.
        NoticiasDTO noticiasDTO = noticiasMapper.toDto(noticias);

        restNoticiasMockMvc.perform(post("/api/noticias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noticiasDTO)))
            .andExpect(status().isBadRequest());

        List<Noticias> noticiasList = noticiasRepository.findAll();
        assertThat(noticiasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNoticias() throws Exception {
        // Initialize the database
        noticiasRepository.saveAndFlush(noticias);

        // Get all the noticiasList
        restNoticiasMockMvc.perform(get("/api/noticias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(noticias.getId().intValue())))
            .andExpect(jsonPath("$.[*].idNoticias").value(hasItem(DEFAULT_ID_NOTICIAS.intValue())))
            .andExpect(jsonPath("$.[*].tituloNoticias").value(hasItem(DEFAULT_TITULO_NOTICIAS.toString())))
            .andExpect(jsonPath("$.[*].textoNoticias").value(hasItem(DEFAULT_TEXTO_NOTICIAS.toString())))
            .andExpect(jsonPath("$.[*].resumoTextoNoticias").value(hasItem(DEFAULT_RESUMO_TEXTO_NOTICIAS.toString())));
    }

    @Test
    @Transactional
    public void getNoticias() throws Exception {
        // Initialize the database
        noticiasRepository.saveAndFlush(noticias);

        // Get the noticias
        restNoticiasMockMvc.perform(get("/api/noticias/{id}", noticias.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(noticias.getId().intValue()))
            .andExpect(jsonPath("$.idNoticias").value(DEFAULT_ID_NOTICIAS.intValue()))
            .andExpect(jsonPath("$.tituloNoticias").value(DEFAULT_TITULO_NOTICIAS.toString()))
            .andExpect(jsonPath("$.textoNoticias").value(DEFAULT_TEXTO_NOTICIAS.toString()))
            .andExpect(jsonPath("$.resumoTextoNoticias").value(DEFAULT_RESUMO_TEXTO_NOTICIAS.toString()));
    }

    @Test
    @Transactional
    public void getAllNoticiasByIdNoticiasIsEqualToSomething() throws Exception {
        // Initialize the database
        noticiasRepository.saveAndFlush(noticias);

        // Get all the noticiasList where idNoticias equals to DEFAULT_ID_NOTICIAS
        defaultNoticiasShouldBeFound("idNoticias.equals=" + DEFAULT_ID_NOTICIAS);

        // Get all the noticiasList where idNoticias equals to UPDATED_ID_NOTICIAS
        defaultNoticiasShouldNotBeFound("idNoticias.equals=" + UPDATED_ID_NOTICIAS);
    }

    @Test
    @Transactional
    public void getAllNoticiasByIdNoticiasIsInShouldWork() throws Exception {
        // Initialize the database
        noticiasRepository.saveAndFlush(noticias);

        // Get all the noticiasList where idNoticias in DEFAULT_ID_NOTICIAS or UPDATED_ID_NOTICIAS
        defaultNoticiasShouldBeFound("idNoticias.in=" + DEFAULT_ID_NOTICIAS + "," + UPDATED_ID_NOTICIAS);

        // Get all the noticiasList where idNoticias equals to UPDATED_ID_NOTICIAS
        defaultNoticiasShouldNotBeFound("idNoticias.in=" + UPDATED_ID_NOTICIAS);
    }

    @Test
    @Transactional
    public void getAllNoticiasByIdNoticiasIsNullOrNotNull() throws Exception {
        // Initialize the database
        noticiasRepository.saveAndFlush(noticias);

        // Get all the noticiasList where idNoticias is not null
        defaultNoticiasShouldBeFound("idNoticias.specified=true");

        // Get all the noticiasList where idNoticias is null
        defaultNoticiasShouldNotBeFound("idNoticias.specified=false");
    }

    @Test
    @Transactional
    public void getAllNoticiasByIdNoticiasIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        noticiasRepository.saveAndFlush(noticias);

        // Get all the noticiasList where idNoticias greater than or equals to DEFAULT_ID_NOTICIAS
        defaultNoticiasShouldBeFound("idNoticias.greaterOrEqualThan=" + DEFAULT_ID_NOTICIAS);

        // Get all the noticiasList where idNoticias greater than or equals to UPDATED_ID_NOTICIAS
        defaultNoticiasShouldNotBeFound("idNoticias.greaterOrEqualThan=" + UPDATED_ID_NOTICIAS);
    }

    @Test
    @Transactional
    public void getAllNoticiasByIdNoticiasIsLessThanSomething() throws Exception {
        // Initialize the database
        noticiasRepository.saveAndFlush(noticias);

        // Get all the noticiasList where idNoticias less than or equals to DEFAULT_ID_NOTICIAS
        defaultNoticiasShouldNotBeFound("idNoticias.lessThan=" + DEFAULT_ID_NOTICIAS);

        // Get all the noticiasList where idNoticias less than or equals to UPDATED_ID_NOTICIAS
        defaultNoticiasShouldBeFound("idNoticias.lessThan=" + UPDATED_ID_NOTICIAS);
    }


    @Test
    @Transactional
    public void getAllNoticiasByTituloNoticiasIsEqualToSomething() throws Exception {
        // Initialize the database
        noticiasRepository.saveAndFlush(noticias);

        // Get all the noticiasList where tituloNoticias equals to DEFAULT_TITULO_NOTICIAS
        defaultNoticiasShouldBeFound("tituloNoticias.equals=" + DEFAULT_TITULO_NOTICIAS);

        // Get all the noticiasList where tituloNoticias equals to UPDATED_TITULO_NOTICIAS
        defaultNoticiasShouldNotBeFound("tituloNoticias.equals=" + UPDATED_TITULO_NOTICIAS);
    }

    @Test
    @Transactional
    public void getAllNoticiasByTituloNoticiasIsInShouldWork() throws Exception {
        // Initialize the database
        noticiasRepository.saveAndFlush(noticias);

        // Get all the noticiasList where tituloNoticias in DEFAULT_TITULO_NOTICIAS or UPDATED_TITULO_NOTICIAS
        defaultNoticiasShouldBeFound("tituloNoticias.in=" + DEFAULT_TITULO_NOTICIAS + "," + UPDATED_TITULO_NOTICIAS);

        // Get all the noticiasList where tituloNoticias equals to UPDATED_TITULO_NOTICIAS
        defaultNoticiasShouldNotBeFound("tituloNoticias.in=" + UPDATED_TITULO_NOTICIAS);
    }

    @Test
    @Transactional
    public void getAllNoticiasByTituloNoticiasIsNullOrNotNull() throws Exception {
        // Initialize the database
        noticiasRepository.saveAndFlush(noticias);

        // Get all the noticiasList where tituloNoticias is not null
        defaultNoticiasShouldBeFound("tituloNoticias.specified=true");

        // Get all the noticiasList where tituloNoticias is null
        defaultNoticiasShouldNotBeFound("tituloNoticias.specified=false");
    }

    @Test
    @Transactional
    public void getAllNoticiasByTextoNoticiasIsEqualToSomething() throws Exception {
        // Initialize the database
        noticiasRepository.saveAndFlush(noticias);

        // Get all the noticiasList where textoNoticias equals to DEFAULT_TEXTO_NOTICIAS
        defaultNoticiasShouldBeFound("textoNoticias.equals=" + DEFAULT_TEXTO_NOTICIAS);

        // Get all the noticiasList where textoNoticias equals to UPDATED_TEXTO_NOTICIAS
        defaultNoticiasShouldNotBeFound("textoNoticias.equals=" + UPDATED_TEXTO_NOTICIAS);
    }

    @Test
    @Transactional
    public void getAllNoticiasByTextoNoticiasIsInShouldWork() throws Exception {
        // Initialize the database
        noticiasRepository.saveAndFlush(noticias);

        // Get all the noticiasList where textoNoticias in DEFAULT_TEXTO_NOTICIAS or UPDATED_TEXTO_NOTICIAS
        defaultNoticiasShouldBeFound("textoNoticias.in=" + DEFAULT_TEXTO_NOTICIAS + "," + UPDATED_TEXTO_NOTICIAS);

        // Get all the noticiasList where textoNoticias equals to UPDATED_TEXTO_NOTICIAS
        defaultNoticiasShouldNotBeFound("textoNoticias.in=" + UPDATED_TEXTO_NOTICIAS);
    }

    @Test
    @Transactional
    public void getAllNoticiasByTextoNoticiasIsNullOrNotNull() throws Exception {
        // Initialize the database
        noticiasRepository.saveAndFlush(noticias);

        // Get all the noticiasList where textoNoticias is not null
        defaultNoticiasShouldBeFound("textoNoticias.specified=true");

        // Get all the noticiasList where textoNoticias is null
        defaultNoticiasShouldNotBeFound("textoNoticias.specified=false");
    }

    @Test
    @Transactional
    public void getAllNoticiasByResumoTextoNoticiasIsEqualToSomething() throws Exception {
        // Initialize the database
        noticiasRepository.saveAndFlush(noticias);

        // Get all the noticiasList where resumoTextoNoticias equals to DEFAULT_RESUMO_TEXTO_NOTICIAS
        defaultNoticiasShouldBeFound("resumoTextoNoticias.equals=" + DEFAULT_RESUMO_TEXTO_NOTICIAS);

        // Get all the noticiasList where resumoTextoNoticias equals to UPDATED_RESUMO_TEXTO_NOTICIAS
        defaultNoticiasShouldNotBeFound("resumoTextoNoticias.equals=" + UPDATED_RESUMO_TEXTO_NOTICIAS);
    }

    @Test
    @Transactional
    public void getAllNoticiasByResumoTextoNoticiasIsInShouldWork() throws Exception {
        // Initialize the database
        noticiasRepository.saveAndFlush(noticias);

        // Get all the noticiasList where resumoTextoNoticias in DEFAULT_RESUMO_TEXTO_NOTICIAS or UPDATED_RESUMO_TEXTO_NOTICIAS
        defaultNoticiasShouldBeFound("resumoTextoNoticias.in=" + DEFAULT_RESUMO_TEXTO_NOTICIAS + "," + UPDATED_RESUMO_TEXTO_NOTICIAS);

        // Get all the noticiasList where resumoTextoNoticias equals to UPDATED_RESUMO_TEXTO_NOTICIAS
        defaultNoticiasShouldNotBeFound("resumoTextoNoticias.in=" + UPDATED_RESUMO_TEXTO_NOTICIAS);
    }

    @Test
    @Transactional
    public void getAllNoticiasByResumoTextoNoticiasIsNullOrNotNull() throws Exception {
        // Initialize the database
        noticiasRepository.saveAndFlush(noticias);

        // Get all the noticiasList where resumoTextoNoticias is not null
        defaultNoticiasShouldBeFound("resumoTextoNoticias.specified=true");

        // Get all the noticiasList where resumoTextoNoticias is null
        defaultNoticiasShouldNotBeFound("resumoTextoNoticias.specified=false");
    }

    @Test
    @Transactional
    public void getAllNoticiasByIdSituacaoIsEqualToSomething() throws Exception {
        // Initialize the database
        Situacao idSituacao = SituacaoResourceIntTest.createEntity(em);
        em.persist(idSituacao);
        em.flush();
        noticias.setIdSituacao(idSituacao);
        noticiasRepository.saveAndFlush(noticias);
        Long idSituacaoId = idSituacao.getId();

        // Get all the noticiasList where idSituacao equals to situacao
        defaultNoticiasShouldBeFound("situacao.equals=" + idSituacaoId);

        // Get all the noticiasList where idSituacao equals to situacao + 1
        defaultNoticiasShouldNotBeFound("situacao.equals=" + (idSituacaoId + 1));
    }


    @Test
    @Transactional
    public void getAllNoticiasByInicioIsEqualToSomething() throws Exception {
        // Initialize the database
        Inicio inicio = InicioResourceIntTest.createEntity(em);
        em.persist(inicio);
        em.flush();
        noticias.addInicio(inicio);
        noticiasRepository.saveAndFlush(noticias);
        Long inicioId = inicio.getId();

        // Get all the noticiasList where inicio equals to inicioId
        defaultNoticiasShouldBeFound("inicioId.equals=" + inicioId);

        // Get all the noticiasList where inicio equals to inicioId + 1
        defaultNoticiasShouldNotBeFound("inicioId.equals=" + (inicioId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultNoticiasShouldBeFound(String filter) throws Exception {
        restNoticiasMockMvc.perform(get("/api/noticias?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(noticias.getId().intValue())))
            .andExpect(jsonPath("$.[*].idNoticias").value(hasItem(DEFAULT_ID_NOTICIAS.intValue())))
            .andExpect(jsonPath("$.[*].tituloNoticias").value(hasItem(DEFAULT_TITULO_NOTICIAS.toString())))
            .andExpect(jsonPath("$.[*].textoNoticias").value(hasItem(DEFAULT_TEXTO_NOTICIAS.toString())))
            .andExpect(jsonPath("$.[*].resumoTextoNoticias").value(hasItem(DEFAULT_RESUMO_TEXTO_NOTICIAS.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultNoticiasShouldNotBeFound(String filter) throws Exception {
        restNoticiasMockMvc.perform(get("/api/noticias?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingNoticias() throws Exception {
        // Get the noticias
        restNoticiasMockMvc.perform(get("/api/noticias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNoticias() throws Exception {
        // Initialize the database
        noticiasRepository.saveAndFlush(noticias);
        int databaseSizeBeforeUpdate = noticiasRepository.findAll().size();

        // Update the noticias
        Noticias updatedNoticias = noticiasRepository.findOne(noticias.getId());
        // Disconnect from session so that the updates on updatedNoticias are not directly saved in db
        em.detach(updatedNoticias);
        updatedNoticias
            .tituloNoticias(UPDATED_TITULO_NOTICIAS)
            .textoNoticias(UPDATED_TEXTO_NOTICIAS)
            .resumoTextoNoticias(UPDATED_RESUMO_TEXTO_NOTICIAS);
        NoticiasDTO noticiasDTO = noticiasMapper.toDto(updatedNoticias);

        restNoticiasMockMvc.perform(put("/api/noticias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noticiasDTO)))
            .andExpect(status().isOk());

        // Validate the Noticias in the database
        List<Noticias> noticiasList = noticiasRepository.findAll();
        assertThat(noticiasList).hasSize(databaseSizeBeforeUpdate);
        Noticias testNoticias = noticiasList.get(noticiasList.size() - 1);
        assertThat(testNoticias.getTituloNoticias()).isEqualTo(UPDATED_TITULO_NOTICIAS);
        assertThat(testNoticias.getTextoNoticias()).isEqualTo(UPDATED_TEXTO_NOTICIAS);
        assertThat(testNoticias.getResumoTextoNoticias()).isEqualTo(UPDATED_RESUMO_TEXTO_NOTICIAS);
    }

    @Test
    @Transactional
    public void updateNonExistingNoticias() throws Exception {
        int databaseSizeBeforeUpdate = noticiasRepository.findAll().size();

        // Create the Noticias
        NoticiasDTO noticiasDTO = noticiasMapper.toDto(noticias);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restNoticiasMockMvc.perform(put("/api/noticias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noticiasDTO)))
            .andExpect(status().isCreated());

        // Validate the Noticias in the database
        List<Noticias> noticiasList = noticiasRepository.findAll();
        assertThat(noticiasList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteNoticias() throws Exception {
        // Initialize the database
        noticiasRepository.saveAndFlush(noticias);
        int databaseSizeBeforeDelete = noticiasRepository.findAll().size();

        // Get the noticias
        restNoticiasMockMvc.perform(delete("/api/noticias/{id}", noticias.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Noticias> noticiasList = noticiasRepository.findAll();
        assertThat(noticiasList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Noticias.class);
        Noticias noticias1 = new Noticias();
        noticias1.setId(1L);
        Noticias noticias2 = new Noticias();
        noticias2.setId(noticias1.getId());
        assertThat(noticias1).isEqualTo(noticias2);
        noticias2.setId(2L);
        assertThat(noticias1).isNotEqualTo(noticias2);
        noticias1.setId(null);
        assertThat(noticias1).isNotEqualTo(noticias2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NoticiasDTO.class);
        NoticiasDTO noticiasDTO1 = new NoticiasDTO();
        noticiasDTO1.setId(1L);
        NoticiasDTO noticiasDTO2 = new NoticiasDTO();
        assertThat(noticiasDTO1).isNotEqualTo(noticiasDTO2);
        noticiasDTO2.setId(noticiasDTO1.getId());
        assertThat(noticiasDTO1).isEqualTo(noticiasDTO2);
        noticiasDTO2.setId(2L);
        assertThat(noticiasDTO1).isNotEqualTo(noticiasDTO2);
        noticiasDTO1.setId(null);
        assertThat(noticiasDTO1).isNotEqualTo(noticiasDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(noticiasMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(noticiasMapper.fromId(null)).isNull();
    }
}
