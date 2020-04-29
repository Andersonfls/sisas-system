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
public interface RelatorioRepository extends JpaRepository<Provincia, Long>, JpaSpecificationExecutor<Provincia> {

    //COBERTURA SERVICOS DE AGUA (Nível Provincial)
    @Query(value = "SELECT p.NM_PROVINCIA,     " +
        "( SELECT  COUNT(m.id_municipio) FROM sisas.municipio m     " +
        "  WHERE m.ID_PROVINCIA = p.ID_PROVINCIA       " +
        ") NumeroMunicipios,     " +
        "( SELECT COUNT(c.ID_comuna)  FROM sisas.comuna c      " +
        "  WHERE m.id_municipio = c.id_municipio     " +
        ") NumeroComunas,     " +
        " (        SELECT      COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)      " +
        "                  FROM      sisas.sistema_agua s      " +
        "                  WHERE      s.ID_PROVINCIA = p.ID_PROVINCIA     " +
        "                     AND      s.POSSUI_SISTEMA_AGUA = 1     " +
        "              AND      s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'     " +
        "            ) NrSistemasFuncionam,     " +
        "      p.populacao,     " +
        "       ( SELECT       COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)     " +
        "                  FROM   sisas.sistema_agua s      " +
        "                  WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA     " +
        "                 AND      s.POSSUI_SISTEMA_AGUA = 1                    " +
        "         ) NrBeneficiariosSistemaAgua,        " +
        "      ROUND((( ((SELECT      ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/p.populacao)     " +
        "                  FROM      sisas.sistema_agua s      " +
        "                  WHERE      s.ID_PROVINCIA = p.ID_PROVINCIA     " +
        "                   AND      s.POSSUI_SISTEMA_AGUA = 1     " +
        "         )))),2) PercentagemCobertura      " +
        " FROM sisas.sistema_agua s     " +
        "    INNER JOIN sisas.provincia p on  s.ID_PROVINCIA = p.ID_PROVINCIA     " +
        "    INNER JOIN sisas.municipio m ON  s.ID_MUNICIPIO  = m.ID_MUNICIPIO      " +
        "    WHERE s.POSSUI_SISTEMA_AGUA = 1  AND s.ID_PROVINCIA = :provinciaId   " +
        "   GROUP BY      " +
        "       p.NM_PROVINCIA ,p.ID_PROVINCIA, m.ID_MUNICIPIO      " +
        "   ORDER BY     " +
        "       p.NM_PROVINCIA ASC ", nativeQuery = true)
    List<Object[]> coberturaServicosAguaProvincial(@Param("provinciaId") Long provinciaId);

    //COBERTURA SERVICOS DE AGUA (Nível Municipal)
    @Query(value = "SELECT p.NM_PROVINCIA,   " +
        "        m.NM_MUNICIPIO,   " +
        "      ( SELECT    COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)   " +
        "            FROM    sisas.sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "              AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND    s.POSSUI_SISTEMA_AGUA = 1   " +
        "        ) NrSistemas,    " +
        "         p.populacao,    " +
        "       ( SELECT     COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)   " +
        "            FROM   sisas.sistema_agua s    " +
        "            WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "             AND s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "            AND    s.POSSUI_SISTEMA_AGUA = 1    " +
        "       ) NrHabAcessoAgua,   " +
        "      ROUND((( ((SELECT    ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/p.populacao)   " +
        "            FROM    sisas.sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "              AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND    s.POSSUI_SISTEMA_AGUA = 1   " +
        "       )))),2) PercentagemCobertura   " +
        "FROM sisas.sistema_agua s   " +
        "    INNER JOIN sisas.provincia p on s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "    INNER JOIN sisas.municipio m on p.ID_PROVINCIA = m.ID_PROVINCIA    " +
        "    WHERE     s.POSSUI_SISTEMA_AGUA = 1  AND s.ID_PROVINCIA = :provinciaId    " +
        "GROUP BY    " +
        "       p.NM_PROVINCIA, p.ID_PROVINCIA,    " +
        "       m.NM_MUNICIPIO, m.ID_MUNICIPIO  " +
        " ORDER BY m.NM_MUNICIPIO ASC ", nativeQuery = true)
    List<Object[]> coberturaServicosAguaMunicipal(@Param("provinciaId") Long provinciaId);

    //COBERTURA SERVICOS DE AGUA (Nível Comunal)
    @Query(value ="SELECT p.NM_PROVINCIA, " +
        "      m.NM_MUNICIPIO, " +
        "        c.NM_COMUNA, " +
        "      (  " +
        "    SELECT  COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0) " +
        "      FROM  sisas.sistema_agua s  " +
        "      WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA " +
        "        AND  s.ID_MUNICIPIO = m.ID_MUNICIPIO " +
        "        AND  s.ID_COMUNA = c.ID_COMUNA " +
        "        AND  s.POSSUI_SISTEMA_AGUA = 1 " +
        "    ) NrSistemas, " +
        "      p.populacao, " +
        "       (  " +
        "    SELECT   COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0) " +
        "      FROM   sisas.sistema_agua s  " +
        "      WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA " +
        "           AND s.ID_MUNICIPIO = m.ID_MUNICIPIO " +
        "      AND  s.ID_COMUNA = c.ID_COMUNA " +
        "      AND  s.POSSUI_SISTEMA_AGUA = 1 " +
        "     ) NrHabAcessoAgua, " +
        "      ROUND((( ((SELECT  ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/p.populacao) " +
        "      FROM  sisas.sistema_agua s  " +
        "      WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA " +
        "        AND  s.ID_MUNICIPIO = m.ID_MUNICIPIO " +
        "        AND  s.ID_COMUNA = c.ID_COMUNA " +
        "        AND  s.POSSUI_SISTEMA_AGUA = 1 " +
        "     )))),2) PercentagemCobertura " +
        "from sisas.sistema_agua s " +
        "    inner join sisas.provincia p on s.ID_PROVINCIA = p.ID_PROVINCIA " +
        "     inner join sisas.municipio m on s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "     inner join sisas.comuna c on s.ID_COMUNA = c.ID_COMUNA " +
        "     WHERE s.ID_PROVINCIA = :provinciaId "+
        "GROUP BY  " +
        "       p.NM_PROVINCIA, p.ID_PROVINCIA,  " +
        "    m.NM_MUNICIPIO, m.ID_MUNICIPIO,  " +
        "    c.NM_COMUNA, c.ID_COMUNA  " +
        "order BY  " +
        "       p.NM_PROVINCIA, " +
        "    m.NM_MUNICIPIO, " +
        "    c.NM_COMUNA ", nativeQuery = true)
    List<Object[]> coberturaServicosAguaComunal(@Param("provinciaId") Long provinciaId);

    //BENEFICIARIOS BOMBA ENERGIA - PROVINCIAL
    @Query(value = "SELECT    p.NM_PROVINCIA,    " +
        "        p.populacao,    " +
        "          (     " +
        "      SELECT   COUNT(Esquema)    " +
        "         FROM   sisas.sistema_agua s     " +
        "         WHERE   s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "              AND   s.NM_TP_FONTE= 'Subterrânea'    " +
        "              AND   s.Esquema = 'Poço/cacimba melhorada'    " +
        "      ) NrPocvoMelhorado,     " +
        "      (     " +
        "         SELECT   COUNT(Esquema)    " +
        "         FROM   sisas.sistema_agua s     " +
        "         WHERE   s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "           AND   s.NM_TP_FONTE = 'Subterrânea'    " +
        "           AND   s.ESQUEMA = 'Furo'     " +
        "      ) Furo,    " +
        "      (     " +
        "         SELECT   COUNT(Esquema)    " +
        "         FROM   sisas.sistema_agua s     " +
        "         WHERE   s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "           AND   s.NM_TP_FONTE  = 'Subterrânea'    " +
        "           AND   s.ESQUEMA = 'Nascente'    " +
        "      ) Nascente,    " +
        "         (     " +
        "         SELECT   COUNT(NM_TP_BOMBA_ENERGIA)    " +
        "         FROM   sisas.sistema_agua s     " +
        "         WHERE   s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "              AND   s.NM_TP_FONTE= 'Subterrânea'    " +
        "           AND   s.NM_TP_BOMBA_ENERGIA  = 'Diesel/Motobomba'    " +
        "      ) NrSistemasAdiesel,    " +
        "         (     " +
        "         SELECT COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)    " +
        "         FROM   sisas.sistema_agua s     " +
        "           Where   s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "              AND     s.NM_TP_FONTE  = 'Subterrânea'    " +
        "              AND     s.NM_TP_BOMBA_ENERGIA  = 'Diesel/Motobomba'    " +
        "      ) QtPessoasAcessoBombaDiesel,    " +
        "      ROUND((( ((SELECT   COALESCE((sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA)*100),0)/p.populacao    " +
        "         FROM   sisas.sistema_agua s     " +
        "         WHERE   s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "              AND   s.NM_TP_FONTE= 'Subterrânea'    " +
        "              AND   s.NM_TP_BOMBA_ENERGIA  = 'Diesel/Motobomba'    " +
        "      )))),2) PercentagemAcessoDiesel,    " +
        "      (     " +
        "         SELECT   COUNT(NM_TP_BOMBA_ENERGIA)    " +
        "         FROM   sisas.sistema_agua s     " +
        "         WHERE   s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "              AND   s.NM_TP_FONTE= 'Subterrânea'    " +
        "           AND   s.NM_TP_BOMBA_ENERGIA  = 'Solar'    " +
        "      ) NrSistemasSolar,    " +
        "         (     " +
        "         SELECT    COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)    " +
        "         FROM   sisas.sistema_agua s     " +
        "           Where   s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "                 AND     s.NM_TP_FONTE = 'Subterrânea'    " +
        "                 AND     s.NM_TP_BOMBA_ENERGIA  = 'Solar'    " +
        "      ) QtPessoasAcessoBombaSolar,    " +
        "      ROUND((( ((SELECT   COALESCE((sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA)*100),0)/p.populacao    " +
        "         FROM   sisas.sistema_agua s                 " +
        "         WHERE   s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "              AND   s.NM_TP_FONTE= 'Subterrânea'    " +
        "              AND   s.NM_TP_BOMBA_ENERGIA  = 'Solar'    " +
        "      )))),2) PercentagemAcessoSolar,    " +
        "   (     " +
        "         SELECT   COUNT(NM_TP_BOMBA_ENERGIA)    " +
        "         FROM   sisas.sistema_agua s     " +
        "         WHERE   s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "              AND   s.NM_TP_FONTE= 'Subterrânea'    " +
        "           AND   s.NM_TP_BOMBA_ENERGIA  = 'Eólica'    " +
        "      ) NrSistemasEolica,    " +
        "         (     " +
        "         SELECT   COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)    " +
        "         FROM   sisas.sistema_agua s     " +
        "           Where   s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "              AND     s.NM_TP_FONTE = 'Subterrânea'    " +
        "              AND     s.NM_TP_BOMBA_ENERGIA  = 'Eólica'    " +
        "      ) QtPessoasAcessoBombaEolica,    " +
        "      ROUND((( ((SELECT   COALESCE((sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA)*100),0)/p.populacao    " +
        "         FROM   sisas.sistema_agua s     " +
        "         WHERE   s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "              AND   s.NM_TP_FONTE= 'Subterrânea'    " +
        "              AND   s.NM_TP_BOMBA_ENERGIA  = 'Eólica'    " +
        "      )))),2) PercentagemAcessoEolica,    " +
        "     (     " +
        "         SELECT   COUNT(NM_TP_BOMBA_ENERGIA)    " +
        "         FROM   sisas.sistema_agua s     " +
        "         WHERE   s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "              AND   s.NM_TP_FONTE= 'Subterrânea'    " +
        "           AND   s.NM_TP_BOMBA_ENERGIA  = 'Elétrica'    " +
        "      ) NrSistemasEletrica,    " +
        "         (     " +
        "         SELECT    COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)    " +
        "         FROM   sisas.sistema_agua s     " +
        "           Where   s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "              AND     s.NM_TP_FONTE = 'Subterrânea'    " +
        "              AND     s.NM_TP_BOMBA_ENERGIA  = 'Elétrica'    " +
        "      ) QtPessoasAcessoBombaEletrica,    " +
        "      ROUND((( ((SELECT   COALESCE((sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA)*100),0)/p.populacao    " +
        "         FROM   sisas.sistema_agua s     " +
        "         WHERE   s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "              AND   s.NM_TP_FONTE = 'Subterrânea'    " +
        "              AND   s.NM_TP_BOMBA_ENERGIA  = 'Elétrica'    " +
        "      )))),2) PercentagemAcessoEletrica,    " +
        "        (     " +
        "         SELECT   COUNT(NM_TP_BOMBA_ENERGIA)    " +
        "         FROM   sisas.sistema_agua s     " +
        "         WHERE   s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "              AND   s.NM_TP_FONTE= 'Subterrânea'    " +
        "           AND   s.NM_TP_BOMBA_ENERGIA  = 'Outros'    " +
        "      ) NrSistemasOutros,    " +
        "         (     " +
        "         SELECT    COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)    " +
        "         FROM   sisas.sistema_agua s     " +
        "           Where   s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "              AND     s.NM_TP_FONTE = 'Subterrânea'    " +
        "              AND     s.NM_TP_BOMBA_ENERGIA  = 'Outros'    " +
        "      ) QtPessoasAcessoOutros,    " +
        "      ROUND((( ((SELECT   COALESCE((sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA)*100),0)/p.populacao    " +
        "         FROM   sisas.sistema_agua s     " +
        "         WHERE   s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "              AND   s.NM_TP_FONTE= 'Subterrânea'    " +
        "              AND   s.NM_TP_BOMBA_ENERGIA  = 'Outros'    " +
        "      )))),2) PercentagemOutros    " +
        "from sisas.sistema_agua s    " +
        "     inner join sisas.provincia p on s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "     where s.POSSUI_SISTEMA_AGUA = 1   AND s.ID_PROVINCIA = :provinciaId   " +
        "GROUP BY     " +
        "       p.NM_PROVINCIA, p.ID_PROVINCIA     " +
        "order by     " +
        "       p.NM_PROVINCIA ", nativeQuery = true)
    List<Object[]> beneficiariosAguaBmbEnergiaProvincialQuery(@Param("provinciaId") Long provinciaId);

    //BENEFICIARIOS BOMBA ENERGIA - COMUNAL
    @Query(value = "SELECT     p.NM_PROVINCIA,  " +
        "        m.NM_MUNICIPIO,  " +
        "        c.NM_COMUNA,  " +
        "        c.populacao,  " +
        "          (   " +
        " SELECT COALESCE(COUNT(Esquema),0)  " +
        " FROM sisas.sistema_agua s   " +
        " WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA  " +
        "              AND s.NM_TP_FONTE= 'Subterrânea'  " +
        "              AND s.Esquema = 'Poço/cacimba melhorada'  " +
        "   ) NrPocvoMelhorado,   " +
        "      (   " +
        " SELECT COALESCE(COUNT(Esquema),0)  " +
        " FROM sisas.sistema_agua s   " +
        " WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA  " +
        "  AND s.NM_TP_FONTE = 'Subterrânea'  " +
        "  AND s.ESQUEMA = 'Furo'   " +
        ") Furo,  " +
        "      (   " +
        " SELECT COALESCE(COUNT(Esquema),0)  " +
        " FROM sisas.sistema_agua s   " +
        " WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA  " +
        "  AND s.NM_TP_FONTE  = 'Subterrânea'  " +
        "  AND s.ESQUEMA = 'Nascente'  " +
        ") Nascente,  " +
        "         (   " +
        " SELECT COALESCE(COUNT(NM_TP_BOMBA_ENERGIA),0)  " +
        " FROM sisas.sistema_agua s   " +
        " WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA  " +
        "              AND s.NM_TP_FONTE= 'Subterrânea'  " +
        "              AND s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')  " +
        "  AND s.NM_TP_BOMBA_ENERGIA  = 'Diesel/Motobomba'  " +
        ") NrSistemasAdiesel,  " +
        "         (   " +
        "   SELECT     COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)  " +
        " FROM   sisas.sistema_agua s   " +
        " Where   s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "              AND   s.ID_MUNICIPIO   = m.ID_MUNICIPIO   " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA  " +
        "              AND  s.NM_TP_FONTE  = 'Subterrânea'  " +
        "              AND s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')  " +
        "              AND     s.NM_TP_BOMBA_ENERGIA  = 'Diesel/Motobomba'  " +
        "   ) QtPessoasAcessoBombaDiesel,  " +
        "      ROUND((( ((SELECT    ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/c.populacao)  " +
        "   FROM    sisas.sistema_agua s   " +
        "   WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA  " +
        "              AND    s.NM_TP_FONTE= 'Subterrânea'  " +
        "              AND    s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')  " +
        "              AND    s.NM_TP_BOMBA_ENERGIA  = 'Diesel/Motobomba'  " +
        "       )))),2) PercentagemAcessoDiesel,  " +
        "      (   " +
        "   SELECT    COALESCE(COUNT(NM_TP_BOMBA_ENERGIA),0)  " +
        "   FROM    sisas.sistema_agua s   " +
        "   WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA  " +
        "              AND    s.NM_TP_FONTE= 'Subterrânea'  " +
        "              AND    s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')  " +
        "     AND    s.NM_TP_BOMBA_ENERGIA  = 'Solar'  " +
        "        ) NrSistemasSolar,  " +
        "         (   " +
        "           SELECT     COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)  " +
        "   FROM   sisas.sistema_agua s   " +
        "     Where   s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "                 AND  s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "                 AND   s.ID_COMUNA = c.ID_COMUNA  " +
        "                 AND  s.NM_TP_FONTE = 'Subterrânea'  " +
        "                 AND    s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')  " +
        "                 AND  s.NM_TP_BOMBA_ENERGIA  = 'Solar'  " +
        "       ) QtPessoasAcessoBombaSolar,  " +
        "      ROUND((( ((SELECT    ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/c.populacao)  " +
        "   FROM    sisas.sistema_agua s               " +
        "   WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA  " +
        "              AND    s.NM_TP_FONTE= 'Subterrânea'  " +
        "              AND    s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')  " +
        "              AND    s.NM_TP_BOMBA_ENERGIA  = 'Solar'  " +
        "       )))),2) PercentagemAcessoSolar,  " +
        "   (   " +
        "   SELECT    COALESCE(COUNT(NM_TP_BOMBA_ENERGIA),0)  " +
        "   FROM    sisas.sistema_agua s   " +
        "   WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA  " +
        "              AND    s.NM_TP_FONTE= 'Subterrânea'  " +
        "              AND    s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')  " +
        "     AND    s.NM_TP_BOMBA_ENERGIA  = 'Eólica'  " +
        "        ) NrSistemasEolica,  " +
        "         (   " +
        "           SELECT     COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)  " +
        "   FROM   sistema_agua s   " +
        "     Where   s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA  " +
        "              AND    s.NM_TP_FONTE = 'Subterrânea'  " +
        "              AND    s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')  " +
        "              AND   s.NM_TP_BOMBA_ENERGIA  = 'Eólica'  " +
        "       ) QtPessoasAcessoBombaEolica,  " +
        "      ROUND((( ((SELECT    ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/c.populacao)  " +
        " FROM    sisas.sistema_agua s   " +
        " WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA  " +
        "              AND    s.NM_TP_FONTE= 'Subterrânea'  " +
        "              AND    s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')  " +
        "              AND    s.NM_TP_BOMBA_ENERGIA  = 'Eólica'  " +
        "   )))),2) PercentagemAcessoEolica,  " +
        "     (   " +
        " SELECT    COALESCE(COUNT(NM_TP_BOMBA_ENERGIA),0)  " +
        " FROM    sisas.sistema_agua s   " +
        " WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA  " +
        "              AND    s.NM_TP_FONTE= 'Subterrânea'  " +
        "              AND    s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')  " +
        "  AND    s.NM_TP_BOMBA_ENERGIA  = 'Elétrica'  " +
        " ) NrSistemasEletrica,  " +
        "         (   " +
        "   SELECT     COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)  " +
        " FROM   sisas.sistema_agua s   " +
        "  Where   s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA  " +
        "              AND      s.NM_TP_FONTE = 'Subterrânea'  " +
        "              AND    s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')  " +
        "              AND     s.NM_TP_BOMBA_ENERGIA  = 'Elétrica'  " +
        "       ) QtPessoasAcessoBombaEletrica,  " +
        "      ROUND((( ((SELECT    ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/c.populacao)  " +
        " FROM sisas.sistema_agua s   " +
        "WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA  " +
        "              AND    s.NM_TP_FONTE = 'Subterrânea'  " +
        "              AND    s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')  " +
        "              AND    s.NM_TP_BOMBA_ENERGIA  = 'Elétrica'  " +
        "   )))),2) PercentagemAcessoEletrica,  " +
        "        (   " +
        " SELECT COALESCE(COUNT(NM_TP_BOMBA_ENERGIA),0)  " +
        " FROM sisas.sistema_agua s   " +
        " WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA  " +
        "              AND s.NM_TP_FONTE= 'Subterrânea'  " +
        "              AND s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')  " +
        "  AND s.NM_TP_BOMBA_ENERGIA  = 'Outros'  " +
        " ) NrSistemasOutros,  " +
        "         (   " +
        "   SELECT COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)  " +
        "  FROM   sisas.sistema_agua s   " +
        "  Where   s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA  " +
        "              AND s.NM_TP_FONTE = 'Subterrânea'  " +
        "              AND s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')  " +
        "              AND   s.NM_TP_BOMBA_ENERGIA  = 'Outros'  " +
        "   ) QtPessoasAcessoOutros,  " +
        "      ROUND((( ((SELECT    ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/c.populacao)  " +
        " FROM sisas.sistema_agua s   " +
        " WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND s.NM_TP_FONTE= 'Subterrânea'  " +
        "              AND s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')  " +
        "              AND s.NM_TP_BOMBA_ENERGIA  = 'Outros'  " +
        "   )))),2) PercentagemOutros  " +
        "from sisas.sistema_agua s  " +
        "     inner join sisas.provincia p on  s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "     inner join sisas.municipio m on  s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "     inner join sisas.comuna c on  s.ID_COMUNA = c.ID_COMUNA  " +
        "     where s.POSSUI_SISTEMA_AGUA = 1  AND s.ID_PROVINCIA = :provinciaId" +
        "GROUP BY   " +
        "       p.NM_PROVINCIA, p.ID_PROVINCIA,   " +
        "       m.NM_MUNICIPIO, m.ID_MUNICIPIO,   " +
        "       c.NM_COMUNA, c.ID_COMUNA"+
        " ORDER BY c.NM_COMUNA ASC", nativeQuery = true)
    List<Object[]> beneficiariosAguaBmbEnergiaComunalQuery(@Param("provinciaId") Long provinciaId);

