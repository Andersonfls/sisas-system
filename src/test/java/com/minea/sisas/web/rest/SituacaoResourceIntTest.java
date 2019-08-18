package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;

import com.minea.sisas.domain.Situacao;
import com.minea.sisas.domain.Contactos;
import com.minea.sisas.domain.Execucao;
import com.minea.sisas.domain.IndicadorProducao;
import com.minea.sisas.domain.Inicio;
import com.minea.sisas.domain.Noticias;
import com.minea.sisas.domain.Projectos;
import com.minea.sisas.domain.Publicacao;
import com.minea.sisas.domain.SistemaAgua;
import com.minea.sisas.domain.SobreDna;
import com.minea.sisas.repository.SituacaoRepository;
import com.minea.sisas.service.SituacaoService;
import com.minea.sisas.service.dto.SituacaoDTO;
import com.minea.sisas.service.mapper.SituacaoMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;
import com.minea.sisas.service.SituacaoQueryService;

import com.minea.sisas.web.rest.SituacaoResource;
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
 * Test class for the SituacaoResource REST controller.
 *
 * @see SituacaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class SituacaoResourceIntTest {

    private static final Long DEFAULT_ID_SITUACAO = 1L;
    private static final Long UPDATED_ID_SITUACAO = 2L;

    private static final String DEFAULT_NM_SITUACAO = "AAAAAAAAAA";
    private static final String UPDATED_NM_SITUACAO = "BBBBBBBBBB";

    @Autowired
    private SituacaoRepository situacaoRepository;

    @Autowired
    private SituacaoMapper situacaoMapper;

    @Autowired
    private SituacaoService situacaoService;

    @Autowired
    private SituacaoQueryService situacaoQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSituacaoMockMvc;

    private Situacao situacao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SituacaoResource situacaoResource = new SituacaoResource(situacaoService, situacaoQueryService,situacaoRepository);
        this.restSituacaoMockMvc = MockMvcBuilders.standaloneSetup(situacaoResource)
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
    public static Situacao createEntity(EntityManager em) {
        Situacao situacao = new Situacao()
            .nmSituacao(DEFAULT_NM_SITUACAO);
        return situacao;
    }

    @Before
    public void initTest() {
        situacao = createEntity(em);
    }

    @Test
    @Transactional
    public void createSituacao() throws Exception {
        int databaseSizeBeforeCreate = situacaoRepository.findAll().size();

        // Create the Situacao
        SituacaoDTO situacaoDTO = situacaoMapper.toDto(situacao);
        restSituacaoMockMvc.perform(post("/api/situacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(situacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Situacao in the database
        List<Situacao> situacaoList = situacaoRepository.findAll();
        assertThat(situacaoList).hasSize(databaseSizeBeforeCreate + 1);
        Situacao testSituacao = situacaoList.get(situacaoList.size() - 1);
        assertThat(testSituacao.getNmSituacao()).isEqualTo(DEFAULT_NM_SITUACAO);
    }

    @Test
    @Transactional
    public void createSituacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = situacaoRepository.findAll().size();

        // Create the Situacao with an existing ID
        situacao.setId(1L);
        SituacaoDTO situacaoDTO = situacaoMapper.toDto(situacao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSituacaoMockMvc.perform(post("/api/situacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(situacaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Situacao in the database
        List<Situacao> situacaoList = situacaoRepository.findAll();
        assertThat(situacaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdSituacaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = situacaoRepository.findAll().size();
        // set the field null

        // Create the Situacao, which fails.
        SituacaoDTO situacaoDTO = situacaoMapper.toDto(situacao);

        restSituacaoMockMvc.perform(post("/api/situacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(situacaoDTO)))
            .andExpect(status().isBadRequest());

        List<Situacao> situacaoList = situacaoRepository.findAll();
        assertThat(situacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNmSituacaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = situacaoRepository.findAll().size();
        // set the field null
        situacao.setNmSituacao(null);

        // Create the Situacao, which fails.
        SituacaoDTO situacaoDTO = situacaoMapper.toDto(situacao);

        restSituacaoMockMvc.perform(post("/api/situacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(situacaoDTO)))
            .andExpect(status().isBadRequest());

        List<Situacao> situacaoList = situacaoRepository.findAll();
        assertThat(situacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSituacaos() throws Exception {
        // Initialize the database
        situacaoRepository.saveAndFlush(situacao);

        // Get all the situacaoList
        restSituacaoMockMvc.perform(get("/api/situacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(situacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSituacao").value(hasItem(DEFAULT_ID_SITUACAO.intValue())))
            .andExpect(jsonPath("$.[*].nmSituacao").value(hasItem(DEFAULT_NM_SITUACAO.toString())));
    }

    @Test
    @Transactional
    public void getSituacao() throws Exception {
        // Initialize the database
        situacaoRepository.saveAndFlush(situacao);

        // Get the situacao
        restSituacaoMockMvc.perform(get("/api/situacaos/{id}", situacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(situacao.getId().intValue()))
            .andExpect(jsonPath("$.idSituacao").value(DEFAULT_ID_SITUACAO.intValue()))
            .andExpect(jsonPath("$.nmSituacao").value(DEFAULT_NM_SITUACAO.toString()));
    }

    @Test
    @Transactional
    public void getAllSituacaosByIdSituacaoIsEqualToSomething() throws Exception {
        // Initialize the database
        situacaoRepository.saveAndFlush(situacao);

        // Get all the situacaoList where idSituacao equals to DEFAULT_ID_SITUACAO
        defaultSituacaoShouldBeFound("idSituacao.equals=" + DEFAULT_ID_SITUACAO);

        // Get all the situacaoList where idSituacao equals to UPDATED_ID_SITUACAO
        defaultSituacaoShouldNotBeFound("idSituacao.equals=" + UPDATED_ID_SITUACAO);
    }

    @Test
    @Transactional
    public void getAllSituacaosByIdSituacaoIsInShouldWork() throws Exception {
        // Initialize the database
        situacaoRepository.saveAndFlush(situacao);

        // Get all the situacaoList where idSituacao in DEFAULT_ID_SITUACAO or UPDATED_ID_SITUACAO
        defaultSituacaoShouldBeFound("idSituacao.in=" + DEFAULT_ID_SITUACAO + "," + UPDATED_ID_SITUACAO);

        // Get all the situacaoList where idSituacao equals to UPDATED_ID_SITUACAO
        defaultSituacaoShouldNotBeFound("idSituacao.in=" + UPDATED_ID_SITUACAO);
    }

    @Test
    @Transactional
    public void getAllSituacaosByIdSituacaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        situacaoRepository.saveAndFlush(situacao);

        // Get all the situacaoList where idSituacao is not null
        defaultSituacaoShouldBeFound("idSituacao.specified=true");

        // Get all the situacaoList where idSituacao is null
        defaultSituacaoShouldNotBeFound("idSituacao.specified=false");
    }

    @Test
    @Transactional
    public void getAllSituacaosByIdSituacaoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        situacaoRepository.saveAndFlush(situacao);

        // Get all the situacaoList where idSituacao greater than or equals to DEFAULT_ID_SITUACAO
        defaultSituacaoShouldBeFound("idSituacao.greaterOrEqualThan=" + DEFAULT_ID_SITUACAO);

        // Get all the situacaoList where idSituacao greater than or equals to UPDATED_ID_SITUACAO
        defaultSituacaoShouldNotBeFound("idSituacao.greaterOrEqualThan=" + UPDATED_ID_SITUACAO);
    }

    @Test
    @Transactional
    public void getAllSituacaosByIdSituacaoIsLessThanSomething() throws Exception {
        // Initialize the database
        situacaoRepository.saveAndFlush(situacao);

        // Get all the situacaoList where idSituacao less than or equals to DEFAULT_ID_SITUACAO
        defaultSituacaoShouldNotBeFound("idSituacao.lessThan=" + DEFAULT_ID_SITUACAO);

        // Get all the situacaoList where idSituacao less than or equals to UPDATED_ID_SITUACAO
        defaultSituacaoShouldBeFound("idSituacao.lessThan=" + UPDATED_ID_SITUACAO);
    }


    @Test
    @Transactional
    public void getAllSituacaosByNmSituacaoIsEqualToSomething() throws Exception {
        // Initialize the database
        situacaoRepository.saveAndFlush(situacao);

        // Get all the situacaoList where nmSituacao equals to DEFAULT_NM_SITUACAO
        defaultSituacaoShouldBeFound("nmSituacao.equals=" + DEFAULT_NM_SITUACAO);

        // Get all the situacaoList where nmSituacao equals to UPDATED_NM_SITUACAO
        defaultSituacaoShouldNotBeFound("nmSituacao.equals=" + UPDATED_NM_SITUACAO);
    }

    @Test
    @Transactional
    public void getAllSituacaosByNmSituacaoIsInShouldWork() throws Exception {
        // Initialize the database
        situacaoRepository.saveAndFlush(situacao);

        // Get all the situacaoList where nmSituacao in DEFAULT_NM_SITUACAO or UPDATED_NM_SITUACAO
        defaultSituacaoShouldBeFound("nmSituacao.in=" + DEFAULT_NM_SITUACAO + "," + UPDATED_NM_SITUACAO);

        // Get all the situacaoList where nmSituacao equals to UPDATED_NM_SITUACAO
        defaultSituacaoShouldNotBeFound("nmSituacao.in=" + UPDATED_NM_SITUACAO);
    }

    @Test
    @Transactional
    public void getAllSituacaosByNmSituacaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        situacaoRepository.saveAndFlush(situacao);

        // Get all the situacaoList where nmSituacao is not null
        defaultSituacaoShouldBeFound("nmSituacao.specified=true");

        // Get all the situacaoList where nmSituacao is null
        defaultSituacaoShouldNotBeFound("nmSituacao.specified=false");
    }

    @Test
    @Transactional
    public void getAllSituacaosByContactosIsEqualToSomething() throws Exception {
        // Initialize the database
        Contactos contactos = ContactosResourceIntTest.createEntity(em);
        em.persist(contactos);
        em.flush();
        situacaoRepository.saveAndFlush(situacao);
        Long contactosId = contactos.getId();

        // Get all the situacaoList where contactos equals to contactosId
        defaultSituacaoShouldBeFound("contactosId.equals=" + contactosId);

        // Get all the situacaoList where contactos equals to contactosId + 1
        defaultSituacaoShouldNotBeFound("contactosId.equals=" + (contactosId + 1));
    }


    @Test
    @Transactional
    public void getAllSituacaosByExecucaoIsEqualToSomething() throws Exception {
        // Initialize the database
        Execucao execucao = ExecucaoResourceIntTest.createEntity(em);
        em.persist(execucao);
        em.flush();
        situacaoRepository.saveAndFlush(situacao);
        Long execucaoId = execucao.getId();

        // Get all the situacaoList where execucao equals to execucaoId
        defaultSituacaoShouldBeFound("execucaoId.equals=" + execucaoId);

        // Get all the situacaoList where execucao equals to execucaoId + 1
        defaultSituacaoShouldNotBeFound("execucaoId.equals=" + (execucaoId + 1));
    }


    @Test
    @Transactional
    public void getAllSituacaosByIndicadorProducaoIsEqualToSomething() throws Exception {
        // Initialize the database
        IndicadorProducao indicadorProducao = IndicadorProducaoResourceIntTest.createEntity(em);
        em.persist(indicadorProducao);
        em.flush();
        situacaoRepository.saveAndFlush(situacao);
        Long indicadorProducaoId = indicadorProducao.getId();

        // Get all the situacaoList where indicadorProducao equals to indicadorProducaoId
        defaultSituacaoShouldBeFound("indicadorProducaoId.equals=" + indicadorProducaoId);

        // Get all the situacaoList where indicadorProducao equals to indicadorProducaoId + 1
        defaultSituacaoShouldNotBeFound("indicadorProducaoId.equals=" + (indicadorProducaoId + 1));
    }


    @Test
    @Transactional
    public void getAllSituacaosByInicioIsEqualToSomething() throws Exception {
        // Initialize the database
        Inicio inicio = InicioResourceIntTest.createEntity(em);
        em.persist(inicio);
        em.flush();
        situacaoRepository.saveAndFlush(situacao);
        Long inicioId = inicio.getId();

        // Get all the situacaoList where inicio equals to inicioId
        defaultSituacaoShouldBeFound("inicioId.equals=" + inicioId);

        // Get all the situacaoList where inicio equals to inicioId + 1
        defaultSituacaoShouldNotBeFound("inicioId.equals=" + (inicioId + 1));
    }


    @Test
    @Transactional
    public void getAllSituacaosByNoticiasIsEqualToSomething() throws Exception {
        // Initialize the database
        Noticias noticias = NoticiasResourceIntTest.createEntity(em);
        em.persist(noticias);
        em.flush();
        situacaoRepository.saveAndFlush(situacao);
        Long noticiasId = noticias.getId();

        // Get all the situacaoList where noticias equals to noticiasId
        defaultSituacaoShouldBeFound("noticiasId.equals=" + noticiasId);

        // Get all the situacaoList where noticias equals to noticiasId + 1
        defaultSituacaoShouldNotBeFound("noticiasId.equals=" + (noticiasId + 1));
    }


    @Test
    @Transactional
    public void getAllSituacaosByProjectosIsEqualToSomething() throws Exception {
        // Initialize the database
        Projectos projectos = ProjectosResourceIntTest.createEntity(em);
        em.persist(projectos);
        em.flush();
        situacaoRepository.saveAndFlush(situacao);
        Long projectosId = projectos.getId();

        // Get all the situacaoList where projectos equals to projectosId
        defaultSituacaoShouldBeFound("projectosId.equals=" + projectosId);

        // Get all the situacaoList where projectos equals to projectosId + 1
        defaultSituacaoShouldNotBeFound("projectosId.equals=" + (projectosId + 1));
    }


    @Test
    @Transactional
    public void getAllSituacaosByPublicacaoIsEqualToSomething() throws Exception {
        // Initialize the database
        Publicacao publicacao = PublicacaoResourceIntTest.createEntity(em);
        em.persist(publicacao);
        em.flush();
        situacaoRepository.saveAndFlush(situacao);
        Long publicacaoId = publicacao.getId();

        // Get all the situacaoList where publicacao equals to publicacaoId
        defaultSituacaoShouldBeFound("publicacaoId.equals=" + publicacaoId);

        // Get all the situacaoList where publicacao equals to publicacaoId + 1
        defaultSituacaoShouldNotBeFound("publicacaoId.equals=" + (publicacaoId + 1));
    }


    @Test
    @Transactional
    public void getAllSituacaosBySistemaAguaIsEqualToSomething() throws Exception {
        // Initialize the database
        SistemaAgua sistemaAgua = SistemaAguaResourceIntTest.createEntity(em);
        em.persist(sistemaAgua);
        em.flush();
        situacaoRepository.saveAndFlush(situacao);
        Long sistemaAguaId = sistemaAgua.getId();

        // Get all the situacaoList where sistemaAgua equals to sistemaAguaId
        defaultSituacaoShouldBeFound("sistemaAguaId.equals=" + sistemaAguaId);

        // Get all the situacaoList where sistemaAgua equals to sistemaAguaId + 1
        defaultSituacaoShouldNotBeFound("sistemaAguaId.equals=" + (sistemaAguaId + 1));
    }


    @Test
    @Transactional
    public void getAllSituacaosBySobreDnaIsEqualToSomething() throws Exception {
        // Initialize the database
        SobreDna sobreDna = SobreDnaResourceIntTest.createEntity(em);
        em.persist(sobreDna);
        em.flush();
        situacaoRepository.saveAndFlush(situacao);
        Long sobreDnaId = sobreDna.getId();

        // Get all the situacaoList where sobreDna equals to sobreDnaId
        defaultSituacaoShouldBeFound("sobreDnaId.equals=" + sobreDnaId);

        // Get all the situacaoList where sobreDna equals to sobreDnaId + 1
        defaultSituacaoShouldNotBeFound("sobreDnaId.equals=" + (sobreDnaId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultSituacaoShouldBeFound(String filter) throws Exception {
        restSituacaoMockMvc.perform(get("/api/situacaos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(situacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSituacao").value(hasItem(DEFAULT_ID_SITUACAO.intValue())))
            .andExpect(jsonPath("$.[*].nmSituacao").value(hasItem(DEFAULT_NM_SITUACAO.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultSituacaoShouldNotBeFound(String filter) throws Exception {
        restSituacaoMockMvc.perform(get("/api/situacaos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingSituacao() throws Exception {
        // Get the situacao
        restSituacaoMockMvc.perform(get("/api/situacaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSituacao() throws Exception {
        // Initialize the database
        situacaoRepository.saveAndFlush(situacao);
        int databaseSizeBeforeUpdate = situacaoRepository.findAll().size();

        // Update the situacao
        Situacao updatedSituacao = situacaoRepository.findOne(situacao.getId());
        // Disconnect from session so that the updates on updatedSituacao are not directly saved in db
        em.detach(updatedSituacao);
        updatedSituacao
            .nmSituacao(UPDATED_NM_SITUACAO);
        SituacaoDTO situacaoDTO = situacaoMapper.toDto(updatedSituacao);

        restSituacaoMockMvc.perform(put("/api/situacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(situacaoDTO)))
            .andExpect(status().isOk());

        // Validate the Situacao in the database
        List<Situacao> situacaoList = situacaoRepository.findAll();
        assertThat(situacaoList).hasSize(databaseSizeBeforeUpdate);
        Situacao testSituacao = situacaoList.get(situacaoList.size() - 1);
        assertThat(testSituacao.getNmSituacao()).isEqualTo(UPDATED_NM_SITUACAO);
    }

    @Test
    @Transactional
    public void updateNonExistingSituacao() throws Exception {
        int databaseSizeBeforeUpdate = situacaoRepository.findAll().size();

        // Create the Situacao
        SituacaoDTO situacaoDTO = situacaoMapper.toDto(situacao);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSituacaoMockMvc.perform(put("/api/situacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(situacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Situacao in the database
        List<Situacao> situacaoList = situacaoRepository.findAll();
        assertThat(situacaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSituacao() throws Exception {
        // Initialize the database
        situacaoRepository.saveAndFlush(situacao);
        int databaseSizeBeforeDelete = situacaoRepository.findAll().size();

        // Get the situacao
        restSituacaoMockMvc.perform(delete("/api/situacaos/{id}", situacao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Situacao> situacaoList = situacaoRepository.findAll();
        assertThat(situacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Situacao.class);
        Situacao situacao1 = new Situacao();
        situacao1.setId(1L);
        Situacao situacao2 = new Situacao();
        situacao2.setId(situacao1.getId());
        assertThat(situacao1).isEqualTo(situacao2);
        situacao2.setId(2L);
        assertThat(situacao1).isNotEqualTo(situacao2);
        situacao1.setId(null);
        assertThat(situacao1).isNotEqualTo(situacao2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SituacaoDTO.class);
        SituacaoDTO situacaoDTO1 = new SituacaoDTO();
        situacaoDTO1.setId(1L);
        SituacaoDTO situacaoDTO2 = new SituacaoDTO();
        assertThat(situacaoDTO1).isNotEqualTo(situacaoDTO2);
        situacaoDTO2.setId(situacaoDTO1.getId());
        assertThat(situacaoDTO1).isEqualTo(situacaoDTO2);
        situacaoDTO2.setId(2L);
        assertThat(situacaoDTO1).isNotEqualTo(situacaoDTO2);
        situacaoDTO1.setId(null);
        assertThat(situacaoDTO1).isNotEqualTo(situacaoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(situacaoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(situacaoMapper.fromId(null)).isNull();
    }
}
