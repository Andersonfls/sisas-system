package com.minea.sisas.repository;

import com.minea.sisas.domain.Provincia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the Provincia entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long>, JpaSpecificationExecutor<Provincia> {
    @Query("select p from Provincia p where LOWER(p.nmProvincia) like LOWER(concat(:nome,'%'))")
    Page buscarPorNome(@Param("nome") String nome, Pageable pageable);

    @Query(value = "SELECT p.NM_PROVINCIA as provincia, COUNT(m.ID_MUNICIPIO ) as municipios, COUNT(c.ID_COMUNA ) as comunas," +
        "   count(CASE WHEN sa.POSSUI_SISTEMA_AGUA = 1  THEN 1 END) as agua_sim," +
        "   count(CASE WHEN sa.POSSUI_SISTEMA_AGUA = 0 THEN 1 END) as agua_nao," +
        "   count(CASE WHEN sa.POSSUI_SISTEMA_AGUA THEN 1 ELSE 1 END) as total_aguas" +
        " FROM sistema_sisas.sistema_agua sa   " +
        " INNER JOIN sistema_sisas.provincia p on p.ID_PROVINCIA  = sa.ID_PROVINCIA " +
        " INNER JOIN sistema_sisas.municipio m on m.ID_MUNICIPIO = sa.ID_MUNICIPIO " +
        " INNER JOIN sistema_sisas.comuna c on c.ID_MUNICIPIO = m.ID_MUNICIPIO " +
        " where sa.ID_SISTEMA_AGUA IS NOT NULL " +
        " group by p.NM_PROVINCIA ", nativeQuery = true)
    List<Object[]> buscaDadosInqueritoAguas();


    @Query(value = "SELECT DISTINCT sa.NM_TP_AREA as ambito, SUM(sa.QTD_POPULACAO_ACTUAL) as populacao," +
        "    SUM(sa.QTD_HABITANTES_ACESSO_SERVICO_AGUA) as habitantes" +
        "    FROM sistema_sisas.sistema_agua sa" +
        "    group by sa.NM_TP_AREA ", nativeQuery = true)
    List<Object[]> buscaDadosInqueritoPorAmbito();


    @Query(value = "SELECT p.NM_PROVINCIA as provincia," +
        "    count(CASE WHEN sa.POSSUI_SISTEMA_AGUA THEN 1 ELSE 1 END) as total_aguas," +
        "    sum(CASE WHEN sa.NM_TP_TRATAMENTO_PADRAO_UTILIZADO IS NOT NULL THEN 1 ELSE 0 END) as padrao," +
        "    sum(CASE WHEN sa.NM_TP_TRATAMENTO_BASICO_UTILIZADO IS NOT NULL THEN 1 ELSE 0 END) as basico," +
        "    sum(CASE WHEN sa.NM_TP_TRATAMENTO_AGUA = 'Não realiza' THEN 1 ELSE 0 END) as nao_realiza," +
        "    sum(CASE WHEN sa.existe_motivo_ausencia_tratamento_obs IS NOT NULL THEN 1 ELSE 0 END) as outros" +
        "    FROM sistema_sisas.sistema_agua sa" +
        "    INNER JOIN sistema_sisas.provincia p on p.ID_PROVINCIA  = sa.ID_PROVINCIA" +
        "    INNER JOIN sistema_sisas.municipio m on m.ID_MUNICIPIO = sa.ID_MUNICIPIO" +
        "    INNER JOIN sistema_sisas.comuna c on c.ID_MUNICIPIO = m.ID_MUNICIPIO" +
        "    where sa.ID_SISTEMA_AGUA IS NOT NULL" +
        "    group by p.NM_PROVINCIA ", nativeQuery = true)
    List<Object[]> buscaDadosTratamentoSistemasAgua();


    @Query(value = "SELECT p.NM_PROVINCIA as provincia," +
        "    COUNT(m.ID_MUNICIPIO ) as municipios," +
        "    COUNT(c.ID_COMUNA ) as comunas," +
        "    SUM(CASE WHEN sa.estado_funcionamento_sistema = 'Está em funcionamento (Bom)' THEN 1 ELSE 0 END) as funcionam," +
        "    SUM(sa.QTD_POPULACAO_ACTUAL) as populacao," +
        "    COUNT(CASE WHEN sa.POSSUI_SISTEMA_AGUA THEN 1 ELSE 1 END) as total_aguas," +
        "    SUM(sa.QTD_HABITANTES_ACESSO_SERVICO_AGUA) as beneficiarios" +
        "    FROM sistema_sisas.sistema_agua sa" +
        "    INNER JOIN sistema_sisas.provincia p on p.ID_PROVINCIA  = sa.ID_PROVINCIA" +
        "    INNER JOIN sistema_sisas.municipio m on m.ID_MUNICIPIO = sa.ID_MUNICIPIO" +
        "    INNER JOIN sistema_sisas.comuna c on c.ID_MUNICIPIO = m.ID_MUNICIPIO" +
        "    where sa.ID_SISTEMA_AGUA IS NOT NULL" +
        "    group by p.NM_PROVINCIA ", nativeQuery = true)
    List<Object[]> buscaDadosSectorAgua();



    @Query(value = "SELECT p.NM_PROVINCIA as provincia," +
        "    COUNT(sa.ID_SISTEMA_AGUA) as numero_sistemas," +
        "    SUM(CASE WHEN sa.estado_funcionamento_sistema = 'Está em funcionamento (Bom)' THEN 1 ELSE 0 END) as funcionam," +
        "    SUM(CASE WHEN sa.estado_funcionamento_sistema = 'Não está em funcionamento' THEN 1 ELSE 0 END) as nao_funcionam," +
        "    SUM(sa.qtd_chafarises_existentes ) as numero_chafariz," +
        "    SUM(sa.QTD_CHAFARISES_FUNCIONANDO) as chafariz_funcionam," +
        "    SUM(sa.qtd_chafarises_existentes - sa.QTD_CHAFARISES_FUNCIONANDO) as chafariz_nao_funcionam" +
        "    FROM sistema_sisas.sistema_agua sa" +
        "    INNER JOIN sistema_sisas.provincia p on p.ID_PROVINCIA  = sa.ID_PROVINCIA" +
        "    where sa.ID_SISTEMA_AGUA IS NOT NULL" +
        "    group by p.NM_PROVINCIA ", nativeQuery = true)
    List<Object[]> buscaDadosSectorAguaChafarizes();
}