    //SISTEMA AGUA BOMBA MANUAL - COMUNAL
    @Query(value = "SELECT  p.NM_PROVINCIA,  " +
        "     m.NM_MUNICIPIO,  " +
        "     c.NM_COMUNA,  " +
        "   (   " +
        "  SELECT COUNT(Esquema)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "     AND s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "     AND s.ID_COMUNA = c.ID_COMUNA  " +
        "              AND s.Esquema = 'Poço/cacimba melhorada'  " +
        "    ) PocoMelhorado,  " +
        "      (   " +
        "  SELECT COUNT(Esquema)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "     AND s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "     AND s.ID_COMUNA = c.ID_COMUNA  " +
        "              AND   s.NM_TIPO_BOMBA =  'Bombagem manual'  " +
        "              AND s.Esquema = 'Furo'  " +
        "    ) Furo,  " +
        " (   " +
        "  SELECT COUNT(Esquema)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "     AND s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "     AND s.ID_COMUNA = c.ID_COMUNA  " +
        "                AND   s.NM_TIPO_BOMBA =  'Bombagem manual'  " +
        "              AND s.Esquema = 'Nascente'  " +
        "    ) Nascente,  " +
        " (   " +
        "  SELECT COUNT(POSSUI_SISTEMA_AGUA)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "     AND s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "     AND s.ID_COMUNA = c.ID_COMUNA  " +
        "              AND   s.NM_TP_FONTE = 'Subterrânea'  " +
        "              AND   s.NM_TIPO_BOMBA =  'Bombagem manual'  " +
        "              AND s.NM_MODELO_BOMBA_MANUAL_UTILIZADA in ('Afridev','Vergnet','Volanta','India Mark II','Outros')  " +
        "    ) TotalSistemas,  " +
        "      (  " +
        "  SELECT COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "     AND s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "     AND s.ID_COMUNA = c.ID_COMUNA  " +
        "              AND   s.NM_TP_FONTE = 'Subterrânea'  " +
        "              AND   s.NM_TIPO_BOMBA =  'Bombagem manual'  " +
        "              AND s.NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Afridev'  " +
        "    ) NrSistemasAfridev,  " +
        "      (  " +
        "  SELECT COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "     AND s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "     AND s.ID_COMUNA = c.ID_COMUNA  " +
        "               AND   s.NM_TP_FONTE = 'Subterrânea'  " +
        "               AND   s.NM_TIPO_BOMBA =  'Bombagem manual'  " +
        "              AND s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'  " +
        "              AND s.NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Afridev'  " +
        "    ) NrSistemasAfridevFincionam,  " +
        "   (  " +
        "  SELECT COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "     AND s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "     AND s.ID_COMUNA = c.ID_COMUNA  " +
        "               AND   s.NM_TP_FONTE = 'Subterrânea'  " +
        "               AND   s.NM_TIPO_BOMBA =  'Bombagem manual'  " +
        "              AND s.estado_funcionamento_sistema = 'Não está em funcionamento'  " +
        "              AND s.NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Afridev'  " +
        "    ) NrSistemasAfridevNaoFuncionam,  " +
        "      (  " +
        "  SELECT COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "     AND s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "     AND s.ID_COMUNA = c.ID_COMUNA  " +
        "               AND   s.NM_TP_FONTE = 'Subterrânea'  " +
        "               AND   s.NM_TIPO_BOMBA =  'Bombagem manual'  " +
        "              AND s.NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Vergnet'  " +
        "    ) NrSistemasVergnet,  " +
        "      (  " +
        "  SELECT COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "     AND s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "     AND s.ID_COMUNA = c.ID_COMUNA  " +
        "               AND   s.NM_TP_FONTE = 'Subterrânea'  " +
        "               AND   s.NM_TIPO_BOMBA =  'Bombagem manual'  " +
        "              AND s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'  " +
        "              AND s.NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Vergnet'  " +
        "    ) NrSistemasVergnetFuncionam,  " +
        "      (  " +
        "  SELECT COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "     AND s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "     AND s.ID_COMUNA = c.ID_COMUNA  " +
        "               AND   s.NM_TP_FONTE = 'Subterrânea'  " +
        "               AND   s.NM_TIPO_BOMBA =  'Bombagem manual'  " +
        "              AND s.estado_funcionamento_sistema = 'Não está em funcionamento'  " +
        "              AND s.NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Vergnet'  " +
        "    ) NrSistemasVergnetNaoFuncionam,  " +
        "      (  " +
        "  SELECT COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "     AND s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "     AND s.ID_COMUNA = c.ID_COMUNA  " +
        "               AND   s.NM_TP_FONTE = 'Subterrânea'  " +
        "               AND   s.NM_TIPO_BOMBA =  'Bombagem manual'  " +
        "              AND s.NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Volanta'  " +
        "    ) NrSistemasVolanta,  " +
        "      (  " +
        "  SELECT COALESCE(COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA),0)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "     AND s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "     AND s.ID_COMUNA = c.ID_COMUNA  " +
        "               AND   s.NM_TP_FONTE = 'Subterrânea'  " +
        "               AND   s.NM_TIPO_BOMBA =  'Bombagem manual'  " +
        "              AND s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'  " +
        "              AND s.NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Volanta'  " +
        "    ) NrSistemasVolantaFuncionam,  " +
        "     (  " +
        "  SELECT COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "     AND s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "     AND s.ID_COMUNA = c.ID_COMUNA  " +
        "               AND   s.NM_TP_FONTE = 'Subterrânea'  " +
        "               AND   s.NM_TIPO_BOMBA =  'Bombagem manual'  " +
        "              AND s.estado_funcionamento_sistema = 'Não está em funcionamento'  " +
        "              AND s.NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Volanta'  " +
        "    ) NrSistemasVolantaNaoFuncionam,  " +
        "      (  " +
        "  SELECT COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "     AND s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "     AND s.ID_COMUNA = c.ID_COMUNA  " +
        "               AND   s.NM_TP_FONTE = 'Subterrânea'  " +
        "               AND   s.NM_TIPO_BOMBA =  'Bombagem manual'  " +
        "              AND s.NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'India Mark II'  " +
        "    ) NrSistemasIndiaMarkII,  " +
        "      (  " +
        "  SELECT COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "     AND s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "     AND s.ID_COMUNA = c.ID_COMUNA  " +
        "              AND   s.NM_TP_FONTE = 'Subterrânea'  " +
        "              AND   s.NM_TIPO_BOMBA =  'Bombagem manual'  " +
        "              AND s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'  " +
        "              AND s.NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'India Mark II'  " +
        "    ) NrSistemasIndiaMarkIIFuncionam,  " +
        "     (  " +
        "  SELECT COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "     AND s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "     AND s.ID_COMUNA = c.ID_COMUNA  " +
        "               AND   s.NM_TP_FONTE = 'Subterrânea'  " +
        "               AND   s.NM_TIPO_BOMBA =  'Bombagem manual'  " +
        "              AND s.estado_funcionamento_sistema = 'Não está em funcionamento'  " +
        "              AND s.NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'India Mark II'  " +
        "    )  NrSistemassIndiaMarkIINaoFuncionam,  " +
        " (  " +
        "  SELECT COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "     AND s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "     AND s.ID_COMUNA = c.ID_COMUNA  " +
        "               AND   s.NM_TP_FONTE = 'Subterrânea'  " +
        "               AND   s.NM_TIPO_BOMBA =  'Bombagem manual'  " +
        "              AND s.NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Outros'  " +
        "    ) NrSistemasOutros,  " +
        "      (  " +
        "  SELECT COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "     AND s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "     AND s.ID_COMUNA = c.ID_COMUNA  " +
        "               AND   s.NM_TP_FONTE = 'Subterrânea'  " +
        "               AND   s.NM_TIPO_BOMBA =  'Bombagem manual'  " +
        "              AND s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'  " +
        "              AND s.NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Outros'  " +
        "    ) NrSistemassOutrosFuncionam,  " +
        "       (  " +
        "  SELECT COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "     AND s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "     AND s.ID_COMUNA = c.ID_COMUNA  " +
        "               AND   s.NM_TP_FONTE = 'Subterrânea'  " +
        "               AND   s.NM_TIPO_BOMBA =  'Bombagem manual'  " +
        "              AND s.estado_funcionamento_sistema = 'Não está em funcionamento'  " +
        "              AND s.NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Outros'  " +
        "    )  NrSistemassOutrosNaoFuncionam  " +
        "from sisas.sistema_agua s  " +
        "     inner join sisas.provincia p on s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "     inner join sisas.municipio m on p.ID_PROVINCIA = m.ID_PROVINCIA   " +
        "     inner join sisas.comuna c on c.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "      where s.NM_TP_FONTE= 'Subterrânea'  " +
        "         AND   s.NM_TIPO_BOMBA =  'Bombagem manual'  " +
        "         AND   s.POSSUI_SISTEMA_AGUA = 1  " +
        "GROUP BY   " +
        "       p.NM_PROVINCIA, p.ID_PROVINCIA,  " +
        "       m.ID_MUNICIPIO,   " +
        "       c.ID_COMUNA   " +
        "order BY   " +
        "       p.NM_PROVINCIA,  " +
        "       m.ID_MUNICIPIO,   " +
        "       c.ID_COMUNA, p.ID_PROVINCIA  ", nativeQuery = true)
    List<Object[]> sistemaAguaBmbManualComunalQuery();

    //SISTEMA AGUA BOMBA GRAVIDADE - COMUNAL
    @Query(value = "SELECT      p.NM_PROVINCIA,    " +
        "         m.NM_MUNICIPIO,    " +
        "         c.NM_COMUNA,    " +
        "          (     " +
        "          SELECT     COALESCE(COUNT(ESQUEMA),0)    " +
        "               FROM     sisas.sistema_agua s     " +
        "               WHERE     s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "                 AND     s.ID_MUNICIPIO = m.ID_MUNICIPIO    " +
        "                 AND     s.ID_COMUNA = c.ID_COMUNA    " +
        "                 AND     s.POSSUI_SISTEMA_AGUA = 1    " +
        "              AND   s.NM_TP_FONTE= 'Subterrânea'    " +
        "                 AND   s.ESQUEMA = 'Poço/cacimba melhorada'    " +
        "        ) TotalPocoMelhorado,    " +
        "       (     " +
        "          SELECT     COALESCE(COUNT(ESQUEMA),0)    " +
        "               FROM     sisas.sistema_agua s     " +
        "               WHERE     s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "                 AND     s.ID_MUNICIPIO = m.ID_MUNICIPIO    " +
        "                 AND     s.ID_COMUNA = c.ID_COMUNA    " +
        "              AND   s.NM_TP_FONTE= 'Subterrânea'      " +
        "                  AND     s.POSSUI_SISTEMA_AGUA = 1    " +
        "                 AND   s.ESQUEMA = 'Furo'    " +
        "        ) TotalFuro,    " +
        "      (     " +
        "          SELECT     COALESCE(COUNT(ESQUEMA),0)    " +
        "               FROM     sisas.sistema_agua s     " +
        "               WHERE     s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "                 AND     s.ID_MUNICIPIO = m.ID_MUNICIPIO    " +
        "                 AND     s.ID_COMUNA = c.ID_COMUNA    " +
        "              AND   s.NM_TP_FONTE= 'Subterrânea'      " +
        "                  AND     s.POSSUI_SISTEMA_AGUA = 1    " +
        "                 AND ESQUEMA = 'Nascente'    " +
        "        ) TotalNascente,    " +
        "      (     " +
        "          SELECT     COALESCE(COUNT(s.POSSUI_SISTEMA_AGUA),0)    " +
        "               FROM     sisas.sistema_agua s     " +
        "               WHERE     s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "               AND     s.ID_MUNICIPIO = m.ID_MUNICIPIO    " +
        "                 AND     s.ID_COMUNA = c.ID_COMUNA    " +
        "              AND   s.NM_TP_FONTE= 'Subterrânea'      " +
        "              AND   s.NM_TIPO_BOMBA IN('Sistema por gravidade','Outros')    " +
        "                 AND     s.POSSUI_SISTEMA_AGUA = 1    " +
        "        ) TotalSistemas,    " +
        "      (     " +
        "          SELECT     COALESCE(COUNT(NM_TIPO_BOMBA),0)    " +
        "               FROM     sisas.sistema_agua s     " +
        "               WHERE     s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "              AND     s.ID_MUNICIPIO = m.ID_MUNICIPIO    " +
        "                 AND     s.ID_COMUNA = c.ID_COMUNA    " +
        "              AND   s.NM_TP_FONTE= 'Subterrânea'      " +
        "                 AND     s.POSSUI_SISTEMA_AGUA = 1    " +
        "              AND   s.NM_TIPO_BOMBA = 'Sistema por gravidade'    " +
        "        ) TotaldBomaGravidade,    " +
        "      (     " +
        "          SELECT     COALESCE(COUNT(NM_TIPO_BOMBA),0)    " +
        "               FROM     sisas.sistema_agua s     " +
        "               WHERE     s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "                 AND     s.ID_MUNICIPIO = m.ID_MUNICIPIO    " +
        "                 AND     s.ID_COMUNA = c.ID_COMUNA    " +
        "                 AND     s.POSSUI_SISTEMA_AGUA = 1    " +
        "              AND   s.NM_TP_FONTE= 'Subterrânea'      " +
        "              AND   s.NM_TIPO_BOMBA = 'Sistema por gravidade'    " +
        "              AND     s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'    " +
        "        ) TotaldBomaGravidadeFunciona,    " +
        "      (     " +
        "          SELECT     COALESCE(COUNT(NM_TIPO_BOMBA),0)    " +
        "               FROM     sisas.sistema_agua s     " +
        "               WHERE     s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "                 AND     s.ID_MUNICIPIO = m.ID_MUNICIPIO    " +
        "                 AND     s.ID_COMUNA = c.ID_COMUNA    " +
        "                 AND     s.POSSUI_SISTEMA_AGUA = 1    " +
        "              AND   s.NM_TP_FONTE= 'Subterrânea'      " +
        "              AND   s.NM_TIPO_BOMBA = 'Sistema por gravidade'    " +
        "              AND     s.estado_funcionamento_sistema = 'Não está em funcionamento'    " +
        "        ) TotaldBomaGravidadeNaoFunciona,    " +
        "      (     " +
        "          SELECT     COALESCE(COUNT(NM_TIPO_BOMBA),0)    " +
        "               FROM     sisas.sistema_agua s     " +
        "               WHERE     s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "                 AND     s.ID_MUNICIPIO = m.ID_MUNICIPIO    " +
        "                 AND     s.ID_COMUNA = c.ID_COMUNA    " +
        "                 AND     s.POSSUI_SISTEMA_AGUA = 1    " +
        "              AND   s.NM_TP_FONTE= 'Subterrânea'      " +
        "              AND   s.NM_TIPO_BOMBA = 'Outros'    " +
        "        ) TotalOutros,    " +
        "      (     " +
        "          SELECT     COALESCE(COUNT(NM_TIPO_BOMBA),0)     " +
        "               FROM     sisas.sistema_agua s     " +
        "               WHERE     s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "                 AND     s.ID_MUNICIPIO = m.ID_MUNICIPIO    " +
        "                 AND     s.ID_COMUNA = c.ID_COMUNA    " +
        "                 AND     s.POSSUI_SISTEMA_AGUA = 1    " +
        "              AND   s.NM_TIPO_BOMBA = 'Outros'    " +
        "              AND   s.NM_TP_FONTE= 'Subterrânea'      " +
        "              AND     s.estado_funcionamento_sistema =  'Está em funcionamento (Bom)'    " +
        "        ) TotalOutrosFunciona,    " +
        "            (     " +
        "          SELECT     COALESCE(COUNT(NM_TIPO_BOMBA),0)    " +
        "               FROM     sisas.sistema_agua s     " +
        "               WHERE     s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "                 AND     s.ID_MUNICIPIO = m.ID_MUNICIPIO    " +
        "                 AND     s.ID_COMUNA = c.ID_COMUNA    " +
        "                 AND     s.POSSUI_SISTEMA_AGUA = 1    " +
        "              AND   s.NM_TIPO_BOMBA = 'Outros'    " +
        "              AND   s.NM_TP_FONTE= 'Subterrânea'      " +
        "              AND     s.estado_funcionamento_sistema = 'Não está em funcionamento'    " +
        "        ) TotalOutrosNaoFunciona    " +
        "from sisas.sistema_agua s    " +
        "     inner join sisas.provincia p on s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "     inner join sisas.municipio m on p.ID_PROVINCIA = m.ID_PROVINCIA     " +
        "     inner join sisas.comuna c on c.ID_MUNICIPIO = m.ID_MUNICIPIO     " +
        "     WHERE s.POSSUI_SISTEMA_AGUA = 1 AND s.ID_PROVINCIA = :provinciaId   " +
        "            AND   s.NM_TP_FONTE= 'Subterrânea'      " +
        "GROUP BY     " +
        "       p.NM_PROVINCIA,    " +
        "       m.ID_MUNICIPIO,     " +
        "       c.ID_COMUNA     " +
        " ORDER BY c.NM_COMUNA ASC          " +
        "       ", nativeQuery = true)
    List<Object[]> sistemaAguaBmbGravidadeComunal(@Param("provinciaId") Long provinciaId);

