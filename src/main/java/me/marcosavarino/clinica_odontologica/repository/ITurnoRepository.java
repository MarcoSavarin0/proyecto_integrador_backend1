package me.marcosavarino.clinica_odontologica.repository;
import me.marcosavarino.clinica_odontologica.dto.response.TurnoResponseDto;
import me.marcosavarino.clinica_odontologica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Integer> {
    @Query("SELECT t FROM Turno t WHERE t.fecha BETWEEN :fechaInit AND :fechaLimit")
    List<Turno> buscarPorFecha(@Param("fechaInit") LocalDate fechaInit, @Param("fechaLimit") LocalDate fechaLimit);
}
