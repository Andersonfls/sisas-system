package com.minea.sisas.repository;

import com.minea.sisas.domain.Obra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the Obra entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ObraRepository extends JpaRepository<Obra, Long> {

    @Query("select count(id) from Obra")
    Integer buscarObra();

//    @Query("select o from Obra o where LOWER(o.nome) like LOWER(concat('%',:nome,'%'))")
//    Page buscarPorNome(@Param("nome") String nome, Pageable pageable);

    @Query(value = "SELECT DISTINCT" +
        " o.id as obraid, o.nome as nomeObra, o.descricao, o.status, t.id as tipoObraId, t.nome as tipoObraNome" +
        " FROM OBRA o" +
        " INNER JOIN editora e on e.id = o.editora_id" +
        " INNER JOIN autor_obra ao on ao.obra_id = o.id" +
        " INNER JOIN autor a on a.id = ao.autor_id" +
        " INNER JOIN tipo_obra t on t.id = o.tipo_obra_id"+
        " WHERE LOWER(o.nome) LIKE :nome " +
        "    OR (LOWER(o.cdd_codigo) LIKE :nome or LOWER(o.cdd_descricao) LIKE :nome) " +
        "    OR (LOWER(o.cdu_codigo) LIKE :nome or LOWER(o.cdu_descricao) LIKE :nome) " +
        "    OR LOWER(o.assunto) LIKE :nome " +
        "    OR LOWER(o.isbn) LIKE :nome " +
        "    OR LOWER(e.nome) LIKE :nome " +
        "    OR LOWER(a.nome) LIKE :nome " +
        " GROUP BY" +
        "    o.id, o.nome, o.descricao, o.status, t.id, t.nome" +
        " ORDER BY o.nome", nativeQuery = true)
    List<Object[]> buscarPorNome(@Param("nome") String nome);

    @Query("select o from Obra o where o.status = '1' order by o.nome")
    List<Obra> findByObraLista();
}