    //SISTEMA AGUA SUPERFICIAL OPCAO TECNICA - COMUNAL
    @Query(value = "SELECT            p.NM_PROVINCIA,            " +
        "               m.NM_MUNICIPIO,            " +
        "               c.NM_COMUNA,            " +
        "          (             " +
        "                      SELECT           COUNT(POSSUI_SISTEMA_AGUA)            " +
        "                                 FROM           sisas.sistema_agua s             " +
        "                                 WHERE           s.ID_PROVINCIA = p.ID_PROVINCIA            " +
        "                                   AND           s.ID_MUNICIPIO = m.ID_MUNICIPIO            " +
        "              AND   s.NM_TP_FONTE  = 'Superficial'            " +
        "                                   AND           s.ID_COMUNA = c.ID_COMUNA            " +
        "                                   AND           s.POSSUI_SISTEMA_AGUA = 1            " +
        "              ) TotalSistemas,            " +
        "    (             " +
        "                      SELECT           COUNT(NM_TIPO_BOMBA)             " +
        "                                 FROM           sisas.sistema_agua s             " +
        "                                 WHERE           s.ID_PROVINCIA = p.ID_PROVINCIA            " +
        "                                   AND           s.ID_MUNICIPIO = m.ID_MUNICIPIO            " +
        "                                   AND           s.ID_COMUNA = c.ID_COMUNA            " +
        "                                   AND           s.POSSUI_SISTEMA_AGUA = 1            " +
        "              AND   s.NM_TP_FONTE  = 'Superficial'            " +
        "              AND   s.NM_TIPO_BOMBA = 'Bomba eléctrica'            " +
        "              ) QtdBomaEletrica,             " +
        "      (             " +
        "                      SELECT           COUNT(NM_TIPO_BOMBA)             " +
        "                                 FROM           sisas.sistema_agua s             " +
        "                                 WHERE           s.ID_PROVINCIA = p.ID_PROVINCIA            " +
        "             AND           s.ID_MUNICIPIO = m.ID_MUNICIPIO            " +
        "             AND           s.ID_COMUNA = c.ID_COMUNA            " +
        "              AND   s.NM_TP_FONTE  = 'Superficial'            " +
        "                                   AND           s.POSSUI_SISTEMA_AGUA = 1            " +
        "              AND   s.NM_TIPO_BOMBA = 'Bomba eléctrica'            " +
        "              AND           s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'            " +
        "              ) QtdBomaEletricaPossuiSistema,             " +
        "             (             " +
        "                      SELECT           COUNT(NM_TIPO_BOMBA)             " +
        "                                 FROM           sisas.sistema_agua s             " +
        "                                 WHERE           s.ID_PROVINCIA = p.ID_PROVINCIA            " +
        "                                   AND           s.ID_MUNICIPIO = m.ID_MUNICIPIO            " +
        "                                   AND           s.ID_COMUNA = c.ID_COMUNA            " +
        "                                   AND           s.POSSUI_SISTEMA_AGUA = 1            " +
        "              AND   s.NM_TP_FONTE  = 'Superficial'            " +
        "              AND   s.NM_TIPO_BOMBA = 'Bomba eléctrica'            " +
        "              AND           s.estado_funcionamento_sistema = 'Não está em funcionamento'            " +
        "              ) QtdBomaEletricaNaoPossuiSistema,            " +
        "      (             " +
        "                      SELECT           COUNT(NM_TIPO_BOMBA)             " +
        "                                 FROM           sisas.sistema_agua s             " +
        "                                 WHERE           s.ID_PROVINCIA = p.ID_PROVINCIA            " +
        "                                   AND           s.ID_MUNICIPIO = m.ID_MUNICIPIO            " +
        "                                   AND           s.ID_COMUNA = c.ID_COMUNA            " +
        "                                   AND           s.POSSUI_SISTEMA_AGUA = 1            " +
        "              AND   s.NM_TP_FONTE  = 'Superficial'            " +
        "              AND   s.NM_TIPO_BOMBA = 'Bomba a diesel'            " +
        "              ) TotalBomaDiesel,            " +
        "      (             " +
        "                      SELECT           COUNT(NM_TIPO_BOMBA)             " +
        "                                 FROM           sisas.sistema_agua s             " +
        "                                 WHERE           s.ID_PROVINCIA = p.ID_PROVINCIA            " +
        "                                   AND           s.ID_MUNICIPIO = m.ID_MUNICIPIO            " +
        "                                   AND           s.ID_COMUNA = c.ID_COMUNA            " +
        "                                   AND           s.POSSUI_SISTEMA_AGUA = 1            " +
        "              AND   s.NM_TIPO_BOMBA = 'Bomba a diesel'            " +
        "              AND   s.NM_TP_FONTE  = 'Superficial'            " +
        "              AND           s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'            " +
        "              ) TotalBomaDieselFunciona,            " +
        "      (             " +
        "                      SELECT           COUNT(NM_TIPO_BOMBA)             " +
        "                                 FROM           sisas.sistema_agua s             " +
        "                                 WHERE           s.ID_PROVINCIA = p.ID_PROVINCIA            " +
        "                                   AND           s.ID_MUNICIPIO = m.ID_MUNICIPIO            " +
        "                                   AND           s.ID_COMUNA = c.ID_COMUNA            " +
        "                                   AND           s.POSSUI_SISTEMA_AGUA = 1            " +
        "                                   AND   s.NM_TP_FONTE  = 'Superficial'            " +
        "              AND   s.NM_TIPO_BOMBA = 'Bomba a diesel'            " +
        "              AND           s.estado_funcionamento_sistema = 'Não está em funcionamento'            " +
        "              ) TotalBomaDieselNaoFunciona,            " +
        "      (             " +
        "                      SELECT           COUNT(NM_TIPO_BOMBA)             " +
        "                                 FROM           sisas.sistema_agua s             " +
        "                                 WHERE           s.ID_PROVINCIA = p.ID_PROVINCIA            " +
        "                                   AND           s.ID_MUNICIPIO = m.ID_MUNICIPIO            " +
        "                                   AND           s.ID_COMUNA = c.ID_COMUNA            " +
        "                                   AND           s.POSSUI_SISTEMA_AGUA = 1            " +
        "                                   AND   s.NM_TP_FONTE  = 'Superficial'            " +
        "              AND   s.NM_TIPO_BOMBA = 'Bomba por gravidade'            " +
        "              ) TotalBomaGravidade,            " +
        "      (             " +
        "                      SELECT           COUNT(NM_TIPO_BOMBA)             " +
        "                                 FROM           sisas.sistema_agua s             " +
        "                                 WHERE           s.ID_PROVINCIA = p.ID_PROVINCIA            " +
        "                                   AND           s.ID_MUNICIPIO = m.ID_MUNICIPIO            " +
        "                                   AND           s.ID_COMUNA = c.ID_COMUNA            " +
        "                                   AND           s.POSSUI_SISTEMA_AGUA = 1            " +
        "              AND   s.NM_TIPO_BOMBA = 'Bomba por gravidade'            " +
        "                                   AND   s.NM_TP_FONTE  = 'Superficial'            " +
        "              AND           s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'            " +
        "              ) TotalBomaGravidadeFunciona,            " +
        "      (             " +
        "                      SELECT           COUNT(NM_TIPO_BOMBA)             " +
        "                                 FROM           sisas.sistema_agua s             " +
        "                                 WHERE           s.ID_PROVINCIA = p.ID_PROVINCIA            " +
        "              AND           s.ID_MUNICIPIO = m.ID_MUNICIPIO            " +
        "              AND           s.ID_COMUNA = c.ID_COMUNA            " +
        "              AND           s.POSSUI_SISTEMA_AGUA = 1            " +
        "              AND   s.NM_TIPO_BOMBA = 'Bomba por gravidade'            " +
        "                                   AND   s.NM_TP_FONTE  = 'Superficial'            " +
        "              AND           s.estado_funcionamento_sistema = 'Não está em funcionamento'            " +
        "              ) TotalBomaGravidadeNaoFunciona            " +
        " from sisas.sistema_agua s            " +
        "     inner join sisas.provincia p on s.ID_PROVINCIA = p.ID_PROVINCIA            " +
        "     inner join sisas.municipio m on s.ID_MUNICIPIO = m.ID_MUNICIPIO             " +
        "     inner join sisas.comuna c on s.ID_COMUNA = c.ID_COMUNA            " +
        " WHERE s.ID_PROVINCIA = :provinciaId"+
        " GROUP BY             " +
        "       p.NM_PROVINCIA, p.ID_PROVINCIA,             " +
        "       m.ID_MUNICIPIO, m.ID_MUNICIPIO,             " +
        "       c.ID_COMUNA " +
        " ORDER BY c.NM_COMUNA ASC ", nativeQuery = true)
    List<Object[]> sistemaAguaSuperOpcaoTecnicaComunal(@Param("provinciaId") Long provinciaId);

    //FUNCIONAMENTO DE SISTEMA DE AGUA E CHAFARIZES
    @Query(value = "SELECT    p.NM_PROVINCIA,    " +
        "      (     " +
        "      SELECT   COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)    " +
        "         FROM   sisas.sistema_agua s     " +
        "         WHERE   s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "           AND   s.POSSUI_SISTEMA_AGUA = 1    " +
        "      ) NrSistemas,     " +
        "      (     " +
        "      SELECT   COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)    " +
        "         FROM   sisas.sistema_agua s     " +
        "         WHERE   s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "           AND   s.POSSUI_SISTEMA_AGUA = 1    " +
        "              AND   s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'    " +
        "      ) NrSistemasFuncionam,     " +
        "      (     " +
        "      SELECT   COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)    " +
        "         FROM   sisas.sistema_agua s     " +
        "         WHERE   s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "           AND   s.POSSUI_SISTEMA_AGUA = 1    " +
        "              AND   s.estado_funcionamento_sistema =  'Não está em funcionamento'    " +
        "      )  NrSistemasNaoFuncionam,    " +
        "      ROUND((( ((SELECT   COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)    " +
        "         FROM   sisas.sistema_agua s     " +
        "         WHERE   s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "           AND   s.POSSUI_SISTEMA_AGUA = 1    " +
        "              AND   s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'    " +
        "      ))  *100)/ (SELECT   COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)    " +
        "         FROM   sisas.sistema_agua s     " +
        "         WHERE   s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "           AND   s.POSSUI_SISTEMA_AGUA = 1    " +
        "      )),2) PercentagemQueFuncionam,    " +
        "    (     " +
        "      SELECT   COALESCE(SUM(qtd_chafarises_existentes),0)    " +
        "         FROM   sisas.sistema_agua s     " +
        "         WHERE   s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "           AND   s.POSSUI_SISTEMA_AGUA = 1    " +
        "      )  QtddeChafarizesExistentes,       " +
        "      (     " +
        "      SELECT   COALESCE(SUM(QTD_CHAFARISES_FUNCIONANDO),0)    " +
        "         FROM   sisas.sistema_agua s     " +
        "         WHERE   s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "           AND   s.POSSUI_SISTEMA_AGUA = 1    " +
        "      )  QtddeChafarizesQueFuncionam,       " +
        "      (     " +
        "      SELECT   COALESCE(SUM(qtd_chafarises_existentes),0)-COALESCE(SUM(QTD_CHAFARISES_FUNCIONANDO),0)    " +
        "         FROM   sisas.sistema_agua s     " +
        "         WHERE   s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "           AND   s.POSSUI_SISTEMA_AGUA = 1    " +
        "      )  QtddeChafarizesQueNaoFuncionam,       " +
        "       ROUND((( ((SELECT   format(SUM(qtd_chafarises_existentes),2)-format(SUM(QTD_CHAFARISES_FUNCIONANDO),2)    " +
        "         FROM   sisas.sistema_agua s     " +
        "         WHERE   s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "             AND   s.POSSUI_SISTEMA_AGUA = 1    " +
        "      ))  *100)/ (SELECT   format(sum(qtd_chafarises_existentes),2)    " +
        "         FROM   sisas.sistema_agua s     " +
        "         WHERE   s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1    " +
        "      )),2) PercentagemQueNaoFuncionam    " +
        "from sisas.sistema_agua s    " +
        "     inner join sisas.provincia p on s.ID_PROVINCIA = p.ID_PROVINCIA    " +
        "     where  s.POSSUI_SISTEMA_AGUA = 1 AND s.ID_PROVINCIA = :provinciaId   " +
        "GROUP BY     " +
        "       p.NM_PROVINCIA,p.ID_PROVINCIA"+
        " ORDER BY p.NM_PROVINCIA ASC ", nativeQuery = true)
    List<Object[]> funcionamentoAguaChafarizesProvincial(@Param("provinciaId") Long provinciaId);

    @Query(value = "SELECT       p.NM_PROVINCIA,     " +
        "        m.NM_MUNICIPIO,     " +
        "          (      " +
        "            SELECT      COALESCE(COUNT(POSSUI_SISTEMA_AGUA),2)     " +
        "                  FROM      sisas.sistema_agua s      " +
        "                  WHERE      s.ID_PROVINCIA = p.ID_PROVINCIA     " +
        "              AND      s.ID_MUNICIPIO = m.ID_MUNICIPIO     " +
        "                    AND      s.POSSUI_SISTEMA_AGUA = 1     " +
        "         ) NrSistemas,      " +
        "        (      " +
        "            SELECT      COALESCE(COUNT(POSSUI_SISTEMA_AGUA),2)     " +
        "                  FROM      sisas.sistema_agua s      " +
        "                  WHERE      s.ID_PROVINCIA = p.ID_PROVINCIA     " +
        "               AND      s.ID_MUNICIPIO = m.ID_MUNICIPIO     " +
        "                    AND      s.POSSUI_SISTEMA_AGUA = 1     " +
        "              AND      s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'     " +
        "         ) NrSistemasFuncionam,      " +
        "      (      " +
        "            SELECT      COALESCE(COUNT(POSSUI_SISTEMA_AGUA),2)     " +
        "                  FROM      sisas.sistema_agua s      " +
        "                  WHERE      s.ID_PROVINCIA = p.ID_PROVINCIA     " +
        "              AND      s.ID_MUNICIPIO = m.ID_MUNICIPIO     " +
        "                    AND      s.POSSUI_SISTEMA_AGUA = 1     " +
        "              AND      s.estado_funcionamento_sistema =  'Não está em funcionamento'     " +
        "         )  NrSistemasNaoFuncionam,     " +
        "      ROUND((( ((SELECT      COALESCE(COUNT(POSSUI_SISTEMA_AGUA),2)     " +
        "                  FROM      sisas.sistema_agua s      " +
        "                  WHERE      s.ID_PROVINCIA = p.ID_PROVINCIA     " +
        "              AND      s.ID_MUNICIPIO = m.ID_MUNICIPIO     " +
        "                    AND      s.POSSUI_SISTEMA_AGUA = 1     " +
        "              AND      s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'     " +
        "         ))  *100)/ (SELECT      COALESCE(COUNT(POSSUI_SISTEMA_AGUA),2)     " +
        "                  FROM      sisas.sistema_agua s      " +
        "                  WHERE      s.ID_PROVINCIA = p.ID_PROVINCIA     " +
        "              AND      s.ID_MUNICIPIO = m.ID_MUNICIPIO     " +
        "                    AND      s.POSSUI_SISTEMA_AGUA = 1     " +
        "         )),2) PercentagemQueFuncionam,     " +
        "      (      " +
        "            SELECT      COALESCE(SUM(qtd_chafarises_existentes),2)     " +
        "                  FROM      sisas.sistema_agua s      " +
        "                  WHERE      s.ID_PROVINCIA = p.ID_PROVINCIA     " +
        "              AND      s.ID_MUNICIPIO = m.ID_MUNICIPIO     " +
        "                    AND      s.POSSUI_SISTEMA_AGUA = 1     " +
        "         )  QtddeChafarizesExistentes,        " +
        "      (      " +
        "            SELECT      COALESCE(SUM(QTD_CHAFARISES_FUNCIONANDO),2)     " +
        "                  FROM      sisas.sistema_agua s      " +
        "                  WHERE      s.ID_PROVINCIA = p.ID_PROVINCIA     " +
        "              AND      s.ID_MUNICIPIO = m.ID_MUNICIPIO     " +
        "                    AND      s.POSSUI_SISTEMA_AGUA = 1     " +
        "         )  QtddeChafarizesQueFuncionam,        " +
        "      (      " +
        "            SELECT      COALESCE(SUM(qtd_chafarises_existentes),2)-COALESCE(SUM(QTD_CHAFARISES_FUNCIONANDO),2)     " +
        "                  FROM      sisas.sistema_agua s      " +
        "                  WHERE      s.ID_PROVINCIA = p.ID_PROVINCIA     " +
        "              AND      s.ID_MUNICIPIO = m.ID_MUNICIPIO     " +
        "                    AND      s.POSSUI_SISTEMA_AGUA = 1     " +
        "         )  QtddeChafarizesQueNaoFuncionam,        " +
        "       ROUND((( ((SELECT      format(SUM(qtd_chafarises_existentes),2)-format(SUM(QTD_CHAFARISES_FUNCIONANDO),2)     " +
        "                  FROM      sisas.sistema_agua s      " +
        "                  WHERE      s.ID_PROVINCIA = p.ID_PROVINCIA     " +
        "              AND      s.ID_MUNICIPIO = m.ID_MUNICIPIO     " +
        "                    AND      s.POSSUI_SISTEMA_AGUA = 1     " +
        "         ))  *100)/ (SELECT      format(sum(qtd_chafarises_existentes),2)     " +
        "                  FROM      sisas.sistema_agua s      " +
        "                  WHERE      s.ID_PROVINCIA = p.ID_PROVINCIA     " +
        "              AND      s.ID_MUNICIPIO = m.ID_MUNICIPIO     " +
        "                    AND      s.POSSUI_SISTEMA_AGUA = 1     " +
        "         )),2) PercentagemQueNaoFuncionam     " +
        "from sisas.sistema_agua s     " +
        "     inner join sisas.provincia p on s.ID_PROVINCIA = p.ID_PROVINCIA     " +
        "     inner join sisas.municipio m on s.ID_MUNICIPIO = m.ID_MUNICIPIO     " +
        "     where  s.POSSUI_SISTEMA_AGUA = 1  AND s.ID_PROVINCIA = :provinciaId   " +
        "GROUP BY      " +
        "       p.NM_PROVINCIA, p.ID_PROVINCIA,      " +
        "       m.NM_MUNICIPIO, m.ID_MUNICIPIO"+
        " ORDER BY m.NM_MUNICIPIO ASC", nativeQuery = true)
    List<Object[]> funcionamentoAguaChafarizesMunicipal(@Param("provinciaId") Long provinciaId);

