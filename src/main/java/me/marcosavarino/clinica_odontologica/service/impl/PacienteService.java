package me.marcosavarino.clinica_odontologica.service.impl;

import me.marcosavarino.clinica_odontologica.entity.Paciente;
import me.marcosavarino.clinica_odontologica.repository.IPacienteRepository;
import me.marcosavarino.clinica_odontologica.service.IPacienteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PacienteService implements IPacienteService {
    private IPacienteRepository pacienteRepository;

    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public Optional<Paciente> buscarPorId(Integer id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public List<Paciente> buscarTodosLosPacientes() {
        return pacienteRepository.findAll();
    }

    @Override
    public void pacienteUpdate(Paciente p) {
        pacienteRepository.save(p);
    }

    @Override
    public void pacienteDelete(Integer id) {
        pacienteRepository.deleteById(id);
    }
}
