package me.marcosavarino.clinica_odontologica.controller;

import me.marcosavarino.clinica_odontologica.model.Turno;
import me.marcosavarino.clinica_odontologica.service.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/")
    public ResponseEntity<?> guardarTurno(@RequestBody Turno turno) {
        Turno turnoAGuardar = turnoService.guardarTurno(turno);
        if (turnoAGuardar != null) {
            return ResponseEntity.ok(turnoAGuardar);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El paciente o el odontologo no fueron encontrados");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Turno>> buscarTodos() {
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> turnoDelete(@PathVariable Integer id) {
        if (turnoService.buscarPorId(id) != null) {
            turnoService.eliminarTurno(id);
            return ResponseEntity.status(HttpStatus.OK).body("Turno con el id : " + id + " eliminado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turno con el ID, " + id + " no encontrado. " + HttpStatus.NOT_FOUND);
    }

    @PutMapping("/editar")
    public ResponseEntity<Object> editarTurno(@RequestBody Turno turno) {
        if (turnoService.buscarPorId(turno.getId()) != null) {
            turnoService.modificarTurno(turno);
            return ResponseEntity.ok(turno);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Hubo un problema al editar el turno!");
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> turnoPorId(@PathVariable Integer id){
        Turno turnoEncontrado = turnoService.buscarPorId(id);
        if (turnoEncontrado != null){
            return ResponseEntity.ok(turnoEncontrado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Hubo un problema al encontrar el turno!");
        }
    }


}
