package com.minea.sisas.repository;

import com.minea.sisas.domain.Provincia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data JPA repository for the Provincia entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MapasTematicosRepository extends JpaRepository<Provincia, Long>, JpaSpecificationExecutor<Provincia> {

    // COBERTURA DE SERVICOS DE AGUA
    @Query(value = "SELECT DISTINCT p.ID_PROVINCIA,  " +
        "       ( SELECT  COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),2)  " +
        "   FROM   sisas.sistema_agua s   " +
        "   WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "       AND s.POSSUI_SISTEMA_AGUA = 1       " +
        "    ) NrBeneficiariosSistemaAgua,     " +
        "      ROUND((( ((SELECT ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/p.populacao)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "    AND s.POSSUI_SISTEMA_AGUA = 1  " +
        "    )))),2) PercentagemCobertura   " +
        "    FROM sisas.sistema_agua s  " +
        "    INNER JOIN sisas.provincia p on s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "    INNER JOIN sisas.municipio m ON s.ID_MUNICIPIO  = m.ID_MUNICIPIO   " +
        "    WHERE s.POSSUI_SISTEMA_AGUA = 1  " +
        "    GROUP BY   " +
        "       p.ID_PROVINCIA, m.ID_MUNICIPIO   " +
        "       ORDER BY p.ID_PROVINCIA ASC ", nativeQuery = true)
    List<Object[]> porcentagemCoberturaServicosAguaProvincial();


    // FUNCIONAMENTO DE SISTEMAS DE AGUA (% FUNCIONAM E % NÃO FUNCIONAM)
    @Query(value = "SELECT p.ID_PROVINCIA ,  " +
        "             ROUND((( ((SELECT    COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)     " +
        "                   FROM    sisas.sistema_agua s      " +
        "                   WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA     " +
        "                     AND    s.POSSUI_SISTEMA_AGUA = 1     " +
        "                     AND    s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'     " +
        "              ))  *100)/ (SELECT    COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)     " +
        "                   FROM    sisas.sistema_agua s      " +
        "                   WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA     " +
        "                     AND    s.POSSUI_SISTEMA_AGUA = 1     " +
        "              )),2) PercentagemQueFuncionam,  " +
        "               ROUND((( ((SELECT    COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)    " +
        "                    FROM    sisas.sistema_agua s      " +
        "                    WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA     " +
        "                    AND    s.POSSUI_SISTEMA_AGUA = 1                  " +
        "                    AND    s.estado_funcionamento_sistema =  'Não está em funcionamento'  " +
        "               ))  *100)/ (SELECT    COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)     " +
        "                    FROM    sisas.sistema_agua s      " +
        "                    WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA     " +
        "                      AND    s.POSSUI_SISTEMA_AGUA = 1     " +
        "               )),2) PercentagemQueNaoFuncionam   " +
        "        FROM sisas.sistema_agua s     " +
        "        INNER JOIN sisas.provincia p on s.ID_PROVINCIA = p.ID_PROVINCIA     " +
        "        WHERE  s.POSSUI_SISTEMA_AGUA = 1     " +
        "        GROUP BY p.NM_PROVINCIA, p.ID_PROVINCIA  " +
        "        ORDER BY p.ID_PROVINCIA  ASC ", nativeQuery = true)
    List<Object[]> porcentagemSistemasAguaProvincial();



}
