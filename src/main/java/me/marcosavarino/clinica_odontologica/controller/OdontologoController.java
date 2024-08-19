package me.marcosavarino.clinica_odontologica.controller;

import me.marcosavarino.clinica_odontologica.model.Odontologo;
import me.marcosavarino.clinica_odontologica.service.OdontologoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class OdontologoController {
    private OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }
    @GetMapping("/odontologo/{id}")
    public Odontologo mostrarPorId(@PathVariable Integer id){
        return odontologoService.buscarPorId(id);
    }
}