    @Query(value = "SELECT          p.NM_PROVINCIA,        " +
        "        m.NM_MUNICIPIO,        " +
        "        c.NM_COMUNA,        " +
        "          (         " +
        "                  SELECT         format(COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0),0)        " +
        "                           FROM         sisas.sistema_agua s         " +
        "                           WHERE         s.ID_PROVINCIA = p.ID_PROVINCIA        " +
        "              AND         s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "              AND s.ID_COMUNA = c.ID_COMUNA        " +
        "                             AND         s.POSSUI_SISTEMA_AGUA = 1        " +
        "            ) NrSistemas,         " +
        "     (         " +
        "                  SELECT         COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)        " +
        "                           FROM         sisas.sistema_agua s         " +
        "                           WHERE         s.ID_PROVINCIA = p.ID_PROVINCIA        " +
        "               AND         s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "               AND s.ID_COMUNA = c.ID_COMUNA        " +
        "                              AND         s.POSSUI_SISTEMA_AGUA = 1        " +
        "               AND         s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'        " +
        "            ) NrSistemasFuncionam,         " +
        "      (         " +
        "                  SELECT         COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)        " +
        "                           FROM         sisas.sistema_agua s         " +
        "                           WHERE         s.ID_PROVINCIA = p.ID_PROVINCIA        " +
        "              AND         s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA        " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA        " +
        "                             AND         s.POSSUI_SISTEMA_AGUA = 1        " +
        "              AND         s.estado_funcionamento_sistema =  'Não está em funcionamento'        " +
        "            )  NrSistemasNaoFuncionam,        " +
        "      ROUND((( ((SELECT         format(COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0),0)        " +
        "                           FROM         sisas.sistema_agua s         " +
        "                           WHERE         s.ID_PROVINCIA = p.ID_PROVINCIA        " +
        "              AND         s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "              AND s.ID_COMUNA = c.ID_COMUNA        " +
        "                             AND         s.POSSUI_SISTEMA_AGUA = 1        " +
        "              AND         s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'        " +
        "            ))  *100)/ (SELECT         format(COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0),0)        " +
        "                           FROM         sisas.sistema_agua s         " +
        "                           WHERE         s.ID_PROVINCIA = p.ID_PROVINCIA        " +
        "              AND         s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA        " +
        "                             AND         s.POSSUI_SISTEMA_AGUA = 1        " +
        "            )),2) PercentagemQueFuncionam,        " +
        "      (         " +
        "                  SELECT         COALESCE(SUM(qtd_chafarises_existentes),0)        " +
        "                           FROM         sisas.sistema_agua s         " +
        "                           WHERE         s.ID_PROVINCIA = p.ID_PROVINCIA        " +
        "              AND         s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "              AND s.ID_COMUNA = c.ID_COMUNA        " +
        "                             AND         s.POSSUI_SISTEMA_AGUA = 1        " +
        "            )  QtddeChafarizesExistentes,           " +
        "      (         " +
        "                  SELECT         COALESCE(SUM(QTD_CHAFARISES_FUNCIONANDO),0)        " +
        "                           FROM         sisas.sistema_agua s         " +
        "                           WHERE         s.ID_PROVINCIA = p.ID_PROVINCIA        " +
        "              AND         s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "              AND s.ID_COMUNA = c.ID_COMUNA        " +
        "                             AND         s.POSSUI_SISTEMA_AGUA = 1        " +
        "            )  QtddeChafarizesQueFuncionam,           " +
        "      (         " +
        "                  SELECT         COALESCE(SUM(qtd_chafarises_existentes),0)-COALESCE(SUM(QTD_CHAFARISES_FUNCIONANDO),0)        " +
        "                           FROM         sisas.sistema_agua s         " +
        "                           WHERE         s.ID_PROVINCIA = p.ID_PROVINCIA        " +
        "              AND         s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA        " +
        "                             AND         s.POSSUI_SISTEMA_AGUA = 1        " +
        "            )  QtddeChafarizesQueNaoFuncionam,           " +
        "       ROUND((( ((SELECT         format(COALESCE(SUM(qtd_chafarises_existentes),0)-COALESCE(SUM(QTD_CHAFARISES_FUNCIONANDO),0),0)        " +
        "                           FROM         sisas.sistema_agua s         " +
        "                           WHERE         s.ID_PROVINCIA = p.ID_PROVINCIA        " +
        "              AND         s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "              AND s.ID_COMUNA = c.ID_COMUNA        " +
        "                             AND         s.POSSUI_SISTEMA_AGUA = 1        " +
        "            ))  *100)/ (SELECT         format(COALESCE(sum(qtd_chafarises_existentes),0),0)        " +
        "                           FROM         sisas.sistema_agua s         " +
        "                           WHERE         s.ID_PROVINCIA = p.ID_PROVINCIA        " +
        "              AND         s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "              AND s.ID_COMUNA = c.ID_COMUNA        " +
        "                             AND         s.POSSUI_SISTEMA_AGUA = 1        " +
        "            )),2) PercentagemQueNaoFuncionam        " +
        " from sisas.sistema_agua s        " +
        "     inner join sisas.provincia p on s.ID_PROVINCIA = p.ID_PROVINCIA        " +
        "     inner join sisas.municipio m on s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "     inner join sisas.comuna c on s.ID_COMUNA = c.ID_COMUNA        " +
        "     where  s.POSSUI_SISTEMA_AGUA = 1 AND s.ID_PROVINCIA = :provinciaId       " +
        "GROUP BY         " +
        "       p.NM_PROVINCIA, p.ID_PROVINCIA,        " +
        "       m.NM_MUNICIPIO, m.ID_MUNICIPIO,        " +
        "       c.NM_COMUNA, C.ID_COMUNA"+
        " ORDER BY c.NM_COMUNA ASC", nativeQuery = true)
    List<Object[]> funcionamentoAguaChafarizesComunal(@Param("provinciaId") Long provinciaId);

    // DASHBOARD
    @Query(value = "SELECT p.NM_PROVINCIA," +
        "    ( " +
        " SELECT COUNT(POSSUI_SISTEMA_AGUA) " +
        " FROM sisas.sistema_agua s " +
        " WHERE s.ID_PROVINCIA = p.ID_PROVINCIA" +
        " AND s.POSSUI_SISTEMA_AGUA = 1" +
        " ) NrSistemas," +
        "    ( " +
        " SELECT COUNT(POSSUI_SISTEMA_AGUA) " +
        " FROM sisas.sistema_agua s " +
        " WHERE s.ID_PROVINCIA = p.ID_PROVINCIA" +
        " AND s.POSSUI_SISTEMA_AGUA = 1" +
        " AND s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'" +
        " ) NrSistemasQueFuncionam," +
        "       ROUND((( ((SELECT COUNT(POSSUI_SISTEMA_AGUA)" +
        "       FROM sisas.sistema_agua s " +
        "       WHERE s.ID_PROVINCIA = p.ID_PROVINCIA" +
        "       AND s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'" +
        "   ))  *100)/ (SELECT COUNT(POSSUI_SISTEMA_AGUA)" +
        "               FROM sisas.sistema_agua s " +
        "               WHERE s.ID_PROVINCIA = p.ID_PROVINCIA" +
        "   )),2) PercentagemQueFuncionam," +
        "   ( " +
        " SELECT COUNT(POSSUI_SISTEMA_AGUA) " +
        " FROM sisas.sistema_agua s " +
        " WHERE s.ID_PROVINCIA = p.ID_PROVINCIA" +
        " AND s.POSSUI_SISTEMA_AGUA = 1" +
        " AND s.estado_funcionamento_sistema = 'Não está em funcionamento'" +
        " ) NrSistemasQueNaoFuncionam,     " +
        "       ROUND((( ((SELECT COUNT(POSSUI_SISTEMA_AGUA)" +
        " FROM sisas.sistema_agua s " +
        " WHERE s.ID_PROVINCIA = p.ID_PROVINCIA" +
        " AND s.estado_funcionamento_sistema = 'Não está em funcionamento'" +
        "   ))  *100)/ (SELECT COUNT(POSSUI_SISTEMA_AGUA)" +
        " FROM sisas.sistema_agua s " +
        " WHERE s.ID_PROVINCIA = p.ID_PROVINCIA" +
        "   )),2) PercentagemQueNaoFuncionam" +
        " FROM sisas.sistema_agua s" +
        "     INNER JOIN sisas.provincia p on s.ID_PROVINCIA = p.ID_PROVINCIA " +
        "     WHERE s.POSSUI_SISTEMA_AGUA = 1 AND s.ID_PROVINCIA = :provinciaId " +
        " GROUP BY " +
        "       p.NM_PROVINCIA," +
        "       p.ID_PROVINCIA ", nativeQuery = true)
    List<Object[]> dadosDashboard(@Param("provinciaId") Long provinciaId);

    //TRATAMENTO SISTEMAS AGUA PROVINCIAL
    @Query(value = "SELECT  p.NM_PROVINCIA,  " +
        "   ( SELECT COUNT(POSSUI_SISTEMA_AGUA)  " +
        "     FROM sisas.sistema_agua s   " +
        "     WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "     AND s.POSSUI_SISTEMA_AGUA = 1  " +
        " ) NrSistemasdeAgua,  " +
        "   (  SELECT COUNT(NM_TP_TRATAMENTO_AGUA)  " +
        "      FROM sisas.sistema_agua s   " +
        "      WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "       AND s.POSSUI_SISTEMA_AGUA = 1  " +
        "       AND s.NM_TP_TRATAMENTO_AGUA = 'Tratamento padrão' " +
        " ) NrSistemasPadrao,  " +
        " (  SELECT COUNT(NM_TP_TRATAMENTO_AGUA)  " +
        "    FROM sisas.sistema_agua s              " +
        "    WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "     AND s.POSSUI_SISTEMA_AGUA = 1  " +
        "     AND s.NM_TP_TRATAMENTO_AGUA = 'Tratamento básico-cloro' " +
        " ) NrSistemasBasicoCloro,  " +
        " (  SELECT COUNT(NM_TP_TRATAMENTO_AGUA)  " +
        "    FROM sisas.sistema_agua s   " +
        "    WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "     AND s.POSSUI_SISTEMA_AGUA = 1  " +
        "     AND s.NM_TP_TRATAMENTO_AGUA = 'Não realiza'" +
        " ) NrSistemasNãoRealiza  " +
        " FROM sisas.sistema_agua s  " +
        "     INNER JOIN sisas.provincia p ON s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        " WHERE s.ID_PROVINCIA = :provinciaId"+
        " GROUP BY   " +
        "       p.NM_PROVINCIA,  " +
        "       p.ID_PROVINCIA  "+
        " ORDER BY p.NM_PROVINCIA ASC", nativeQuery = true)
    List<Object[]> buscaDadosTratamentoSistemasAguaProvincial(@Param("provinciaId") Long provinciaId);

    @Query(value = " SELECT p.NM_PROVINCIA, m.NM_MUNICIPIO,  " +
        "   ( SELECT COUNT(POSSUI_SISTEMA_AGUA)   " +
        "            FROM    sisas.sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "             AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "             AND    s.POSSUI_SISTEMA_AGUA = 1   " +
        ") NrSistemasdeAgua,   " +
        "   ( SELECT COUNT(NM_TP_TRATAMENTO_AGUA)   " +
        "            FROM    sisas.sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "             AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "             AND    s.POSSUI_SISTEMA_AGUA = 1   " +
        "             AND   s.NM_TP_TRATAMENTO_AGUA = 'Tratamento padrão'   " +
        ") NrSistemasPadrao,   " +
        "(     SELECT COUNT(NM_TP_TRATAMENTO_AGUA)   " +
        "            FROM    sisas.sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "             AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "             AND    s.POSSUI_SISTEMA_AGUA = 1   " +
        "             AND   s.NM_TP_TRATAMENTO_AGUA = 'Tratamento básico-cloro'   " +
        ") NrSistemasBasicoCloro,   " +
        "(     SELECT COUNT(NM_TP_TRATAMENTO_AGUA)   " +
        "            FROM    sisas.sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "             AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "             AND    s.POSSUI_SISTEMA_AGUA = 1   " +
        "             AND   s.NM_TP_TRATAMENTO_AGUA = 'Não realiza'   " +
        " ) NrSistemasNãoRealiza   " +
        " FROM sisas.sistema_agua s   " +
        "     INNER JOIN sisas.provincia p on s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "     INNER JOIN sisas.municipio m on s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        " WHERE s.ID_PROVINCIA = :provinciaId"+
        " GROUP BY    " +
        "       p.NM_PROVINCIA,   " +
        "       p.ID_PROVINCIA,    " +
        "       m.NM_MUNICIPIO,   " +
        "       M.ID_MUNICIPIO "+
        " ORDER BY m.NM_MUNICIPIO ASC ", nativeQuery = true)
    List<Object[]> buscaDadosTratamentoSistemasAguaMunicipal(@Param("provinciaId") Long provinciaId);

    //COMUNAL
    @Query(value = " SELECT p.NM_PROVINCIA, m.NM_MUNICIPIO, c.NM_COMUNA,      " +
        "   ( SELECT COUNT(POSSUI_SISTEMA_AGUA)      " +
        "            FROM   sisas.sistema_agua s       " +
        "            WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA      " +
        "             AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO      " +
        "             AND   s.ID_COMUNA =   c.ID_COMUNA      " +
        "             AND   s.POSSUI_SISTEMA_AGUA = 1      " +
        ") NrSistemasdeAgua,      " +
        "   ( SELECT COUNT(NM_TP_TRATAMENTO_AGUA)      " +
        "            FROM   sisas.sistema_agua s       " +
        "            WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA      " +
        "             AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO      " +
        "             AND   s.ID_COMUNA =   c.ID_COMUNA      " +
        "             AND   s.POSSUI_SISTEMA_AGUA = 1      " +
        "             AND   s.NM_TP_TRATAMENTO_AGUA = 'Tratamento padrão'      " +
        ") NrSistemasPadrao,      " +
        "(      SELECT COUNT(NM_TP_TRATAMENTO_AGUA)      " +
        "              FROM  sisas.sistema_agua s       " +
        "              WHERE s.ID_PROVINCIA = p.ID_PROVINCIA      " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO      " +
        "              AND   s.ID_COMUNA =   c.ID_COMUNA      " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1      " +
        "              AND   s.NM_TP_TRATAMENTO_AGUA = 'Tratamento básico-cloro'      " +
        ") NrSistemasBasicoCloro,      " +
        "(  SELECT     COUNT(NM_TP_TRATAMENTO_AGUA)      " +
        "              FROM  sisas.sistema_agua s       " +
        "              WHERE s.ID_PROVINCIA = p.ID_PROVINCIA      " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO      " +
        "              AND   s.ID_COMUNA =   c.ID_COMUNA      " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1      " +
        "              AND   s.NM_TP_TRATAMENTO_AGUA = 'Não realiza'      " +
        ") NrSistemasNãoRealiza      " +
        "FROM sisas.sistema_agua s      " +
        "     INNER JOIN sisas.provincia p ON  s.ID_PROVINCIA = p.ID_PROVINCIA      " +
        "     INNER JOIN sisas.municipio m ON s.ID_MUNICIPIO = m.ID_MUNICIPIO      " +
        "     INNER JOIN sisas.comuna c ON s.ID_COMUNA = c.ID_COMUNA      " +
        " WHERE s.ID_PROVINCIA = :provinciaId"+
        " GROUP BY       " +
        "       p.NM_PROVINCIA, p.ID_PROVINCIA,  m.NM_MUNICIPIO,      " +
        "        m.ID_MUNICIPIO,  c.NM_COMUNA, c.ID_COMUNA "+
        " ORDER BY c.NM_COMUNA ASC ", nativeQuery = true)
    List<Object[]> buscaDadosTratamentoSistemasAguaComunal(@Param("provinciaId") Long provinciaId);

    //FUNCIONAMNETO SISTEMAS DE AGUA
    @Query(value = "SELECT  p.NM_PROVINCIA,        " +
        "          (  SELECT COALESCE(SUM(s.QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)  " +
        "                      FROM  sisas.sistema_agua s         " +
        "                      WHERE s.ID_PROVINCIA = p.ID_PROVINCIA        " +
        "                      AND   s.POSSUI_SISTEMA_AGUA = 1        " +
        "          ) NrPopulcaoBeneficiada,         " +
        "          ( SELECT  COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)        " +
        "                     FROM   sisas.sistema_agua s         " +
        "                     WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA        " +
        "                     AND    s.POSSUI_SISTEMA_AGUA = 1               " +
        "          ) NrSistemas,        " +
        "          ( SELECT  COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)        " +
        "                     FROM   sisas.sistema_agua s         " +
        "                     WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA        " +
        "                     AND    s.POSSUI_SISTEMA_AGUA = 1        " +
        "                     AND    s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'           " +
        "          ) NrSistemasFuncionam,         " +
        "      ( SELECT COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)        " +
        "                     FROM  sisas.sistema_agua s         " +
        "                     WHERE s.ID_PROVINCIA = p.ID_PROVINCIA        " +
        "                     AND   s.POSSUI_SISTEMA_AGUA = 1        " +
        "                     AND   s.estado_funcionamento_sistema =  'Não está em funcionamento'        " +
        "          )  NrSistemasNaoFuncionam,           " +
        "      ROUND((( ((SELECT  COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)        " +
        "                     FROM  sisas.sistema_agua s         " +
        "                     WHERE s.ID_PROVINCIA = p.ID_PROVINCIA        " +
        "                     AND   s.POSSUI_SISTEMA_AGUA = 1        " +
        "                     AND   s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'          " +
        "          ))  *100)/ (SELECT COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)        " +
        "                     FROM  sisas.sistema_agua s         " +
        "                     WHERE s.ID_PROVINCIA = p.ID_PROVINCIA        " +
        "                     AND   s.POSSUI_SISTEMA_AGUA = 1        " +
        "          )),2) PercentagemQueFuncionam,        " +
        "      ROUND((( (( SELECT  COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)        " +
        "                     FROM  sisas.sistema_agua s         " +
        "                     WHERE s.ID_PROVINCIA = p.ID_PROVINCIA        " +
        "                     AND   s.POSSUI_SISTEMA_AGUA = 1        " +
        "                     AND   s.estado_funcionamento_sistema = 'Não está em funcionamento'            " +
        "          ))  *100)/ (SELECT COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)        " +
        "                     FROM  sisas.sistema_agua s         " +
        "                     WHERE s.ID_PROVINCIA = p.ID_PROVINCIA        " +
        "                     AND   s.POSSUI_SISTEMA_AGUA = 1        " +
        "          )),2) PercentagemQueNaoFuncionam              " +
        " FROM sisas.sistema_agua s        " +
        "     INNER JOIN sisas.provincia p ON s.ID_PROVINCIA = p.ID_PROVINCIA        " +
        "     WHERE s.POSSUI_SISTEMA_AGUA = 1 AND s.ID_PROVINCIA = :provinciaId        " +
        " GROUP BY         " +
        "       p.NM_PROVINCIA, p.ID_PROVINCIA"+
        " ORDER BY p.NM_PROVINCIA ASC ", nativeQuery = true)
    List<Object[]> buscaDadosFuncionamentoSistemasAguaProvincial(@Param("provinciaId") Long provinciaId);

    @Query(value = "SELECT           p.NM_PROVINCIA,         " +
        "      m.NM_MUNICIPIO,         " +
        "          (  SELECT          COALESCE(SUM(s.QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)         " +
        "                               FROM          sisas.sistema_agua s          " +
        "                               WHERE          s.ID_PROVINCIA = p.ID_PROVINCIA         " +
        "             AND          s.ID_MUNICIPIO = m.ID_MUNICIPIO         " +
        "                                AND          s.POSSUI_SISTEMA_AGUA = 1         " +
        "             ) NrPopulcaoBeneficiada,          " +
        "          ( SELECT          COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)         " +
        "                              FROM          sisas.sistema_agua s          " +
        "                              WHERE          s.ID_PROVINCIA = p.ID_PROVINCIA         " +
        "            AND          s.ID_MUNICIPIO = m.ID_MUNICIPIO         " +
        "                              AND          s.POSSUI_SISTEMA_AGUA = 1         " +
        "             ) NrSistemas,          " +
        "      (           SELECT          COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)         " +
        "                              FROM          sisas.sistema_agua s          " +
        "                              WHERE          s.ID_PROVINCIA = p.ID_PROVINCIA         " +
        "            AND          s.ID_MUNICIPIO = m.ID_MUNICIPIO         " +
        "                              AND          s.POSSUI_SISTEMA_AGUA = 1         " +
        "            AND          s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'                   " +
        "             ) NrSistemasFuncionam,          " +
        "      ( SELECT          COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)         " +
        "                              FROM          sisas.sistema_agua s          " +
        "                              WHERE          s.ID_PROVINCIA = p.ID_PROVINCIA         " +
        "            AND          s.ID_MUNICIPIO = m.ID_MUNICIPIO         " +
        "                              AND          s.POSSUI_SISTEMA_AGUA = 1         " +
        "            AND          s.estado_funcionamento_sistema =  'Não está em funcionamento'           " +
        "             )  NrSistemasNaoFuncionam,         " +
        "      ROUND((( ((SELECT          COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)         " +
        "                              FROM          sisas.sistema_agua s          " +
        "                              WHERE          s.ID_PROVINCIA = p.ID_PROVINCIA         " +
        "            AND          s.ID_MUNICIPIO = m.ID_MUNICIPIO         " +
        "                           AND          s.POSSUI_SISTEMA_AGUA = 1         " +
        "            AND          s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'         " +
        "             ))  *100)/ (SELECT          COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)         " +
        "                              FROM          sisas.sistema_agua s          " +
        "                              WHERE          s.ID_PROVINCIA = p.ID_PROVINCIA         " +
        "            AND          s.ID_MUNICIPIO = m.ID_MUNICIPIO         " +
        "                                AND          s.POSSUI_SISTEMA_AGUA = 1                 " +
        "             )),2) PercentagemQueFuncionam,         " +
        "      ROUND((( ((SELECT          COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)         " +
        "                              FROM          sisas.sistema_agua s          " +
        "                              WHERE          s.ID_PROVINCIA = p.ID_PROVINCIA         " +
        "            AND          s.ID_MUNICIPIO = m.ID_MUNICIPIO         " +
        "                              AND          s.POSSUI_SISTEMA_AGUA = 1         " +
        "            AND          s.estado_funcionamento_sistema = 'Não está em funcionamento'                       " +
        "             ))  *100)/ (SELECT          COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)         " +
        "                              FROM          sisas.sistema_agua s          " +
        "                              WHERE          s.ID_PROVINCIA = p.ID_PROVINCIA         " +
        "            AND          s.ID_MUNICIPIO = m.ID_MUNICIPIO         " +
        "                              AND          s.POSSUI_SISTEMA_AGUA = 1         " +
        "             )),2) PercentagemQueNaoFuncionam               " +
        " FROM sisas.sistema_agua s          " +
        "     INNER JOIN sisas.provincia p on s.ID_PROVINCIA = p.ID_PROVINCIA         " +
        "     INNER JOIN sisas.municipio m on s.ID_MUNICIPIO = m.ID_MUNICIPIO         " +
        "     WHERE s.POSSUI_SISTEMA_AGUA = 1 AND s.ID_PROVINCIA = :provinciaId        " +
        " GROUP BY          " +
        "       p.NM_PROVINCIA, p.ID_PROVINCIA,          " +
        "       m.NM_MUNICIPIO, m.ID_MUNICIPIO"+
        " ORDER BY m.NM_MUNICIPIO ASC ", nativeQuery = true)
    List<Object[]> buscaDadosFuncionamentoSistemasAguaMunicipal(@Param("provinciaId") Long provinciaId);

