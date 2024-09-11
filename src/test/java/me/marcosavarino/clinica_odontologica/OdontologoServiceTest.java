package me.marcosavarino.clinica_odontologica;

import me.marcosavarino.clinica_odontologica.dto.response.ResponsesTurno.Odontologos.OdontologoTurnoResponseDto;
import me.marcosavarino.clinica_odontologica.entity.Odontologo;
import me.marcosavarino.clinica_odontologica.entity.Turno;
import me.marcosavarino.clinica_odontologica.service.impl.OdontologoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
public class OdontologoServiceTest {
    @Autowired
    OdontologoService odontologoService;

    Odontologo odontologo;
    Odontologo odontologoDesdeLaDb;

    @BeforeEach
    void CrearOdontologo() {
        odontologo = new Odontologo();
        odontologo.setNumero_De_Licencia("123");
        odontologo.setNombre("Marco");
        odontologo.setApellido("Savarino");
        odontologoDesdeLaDb = odontologoService.guardarOdontologo(odontologo);
    }

    @Test
    @DisplayName("Testear si se crea correctamente el odontologo")
    void caso1() {
        assertNotNull(odontologoDesdeLaDb.getId());
    }

    @Test
    @DisplayName("Obtener odontologo por id")
    void caso2() {
        Set<Turno> turnos = new HashSet<>();

        odontologoDesdeLaDb.setTurnoSet(turnos);

        // Continúa con la prueba normalmente
        Integer id = odontologoDesdeLaDb.getId();
        Optional<OdontologoTurnoResponseDto> odontologoPorId = odontologoService.buscarPorIdController(id);
        System.out.println("Odontologo encontrado " + odontologoPorId);
        assertEquals(id, odontologoPorId.get().getId());
    }

}
