package me.marcosavarino.clinica_odontologica.controller;

import me.marcosavarino.clinica_odontologica.dto.response.OdontologoResponseDto;
import me.marcosavarino.clinica_odontologica.dto.response.ResponsesTurno.Odontologos.OdontologoTurnoResponseDto;
import me.marcosavarino.clinica_odontologica.entity.Odontologo;
import me.marcosavarino.clinica_odontologica.service.impl.OdontologoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoTurnoResponseDto> mostrarPorId(@PathVariable Integer id) {
        Optional<OdontologoTurnoResponseDto> odontologo = odontologoService.buscarPorIdController(id);
        return ResponseEntity.ok(odontologo.get());
    }

    @PostMapping
    public ResponseEntity<OdontologoResponseDto> guardarOdontologo(@RequestBody Odontologo odontologo) {
        Odontologo savedOdontologo = odontologoService.guardarOdontologo(odontologo);
        OdontologoResponseDto odontologoResponseDto = new OdontologoResponseDto(savedOdontologo.getId(), savedOdontologo.getNumero_De_Licencia(),
                savedOdontologo.getNombre(), savedOdontologo.getApellido()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(odontologoResponseDto);
    }

    @PutMapping("/editar")
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        odontologoService.odontologoUpdate(odontologo);
        return ResponseEntity.ok(odontologo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Integer id) {
        odontologoService.odontologoDelete(id);
        return ResponseEntity.ok("{\"mensaje\": \"El Odontologo fue modificado\"}");
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> mostrarTodos() {
        List<Odontologo> odontologos = odontologoService.buscarTodosLosOdontologos();
        return ResponseEntity.ok(odontologos);
    }
}

