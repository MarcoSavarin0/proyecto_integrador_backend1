package me.marcosavarino.clinica_odontologica.repository;

import me.marcosavarino.clinica_odontologica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Integer> {
    @Query("SELECT o FROM Odontologo o WHERE o.numero_De_Licencia LIKE %:licencia%")
    Optional<List<Odontologo>> buscarPorLicencia(@Param("licencia") String licencia);
}
