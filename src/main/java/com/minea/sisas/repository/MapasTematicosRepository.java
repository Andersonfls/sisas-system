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
    List<Object[]> porcentagemCoberturaServicosAguaNacional();

    @Query(value = "SELECT  m.ID_MUNICIPIO, " +
        "    m.NM_MUNICIPIO," +
        "        c.NM_COMUNA," +
        "        c.ID_COMUNA," +
        "      ROUND((( ((SELECT ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/p.populacao)" +
        " FROM sisas.sistema_agua s " +
        " WHERE s.ID_PROVINCIA = p.ID_PROVINCIA" +
        "  AND s.ID_MUNICIPIO = m.ID_MUNICIPIO" +
        "  AND s.ID_COMUNA = c.ID_COMUNA" +
        "  AND s.POSSUI_SISTEMA_AGUA = 1" +
        "   )))),2) PercentagemCobertura" +
        " FROM sisas.sistema_agua s" +
        "    INNER JOIN sisas.provincia p ON s.ID_PROVINCIA = p.ID_PROVINCIA" +
        "    INNER JOIN sisas.municipio m ON  p.ID_PROVINCIA = m.ID_PROVINCIA " +
        "    INNER JOIN sisas.comuna c ON  s.ID_COMUNA = c.ID_COMUNA" +
        "    WHERE s.ID_PROVINCIA = :provinciaId ", nativeQuery = true)
    List<Object[]> porcentagemCoberturaServicosAguaPorProvincia(@Param("provinciaId") Long provinciaId);


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
    List<Object[]> porcentagemSistemasAguaNacional();

    @Query(value = "SELECT       p.NM_PROVINCIA,      " +
        "             m.NM_MUNICIPIO, m.ID_MUNICIPIO,       " +
        "             (      " +
        "              SELECT   COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)     " +
        "                 FROM   sisas.sistema_agua s      " +
        "                 WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA     " +
        "                 AND    s.POSSUI_SISTEMA_AGUA = 1     " +
        "              ) NrSistemas,  " +
        "         " +
        "               (      " +
        "              SELECT   COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)     " +
        "                 FROM   sisas.sistema_agua s      " +
        "                 WHERE   s.ID_PROVINCIA = p.ID_PROVINCIA     " +
        "                   AND   s.POSSUI_SISTEMA_AGUA = 1     " +
        "                      AND   s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'     " +
        "              ) NrSistemasFuncionam,      " +
        "              (      " +
        "              SELECT   COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)     " +
        "                 FROM   sisas.sistema_agua s     " +
        "                 WHERE   s.ID_PROVINCIA = p.ID_PROVINCIA     " +
        "                   AND   s.POSSUI_SISTEMA_AGUA = 1     " +
        "                      AND   s.estado_funcionamento_sistema =  'Não está em funcionamento'     " +
        "              )  NrSistemasNaoFuncionam,  " +
        "               " +
        "             ROUND((( ((SELECT    COALESCE(COUNT(POSSUI_SISTEMA_AGUA),2)      " +
        "                         FROM     sisas.sistema_agua s       " +
        "                         WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA      " +
        "                         AND      s.ID_MUNICIPIO = m.ID_MUNICIPIO      " +
        "                         AND      s.POSSUI_SISTEMA_AGUA = 1      " +
        "                         AND      s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'      " +
        "                ))  *100)/ (SELECT      COALESCE(COUNT(POSSUI_SISTEMA_AGUA),2)      " +
        "                         FROM     sisas.sistema_agua s       " +
        "                         WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA      " +
        "                         AND      s.ID_MUNICIPIO = m.ID_MUNICIPIO      " +
        "                         AND      s.POSSUI_SISTEMA_AGUA = 1      " +
        "                )),2) PercentagemQueFuncionam,    " +
        "        " +
        "                ROUND((( ((SELECT      COALESCE(COUNT(POSSUI_SISTEMA_AGUA),2)      " +
        "                         FROM      sisas.sistema_agua s       " +
        "                         WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA      " +
        "                         AND      s.ID_MUNICIPIO = m.ID_MUNICIPIO      " +
        "                         AND      s.POSSUI_SISTEMA_AGUA = 1      " +
        "                         AND      s.estado_funcionamento_sistema = 'Não está em funcionamento'      " +
        "                ))  *100)/ (SELECT      COALESCE(COUNT(POSSUI_SISTEMA_AGUA),2)      " +
        "                         FROM      sisas.sistema_agua s       " +
        "                         WHERE      s.ID_PROVINCIA = p.ID_PROVINCIA      " +
        "                     AND      s.ID_MUNICIPIO = m.ID_MUNICIPIO      " +
        "                           AND      s.POSSUI_SISTEMA_AGUA = 1      " +
        "                )),2) PercentagemQueNaoFuncionam " +
        "                 " +
        "        from sisas.sistema_agua s      " +
        "            inner join sisas.provincia p on s.ID_PROVINCIA = p.ID_PROVINCIA      " +
        "            inner join sisas.municipio m on s.ID_MUNICIPIO = m.ID_MUNICIPIO      " +
        "            where  s.POSSUI_SISTEMA_AGUA = 1 AND s.ID_PROVINCIA = :provinciaId     " +
        "        GROUP BY       " +
        "              p.NM_PROVINCIA, p.ID_PROVINCIA,       " +
        "              m.NM_MUNICIPIO, m.ID_MUNICIPIO", nativeQuery = true)
    List<Object[]> porcentagemSistemasAguaNacionalPorProvincia(@Param("provinciaId") Long provinciaId);


    @Query(value = "SELECT  m.ID_MUNICIPIO, " +
        "    m.NM_MUNICIPIO," +
        "        c.NM_COMUNA," +
        "        c.ID_COMUNA," +
        "      ROUND((( ((SELECT ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/p.populacao)" +
        " FROM sisas.sistema_agua s " +
        " WHERE s.ID_PROVINCIA = p.ID_PROVINCIA" +
        "  AND s.ID_MUNICIPIO = m.ID_MUNICIPIO" +
        "  AND s.ID_COMUNA = c.ID_COMUNA" +
        "  AND s.POSSUI_SISTEMA_AGUA = 1" +
        "   )))),2) PercentagemCobertura" +
        " FROM sisas.sistema_agua s" +
        "    INNER JOIN sisas.provincia p ON s.ID_PROVINCIA = p.ID_PROVINCIA" +
        "    INNER JOIN sisas.municipio m ON  p.ID_PROVINCIA = m.ID_PROVINCIA " +
        "    INNER JOIN sisas.comuna c ON  s.ID_COMUNA = c.ID_COMUNA", nativeQuery = true)
    List<Object[]> porcentagemCoberturaServicosAguaMunicipal();

    @Query(value = "SELECT       p.NM_PROVINCIA,      " +
        "             m.NM_MUNICIPIO, m.ID_MUNICIPIO,       " +
        "             (      " +
        "              SELECT   COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)     " +
        "                 FROM   sisas.sistema_agua s      " +
        "                 WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA     " +
        "                 AND    s.POSSUI_SISTEMA_AGUA = 1     " +
        "              ) NrSistemas,  " +
        "         " +
        "               (      " +
        "              SELECT   COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)     " +
        "                 FROM   sisas.sistema_agua s      " +
        "                 WHERE   s.ID_PROVINCIA = p.ID_PROVINCIA     " +
        "                   AND   s.POSSUI_SISTEMA_AGUA = 1     " +
        "                      AND   s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'     " +
        "              ) NrSistemasFuncionam,      " +
        "              (      " +
        "              SELECT   COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)     " +
        "                 FROM   sisas.sistema_agua s     " +
        "                 WHERE   s.ID_PROVINCIA = p.ID_PROVINCIA     " +
        "                   AND   s.POSSUI_SISTEMA_AGUA = 1     " +
        "                      AND   s.estado_funcionamento_sistema =  'Não está em funcionamento'     " +
        "              )  NrSistemasNaoFuncionam,  " +
        "               " +
        "             ROUND((( ((SELECT    COALESCE(COUNT(POSSUI_SISTEMA_AGUA),2)      " +
        "                         FROM     sisas.sistema_agua s       " +
        "                         WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA      " +
        "                         AND      s.ID_MUNICIPIO = m.ID_MUNICIPIO      " +
        "                         AND      s.POSSUI_SISTEMA_AGUA = 1      " +
        "                         AND      s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'      " +
        "                ))  *100)/ (SELECT      COALESCE(COUNT(POSSUI_SISTEMA_AGUA),2)      " +
        "                         FROM     sisas.sistema_agua s       " +
        "                         WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA      " +
        "                         AND      s.ID_MUNICIPIO = m.ID_MUNICIPIO      " +
        "                         AND      s.POSSUI_SISTEMA_AGUA = 1      " +
        "                )),2) PercentagemQueFuncionam,    " +
        "        " +
        "                ROUND((( ((SELECT      COALESCE(COUNT(POSSUI_SISTEMA_AGUA),2)      " +
        "                         FROM      sisas.sistema_agua s       " +
        "                         WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA      " +
        "                         AND      s.ID_MUNICIPIO = m.ID_MUNICIPIO      " +
        "                         AND      s.POSSUI_SISTEMA_AGUA = 1      " +
        "                         AND      s.estado_funcionamento_sistema = 'Não está em funcionamento'      " +
        "                ))  *100)/ (SELECT      COALESCE(COUNT(POSSUI_SISTEMA_AGUA),2)      " +
        "                         FROM      sisas.sistema_agua s       " +
        "                         WHERE      s.ID_PROVINCIA = p.ID_PROVINCIA      " +
        "                     AND      s.ID_MUNICIPIO = m.ID_MUNICIPIO      " +
        "                           AND      s.POSSUI_SISTEMA_AGUA = 1      " +
        "                )),2) PercentagemQueNaoFuncionam " +
        "                 " +
        "        from sisas.sistema_agua s      " +
        "            inner join sisas.provincia p on s.ID_PROVINCIA = p.ID_PROVINCIA      " +
        "            inner join sisas.municipio m on s.ID_MUNICIPIO = m.ID_MUNICIPIO      " +
        "            where  s.POSSUI_SISTEMA_AGUA = 1  " +
        "        GROUP BY       " +
        "              p.NM_PROVINCIA, p.ID_PROVINCIA,       " +
        "              m.NM_MUNICIPIO, m.ID_MUNICIPIO", nativeQuery = true)
    List<Object[]> porcentagemSistemasAguasMunicipal();

}
