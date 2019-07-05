package com.minea.sisas.repository;

import com.minea.sisas.domain.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Spring Data JPA repository for the Reserva entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("select count(id) from Reserva")
    Integer buscarReserva();

    @Query("select r from Reserva r where r.dataReserva >= (?1) and r.dataReserva < (?1 + 1)")
    Page buscarPorData(LocalDate dataReserva, Pageable pageable);

    @Query("select r from Reserva r where LOWER(r.nomeUsuario) like LOWER(concat('%',:nomeUsuario,'%'))")
    Page buscarPorNome(@Param("nomeUsuario") String nomeUsuario, Pageable pageable);

    @Query("select r from Reserva r where LOWER(r.nomeObra) like LOWER(concat('%',:nomeObra,'%'))")
    Page buscarPorObra(@Param("nomeObra") String nomeObra, Pageable pageable);

    @Query("select count(id) from Reserva r where r.user.id = :userId")
    Integer buscarReservaUser(@Param("userId") Long userId);

    @Query("select r from Reserva r where r.user.id = :userId")
    Page buscarReservaUserPage(@Param("userId") Long userId, Pageable pageable);

    @Query("select r from Reserva r where r.obra.id = :obraId and r.status = '1' order by r.dataReserva")
    List<Reserva> findByObraId(@Param("obraId") Long obraId);

}

