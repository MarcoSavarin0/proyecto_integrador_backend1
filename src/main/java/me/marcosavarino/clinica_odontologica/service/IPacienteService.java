package me.marcosavarino.clinica_odontologica.service;

import me.marcosavarino.clinica_odontologica.entity.Paciente;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {
    Paciente guardarPaciente(Paciente paciente);

    Optional<Paciente> buscarPorId(Integer id);

    List<Paciente> buscarTodosLosPacientes();

    void pacienteUpdate(Paciente p);

    void pacienteDelete(Integer id);
}
