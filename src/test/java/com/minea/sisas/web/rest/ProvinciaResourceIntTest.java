package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;

import com.minea.sisas.domain.Provincia;
import com.minea.sisas.domain.Municipio;
import com.minea.sisas.repository.ProvinciaRepository;
import com.minea.sisas.service.ProvinciaService;
import com.minea.sisas.service.dto.ProvinciaDTO;
import com.minea.sisas.service.mapper.ProvinciaMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;
import com.minea.sisas.service.ProvinciaQueryService;

import com.minea.sisas.web.rest.ProvinciaResource;
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
 * Test class for the ProvinciaResource REST controller.
 *
 * @see ProvinciaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class ProvinciaResourceIntTest {

    private static final Long DEFAULT_ID_PROVINCIA = 1L;
    private static final Long UPDATED_ID_PROVINCIA = 2L;

    private static final String DEFAULT_NM_PROVINCIA = "AAAAAAAAAA";
    private static final String UPDATED_NM_PROVINCIA = "BBBBBBBBBB";

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private ProvinciaMapper provinciaMapper;

    @Autowired
    private ProvinciaService provinciaService;

    @Autowired
    private ProvinciaQueryService provinciaQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProvinciaMockMvc;

    private Provincia provincia;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProvinciaResource provinciaResource = new ProvinciaResource(provinciaService, provinciaQueryService,provinciaRepository);
        this.restProvinciaMockMvc = MockMvcBuilders.standaloneSetup(provinciaResource)
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
    public static Provincia createEntity(EntityManager em) {
        Provincia provincia = new Provincia()
            .nmProvincia(DEFAULT_NM_PROVINCIA);
        return provincia;
    }

    @Before
    public void initTest() {
        provincia = createEntity(em);
    }

    @Test
    @Transactional
    public void createProvincia() throws Exception {
        int databaseSizeBeforeCreate = provinciaRepository.findAll().size();

        // Create the Provincia
        ProvinciaDTO provinciaDTO = provinciaMapper.toDto(provincia);
        restProvinciaMockMvc.perform(post("/api/provincias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provinciaDTO)))
            .andExpect(status().isCreated());

        // Validate the Provincia in the database
        List<Provincia> provinciaList = provinciaRepository.findAll();
        assertThat(provinciaList).hasSize(databaseSizeBeforeCreate + 1);
        Provincia testProvincia = provinciaList.get(provinciaList.size() - 1);
        assertThat(testProvincia.getNmProvincia()).isEqualTo(DEFAULT_NM_PROVINCIA);
    }

    @Test
    @Transactional
    public void createProvinciaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = provinciaRepository.findAll().size();

        // Create the Provincia with an existing ID
        provincia.setId(1L);
        ProvinciaDTO provinciaDTO = provinciaMapper.toDto(provincia);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProvinciaMockMvc.perform(post("/api/provincias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provinciaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Provincia in the database
        List<Provincia> provinciaList = provinciaRepository.findAll();
        assertThat(provinciaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdProvinciaIsRequired() throws Exception {
        int databaseSizeBeforeTest = provinciaRepository.findAll().size();
        // set the field null

        // Create the Provincia, which fails.
        ProvinciaDTO provinciaDTO = provinciaMapper.toDto(provincia);

        restProvinciaMockMvc.perform(post("/api/provincias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provinciaDTO)))
            .andExpect(status().isBadRequest());

        List<Provincia> provinciaList = provinciaRepository.findAll();
        assertThat(provinciaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNmProvinciaIsRequired() throws Exception {
        int databaseSizeBeforeTest = provinciaRepository.findAll().size();
        // set the field null
        provincia.setNmProvincia(null);

        // Create the Provincia, which fails.
        ProvinciaDTO provinciaDTO = provinciaMapper.toDto(provincia);

        restProvinciaMockMvc.perform(post("/api/provincias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provinciaDTO)))
            .andExpect(status().isBadRequest());

        List<Provincia> provinciaList = provinciaRepository.findAll();
        assertThat(provinciaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProvincias() throws Exception {
        // Initialize the database
        provinciaRepository.saveAndFlush(provincia);

        // Get all the provinciaList
        restProvinciaMockMvc.perform(get("/api/provincias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(provincia.getId().intValue())))
            .andExpect(jsonPath("$.[*].provincia").value(hasItem(DEFAULT_ID_PROVINCIA.intValue())))
            .andExpect(jsonPath("$.[*].nmProvincia").value(hasItem(DEFAULT_NM_PROVINCIA.toString())));
    }

    @Test
    @Transactional
    public void getProvincia() throws Exception {
        // Initialize the database
        provinciaRepository.saveAndFlush(provincia);

        // Get the provincia
        restProvinciaMockMvc.perform(get("/api/provincias/{id}", provincia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(provincia.getId().intValue()))
            .andExpect(jsonPath("$.provincia").value(DEFAULT_ID_PROVINCIA.intValue()))
            .andExpect(jsonPath("$.nmProvincia").value(DEFAULT_NM_PROVINCIA.toString()));
    }

    @Test
    @Transactional
    public void getAllProvinciasByIdProvinciaIsEqualToSomething() throws Exception {
        // Initialize the database
        provinciaRepository.saveAndFlush(provincia);

        // Get all the provinciaList where provincia equals to DEFAULT_ID_PROVINCIA
        defaultProvinciaShouldBeFound("provincia.equals=" + DEFAULT_ID_PROVINCIA);

        // Get all the provinciaList where provincia equals to UPDATED_ID_PROVINCIA
        defaultProvinciaShouldNotBeFound("provincia.equals=" + UPDATED_ID_PROVINCIA);
    }

    @Test
    @Transactional
    public void getAllProvinciasByIdProvinciaIsInShouldWork() throws Exception {
        // Initialize the database
        provinciaRepository.saveAndFlush(provincia);

        // Get all the provinciaList where provincia in DEFAULT_ID_PROVINCIA or UPDATED_ID_PROVINCIA
        defaultProvinciaShouldBeFound("provincia.in=" + DEFAULT_ID_PROVINCIA + "," + UPDATED_ID_PROVINCIA);

        // Get all the provinciaList where provincia equals to UPDATED_ID_PROVINCIA
        defaultProvinciaShouldNotBeFound("provincia.in=" + UPDATED_ID_PROVINCIA);
    }

    @Test
    @Transactional
    public void getAllProvinciasByIdProvinciaIsNullOrNotNull() throws Exception {
        // Initialize the database
        provinciaRepository.saveAndFlush(provincia);

        // Get all the provinciaList where provincia is not null
        defaultProvinciaShouldBeFound("provincia.specified=true");

        // Get all the provinciaList where provincia is null
        defaultProvinciaShouldNotBeFound("provincia.specified=false");
    }

    @Test
    @Transactional
    public void getAllProvinciasByIdProvinciaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        provinciaRepository.saveAndFlush(provincia);

        // Get all the provinciaList where provincia greater than or equals to DEFAULT_ID_PROVINCIA
        defaultProvinciaShouldBeFound("provincia.greaterOrEqualThan=" + DEFAULT_ID_PROVINCIA);

        // Get all the provinciaList where provincia greater than or equals to UPDATED_ID_PROVINCIA
        defaultProvinciaShouldNotBeFound("provincia.greaterOrEqualThan=" + UPDATED_ID_PROVINCIA);
    }

    @Test
    @Transactional
    public void getAllProvinciasByIdProvinciaIsLessThanSomething() throws Exception {
        // Initialize the database
        provinciaRepository.saveAndFlush(provincia);

        // Get all the provinciaList where provincia less than or equals to DEFAULT_ID_PROVINCIA
        defaultProvinciaShouldNotBeFound("provincia.lessThan=" + DEFAULT_ID_PROVINCIA);

        // Get all the provinciaList where provincia less than or equals to UPDATED_ID_PROVINCIA
        defaultProvinciaShouldBeFound("provincia.lessThan=" + UPDATED_ID_PROVINCIA);
    }


    @Test
    @Transactional
    public void getAllProvinciasByNmProvinciaIsEqualToSomething() throws Exception {
        // Initialize the database
        provinciaRepository.saveAndFlush(provincia);

        // Get all the provinciaList where nmProvincia equals to DEFAULT_NM_PROVINCIA
        defaultProvinciaShouldBeFound("nmProvincia.equals=" + DEFAULT_NM_PROVINCIA);

        // Get all the provinciaList where nmProvincia equals to UPDATED_NM_PROVINCIA
        defaultProvinciaShouldNotBeFound("nmProvincia.equals=" + UPDATED_NM_PROVINCIA);
    }

    @Test
    @Transactional
    public void getAllProvinciasByNmProvinciaIsInShouldWork() throws Exception {
        // Initialize the database
        provinciaRepository.saveAndFlush(provincia);

        // Get all the provinciaList where nmProvincia in DEFAULT_NM_PROVINCIA or UPDATED_NM_PROVINCIA
        defaultProvinciaShouldBeFound("nmProvincia.in=" + DEFAULT_NM_PROVINCIA + "," + UPDATED_NM_PROVINCIA);

        // Get all the provinciaList where nmProvincia equals to UPDATED_NM_PROVINCIA
        defaultProvinciaShouldNotBeFound("nmProvincia.in=" + UPDATED_NM_PROVINCIA);
    }

    @Test
    @Transactional
    public void getAllProvinciasByNmProvinciaIsNullOrNotNull() throws Exception {
        // Initialize the database
        provinciaRepository.saveAndFlush(provincia);

        // Get all the provinciaList where nmProvincia is not null
        defaultProvinciaShouldBeFound("nmProvincia.specified=true");

        // Get all the provinciaList where nmProvincia is null
        defaultProvinciaShouldNotBeFound("nmProvincia.specified=false");
    }

    @Test
    @Transactional
    public void getAllProvinciasByMunicipioIsEqualToSomething() throws Exception {
        // Initialize the database
        Municipio municipio = MunicipioResourceIntTest.createEntity(em);
        em.persist(municipio);
        em.flush();
        provincia.addMunicipio(municipio);
        provinciaRepository.saveAndFlush(provincia);
        Long municipioId = municipio.getId();

        // Get all the provinciaList where municipio equals to municipioId
        defaultProvinciaShouldBeFound("municipioId.equals=" + municipioId);

        // Get all the provinciaList where municipio equals to municipioId + 1
        defaultProvinciaShouldNotBeFound("municipioId.equals=" + (municipioId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultProvinciaShouldBeFound(String filter) throws Exception {
        restProvinciaMockMvc.perform(get("/api/provincias?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(provincia.getId().intValue())))
            .andExpect(jsonPath("$.[*].provincia").value(hasItem(DEFAULT_ID_PROVINCIA.intValue())))
            .andExpect(jsonPath("$.[*].nmProvincia").value(hasItem(DEFAULT_NM_PROVINCIA.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultProvinciaShouldNotBeFound(String filter) throws Exception {
        restProvinciaMockMvc.perform(get("/api/provincias?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingProvincia() throws Exception {
        // Get the provincia
        restProvinciaMockMvc.perform(get("/api/provincias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProvincia() throws Exception {
        // Initialize the database
        provinciaRepository.saveAndFlush(provincia);
        int databaseSizeBeforeUpdate = provinciaRepository.findAll().size();

        // Update the provincia
        Provincia updatedProvincia = provinciaRepository.findOne(provincia.getId());
        // Disconnect from session so that the updates on updatedProvincia are not directly saved in db
        em.detach(updatedProvincia);
        updatedProvincia
            .nmProvincia(UPDATED_NM_PROVINCIA);
        ProvinciaDTO provinciaDTO = provinciaMapper.toDto(updatedProvincia);

        restProvinciaMockMvc.perform(put("/api/provincias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provinciaDTO)))
            .andExpect(status().isOk());

        // Validate the Provincia in the database
        List<Provincia> provinciaList = provinciaRepository.findAll();
        assertThat(provinciaList).hasSize(databaseSizeBeforeUpdate);
        Provincia testProvincia = provinciaList.get(provinciaList.size() - 1);
        assertThat(testProvincia.getNmProvincia()).isEqualTo(UPDATED_NM_PROVINCIA);
    }

    @Test
    @Transactional
    public void updateNonExistingProvincia() throws Exception {
        int databaseSizeBeforeUpdate = provinciaRepository.findAll().size();

        // Create the Provincia
        ProvinciaDTO provinciaDTO = provinciaMapper.toDto(provincia);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProvinciaMockMvc.perform(put("/api/provincias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provinciaDTO)))
            .andExpect(status().isCreated());

        // Validate the Provincia in the database
        List<Provincia> provinciaList = provinciaRepository.findAll();
        assertThat(provinciaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteProvincia() throws Exception {
        // Initialize the database
        provinciaRepository.saveAndFlush(provincia);
        int databaseSizeBeforeDelete = provinciaRepository.findAll().size();

        // Get the provincia
        restProvinciaMockMvc.perform(delete("/api/provincias/{id}", provincia.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Provincia> provinciaList = provinciaRepository.findAll();
        assertThat(provinciaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Provincia.class);
        Provincia provincia1 = new Provincia();
        provincia1.setId(1L);
        Provincia provincia2 = new Provincia();
        provincia2.setId(provincia1.getId());
        assertThat(provincia1).isEqualTo(provincia2);
        provincia2.setId(2L);
        assertThat(provincia1).isNotEqualTo(provincia2);
        provincia1.setId(null);
        assertThat(provincia1).isNotEqualTo(provincia2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProvinciaDTO.class);
        ProvinciaDTO provinciaDTO1 = new ProvinciaDTO();
        provinciaDTO1.setId(1L);
        ProvinciaDTO provinciaDTO2 = new ProvinciaDTO();
        assertThat(provinciaDTO1).isNotEqualTo(provinciaDTO2);
        provinciaDTO2.setId(provinciaDTO1.getId());
        assertThat(provinciaDTO1).isEqualTo(provinciaDTO2);
        provinciaDTO2.setId(2L);
        assertThat(provinciaDTO1).isNotEqualTo(provinciaDTO2);
        provinciaDTO1.setId(null);
        assertThat(provinciaDTO1).isNotEqualTo(provinciaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(provinciaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(provinciaMapper.fromId(null)).isNull();
    }
}
