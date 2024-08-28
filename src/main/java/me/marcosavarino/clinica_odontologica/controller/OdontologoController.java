package me.marcosavarino.clinica_odontologica.controller;

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
    public ResponseEntity<Odontologo> mostrarPorId(@PathVariable Integer id) {
       Optional<Odontologo> odontologo = odontologoService.buscarPorId(id);
        return odontologo.isPresent() ? ResponseEntity.ok(odontologo.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo) {
        Odontologo savedOdontologo = odontologoService.guardarOdontologo(odontologo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOdontologo);
    }

    @PutMapping("/editar")
    public ResponseEntity<Object> actualizarOdontologo( @RequestBody Odontologo odontologo) {
        if (odontologoService.buscarPorId(odontologo.getId()) != null) {
            odontologoService.odontologoUpdate(odontologo);
            return ResponseEntity.ok(odontologo);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Odontologo con ID " + odontologo.getId() + " no encontrado.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Integer id) {
        if (odontologoService.buscarPorId(id).isPresent()) {
            odontologoService.odontologoDelete(id);
            String jsonResponse = "{\"mensaje\": \"El Odontologo fue modificado\"}";
            return ResponseEntity.ok(jsonResponse);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Odontologo con el ID, " + id + " no encontrado. " + HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> mostrarTodos() {
        List<Odontologo> odontologos = odontologoService.buscarTodosLosOdontologos();
        return ResponseEntity.ok(odontologos);
    }
}

