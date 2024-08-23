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
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Integer id) {
        if (odontologoService.buscarPorId(id) != null) {
            odontologoService.odontologoDelete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Odontologo con el id : " + id + " eliminado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Odontologo con el ID, " + id + " no encontrado. " + HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> mostrarTodos() {
        List<Odontologo> odontologos = odontologoService.buscarTodosLosOdontologos();
        return ResponseEntity.ok(odontologos);
    }
}

