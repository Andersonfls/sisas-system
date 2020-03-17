package com.minea.sisas.repository;

import com.minea.sisas.domain.Provincia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(value = "SELECT v.NM_PROVINCIA as Provincia, " +
        " (SELECT count(m.ID_MUNICIPIO) from sisas.municipio m where m.ID_PROVINCIA  = v.ID_PROVINCIA  group  by  v.NM_PROVINCIA) as NumeroMunicipios, " +
        " (SELECT DISTINCT count(c.ID_COMUNA ) from sisas.comuna c inner join sisas.municipio mm on mm.ID_MUNICIPIO = c.ID_MUNICIPIO  where mm.ID_PROVINCIA = v.ID_PROVINCIA  group  by  v.NM_PROVINCIA) as NumeroComunas, " +
        "            ( SELECT count( a1.POSSUI_SISTEMA_AGUA ) from sisas.sistema_agua a1 where a1.ID_PROVINCIA = v.ID_PROVINCIA and POSSUI_SISTEMA_AGUA = 1 ) as AguasSIM, " +
        " ( SELECT count( a2.POSSUI_SISTEMA_AGUA ) from sisas.sistema_agua a2 where a2.ID_PROVINCIA = v.ID_PROVINCIA and POSSUI_SISTEMA_AGUA = 0 ) as AguasNAO, " +
        " ( SELECT count( a1.POSSUI_SISTEMA_AGUA ) from sisas.sistema_agua a1 where a1.ID_PROVINCIA = v.ID_PROVINCIA and POSSUI_SISTEMA_AGUA = 0 or POSSUI_SISTEMA_AGUA = 1 ) as TotalSector " +
        " FROM sisas.provincia v " +
        " ORDER BY v.NM_PROVINCIA ", nativeQuery = true)
    List<Object[]> buscaDadosInqueritoAguas();


    // COBERTURA SERVICOS DE AGUA POR AMBITO
    @Query(value = "SELECT DISTINCT sa.NM_TP_AREA as ambito, SUM(sa.QTD_POPULACAO_ACTUAL) as populacao," +
        "    SUM(sa.QTD_HABITANTES_ACESSO_SERVICO_AGUA) as habitantes" +
        "    FROM sisas.sistema_agua sa" +
        "    group by sa.NM_TP_AREA ", nativeQuery = true)
    List<Object[]> buscaDadosInqueritoPorAmbito();

    //COBERTURA SERVICOS DE AGUA POR PROVINCIA
    @Query(value = "SELECT v.NM_PROVINCIA as Provincia, " +
        "         (SELECT count(m.ID_MUNICIPIO) from sisas.municipio m where m.ID_PROVINCIA  = v.ID_PROVINCIA  group  by  v.NM_PROVINCIA) as NumeroMunicipios, " +
        "         (SELECT DISTINCT count(c.ID_COMUNA ) from sisas.comuna c inner join sisas.municipio mm on mm.ID_MUNICIPIO = c.ID_MUNICIPIO  where mm.ID_PROVINCIA = v.ID_PROVINCIA  group  by  v.NM_PROVINCIA) as NumeroComunas, " +
        "                    ( SELECT count( a1.POSSUI_SISTEMA_AGUA ) from sisas.sistema_agua a1 where a1.ID_PROVINCIA = v.ID_PROVINCIA and POSSUI_SISTEMA_AGUA = 1 ) as AguasSIM, " +
        "         (( SELECT count( a1.POSSUI_SISTEMA_AGUA ) from sisas.sistema_agua a1 where a1.ID_PROVINCIA = v.ID_PROVINCIA and POSSUI_SISTEMA_AGUA = 1 )+ " +
        "                    ( SELECT count( a2.POSSUI_SISTEMA_AGUA ) from sisas.sistema_agua a2 where a2.ID_PROVINCIA = v.ID_PROVINCIA and POSSUI_SISTEMA_AGUA = 0 )) TotalSector ," +
        "         ( SELECT SUM(CASE WHEN a3.estado_funcionamento_sistema = 'Está em funcionamento (Bom)' THEN 1 ELSE 0 END) from sisas.sistema_agua a3 where a3.ID_PROVINCIA  = v.ID_PROVINCIA)  as funcionam " +
        "         FROM sisas.provincia v " +
        "         ORDER BY v.NM_PROVINCIA ", nativeQuery = true)
    List<Object[]> buscaDadosInqueritoPorProvincia();

    //COBERTURA SERVICOS DE AGUA POR MUNICIPIO
    @Query(value = "SELECT DISTINCT m.NM_MUNICIPIO as municipio," +
        " COUNT(c.ID_COMUNA) as comunas, " +
        " SUM(sa.QTD_POPULACAO_ACTUAL) as populacao," +
        " SUM(sa.QTD_HABITANTES_ACESSO_SERVICO_AGUA) as habitantes" +
        " FROM sisas.sistema_agua sa  " +
        " INNER JOIN sisas.provincia p on p.ID_PROVINCIA = sa.ID_PROVINCIA " +
        " INNER JOIN sisas.municipio m on m.ID_PROVINCIA = p.ID_PROVINCIA " +
        " INNER JOIN sisas.comuna c on c.ID_MUNICIPIO = m.ID_MUNICIPIO " +
        " group by m.NM_MUNICIPIO ", nativeQuery = true)
    List<Object[]> buscaDadosInqueritoPorMunicipio();

    //COBERTURA SERVICOS DE AGUA POR COMUNA
    @Query(value = "SELECT DISTINCT c.NM_COMUNA as comuna," +
        "                SUM(sa.QTD_POPULACAO_ACTUAL) as populacao," +
        " SUM(sa.QTD_HABITANTES_ACESSO_SERVICO_AGUA) as habitantes" +
        " FROM sisas.sistema_agua sa  " +
        " INNER JOIN sisas.provincia p on p.ID_PROVINCIA = sa.ID_PROVINCIA " +
        " INNER JOIN sisas.municipio m on m.ID_PROVINCIA = p.ID_PROVINCIA " +
        " INNER JOIN sisas.comuna c on c.ID_MUNICIPIO = m.ID_MUNICIPIO " +
        " group by c.NM_COMUNA ", nativeQuery = true)
    List<Object[]> buscaDadosInqueritoPorComuna();

    @Query(value = "SELECT p.NM_PROVINCIA as provincia," +
        "    COUNT(m.ID_MUNICIPIO ) as municipios," +
        "    COUNT(c.ID_COMUNA ) as comunas," +
        "    SUM(CASE WHEN sa.estado_funcionamento_sistema = 'Está em funcionamento (Bom)' THEN 1 ELSE 0 END) as funcionam," +
        "    SUM(sa.QTD_POPULACAO_ACTUAL) as populacao," +
        "    COUNT(CASE WHEN sa.POSSUI_SISTEMA_AGUA THEN 1 ELSE 1 END) as total_aguas," +
        "    SUM(sa.QTD_HABITANTES_ACESSO_SERVICO_AGUA) as beneficiarios" +
        "    FROM sisas.sistema_agua sa" +
        "    INNER JOIN sisas.provincia p on p.ID_PROVINCIA  = sa.ID_PROVINCIA" +
        "    INNER JOIN sisas.municipio m on m.ID_MUNICIPIO = sa.ID_MUNICIPIO" +
        "    INNER JOIN sisas.comuna c on c.ID_MUNICIPIO = m.ID_MUNICIPIO" +
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
        "    FROM sisas.sistema_agua sa" +
        "    INNER JOIN sisas.provincia p on p.ID_PROVINCIA  = sa.ID_PROVINCIA" +
        "    where sa.ID_SISTEMA_AGUA IS NOT NULL" +
        "    group by p.NM_PROVINCIA ", nativeQuery = true)
    List<Object[]> buscaDadosSectorAguaChafarizes();


    @Query(value = "SELECT p.NM_PROVINCIA," +
        "    m.NM_MUNICIPIO," +
        "    c.NM_COMUNA," +
        "0 TotalSistemas," +
        "0 ELECTRICA_NrSistemas, " +
        "( " +
        " SELECT COUNT( * ) as ELECTRICA_Funciona  " +
        " FROM sisas.sistema_agua s " +
        " WHERE s.ID_PROVINCIA = p.ID_PROVINCIA " +
        "  AND s.ID_MUNICIPIO = m.ID_MUNICIPIO " +
        "  AND s.ID_COMUNA = c.ID_COMUNA " +
        "  AND s.POSSUI_SISTEMA_AGUA = 1 " +
        "  AND s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)' " +
        "  AND s.NM_TP_FONTE  = 'Superficial' " +
        "  AND s.NM_TIPO_BOMBA  = 'Elétrica'" +
        ") ELECTRICA_Funciona, " +
        "" +
        " ( " +
        " SELECT COUNT( * ) as ELECTRICA_NAO_Funciona " +
        " FROM sisas.sistema_agua s " +
        " WHERE s.ID_PROVINCIA = p.ID_PROVINCIA " +
        "  AND s.ID_MUNICIPIO = m.ID_MUNICIPIO " +
        "  AND s.ID_COMUNA = c.ID_COMUNA" +
        "      " +
        "  AND s.POSSUI_SISTEMA_AGUA = 1 " +
        "  AND s.estado_funcionamento_sistema = 'Não está em funcionamento'  " +
        "  AND s.NM_TP_FONTE = 'Superficial'" +
        "  AND s.NM_TIPO_BOMBA  = 'Elétrica'" +
        " )  ELECTRICA_NAO_Funciona," +
        "0 DIESEL_NrSistemas, " +
        "( " +
        " SELECT COUNT( * ) as DIESEL_Funciona" +
        " FROM sisas.sistema_agua s" +
        " WHERE s.ID_PROVINCIA = p.ID_PROVINCIA" +
        " AND s.ID_MUNICIPIO = m.ID_MUNICIPIO" +
        " AND s.ID_COMUNA = c.ID_COMUNA" +
        " AND s.POSSUI_SISTEMA_AGUA = 1 " +
        " AND s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'  " +
        " AND s.NM_TP_FONTE = 'Superficial'" +
        " AND s.NM_TIPO_BOMBA  = 'Sistema por gravidade'" +
        " ) DIESEL_Funciona, " +
        "( " +
        " SELECT COUNT( * ) as DIESEL_NAO_Funciona" +
        " FROM sisas.sistema_agua s" +
        " WHERE s.ID_PROVINCIA = p.ID_PROVINCIA " +
        " AND s.ID_MUNICIPIO = m.ID_MUNICIPIO " +
        " AND s.ID_COMUNA = c.ID_COMUNA " +
        " AND s.POSSUI_SISTEMA_AGUA  = 1 " +
        " AND s.estado_funcionamento_sistema = 'Não está em funcionamento'" +
        " AND s.NM_TP_FONTE = 'Superficial'" +
        " AND s.NM_TIPO_BOMBA = 'Sistema por gravidade'" +
        ") DIESEL_NAO_Funciona," +
        "0 GRAVIDADE_NrSistemas, " +
        "( " +
        " SELECT COUNT( * ) as GRAVIDADE_Funciona " +
        " FROM sisas.sistema_agua s " +
        " WHERE s.ID_PROVINCIA = p.ID_PROVINCIA " +
        "  AND s.ID_MUNICIPIO = m.ID_MUNICIPIO " +
        "  AND s.ID_COMUNA = c.ID_COMUNA" +
        "  AND s.POSSUI_SISTEMA_AGUA = 1 " +
        "  AND s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'" +
        "  AND s.NM_TP_FONTE  = 'Superficial'" +
        "  AND s.NM_TIPO_BOMBA  = 'Sistema por gravidade'" +
        ") GRAVIDADE_Funciona, " +
        "( " +
        " SELECT COUNT( * ) as GRAVIDADE_NAO_Funciona" +
        " FROM sisas.sistema_agua s " +
        " WHERE s.ID_PROVINCIA = p.ID_PROVINCIA" +
        "  AND s.ID_MUNICIPIO = m.ID_MUNICIPIO" +
        "  AND s.ID_COMUNA = c.ID_COMUNA" +
        "  AND s.POSSUI_SISTEMA_AGUA = 1" +
        "  AND s.estado_funcionamento_sistema = 'Não está em funcionamento'  " +
        "  AND s.NM_TP_FONTE = 'Superficial'" +
        "  AND s.NM_TIPO_BOMBA = 'Sistema por gravidade'" +
        ")  GRAVIDADE_NAO_Funciona" +
        " from sisas.sistema_agua s" +
        "     inner join sisas.provincia p on" +
        "     s.ID_PROVINCIA = p.ID_PROVINCIA" +
        "     inner join sisas.municipio m on" +
        "     m.ID_PROVINCIA = p.ID_PROVINCIA " +
        "     inner join sisas.comuna c on" +
        "     m.ID_MUNICIPIO = c.ID_MUNICIPIO " +
        "WHERE NM_TP_FONTE = 'Superficial'" +
        "GROUP BY " +
        "       p.NM_PROVINCIA," +
        "       m.ID_MUNICIPIO, " +
        "       c.ID_COMUNA  ", nativeQuery = true)
    List<Object[]> buscaDadosFonteSubterraneaBombaMecanica();


}
