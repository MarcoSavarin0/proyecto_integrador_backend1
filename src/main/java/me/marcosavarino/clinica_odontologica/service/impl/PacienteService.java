package me.marcosavarino.clinica_odontologica.service.impl;

import me.marcosavarino.clinica_odontologica.dto.response.*;
import me.marcosavarino.clinica_odontologica.dto.response.ResponsesTurno.Pacientes.PacienteTurnoResponseDto;
import me.marcosavarino.clinica_odontologica.dto.response.ResponsesTurno.Pacientes.TurnosPacienteResponseDto;
import me.marcosavarino.clinica_odontologica.entity.Paciente;
import me.marcosavarino.clinica_odontologica.repository.IPacienteRepository;
import me.marcosavarino.clinica_odontologica.service.IPacienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteService implements IPacienteService {
    private IPacienteRepository pacienteRepository;

    @Autowired
    private ModelMapper modelMapper;

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
    public List<PacienteTurnoResponseDto> buscarTodosLosPacientes() {
        List<Paciente> pacientesList = pacienteRepository.findAll();
        List<PacienteTurnoResponseDto> pacienteDto = new ArrayList<>();
        for (Paciente paciente : pacientesList){
            PacienteTurnoResponseDto pacienteAuxiliar = mapearPaciente(paciente);
            pacienteDto.add(pacienteAuxiliar);
        }
        return pacienteDto;
    }

    @Override
    public void pacienteUpdate(Paciente p) {
        pacienteRepository.save(p);
    }

    @Override
    public void pacienteDelete(Integer id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    public Optional<PacienteTurnoResponseDto> buscarPorIdController(Integer id) {
        Optional<Paciente> pacienteOpt = pacienteRepository.findById(id);
        if (pacienteOpt.isPresent()) {
            PacienteTurnoResponseDto responseDto = mapearPaciente(pacienteOpt.get());
            return Optional.of(responseDto);
        } else {
            return Optional.empty();
        }
    }

    private PacienteTurnoResponseDto mapearPaciente(Paciente paciente) {
        PacienteTurnoResponseDto turnoResponseDto = modelMapper.map(paciente, PacienteTurnoResponseDto.class);
        turnoResponseDto.setDomicilio(modelMapper.map(paciente.getDomicilio(), DomicilioResponseDto.class));
        ModelMapper modelMapper = new ModelMapper();
        List<TurnosPacienteResponseDto> turnosDto = paciente.getTurnoSet().stream().map(turno -> {
            TurnosPacienteResponseDto turnoResponse = modelMapper.map(turno, TurnosPacienteResponseDto.class);
            turnoResponse.setOdontologoResponseDto(modelMapper.map(turno.getOdontologo(), OdontologoResponseDto.class));
            return turnoResponse;
        }).collect(Collectors.toList());
        turnoResponseDto.setTurnos(turnosDto);
        return turnoResponseDto;
    }
}
