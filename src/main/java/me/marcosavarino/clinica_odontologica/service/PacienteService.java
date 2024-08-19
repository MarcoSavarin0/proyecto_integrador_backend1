package me.marcosavarino.clinica_odontologica.service;

import me.marcosavarino.clinica_odontologica.dao.Idao;
import me.marcosavarino.clinica_odontologica.model.Paciente;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {
    private Idao<Paciente> pacienteIdao;

    public PacienteService(Idao<Paciente> pacienteIdao) {
        this.pacienteIdao = pacienteIdao;
    }
    public Paciente guardarPaciente(Paciente paciente){
        return pacienteIdao.guardar(paciente);
    }

    public Paciente buscarPorId(Integer id){
        return pacienteIdao.buscarPorId(id);
    }
}
