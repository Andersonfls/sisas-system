package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;

import com.minea.sisas.domain.Projectos;
import com.minea.sisas.domain.Situacao;
import com.minea.sisas.domain.Inicio;
import com.minea.sisas.repository.ProjectosRepository;
import com.minea.sisas.service.ProjectosService;
import com.minea.sisas.service.dto.ProjectosDTO;
import com.minea.sisas.service.mapper.ProjectosMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;
import com.minea.sisas.service.ProjectosQueryService;

import com.minea.sisas.web.rest.ProjectosResource;
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
 * Test class for the ProjectosResource REST controller.
 *
 * @see ProjectosResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class ProjectosResourceIntTest {

    private static final Long DEFAULT_ID_PROJECTOS = 1L;
    private static final Long UPDATED_ID_PROJECTOS = 2L;

    private static final String DEFAULT_NM_PROJECTOS = "AAAAAAAAAA";
    private static final String UPDATED_NM_PROJECTOS = "BBBBBBBBBB";

    private static final String DEFAULT_TEXTO_PROJECTOS = "AAAAAAAAAA";
    private static final String UPDATED_TEXTO_PROJECTOS = "BBBBBBBBBB";

    private static final String DEFAULT_RESUMO_TEXTO_PROJECTOS = "AAAAAAAAAA";
    private static final String UPDATED_RESUMO_TEXTO_PROJECTOS = "BBBBBBBBBB";

    @Autowired
    private ProjectosRepository projectosRepository;

    @Autowired
    private ProjectosMapper projectosMapper;

    @Autowired
    private ProjectosService projectosService;

    @Autowired
    private ProjectosQueryService projectosQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProjectosMockMvc;

    private Projectos projectos;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProjectosResource projectosResource = new ProjectosResource(projectosService, projectosQueryService);
        this.restProjectosMockMvc = MockMvcBuilders.standaloneSetup(projectosResource)
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
    public static Projectos createEntity(EntityManager em) {
        Projectos projectos = new Projectos()
            .nmProjectos(DEFAULT_NM_PROJECTOS)
            .textoProjectos(DEFAULT_TEXTO_PROJECTOS)
            .resumoTextoProjectos(DEFAULT_RESUMO_TEXTO_PROJECTOS);
        // Add required entity
        Situacao idSituacao = SituacaoResourceIntTest.createEntity(em);
        em.persist(idSituacao);
        em.flush();
        projectos.setIdSituacao(idSituacao);
        return projectos;
    }

    @Before
    public void initTest() {
        projectos = createEntity(em);
    }

    @Test
    @Transactional
    public void createProjectos() throws Exception {
        int databaseSizeBeforeCreate = projectosRepository.findAll().size();

        // Create the Projectos
        ProjectosDTO projectosDTO = projectosMapper.toDto(projectos);
        restProjectosMockMvc.perform(post("/api/projectos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectosDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectos in the database
        List<Projectos> projectosList = projectosRepository.findAll();
        assertThat(projectosList).hasSize(databaseSizeBeforeCreate + 1);
        Projectos testProjectos = projectosList.get(projectosList.size() - 1);
        assertThat(testProjectos.getNmProjectos()).isEqualTo(DEFAULT_NM_PROJECTOS);
        assertThat(testProjectos.getTextoProjectos()).isEqualTo(DEFAULT_TEXTO_PROJECTOS);
        assertThat(testProjectos.getResumoTextoProjectos()).isEqualTo(DEFAULT_RESUMO_TEXTO_PROJECTOS);
    }

    @Test
    @Transactional
    public void createProjectosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectosRepository.findAll().size();

        // Create the Projectos with an existing ID
        projectos.setId(1L);
        ProjectosDTO projectosDTO = projectosMapper.toDto(projectos);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectosMockMvc.perform(post("/api/projectos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectosDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Projectos in the database
        List<Projectos> projectosList = projectosRepository.findAll();
        assertThat(projectosList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdProjectosIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectosRepository.findAll().size();
        // set the field null

        // Create the Projectos, which fails.
        ProjectosDTO projectosDTO = projectosMapper.toDto(projectos);

        restProjectosMockMvc.perform(post("/api/projectos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectosDTO)))
            .andExpect(status().isBadRequest());

        List<Projectos> projectosList = projectosRepository.findAll();
        assertThat(projectosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNmProjectosIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectosRepository.findAll().size();
        // set the field null
        projectos.setNmProjectos(null);

        // Create the Projectos, which fails.
        ProjectosDTO projectosDTO = projectosMapper.toDto(projectos);

        restProjectosMockMvc.perform(post("/api/projectos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectosDTO)))
            .andExpect(status().isBadRequest());

        List<Projectos> projectosList = projectosRepository.findAll();
        assertThat(projectosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTextoProjectosIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectosRepository.findAll().size();
        // set the field null
        projectos.setTextoProjectos(null);

        // Create the Projectos, which fails.
        ProjectosDTO projectosDTO = projectosMapper.toDto(projectos);

        restProjectosMockMvc.perform(post("/api/projectos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectosDTO)))
            .andExpect(status().isBadRequest());

        List<Projectos> projectosList = projectosRepository.findAll();
        assertThat(projectosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkResumoTextoProjectosIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectosRepository.findAll().size();
        // set the field null
        projectos.setResumoTextoProjectos(null);

        // Create the Projectos, which fails.
        ProjectosDTO projectosDTO = projectosMapper.toDto(projectos);

        restProjectosMockMvc.perform(post("/api/projectos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectosDTO)))
            .andExpect(status().isBadRequest());

        List<Projectos> projectosList = projectosRepository.findAll();
        assertThat(projectosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProjectos() throws Exception {
        // Initialize the database
        projectosRepository.saveAndFlush(projectos);

        // Get all the projectosList
        restProjectosMockMvc.perform(get("/api/projectos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectos.getId().intValue())))
            .andExpect(jsonPath("$.[*].idProjectos").value(hasItem(DEFAULT_ID_PROJECTOS.intValue())))
            .andExpect(jsonPath("$.[*].nmProjectos").value(hasItem(DEFAULT_NM_PROJECTOS.toString())))
            .andExpect(jsonPath("$.[*].textoProjectos").value(hasItem(DEFAULT_TEXTO_PROJECTOS.toString())))
            .andExpect(jsonPath("$.[*].resumoTextoProjectos").value(hasItem(DEFAULT_RESUMO_TEXTO_PROJECTOS.toString())));
    }

    @Test
    @Transactional
    public void getProjectos() throws Exception {
        // Initialize the database
        projectosRepository.saveAndFlush(projectos);

        // Get the projectos
        restProjectosMockMvc.perform(get("/api/projectos/{id}", projectos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(projectos.getId().intValue()))
            .andExpect(jsonPath("$.idProjectos").value(DEFAULT_ID_PROJECTOS.intValue()))
            .andExpect(jsonPath("$.nmProjectos").value(DEFAULT_NM_PROJECTOS.toString()))
            .andExpect(jsonPath("$.textoProjectos").value(DEFAULT_TEXTO_PROJECTOS.toString()))
            .andExpect(jsonPath("$.resumoTextoProjectos").value(DEFAULT_RESUMO_TEXTO_PROJECTOS.toString()));
    }

    @Test
    @Transactional
    public void getAllProjectosByIdProjectosIsEqualToSomething() throws Exception {
        // Initialize the database
        projectosRepository.saveAndFlush(projectos);

        // Get all the projectosList where idProjectos equals to DEFAULT_ID_PROJECTOS
        defaultProjectosShouldBeFound("idProjectos.equals=" + DEFAULT_ID_PROJECTOS);

        // Get all the projectosList where idProjectos equals to UPDATED_ID_PROJECTOS
        defaultProjectosShouldNotBeFound("idProjectos.equals=" + UPDATED_ID_PROJECTOS);
    }

    @Test
    @Transactional
    public void getAllProjectosByIdProjectosIsInShouldWork() throws Exception {
        // Initialize the database
        projectosRepository.saveAndFlush(projectos);

        // Get all the projectosList where idProjectos in DEFAULT_ID_PROJECTOS or UPDATED_ID_PROJECTOS
        defaultProjectosShouldBeFound("idProjectos.in=" + DEFAULT_ID_PROJECTOS + "," + UPDATED_ID_PROJECTOS);

        // Get all the projectosList where idProjectos equals to UPDATED_ID_PROJECTOS
        defaultProjectosShouldNotBeFound("idProjectos.in=" + UPDATED_ID_PROJECTOS);
    }

    @Test
    @Transactional
    public void getAllProjectosByIdProjectosIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectosRepository.saveAndFlush(projectos);

        // Get all the projectosList where idProjectos is not null
        defaultProjectosShouldBeFound("idProjectos.specified=true");

        // Get all the projectosList where idProjectos is null
        defaultProjectosShouldNotBeFound("idProjectos.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectosByIdProjectosIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        projectosRepository.saveAndFlush(projectos);

        // Get all the projectosList where idProjectos greater than or equals to DEFAULT_ID_PROJECTOS
        defaultProjectosShouldBeFound("idProjectos.greaterOrEqualThan=" + DEFAULT_ID_PROJECTOS);

        // Get all the projectosList where idProjectos greater than or equals to UPDATED_ID_PROJECTOS
        defaultProjectosShouldNotBeFound("idProjectos.greaterOrEqualThan=" + UPDATED_ID_PROJECTOS);
    }

    @Test
    @Transactional
    public void getAllProjectosByIdProjectosIsLessThanSomething() throws Exception {
        // Initialize the database
        projectosRepository.saveAndFlush(projectos);

        // Get all the projectosList where idProjectos less than or equals to DEFAULT_ID_PROJECTOS
        defaultProjectosShouldNotBeFound("idProjectos.lessThan=" + DEFAULT_ID_PROJECTOS);

        // Get all the projectosList where idProjectos less than or equals to UPDATED_ID_PROJECTOS
        defaultProjectosShouldBeFound("idProjectos.lessThan=" + UPDATED_ID_PROJECTOS);
    }


    @Test
    @Transactional
    public void getAllProjectosByNmProjectosIsEqualToSomething() throws Exception {
        // Initialize the database
        projectosRepository.saveAndFlush(projectos);

        // Get all the projectosList where nmProjectos equals to DEFAULT_NM_PROJECTOS
        defaultProjectosShouldBeFound("nmProjectos.equals=" + DEFAULT_NM_PROJECTOS);

        // Get all the projectosList where nmProjectos equals to UPDATED_NM_PROJECTOS
        defaultProjectosShouldNotBeFound("nmProjectos.equals=" + UPDATED_NM_PROJECTOS);
    }

    @Test
    @Transactional
    public void getAllProjectosByNmProjectosIsInShouldWork() throws Exception {
        // Initialize the database
        projectosRepository.saveAndFlush(projectos);

        // Get all the projectosList where nmProjectos in DEFAULT_NM_PROJECTOS or UPDATED_NM_PROJECTOS
        defaultProjectosShouldBeFound("nmProjectos.in=" + DEFAULT_NM_PROJECTOS + "," + UPDATED_NM_PROJECTOS);

        // Get all the projectosList where nmProjectos equals to UPDATED_NM_PROJECTOS
        defaultProjectosShouldNotBeFound("nmProjectos.in=" + UPDATED_NM_PROJECTOS);
    }

    @Test
    @Transactional
    public void getAllProjectosByNmProjectosIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectosRepository.saveAndFlush(projectos);

        // Get all the projectosList where nmProjectos is not null
        defaultProjectosShouldBeFound("nmProjectos.specified=true");

        // Get all the projectosList where nmProjectos is null
        defaultProjectosShouldNotBeFound("nmProjectos.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectosByTextoProjectosIsEqualToSomething() throws Exception {
        // Initialize the database
        projectosRepository.saveAndFlush(projectos);

        // Get all the projectosList where textoProjectos equals to DEFAULT_TEXTO_PROJECTOS
        defaultProjectosShouldBeFound("textoProjectos.equals=" + DEFAULT_TEXTO_PROJECTOS);

        // Get all the projectosList where textoProjectos equals to UPDATED_TEXTO_PROJECTOS
        defaultProjectosShouldNotBeFound("textoProjectos.equals=" + UPDATED_TEXTO_PROJECTOS);
    }

    @Test
    @Transactional
    public void getAllProjectosByTextoProjectosIsInShouldWork() throws Exception {
        // Initialize the database
        projectosRepository.saveAndFlush(projectos);

        // Get all the projectosList where textoProjectos in DEFAULT_TEXTO_PROJECTOS or UPDATED_TEXTO_PROJECTOS
        defaultProjectosShouldBeFound("textoProjectos.in=" + DEFAULT_TEXTO_PROJECTOS + "," + UPDATED_TEXTO_PROJECTOS);

        // Get all the projectosList where textoProjectos equals to UPDATED_TEXTO_PROJECTOS
        defaultProjectosShouldNotBeFound("textoProjectos.in=" + UPDATED_TEXTO_PROJECTOS);
    }

    @Test
    @Transactional
    public void getAllProjectosByTextoProjectosIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectosRepository.saveAndFlush(projectos);

        // Get all the projectosList where textoProjectos is not null
        defaultProjectosShouldBeFound("textoProjectos.specified=true");

        // Get all the projectosList where textoProjectos is null
        defaultProjectosShouldNotBeFound("textoProjectos.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectosByResumoTextoProjectosIsEqualToSomething() throws Exception {
        // Initialize the database
        projectosRepository.saveAndFlush(projectos);

        // Get all the projectosList where resumoTextoProjectos equals to DEFAULT_RESUMO_TEXTO_PROJECTOS
        defaultProjectosShouldBeFound("resumoTextoProjectos.equals=" + DEFAULT_RESUMO_TEXTO_PROJECTOS);

        // Get all the projectosList where resumoTextoProjectos equals to UPDATED_RESUMO_TEXTO_PROJECTOS
        defaultProjectosShouldNotBeFound("resumoTextoProjectos.equals=" + UPDATED_RESUMO_TEXTO_PROJECTOS);
    }

    @Test
    @Transactional
    public void getAllProjectosByResumoTextoProjectosIsInShouldWork() throws Exception {
        // Initialize the database
        projectosRepository.saveAndFlush(projectos);

        // Get all the projectosList where resumoTextoProjectos in DEFAULT_RESUMO_TEXTO_PROJECTOS or UPDATED_RESUMO_TEXTO_PROJECTOS
        defaultProjectosShouldBeFound("resumoTextoProjectos.in=" + DEFAULT_RESUMO_TEXTO_PROJECTOS + "," + UPDATED_RESUMO_TEXTO_PROJECTOS);

        // Get all the projectosList where resumoTextoProjectos equals to UPDATED_RESUMO_TEXTO_PROJECTOS
        defaultProjectosShouldNotBeFound("resumoTextoProjectos.in=" + UPDATED_RESUMO_TEXTO_PROJECTOS);
    }

    @Test
    @Transactional
    public void getAllProjectosByResumoTextoProjectosIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectosRepository.saveAndFlush(projectos);

        // Get all the projectosList where resumoTextoProjectos is not null
        defaultProjectosShouldBeFound("resumoTextoProjectos.specified=true");

        // Get all the projectosList where resumoTextoProjectos is null
        defaultProjectosShouldNotBeFound("resumoTextoProjectos.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectosByIdSituacaoIsEqualToSomething() throws Exception {
        // Initialize the database
        Situacao idSituacao = SituacaoResourceIntTest.createEntity(em);
        em.persist(idSituacao);
        em.flush();
        projectos.setIdSituacao(idSituacao);
        projectosRepository.saveAndFlush(projectos);
        Long idSituacaoId = idSituacao.getId();

        // Get all the projectosList where idSituacao equals to situacao
        defaultProjectosShouldBeFound("situacao.equals=" + idSituacaoId);

        // Get all the projectosList where idSituacao equals to situacao + 1
        defaultProjectosShouldNotBeFound("situacao.equals=" + (idSituacaoId + 1));
    }


    @Test
    @Transactional
    public void getAllProjectosByInicioIsEqualToSomething() throws Exception {
        // Initialize the database
        Inicio inicio = InicioResourceIntTest.createEntity(em);
        em.persist(inicio);
        em.flush();
        projectos.addInicio(inicio);
        projectosRepository.saveAndFlush(projectos);
        Long inicioId = inicio.getId();

        // Get all the projectosList where inicio equals to inicioId
        defaultProjectosShouldBeFound("inicioId.equals=" + inicioId);

        // Get all the projectosList where inicio equals to inicioId + 1
        defaultProjectosShouldNotBeFound("inicioId.equals=" + (inicioId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultProjectosShouldBeFound(String filter) throws Exception {
        restProjectosMockMvc.perform(get("/api/projectos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectos.getId().intValue())))
            .andExpect(jsonPath("$.[*].idProjectos").value(hasItem(DEFAULT_ID_PROJECTOS.intValue())))
            .andExpect(jsonPath("$.[*].nmProjectos").value(hasItem(DEFAULT_NM_PROJECTOS.toString())))
            .andExpect(jsonPath("$.[*].textoProjectos").value(hasItem(DEFAULT_TEXTO_PROJECTOS.toString())))
            .andExpect(jsonPath("$.[*].resumoTextoProjectos").value(hasItem(DEFAULT_RESUMO_TEXTO_PROJECTOS.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultProjectosShouldNotBeFound(String filter) throws Exception {
        restProjectosMockMvc.perform(get("/api/projectos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingProjectos() throws Exception {
        // Get the projectos
        restProjectosMockMvc.perform(get("/api/projectos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProjectos() throws Exception {
        // Initialize the database
        projectosRepository.saveAndFlush(projectos);
        int databaseSizeBeforeUpdate = projectosRepository.findAll().size();

        // Update the projectos
        Projectos updatedProjectos = projectosRepository.findOne(projectos.getId());
        // Disconnect from session so that the updates on updatedProjectos are not directly saved in db
        em.detach(updatedProjectos);
        updatedProjectos
            .nmProjectos(UPDATED_NM_PROJECTOS)
            .textoProjectos(UPDATED_TEXTO_PROJECTOS)
            .resumoTextoProjectos(UPDATED_RESUMO_TEXTO_PROJECTOS);
        ProjectosDTO projectosDTO = projectosMapper.toDto(updatedProjectos);

        restProjectosMockMvc.perform(put("/api/projectos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectosDTO)))
            .andExpect(status().isOk());

        // Validate the Projectos in the database
        List<Projectos> projectosList = projectosRepository.findAll();
        assertThat(projectosList).hasSize(databaseSizeBeforeUpdate);
        Projectos testProjectos = projectosList.get(projectosList.size() - 1);
        assertThat(testProjectos.getNmProjectos()).isEqualTo(UPDATED_NM_PROJECTOS);
        assertThat(testProjectos.getTextoProjectos()).isEqualTo(UPDATED_TEXTO_PROJECTOS);
        assertThat(testProjectos.getResumoTextoProjectos()).isEqualTo(UPDATED_RESUMO_TEXTO_PROJECTOS);
    }

    @Test
    @Transactional
    public void updateNonExistingProjectos() throws Exception {
        int databaseSizeBeforeUpdate = projectosRepository.findAll().size();

        // Create the Projectos
        ProjectosDTO projectosDTO = projectosMapper.toDto(projectos);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProjectosMockMvc.perform(put("/api/projectos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectosDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectos in the database
        List<Projectos> projectosList = projectosRepository.findAll();
        assertThat(projectosList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteProjectos() throws Exception {
        // Initialize the database
        projectosRepository.saveAndFlush(projectos);
        int databaseSizeBeforeDelete = projectosRepository.findAll().size();

        // Get the projectos
        restProjectosMockMvc.perform(delete("/api/projectos/{id}", projectos.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Projectos> projectosList = projectosRepository.findAll();
        assertThat(projectosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Projectos.class);
        Projectos projectos1 = new Projectos();
        projectos1.setId(1L);
        Projectos projectos2 = new Projectos();
        projectos2.setId(projectos1.getId());
        assertThat(projectos1).isEqualTo(projectos2);
        projectos2.setId(2L);
        assertThat(projectos1).isNotEqualTo(projectos2);
        projectos1.setId(null);
        assertThat(projectos1).isNotEqualTo(projectos2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectosDTO.class);
        ProjectosDTO projectosDTO1 = new ProjectosDTO();
        projectosDTO1.setId(1L);
        ProjectosDTO projectosDTO2 = new ProjectosDTO();
        assertThat(projectosDTO1).isNotEqualTo(projectosDTO2);
        projectosDTO2.setId(projectosDTO1.getId());
        assertThat(projectosDTO1).isEqualTo(projectosDTO2);
        projectosDTO2.setId(2L);
        assertThat(projectosDTO1).isNotEqualTo(projectosDTO2);
        projectosDTO1.setId(null);
        assertThat(projectosDTO1).isNotEqualTo(projectosDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(projectosMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(projectosMapper.fromId(null)).isNull();
    }
}