    @Query(value = "SELECT p.NM_PROVINCIA,  " +
        "       m.NM_MUNICIPIO,  " +
        "       c.NM_COMUNA,  " +
        "          ( SELECT COALESCE(SUM(s.QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "            AND s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "            AND  c.ID_COMUNA = c.ID_COMUNA   " +
        "   AND s.POSSUI_SISTEMA_AGUA = 1  " +
        "    ) NrPopulcaoBeneficiada,   " +
        "          (  SELECT COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)  " +
        "    FROM sisas.sistema_agua s   " +
        "    WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "             AND s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "             AND  c.ID_COMUNA = c.ID_COMUNA  " +
        "    AND s.POSSUI_SISTEMA_AGUA = 1      " +
        "    ) NrSistemas,   " +
        "      (  SELECT COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "            AND s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "            AND  c.ID_COMUNA = c.ID_COMUNA  " +
        "   AND s.POSSUI_SISTEMA_AGUA = 1  " +
        "            AND s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'    " +
        "    ) NrSistemasFuncionam,   " +
        "      ( SELECT COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "            AND s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "            AND  c.ID_COMUNA = c.ID_COMUNA  " +
        "      AND s.POSSUI_SISTEMA_AGUA = 1  " +
        "            AND s.estado_funcionamento_sistema =  'Não está em funcionamento'  " +
        "    )  NrSistemasNaoFuncionam,  " +
        "      ROUND((( ((SELECT COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "            AND s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "            AND  c.ID_COMUNA = c.ID_COMUNA  " +
        "     AND s.POSSUI_SISTEMA_AGUA = 1  " +
        "             AND s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'           " +
        "    ))  *100)/ (SELECT COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "            AND s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "            AND  c.ID_COMUNA = c.ID_COMUNA  " +
        "   AND s.POSSUI_SISTEMA_AGUA = 1  " +
        "    )),2) PercentagemQueFuncionam,      " +
        "      ROUND((( ((SELECT COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "            AND s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "            AND  c.ID_COMUNA = c.ID_COMUNA  " +
        "     AND s.POSSUI_SISTEMA_AGUA = 1  " +
        "             AND s.estado_funcionamento_sistema = 'Não está em funcionamento'                 " +
        "    ))  *100)/ (SELECT COALESCE(COUNT(POSSUI_SISTEMA_AGUA),0)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "            AND s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "            AND  c.ID_COMUNA = c.ID_COMUNA  " +
        "     AND s.POSSUI_SISTEMA_AGUA = 1        " +
        "    )),2) PercentagemQueNaoFuncionam            " +
        " FROM sisas.sistema_agua s  " +
        "     INNER JOIN sisas.provincia p on s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "     INNER JOIN sisas.municipio m on s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "     INNER JOIN sisas.comuna c on s.ID_COMUNA = c.ID_COMUNA  " +
        "     WHERE  s.POSSUI_SISTEMA_AGUA = 1 AND s.ID_PROVINCIA = :provinciaId  " +
        " GROUP BY   " +
        "       p.NM_PROVINCIA, p.id_provincia,   " +
        "       m.NM_MUNICIPIO, m.id_municipio,   " +
        "       c.NM_COMUNA, c.id_comuna"+
        " ORDER BY c.NM_COMUNA ASC ", nativeQuery = true)
    List<Object[]> buscaDadosFuncionamentoSistemasAguaComunal(@Param("provinciaId") Long provinciaId);


    // BENEFICIARIOS DE AGUA POR FONTE SUBTERRANEA E POR TIPO DE BOMBA (Nivel Comunal)
    @Query(value = "SELECT             p.NM_PROVINCIA,             " +
        "                m.NM_MUNICIPIO,             " +
        "                        c.NM_COMUNA,             " +
        "          (              " +
        "                        SELECT            COUNT(Esquema)             " +
        "                                    FROM            sisas.sistema_agua s              " +
        "                                    WHERE            s.ID_PROVINCIA = p.ID_PROVINCIA             " +
        "              AND            s.ID_MUNICIPIO = m.ID_MUNICIPIO             " +
        "              AND            s.ID_COMUNA = c.ID_COMUNA             " +
        "              AND            s.NM_TP_FONTE= 'Subterrânea'             " +
        "              AND            s.Esquema = 'Poço/cacimba melhorada'             " +
        "               ) NrPocvoMelhorado,              " +
        "          (              " +
        "                                    SELECT            COUNT(Esquema) as Furo              " +
        "                                    FROM            sisas.sistema_agua s              " +
        "                                    WHERE            s.ID_PROVINCIA = p.ID_PROVINCIA             " +
        "                                      AND            s.ID_MUNICIPIO = m.ID_MUNICIPIO             " +
        "                                      AND            s.ID_COMUNA = c.ID_COMUNA             " +
        "                                      AND            s.NM_TP_FONTE = 'Subterrânea'             " +
        "                                      AND            s.ESQUEMA = 'Furo'              " +
        "                        ) Furo,             " +
        "      (              " +
        "                                    SELECT            COUNT(Esquema)  as esquema              " +
        "                                    FROM            sisas.sistema_agua s              " +
        "                                    WHERE            s.ID_PROVINCIA = p.ID_PROVINCIA             " +
        "                                      AND            s.ID_MUNICIPIO = m.ID_MUNICIPIO             " +
        "                                      AND            s.ID_COMUNA = c.ID_COMUNA             " +
        "                                      AND            s.NM_TP_FONTE  = 'Subterrânea'             " +
        "                                      AND            s.ESQUEMA = 'Nascente'             " +
        "                        ) Nascente,             " +
        "     (                         " +
        "           SELECT            count(NM_TIPO_BOMBA)             " +
        "                                    FROM            sisas.sistema_agua s              " +
        "                                    WHERE            s.ID_PROVINCIA = p.ID_PROVINCIA             " +
        "                                      AND            s.ID_MUNICIPIO = m.ID_MUNICIPIO             " +
        "                                      AND            s.ID_COMUNA = c.ID_COMUNA             " +
        "              AND            s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'             " +
        "                                      AND            s.NM_TP_FONTE  = 'Subterrânea'             " +
        "                                     AND            s.NM_TIPO_BOMBA = 'Bomba por gravidade'             " +
        "                        )  TotalGravidade,             " +
        "          (                         " +
        "           SELECT            COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)             " +
        "                                    FROM            sisas.sistema_agua s              " +
        "                                    WHERE            s.ID_PROVINCIA = p.ID_PROVINCIA             " +
        "                                      AND            s.ID_MUNICIPIO = m.ID_MUNICIPIO             " +
        "                                      AND            s.ID_COMUNA = c.ID_COMUNA             " +
        "              AND            s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'             " +
        "                                      AND            s.NM_TP_FONTE  = 'Subterrânea'             " +
        "                                     AND            s.NM_TIPO_BOMBA = 'Bomba por gravidade'             " +
        "                        )  PopulacaoGravidade,             " +
        "     (                         " +
        "           SELECT            COALESCE(count(NM_TIPO_BOMBA),0)             " +
        "                                    FROM            sisas.sistema_agua s              " +
        "                                    WHERE            s.ID_PROVINCIA = p.ID_PROVINCIA             " +
        "                                      AND            s.ID_MUNICIPIO = m.ID_MUNICIPIO             " +
        "                                      AND            s.ID_COMUNA = c.ID_COMUNA             " +
        "             " +
        "              AND            s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'             " +
        "                                      AND            s.NM_TP_FONTE  = 'Subterrânea'             " +
        "                                     AND            s.NM_TIPO_BOMBA = 'Outros'             " +
        "                        ) TotalOutros,             " +
        "          (                         " +
        "           SELECT            COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)             " +
        "                                    FROM            sisas.sistema_agua s              " +
        "                                    WHERE            s.ID_PROVINCIA = p.ID_PROVINCIA             " +
        "                                      AND            s.ID_MUNICIPIO = m.ID_MUNICIPIO             " +
        "                                      AND            s.ID_COMUNA = c.ID_COMUNA             " +
        "              AND            s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'             " +
        "                                      AND            s.NM_TP_FONTE  = 'Subterrânea'             " +
        "                                     AND            s.NM_TIPO_BOMBA = 'Outros'             " +
        "                        )  PopulacaoOutros             " +
        "from sisas.sistema_agua s             " +
        "     inner join sisas.provincia p on s.ID_PROVINCIA = p.ID_PROVINCIA             " +
        "     inner join sisas.municipio m on s.ID_MUNICIPIO = m.ID_MUNICIPIO              " +
        "     INNER JOIN sisas.comuna c on  s.ID_COMUNA = c.ID_COMUNA             " +
        "     where  s.POSSUI_SISTEMA_AGUA = 1  AND s.ID_PROVINCIA = :provinciaId           " +
        "GROUP BY              " +
        "       p.NM_PROVINCIA, p.ID_PROVINCIA     " +
        "       m.ID_MUNICIPIO, m.NM_MUNICIPIO, C.ID_COMUNA, c.NM_COMUNA"+
        " ORDER BY c.NM_COMUNA ASC", nativeQuery = true)
    List<Object[]> buscaDadosBenefAguaFonteSubterraneaTipoBombaComunal(@Param("provinciaId") Long provinciaId);

    @Query(value = "SELECT p.NM_PROVINCIA,            " +
        "                  m.NM_MUNICIPIO,            " +
        "                  m.populacao,            " +
        "      (SELECT COALESCE(COUNT(Esquema),0)            " +
        "              FROM sisas.sistema_agua s             " +
        "              WHERE s.ID_PROVINCIA = p.ID_PROVINCIA            " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO            " +
        "              AND   s.NM_TP_FONTE= 'Subterrânea'            " +
        "              AND   s.Esquema = 'Poço/cacimba melhorada'            " +
        "       ) NrPocvoMelhorado,             " +
        "       ( SELECT COALESCE(COUNT(Esquema),0)            " +
        "                                 WHERE s.ID_PROVINCIA = p.ID_PROVINCIA            " +
        "                                   AND s.ID_MUNICIPIO = m.ID_MUNICIPIO            " +
        "                                   AND s.NM_TP_FONTE = 'Subterrânea'            " +
        "                                   AND s.ESQUEMA = 'Furo'             " +
        "              ) Furo,            " +
        "       ( SELECT COALESCE(COUNT(Esquema),0)            " +
        "                FROM sisas.sistema_agua s             " +
        "                WHERE s.ID_PROVINCIA = p.ID_PROVINCIA            " +
        "                AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO            " +
        "                AND   s.NM_TP_FONTE  = 'Subterrânea'            " +
        "                AND   s.ESQUEMA = 'Nascente'            " +
        "        ) Nascente,             " +
        "        ( SELECT COALESCE(COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA),0)            " +
        "                 FROM  sisas.sistema_agua s             " +
        "                 WHERE s.ID_PROVINCIA = p.ID_PROVINCIA            " +
        "                 AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO            " +
        "                 AND   s.NM_TIPO_BOMBA  = 'Bombagem manual'            " +
        "              AND   s.NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Afridev'            " +
        "        ) Afridev,            " +
        "        ( SELECT COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)            " +
        "                 FROM   sisas.sistema_agua s             " +
        "                 WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA            " +
        "                 AND s.ID_MUNICIPIO = m.ID_MUNICIPIO            " +
        "                 AND           s.NM_TIPO_BOMBA  = 'Bombagem manual'            " +
        "            AND NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Afridev'            " +
        "       ) QtPessoasAcessoAfridev,            " +
        "      ( SELECT COALESCE(COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA),0)            " +
        "                FROM   sisas.sistema_agua s             " +
        "                WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA            " +
        "                AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO            " +
        "                AND    s.NM_TIPO_BOMBA  = 'Bombagem manual'            " +
        "              AND NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Vergnet'            " +
        "       ) Vergnet,             " +
        "       ( SELECT COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)            " +
        "                FROM   sisas.sistema_agua s             " +
        "                WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA            " +
        "                AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO            " +
        "                AND    s.NM_TIPO_BOMBA  = 'Bombagem manual'            " +
        "            AND NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Vergnet'            " +
        "      ) QtPessoasAcessoVergnet,            " +
        "      ( SELECT COALESCE(COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA),0)            " +
        "               FROM    sisas.sistema_agua s             " +
        "               WHERE   s.ID_PROVINCIA = p.ID_PROVINCIA            " +
        "               AND     s.ID_MUNICIPIO = m.ID_MUNICIPIO            " +
        "               AND     s.NM_TIPO_BOMBA  = 'Bombagem manual'            " +
        "              AND NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Volanta'            " +
        "       ) Volanta,            " +
        "       ( SELECT COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)            " +
        "                FROM   sisas.sistema_agua s             " +
        "                WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA            " +
        "                AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO            " +
        "                AND    s.NM_TIPO_BOMBA  = 'Bombagem manual'            " +
        "                AND    s.NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Volanta'            " +
        "      ) QtPessoasAcessoVolanta,            " +
        "      ( SELECT COALESCE(COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA),0)            " +
        "                FROM   sisas.sistema_agua s             " +
        "                WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA            " +
        "                AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO            " +
        "                AND    s.NM_TIPO_BOMBA  = 'Bombagem manual'            " +
        "                AND    s.NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'India Mark II'            " +
        "       ) IndiaMarkII,             " +
        "       ( SELECT COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)            " +
        "                FROM   sisas.sistema_agua s             " +
        "                WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA            " +
        "                AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO            " +
        "                AND    s.NM_TIPO_BOMBA  = 'Bombagem manual'            " +
        "                AND    s.NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'India Mark II'            " +
        "              ) QtPessoasAcessoIndiaMarkII,             " +
        "      ( SELECT  COALESCE(COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA),0)            " +
        "                FROM   sisas.sistema_agua s             " +
        "                WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA            " +
        "                AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO            " +
        "                AND    s.NM_TIPO_BOMBA  = 'Bombagem manual'            " +
        "                AND   s.NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Outros'            " +
        "       ) Outros,             " +
        "       ( SELECT  COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)            " +
        "                FROM   sisas.sistema_agua s             " +
        "                WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA            " +
        "                AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO            " +
        "                AND   s.NM_TIPO_BOMBA  = 'Bombagem manual'            " +
        "                AND   s.NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Outros'            " +
        "       ) QtPessoasAcessoOutros            " +
        "from sisas.sistema_agua s            " +
        "     inner join sisas.provincia p on            " +
        "     s.ID_PROVINCIA = p.ID_PROVINCIA            " +
        "     inner join sisas.municipio m on            " +
        "     p.ID_PROVINCIA = m.ID_PROVINCIA             " +
        "     where  s.POSSUI_SISTEMA_AGUA = 1 AND s.ID_PROVINCIA = :provinciaId        " +
        "GROUP BY             " +
        "       p.NM_PROVINCIA, p.ID_PROVINCIA,             " +
        "       m.ID_MUNICIPIO, s.ID_MUNICIPIO,            " +
        "       s.NM_TP_FONTE, s.ESQUEMA"+
        " ORDER BY m.NM_MUNICIPIO ASC ", nativeQuery = true)
    List<Object[]> buscaDadosBenefAguaFonteSubterraneaTipoBombaMunicipal(@Param("provinciaId") Long provinciaId);

