package me.marcosavarino.clinica_odontologica.controller;

import me.marcosavarino.clinica_odontologica.dto.request.TurnoModificarDto;
import me.marcosavarino.clinica_odontologica.dto.request.TurnoRequestDto;
import me.marcosavarino.clinica_odontologica.dto.response.TurnoResponseDto;
import me.marcosavarino.clinica_odontologica.entity.Turno;
import me.marcosavarino.clinica_odontologica.service.impl.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/")
    public ResponseEntity<?> guardarTurno(@RequestBody TurnoRequestDto turnoRequestDto) {
        TurnoResponseDto turnoAGuardar = turnoService.guardarTurno(turnoRequestDto);
        return ResponseEntity.ok(turnoAGuardar);
    }

    @GetMapping("/")
    public ResponseEntity<List<TurnoResponseDto>> buscarTodos() {
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> turnoDelete(@PathVariable Integer id) {
        turnoService.eliminarTurno(id);
        return ResponseEntity.status(HttpStatus.OK).body("Turno con el id : " + id + " eliminado");
    }

    @PutMapping("/editar")
    public ResponseEntity<Object> editarTurno(@RequestBody TurnoModificarDto turnoModificarDto) {
        if (turnoService.buscarPorId(turnoModificarDto.getId()) != null) {
            turnoService.modificarTurno(turnoModificarDto);
            return ResponseEntity.ok(turnoModificarDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Hubo un problema al editar el turno!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> turnoPorId(@PathVariable Integer id) {
        Optional<TurnoResponseDto> turnoEncontrado = turnoService.buscarPorId(id);
        if (turnoEncontrado.isPresent()) {
            return ResponseEntity.ok(turnoEncontrado.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Hubo un problema al encontrar el turno!");
        }
    }


}
