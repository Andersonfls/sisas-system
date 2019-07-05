package com.minea.sisas.repository;

import com.minea.sisas.domain.Emprestimo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Area entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    @Query("select count(id) from Emprestimo")
    Integer buscarEmprestimo();

    @Query("select count(id) from Emprestimo e where e.user.id = :userId and e.status = '0'")
    Integer buscarDevolucaoUser(@Param("userId") Long userId);

    @Query("select count(id) from Emprestimo e where e.user.id = :userId")
    Integer buscarEmprestimoUser(@Param("userId") Long userId);

    @Query("select e from Emprestimo e where e.exemplar.id = :exemplarId and e.status='1' ")
    Emprestimo buscarEmprestimoExemplar(@Param("exemplarId") Long exemplarId);

    @Query("select e from Emprestimo e where e.user.id = :userId")
    Page buscarEmprestimoUserPage(@Param("userId") Long userId, Pageable pageable);

    @Query("select e from Emprestimo e where LOWER(e.userName) like LOWER(concat('%',:userName,'%'))")
    Page buscarPorNome(@Param("userName") String nomeUsuario, Pageable pageable);

    @Query("select e from Emprestimo e where LOWER(e.dataEmprestimo) like LOWER(concat('%',:dataEmprestimo,'%'))")
    Page buscarPorDataEmprestimo(@Param("dataEmprestimo") String dataEmprestimo, Pageable pageable);

    @Query("select e from Emprestimo e where LOWER(e.dataPrev) like LOWER(concat('%',:dataPrev,'%'))")
    Page buscarPorDataPrev(@Param("dataPrev") String dataPrev, Pageable pageable);
}
