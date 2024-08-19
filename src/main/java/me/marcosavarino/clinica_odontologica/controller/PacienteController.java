package me.marcosavarino.clinica_odontologica.controller;

import me.marcosavarino.clinica_odontologica.model.Paciente;
import me.marcosavarino.clinica_odontologica.service.PacienteService;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class PacienteController {
    private PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/")
    public Paciente mostrarPacientePorId(@RequestParam Integer id){
        Paciente paciente = pacienteService.buscarPorId(id);
        return paciente;
    }
}
