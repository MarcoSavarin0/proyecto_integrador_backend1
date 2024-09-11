package me.marcosavarino.clinica_odontologica.controller;

import jakarta.validation.Valid;
import me.marcosavarino.clinica_odontologica.dto.response.DomicilioResponseDto;
import me.marcosavarino.clinica_odontologica.dto.response.PacienteResponseSaveDto;
import me.marcosavarino.clinica_odontologica.dto.response.ResponsesTurno.Odontologos.OdontologoTurnoResponseDto;
import me.marcosavarino.clinica_odontologica.dto.response.ResponsesTurno.Pacientes.PacienteTurnoResponseDto;
import me.marcosavarino.clinica_odontologica.entity.Domicilio;
import me.marcosavarino.clinica_odontologica.entity.Paciente;
import me.marcosavarino.clinica_odontologica.service.impl.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteTurnoResponseDto> mostrarPacientePorId(@PathVariable Integer id) {
        Optional<PacienteTurnoResponseDto> paciente = pacienteService.buscarPorIdController(id);
        return paciente.isPresent() ? ResponseEntity.ok(paciente.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PacienteResponseSaveDto> guardarPaciente(@Valid @RequestBody Paciente paciente) {
        Paciente savedPaciente = pacienteService.guardarPaciente(paciente);

        Domicilio domicilioPaciente = savedPaciente.getDomicilio();
        DomicilioResponseDto domicilioResponseDto = new DomicilioResponseDto(
                domicilioPaciente.getId(),
                domicilioPaciente.getCalle(),
                domicilioPaciente.getNumero(),
                domicilioPaciente.getLocalidad(),
                domicilioPaciente.getProvincia()
        );

        PacienteResponseSaveDto pacienteResponseSaveDto = new PacienteResponseSaveDto(
                savedPaciente.getId(),
                savedPaciente.getNombre(),
                savedPaciente.getApellido(),
                savedPaciente.getDni(),
                domicilioResponseDto
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteResponseSaveDto);
    }

    @PutMapping("/editar")
    public ResponseEntity<Paciente> actualizarPaciente(@Valid @RequestBody Paciente paciente) {
            pacienteService.pacienteUpdate(paciente);
            return ResponseEntity.status(HttpStatus.OK).body(paciente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Integer id) {
        pacienteService.pacienteDelete(id);
        return ResponseEntity.ok("{\"mensaje\": \"El Paciente fue modificado\"}");
    }

    @GetMapping
    public ResponseEntity<List<PacienteTurnoResponseDto>> mostrarTodosLosPacientes() {
        List<PacienteTurnoResponseDto> pacientes = pacienteService.buscarTodosLosPacientes();
        return ResponseEntity.ok(pacientes);
    }
}
