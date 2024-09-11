package me.marcosavarino.clinica_odontologica;

import me.marcosavarino.clinica_odontologica.dto.request.TurnoRequestDto;
import me.marcosavarino.clinica_odontologica.dto.response.TurnoResponseDto;
import me.marcosavarino.clinica_odontologica.entity.Domicilio;
import me.marcosavarino.clinica_odontologica.entity.Odontologo;
import me.marcosavarino.clinica_odontologica.entity.Paciente;
import me.marcosavarino.clinica_odontologica.entity.Turno;
import me.marcosavarino.clinica_odontologica.service.impl.OdontologoService;
import me.marcosavarino.clinica_odontologica.service.impl.PacienteService;
import me.marcosavarino.clinica_odontologica.service.impl.TurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
public class TurnoServiceTest {
    @Autowired
    OdontologoService odontologoService;
    @Autowired
    TurnoService turnoService;
    @Autowired
    PacienteService pacienteService;
    Odontologo odontologo;
    Paciente paciente;
    TurnoRequestDto turno;
    Odontologo odontologoDesdeLaDb;
    @BeforeEach
    void CrearOdontologo() {
        odontologo = new Odontologo();
        odontologo.setNumero_De_Licencia("123");
        odontologo.setNombre("Marco");
        odontologo.setApellido("Savarino");
        odontologoDesdeLaDb = odontologoService.guardarOdontologo(odontologo);
    }
    Paciente pacienteDesdeDb;

    @BeforeEach
    void crearPaciente() {
        Domicilio domicilio = new Domicilio(null, "Falsa", 456, "Cipolleti", "Rio Negro");
        paciente = new Paciente();
        paciente.setApellido("Romero");
        paciente.setNombre("Luciana");
        paciente.setDni("56655444");
        paciente.setFechaIngreso(LocalDate.of(2024, 7, 16));
        paciente.setDomicilio(domicilio);
        pacienteDesdeDb = pacienteService.guardarPaciente(paciente);
    }


    @Test
    @DisplayName("Guardar Turno")
    void caso1(){
        Integer idP = pacienteDesdeDb.getId();
        Integer idO = odontologoDesdeLaDb.getId();
        TurnoRequestDto turnoRequestDto = new TurnoRequestDto(idP, idO, "2024-10-12");
        TurnoResponseDto guardarTurno = turnoService.guardarTurno(turnoRequestDto);
        assertNotNull(guardarTurno);
        assertEquals("2024-10-12", guardarTurno.getFecha());
    }

}