    // BENEFICIARIOS DE AGUA POR FONTE SUBTERRANEA E POR TIPO DE BOMBA MANUAL (Nivel Provincial)
    @Query(value = "SELECT p.NM_PROVINCIA,         " +
        "                  p.populacao,         " +
        "      (SELECT  COALESCE(COUNT(Esquema),0)         " +
        "              FROM  sisas.sistema_agua s          " +
        "              WHERE s.ID_PROVINCIA = p.ID_PROVINCIA         " +
        "              AND s.POSSUI_SISTEMA_AGUA = 1         " +
        "              AND s.NM_TP_FONTE= 'Subterrânea'         " +
        "              AND s.Esquema = 'Poço/cacimba melhorada'         " +
        "             ) NrPocvoMelhorado,          " +
        "      ( SELECT COALESCE(COUNT(Esquema),0)         " +
        "              FROM sisas.sistema_agua s          " +
        "              WHERE s.ID_PROVINCIA = p.ID_PROVINCIA         " +
        "              AND s.POSSUI_SISTEMA_AGUA = 1         " +
        "              AND s.NM_TP_FONTE = 'Subterrânea'         " +
        "              AND s.ESQUEMA = 'Furo'          " +
        "                    ) Furo,         " +
        "      ( SELECT          COALESCE(COUNT(Esquema),0)         " +
        "                              FROM          sisas.sistema_agua s          " +
        "                              WHERE          s.ID_PROVINCIA = p.ID_PROVINCIA         " +
        "              AND s.POSSUI_SISTEMA_AGUA = 1         " +
        "                                AND          s.NM_TP_FONTE  = 'Subterrânea'         " +
        "                                AND          s.ESQUEMA = 'Nascente'         " +
        "                    ) Nascente,         " +
        "        ( SELECT          COALESCE(COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA),0)         " +
        "                              FROM          sisas.sistema_agua s          " +
        "                              WHERE          s.ID_PROVINCIA = p.ID_PROVINCIA         " +
        "              AND s.POSSUI_SISTEMA_AGUA = 1         " +
        "                                AND          s.NM_TIPO_BOMBA  = 'Bombagem manual'         " +
        "              AND NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Afridev'         " +
        "                    ) Afridev,         " +
        "        ( SELECT           COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)         " +
        "                              FROM   sisas.sistema_agua s          " +
        "                              WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA         " +
        "            AND s.POSSUI_SISTEMA_AGUA = 1         " +
        "                                AND          s.NM_TIPO_BOMBA  = 'Bombagem manual'         " +
        "            AND NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Afridev'         " +
        "             ) QtPessoasAcessoAfridev,         " +
        "      ( SELECT          COALESCE(COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA),0)         " +
        "                              FROM          sisas.sistema_agua s          " +
        "                              WHERE          s.ID_PROVINCIA = p.ID_PROVINCIA         " +
        "              AND s.POSSUI_SISTEMA_AGUA = 1         " +
        "                                AND          s.NM_TIPO_BOMBA  = 'Bombagem manual'         " +
        "              AND NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Vergnet'         " +
        "                    ) Vergnet,         " +
        "        ( SELECT           COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)         " +
        "                              FROM   sisas.sistema_agua s          " +
        "                              WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA         " +
        "            AND s.POSSUI_SISTEMA_AGUA = 1         " +
        "                                AND          s.NM_TIPO_BOMBA  = 'Bombagem manual'         " +
        "            AND NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Vergnet'         " +
        "             ) QtPessoasAcessoVergnet,         " +
        "      ( SELECT          COALESCE(COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA),0)         " +
        "                              FROM          sisas.sistema_agua s          " +
        "                              WHERE          s.ID_PROVINCIA = p.ID_PROVINCIA         " +
        "               AND s.POSSUI_SISTEMA_AGUA = 1         " +
        "                                AND          s.NM_TIPO_BOMBA  = 'Bombagem manual'         " +
        "              AND NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Volanta'         " +
        "                    ) Volanta,         " +
        "        ( SELECT           COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)         " +
        "                              FROM   sisas.sistema_agua s          " +
        "                              WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA         " +
        "                                AND          s.NM_TIPO_BOMBA  = 'Bombagem manual'         " +
        "            AND s.POSSUI_SISTEMA_AGUA = 1         " +
        "            AND NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Volanta'         " +
        "             ) QtPessoasAcessoVolanta,         " +
        "      ( SELECT          COALESCE(COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA),0)         " +
        "                              FROM          sisas.sistema_agua s          " +
        "                              WHERE          s.ID_PROVINCIA = p.ID_PROVINCIA         " +
        "                                AND          s.NM_TIPO_BOMBA  = 'Bombagem manual'         " +
        "              AND s.POSSUI_SISTEMA_AGUA = 1         " +
        "              AND NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'India Mark II'         " +
        "                    ) IndiaMarkII,         " +
        "       ( SELECT           COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)         " +
        "                              FROM   sisas.sistema_agua s          " +
        "                              WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA         " +
        "                                AND          s.NM_TIPO_BOMBA  = 'Bombagem manual'         " +
        "            AND s.POSSUI_SISTEMA_AGUA = 1         " +
        "            AND NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'India Mark II'         " +
        "             ) QtPessoasAcessoIndiaMarkII,         " +
        "      ( SELECT          COALESCE(COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA),0)         " +
        "                              FROM          sisas.sistema_agua s          " +
        "                              WHERE          s.ID_PROVINCIA = p.ID_PROVINCIA         " +
        "                                AND          s.NM_TIPO_BOMBA  = 'Bombagem manual'         " +
        "              AND s.POSSUI_SISTEMA_AGUA = 1         " +
        "              AND NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Outros'         " +
        "                    ) Outros,         " +
        "       ( SELECT           COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)         " +
        "                              FROM   sisas.sistema_agua s          " +
        "                              WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA         " +
        "                                AND          s.NM_TIPO_BOMBA  = 'Bombagem manual'         " +
        "            AND s.POSSUI_SISTEMA_AGUA = 1         " +
        "            AND NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Outros'         " +
        "             ) QtPessoasAcessoOutros         " +
        " FROM sisas.sistema_agua s         " +
        "     INNER JOIN sisas.provincia p ON s.ID_PROVINCIA = p.ID_PROVINCIA         " +
        "     WHERE  s.POSSUI_SISTEMA_AGUA = 1 AND s.ID_PROVINCIA = :provinciaId  " +
        " GROUP BY          " +
        "       p.NM_PROVINCIA, p.ID_PROVINCIA "+
        " ORDER BY p.NM_PROVINCIA ASC ", nativeQuery = true)
    List<Object[]> buscaDadosBenefAguaFonteSubterraneaTipoBombaManualProvincial(@Param("provinciaId") Long provinciaId);

    @Query(value = "SELECT            p.NM_PROVINCIA,          " +
        "               m.NM_MUNICIPIO,          " +
        "        m.populacao,          " +
        "          (           " +
        "                      SELECT           COUNT(Esquema)          " +
        "                                 FROM           sisas.sistema_agua s           " +
        "                                 WHERE           s.ID_PROVINCIA = p.ID_PROVINCIA          " +
        "              AND           s.ID_MUNICIPIO = m.ID_MUNICIPIO          " +
        "              AND           s.NM_TP_FONTE= 'Subterrânea'          " +
        "              AND           s.Esquema = 'Poço/cacimba melhorada'          " +
        "              ) NrPocoMelhorado,           " +
        "          (           " +
        "                                 SELECT           COUNT(Esquema) as Furo           " +
        "                                 FROM           sisas.sistema_agua s           " +
        "                                 WHERE           s.ID_PROVINCIA = p.ID_PROVINCIA          " +
        "                                   AND           s.ID_MUNICIPIO = m.ID_MUNICIPIO          " +
        "                                   AND           s.NM_TP_FONTE = 'Subterrânea'          " +
        "                                   AND           s.ESQUEMA = 'Furo'           " +
        "                      ) Furo,          " +
        "      (           " +
        "                                 SELECT           COUNT(Esquema)          " +
        "                                 FROM           sisas.sistema_agua s           " +
        "                                 WHERE           s.ID_PROVINCIA = p.ID_PROVINCIA          " +
        "                                   AND           s.ID_MUNICIPIO = m.ID_MUNICIPIO          " +
        "                                   AND           s.NM_TP_FONTE  = 'Subterrânea'          " +
        "                                   AND           s.ESQUEMA = 'Nascente'          " +
        "                      ) Nascente,          " +
        "         (           " +
        "                                 SELECT           COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA)          " +
        "                                 FROM           sisas.sistema_agua s           " +
        "                                 WHERE           s.ID_PROVINCIA = p.ID_PROVINCIA          " +
        "                                   AND           s.ID_MUNICIPIO = m.ID_MUNICIPIO          " +
        "                                   AND           s.NM_TIPO_BOMBA  = 'Bombagem manual'          " +
        "              AND           s.NM_TP_FONTE  = 'Subterrânea'          " +
        "              AND   s.NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Afridev'          " +
        "                      ) Afridev,          " +
        "         (           " +
        "                         SELECT            COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)          " +
        "                                 FROM   sisas.sistema_agua s           " +
        "                                 WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA          " +
        "                    AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO          " +
        "                                   AND              s.NM_TIPO_BOMBA  = 'Bombagem manual'          " +
        "            AND           s.NM_TP_FONTE  = 'Subterrânea'          " +
        "            AND    s.NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Afridev'          " +
        "              ) QtPessoasAcessoAfridev,          " +
        "      (           " +
        "                                 SELECT           COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA)          " +
        "                                 FROM           sisas.sistema_agua s           " +
        "                                 WHERE           s.ID_PROVINCIA = p.ID_PROVINCIA          " +
        "                                   AND           s.ID_MUNICIPIO = m.ID_MUNICIPIO          " +
        "                                   AND           s.NM_TIPO_BOMBA  = 'Bombagem manual'          " +
        "              AND           s.NM_TP_FONTE  = 'Subterrânea'          " +
        "              AND   s.NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Vergnet'          " +
        "                      ) Vergnet,          " +
        "         (           " +
        "                         SELECT            COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)          " +
        "                                 FROM   sisas.sistema_agua s           " +
        "                                 WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA          " +
        "                    AND s.ID_MUNICIPIO = m.ID_MUNICIPIO          " +
        "                                   AND           s.NM_TIPO_BOMBA  = 'Bombagem manual'          " +
        "            AND           s.NM_TP_FONTE  = 'Subterrânea'          " +
        "            AND NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Vergnet'          " +
        "              ) QtPessoasAcessoVergnet,          " +
        "      (           " +
        "                                 SELECT           COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA)          " +
        "                                 FROM           sisas.sistema_agua s           " +
        "                                 WHERE           s.ID_PROVINCIA = p.ID_PROVINCIA          " +
        "                                   AND           s.ID_MUNICIPIO = m.ID_MUNICIPIO          " +
        "                                   AND           s.NM_TIPO_BOMBA  = 'Bombagem manual'          " +
        "              AND           s.NM_TP_FONTE  = 'Subterrânea'          " +
        "              AND   s.NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Volanta'          " +
        "                      ) Volanta,          " +
        "         (           " +
        "                         SELECT            COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)          " +
        "                                 FROM   sisas.sistema_agua s           " +
        "                                 WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA          " +
        "                    AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO          " +
        "                                   AND              s.NM_TIPO_BOMBA  = 'Bombagem manual'          " +
        "            AND              s.NM_TP_FONTE  = 'Subterrânea'          " +
        "            AND    s.NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Volanta'          " +
        "              ) QtPessoasAcessoVolanta,          " +
        "      (           " +
        "                                 SELECT           COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA)          " +
        "                                 FROM           sisas.sistema_agua s           " +
        "                                 WHERE           s.ID_PROVINCIA = p.ID_PROVINCIA          " +
        "                                   AND           s.ID_MUNICIPIO = m.ID_MUNICIPIO          " +
        "                                   AND           s.NM_TIPO_BOMBA  = 'Bombagem manual'          " +
        "              AND           s.NM_TP_FONTE  = 'Subterrânea'          " +
        "              AND   s.NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'India Mark II'          " +
        "                      ) IndiaMarkII,          " +
        "         (           " +
        "                         SELECT            COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)          " +
        "                                 FROM   sisas.sistema_agua s           " +
        "                                 WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA          " +
        "                    AND s.ID_MUNICIPIO = m.ID_MUNICIPIO          " +
        "                                   AND           s.NM_TIPO_BOMBA  = 'Bombagem manual'          " +
        "            AND           s.NM_TP_FONTE  = 'Subterrânea'          " +
        "            AND NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'India Mark II'          " +
        "              ) QtPessoasAcessoIndiaMarkII,          " +
        "      (           " +
        "                                 SELECT           COUNT(NM_MODELO_BOMBA_MANUAL_UTILIZADA)          " +
        "                                 FROM           sisas.sistema_agua s           " +
        "                                 WHERE           s.ID_PROVINCIA = p.ID_PROVINCIA          " +
        "                                   AND           s.ID_MUNICIPIO = m.ID_MUNICIPIO          " +
        "                                   AND           s.NM_TIPO_BOMBA  = 'Bombagem manual'          " +
        "              AND           s.NM_TP_FONTE  = 'Subterrânea'          " +
        "              AND   s.NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Outros'          " +
        "                      ) Outros,          " +
        "         (           " +
        "                         SELECT            COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)          " +
        "                                 FROM   sisas.sistema_agua s           " +
        "                                 WHERE  s.ID_PROVINCIA = p.ID_PROVINCIA          " +
        "                    AND s.ID_MUNICIPIO = m.ID_MUNICIPIO          " +
        "                                   AND           s.NM_TIPO_BOMBA  = 'Bombagem manual'          " +
        "             AND           s.NM_TP_FONTE  = 'Subterrânea'          " +
        "            AND NM_MODELO_BOMBA_MANUAL_UTILIZADA = 'Outros'          " +
        "              ) QtPessoasAcessoOutros          " +
        "from sisas.sistema_agua s          " +
        "     inner join sisas.provincia p on s.ID_PROVINCIA = p.ID_PROVINCIA          " +
        "     inner join sisas.municipio m on s.ID_MUNICIPIO = m.ID_MUNICIPIO           " +
        "     where  s.POSSUI_SISTEMA_AGUA = 1  AND s.ID_PROVINCIA = :provinciaId       " +
        "GROUP BY           " +
        "       p.NM_PROVINCIA, p.ID_PROVINCIA,          " +
        "       m.ID_MUNICIPIO, m.NM_MUNICIPIO"+
        " ORDER BY m.NM_MUNICIPIO ASC", nativeQuery = true)
    List<Object[]> buscaDadosBenefAguaFonteSubterraneaTipoBombaManualMunicipal(@Param("provinciaId") Long provinciaId);

    //BENEFICIARIOS DE AGUA POR FONTE SUPERFICIAL E POR OPCAO TECNICA
    @Query(value = "SELECT     p.NM_PROVINCIA,   " +
        "        s.NM_FONTE_AGUA_UTILIZADA,   " +
        "        p.populacao,   " +
        "          (    " +
        "        SELECT    COUNT(NM_TIPO_BOMBA)   " +
        "            FROM    sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "              AND    s.NM_TP_FONTE= 'Superficial'   " +
        "              AND    s.NM_TIPO_BOMBA ='Bomba elétrica'   " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1                  " +
        "       ) QtdBomaEletrica,    " +
        "         (    " +
        "           SELECT     COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)   " +
        "            FROM   sistema_agua s    " +
        "              Where   s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "              AND      s.NM_TP_FONTE = 'Superficial'   " +
        "              AND    s.NM_TIPO_BOMBA ='Bomba elétrica'   " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1          " +
        "          ) QtPessoasBenefEletrica,   " +
        "      ROUND((( ((SELECT    ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/p.populacao)   " +
        "            FROM    sisas.sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "              AND     s.NM_TP_FONTE = 'Superficial'   " +
        "              AND    s.NM_TIPO_BOMBA ='Bomba elétrica'   " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1          " +
        "       )))),2) PercentagemAcessoBombaEletrica,   " +
        "      (    " +
        "        SELECT    COUNT(NM_TIPO_BOMBA)   " +
        "            FROM    sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "              AND    s.NM_TP_FONTE= 'Superficial'   " +
        "              AND    s.NM_TIPO_BOMBA ='Bomba a diesel'   " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1                  " +
        "       ) QtdBomaDiesel,    " +
        "         (    " +
        "           SELECT     COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)   " +
        "            FROM   sistema_agua s    " +
        "              Where   s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "              AND      s.NM_TP_FONTE = 'Superficial'   " +
        "              AND    s.NM_TIPO_BOMBA ='Bomba a diesel'   " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1          " +
        "          ) QtPessoasBenefDiesel,   " +
        "      ROUND((( ((SELECT    ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/p.populacao)   " +
        "            FROM    sisas.sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "              AND      s.NM_TP_FONTE = 'Superficial'   " +
        "              AND    s.NM_TIPO_BOMBA ='Bomba a diesel'   " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1          " +
        "       )))),2) PercentagemAcessoBombaDiesel,   " +
        "      (    " +
        "        SELECT    COUNT(NM_TIPO_BOMBA)   " +
        "            FROM    sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "              AND    s.NM_TP_FONTE= 'Superficial'   " +
        "              AND    s.NM_TIPO_BOMBA ='Bomba por gravidade'   " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1                  " +
        "       ) QtdBomaGravidade,    " +
        "         (    " +
        "           SELECT     COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)   " +
        "            FROM   sistema_agua s    " +
        "              Where   s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "              AND      s.NM_TP_FONTE = 'Superficial'   " +
        "              AND    s.NM_TIPO_BOMBA ='Bomba por gravidade'   " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1          " +
        "          ) QtPessoasBenefGravidade,   " +
        "      ROUND((( ((SELECT    ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/p.populacao)   " +
        "            FROM    sisas.sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "              AND      s.NM_TP_FONTE = 'Superficial'   " +
        "              AND    s.NM_TIPO_BOMBA ='Bomba por gravidade'   " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1          " +
        "       )))),2) PercentagemAcessoGravidade   " +
        "from sistema_agua s   " +
        "     inner join provincia p on   " +
        "     s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "    where s.POSSUI_SISTEMA_AGUA = 1  AND s.ID_PROVINCIA = :provinciaId " +
        "GROUP BY    " +
        "       p.NM_PROVINCIA,p.ID_PROVINCIA,   " +
        "       s.NM_FONTE_AGUA_UTILIZADA"+
        " ORDER BY p.NM_PROVINCIA ASC ", nativeQuery = true)
    List<Object[]> buscaDadosBenefFtSubtOptTecnicaProvincial(@Param("provinciaId") Long provinciaId);

    @Query(value = "SELECT      p.NM_PROVINCIA,      " +
        "        m.NM_MUNICIPIO,      " +
        "        s.NM_FONTE_AGUA_UTILIZADA,      " +
        "        m.populacao,      " +
        "          (       " +
        "          SELECT     COUNT(NM_TIPO_BOMBA)      " +
        "               FROM     sistema_agua s       " +
        "               WHERE     s.ID_PROVINCIA = p.ID_PROVINCIA      " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO      " +
        "              AND     s.NM_TP_FONTE= 'Superficial'      " +
        "              AND     s.NM_TIPO_BOMBA ='Bomba elétrica'      " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1                     " +
        "        ) QtdBomaEletrica,       " +
        "         (       " +
        "             SELECT      COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)      " +
        "               FROM   sistema_agua s       " +
        "                 Where   s.ID_PROVINCIA = p.ID_PROVINCIA      " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO      " +
        "              AND       s.NM_TP_FONTE = 'Superficial'      " +
        "              AND     s.NM_TIPO_BOMBA ='Bomba elétrica'      " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1             " +
        "           ) QtPessoasBenefEletrica,      " +
        "      ROUND((( ((SELECT     ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/m.populacao)      " +
        "               FROM     sisas.sistema_agua s       " +
        "               WHERE     s.ID_PROVINCIA = p.ID_PROVINCIA      " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO      " +
        "              AND     s.NM_TP_FONTE = 'Superficial'      " +
        "              AND     s.NM_TIPO_BOMBA ='Bomba elétrica'      " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1             " +
        "        )))),2) PercentagemAcessoBombaEletrica,      " +
        "      (       " +
        "          SELECT     COUNT(NM_TIPO_BOMBA)      " +
        "               FROM     sistema_agua s       " +
        "               WHERE     s.ID_PROVINCIA = p.ID_PROVINCIA      " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO      " +
        "              AND     s.NM_TP_FONTE= 'Superficial'      " +
        "              AND     s.NM_TIPO_BOMBA ='Bomba a diesel'      " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1                     " +
        "        ) QtdBomaDiesel,       " +
        "         (       " +
        "             SELECT      COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)      " +
        "               FROM   sistema_agua s       " +
        "                 Where   s.ID_PROVINCIA = p.ID_PROVINCIA      " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO      " +
        "              AND       s.NM_TP_FONTE = 'Superficial'      " +
        "              AND     s.NM_TIPO_BOMBA ='Bomba a diesel'      " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1             " +
        "           ) QtPessoasBenefDiesel,      " +
        "      ROUND((( ((SELECT     ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/m.populacao)      " +
        "               FROM     sisas.sistema_agua s       " +
        "               WHERE     s.ID_PROVINCIA = p.ID_PROVINCIA      " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO      " +
        "              AND     s.NM_TP_FONTE = 'Superficial'      " +
        "              AND     s.NM_TIPO_BOMBA ='Bomba a diesel'      " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1             " +
        "        )))),2) PercentagemAcessoBombaDiesel,      " +
        "      (       " +
        "          SELECT     COUNT(NM_TIPO_BOMBA)      " +
        "               FROM     sistema_agua s       " +
        "               WHERE     s.ID_PROVINCIA = p.ID_PROVINCIA      " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO      " +
        "              AND     s.NM_TP_FONTE= 'Superficial'      " +
        "              AND     s.NM_TIPO_BOMBA ='Bomba por gravidade'      " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1                     " +
        "        ) QtdBomaGravidade,       " +
        "         (       " +
        "             SELECT      COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)      " +
        "               FROM   sistema_agua s       " +
        "                 Where   s.ID_PROVINCIA = p.ID_PROVINCIA      " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO      " +
        "              AND       s.NM_TP_FONTE = 'Superficial'      " +
        "              AND     s.NM_TIPO_BOMBA ='Bomba por gravidade'      " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1             " +
        "           ) QtPessoasBenefGravidade,      " +
        "      ROUND((( ((SELECT     ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/m.populacao)      " +
        "               FROM     sisas.sistema_agua s       " +
        "               WHERE     s.ID_PROVINCIA = p.ID_PROVINCIA      " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO      " +
        "              AND   s.NM_TP_FONTE = 'Superficial'      " +
        "              AND     s.NM_TIPO_BOMBA ='Bomba por gravidade'      " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1             " +
        "        )))),2) PercentagemAcessoGravidade      " +
        "from sistema_agua s      " +
        "     inner join provincia p on      " +
        "     s.ID_PROVINCIA = p.ID_PROVINCIA      " +
        "     inner join municipio m on      " +
        "     s.ID_MUNICIPIO = m.ID_MUNICIPIO      " +
        "    where s.POSSUI_SISTEMA_AGUA = 1 AND s.ID_PROVINCIA = :provinciaId  " +
        "GROUP BY       " +
        "       p.NM_PROVINCIA, p.ID_PROVINCIA,      " +
        "       m.NM_MUNICIPIO, p.ID_PROVINCIA,      " +
        "       s.NM_FONTE_AGUA_UTILIZADA, m.populacao,      " +
        "       m.ID_MUNICIPIO "+
        " ORDER BY m.NM_MUNICIPIO ASC", nativeQuery = true)
    List<Object[]> buscaDadosBenefFtSubtOptTecnicaMunicipal(@Param("provinciaId") Long provinciaId);

