package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;

import com.minea.sisas.domain.Contactos;
import com.minea.sisas.domain.Situacao;
import com.minea.sisas.domain.Inicio;
import com.minea.sisas.repository.ContactosRepository;
import com.minea.sisas.service.ContactosService;
import com.minea.sisas.service.dto.ContactosDTO;
import com.minea.sisas.service.mapper.ContactosMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;
import com.minea.sisas.service.ContactosQueryService;

import com.minea.sisas.web.rest.ContactosResource;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.minea.sisas.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ContactosResource REST controller.
 *
 * @see ContactosResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class ContactosResourceIntTest {

    private static final Long DEFAULT_ID_CONTACTOS = 1L;
    private static final Long UPDATED_ID_CONTACTOS = 2L;

    private static final String DEFAULT_NM_CONTACTOS = "AAAAAAAAAA";
    private static final String UPDATED_NM_CONTACTOS = "BBBBBBBBBB";

    private static final String DEFAULT_TEXTO_CONTACTOS = "AAAAAAAAAA";
    private static final String UPDATED_TEXTO_CONTACTOS = "BBBBBBBBBB";

    private static final String DEFAULT_RESUMO_TEXTO_CONTACTOS = "AAAAAAAAAA";
    private static final String UPDATED_RESUMO_TEXTO_CONTACTOS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DT_LANCAMENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_LANCAMENTO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DT_ULTIMA_ALTERACAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_ULTIMA_ALTERACAO = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private ContactosRepository contactosRepository;

    @Autowired
    private ContactosMapper contactosMapper;

    @Autowired
    private ContactosService contactosService;

    @Autowired
    private ContactosQueryService contactosQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restContactosMockMvc;

    private Contactos contactos;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ContactosResource contactosResource = new ContactosResource(contactosService, contactosQueryService);
        this.restContactosMockMvc = MockMvcBuilders.standaloneSetup(contactosResource)
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
    public static Contactos createEntity(EntityManager em) {
        Contactos contactos = new Contactos()
            .idContactos(DEFAULT_ID_CONTACTOS)
            .nmContactos(DEFAULT_NM_CONTACTOS)
            .textoContactos(DEFAULT_TEXTO_CONTACTOS)
            .resumoTextoContactos(DEFAULT_RESUMO_TEXTO_CONTACTOS)
            .dtLancamento(DEFAULT_DT_LANCAMENTO)
            .dtUltimaAlteracao(DEFAULT_DT_ULTIMA_ALTERACAO);
        // Add required entity
        Situacao idSituacao = SituacaoResourceIntTest.createEntity(em);
        em.persist(idSituacao);
        em.flush();
        contactos.setIdSituacao(idSituacao);
        return contactos;
    }

    @Before
    public void initTest() {
        contactos = createEntity(em);
    }

    @Test
    @Transactional
    public void createContactos() throws Exception {
        int databaseSizeBeforeCreate = contactosRepository.findAll().size();

        // Create the Contactos
        ContactosDTO contactosDTO = contactosMapper.toDto(contactos);
        restContactosMockMvc.perform(post("/api/contactos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactosDTO)))
            .andExpect(status().isCreated());

        // Validate the Contactos in the database
        List<Contactos> contactosList = contactosRepository.findAll();
        assertThat(contactosList).hasSize(databaseSizeBeforeCreate + 1);
        Contactos testContactos = contactosList.get(contactosList.size() - 1);
        assertThat(testContactos.getIdContactos()).isEqualTo(DEFAULT_ID_CONTACTOS);
        assertThat(testContactos.getNmContactos()).isEqualTo(DEFAULT_NM_CONTACTOS);
        assertThat(testContactos.getTextoContactos()).isEqualTo(DEFAULT_TEXTO_CONTACTOS);
        assertThat(testContactos.getResumoTextoContactos()).isEqualTo(DEFAULT_RESUMO_TEXTO_CONTACTOS);
        assertThat(testContactos.getDtLancamento()).isEqualTo(DEFAULT_DT_LANCAMENTO);
        assertThat(testContactos.getDtUltimaAlteracao()).isEqualTo(DEFAULT_DT_ULTIMA_ALTERACAO);
    }

    @Test
    @Transactional
    public void createContactosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contactosRepository.findAll().size();

        // Create the Contactos with an existing ID
        contactos.setId(1L);
        ContactosDTO contactosDTO = contactosMapper.toDto(contactos);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContactosMockMvc.perform(post("/api/contactos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactosDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Contactos in the database
        List<Contactos> contactosList = contactosRepository.findAll();
        assertThat(contactosList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdContactosIsRequired() throws Exception {
        int databaseSizeBeforeTest = contactosRepository.findAll().size();
        // set the field null
        contactos.setIdContactos(null);

        // Create the Contactos, which fails.
        ContactosDTO contactosDTO = contactosMapper.toDto(contactos);

        restContactosMockMvc.perform(post("/api/contactos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactosDTO)))
            .andExpect(status().isBadRequest());

        List<Contactos> contactosList = contactosRepository.findAll();
        assertThat(contactosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNmContactosIsRequired() throws Exception {
        int databaseSizeBeforeTest = contactosRepository.findAll().size();
        // set the field null
        contactos.setNmContactos(null);

        // Create the Contactos, which fails.
        ContactosDTO contactosDTO = contactosMapper.toDto(contactos);

        restContactosMockMvc.perform(post("/api/contactos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactosDTO)))
            .andExpect(status().isBadRequest());

        List<Contactos> contactosList = contactosRepository.findAll();
        assertThat(contactosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTextoContactosIsRequired() throws Exception {
        int databaseSizeBeforeTest = contactosRepository.findAll().size();
        // set the field null
        contactos.setTextoContactos(null);

        // Create the Contactos, which fails.
        ContactosDTO contactosDTO = contactosMapper.toDto(contactos);

        restContactosMockMvc.perform(post("/api/contactos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactosDTO)))
            .andExpect(status().isBadRequest());

        List<Contactos> contactosList = contactosRepository.findAll();
        assertThat(contactosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkResumoTextoContactosIsRequired() throws Exception {
        int databaseSizeBeforeTest = contactosRepository.findAll().size();
        // set the field null
        contactos.setResumoTextoContactos(null);

        // Create the Contactos, which fails.
        ContactosDTO contactosDTO = contactosMapper.toDto(contactos);

        restContactosMockMvc.perform(post("/api/contactos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactosDTO)))
            .andExpect(status().isBadRequest());

        List<Contactos> contactosList = contactosRepository.findAll();
        assertThat(contactosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDtLancamentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = contactosRepository.findAll().size();
        // set the field null
        contactos.setDtLancamento(null);

        // Create the Contactos, which fails.
        ContactosDTO contactosDTO = contactosMapper.toDto(contactos);

        restContactosMockMvc.perform(post("/api/contactos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactosDTO)))
            .andExpect(status().isBadRequest());

        List<Contactos> contactosList = contactosRepository.findAll();
        assertThat(contactosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllContactos() throws Exception {
        // Initialize the database
        contactosRepository.saveAndFlush(contactos);

        // Get all the contactosList
        restContactosMockMvc.perform(get("/api/contactos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contactos.getId().intValue())))
            .andExpect(jsonPath("$.[*].idContactos").value(hasItem(DEFAULT_ID_CONTACTOS.intValue())))
            .andExpect(jsonPath("$.[*].nmContactos").value(hasItem(DEFAULT_NM_CONTACTOS.toString())))
            .andExpect(jsonPath("$.[*].textoContactos").value(hasItem(DEFAULT_TEXTO_CONTACTOS.toString())))
            .andExpect(jsonPath("$.[*].resumoTextoContactos").value(hasItem(DEFAULT_RESUMO_TEXTO_CONTACTOS.toString())))
            .andExpect(jsonPath("$.[*].dtLancamento").value(hasItem(DEFAULT_DT_LANCAMENTO.toString())))
            .andExpect(jsonPath("$.[*].dtUltimaAlteracao").value(hasItem(DEFAULT_DT_ULTIMA_ALTERACAO.toString())));
    }

    @Test
    @Transactional
    public void getContactos() throws Exception {
        // Initialize the database
        contactosRepository.saveAndFlush(contactos);

        // Get the contactos
        restContactosMockMvc.perform(get("/api/contactos/{id}", contactos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(contactos.getId().intValue()))
            .andExpect(jsonPath("$.idContactos").value(DEFAULT_ID_CONTACTOS.intValue()))
            .andExpect(jsonPath("$.nmContactos").value(DEFAULT_NM_CONTACTOS.toString()))
            .andExpect(jsonPath("$.textoContactos").value(DEFAULT_TEXTO_CONTACTOS.toString()))
            .andExpect(jsonPath("$.resumoTextoContactos").value(DEFAULT_RESUMO_TEXTO_CONTACTOS.toString()))
            .andExpect(jsonPath("$.dtLancamento").value(DEFAULT_DT_LANCAMENTO.toString()))
            .andExpect(jsonPath("$.dtUltimaAlteracao").value(DEFAULT_DT_ULTIMA_ALTERACAO.toString()));
    }

    @Test
    @Transactional
    public void getAllContactosByIdContactosIsEqualToSomething() throws Exception {
        // Initialize the database
        contactosRepository.saveAndFlush(contactos);

        // Get all the contactosList where idContactos equals to DEFAULT_ID_CONTACTOS
        defaultContactosShouldBeFound("idContactos.equals=" + DEFAULT_ID_CONTACTOS);

        // Get all the contactosList where idContactos equals to UPDATED_ID_CONTACTOS
        defaultContactosShouldNotBeFound("idContactos.equals=" + UPDATED_ID_CONTACTOS);
    }

    @Test
    @Transactional
    public void getAllContactosByIdContactosIsInShouldWork() throws Exception {
        // Initialize the database
        contactosRepository.saveAndFlush(contactos);

        // Get all the contactosList where idContactos in DEFAULT_ID_CONTACTOS or UPDATED_ID_CONTACTOS
        defaultContactosShouldBeFound("idContactos.in=" + DEFAULT_ID_CONTACTOS + "," + UPDATED_ID_CONTACTOS);

        // Get all the contactosList where idContactos equals to UPDATED_ID_CONTACTOS
        defaultContactosShouldNotBeFound("idContactos.in=" + UPDATED_ID_CONTACTOS);
    }

    @Test
    @Transactional
    public void getAllContactosByIdContactosIsNullOrNotNull() throws Exception {
        // Initialize the database
        contactosRepository.saveAndFlush(contactos);

        // Get all the contactosList where idContactos is not null
        defaultContactosShouldBeFound("idContactos.specified=true");

        // Get all the contactosList where idContactos is null
        defaultContactosShouldNotBeFound("idContactos.specified=false");
    }

    @Test
    @Transactional
    public void getAllContactosByIdContactosIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        contactosRepository.saveAndFlush(contactos);

        // Get all the contactosList where idContactos greater than or equals to DEFAULT_ID_CONTACTOS
        defaultContactosShouldBeFound("idContactos.greaterOrEqualThan=" + DEFAULT_ID_CONTACTOS);

        // Get all the contactosList where idContactos greater than or equals to UPDATED_ID_CONTACTOS
        defaultContactosShouldNotBeFound("idContactos.greaterOrEqualThan=" + UPDATED_ID_CONTACTOS);
    }

    @Test
    @Transactional
    public void getAllContactosByIdContactosIsLessThanSomething() throws Exception {
        // Initialize the database
        contactosRepository.saveAndFlush(contactos);

        // Get all the contactosList where idContactos less than or equals to DEFAULT_ID_CONTACTOS
        defaultContactosShouldNotBeFound("idContactos.lessThan=" + DEFAULT_ID_CONTACTOS);

        // Get all the contactosList where idContactos less than or equals to UPDATED_ID_CONTACTOS
        defaultContactosShouldBeFound("idContactos.lessThan=" + UPDATED_ID_CONTACTOS);
    }


    @Test
    @Transactional
    public void getAllContactosByNmContactosIsEqualToSomething() throws Exception {
        // Initialize the database
        contactosRepository.saveAndFlush(contactos);

        // Get all the contactosList where nmContactos equals to DEFAULT_NM_CONTACTOS
        defaultContactosShouldBeFound("nmContactos.equals=" + DEFAULT_NM_CONTACTOS);

        // Get all the contactosList where nmContactos equals to UPDATED_NM_CONTACTOS
        defaultContactosShouldNotBeFound("nmContactos.equals=" + UPDATED_NM_CONTACTOS);
    }

    @Test
    @Transactional
    public void getAllContactosByNmContactosIsInShouldWork() throws Exception {
        // Initialize the database
        contactosRepository.saveAndFlush(contactos);

        // Get all the contactosList where nmContactos in DEFAULT_NM_CONTACTOS or UPDATED_NM_CONTACTOS
        defaultContactosShouldBeFound("nmContactos.in=" + DEFAULT_NM_CONTACTOS + "," + UPDATED_NM_CONTACTOS);

        // Get all the contactosList where nmContactos equals to UPDATED_NM_CONTACTOS
        defaultContactosShouldNotBeFound("nmContactos.in=" + UPDATED_NM_CONTACTOS);
    }

    @Test
    @Transactional
    public void getAllContactosByNmContactosIsNullOrNotNull() throws Exception {
        // Initialize the database
        contactosRepository.saveAndFlush(contactos);

        // Get all the contactosList where nmContactos is not null
        defaultContactosShouldBeFound("nmContactos.specified=true");

        // Get all the contactosList where nmContactos is null
        defaultContactosShouldNotBeFound("nmContactos.specified=false");
    }

    @Test
    @Transactional
    public void getAllContactosByTextoContactosIsEqualToSomething() throws Exception {
        // Initialize the database
        contactosRepository.saveAndFlush(contactos);

        // Get all the contactosList where textoContactos equals to DEFAULT_TEXTO_CONTACTOS
        defaultContactosShouldBeFound("textoContactos.equals=" + DEFAULT_TEXTO_CONTACTOS);

        // Get all the contactosList where textoContactos equals to UPDATED_TEXTO_CONTACTOS
        defaultContactosShouldNotBeFound("textoContactos.equals=" + UPDATED_TEXTO_CONTACTOS);
    }

    @Test
    @Transactional
    public void getAllContactosByTextoContactosIsInShouldWork() throws Exception {
        // Initialize the database
        contactosRepository.saveAndFlush(contactos);

        // Get all the contactosList where textoContactos in DEFAULT_TEXTO_CONTACTOS or UPDATED_TEXTO_CONTACTOS
        defaultContactosShouldBeFound("textoContactos.in=" + DEFAULT_TEXTO_CONTACTOS + "," + UPDATED_TEXTO_CONTACTOS);

        // Get all the contactosList where textoContactos equals to UPDATED_TEXTO_CONTACTOS
        defaultContactosShouldNotBeFound("textoContactos.in=" + UPDATED_TEXTO_CONTACTOS);
    }

    @Test
    @Transactional
    public void getAllContactosByTextoContactosIsNullOrNotNull() throws Exception {
        // Initialize the database
        contactosRepository.saveAndFlush(contactos);

        // Get all the contactosList where textoContactos is not null
        defaultContactosShouldBeFound("textoContactos.specified=true");

        // Get all the contactosList where textoContactos is null
        defaultContactosShouldNotBeFound("textoContactos.specified=false");
    }

    @Test
    @Transactional
    public void getAllContactosByResumoTextoContactosIsEqualToSomething() throws Exception {
        // Initialize the database
        contactosRepository.saveAndFlush(contactos);

        // Get all the contactosList where resumoTextoContactos equals to DEFAULT_RESUMO_TEXTO_CONTACTOS
        defaultContactosShouldBeFound("resumoTextoContactos.equals=" + DEFAULT_RESUMO_TEXTO_CONTACTOS);

        // Get all the contactosList where resumoTextoContactos equals to UPDATED_RESUMO_TEXTO_CONTACTOS
        defaultContactosShouldNotBeFound("resumoTextoContactos.equals=" + UPDATED_RESUMO_TEXTO_CONTACTOS);
    }

    @Test
    @Transactional
    public void getAllContactosByResumoTextoContactosIsInShouldWork() throws Exception {
        // Initialize the database
        contactosRepository.saveAndFlush(contactos);

        // Get all the contactosList where resumoTextoContactos in DEFAULT_RESUMO_TEXTO_CONTACTOS or UPDATED_RESUMO_TEXTO_CONTACTOS
        defaultContactosShouldBeFound("resumoTextoContactos.in=" + DEFAULT_RESUMO_TEXTO_CONTACTOS + "," + UPDATED_RESUMO_TEXTO_CONTACTOS);

        // Get all the contactosList where resumoTextoContactos equals to UPDATED_RESUMO_TEXTO_CONTACTOS
        defaultContactosShouldNotBeFound("resumoTextoContactos.in=" + UPDATED_RESUMO_TEXTO_CONTACTOS);
    }

    @Test
    @Transactional
    public void getAllContactosByResumoTextoContactosIsNullOrNotNull() throws Exception {
        // Initialize the database
        contactosRepository.saveAndFlush(contactos);

        // Get all the contactosList where resumoTextoContactos is not null
        defaultContactosShouldBeFound("resumoTextoContactos.specified=true");

        // Get all the contactosList where resumoTextoContactos is null
        defaultContactosShouldNotBeFound("resumoTextoContactos.specified=false");
    }

    @Test
    @Transactional
    public void getAllContactosByDtLancamentoIsEqualToSomething() throws Exception {
        // Initialize the database
        contactosRepository.saveAndFlush(contactos);

        // Get all the contactosList where dtLancamento equals to DEFAULT_DT_LANCAMENTO
        defaultContactosShouldBeFound("dtLancamento.equals=" + DEFAULT_DT_LANCAMENTO);

        // Get all the contactosList where dtLancamento equals to UPDATED_DT_LANCAMENTO
        defaultContactosShouldNotBeFound("dtLancamento.equals=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllContactosByDtLancamentoIsInShouldWork() throws Exception {
        // Initialize the database
        contactosRepository.saveAndFlush(contactos);

        // Get all the contactosList where dtLancamento in DEFAULT_DT_LANCAMENTO or UPDATED_DT_LANCAMENTO
        defaultContactosShouldBeFound("dtLancamento.in=" + DEFAULT_DT_LANCAMENTO + "," + UPDATED_DT_LANCAMENTO);

        // Get all the contactosList where dtLancamento equals to UPDATED_DT_LANCAMENTO
        defaultContactosShouldNotBeFound("dtLancamento.in=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllContactosByDtLancamentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        contactosRepository.saveAndFlush(contactos);

        // Get all the contactosList where dtLancamento is not null
        defaultContactosShouldBeFound("dtLancamento.specified=true");

        // Get all the contactosList where dtLancamento is null
        defaultContactosShouldNotBeFound("dtLancamento.specified=false");
    }

    @Test
    @Transactional
    public void getAllContactosByDtLancamentoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        contactosRepository.saveAndFlush(contactos);

        // Get all the contactosList where dtLancamento greater than or equals to DEFAULT_DT_LANCAMENTO
        defaultContactosShouldBeFound("dtLancamento.greaterOrEqualThan=" + DEFAULT_DT_LANCAMENTO);

        // Get all the contactosList where dtLancamento greater than or equals to UPDATED_DT_LANCAMENTO
        defaultContactosShouldNotBeFound("dtLancamento.greaterOrEqualThan=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllContactosByDtLancamentoIsLessThanSomething() throws Exception {
        // Initialize the database
        contactosRepository.saveAndFlush(contactos);

        // Get all the contactosList where dtLancamento less than or equals to DEFAULT_DT_LANCAMENTO
        defaultContactosShouldNotBeFound("dtLancamento.lessThan=" + DEFAULT_DT_LANCAMENTO);

        // Get all the contactosList where dtLancamento less than or equals to UPDATED_DT_LANCAMENTO
        defaultContactosShouldBeFound("dtLancamento.lessThan=" + UPDATED_DT_LANCAMENTO);
    }


    @Test
    @Transactional
    public void getAllContactosByDtUltimaAlteracaoIsEqualToSomething() throws Exception {
        // Initialize the database
        contactosRepository.saveAndFlush(contactos);

        // Get all the contactosList where dtUltimaAlteracao equals to DEFAULT_DT_ULTIMA_ALTERACAO
        defaultContactosShouldBeFound("dtUltimaAlteracao.equals=" + DEFAULT_DT_ULTIMA_ALTERACAO);

        // Get all the contactosList where dtUltimaAlteracao equals to UPDATED_DT_ULTIMA_ALTERACAO
        defaultContactosShouldNotBeFound("dtUltimaAlteracao.equals=" + UPDATED_DT_ULTIMA_ALTERACAO);
    }

    @Test
    @Transactional
    public void getAllContactosByDtUltimaAlteracaoIsInShouldWork() throws Exception {
        // Initialize the database
        contactosRepository.saveAndFlush(contactos);

        // Get all the contactosList where dtUltimaAlteracao in DEFAULT_DT_ULTIMA_ALTERACAO or UPDATED_DT_ULTIMA_ALTERACAO
        defaultContactosShouldBeFound("dtUltimaAlteracao.in=" + DEFAULT_DT_ULTIMA_ALTERACAO + "," + UPDATED_DT_ULTIMA_ALTERACAO);

        // Get all the contactosList where dtUltimaAlteracao equals to UPDATED_DT_ULTIMA_ALTERACAO
        defaultContactosShouldNotBeFound("dtUltimaAlteracao.in=" + UPDATED_DT_ULTIMA_ALTERACAO);
    }

    @Test
    @Transactional
    public void getAllContactosByDtUltimaAlteracaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        contactosRepository.saveAndFlush(contactos);

        // Get all the contactosList where dtUltimaAlteracao is not null
        defaultContactosShouldBeFound("dtUltimaAlteracao.specified=true");

        // Get all the contactosList where dtUltimaAlteracao is null
        defaultContactosShouldNotBeFound("dtUltimaAlteracao.specified=false");
    }

    @Test
    @Transactional
    public void getAllContactosByDtUltimaAlteracaoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        contactosRepository.saveAndFlush(contactos);

        // Get all the contactosList where dtUltimaAlteracao greater than or equals to DEFAULT_DT_ULTIMA_ALTERACAO
        defaultContactosShouldBeFound("dtUltimaAlteracao.greaterOrEqualThan=" + DEFAULT_DT_ULTIMA_ALTERACAO);

        // Get all the contactosList where dtUltimaAlteracao greater than or equals to UPDATED_DT_ULTIMA_ALTERACAO
        defaultContactosShouldNotBeFound("dtUltimaAlteracao.greaterOrEqualThan=" + UPDATED_DT_ULTIMA_ALTERACAO);
    }

    @Test
    @Transactional
    public void getAllContactosByDtUltimaAlteracaoIsLessThanSomething() throws Exception {
        // Initialize the database
        contactosRepository.saveAndFlush(contactos);

        // Get all the contactosList where dtUltimaAlteracao less than or equals to DEFAULT_DT_ULTIMA_ALTERACAO
        defaultContactosShouldNotBeFound("dtUltimaAlteracao.lessThan=" + DEFAULT_DT_ULTIMA_ALTERACAO);

        // Get all the contactosList where dtUltimaAlteracao less than or equals to UPDATED_DT_ULTIMA_ALTERACAO
        defaultContactosShouldBeFound("dtUltimaAlteracao.lessThan=" + UPDATED_DT_ULTIMA_ALTERACAO);
    }


    @Test
    @Transactional
    public void getAllContactosByIdSituacaoIsEqualToSomething() throws Exception {
        // Initialize the database
        Situacao idSituacao = SituacaoResourceIntTest.createEntity(em);
        em.persist(idSituacao);
        em.flush();
        contactos.setIdSituacao(idSituacao);
        contactosRepository.saveAndFlush(contactos);
        Long idSituacaoId = idSituacao.getId();

        // Get all the contactosList where idSituacao equals to situacao
        defaultContactosShouldBeFound("situacao.equals=" + idSituacaoId);

        // Get all the contactosList where idSituacao equals to situacao + 1
        defaultContactosShouldNotBeFound("situacao.equals=" + (idSituacaoId + 1));
    }


    @Test
    @Transactional
    public void getAllContactosByInicioIsEqualToSomething() throws Exception {
        // Initialize the database
        Inicio inicio = InicioResourceIntTest.createEntity(em);
        em.persist(inicio);
        em.flush();
        contactos.addInicio(inicio);
        contactosRepository.saveAndFlush(contactos);
        Long inicioId = inicio.getId();

        // Get all the contactosList where inicio equals to inicioId
        defaultContactosShouldBeFound("inicioId.equals=" + inicioId);

        // Get all the contactosList where inicio equals to inicioId + 1
        defaultContactosShouldNotBeFound("inicioId.equals=" + (inicioId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultContactosShouldBeFound(String filter) throws Exception {
        restContactosMockMvc.perform(get("/api/contactos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contactos.getId().intValue())))
            .andExpect(jsonPath("$.[*].idContactos").value(hasItem(DEFAULT_ID_CONTACTOS.intValue())))
            .andExpect(jsonPath("$.[*].nmContactos").value(hasItem(DEFAULT_NM_CONTACTOS.toString())))
            .andExpect(jsonPath("$.[*].textoContactos").value(hasItem(DEFAULT_TEXTO_CONTACTOS.toString())))
            .andExpect(jsonPath("$.[*].resumoTextoContactos").value(hasItem(DEFAULT_RESUMO_TEXTO_CONTACTOS.toString())))
            .andExpect(jsonPath("$.[*].dtLancamento").value(hasItem(DEFAULT_DT_LANCAMENTO.toString())))
            .andExpect(jsonPath("$.[*].dtUltimaAlteracao").value(hasItem(DEFAULT_DT_ULTIMA_ALTERACAO.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultContactosShouldNotBeFound(String filter) throws Exception {
        restContactosMockMvc.perform(get("/api/contactos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingContactos() throws Exception {
        // Get the contactos
        restContactosMockMvc.perform(get("/api/contactos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContactos() throws Exception {
        // Initialize the database
        contactosRepository.saveAndFlush(contactos);
        int databaseSizeBeforeUpdate = contactosRepository.findAll().size();

        // Update the contactos
        Contactos updatedContactos = contactosRepository.findOne(contactos.getId());
        // Disconnect from session so that the updates on updatedContactos are not directly saved in db
        em.detach(updatedContactos);
        updatedContactos
            .idContactos(UPDATED_ID_CONTACTOS)
            .nmContactos(UPDATED_NM_CONTACTOS)
            .textoContactos(UPDATED_TEXTO_CONTACTOS)
            .resumoTextoContactos(UPDATED_RESUMO_TEXTO_CONTACTOS)
            .dtLancamento(UPDATED_DT_LANCAMENTO)
            .dtUltimaAlteracao(UPDATED_DT_ULTIMA_ALTERACAO);
        ContactosDTO contactosDTO = contactosMapper.toDto(updatedContactos);

        restContactosMockMvc.perform(put("/api/contactos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactosDTO)))
            .andExpect(status().isOk());

        // Validate the Contactos in the database
        List<Contactos> contactosList = contactosRepository.findAll();
        assertThat(contactosList).hasSize(databaseSizeBeforeUpdate);
        Contactos testContactos = contactosList.get(contactosList.size() - 1);
        assertThat(testContactos.getIdContactos()).isEqualTo(UPDATED_ID_CONTACTOS);
        assertThat(testContactos.getNmContactos()).isEqualTo(UPDATED_NM_CONTACTOS);
        assertThat(testContactos.getTextoContactos()).isEqualTo(UPDATED_TEXTO_CONTACTOS);
        assertThat(testContactos.getResumoTextoContactos()).isEqualTo(UPDATED_RESUMO_TEXTO_CONTACTOS);
        assertThat(testContactos.getDtLancamento()).isEqualTo(UPDATED_DT_LANCAMENTO);
        assertThat(testContactos.getDtUltimaAlteracao()).isEqualTo(UPDATED_DT_ULTIMA_ALTERACAO);
    }

    @Test
    @Transactional
    public void updateNonExistingContactos() throws Exception {
        int databaseSizeBeforeUpdate = contactosRepository.findAll().size();

        // Create the Contactos
        ContactosDTO contactosDTO = contactosMapper.toDto(contactos);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restContactosMockMvc.perform(put("/api/contactos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactosDTO)))
            .andExpect(status().isCreated());

        // Validate the Contactos in the database
        List<Contactos> contactosList = contactosRepository.findAll();
        assertThat(contactosList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteContactos() throws Exception {
        // Initialize the database
        contactosRepository.saveAndFlush(contactos);
        int databaseSizeBeforeDelete = contactosRepository.findAll().size();

        // Get the contactos
        restContactosMockMvc.perform(delete("/api/contactos/{id}", contactos.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Contactos> contactosList = contactosRepository.findAll();
        assertThat(contactosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Contactos.class);
        Contactos contactos1 = new Contactos();
        contactos1.setId(1L);
        Contactos contactos2 = new Contactos();
        contactos2.setId(contactos1.getId());
        assertThat(contactos1).isEqualTo(contactos2);
        contactos2.setId(2L);
        assertThat(contactos1).isNotEqualTo(contactos2);
        contactos1.setId(null);
        assertThat(contactos1).isNotEqualTo(contactos2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContactosDTO.class);
        ContactosDTO contactosDTO1 = new ContactosDTO();
        contactosDTO1.setId(1L);
        ContactosDTO contactosDTO2 = new ContactosDTO();
        assertThat(contactosDTO1).isNotEqualTo(contactosDTO2);
        contactosDTO2.setId(contactosDTO1.getId());
        assertThat(contactosDTO1).isEqualTo(contactosDTO2);
        contactosDTO2.setId(2L);
        assertThat(contactosDTO1).isNotEqualTo(contactosDTO2);
        contactosDTO1.setId(null);
        assertThat(contactosDTO1).isNotEqualTo(contactosDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(contactosMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(contactosMapper.fromId(null)).isNull();
    }
}
