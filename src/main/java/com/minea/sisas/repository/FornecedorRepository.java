package com.minea.sisas.repository;

import com.minea.sisas.domain.Fornecedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Fornecedor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>, JpaSpecificationExecutor<Fornecedor> {
    @Query("select f from Fornecedor f where LOWER(f.nmFornecedor) like LOWER(concat(:nome,'%')) " +
        "or LOWER(f.endereco) like LOWER(concat(:nome,'%'))" +
        "or LOWER(f.email) like LOWER(concat(:nome,'%'))" +
        "or LOWER(f.especialidade) like LOWER(concat(:nome,'%'))" +
        "or LOWER(f.contato) like LOWER(concat(:nome,'%'))" +
        "or LOWER(f.numContribuinte) like LOWER(concat(:nome,'%'))")
    Page buscarPorNome(@Param("nome") String nome, Pageable pageable);
}