    @Query(value = "SELECT  p.NM_PROVINCIA,  " +
        "        m.NM_MUNICIPIO,  " +
        "        c.NM_COMUNA,  " +
        "        s.NM_FONTE_AGUA_UTILIZADA,  " +
        "        c.populacao,  " +
        "     (  SELECT COUNT(NM_TIPO_BOMBA)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA  " +
        "              AND s.NM_TP_FONTE= 'Superficial'  " +
        "              AND s.NM_TIPO_BOMBA ='Bomba elétrica'  " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1                 " +
        "    ) QtdBomaEletrica,   " +
        "      (  SELECT  COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)  " +
        "   FROM   sisas.sistema_agua s   " +
        "     Where   s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA  " +
        "              AND s.NM_TP_FONTE = 'Superficial'  " +
        "              AND s.NM_TIPO_BOMBA ='Bomba elétrica'  " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1         " +
        "       ) QtPessoasBenefEletrica,  " +
        "      ROUND((( ((SELECT ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/c.populacao)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA  " +
        "              AND s.NM_TP_FONTE = 'Superficial'  " +
        "              AND s.NM_TIPO_BOMBA ='Bomba elétrica'  " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1         " +
        "    )))),2) PercentagemAcessoBombaEletrica,  " +
        "      (  SELECT COUNT(NM_TIPO_BOMBA)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA  " +
        "              AND s.NM_TP_FONTE= 'Superficial'  " +
        "              AND s.NM_TIPO_BOMBA ='Bomba a diesel'  " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1                 " +
        "    ) QtdBomaDiesel,   " +
        "      (  SELECT  COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)  " +
        "   FROM   sisas.sistema_agua s   " +
        "     Where   s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA  " +
        "              AND s.NM_TP_FONTE = 'Superficial'  " +
        "              AND s.NM_TIPO_BOMBA ='Bomba a diesel'  " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1         " +
        "       ) QtPessoasBenefDiesel,  " +
        "      ROUND((( ((SELECT ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/c.populacao)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA  " +
        "              AND   s.NM_TP_FONTE = 'Superficial'  " +
        "              AND s.NM_TIPO_BOMBA ='Bomba a diesel'  " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1         " +
        "    )))),2) PercentagemAcessoBombaDiesel,  " +
        "      (  SELECT COUNT(NM_TIPO_BOMBA)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA  " +
        "              AND s.NM_TP_FONTE= 'Superficial'  " +
        "              AND s.NM_TIPO_BOMBA ='Bomba por gravidade'  " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1                 " +
        "    ) QtdBomaGravidade,   " +
        "      (  SELECT  COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)  " +
        "   FROM   sisas.sistema_agua s   " +
        "     Where   s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA  " +
        "              AND s.NM_TP_FONTE = 'Superficial'  " +
        "              AND s.NM_TIPO_BOMBA ='Bomba por gravidade'  " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1         " +
        "       ) QtPessoasBenefGravidade,  " +
        "      ROUND((( ((SELECT ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/c.populacao)  " +
        "   FROM sisas.sistema_agua s   " +
        "   WHERE s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA  " +
        "              AND s.NM_TP_FONTE = 'Superficial'  " +
        "              AND s.NM_TIPO_BOMBA ='Bomba por gravidade'  " +
        "              AND   s.POSSUI_SISTEMA_AGUA = 1         " +
        "    )))),2) PercentagemAcessoGravidade  " +
        "FROM sisas.sistema_agua s  " +
        "     INNER JOIN sisas.provincia p on s.ID_PROVINCIA = p.ID_PROVINCIA  " +
        "     INNER JOIN sisas.municipio m on s.ID_MUNICIPIO = m.ID_MUNICIPIO  " +
        "     INNER JOIN sisas.comuna c on s.ID_COMUNA = c.ID_COMUNA  " +
        "     WHERE s.POSSUI_SISTEMA_AGUA = 1 AND s.ID_PROVINCIA = :provinciaId  " +
        "GROUP BY   " +
        "       p.NM_PROVINCIA, p.ID_PROVINCIA,   " +
        "       m.NM_MUNICIPIO, m.ID_MUNICIPIO,   " +
        "    c.NM_COMUNA, c.ID_COMUNA,  " +
        "    s.NM_FONTE_AGUA_UTILIZADA" +
        " ORDER BY c.NM_COMUNA ASC", nativeQuery = true)
    List<Object[]> buscaDadosBenefFtSubtOptTecnicaComunal(@Param("provinciaId") Long provinciaId);

    @Query(value = "SELECT        p.NM_PROVINCIA,       " +
        "        m.NM_MUNICIPIO,       " +
        "        m.populacao,       " +
        "          (        " +
        "              SELECT       COALESCE(COUNT(Esquema),0)       " +
        "                     FROM       sisas.sistema_agua s        " +
        "                     WHERE       s.ID_PROVINCIA = p.ID_PROVINCIA       " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "              AND       s.NM_TP_FONTE= 'Subterrânea'       " +
        "              AND       s.Esquema = 'Poço/cacimba melhorada'       " +
        "          ) NrPocvoMelhorado,        " +
        "      (        " +
        "                     SELECT       COALESCE(COUNT(Esquema),0)       " +
        "                     FROM       sisas.sistema_agua s        " +
        "                     WHERE       s.ID_PROVINCIA = p.ID_PROVINCIA       " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "                       AND       s.NM_TP_FONTE = 'Subterrânea'       " +
        "                       AND       s.ESQUEMA = 'Furo'        " +
        "              ) Furo,       " +
        "      (        " +
        "                     SELECT       COALESCE(COUNT(Esquema),0)       " +
        "                     FROM       sisas.sistema_agua s        " +
        "                     WHERE       s.ID_PROVINCIA = p.ID_PROVINCIA       " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "                       AND       s.NM_TP_FONTE  = 'Subterrânea'       " +
        "                       AND       s.ESQUEMA = 'Nascente'       " +
        "              ) Nascente,       " +
        "         (        " +
        "                     SELECT       COALESCE(COUNT(NM_TP_BOMBA_ENERGIA),0)       " +
        "                     FROM       sisas.sistema_agua s        " +
        "                     WHERE       s.ID_PROVINCIA = p.ID_PROVINCIA       " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "              AND       s.NM_TP_FONTE= 'Subterrânea'       " +
        "              AND       s.NM_TIPO_BOMBA = 'Bomba eléctrica'       " +
        "                       AND       s.NM_TP_BOMBA_ENERGIA  = 'Diesel/Motobomba'       " +
        "              ) NrSistemasAdiesel,       " +
        "         (        " +
        "                 SELECT        COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)       " +
        "                     FROM   sisas.sistema_agua s        " +
        "                       Where   s.ID_PROVINCIA = p.ID_PROVINCIA       " +
        "              AND   s.ID_MUNICIPIO   = m.ID_MUNICIPIO        " +
        "              AND         s.NM_TP_FONTE  = 'Subterrânea'       " +
        "              AND       s.NM_TIPO_BOMBA = 'Bomba eléctrica'       " +
        "              AND     s.NM_TP_BOMBA_ENERGIA  = 'Diesel/Motobomba'       " +
        "          ) QtPessoasAcessoBombaDiesel,       " +
        "      ROUND((( ((SELECT       ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/m.populacao)       " +
        "                     FROM       sisas.sistema_agua s        " +
        "                     WHERE       s.ID_PROVINCIA = p.ID_PROVINCIA       " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "              AND       s.NM_TP_FONTE= 'Subterrânea'       " +
        "              AND       s.NM_TIPO_BOMBA = 'Bomba eléctrica'       " +
        "              AND       s.NM_TP_BOMBA_ENERGIA  = 'Diesel/Motobomba'       " +
        "          )))),2) PercentagemAcessoDiesel,       " +
        "      (        " +
        "                     SELECT       COALESCE(COUNT(NM_TP_BOMBA_ENERGIA),0)       " +
        "                     FROM       sisas.sistema_agua s        " +
        "                     WHERE       s.ID_PROVINCIA = p.ID_PROVINCIA       " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "              AND       s.NM_TP_FONTE= 'Subterrânea'       " +
        "              AND       s.NM_TIPO_BOMBA = 'Bomba eléctrica'       " +
        "                       AND       s.NM_TP_BOMBA_ENERGIA  = 'Solar'       " +
        "              ) NrSistemasSolar,       " +
        "         (        " +
        "                 SELECT        COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)       " +
        "                     FROM   sisas.sistema_agua s        " +
        "                       Where   s.ID_PROVINCIA = p.ID_PROVINCIA       " +
        "                 AND  s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "                 AND  s.NM_TP_FONTE = 'Subterrânea'       " +
        "                 AND       s.NM_TIPO_BOMBA = 'Bomba eléctrica'       " +
        "                 AND  s.NM_TP_BOMBA_ENERGIA  = 'Solar'       " +
        "          ) QtPessoasAcessoBombaSolar,       " +
        "      ROUND((( ((SELECT       ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/m.populacao)       " +
        "                     FROM       sisas.sistema_agua s                    " +
        "                     WHERE       s.ID_PROVINCIA = p.ID_PROVINCIA       " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "              AND       s.NM_TP_FONTE= 'Subterrânea'       " +
        "              AND       s.NM_TIPO_BOMBA = 'Bomba eléctrica'       " +
        "              AND       s.NM_TP_BOMBA_ENERGIA  = 'Solar'       " +
        "          )))),2) PercentagemAcessoSolar,       " +
        "   (        " +
        "                     SELECT       COALESCE(COUNT(NM_TP_BOMBA_ENERGIA),0)       " +
        "                     FROM       sistema_agua s        " +
        "                     WHERE       s.ID_PROVINCIA = p.ID_PROVINCIA       " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "              AND       s.NM_TP_FONTE= 'Subterrânea'       " +
        "              AND       s.NM_TIPO_BOMBA = 'Bomba eléctrica'       " +
        "                       AND       s.NM_TP_BOMBA_ENERGIA  = 'Eólica'       " +
        "              ) NrSistemasEolica,       " +
        "         (        " +
        "                 SELECT        COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)       " +
        "                     FROM   sistema_agua s        " +
        "                       Where   s.ID_PROVINCIA = p.ID_PROVINCIA       " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "              AND         s.NM_TP_FONTE = 'Subterrânea'       " +
        "              AND       s.NM_TIPO_BOMBA = 'Bomba eléctrica'       " +
        "              AND     s.NM_TP_BOMBA_ENERGIA  = 'Eólica'       " +
        "          ) QtPessoasAcessoBombaEolica,       " +
        "      ROUND((( ((SELECT       ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/m.populacao)       " +
        "                     FROM       sisas.sistema_agua s        " +
        "                     WHERE       s.ID_PROVINCIA = p.ID_PROVINCIA       " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "              AND       s.NM_TP_FONTE= 'Subterrânea'       " +
        "              AND       s.NM_TIPO_BOMBA = 'Bomba eléctrica'       " +
        "              AND       s.NM_TP_BOMBA_ENERGIA  = 'Eólica'       " +
        "          )))),2) PercentagemAcessoEolica,       " +
        "     (        " +
        "                     SELECT       COALESCE(COUNT(NM_TP_BOMBA_ENERGIA),0)       " +
        "                     FROM       sisas.sistema_agua s        " +
        "                     WHERE       s.ID_PROVINCIA = p.ID_PROVINCIA       " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "              AND       s.NM_TP_FONTE= 'Subterrânea'       " +
        "              AND       s.NM_TIPO_BOMBA = 'Bomba eléctrica'       " +
        "                       AND       s.NM_TP_BOMBA_ENERGIA  = 'Elétrica'       " +
        "              ) NrSistemasEletrica,       " +
        "         (        " +
        "                 SELECT        COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)       " +
        "                     FROM   sisas.sistema_agua s        " +
        "                       Where   s.ID_PROVINCIA = p.ID_PROVINCIA       " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "              AND         s.NM_TP_FONTE = 'Subterrânea'       " +
        "              AND       s.NM_TIPO_BOMBA = 'Bomba eléctrica'       " +
        "              AND     s.NM_TP_BOMBA_ENERGIA  = 'Elétrica'       " +
        "          ) QtPessoasAcessoBombaEletrica,       " +
        "      ROUND((( ((SELECT       ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/m.populacao)       " +
        "                     FROM       sisas.sistema_agua s        " +
        "                     WHERE       s.ID_PROVINCIA = p.ID_PROVINCIA       " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "              AND       s.NM_TP_FONTE = 'Subterrânea'       " +
        "              AND       s.NM_TIPO_BOMBA = 'Bomba eléctrica'       " +
        "              AND       s.NM_TP_BOMBA_ENERGIA  = 'Elétrica'       " +
        "          )))),2) PercentagemAcessoEletrica,       " +
        "        (        " +
        "                     SELECT       COALESCE(COUNT(NM_TP_BOMBA_ENERGIA),0)       " +
        "                     FROM       sisas.sistema_agua s        " +
        "                     WHERE       s.ID_PROVINCIA = p.ID_PROVINCIA       " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "              AND       s.NM_TP_FONTE= 'Subterrânea'       " +
        "              AND       s.NM_TIPO_BOMBA = 'Bomba eléctrica'       " +
        "                       AND       s.NM_TP_BOMBA_ENERGIA  = 'Outros'       " +
        "              ) NrSistemasOutros,       " +
        "         (        " +
        "                 SELECT        COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)       " +
        "                     FROM   sisas.sistema_agua s        " +
        "                       Where   s.ID_PROVINCIA = p.ID_PROVINCIA       " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "              AND         s.NM_TP_FONTE = 'Subterrânea'       " +
        "              AND       s.NM_TIPO_BOMBA = 'Bomba eléctrica'       " +
        "              AND     s.NM_TP_BOMBA_ENERGIA  = 'Outros'       " +
        "          ) QtPessoasAcessoOutros,       " +
        "      ROUND((( ((SELECT       ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/m.populacao)       " +
        "                     FROM       sisas.sistema_agua s        " +
        "                     WHERE       s.ID_PROVINCIA = p.ID_PROVINCIA       " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "              AND       s.NM_TP_FONTE= 'Subterrânea'       " +
        "              AND       s.NM_TIPO_BOMBA = 'Bomba eléctrica'       " +
        "              AND       s.NM_TP_BOMBA_ENERGIA  = 'Outros'       " +
        "          )))),2) PercentagemOutros       " +
        "from sisas.sistema_agua s       " +
        "     inner join sisas.provincia p on s.ID_PROVINCIA = p.ID_PROVINCIA       " +
        "     inner join sisas.municipio m on s.ID_MUNICIPIO = m.ID_MUNICIPIO        " +
        "     where s.POSSUI_SISTEMA_AGUA = 1 AND s.ID_PROVINCIA = :provinciaId       " +
        "GROUP BY        " +
        "       p.NM_PROVINCIA, p.ID_PROVINCIA,  " +
        "       m.NM_MUNICIPIO, m.ID_MUNICIPIO"+
        " ORDER BY m.NM_MUNICIPIO ASC", nativeQuery = true)
    List<Object[]> buscaDadosBenefFtSubtBmbMecanicaMunicipal(@Param("provinciaId") Long provinciaId);

