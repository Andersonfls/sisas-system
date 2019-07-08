package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;

import com.minea.sisas.domain.OrigemFinanciamento;
import com.minea.sisas.repository.OrigemFinanciamentoRepository;
import com.minea.sisas.service.OrigemFinanciamentoService;
import com.minea.sisas.service.dto.OrigemFinanciamentoDTO;
import com.minea.sisas.service.mapper.OrigemFinanciamentoMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;
import com.minea.sisas.service.OrigemFinanciamentoQueryService;

import com.minea.sisas.web.rest.OrigemFinanciamentoResource;
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
 * Test class for the OrigemFinanciamentoResource REST controller.
 *
 * @see OrigemFinanciamentoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class OrigemFinanciamentoResourceIntTest {

    private static final Long DEFAULT_ID_ORIGEM_FINANCIAMENTO = 1L;
    private static final Long UPDATED_ID_ORIGEM_FINANCIAMENTO = 2L;

    private static final String DEFAULT_DESCRICAO_ORIGEM_FINANCIAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO_ORIGEM_FINANCIAMENTO = "BBBBBBBBBB";

    @Autowired
    private OrigemFinanciamentoRepository origemFinanciamentoRepository;

    @Autowired
    private OrigemFinanciamentoMapper origemFinanciamentoMapper;

    @Autowired
    private OrigemFinanciamentoService origemFinanciamentoService;

    @Autowired
    private OrigemFinanciamentoQueryService origemFinanciamentoQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOrigemFinanciamentoMockMvc;

    private OrigemFinanciamento origemFinanciamento;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrigemFinanciamentoResource origemFinanciamentoResource = new OrigemFinanciamentoResource(origemFinanciamentoService, origemFinanciamentoQueryService);
        this.restOrigemFinanciamentoMockMvc = MockMvcBuilders.standaloneSetup(origemFinanciamentoResource)
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
    public static OrigemFinanciamento createEntity(EntityManager em) {
        OrigemFinanciamento origemFinanciamento = new OrigemFinanciamento()
            .descricaoOrigemFinanciamento(DEFAULT_DESCRICAO_ORIGEM_FINANCIAMENTO);
        return origemFinanciamento;
    }

    @Before
    public void initTest() {
        origemFinanciamento = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrigemFinanciamento() throws Exception {
        int databaseSizeBeforeCreate = origemFinanciamentoRepository.findAll().size();

        // Create the OrigemFinanciamento
        OrigemFinanciamentoDTO origemFinanciamentoDTO = origemFinanciamentoMapper.toDto(origemFinanciamento);
        restOrigemFinanciamentoMockMvc.perform(post("/api/origem-financiamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(origemFinanciamentoDTO)))
            .andExpect(status().isCreated());

        // Validate the OrigemFinanciamento in the database
        List<OrigemFinanciamento> origemFinanciamentoList = origemFinanciamentoRepository.findAll();
        assertThat(origemFinanciamentoList).hasSize(databaseSizeBeforeCreate + 1);
        OrigemFinanciamento testOrigemFinanciamento = origemFinanciamentoList.get(origemFinanciamentoList.size() - 1);
        assertThat(testOrigemFinanciamento.getDescricaoOrigemFinanciamento()).isEqualTo(DEFAULT_DESCRICAO_ORIGEM_FINANCIAMENTO);
    }

    @Test
    @Transactional
    public void createOrigemFinanciamentoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = origemFinanciamentoRepository.findAll().size();

        // Create the OrigemFinanciamento with an existing ID
        origemFinanciamento.setId(1L);
        OrigemFinanciamentoDTO origemFinanciamentoDTO = origemFinanciamentoMapper.toDto(origemFinanciamento);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrigemFinanciamentoMockMvc.perform(post("/api/origem-financiamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(origemFinanciamentoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrigemFinanciamento in the database
        List<OrigemFinanciamento> origemFinanciamentoList = origemFinanciamentoRepository.findAll();
        assertThat(origemFinanciamentoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdOrigemFinanciamentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = origemFinanciamentoRepository.findAll().size();
        // set the field null

        // Create the OrigemFinanciamento, which fails.
        OrigemFinanciamentoDTO origemFinanciamentoDTO = origemFinanciamentoMapper.toDto(origemFinanciamento);

        restOrigemFinanciamentoMockMvc.perform(post("/api/origem-financiamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(origemFinanciamentoDTO)))
            .andExpect(status().isBadRequest());

        List<OrigemFinanciamento> origemFinanciamentoList = origemFinanciamentoRepository.findAll();
        assertThat(origemFinanciamentoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescricaoOrigemFinanciamentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = origemFinanciamentoRepository.findAll().size();
        // set the field null
        origemFinanciamento.setDescricaoOrigemFinanciamento(null);

        // Create the OrigemFinanciamento, which fails.
        OrigemFinanciamentoDTO origemFinanciamentoDTO = origemFinanciamentoMapper.toDto(origemFinanciamento);

        restOrigemFinanciamentoMockMvc.perform(post("/api/origem-financiamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(origemFinanciamentoDTO)))
            .andExpect(status().isBadRequest());

        List<OrigemFinanciamento> origemFinanciamentoList = origemFinanciamentoRepository.findAll();
        assertThat(origemFinanciamentoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOrigemFinanciamentos() throws Exception {
        // Initialize the database
        origemFinanciamentoRepository.saveAndFlush(origemFinanciamento);

        // Get all the origemFinanciamentoList
        restOrigemFinanciamentoMockMvc.perform(get("/api/origem-financiamentos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(origemFinanciamento.getId().intValue())))
            .andExpect(jsonPath("$.[*].idOrigemFinanciamento").value(hasItem(DEFAULT_ID_ORIGEM_FINANCIAMENTO.intValue())))
            .andExpect(jsonPath("$.[*].descricaoOrigemFinanciamento").value(hasItem(DEFAULT_DESCRICAO_ORIGEM_FINANCIAMENTO.toString())));
    }

    @Test
    @Transactional
    public void getOrigemFinanciamento() throws Exception {
        // Initialize the database
        origemFinanciamentoRepository.saveAndFlush(origemFinanciamento);

        // Get the origemFinanciamento
        restOrigemFinanciamentoMockMvc.perform(get("/api/origem-financiamentos/{id}", origemFinanciamento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(origemFinanciamento.getId().intValue()))
            .andExpect(jsonPath("$.idOrigemFinanciamento").value(DEFAULT_ID_ORIGEM_FINANCIAMENTO.intValue()))
            .andExpect(jsonPath("$.descricaoOrigemFinanciamento").value(DEFAULT_DESCRICAO_ORIGEM_FINANCIAMENTO.toString()));
    }

    @Test
    @Transactional
    public void getAllOrigemFinanciamentosByIdOrigemFinanciamentoIsEqualToSomething() throws Exception {
        // Initialize the database
        origemFinanciamentoRepository.saveAndFlush(origemFinanciamento);

        // Get all the origemFinanciamentoList where idOrigemFinanciamento equals to DEFAULT_ID_ORIGEM_FINANCIAMENTO
        defaultOrigemFinanciamentoShouldBeFound("idOrigemFinanciamento.equals=" + DEFAULT_ID_ORIGEM_FINANCIAMENTO);

        // Get all the origemFinanciamentoList where idOrigemFinanciamento equals to UPDATED_ID_ORIGEM_FINANCIAMENTO
        defaultOrigemFinanciamentoShouldNotBeFound("idOrigemFinanciamento.equals=" + UPDATED_ID_ORIGEM_FINANCIAMENTO);
    }

    @Test
    @Transactional
    public void getAllOrigemFinanciamentosByIdOrigemFinanciamentoIsInShouldWork() throws Exception {
        // Initialize the database
        origemFinanciamentoRepository.saveAndFlush(origemFinanciamento);

        // Get all the origemFinanciamentoList where idOrigemFinanciamento in DEFAULT_ID_ORIGEM_FINANCIAMENTO or UPDATED_ID_ORIGEM_FINANCIAMENTO
        defaultOrigemFinanciamentoShouldBeFound("idOrigemFinanciamento.in=" + DEFAULT_ID_ORIGEM_FINANCIAMENTO + "," + UPDATED_ID_ORIGEM_FINANCIAMENTO);

        // Get all the origemFinanciamentoList where idOrigemFinanciamento equals to UPDATED_ID_ORIGEM_FINANCIAMENTO
        defaultOrigemFinanciamentoShouldNotBeFound("idOrigemFinanciamento.in=" + UPDATED_ID_ORIGEM_FINANCIAMENTO);
    }

    @Test
    @Transactional
    public void getAllOrigemFinanciamentosByIdOrigemFinanciamentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        origemFinanciamentoRepository.saveAndFlush(origemFinanciamento);

        // Get all the origemFinanciamentoList where idOrigemFinanciamento is not null
        defaultOrigemFinanciamentoShouldBeFound("idOrigemFinanciamento.specified=true");

        // Get all the origemFinanciamentoList where idOrigemFinanciamento is null
        defaultOrigemFinanciamentoShouldNotBeFound("idOrigemFinanciamento.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrigemFinanciamentosByIdOrigemFinanciamentoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        origemFinanciamentoRepository.saveAndFlush(origemFinanciamento);

        // Get all the origemFinanciamentoList where idOrigemFinanciamento greater than or equals to DEFAULT_ID_ORIGEM_FINANCIAMENTO
        defaultOrigemFinanciamentoShouldBeFound("idOrigemFinanciamento.greaterOrEqualThan=" + DEFAULT_ID_ORIGEM_FINANCIAMENTO);

        // Get all the origemFinanciamentoList where idOrigemFinanciamento greater than or equals to UPDATED_ID_ORIGEM_FINANCIAMENTO
        defaultOrigemFinanciamentoShouldNotBeFound("idOrigemFinanciamento.greaterOrEqualThan=" + UPDATED_ID_ORIGEM_FINANCIAMENTO);
    }

    @Test
    @Transactional
    public void getAllOrigemFinanciamentosByIdOrigemFinanciamentoIsLessThanSomething() throws Exception {
        // Initialize the database
        origemFinanciamentoRepository.saveAndFlush(origemFinanciamento);

        // Get all the origemFinanciamentoList where idOrigemFinanciamento less than or equals to DEFAULT_ID_ORIGEM_FINANCIAMENTO
        defaultOrigemFinanciamentoShouldNotBeFound("idOrigemFinanciamento.lessThan=" + DEFAULT_ID_ORIGEM_FINANCIAMENTO);

        // Get all the origemFinanciamentoList where idOrigemFinanciamento less than or equals to UPDATED_ID_ORIGEM_FINANCIAMENTO
        defaultOrigemFinanciamentoShouldBeFound("idOrigemFinanciamento.lessThan=" + UPDATED_ID_ORIGEM_FINANCIAMENTO);
    }


    @Test
    @Transactional
    public void getAllOrigemFinanciamentosByDescricaoOrigemFinanciamentoIsEqualToSomething() throws Exception {
        // Initialize the database
        origemFinanciamentoRepository.saveAndFlush(origemFinanciamento);

        // Get all the origemFinanciamentoList where descricaoOrigemFinanciamento equals to DEFAULT_DESCRICAO_ORIGEM_FINANCIAMENTO
        defaultOrigemFinanciamentoShouldBeFound("descricaoOrigemFinanciamento.equals=" + DEFAULT_DESCRICAO_ORIGEM_FINANCIAMENTO);

        // Get all the origemFinanciamentoList where descricaoOrigemFinanciamento equals to UPDATED_DESCRICAO_ORIGEM_FINANCIAMENTO
        defaultOrigemFinanciamentoShouldNotBeFound("descricaoOrigemFinanciamento.equals=" + UPDATED_DESCRICAO_ORIGEM_FINANCIAMENTO);
    }

    @Test
    @Transactional
    public void getAllOrigemFinanciamentosByDescricaoOrigemFinanciamentoIsInShouldWork() throws Exception {
        // Initialize the database
        origemFinanciamentoRepository.saveAndFlush(origemFinanciamento);

        // Get all the origemFinanciamentoList where descricaoOrigemFinanciamento in DEFAULT_DESCRICAO_ORIGEM_FINANCIAMENTO or UPDATED_DESCRICAO_ORIGEM_FINANCIAMENTO
        defaultOrigemFinanciamentoShouldBeFound("descricaoOrigemFinanciamento.in=" + DEFAULT_DESCRICAO_ORIGEM_FINANCIAMENTO + "," + UPDATED_DESCRICAO_ORIGEM_FINANCIAMENTO);

        // Get all the origemFinanciamentoList where descricaoOrigemFinanciamento equals to UPDATED_DESCRICAO_ORIGEM_FINANCIAMENTO
        defaultOrigemFinanciamentoShouldNotBeFound("descricaoOrigemFinanciamento.in=" + UPDATED_DESCRICAO_ORIGEM_FINANCIAMENTO);
    }

    @Test
    @Transactional
    public void getAllOrigemFinanciamentosByDescricaoOrigemFinanciamentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        origemFinanciamentoRepository.saveAndFlush(origemFinanciamento);

        // Get all the origemFinanciamentoList where descricaoOrigemFinanciamento is not null
        defaultOrigemFinanciamentoShouldBeFound("descricaoOrigemFinanciamento.specified=true");

        // Get all the origemFinanciamentoList where descricaoOrigemFinanciamento is null
        defaultOrigemFinanciamentoShouldNotBeFound("descricaoOrigemFinanciamento.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultOrigemFinanciamentoShouldBeFound(String filter) throws Exception {
        restOrigemFinanciamentoMockMvc.perform(get("/api/origem-financiamentos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(origemFinanciamento.getId().intValue())))
            .andExpect(jsonPath("$.[*].idOrigemFinanciamento").value(hasItem(DEFAULT_ID_ORIGEM_FINANCIAMENTO.intValue())))
            .andExpect(jsonPath("$.[*].descricaoOrigemFinanciamento").value(hasItem(DEFAULT_DESCRICAO_ORIGEM_FINANCIAMENTO.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultOrigemFinanciamentoShouldNotBeFound(String filter) throws Exception {
        restOrigemFinanciamentoMockMvc.perform(get("/api/origem-financiamentos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingOrigemFinanciamento() throws Exception {
        // Get the origemFinanciamento
        restOrigemFinanciamentoMockMvc.perform(get("/api/origem-financiamentos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrigemFinanciamento() throws Exception {
        // Initialize the database
        origemFinanciamentoRepository.saveAndFlush(origemFinanciamento);
        int databaseSizeBeforeUpdate = origemFinanciamentoRepository.findAll().size();

        // Update the origemFinanciamento
        OrigemFinanciamento updatedOrigemFinanciamento = origemFinanciamentoRepository.findOne(origemFinanciamento.getId());
        // Disconnect from session so that the updates on updatedOrigemFinanciamento are not directly saved in db
        em.detach(updatedOrigemFinanciamento);
        updatedOrigemFinanciamento
            .descricaoOrigemFinanciamento(UPDATED_DESCRICAO_ORIGEM_FINANCIAMENTO);
        OrigemFinanciamentoDTO origemFinanciamentoDTO = origemFinanciamentoMapper.toDto(updatedOrigemFinanciamento);

        restOrigemFinanciamentoMockMvc.perform(put("/api/origem-financiamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(origemFinanciamentoDTO)))
            .andExpect(status().isOk());

        // Validate the OrigemFinanciamento in the database
        List<OrigemFinanciamento> origemFinanciamentoList = origemFinanciamentoRepository.findAll();
        assertThat(origemFinanciamentoList).hasSize(databaseSizeBeforeUpdate);
        OrigemFinanciamento testOrigemFinanciamento = origemFinanciamentoList.get(origemFinanciamentoList.size() - 1);
        assertThat(testOrigemFinanciamento.getDescricaoOrigemFinanciamento()).isEqualTo(UPDATED_DESCRICAO_ORIGEM_FINANCIAMENTO);
    }

    @Test
    @Transactional
    public void updateNonExistingOrigemFinanciamento() throws Exception {
        int databaseSizeBeforeUpdate = origemFinanciamentoRepository.findAll().size();

        // Create the OrigemFinanciamento
        OrigemFinanciamentoDTO origemFinanciamentoDTO = origemFinanciamentoMapper.toDto(origemFinanciamento);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOrigemFinanciamentoMockMvc.perform(put("/api/origem-financiamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(origemFinanciamentoDTO)))
            .andExpect(status().isCreated());

        // Validate the OrigemFinanciamento in the database
        List<OrigemFinanciamento> origemFinanciamentoList = origemFinanciamentoRepository.findAll();
        assertThat(origemFinanciamentoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOrigemFinanciamento() throws Exception {
        // Initialize the database
        origemFinanciamentoRepository.saveAndFlush(origemFinanciamento);
        int databaseSizeBeforeDelete = origemFinanciamentoRepository.findAll().size();

        // Get the origemFinanciamento
        restOrigemFinanciamentoMockMvc.perform(delete("/api/origem-financiamentos/{id}", origemFinanciamento.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OrigemFinanciamento> origemFinanciamentoList = origemFinanciamentoRepository.findAll();
        assertThat(origemFinanciamentoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrigemFinanciamento.class);
        OrigemFinanciamento origemFinanciamento1 = new OrigemFinanciamento();
        origemFinanciamento1.setId(1L);
        OrigemFinanciamento origemFinanciamento2 = new OrigemFinanciamento();
        origemFinanciamento2.setId(origemFinanciamento1.getId());
        assertThat(origemFinanciamento1).isEqualTo(origemFinanciamento2);
        origemFinanciamento2.setId(2L);
        assertThat(origemFinanciamento1).isNotEqualTo(origemFinanciamento2);
        origemFinanciamento1.setId(null);
        assertThat(origemFinanciamento1).isNotEqualTo(origemFinanciamento2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrigemFinanciamentoDTO.class);
        OrigemFinanciamentoDTO origemFinanciamentoDTO1 = new OrigemFinanciamentoDTO();
        origemFinanciamentoDTO1.setId(1L);
        OrigemFinanciamentoDTO origemFinanciamentoDTO2 = new OrigemFinanciamentoDTO();
        assertThat(origemFinanciamentoDTO1).isNotEqualTo(origemFinanciamentoDTO2);
        origemFinanciamentoDTO2.setId(origemFinanciamentoDTO1.getId());
        assertThat(origemFinanciamentoDTO1).isEqualTo(origemFinanciamentoDTO2);
        origemFinanciamentoDTO2.setId(2L);
        assertThat(origemFinanciamentoDTO1).isNotEqualTo(origemFinanciamentoDTO2);
        origemFinanciamentoDTO1.setId(null);
        assertThat(origemFinanciamentoDTO1).isNotEqualTo(origemFinanciamentoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(origemFinanciamentoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(origemFinanciamentoMapper.fromId(null)).isNull();
    }
}
