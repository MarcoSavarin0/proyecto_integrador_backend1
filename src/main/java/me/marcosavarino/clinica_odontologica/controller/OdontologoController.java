package me.marcosavarino.clinica_odontologica.controller;

import me.marcosavarino.clinica_odontologica.model.Odontologo;
import me.marcosavarino.clinica_odontologica.service.OdontologoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

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
        Odontologo odontologo = odontologoService.buscarPorId(id);
        return odontologo != null ? ResponseEntity.ok(odontologo) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo) {
        Odontologo savedOdontologo = odontologoService.guardarOdontologo(odontologo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOdontologo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Odontologo> actualizarOdontologo(@PathVariable Integer id, @RequestBody Odontologo odontologo) {
        if (odontologoService.buscarPorId(id) != null) {
            odontologo.setId(id);
            odontologoService.odontologoUpdate(odontologo);
            return ResponseEntity.ok(odontologo);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOdontologo(@PathVariable Integer id) {
        if (odontologoService.buscarPorId(id) != null) {
            odontologoService.odontologoDelete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> mostrarTodos() {
        List<Odontologo> odontologos = odontologoService.buscarTodosLosOdontologos();
        return ResponseEntity.ok(odontologos);
    }
}

