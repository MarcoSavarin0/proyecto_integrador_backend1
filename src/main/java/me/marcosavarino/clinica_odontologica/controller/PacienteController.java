package me.marcosavarino.clinica_odontologica.controller;

import me.marcosavarino.clinica_odontologica.model.Paciente;
import me.marcosavarino.clinica_odontologica.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> mostrarPacientePorId(@PathVariable Integer id) {
        Paciente paciente = pacienteService.buscarPorId(id);
        return paciente != null ? ResponseEntity.ok(paciente) : ResponseEntity.notFound().build();
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
            return ResponseEntity.status(HttpStatus.OK).body("Paciente con el id : " + id + " eliminado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente con el ID, " + id + " no encontrado. " + HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> mostrarTodosLosPacientes() {
        List<Paciente> pacientes = pacienteService.buscarTodosLosPacientes();
        return ResponseEntity.ok(pacientes);
    }
}
