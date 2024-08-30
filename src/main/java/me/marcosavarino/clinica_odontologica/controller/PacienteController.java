package me.marcosavarino.clinica_odontologica.controller;

import me.marcosavarino.clinica_odontologica.dto.response.ResponsesTurno.Odontologos.OdontologoTurnoResponseDto;
import me.marcosavarino.clinica_odontologica.dto.response.ResponsesTurno.Pacientes.PacienteTurnoResponseDto;
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
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente) {
        Paciente savedPaciente = pacienteService.guardarPaciente(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPaciente);
    }

    @PutMapping("/editar")
    public ResponseEntity<Object> actualizarPaciente(@RequestBody Paciente paciente) {
        if (pacienteService.buscarPorId(paciente.getId()) != null) {
            pacienteService.pacienteUpdate(paciente);
            return ResponseEntity.ok(paciente);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Paciente con ID " + paciente.getId() + " no encontrado.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Integer id) {
        if (pacienteService.buscarPorId(id) != null) {
            pacienteService.pacienteDelete(id);
            String jsonResponse = "{\"mensaje\": \"El Paciente fue modificado\"}";
            return ResponseEntity.ok(jsonResponse);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente con el ID, " + id + " no encontrado. " + HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<PacienteTurnoResponseDto>> mostrarTodosLosPacientes() {
        List<PacienteTurnoResponseDto> pacientes = pacienteService.buscarTodosLosPacientes();
        return ResponseEntity.ok(pacientes);
    }
}