    @Query(value = "SELECT                p.NM_PROVINCIA,              " +
        "        m.NM_MUNICIPIO,              " +
        "        c.NM_COMUNA,              " +
        "        c.populacao,              " +
        "          (               " +
        "                              SELECT               COALESCE(COUNT(Esquema),0)              " +
        "                                             FROM               sisas.sistema_agua s               " +
        "                                             WHERE               s.ID_PROVINCIA = p.ID_PROVINCIA              " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO               " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA              " +
        "              AND               s.NM_TP_FONTE= 'Subterrânea'              " +
        "              AND               s.Esquema = 'Poço/cacimba melhorada'              " +
        "                  ) NrPocvoMelhorado,               " +
        "      (               " +
        "                                             SELECT               COALESCE(COUNT(Esquema),0)              " +
        "                                             FROM               sisas.sistema_agua s               " +
        "                                             WHERE               s.ID_PROVINCIA = p.ID_PROVINCIA              " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO               " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA              " +
        "                                               AND               s.NM_TP_FONTE = 'Subterrânea'              " +
        "                                               AND               s.ESQUEMA = 'Furo'               " +
        "                              ) Furo,              " +
        "      (               " +
        "                                             SELECT               COALESCE(COUNT(Esquema),0)              " +
        "                                             FROM               sisas.sistema_agua s               " +
        "                                             WHERE               s.ID_PROVINCIA = p.ID_PROVINCIA              " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO               " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA              " +
        "                                               AND               s.NM_TP_FONTE  = 'Subterrânea'              " +
        "                                               AND               s.ESQUEMA = 'Nascente'              " +
        "                              ) Nascente,              " +
        "         (               " +
        "                                             SELECT               COALESCE(COUNT(NM_TP_BOMBA_ENERGIA),0)              " +
        "                                             FROM               sisas.sistema_agua s               " +
        "                                             WHERE               s.ID_PROVINCIA = p.ID_PROVINCIA              " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO               " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA              " +
        "              AND               s.NM_TP_FONTE= 'Subterrânea'              " +
        "              AND               s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')              " +
        "                                               AND               s.NM_TP_BOMBA_ENERGIA  = 'Diesel/Motobomba'              " +
        "                              ) NrSistemasAdiesel,              " +
        "         (               " +
        "                                 SELECT                COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)              " +
        "                                             FROM   sisas.sistema_agua s               " +
        "                                               Where   s.ID_PROVINCIA = p.ID_PROVINCIA              " +
        "              AND   s.ID_MUNICIPIO   = m.ID_MUNICIPIO               " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA              " +
        "              AND                 s.NM_TP_FONTE  = 'Subterrânea'              " +
        "              AND               s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')              " +
        "              AND     s.NM_TP_BOMBA_ENERGIA  = 'Diesel/Motobomba'              " +
        "                  ) QtPessoasAcessoBombaDiesel,              " +
        "      ROUND((( ((SELECT               ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/c.populacao)              " +
        "                                             FROM               sisas.sistema_agua s               " +
        "                                             WHERE               s.ID_PROVINCIA = p.ID_PROVINCIA              " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO               " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA              " +
        "              AND               s.NM_TP_FONTE= 'Subterrânea'              " +
        "              AND               s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')              " +
        "              AND               s.NM_TP_BOMBA_ENERGIA  = 'Diesel/Motobomba'              " +
        "                  )))),2) PercentagemAcessoDiesel,              " +
        "      (               " +
        "                                             SELECT               COALESCE(COUNT(NM_TP_BOMBA_ENERGIA),0)              " +
        "                                             FROM               sisas.sistema_agua s               " +
        "                                             WHERE               s.ID_PROVINCIA = p.ID_PROVINCIA              " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO               " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA              " +
        "              AND               s.NM_TP_FONTE= 'Subterrânea'              " +
        "              AND               s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')              " +
        "                                               AND               s.NM_TP_BOMBA_ENERGIA  = 'Solar'              " +
        "                              ) NrSistemasSolar,              " +
        "         (               " +
        "                                 SELECT                COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)              " +
        "                                             FROM   sisas.sistema_agua s               " +
        "                                               Where   s.ID_PROVINCIA = p.ID_PROVINCIA              " +
        "                 AND  s.ID_MUNICIPIO = m.ID_MUNICIPIO               " +
        "                 AND   s.ID_COMUNA = c.ID_COMUNA              " +
        "                 AND  s.NM_TP_FONTE = 'Subterrânea'              " +
        "                 AND               s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')              " +
        "                 AND  s.NM_TP_BOMBA_ENERGIA  = 'Solar'              " +
        "                  ) QtPessoasAcessoBombaSolar,              " +
        "      ROUND((( ((SELECT               ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/c.populacao)              " +
        "                                             FROM               sisas.sistema_agua s                           " +
        "                                             WHERE               s.ID_PROVINCIA = p.ID_PROVINCIA              " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO               " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA              " +
        "              AND               s.NM_TP_FONTE= 'Subterrânea'              " +
        "              AND               s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')              " +
        "              AND               s.NM_TP_BOMBA_ENERGIA  = 'Solar'              " +
        "                  )))),2) PercentagemAcessoSolar,              " +
        "   (               " +
        "                                             SELECT               COALESCE(COUNT(NM_TP_BOMBA_ENERGIA),0)              " +
        "                                             FROM               sisas.sistema_agua s               " +
        "                                             WHERE               s.ID_PROVINCIA = p.ID_PROVINCIA              " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO               " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA              " +
        "              AND               s.NM_TP_FONTE= 'Subterrânea'              " +
        "              AND               s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')              " +
        "                                               AND               s.NM_TP_BOMBA_ENERGIA  = 'Eólica'              " +
        "                              ) NrSistemasEolica,              " +
        "         (               " +
        "                                 SELECT                COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)              " +
        "                                             FROM   sisas.sistema_agua s               " +
        "                                               Where   s.ID_PROVINCIA = p.ID_PROVINCIA              " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO               " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA              " +
        "              AND               s.NM_TP_FONTE = 'Subterrânea'              " +
        "              AND               s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')              " +
        "              AND   s.NM_TP_BOMBA_ENERGIA  = 'Eólica'              " +
        "                  ) QtPessoasAcessoBombaEolica,              " +
        "      ROUND((( ((SELECT               ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/c.populacao)              " +
        "                                             FROM               sisas.sistema_agua s               " +
        "                                             WHERE               s.ID_PROVINCIA = p.ID_PROVINCIA              " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO               " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA              " +
        "              AND               s.NM_TP_FONTE= 'Subterrânea'              " +
        "              AND               s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')              " +
        "              AND               s.NM_TP_BOMBA_ENERGIA  = 'Eólica'              " +
        "                  )))),2) PercentagemAcessoEolica,              " +
        "     (               " +
        "                                             SELECT               COALESCE(COUNT(NM_TP_BOMBA_ENERGIA),0)              " +
        "                                             FROM               sisas.sistema_agua s               " +
        "                                             WHERE               s.ID_PROVINCIA = p.ID_PROVINCIA              " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO               " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA              " +
        "              AND               s.NM_TP_FONTE= 'Subterrânea'              " +
        "              AND               s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')              " +
        "                                               AND               s.NM_TP_BOMBA_ENERGIA  = 'Elétrica'              " +
        "                              ) NrSistemasEletrica,              " +
        "         (               " +
        "                                 SELECT                COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)              " +
        "                                             FROM   sisas.sistema_agua s               " +
        "                                               Where   s.ID_PROVINCIA = p.ID_PROVINCIA              " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO               " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA              " +
        "              AND                 s.NM_TP_FONTE = 'Subterrânea'              " +
        "              AND               s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')              " +
        "              AND     s.NM_TP_BOMBA_ENERGIA  = 'Elétrica'              " +
        "                  ) QtPessoasAcessoBombaEletrica,              " +
        "      ROUND((( ((SELECT               ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/c.populacao)              " +
        "                                             FROM               sisas.sistema_agua s               " +
        "                                             WHERE               s.ID_PROVINCIA = p.ID_PROVINCIA              " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO               " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA              " +
        "              AND               s.NM_TP_FONTE = 'Subterrânea'              " +
        "              AND               s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')              " +
        "              AND               s.NM_TP_BOMBA_ENERGIA  = 'Elétrica'              " +
        "                  )))),2) PercentagemAcessoEletrica,              " +
        "        (               " +
        "                                             SELECT               COALESCE(COUNT(NM_TP_BOMBA_ENERGIA),0)              " +
        "                                             FROM               sisas.sistema_agua s               " +
        "                                             WHERE               s.ID_PROVINCIA = p.ID_PROVINCIA              " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO               " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA              " +
        "              AND               s.NM_TP_FONTE= 'Subterrânea'              " +
        "              AND               s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')              " +
        "                                               AND               s.NM_TP_BOMBA_ENERGIA  = 'Outros'              " +
        "                              ) NrSistemasOutros,              " +
        "         (               " +
        "                                 SELECT                COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)              " +
        "                                             FROM   sisas.sistema_agua s               " +
        "                                               Where   s.ID_PROVINCIA = p.ID_PROVINCIA              " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO               " +
        "              AND   s.ID_COMUNA = c.ID_COMUNA              " +
        "              AND               s.NM_TP_FONTE = 'Subterrânea'              " +
        "              AND               s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')              " +
        "              AND   s.NM_TP_BOMBA_ENERGIA  = 'Outros'              " +
        "                  ) QtPessoasAcessoOutros,              " +
        "      ROUND((( ((SELECT               ((COALESCE(sum(QTD_HABITANTES_ACESSO_SERVICO_AGUA),0)*100)/c.populacao)              " +
        "                                             FROM               sisas.sistema_agua s               " +
        "                                             WHERE               s.ID_PROVINCIA = p.ID_PROVINCIA              " +
        "              AND   s.ID_MUNICIPIO = m.ID_MUNICIPIO               " +
        "              AND               s.NM_TP_FONTE= 'Subterrânea'              " +
        "              AND               s.NM_TIPO_BOMBA  IN('Bombagem manual','Bomba eléctrica')              " +
        "              AND               s.NM_TP_BOMBA_ENERGIA  = 'Outros'              " +
        "                  )))),2) PercentagemOutros              " +
        "from sisas.sistema_agua s              " +
        "     inner join sisas.provincia p on s.ID_PROVINCIA = p.ID_PROVINCIA              " +
        "     inner join sisas.municipio m on s.ID_MUNICIPIO = m.ID_MUNICIPIO               " +
        "     inner join sisas.comuna c on s.ID_COMUNA = c.ID_COMUNA              " +
        "     where s.POSSUI_SISTEMA_AGUA = 1  and s.ID_PROVINCIA = :provinciaId           " +
        "GROUP BY               " +
        "       p.NM_PROVINCIA, p.ID_PROVINCIA,   " +
        "       m.NM_MUNICIPIO, m.ID_MUNICIPIO,   " +
        "       c.NM_COMUNA, c.ID_COMUNA, c.populacao"+
        " ORDER BY c.NM_COMUNA ASC", nativeQuery = true)
    List<Object[]> buscaDadosBenefFtSubtBmbMecanicaComunal(@Param("provinciaId") Long provinciaId);

    //SISTEMAS AGUA FT SUBT BOMBA ENERGIA
    @Query(value = "SELECT     p.NM_PROVINCIA,   " +
        "        m.NM_MUNICIPIO,   " +
        "        c.NM_COMUNA,   " +
        "          (    " +
        "        SELECT    COUNT(Esquema)   " +
        "            FROM    sisas.sistema_agua s    " +
        "             WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "              AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND    s.ID_COMUNA = c.ID_COMUNA   " +
        "              AND    s.POSSUI_SISTEMA_AGUA = 1   " +
        "              AND    s.NM_TP_FONTE= 'Subterrânea'   " +
        "              AND   s.NM_TIPO_BOMBA = 'Bomba de energia'   " +
        "              AND    s.Esquema = 'Poço/cacimba melhorada'   " +
        "       ) PocoMelhorado,    " +
        "      (    " +
        "            SELECT    COUNT(Esquema)   " +
        "            FROM    sisas.sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "              AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND    s.ID_COMUNA = c.ID_COMUNA   " +
        "              AND    s.POSSUI_SISTEMA_AGUA = 1    " +
        "              AND   s.NM_TIPO_BOMBA = 'Bomba de energia'   " +
        "              AND    s.NM_TP_FONTE = 'Subterrânea'   " +
        "              AND    s.ESQUEMA = 'Furo'    " +
        "        ) Furo,   " +
        "        (    " +
        "            SELECT    COUNT(Esquema)   " +
        "            FROM    sisas.sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "               AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "                  AND    s.ID_COMUNA = c.ID_COMUNA   " +
        "               AND    s.POSSUI_SISTEMA_AGUA = 1    " +
        "               AND   s.NM_TIPO_BOMBA = 'Bomba de energia'   " +
        "              AND    s.NM_TP_FONTE  = 'Subterrânea'   " +
        "              AND    s.ESQUEMA = 'Nascente'   " +
        "        ) Nascente,    " +
        "        (   " +
        "        SELECT    COUNT(POSSUI_SISTEMA_AGUA)   " +
        "            FROM    sisas.sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "              AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND    s.ID_COMUNA = c.ID_COMUNA   " +
        "              AND   s.NM_TP_FONTE = 'Subterrânea'   " +
        "              AND   s.NM_TIPO_BOMBA =  'Bombagem manual'   " +
        "              AND    s.NM_TP_BOMBA_ENERGIA in('Diesel/Motobomba','Solar','Eólica','Eléctrica','Outros')   " +
        "       ) TotalSistemas,   " +
        "           (                " +
        "           SELECT    count(POSSUI_SISTEMA_AGUA)   " +
        "            FROM    sisas.sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "              AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND    s.ID_COMUNA = c.ID_COMUNA   " +
        "              AND    s.POSSUI_SISTEMA_AGUA = 1    " +
        "              AND    s.NM_TP_FONTE  = 'Subterrânea'   " +
        "              AND   s.NM_TIPO_BOMBA = 'Bomba de energia'   " +
        "              AND    s.NM_TP_BOMBA_ENERGIA = 'Diesel/Motobomba'   " +
        "        )  TotalSistemasDiesel,   " +
        "        (                " +
        "           SELECT    count(POSSUI_SISTEMA_AGUA)   " +
        "            FROM    sisas.sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "              AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND    s.ID_COMUNA = c.ID_COMUNA   " +
        "                AND    s.POSSUI_SISTEMA_AGUA = 1    " +
        "              AND   s.NM_TIPO_BOMBA = 'Bomba de energia'   " +
        "              AND    s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'   " +
        "                AND    s.NM_TP_FONTE  = 'Subterrânea'   " +
        "             AND    s.NM_TP_BOMBA_ENERGIA = 'Diesel/Motobomba'   " +
        "        )  TotalSistemasDieselFunciona,   " +
        "        (               " +
        "           SELECT    count(POSSUI_SISTEMA_AGUA)   " +
        "            FROM    sisas.sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "               AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "               AND    s.ID_COMUNA = c.ID_COMUNA   " +
        "               AND    s.POSSUI_SISTEMA_AGUA = 1    " +
        "              AND    s.estado_funcionamento_sistema = 'Não está em funcionamento'   " +
        "               AND    s.NM_TP_FONTE  = 'Subterrânea'   " +
        "              AND   s.NM_TIPO_BOMBA = 'Bomba de energia'   " +
        "               AND    s.NM_TP_BOMBA_ENERGIA = 'Diesel/Motobomba'   " +
        "        )  TotalSistemasDieselNaoFunciona,   " +
        "        (                " +
        "           SELECT    count(NM_TP_BOMBA_ENERGIA)   " +
        "            FROM    sisas.sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "              AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "                AND    s.ID_COMUNA = c.ID_COMUNA   " +
        "              AND    s.POSSUI_SISTEMA_AGUA = 1    " +
        "              AND    s.NM_TP_FONTE  = 'Subterrânea'   " +
        "              AND   s.NM_TIPO_BOMBA = 'Bomba de energia'   " +
        "              AND    s.NM_TP_BOMBA_ENERGIA = 'Solar'   " +
        "        )  TotalSistemasSolar,   " +
        "        (                " +
        "           SELECT    count(POSSUI_SISTEMA_AGUA)   " +
        "            FROM    sisas.sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "              AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND    s.ID_COMUNA = c.ID_COMUNA   " +
        "              AND    s.POSSUI_SISTEMA_AGUA = 1    " +
        "              AND    s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'   " +
        "              AND    s.NM_TP_FONTE  = 'Subterrânea'   " +
        "              AND   s.NM_TIPO_BOMBA = 'Bomba de energia'   " +
        "                AND    s.NM_TP_BOMBA_ENERGIA = 'Solar'   " +
        "        )  TotalSistemasSolarFunciona,   " +
        "        (               " +
        "           SELECT    count(POSSUI_SISTEMA_AGUA)   " +
        "            FROM    sisas.sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "              AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND    s.ID_COMUNA = c.ID_COMUNA   " +
        "              AND    s.POSSUI_SISTEMA_AGUA = 1    " +
        "              AND    s.estado_funcionamento_sistema = 'Não está em funcionamento'   " +
        "              AND    s.NM_TP_FONTE  = 'Subterrânea'   " +
        "              AND   s.NM_TIPO_BOMBA = 'Bomba de energia'   " +
        "              AND    s.NM_TP_BOMBA_ENERGIA = 'Solar'   " +
        "        )  TotalSistemasSolarNaoFunciona,   " +
        "        (                " +
        "           SELECT    count(NM_TP_BOMBA_ENERGIA)   " +
        "            FROM    sisas.sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "              AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND    s.ID_COMUNA = c.ID_COMUNA   " +
        "              AND    s.POSSUI_SISTEMA_AGUA = 1    " +
        "              AND    s.NM_TP_FONTE  = 'Subterrânea'   " +
        "              AND   s.NM_TIPO_BOMBA = 'Bomba de energia'   " +
        "              AND    s.NM_TP_BOMBA_ENERGIA = 'Eólica'   " +
        "        )  TotalSistemasEolica,   " +
        "        (                " +
        "           SELECT    count(POSSUI_SISTEMA_AGUA)   " +
        "            FROM    sisas.sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "              AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND    s.ID_COMUNA = c.ID_COMUNA   " +
        "              AND    s.POSSUI_SISTEMA_AGUA = 1    " +
        "              AND    s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'   " +
        "              AND    s.NM_TP_FONTE  = 'Subterrânea'   " +
        "              AND    s.NM_TP_BOMBA_ENERGIA = 'Eólica'   " +
        "              AND   s.NM_TIPO_BOMBA = 'Bomba de energia'   " +
        "        )  TotalSistemasEolicaFunciona,   " +
        "        (               " +
        "           SELECT    count(POSSUI_SISTEMA_AGUA)   " +
        "            FROM    sisas.sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "              AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND    s.ID_COMUNA = c.ID_COMUNA   " +
        "              AND    s.POSSUI_SISTEMA_AGUA = 1    " +
        "              AND    s.estado_funcionamento_sistema = 'Não está em funcionamento'   " +
        "              AND    s.NM_TP_FONTE  = 'Subterrânea'   " +
        "              AND   s.NM_TIPO_BOMBA = 'Bomba de energia'   " +
        "             AND    s.NM_TP_BOMBA_ENERGIA = 'Eólica'   " +
        "        )  TotalSistemasEolicaNaoFunciona,   " +
        "          (                " +
        "           SELECT    count(NM_TP_BOMBA_ENERGIA)   " +
        "            FROM    sisas.sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "              AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND    s.ID_COMUNA = c.ID_COMUNA   " +
        "              AND    s.POSSUI_SISTEMA_AGUA = 1    " +
        "              AND    s.NM_TP_FONTE  = 'Subterrânea'   " +
        "              AND   s.NM_TIPO_BOMBA = 'Bomba de energia'   " +
        "              AND    s.NM_TP_BOMBA_ENERGIA = 'Eléctrica'   " +
        "        )  TotalSistemasElectrica,   " +
        "        (                " +
        "           SELECT    count(POSSUI_SISTEMA_AGUA)   " +
        "            FROM    sisas.sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "              AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND    s.ID_COMUNA = c.ID_COMUNA   " +
        "              AND    s.POSSUI_SISTEMA_AGUA = 1    " +
        "              AND    s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'   " +
        "              AND    s.NM_TP_FONTE  = 'Subterrânea'   " +
        "              AND   s.NM_TIPO_BOMBA = 'Bomba de energia'   " +
        "              AND    s.NM_TP_BOMBA_ENERGIA = 'Eléctrica'   " +
        "        )  TotalSistemasElectricaFunciona,   " +
        "    (               " +
        "           SELECT    count(NM_TP_BOMBA_ENERGIA)  as Total    " +
        "            FROM    sisas.sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "              AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND    s.ID_COMUNA = c.ID_COMUNA   " +
        "              AND    s.POSSUI_SISTEMA_AGUA = 1    " +
        "              AND    s.estado_funcionamento_sistema = 'Não está em funcionamento'   " +
        "               AND    s.NM_TP_FONTE  = 'Subterrânea'   " +
        "              AND   s.NM_TIPO_BOMBA = 'Bomba de energia'   " +
        "             AND    s.NM_TP_BOMBA_ENERGIA = 'Eléctrica'   " +
        "        )  TotalSistemasElectricaNaoFunciona,   " +
        "        (                " +
        "           SELECT    count(NM_TP_BOMBA_ENERGIA)   " +
        "            FROM    sisas.sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "              AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND    s.ID_COMUNA = c.ID_COMUNA   " +
        "              AND    s.POSSUI_SISTEMA_AGUA = 1    " +
        "              AND    s.NM_TP_FONTE  = 'Subterrânea'   " +
        "              AND   s.NM_TIPO_BOMBA = 'Bomba de energia'   " +
        "              AND    s.NM_TP_BOMBA_ENERGIA = 'Outros'   " +
        "        )  TotalSistemasOutros,   " +
        "        (                " +
        "           SELECT    count(POSSUI_SISTEMA_AGUA)   " +
        "            FROM    sisas.sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "              AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND    s.ID_COMUNA = c.ID_COMUNA   " +
        "              AND    s.POSSUI_SISTEMA_AGUA = 1    " +
        "              AND    s.estado_funcionamento_sistema = 'Está em funcionamento (Bom)'   " +
        "               AND    s.NM_TP_FONTE  = 'Subterrânea'   " +
        "              AND   s.NM_TIPO_BOMBA = 'Bomba de energia'   " +
        "              AND    s.NM_TP_BOMBA_ENERGIA = 'Outros'   " +
        "        )  TotalSistemasOutrosFunciona,   " +
        "        (               " +
        "           SELECT    count(POSSUI_SISTEMA_AGUA)  as Total    " +
        "            FROM    sisas.sistema_agua s    " +
        "            WHERE    s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "              AND    s.ID_MUNICIPIO = m.ID_MUNICIPIO   " +
        "              AND    s.ID_COMUNA = c.ID_COMUNA   " +
        "              AND    s.POSSUI_SISTEMA_AGUA = 1    " +
        "              AND    s.estado_funcionamento_sistema = 'Não está em funcionamento'   " +
        "              AND    s.NM_TP_FONTE  = 'Subterrânea'   " +
        "              AND   s.NM_TIPO_BOMBA = 'Bomba de energia'   " +
        "             AND    s.NM_TP_BOMBA_ENERGIA = 'Outros'   " +
        "        )  TotalSistemasOutrosNaoFunciona                 " +
        " from sisas.sistema_agua s   " +
        "     inner join sisas.provincia p on s.ID_PROVINCIA = p.ID_PROVINCIA   " +
        "     inner join sisas.municipio m on p.ID_PROVINCIA = m.ID_PROVINCIA    " +
        "     inner join sisas.comuna c on c.ID_MUNICIPIO = m.ID_MUNICIPIO    " +
        "     WHERE s.POSSUI_SISTEMA_AGUA = 1  AND s.ID_PROVINCIA = :provinciaId  " +
        "       AND    s.NM_TP_FONTE  = 'Subterrânea'   " +
        "       AND  s.NM_TIPO_BOMBA = 'Bomba de energia'   " +
        "GROUP BY    " +
        "       p.NM_PROVINCIA,   " +
        "       m.ID_MUNICIPIO,    " +
        "       c.ID_COMUNA"+
        " ORDER BY m.NM_MUNICIPIO ASC", nativeQuery = true)
    List<Object[]> buscaDadosSistAguafFtSubtBmbEnergiaMunicipal(@Param("provinciaId") Long provinciaId);

}
