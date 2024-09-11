package me.marcosavarino.clinica_odontologica;

import me.marcosavarino.clinica_odontologica.entity.Domicilio;
import me.marcosavarino.clinica_odontologica.entity.Paciente;
import me.marcosavarino.clinica_odontologica.service.impl.PacienteService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional

public class PacienteServiceTest {
    @Autowired
    PacienteService pacienteService;
    Paciente paciente;
    Paciente pacienteDesdeDb;

    @BeforeEach
    void crearPaciente() {
        Domicilio domicilio = new Domicilio(null, "Falsa", 456, "Cipolleti", "Rio Negro");
        paciente = new Paciente();
        paciente.setApellido("Romero");
        paciente.setNombre("Luciana");
        paciente.setDni("56655");
        paciente.setFechaIngreso(LocalDate.of(2024, 7, 16));
        paciente.setDomicilio(domicilio);
        pacienteDesdeDb = pacienteService.guardarPaciente(paciente);
    }

    @Test
    @DisplayName("Testear que un paciente se guarde en la base de datos con su domicilio")
    void caso1() {
        assertNotNull(pacienteDesdeDb.getId());
    }

    @Test
    @DisplayName("Testear que un paciente pueda ser obtenido cuando se envia el id")
    void caso2() {

        Integer id = pacienteDesdeDb.getId();
        Paciente pacienteEncontrado = pacienteService.buscarPorId(id).get();
        assertEquals(id, pacienteEncontrado.getId());
    }

}
